//package com.example.groupierecycler
//
//import androidx.recyclerview.widget.ItemTouchHelper
//import com.xwray.groupie.kotlinandroidextensions.Item
//import com.xwray.groupie.kotlinandroidextensions.ViewHolder
//import kotlinx.android.synthetic.main.item_body.view.*
//
//class BodyItem(private val data: ListData.BodyData) : Item() {
//    override fun bind(viewHolder: ViewHolder, position: Int) = viewHolder.itemView.run {
//        bodyTextView.text = data.body
//        ratingTextView.text = data.rating
//    }
//
//    override fun getLayout(): Int = R.layout.item_body
//
//    override fun getDragDirs(): Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
//
//    override fun getSwipeDirs(): Int = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//}