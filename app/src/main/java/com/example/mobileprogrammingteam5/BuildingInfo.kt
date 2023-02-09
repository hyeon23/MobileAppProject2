package com.example.mobileprogrammingteam5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mobileprogrammingteam5.databinding.ActivityBuildingInfoBinding

class BuildingInfo : AppCompatActivity() {
    lateinit var binding:ActivityBuildingInfoBinding
    lateinit var buildingName:String
    lateinit var buildingNum:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildingInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        if(intent.hasExtra("bname")){
            buildingName = intent.getStringExtra("bname").toString()
            Log.i("받아와진 bname", buildingName.toString())
        }
        if(intent.hasExtra("bnum")){
            buildingNum = intent.getStringExtra("bnum").toString()
        }

        if (intent != null) {
            binding.building.text = buildingName
            binding.buildingNum.text = buildingNum
        }
        binding.facilityListBtn.setOnClickListener {
            val i = Intent(this, ActivityFacilities::class.java)
            i.putExtra("ModelFacilityList", buildingName)
            startActivity(i)
        }
        binding.cancelBtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

}