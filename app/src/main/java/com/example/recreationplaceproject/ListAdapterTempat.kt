package com.example.recreationplaceproject

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recreationplaceproject.DetailActivity.Companion.EXTRA_IMAGE
import java.lang.Thread.sleep

class ListAdapterTempat(private val listTempat : ArrayList<TempatRekreasi>) : RecyclerView.Adapter<ListAdapterTempat.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNamaTempat : TextView = itemView.findViewById(R.id.tv_nama_tempat)
        var tvDeskripsiTempat : TextView = itemView.findViewById(R.id.tv_deskripsi_tempat)
        var imgTempat : ImageView = itemView.findViewById(R.id.photo_recreation_place)
        var buttonCheck : Button = itemView.findViewById(R.id.btn_check)
        var buttonShare : Button = itemView.findViewById(R.id.btn_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_recreation_place, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tempat = listTempat[position]

        Glide.with(holder.itemView.context)
            .load(tempat.photo)
            .apply(RequestOptions().override(100, 100))
            .into(holder.imgTempat)

        holder.tvNamaTempat.text = tempat.name
        holder.tvDeskripsiTempat.text = tempat.detail

        holder.buttonCheck.setOnClickListener {
            val detailIntent = Intent(it.context, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.EXTRA_NAME, listTempat[holder.adapterPosition].name)
            detailIntent.putExtra(DetailActivity.EXTRA_DESCRIPTION, listTempat[holder.adapterPosition].detail)
            detailIntent.putExtra(DetailActivity.EXTRA_IMAGE, listTempat[holder.adapterPosition].photo)
            it.context.startActivity(detailIntent)
        }

        holder.buttonShare.setOnClickListener {
            val shareTextIntent = Intent(Intent.ACTION_SEND)
            val shareImageIntent = Intent(Intent.ACTION_SEND)

            shareTextIntent.putExtra(Intent.EXTRA_TEXT, "Ayo berkunjung ke ${listTempat[holder.adapterPosition].name}!!\nDeskripsi:\n ${listTempat[holder.adapterPosition].detail}")
            shareTextIntent.setType("text/plain")
            it.context.startActivity(Intent.createChooser(shareTextIntent, "Share"))
            //send gambar
            /*
            sleep(7500)
            val imgUri = Uri.parse("android.resource://com.example.recreationplaceproject/" + listTempat[holder.adapterPosition].photo)
            shareImageIntent.putExtra(Intent.EXTRA_STREAM, imgUri)
            shareImageIntent.setType("image/*")
            shareImageIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            it.context.startActivity(Intent.createChooser(shareImageIntent, "Share Gambar"))
             */*/
        }
    }

    override fun getItemCount(): Int {
        return listTempat.size
    }
}