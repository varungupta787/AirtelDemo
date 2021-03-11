package com.airtel.demo

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback

class ElapsedTimeIdlingResource(waitingTime: Long) : IdlingResource {
    private val startTime: Long
    private val waitingTime: Long
    private var resourceCallback: ResourceCallback? = null

    init {
        this.startTime = System.currentTimeMillis()
        this.waitingTime = waitingTime
    }

    override fun getName(): String {
        return ElapsedTimeIdlingResource::class.java.name + ":" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(
            resourceCallback: ResourceCallback) {
        this.resourceCallback = resourceCallback
    }
}