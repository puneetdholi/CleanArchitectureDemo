package com.example.architecturedemo.views.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturedemo.R
import com.example.domain.model.UserDomain

class UsersAdapter(
    private val userList: List<UserDomain>
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_row_user_details, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userDomain = userList[position]
        holder.userName.text = userDomain.name
        holder.userEmail.text = userDomain.email
        holder.userAddress.text = userDomain.suite + ", " + userDomain.street + ", " + userDomain.city + ", " + userDomain.zipCode
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById(R.id.tv_user_name) as TextView
        val userEmail = itemView.findViewById(R.id.tv_user_email) as TextView
        val userAddress = itemView.findViewById(R.id.tv_user_address) as TextView
    }
}