package com.example.groupierecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groupierecycler.item_touch_helper_callback.ItemMoveListener
import com.example.groupierecycler.item_touch_helper_callback.ItemSwipeListener
import com.example.groupierecycler.item_touch_helper_callback.SimpleTouchHelperCallback
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "MAIN_ACTIVITY"

class MainActivity : AppCompatActivity() {

    val adapter = TouchAdapter(R.layout.item_body, mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh.setOnRefreshListener { generateSomeData() }

        initRecycler()
        generateSomeData()
    }

    private fun initRecycler() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemTouchHelperCallback = SimpleTouchHelperCallback(adapter).apply {
            isLongPressEnabled = true
            isSwipeEnabled = true
            moveListener = object : ItemMoveListener<ListData.BodyData> {
                override fun onItemMove(item: ListData.BodyData, from: Int, to: Int) {
                    Log.d(TAG, "${item.body} moved from $from to $to")
                }
            }
            swipeListener = object: ItemSwipeListener<ListData.BodyData>{
                override fun onItemSwipe(item: ListData.BodyData, position: Int) {
                    Log.d(TAG, "${item.body} removed from $position")
                }
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
    }

    private fun generateSomeData() {
        swipeRefresh.isRefreshing = true
        val list = List(100) {
            ListData.BodyData("#$it", "body item $it")
        }.toMutableList()
        adapter.items = list
        adapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }
}


//val list = List<ListData.BodyData>(100) {
//            ListData.BodyData("#$it", "body #$it")
//        }
//        val bodyItems = list.map { BodyItem(it) }.toMutableList()
//
//        val adapter = GroupAdapter<ViewHolder>()
//        val layoutManager = GridLayoutManager(this, adapter.spanCount).apply {
//            spanSizeLookup = adapter.spanSizeLookup
//        }
//
//        val section = Section()
//        section.addAll(bodyItems)
//        adapter.add(section)
//
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = layoutManager
//
//        val touchCallback = object : TouchCallback() {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                val from = viewHolder.adapterPosition
//                val to = target.adapterPosition
//                //bodyItems.swap(from,to)
//                val group = section.getGroup(from)
//                section.onItemMoved(group,from,to)
//
//                //adapter.notifyItemMoved(from,to)
//                return true
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item = adapter.getItem(viewHolder.adapterPosition)
//                // Change notification to the adapter happens automatically when the section is
//                // changed.
//                section.remove(item)
//            }
//        }
//
//        ItemTouchHelper(touchCallback).attachToRecyclerView(recyclerView)

//groupAdapter.add(new HeaderItem());
//groupAdapter.add(new CommentItem());
//
//Section section = new Section();
//section.setHeader(new HeaderItem());
//section.addAll(bodyItems);
//groupAdapter.add(section);
