package com.example.mobileprogrammingteam5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileprogrammingteam5.databinding.RowCommentBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CommentAdapter(options: FirebaseRecyclerOptions<Comment>) :
    FirebaseRecyclerAdapter<Comment, CommentAdapter.ViewHolder>(options) {


    inner class ViewHolder(val binding: RowCommentBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Comment)
    {
        holder.binding.apply {
            ratingBar.rating = model.starNum
            comment.setText(model.info)
        }
    }
}