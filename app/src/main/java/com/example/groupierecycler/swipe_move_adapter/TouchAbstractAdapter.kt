package com.example.groupierecycler.swipe_move_adapter

import com.example.groupierecycler.abstract_adapter.AbstractAdapter

abstract class TouchAbstractAdapter<T>
    : AbstractAdapter<T>(), ItemTouchAdapter {

    override fun onItemMoved(from: Int, to: Int) {
        items.swap(from, to)
        notifyItemMoved(from, to)
    }

    override fun onItemRemoved(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}