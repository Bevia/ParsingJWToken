package org.example
import java.nio.charset.StandardCharsets
import java.util.*

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
    val payload = decodeBase64(parts[1])

    println("Header: $header")
    println("Payload: $payload")
}

fun decodeBase64(encodedString: String): String {
    val decodedBytes = Base64.getUrlDecoder().decode(encodedString)
    return String(decodedBytes, StandardCharsets.UTF_8)
}