package com.example.databaseroom.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.databaseroom.databinding.FragmentEdtRakBukuBinding
import com.example.databaseroom.entity.RakBukuEntity
import com.example.databaseroom.helper.RakBukuHelper
import com.example.databaseroom.injection.ViewModelRakBukuFactory
import com.example.databaseroom.viewmodel.EdtRakBukuViewModel
import java.lang.Exception


class EdtRakBukuFragment : Fragment() {

    private var _binding: FragmentEdtRakBukuBinding? = null
    private val binding get() = _binding!!

    var date = ""
    private var idBuku = 0

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let {
            ViewModelRakBukuFactory.getInstance(it)
        }
        ViewModelProvider(this, factory!!).get(EdtRakBukuViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEdtRakBukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argument = arguments?.let { EdtRakBukuFragmentArgs.fromBundle(it) }
        idBuku = argument?.passId ?: 0

        viewModel.getRakBukuById(idBuku).observe(viewLifecycleOwner, Observer {
            binding.edtUpdateNamaRakBuku.setText(it.jenisBuku)
            binding.edtUpdateKataKata.setText(it.kata)
        })

        binding.btnSimpan.setOnClickListener {
            Toast.makeText(context, "data berhasil di update", Toast.LENGTH_SHORT).show()
            edtRakBuku(it)
        }
        iniateDateView()
    }

    fun iniateDateView() {
        date = RakBukuHelper.getTodayDate()
        binding.tvUpdateTanggal.text = date
    }

    fun edtRakBuku(view: View) {
        val namaRakBuku = binding.edtUpdateNamaRakBuku.text.toString()
        val kata = binding.edtUpdateKataKata.text.toString()

        try {
            viewModel.updateRakBuku(
                RakBukuEntity(
                    id = idBuku,
                    jenisBuku = namaRakBuku,
                    kata = kata,
                    tanggal = date
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            view.findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}