package com.example.groupierecycler.item_touch_helper_callback

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.groupierecycler.TAG
import com.example.groupierecycler.swipe_move_adapter.TouchAbstractAdapter

class SimpleTouchHelperCallback<T>(
    private val abstractAdapter: TouchAbstractAdapter<T>
) : ItemTouchHelper.Callback() {

    var moveListener: ItemMoveListener<T>? = null
    var swipeListener: ItemSwipeListener<T>? = null
    var stateListener: StateChangeListener? = null
    var isSwipeEnabled: Boolean = false
    var isLongPressEnabled: Boolean = false

    override fun isLongPressDragEnabled(): Boolean = isLongPressEnabled

    override fun isItemViewSwipeEnabled(): Boolean = isSwipeEnabled

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipeFlags = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        val moveFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        return when {
            isSwipeEnabled && isLongPressEnabled -> makeMovementFlags(moveFlags, swipeFlags)
            isLongPressEnabled -> makeMovementFlags(moveFlags, 0)
            else -> makeMovementFlags(0, swipeFlags)
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val from = viewHolder.adapterPosition
        val to = target.adapterPosition
        val item = abstractAdapter.items[from]
        abstractAdapter.onItemMoved(from, to)
        moveListener?.onItemMove(item, from, to)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val item = abstractAdapter.items[position]
        abstractAdapter.onItemRemoved(position)
        swipeListener?.onItemSwipe(item, position)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        //super.onSelectedChanged(viewHolder, actionState)
        viewHolder?.adapterPosition?.let {
            when (actionState){
                0 -> stateListener?.onIdle(it)
                1 -> stateListener?.onSwipe(it)
                else -> stateListener?.onMove(it)
            }
        }
    }
}


const val ACTION_STATE_IDLE = 0

/**
 * A View is currently being swiped.
 */
const val ACTION_STATE_SWIPE = 1

/**
 * A View is currently being dragged.
 */
const val ACTION_STATE_DRAG = 2