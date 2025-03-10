package com.example.cashapp.data

/**
 * Return Http Codes to user understandable error messages
 */
fun Int.toErrorMessage(errorBody: String? = null): String {
    val error = when (this) {
        400 -> "Bad request. Please check your input."
        401 -> "Unauthorized access. Please log in again."
        403 -> "Access forbidden. You don't have permission."
        404 -> "Requested data not found."
        408 -> "Request timed out. Please try again."
        in 500..599 -> "Server error. Please try again later."
        else -> errorBody ?: "Unknown error occurred."
    }
    return "HTTP $this: $error"
}
