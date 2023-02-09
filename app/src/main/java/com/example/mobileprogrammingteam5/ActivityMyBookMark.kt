package com.example.mobileprogrammingteam5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileprogrammingteam5.adapter.AdapterBookMark
import com.example.mobileprogrammingteam5.databinding.ActivityMyBookmarkBinding
import com.example.mobileprogrammingteam5.model.ModelBookmark
import com.example.mobileprogrammingteam5.model.ModelFacilities
import com.google.firebase.database.*
import kotlin.collections.ArrayList

//사용자 리스트 버튼 클릭시 보이는 즐겨찾기 건물 화면.
class ActivityMyBookMark : AppCompatActivity() {
    private lateinit var mBinding: ActivityMyBookmarkBinding

    private lateinit var database: DatabaseReference
    private lateinit var mAdapter: AdapterBookMark
    private lateinit var bList: ArrayList<ModelBookmark>
    private lateinit var likeArr: ArrayList<ModelFacilities>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMyBookmarkBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
        setEvent()
    }

    fun initView() {

        database =
            FirebaseDatabase.getInstance().reference.child("KonkukUniv/Buildings")
        likeArr = ArrayList()
        bList = ArrayList()
        mAdapter = AdapterBookMark(bList)
        mBinding.reMyBookmark.apply {
            layoutManager = LinearLayoutManager(this@ActivityMyBookMark)
            adapter = mAdapter
        }

        //파이어베이스 데이터 가져와서 건물 이름과 좋아요 여부 데이터 가공 후 리사이클러뷰 초기화 진행.
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (mSnapshot: DataSnapshot in snapshot.children) {
                    val mData: ModelFacilities? = mSnapshot.getValue(ModelFacilities::class.java)

                    if (mData?.Facilities != null) {
                        val likeFacility = mData.Facilities.values.filter {
                            it.like == true
                        }
                        if (likeFacility.isNotEmpty()) {
                            val building = mData.buildingName
                            bList.add(ModelBookmark(building,likeFacility))
                        }
                    }
                }
                mAdapter.notifyItemInserted(bList.size)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun setEvent() {
    }
}
