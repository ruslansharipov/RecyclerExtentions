package com.example.groupierecycler.abstract_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractAdapter<T>
    : RecyclerView.Adapter<AbstractAdapter<T>.BodyHolder>() {

    abstract val bodyLayoutRes: Int
    abstract var items: MutableList<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyHolder {
        val view = LayoutInflater.from(parent.context).inflate(bodyLayoutRes, parent, false)
        return BodyHolder(view)
    }

    override fun getItemCount(): Int = items.size

    inner class BodyHolder(view: View) : RecyclerView.ViewHolder(view)
}