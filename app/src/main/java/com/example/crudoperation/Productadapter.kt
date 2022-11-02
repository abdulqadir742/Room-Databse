package com.example.crudoperation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class Productadapter(var productList: List<Product> = emptyList<Product>(),val itemClicked: (pid:Long) -> Unit):Adapter<MyViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_view,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.Pname.text = productList.get(position).productName
        holder.Pprice.text = productList.get(position).productPrice.toString()
        holder.Pstock.text = productList.get(position).productQuantity.toString()
        holder.listClick.setOnClickListener {
            var pid = productList.get(position).id.toString().toLong()
            itemClicked(pid)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val Pname = itemView.findViewById<TextView>(R.id.Pname)
    val Pprice = itemView.findViewById<TextView>(R.id.Pprice)
    val Pstock = itemView.findViewById<TextView>(R.id.Pstock)
    val listClick = itemView.findViewById<CardView>(R.id.list)

}