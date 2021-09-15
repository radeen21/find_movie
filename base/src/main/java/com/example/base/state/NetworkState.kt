package com.example.base.state

class NetworkState(val status: Status, val message: String) {

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    companion object {
        val LOADED: NetworkState = NetworkState(Status.SUCCESS, "Success")
        val LOADING: NetworkState = NetworkState(Status.RUNNING, "Running")
    }
}