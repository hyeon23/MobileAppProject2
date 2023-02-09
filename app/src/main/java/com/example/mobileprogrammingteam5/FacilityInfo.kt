package com.example.mobileprogrammingteam5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogrammingteam5.databinding.ActivityFacilityInfoBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FacilityInfo : AppCompatActivity(), OnMapReadyCallback {

    lateinit var binding: ActivityFacilityInfoBinding
    lateinit var adapter:CommentAdapter
    lateinit var layoutManager: LinearLayoutManager

    lateinit var buildingName: String // 건물이름
    lateinit var facilityName: String // 편의시설 이름

    private lateinit var mMap: GoogleMap // 구글맵 변수


//    private val activityResultLauncher = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ){
//        if(it.resultCode == Activity.RESULT_OK){
//            redrawComment()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFacilityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val infoStr = intent.getStringExtra("info")
        val infoArr = infoStr?.split(",")
        buildingName = infoArr?.get(0).toString()
        facilityName = infoArr?.get(1).toString()

        initFacility()
        initLayout()
    }

    // 구글맵 초기 설정해주는 부분
    override fun onMapReady(p0: GoogleMap) {

        mMap = p0
        mMap.setMinZoomPreference(10f)
        mMap.setMaxZoomPreference(17f)

        var lat = 0.0
        var long = 0.0

        // 건물이름마다 좌표 설정해주는 부분
        when(buildingName){
            "건국대학교병원" -> {
                lat = 37.5406188826296
                long = 127.0720799739956
            }

            "건대부중" -> {
                lat = 37.53997837643497
                long = 127.08029687240432
            }

            "건대부고" -> {
                lat = 37.540021123860384
                long = 127.0803704657522
            }

            "건축관" -> {
                lat =  37.54370079034536
                long = 127.07838287766637
            }

            "경영관" -> {
                lat = 37.54440068317769
                long = 127.07590548021706
            }

            "공학관" -> {
                lat = 37.54157830356089
                long = 127.0793311354902
            }
            "과학관" -> {
                lat = 37.54161806365394
                long =  127.08049098995897
            }

            "교육과학관" -> {
                lat = 37.544036854055214
                long = 127.07421342790276
            }

            "국제학사" -> {
                lat = 37.544036854055214
                long = 127.07421342790276
            }

            "동물생명과학관" -> {
                lat = 37.54034717809133
                long = 127.07434555135158
            }

            "박물관" -> {
                lat = 37.54240518515736
                long = 127.07556965393007
            }

            "법학관" -> {
                lat = 37.54186944073423
                long = 127.07502032208023
            }

            "산학협동관" -> {
                lat = 37.539712698086724
                long = 127.07318513850892
            }
            "상허기념도서관" -> {
                lat = 37.54190625073303
                long = 127.073798308044
            }

            "상허연구관" -> {
                lat = 37.544211824981396
                long = 127.07535648045499
            }

            "새천년관" -> {
                lat = 37.54353026108821
                long = 127.07738127459312
            }
            "생명과학관" -> {
                lat = 37.5409641876488
                long = 127.07462338502171
            }

            "수의학관" -> {
                lat = 37.53914399369833
                long = 127.07489878530706
            }

            "신공학관" -> {
                lat = 37.540388954477784
                long = 127.07937513558939
            }

            "언어교육원" -> {
                lat = 37.54252738152498
                long = 127.07468717761867
            }

            "예술문화관" -> {
                lat = 37.54280541773237
                long = 127.07312309785497
            }

            "의생명과학연구관" -> {
                lat = 37.541440919842955
                long = 127.0722844392455
            }

            "인문학관" -> {
                lat = 37.54255435769568
                long = 127.07824872167318
            }

            "입학정보관" -> {
                lat = 37.54026882079876
                long = 127.07357887966688
            }

            "창의관" -> {
                lat = 37.541137491369035
                long = 127.08164179184494
            }

            "쿨하우스" -> {
                lat =37.53936463340009
                long = 127.07852826083506
            }

            "학생회관" -> {
                lat = 37.54191031196315
                long = 127.07799345462819
            }

            "해봉부동산학관" -> {
                lat = 37.54341938015227
                long = 127.07815344387122
            }

            "행정관" -> {
                lat = 37.543169050629814
                long = 127.07514892341864
            }

        }

        // 마커위치 설정
        val marker = LatLng(lat, long)

        // 마커 클릭했을 때 뜨는 상세정보 설정
        val mDatabase = Firebase.database.getReference("KonkukUniv/Buildings/${buildingName}/Facilities/${facilityName}")
        mDatabase.child("location").get().addOnSuccessListener {
            mMap.addMarker(MarkerOptions().position(marker).title(it.value.toString()))
        }

        // 맵에다가 마커 추가
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))

        // 마커 위치로 카메라 설정
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))

    }

    // 다시 편의시설 상세정보 페이지로 돌아왔을 때 댓글을 불러와주는 부분
    private fun redrawComment() {

        val db = Firebase.database.getReference("KonkukUniv/Buildings/${buildingName}/Facilities/${facilityName}/Comments")
        val query = db.limitToLast(50)

        val option = FirebaseRecyclerOptions.Builder<Comment>()
            .setQuery(query, Comment::class.java)
            .build()

        adapter = CommentAdapter(option)
        binding.commentRecyclerView.adapter = adapter
        adapter.startListening()
    }

    // 어느 건물, 어느 편의시설인지 가져오는 부분
    private fun initFacility() {

           //Intent infoIntent = new Intent(this, FacilityInfo::class.java)
           //infoIntent.putExtra("info", "건물이름,편의시설이름")



//        buildingName = "학생회관"
//        facilityName = "식당(KU’s Kitchen)"

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // 각 편의시설에 해당하는 Comments 를 가져와주는 query문
        val db = Firebase.database.getReference("KonkukUniv/Buildings/${buildingName}/Facilities/${facilityName}/Comments")
        val query = db.limitToLast(50)

        // CommentAdapter에 보내줄 option 생성
        val option = FirebaseRecyclerOptions.Builder<Comment>()
            .setQuery(query, Comment::class.java)
            .build()

        adapter = CommentAdapter(option)

        binding.commentRecyclerView.layoutManager = layoutManager
        binding.commentRecyclerView.adapter = adapter

        //이용시간, 전화번호 데이터 가져온 후, 보여주는 부분
        val mDatabase = Firebase.database.getReference("KonkukUniv/Buildings/${buildingName}/Facilities/${facilityName}")
        mDatabase.child("useTime").get().addOnSuccessListener {
            binding.facilityInfo.setText("이용시간 : " + it.value.toString() + "\n\n")
        }
        mDatabase.child("phoneNum").get().addOnSuccessListener {
            val infoText = binding.facilityInfo.text.toString()
            val newText= infoText + "전화번호 : " + it.value.toString()
            binding.facilityInfo.setText(newText)
        }

    }

    // 댓글 입력 버튼 눌렀을 때의 event를 설정해주는 부분
    private fun initLayout() {

        binding.commentBtn.setOnClickListener {
            val intent = Intent(this, WriteComment::class.java)
            intent.putExtra("bName", buildingName)
            intent.putExtra("fName", facilityName)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    // 댓글 입력 후, 또는 취소 후 다시 돌아왔을 때 댓글을 다시 불러와주는 역할
    override fun onResume() {
        super.onResume()
        redrawComment()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }



}