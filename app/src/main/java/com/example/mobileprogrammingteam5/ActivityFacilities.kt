package com.example.mobileprogrammingteam5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileprogrammingteam5.adapter.AdapterFacilities
import com.example.mobileprogrammingteam5.adapter.isLikeClickCallback
import com.example.mobileprogrammingteam5.databinding.ActivityFacilitiesBinding
import com.example.mobileprogrammingteam5.model.ModelBookmark
import com.example.mobileprogrammingteam5.model.ModelFacilities
import com.example.mobileprogrammingteam5.model.ModelFacility
import com.google.firebase.database.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class ActivityFacilities : AppCompatActivity() {
    private val FACILITYLIST = 0
    private val BOOKMARKLIST = 1
    private lateinit var mBinding: ActivityFacilitiesBinding
    private lateinit var getFacilityList:String
    private lateinit var getBookmarkBuilding:String

    private lateinit var database: DatabaseReference
    private lateinit var mAdapter: AdapterFacilities
    private lateinit var bList: ArrayList<ModelFacility>
    private var buildingName: String = ""
    private var pageType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityFacilitiesBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        setEvent()
    }

    fun initView() {

        bList = ArrayList()
        mAdapter = AdapterFacilities(buildingName, bList, pageType)
        mBinding.reFacilities.apply {
            layoutManager = LinearLayoutManager(this@ActivityFacilities)
            adapter = mAdapter
        }


        if(intent.hasExtra("ModelFacilityList")){
            getFacilityList = intent.getStringExtra("ModelFacilityList").toString()
            Log.i("받아와진 ModelFacilityList", buildingName.toString())
            mBinding.tvTopTitle.text = getFacilityList
            getBookmarkBuilding = "NULL"
        }

        if(intent.hasExtra("bookmarkBuilding")) {
            getBookmarkBuilding = intent.getStringExtra("bookmarkBuilding").toString()
            Log.i("받아와진 BookMark 건물이름 ->", getBookmarkBuilding)
            mBinding.tvTopTitle.text = getBookmarkBuilding
            getFacilityList = "NULL"
        }

//        편의시설 목록에서 넘어온 데이터와 북마크 데이터의 분기처리
        if (getBookmarkBuilding.equals("NULL")) {
            buildingName = getFacilityList
            facilities(FACILITYLIST)
        }
        else if (getFacilityList.equals("NULL")) {
            buildingName = getBookmarkBuilding
            facilities(BOOKMARKLIST)
        }


    }

    fun setEvent() {

        //좋아요 클릭시 사용되는 콜백
        mAdapter.isLikeClickListener(object : isLikeClickCallback {
            override fun isLike(ModelFacilityName: String, isLike: Boolean) {
                database =
                    FirebaseDatabase.getInstance().reference.child("KonkukUniv/Buildings/${buildingName}/Facilities")

                val taskMap: HashMap<String, Any> = HashMap<String, Any>()
                taskMap.put("${ModelFacilityName}/like", isLike)
                database.updateChildren(taskMap)
            }
        })
    }

    //편의시설 목록 페이지 작업 메서드
    fun facilities(pageKind: Int) {
        database =
            FirebaseDatabase.getInstance().reference.child("KonkukUniv/Buildings/${buildingName}/Facilities")

        //파이어베이스 데이터 가져와서 건물 이름과 좋아요 여부 데이터 가공 후 리사이클러뷰 초기화 진행.
        if (pageKind == FACILITYLIST) {
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (mSnapshot: DataSnapshot in snapshot.children) {
                        val mData: ModelFacility? = mSnapshot.getValue(ModelFacility::class.java)

                        val bName = mBinding.tvTopTitle.text.toString()
                        val fName = mData?.facilityName
                        val bIsLike = mData?.like
                        bList.add(ModelFacility(bName, fName, bIsLike))
                    }
                    pageType = FACILITYLIST
                    mAdapter.notifyItemInserted(bList.size)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        } else if(pageKind == BOOKMARKLIST) {
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (mSnapshot: DataSnapshot in snapshot.children) {
                        val mData: ModelFacility? = mSnapshot.getValue(ModelFacility::class.java)

                        val bName = mBinding.tvTopTitle.text.toString()
                        val fName = mData?.facilityName
                        val bIsLike = mData?.like
                        if (bIsLike == true) {
                            bList.add(ModelFacility(bName, fName, bIsLike))
                        }
                    }
                    pageType = BOOKMARKLIST
                    mAdapter.notifyItemInserted(bList.size)
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }
}
































