package com.example.mobileprogrammingteam5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnimalFragment : Fragment() {
    private var columnCount = 1
    lateinit var adapter2:AnimalAdapter
    val arrayList = arrayListOf<AnimalData>(
        AnimalData(R.drawable.cat1,"만쥬","고양이","새천년관, 박물관 공원", "" +
                "건국대학교의 마스코트 고양이 만쥬\n" +
                "모든 고양이 중 가장 네임드\n" +
                "건국대학교 총장님은 사실 만쥬라는 소문이 있어, 총장님이라 불린다.", View.GONE),
        AnimalData(R.drawable.cat2,"춘장이","고양이","새천년관, 박물관 공원", "" +
                "두번째 인기 고양이 춘장이\n" +
                "생긴게 춘장색이여서 춘장이이다.", View.GONE),
        AnimalData(R.drawable.cat3,"얼룩","고양이","예디대, 사범대", "" +
                "예디대, 사범대에 서식해 건국대 중심에 서식하는 만쥬와 춘장이에 비해선 인지도와 인기가 밀린다.\n" +
                "동아리 \"꽁냥꽁냥\"의 관심어린 보살핌을 받고 있다.", View.GONE),
        AnimalData(R.drawable.cat4,"호떡","고양이","예디대, 사범대", "" +
                "예디대, 사범대에 서식해 건국대 중심에 서식하는 만쥬와 춘장이에 비해선 인지도와 인기가 밀린다.\n" +
                "동아리 \"꽁냥꽁냥\"의 관심어린 보살핌을 받고 있다.", View.GONE),
        AnimalData(R.drawable.cat5,"배트","고양이","예디대, 사범대", "" +
                "예디대, 사범대에 서식해 건국대 중심에 서식하는 만쥬와 춘장이에 비해선 인지도와 인기가 밀린다.\n" +
                "동아리 \"꽁냥꽁냥\"의 관심어린 보살핌을 받고 있다.", View.GONE),
        AnimalData(R.drawable.cat6,"사고","고양이","예디대, 사범대", "" +
                "예디대, 사범대에 서식해 건국대 중심에 서식하는 만쥬와 춘장이에 비해선 인지도와 인기가 밀린다.\n" +
                "동아리 \"꽁냥꽁냥\"의 관심어린 보살핌을 받고 있다.", View.GONE),
        AnimalData(R.drawable.cat7,"뽀또","고양이","예디대, 사범대", "" +
                "예디대, 사범대에 서식해 건국대 중심에 서식하는 만쥬와 춘장이에 비해선 인지도와 인기가 밀린다.\n" +
                "동아리 \"꽁냥꽁냥\"의 관심어린 보살핌을 받고 있다.", View.GONE),
        AnimalData(R.drawable.cat8,"조에스","고양이","예디대", "" +
                "개냥이이다.\n" +
                "먼저 잘 다가오는 고양이답지 않은 매력을 가져, 매력쟁이이다.", View.GONE),
        AnimalData(R.drawable.dog1,"콩이","강아지","후문 사진관", "" +
                "후문에서 종종 돌아다니는 것을 볼 수 있다.\n" +
                "만지려고 하면 시크하게 도망간다.", View.GONE),
        AnimalData(R.drawable.cow1,"KU","소","건국대학교", "" +
                "건국대학교의 마스코드 KU이다.\n" +
                "가마우지도 있고, 자라도 있고, 다있는데 마스코트인 황소만 없는 아이러니가 있다.\n" +
                "건국대학교가 축산, 식품, 생명이 메이저 학과였기 때문에 이를 담당하는 소가 건국대학교를 대표하는 동물이 되었다.", View.GONE),
    )

    //초기화 수행
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter2 = AnimalAdapter(arrayList)
                adapter = adapter2
                adapter2.itemClickListener = object: AnimalAdapter.OnItemClickListener{
                    override fun OnItemClick(data: AnimalData, pos: Int, ll1: LinearLayout) {
                        if(data.visibility == View.VISIBLE){
                            ll1.visibility = View.GONE
                            data.visibility = View.GONE
                        }
                        else if(data.visibility == View.GONE){
                            ll1.visibility = View.VISIBLE
                            data.visibility = View.VISIBLE
                        }
                    }
                }
                val simpleItemTouchCallBack = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT and ItemTouchHelper.RIGHT) {
                    override fun onMove(p0:RecyclerView, p1:RecyclerView.ViewHolder, p2:RecyclerView.ViewHolder):Boolean{
                        adapter2.moveItem(p1.bindingAdapterPosition, p2.bindingAdapterPosition)
                        return true
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        //adapter2.removeItem(viewHolder.bindingAdapterPosition)
                    }
                }
                val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallBack)
                itemTouchHelper.attachToRecyclerView(findViewById(R.id.recyclerview))
            }
        }
        return view
    }
}