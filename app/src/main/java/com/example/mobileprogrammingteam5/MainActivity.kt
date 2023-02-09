package com.example.mobileprogrammingteam5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mobileprogrammingteam5.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener, OnMapReadyCallback {
    lateinit var binding: ActivityMainBinding

    private lateinit var mMap: GoogleMap // 구글맵 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(binding.mainMap.id) as SupportMapFragment
        mapFragment.getMapAsync(this)
        initLayout()
    }
    // 구글맵 초기 설정해주는 부분
    override fun onMapReady(p0: GoogleMap) {

        mMap = p0
        mMap.setMinZoomPreference(10f)
        mMap.setMaxZoomPreference(17f)

        var initLat = 37.541501244616796
        var initLong = 127.07663803033236
        val initmarker = LatLng(initLat, initLong)

        mMap.addMarker(MarkerOptions().position(initmarker).title("건국대학교"))
        binding.hashtag.text = "#건국대학교"
        // 맵에다가 마커 추가
        mMap.moveCamera(CameraUpdateFactory.newLatLng(initmarker))
        // 마커 위치로 카메라 설정
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(initmarker, 15f))


        binding.admin.setOnClickListener {
            binding.hashtag.text = "#"+binding.admin.text

            var lat = 37.543169050629814
            var long = 127.07514892341864
            val marker = LatLng(lat, long)
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(marker).title("행정관").snippet("1"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.management.setOnClickListener {
            binding.hashtag.text = "#"+binding.management.text

            var lat = 37.54440068317769
            var long= 127.07590548021706
            val marker = LatLng(lat, long)
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(marker).title("경영관").snippet("2"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.research.setOnClickListener {
            binding.hashtag.text = "#"+binding.research.text

            var lat = 37.544211824981396
            var long = 127.07535648045499
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("상허연구관").snippet("3"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.educationScience.setOnClickListener {
            binding.hashtag.text = "#"+binding.educationScience.text

            var lat = 37.544036854055214
            var long = 127.07421342790276
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("교육과학관").snippet("4"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.art.setOnClickListener {
            binding.hashtag.text = "#"+binding.art.text

            var lat = 37.54280541773237
            var long =127.07312309785497

            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("예술문화관").snippet("5"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.languageEdu.setOnClickListener {
            binding.hashtag.text = "#"+binding.languageEdu.text

            var lat = 37.54252738152498
            var long = 127.07468717761867


            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("언어교육원").snippet("6"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.museum.setOnClickListener {
            binding.hashtag.text = "#"+binding.museum.text

            var lat = 37.54240518515736
            var long = 127.07556965393007

            mMap.clear()

            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("박물관").snippet("7"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.law.setOnClickListener {
            binding.hashtag.text = "#"+binding.law.text

            var lat = 37.54186944073423
            var long = 127.07502032208023

            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("법학관").snippet("8"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.library.setOnClickListener {
            binding.hashtag.text = "#"+binding.library.text

            var lat = 37.54190625073303
            var long = 127.073798308044

            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("상허기념도서관").snippet("9"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.medicalLife.setOnClickListener {
            binding.hashtag.text = "#"+binding.medicalLife.text

            var lat = 37.541440919842955
            var long = 127.0722844392455
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("의생명과학연구관").snippet("10"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.lifeScience.setOnClickListener {
            binding.hashtag.text = "#"+binding.lifeScience.text

            var lat = 37.5409641876488
            var long = 127.07462338502171
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("생명과학관").snippet("11"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.animalLife.setOnClickListener {
            binding.hashtag.text = "#"+binding.animalLife.text

            var lat = 37.54034717809133
            var long = 127.07434555135158
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("동물생명과학관").snippet("12"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.admission.setOnClickListener {
            binding.hashtag.text = "#"+binding.admission.text

            var lat = 37.54026882079876
            var long = 127.07357887966688
            mMap.clear()

            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("입학정보관").snippet("13"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.industry.setOnClickListener {
            binding.hashtag.text = "#"+binding.industry.text

            var lat = 37.539712698086724
            var long = 127.07318513850892

            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("산학협동관").snippet("14"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
        binding.animalMedical.setOnClickListener {
            binding.hashtag.text = "#"+binding.animalMedical.text

            var lat = 37.53914399369833
            var long = 127.07489878530706
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("수의학관").snippet("15"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.newThousand.setOnClickListener {
            binding.hashtag.text = "#"+binding.newThousand.text

            var lat = 37.54353026108821
            var long = 127.07738127459312


            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("새천년관").snippet("16"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.architecture.setOnClickListener {
            binding.hashtag.text = "#"+binding.architecture.text
            var lat =  37.54370079034536
            var long = 127.07838287766637
            mMap.clear()

            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("건축관").snippet("17"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.realestate.setOnClickListener {
            binding.hashtag.text = "#"+binding.realestate.text

            var lat = 37.54341938015227
            var long = 127.07815344387122

            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("해봉부동산학관").snippet("18"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.humanLiterature.setOnClickListener {
            binding.hashtag.text = "#"+binding.humanLiterature.text

            var lat = 37.54255435769568
            var long = 127.07824872167318

            mMap.clear()

            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("인문학관").snippet("19"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.student.setOnClickListener {
            binding.hashtag.text = "#"+binding.student.text

            var lat = 37.54191031196315
            var long = 127.07799345462819

            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("학생회관").snippet("20"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.engineering.setOnClickListener {
            binding.hashtag.text = "#"+binding.engineering.text

            var lat = 37.54157830356089
            var long = 127.0793311354902

            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("공학관").snippet("21"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.newEngineering.setOnClickListener {
            binding.hashtag.text = "#"+binding.newEngineering.text

            var lat = 37.540388954477784
            var long = 127.07937513558939
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("신공학관").snippet("22"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.science.setOnClickListener {
            binding.hashtag.text = "#"+binding.science.text

            var lat = 37.54161806365394
            var long = 127.08049098995897

            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("과학관").snippet("23"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.creative.setOnClickListener {
            binding.hashtag.text = "#"+binding.creative.text

            var lat = 37.541137491369035
            var long = 127.08164179184494

            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("창의관").snippet("24"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.international.setOnClickListener {
            binding.hashtag.text = "#"+binding.international.text

            var lat =  37.544036854055214
            var long =127.07421342790276


            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("국제학사").snippet("25"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.kulhouse.setOnClickListener {
            binding.hashtag.text = "#"+binding.kulhouse.text

            var lat = 37.53936463340009
            var long = 127.07852826083506

            mMap.clear()

            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("쿨하우스").snippet("26"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.middleSchool.setOnClickListener {
            binding.hashtag.text = "#"+binding.middleSchool.text
            var lat =  37.53997837643497
            var long =127.08029687240432
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("건대부중").snippet("28"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.highSchool.setOnClickListener {
            binding.hashtag.text = "#"+binding.highSchool.text
            var lat = 37.540021123860384
            var long =127.0803704657522
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("건대부고").snippet("29"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }

        binding.kHospital.setOnClickListener {
            binding.hashtag.text = "#"+binding.kHospital.text
            var lat =  37.5406188826296
            var long =127.0720799739956
            mMap.clear()
            val marker = LatLng(lat, long)
            mMap.addMarker(MarkerOptions().position(marker).title("건국대학교병원").snippet("30"))
            // 맵에다가 마커 추가
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            // 마커 위치로 카메라 설정
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 20f))
            mMap.setOnMarkerClickListener(this)
        }
    }

    private fun initLayout() {
        binding.userlistBtn.setOnClickListener {
            val intent = Intent(this, ActivityMyBookMark::class.java)
            startActivity(intent)
        }
        binding.dogamBtn.setOnClickListener {
            val dIntent = Intent(this, Dogam::class.java)
            startActivity(dIntent)
        }
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        val intent = Intent(this, BuildingInfo::class.java)
        Log.i("마커클릭", p0.title.toString())
        intent.putExtra("bname", p0.title.toString())
        intent.putExtra("bnum", p0.snippet.toString())
        startActivity(intent)
        return true
    }

}