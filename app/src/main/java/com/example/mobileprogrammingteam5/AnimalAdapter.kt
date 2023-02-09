package com.example.mobileprogrammingteam5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogrammingteam5.databinding.AnimalrowBinding
import com.example.mobileprogrammingteam5.databinding.FragmentAnimalBinding

class AnimalAdapter(val items:ArrayList<AnimalData>): RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(data:AnimalData, pos:Int, ll1: LinearLayout)
    }
    var itemClickListener:OnItemClickListener? = null

    fun moveItem(oldPos:Int, newPos:Int){
        val item = items[oldPos]
        items.removeAt(oldPos)
        items.add(newPos, item)
        notifyItemMoved(oldPos, newPos)
    }
//    fun removeItem(pos:Int){
//        items.removeAt(pos)
//        notifyItemRemoved(pos)
//    }

    inner class ViewHolder(val binding: AnimalrowBinding): RecyclerView.ViewHolder(binding.root){
        init{
            binding.animalrow.setOnClickListener {
                itemClickListener?.OnItemClick(items[bindingAdapterPosition], bindingAdapterPosition, binding.ll)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimalrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.binding.animalImage.setImageResource(items[position].aImage)
        holder.binding.animalTextName.text = items[position].aName
        holder.binding.animalTextType.text = items[position].aType
        holder.binding.animalTextDescription.text = items[position].aDescription
        holder.binding.animalTextPosition.text = items[position].aposition
        holder.binding.ll.visibility = items[position].visibility
    }

    override fun getItemCount(): Int {
        return items.size
    }
}