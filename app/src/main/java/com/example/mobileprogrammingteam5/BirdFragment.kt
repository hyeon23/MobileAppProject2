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

class BirdFragment : Fragment() {
    private var columnCount = 1
    lateinit var adapter2:AnimalAdapter
    val arrayList = arrayListOf<AnimalData>(
        AnimalData(R.drawable.kugoose,"건구스","거위","일감호, 청심대 인근", "" +
                "-건국대학교의 마스코트 중 하나로 일감호에 서식하는 거위이다.\n" +
                "-건구스와 건덕이는 팀으로 활동하고, 팀의 대장 역을 맡고 있다.\n" +
                "-비오는 날, 일렬로 산책하는 모습을 볼 수 있다.", View.GONE),
        AnimalData(R.drawable.kuduck,"건덕이","오리","청심대, 쪽문, 물레방아", "" +
                "-건국대학교의 마스코트 중 하나로 일감호에 서식하는 오리이다.\n" +
                "-사는 위치에 따라 청심대파, 쪽문파, 물레방아파로 나뉘고, 청심대파의 서열이 가장 높다.", View.GONE),
        AnimalData(R.drawable.bird1,"백로","왜가리","와우도, 기숙사 측 둘레길", "" +
                "-일감호를 촬영 코스로 만드는데 가장 큰 기여를 했다.\n" +
                "-왜가리와 팀을 이루고, 까마귀와 가마우지와 매일 싸운다.\n" +
                "-까마귀와 가마우지와로 인해 영역다툼에서 밀려나 건국대학교 도로변에 큰 피해를 주고 있다.", View.GONE),
        AnimalData(R.drawable.bird2,"왜가리","왜가리","와우도, 기숙사 측 둘레길", "" +
                "-같은 왜가리 과인 백로와 친구이다.\n" +
                "-까마귀와 가마우지와로 인해 영역다툼에서 밀려나 건국대학교 도로변에 큰 피해를 주고 있다.", View.GONE),
        AnimalData(R.drawable.bird3,"가마우지","가마우지","와우도, 기숙사 측 둘레길", "" +
                "-건국대학교 도로변에 심미성을 해친 가장 큰 원인이다.\n" +
                "-신기하게 생겼지만 유해조수로 배설물로 나무를 고사시킨다.\n" +
                "-이를 방지하기 위해 일감호에 독수리 연을 설치했다.\n" +
                "-하지만 그 결과 백로와 왜가리가 영역 싸움에서 밀려나 의도치 않은 더 큰 피해와 불편을 야기하게 만들었다.", View.GONE),
        AnimalData(R.drawable.bird4,"청 딱따구리","딱따구리","기숙사 산지, 박물관", "" +
                "-특유의 울음소리가 인상적이다.\n" +
                "-최근 신공학관이 맘이 들었는지 둥지파기를 시도했다.\n" +
                "-가끔 철근도 두드린다.", View.GONE),
        AnimalData(R.drawable.bird5,"오색 딱따구리","딱따구리","기숙사 산지, 박물관","" +
                "-이름처럼 머리 주위에 다섯개의 색의 깃털이 나 청 딱따구리와 구분이 쉽다.", View.GONE),
        AnimalData(R.drawable.bird6,"까마귀","까마귀","기숙사 산지 및 둘레길", "" +
                "-까마귀이다.\n" +
                "-의도치 않은 기숙사 생의 알람시계가 되는 경우도 있다.\n" +
                "-건국대학교에 존재하는 모든 조류와 싸운다.", View.GONE),
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