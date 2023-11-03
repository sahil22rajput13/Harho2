package com.app.harho.adapter.leaderboard

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.harho.R
import com.app.harho.base.GetObjects
import com.app.harho.model.leaderBoard.AllScoresInfo
import com.app.harho.utils.SharedPreference

class LeaderBoardAdapter(private val leaderList: ArrayList<AllScoresInfo>, val context: Context) :
    RecyclerView.Adapter<LeaderBoardAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvScore = itemView.findViewById<TextView>(R.id.tvScore)
        var tvCount = itemView.findViewById<TextView>(R.id.tvCount)
        var clBg = itemView.findViewById<ConstraintLayout>(R.id.clBg)
        var ClNos = itemView.findViewById<ConstraintLayout>(R.id.ClNos)
        var ClWhite = itemView.findViewById<ConstraintLayout>(R.id.ClWhite)
        var tvName = itemView.findViewById<TextView>(R.id.tvName)
    }

    override fun getItemViewType(position: Int): Int {
        return when (leaderList[position].id) {
            -1 -> -1
            else -> 1
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.score_list_item_design, parent, false)
        if (viewType == -1)
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return leaderList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType != -1) {
            val userId = GetObjects.preference.getString(SharedPreference.Key.USERID)
            if (userId == leaderList[position].id.toString()) {
                holder.clBg.setBackgroundResource(R.drawable.orange_score_list_bg)
                holder.ClNos.setBackgroundResource(R.drawable.orange_circle)
                holder.ClWhite.setBackgroundResource(R.drawable.orange_white_bg)
                holder.tvName.text = "You"
            } else {
                holder.clBg.setBackgroundResource(R.drawable.green_score_list_bg)
                holder.ClNos.setBackgroundResource(R.drawable.green_circle)
                holder.ClWhite.setBackgroundResource(R.drawable.white_background_shape)
                holder.tvName.text = leaderList[position].display_name
            }

            holder.tvScore.text = leaderList[position].userscores
            holder.tvCount.text = (position + 1).toString()
        }
    }

    fun addMoreItems(list: ArrayList<AllScoresInfo>) {
        list.reverse()
        for (data in list) {
            leaderList.add(0, data)
            notifyItemInserted(0)
        }
    }

}