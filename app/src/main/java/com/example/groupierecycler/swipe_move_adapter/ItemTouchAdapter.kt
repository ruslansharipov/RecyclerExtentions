package com.example.groupierecycler.swipe_move_adapter

interface ItemTouchAdapter{
    fun onItemMoved(from: Int, to: Int)
    fun onItemRemoved(position: Int)
}