package com.example.databaseroom.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseroom.R
import com.example.databaseroom.databinding.ItemRakBukuBinding
import com.example.databaseroom.entity.RakBukuEntity
import com.example.databaseroom.fragment.ListRakBukuFragmentDirections
import com.example.databaseroom.handler.OnClikHandler


class RakBukuAdapter(
    private val listdatarakbuku: List<RakBukuEntity>,
    private val onClikHandler: OnClikHandler
) : RecyclerView.Adapter<RakBukuAdapter.RakBukuViewHolder>() {

    inner class RakBukuViewHolder(val binding: ItemRakBukuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindRakData(rakEntity: RakBukuEntity) {
            binding.tvJneisBuku.text = rakEntity.jenisBuku
            binding.tvKataKata.text = rakEntity.kata
            binding.tvTanggal.text = rakEntity.tanggal
        }
    }

    override fun onBindViewHolder(holder: RakBukuViewHolder, position: Int) {
        holder.bindRakData(listdatarakbuku[position])
        //mempersatukan antara item
        holder.binding.btnUpdate.setOnClickListener {
            val direction = ListRakBukuFragmentDirections.actionListBukuFragmentToEdtRakBukuFragment(passId = listdatarakbuku[position].id)
            it.findNavController().navigate(direction)
        }
//        holder.itemView.setOnClickListener {
//            val arah = it.findNavController().navigate(R.id.action_listBukuFragment_to_listJenisBukuFragment)
//            onClikHandler.onclikRakBuku(listdatarakbuku[position])
//        }

        holder.binding.btnDelet.setOnClickListener {
            try {
                onClikHandler.onDelet(listdatarakbuku[position])
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                notifyItemRemoved(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return listdatarakbuku.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RakBukuViewHolder {
        //menggabungkan ke layar
        val binding = ItemRakBukuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RakBukuViewHolder(binding)
    }


}


