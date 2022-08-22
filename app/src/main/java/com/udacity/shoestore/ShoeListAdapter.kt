package com.udacity.shoestore

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.databinding.FragmentInstractionsBinding.inflate
import com.udacity.shoestore.models.Shoe

class ShoeListAdapter(val list:MutableList<Shoe>, val context: ShoeListingFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShoeViewHolder(LayoutInflater.from(context.context).inflate(R.layout.shoe_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (holder is ShoeViewHolder) {

            holder.itemView.findViewById<TextView>(R.id.item_name).text = "Name "+model.name
            holder.itemView.findViewById<TextView>(R.id.item_description).text = "description "+model.description
            holder.itemView.findViewById<TextView>(R.id.item_company).text = "Company "+model.company
            holder.itemView.findViewById<TextView>(R.id.item_size).text = "Size "+model.size



        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ShoeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}