package com.example.domain

class DefaultErrorBundle(
    override val exception: Exception?,
    override val message: String = DEFAULT_ERROR_MESSAGE
) : Throwable(), ErrorBundle {
    companion object {
        const val DEFAULT_ERROR_MESSAGE =
            "Maaf, Terjadi masalah pada sistem kami, mohon coba kembali"
    }
}