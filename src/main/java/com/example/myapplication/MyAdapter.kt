package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

class MyAdapter(val arrayList: ArrayList<Model>,val context: Context):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(model: Model) {
            itemView.titleTv.text = model.title
            itemView.descriptionTv.text = model.des
            itemView.imageTv.setImageResource(model.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener {
//            if (position == 0) {
//                Toast.makeText(
//                    context,
//                    "You clicked over Husky",
//                    Toast.LENGTH_LONG
//                ).show()
//
//            }
//
//            if (position == 0) {
//                Toast.makeText(
//                    context,
//                    "You clicked over Doberman",
//                    Toast.LENGTH_LONG
//                ).show()
//            }

            val model = arrayList.get(position)
            var gTitle: String = model.title
            var gDescription: String = model.des
            var gImageView : Int = model.image

            val intent = Intent(context ,IntentActivity::class.java)

            intent.putExtra("iTitle",gTitle)
            intent.putExtra("iDescription",gDescription)
            intent.putExtra("iImageView",gImageView)

            context.startActivity(intent)

        }
    }



    override fun getItemCount(): Int {
        return arrayList.size
    }
}