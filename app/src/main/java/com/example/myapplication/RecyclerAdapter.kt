package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class RecyclerAdapter(
    private val list: MutableList<Todo>,

    // передаём коллбек нажатия на кнопку
    private val onItemClick: (id: Int) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = "${list[position].title} ${list[position].name}"
        // обработчик нажатия кнопки
        holder.button.setOnClickListener {
            if (holder.adapterPosition >= 0) {
                onItemClick(holder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val button = itemView.findViewById<Button>(R.id.button_back)
    }
    fun upd(nList:List<Todo>){
        list.clear()
        list.addAll(nList)
        this.notifyDataSetChanged()
    }
}

