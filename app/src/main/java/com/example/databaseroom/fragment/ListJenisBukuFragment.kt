package com.example.databaseroom.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databaseroom.R
import com.example.databaseroom.adapter.JenisBukuAdapter
import com.example.databaseroom.databinding.FragmentListJenisBukuBinding
import com.example.databaseroom.entity.JenisBukuEntity
import com.example.databaseroom.handler.OnClikJenisHandler
import com.example.databaseroom.injection.ViewModelJenisBukuFactory
import com.example.databaseroom.injection.ViewModelRakBukuFactory
import com.example.databaseroom.viewmodel.AddJenisBukuViewModel
import com.example.databaseroom.viewmodel.AddRakBukuViewModel

class ListJenisBukuFragment : Fragment(), OnClikJenisHandler {

    private var _binding: FragmentListJenisBukuBinding? = null
    private val binding get() = _binding!!

    //variabel yg akan d isi nnti yg isinya bisa berubah-ubah
    private lateinit var adapter: JenisBukuAdapter

    private val viewModel by lazy {
        //pabrik
        val pabrik = ViewModelJenisBukuFactory.ambilContoh(requireContext().applicationContext)
        ViewModelProvider(this, pabrik).get(AddJenisBukuViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListJenisBukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //pindah fragment
        binding.fabAddJenisBuku.setOnClickListener {
            findNavController().navigate(R.id.action_listJenisBukuFragment_to_addJenisBukuFragment)
        }

        //panggil viewmodel dan mengambil hasik dari database yang akan di tampilkan
        viewModel.getJenisBuku().observe(viewLifecycleOwner, Observer {
            setDataJenisBuku(it)
        })
    }

    //menampilkan data yg dari database
    fun setDataJenisBuku(listJenis: List<JenisBukuEntity>) {
        val rvJenisBuku = binding.rvJenisBuku
        adapter = JenisBukuAdapter(listJenis, this)
        rvJenisBuku.adapter = this.adapter
        rvJenisBuku.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onDeletJenis(jenis: JenisBukuEntity) {
        TODO("Not yet implemented")
    }

}