package com.example.mobileprogrammingteam5.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogrammingteam5.FacilityInfo
import com.example.mobileprogrammingteam5.databinding.ActivityFacilitiesItemBinding
import com.example.mobileprogrammingteam5.model.ModelFacility
import com.example.mobileprogrammingteam5.model.ModelFacilities

class AdapterFacilities(
    private val buildingName: String,
    private val bList: ArrayList<ModelFacility>,
    private val pageType: Int
) :
    RecyclerView.Adapter<ViewHolderFacilities>() {
    var mCallback: isLikeClickCallback? = null
    private var isLike: Boolean = false

    fun isLikeClickListener(likeCallback: isLikeClickCallback) {
        this.mCallback = likeCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFacilities {
        val binding = ActivityFacilitiesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolderFacilities(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderFacilities, position: Int) {
        val facilityName = bList[position].facilityName.toString()
        val buildingName = bList[position].buildingName.toString()
        isLike = bList[position].like!!

        holder.apply {
            initView(facilityName, isLike)

            onMoveFacilities(buildingName, facilityName)

            binding.imLikeStar.setOnClickListener {
                binding.imLikeStar.isPressed = !isLike
                binding.imLikeStar.isSelected = !isLike
                mCallback?.isLike(facilityName, !isLike)
                isLike = !isLike

                if (pageType == 1){
                    bList.remove(bList[position])
                    notifyItemRemoved(position)
                }
            }
        }

    }

    override fun getItemCount(): Int = bList.size
}

class ViewHolderFacilities(val binding: ActivityFacilitiesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun initView(facilityName: String, isLike: Boolean) {
        binding.apply {
            tvFacilityName.text = facilityName
            imLikeStar.isSelected = isLike
        }
    }

    fun onMoveFacilities(buildingName: String, facilityName: String) {
        binding.imArrowRight.setOnClickListener {

            val i: Intent = Intent(itemView.context, FacilityInfo::class.java)
            i.putExtra("info", "${buildingName},${facilityName}")
            itemView.context.startActivity(i)
        }
    }
}

interface isLikeClickCallback {
    fun isLike(facilityName: String, isLike: Boolean)
}






















