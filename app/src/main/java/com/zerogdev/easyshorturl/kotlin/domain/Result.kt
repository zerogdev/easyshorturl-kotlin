package com.zerogdev.easyshorturl.kotlin.domain


sealed class Result<out T> {
    data class Success<T>(val value: T): Result<T>()
    data class Failure(val cause: Throwable): Result<Nothing>()
}

inline fun <T> Result(block: () -> T): Result<T> = try {
    Result.Success(block())
} catch (t: Throwable) {
    Result.Failure(t)
}

fun <T> Result<T>.requireValue(): T = when (this) {
    is Result.Success -> value
    is Result.Failure -> throw cause
}