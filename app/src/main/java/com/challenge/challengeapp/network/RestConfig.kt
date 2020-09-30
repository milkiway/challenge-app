package com.challenge.challengeapp.network

class RestConfig {
    companion object {
        const val API_BASE_URL = "http://jsonplaceholder.typicode.com/"
        const val DEBUG = false
        const val CONNECT_TIMEOUT_DEBUG = 100L
        const val READ_TIMEOUT_DEBUG = 100L
        const val CONNECT_TIMEOUT_RELEASE = 30L
        const val READ_TIMEOUT_RELEASE = 30L
    }
}