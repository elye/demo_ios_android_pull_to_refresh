package com.elyeproj.myapplication

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_list_view.pull_to_refresh_layout
import kotlinx.android.synthetic.main.activity_list_view.recycler_view

class ListView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MyRecyclerViewAdapter(this)
        pull_to_refresh_layout.setOnRefreshListener {
            Handler().postDelayed({
                pull_to_refresh_layout.isRefreshing = false
            }, 3000)
        }
    }

    class MyRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_view, parent, false))
        }

        override fun getItemCount() = 15

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.populate(position)

        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun populate(position: Int) {
            itemView.findViewById<TextView>(R.id.text_view).text =
                    "Section ${position/3} Row ${position%3}"
        }
    }
}
