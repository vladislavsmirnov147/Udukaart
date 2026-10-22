package com.example.udukaart

interface WalkDetection {
    fun subscribe(onWalkDetected: () -> Unit)
    fun unsubscribe()
}

class DemoWalkDetection : WalkDetection {
    private var listener: (() -> Unit)? = null

    override fun subscribe(onWalkDetected: () -> Unit) {
        listener = onWalkDetected
    }

    override fun unsubscribe() {
        listener = null
    }
}
