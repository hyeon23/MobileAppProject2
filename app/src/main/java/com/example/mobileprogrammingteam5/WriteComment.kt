package com.example.mobileprogrammingteam5

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mobileprogrammingteam5.databinding.ActivityWriteCommentBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class WriteComment : AppCompatActivity() {

    lateinit var binding: ActivityWriteCommentBinding
    lateinit var buildingName: String // 건물이름
    lateinit var facilityName: String // 편의시설 이름


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        
        val stars = binding.ratingBar.progressDrawable
        stars.colorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP)
        
        // 건물이름 설정
        if(intent.hasExtra("bName"))
            buildingName = intent.getStringExtra("bName").toString()
        
        // 편의시설 이름 설정
        if(intent.hasExtra("fName"))
            facilityName = intent.getStringExtra("fName").toString()

        binding.apply {
            // 취소버튼 
            cancelBtn.setOnClickListener{
                setResult(Activity.RESULT_CANCELED)
                finish()
            }

            // 완료버튼
            writeBtn.setOnClickListener{

                val star = binding.ratingBar.rating
                val info = binding.commentSection.text.toString()
                var commentOk = true
                
                // 빈 내용의 댓글일 때 등록되지 않도록 설정
                if(binding.commentSection.text.toString().isEmpty()){
                    val builder = AlertDialog.Builder(this@WriteComment)
                    builder.setTitle("빈 댓글입니다.")
                        .setMessage("댓글을 작성하여 주십시오.")
                        .setPositiveButton("확인",
                            DialogInterface.OnClickListener { dialog , id ->
                                // 필요x
                            })
                    builder.show()

                    commentOk = false
                }
                
                // 내용있는 댓글일때만 댓글 추가 허용함
                if(commentOk) {
                    // firebase db에서 해당 건물과, 편의시설의 Comments 항목으로 이동
                    val cdb = Firebase.database.getReference("KonkukUniv/Buildings/${buildingName}/Facilities/${facilityName}/Comments")

                    var comment = Comment(star, info)
                    // 댓글 추가해주는 부분
                    cdb.push().setValue(comment)

                    val intent = Intent()
                    setResult(Activity.RESULT_OK, intent);
                    finish()
                }
                
            }
        }
    }
}

private fun Drawable.colorFilter(yellow: Int, srcAtop: PorterDuff.Mode) {

}
