package com.example.groupierecycler.item_touch_helper_callback

interface StateChangeListener {
    fun onIdle(position: Int)
    fun onSwipe(position: Int)
    fun onMove(position: Int)
}