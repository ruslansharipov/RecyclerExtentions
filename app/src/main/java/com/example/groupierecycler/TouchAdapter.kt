package com.example.groupierecycler

import com.example.groupierecycler.abstract_adapter.AbstractAdapter
import com.example.groupierecycler.swipe_move_adapter.TouchAbstractAdapter
import kotlinx.android.synthetic.main.item_body.view.*

class TouchAdapter(
    override val bodyLayoutRes: Int,
    override var items: MutableList<ListData.BodyData>
) : TouchAbstractAdapter<ListData.BodyData>() {

    override fun onBindViewHolder(holder: BodyHolder, position: Int) = with(holder.itemView) {
        val item = items[position]
        ratingTextView.text = item.rating
        bodyTextView.text = item.body
    }
}

class JustAdapter (
    override var items: MutableList<ListData.BodyData>
): AbstractAdapter<ListData.BodyData>(){
    override val bodyLayoutRes: Int = R.layout.item_body

    override fun onBindViewHolder(holder: BodyHolder, position: Int) = with(holder.itemView) {
        val item = items[position]
        ratingTextView.text = item.rating
        bodyTextView.text = item.body
    }
}