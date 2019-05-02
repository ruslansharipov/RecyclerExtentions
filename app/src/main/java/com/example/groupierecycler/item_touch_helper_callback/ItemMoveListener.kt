package com.example.groupierecycler.item_touch_helper_callback

interface ItemMoveListener<T> {
    fun onItemMove(item: T, from: Int, to: Int)
}