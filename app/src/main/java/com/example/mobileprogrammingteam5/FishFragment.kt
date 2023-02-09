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

class FishFragment : Fragment() {
    private var columnCount = 1
    lateinit var adapter2:AnimalAdapter
    val arrayList = arrayListOf<AnimalData>(
        AnimalData(R.drawable.jara1,"따봉자라","자라","다리 근처, 청심대 하부", "" +
                "물 속에 사는 생물 중 가장 유명하고, 인기있다.\n" +
                "따봉자라야 고마워라는 말로 유명한데, 이는 시험기간에 따봉자라를 보면 A+를 받는다는 소문이 있다.\n" +
                "일감호 다리에서 보이는 강 위 나무 판넬 위에서 다른 거북이들과 일광욕을 자주 즐기고 있다.", View.GONE),
        AnimalData(R.drawable.turtle1,"KU북이","거북이","다리 근처, 청심대 하부", "" +
                "일감호에 사는 거북이의 일종이다.\n" +
                "토종 거북이 남생이로 등갑이 구형으로 동그랗다.\n" +
                "학생들이 종종 따봉자라와 착각한다.\n" +
                "일감호 다리에서 보이는 강 위 나무 판넬 위에서 다른 거북이들과 일광욕을 자주 즐기고 있다.", View.GONE),
        AnimalData(R.drawable.turtle2,"붉은 귀 거북","거북이","다리 근처, 청심대 하부", "" +
                "일감호에 사는 거북이의 일종\n" +
                "외래종으로 토착 거북종에게 상당한 위험이 된다.\n" +
                "학생들이 따봉자라라고 말하면 대부분이 붉은 귀 거북이다.", View.GONE),
        AnimalData(R.drawable.coi1,"황금잉어","잉어","일감호", "" +
                "일감호에 사는 다양한 잉어 중 하나\n" +
                "물속에 살기 때문에 잘 보이지 않는다.\n" +
                "먹이를 물가에 뿌리면 엄청나게 몰려들지만, 먹이를 주지말자!", View.GONE),
        AnimalData(R.drawable.coi2,"붕어","붕어","일감호", "" +
                "일감소에 사는 다양한 붕어 중 하나\n" +
                "먹이를 물가에 뿌리면 엄청나게 몰려들지만, 먹이를 주지말자!", View.GONE),
        AnimalData(R.drawable.crab1,"꽃게","게","일감호?", "" +
                "일감호에 사는 꽃게이다.\n" +
                "실제로 확인되지는 않았고, 과거에 일감호에 꽃게가 산다는 소문에서 파생된 생물이다.\n" +
                "전문가 의견으로는 바다에 사는 꽃개가 일감호에서 서식할 확률은 없다고한다.", View.GONE)
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