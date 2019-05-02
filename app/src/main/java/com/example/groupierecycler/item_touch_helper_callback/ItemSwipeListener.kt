package com.example.groupierecycler.item_touch_helper_callback

interface ItemSwipeListener<T> {
    fun onItemSwipe(item: T, position: Int)
}