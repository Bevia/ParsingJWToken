package org.example
import java.nio.charset.StandardCharsets
import java.util.*
import kotlinx.serialization.json.*

//How to parse JWT Token
/*
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbWFydHBvcyIsImdydCI6WyJwb3M6c3RhdHVzIiwicG9zOmNvbmZpZ3VyYXRpb24iLCJtYnVzOnBvcy5wYXltZW50LnB1c2giLCJwb3M6ZmluYWxpemUtb3JkZXIiLCJvcmRlcnM6Y3JlYXRlIl0sIm1pZCI6MTAwMTAwMSwiaWF0IjoxNzEwNTE0MjMxLCJqdGkiOiI3OTk0NiIsInRpZCI6IjAwMDAwME5uIiwic2lkIjo5MTI3OX0.QI1iPp9T1Uuh8QnhblLfT-3_gX0EqakNBT4pKMrorG4
 */
fun main() {
    val jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbWFydHBvcyIsImdydCI6WyJwb3M6c3RhdHVzIiwicG9zOmNvbmZpZ3VyYXRpb24iLCJtYnVzOnBvcy5wYXltZW50LnB1c2giLCJwb3M6ZmluYWxpemUtb3JkZXIiLCJvcmRlcnM6Y3JlYXRlIl0sIm1pZCI6MTAwMTAwMSwiaWF0IjoxNzEwNTE0MjMxLCJqdGkiOiI3OTk0NiIsInRpZCI6IjAwMDAwME5uIiwic2lkIjo5MTI3OX0.QI1iPp9T1Uuh8QnhblLfT-3_gX0EqakNBT4pKMrorG4"

    val parts = jwt.split(".")
    if (parts.size != 3) {
        println("Invalid JWT format")
        return
    }

    val header = decodeBase64(parts[0])
    //val payload = decodeBase64(parts[1])
    val payload = decodeBase64UrlSafe(parts[1])

    val tid = parseTid(payload)

    println("Header: $header")
    println("Payload: $payload")
    println("Tenant ID: $tid")
}

fun decodeBase64(encodedString: String): String {
    val decodedBytes = Base64.getUrlDecoder().decode(encodedString)
    return String(decodedBytes, StandardCharsets.UTF_8)
}

fun decodeBase64UrlSafe(encodedString: String): String {
    // Replace underscores with pluses and hyphens with slashes, as required by the URL-safe Base64 decoding
    val urlSafeEncodedString = encodedString.replace('_', '/').replace('-', '+')
    val decodedBytes = Base64.getDecoder().decode(urlSafeEncodedString)
    return String(decodedBytes, StandardCharsets.UTF_8)
}

fun parseTid(payload: String): String? {
    val jsonElement = Json.parseToJsonElement(payload)
    return jsonElement.jsonObject["tid"]?.jsonPrimitive?.content
}

