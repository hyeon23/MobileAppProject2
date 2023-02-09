package com.example.mobileprogrammingteam5.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogrammingteam5.ActivityFacilities
import com.example.mobileprogrammingteam5.FacilityInfo
import com.example.mobileprogrammingteam5.databinding.ActivityMyBookmarkItemBinding
import com.example.mobileprogrammingteam5.model.ModelBookmark
import com.example.mobileprogrammingteam5.model.ModelFacilities

class AdapterBookMark(
    private val bList: ArrayList<ModelBookmark>
) :
    RecyclerView.Adapter<ViewHolderBookMark>() {
    private val TAG : String = "AdapterBookMark 로그"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBookMark {
        val binding = ActivityMyBookmarkItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderBookMark(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderBookMark, position: Int) {
        val buildingName = bList[position].buildingName.toString()
        holder.apply {
            initView(buildingName)
            onMoveFacilities(bList)
        }
    }

    override fun getItemCount(): Int = bList.size
}

class ViewHolderBookMark(val binding: ActivityMyBookmarkItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun initView(building : String) {
        binding.apply {
            tvBookmarkName.text = building
        }
    }

    fun onMoveFacilities(bList : ArrayList<ModelBookmark>) {
        binding.imArrowRight.setOnClickListener {
            val bName = bList[absoluteAdapterPosition].buildingName
            val i : Intent = Intent(itemView.context, ActivityFacilities::class.java)
            i.putExtra("bookmarkBuilding",bName)
            itemView.context.startActivity(i)
        }
    }
}

























