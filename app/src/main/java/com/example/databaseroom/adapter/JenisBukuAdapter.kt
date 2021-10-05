package com.example.databaseroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseroom.databinding.ItemJenisBukuBinding
import com.example.databaseroom.entity.JenisBukuEntity
import com.example.databaseroom.handler.OnClikJenisHandler

class JenisBukuAdapter(
    private val listJenisBuku: List<JenisBukuEntity>,
    private val onClikJenisHandler: OnClikJenisHandler
) : RecyclerView.Adapter<JenisBukuAdapter.JenisBukuViewHolder>() {

    //penggabung antara item  dan list fragment
    inner class JenisBukuViewHolder(private val binding: ItemJenisBukuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //pemersatu antara data dengan item
        fun bindJenisBuku(jenisBukuEntity: JenisBukuEntity) {
            binding.txtJudulBukuItem.text = jenisBukuEntity.judul_buku
            binding.tvHal.text = jenisBukuEntity.halaman
            binding.tvJenisBukuItem.text = jenisBukuEntity.jenisBuku
            binding.tvPenerbit.text = jenisBukuEntity.penerbit
            binding.tvTahunTerbit.text = jenisBukuEntity.tahunTerbit
        }
    }

    //yang minta item
    override fun getItemCount(): Int {
        return listJenisBuku.size
    }

    //yg mempersatukan holder
    override fun onBindViewHolder(holder: JenisBukuViewHolder, position: Int) {
        holder.bindJenisBuku(listJenisBuku[position])

//        holder.binding.btnDelet
    }

    //mempersatukan antara layout dengan holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JenisBukuViewHolder {
        val binding = ItemJenisBukuBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return JenisBukuViewHolder(binding)
    }


}