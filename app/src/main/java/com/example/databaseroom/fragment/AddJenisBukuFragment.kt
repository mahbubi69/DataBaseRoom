package com.example.databaseroom.fragment

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.Builder
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.databaseroom.R
import com.example.databaseroom.databinding.FragmentAddJenisBukuBinding
import com.example.databaseroom.entity.JenisBukuEntity
import com.example.databaseroom.injection.ViewModelJenisBukuFactory
import com.example.databaseroom.value.Value
import com.example.databaseroom.viewmodel.AddJenisBukuViewModel

class AddJenisBukuFragment : Fragment() {

    private var _binding: FragmentAddJenisBukuBinding? = null
    private val binding get() = _binding!!

    //memanggil view model
    //data yg sudah di bungkus dari pabrik yg sdah siap di tampilkan ke ui
    private val viewModel by lazy {
        val factory = context?.applicationContext?.let {
            ViewModelJenisBukuFactory.ambilContoh(it)
        }
        ViewModelProvider(this, factory!!).get(AddJenisBukuViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddJenisBukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSimpan.setOnClickListener {
            Toast.makeText(context, "data berhasil di simpan", Toast.LENGTH_SHORT).show()
            saveJenisBuuku(it)
        }
    }

    //yang akan mengesave database jenis buku
    fun saveJenisBuuku(view: View) {
        val judulbuku = binding.edtJudulBuku.text.toString()
        val halaman = binding.edtHalamanBuku.text.toString()
        //redio jenis buku
        val radioJenis = {
            if (view is RadioButton) {
                //val periksa
                val checked = view.isChecked
                when (view.id) {
                    R.id.radio_cerpen ->
                        if (checked) {
                            val cerpen = "cerpen"
                            binding.radioCerpen.text = cerpen
                        }
                    R.id.radio_novel ->
                        if (checked) {
                            val novel = "novel"
                            binding.radioNovel.text = novel
                        }
                }
            }
        }
        val penerbit = binding.edtPenerbit.text.toString()
        val thnTerbit = binding.edtThnTerbit.text.toString()

        try {
            viewModel.addJenisbuku(
                JenisBukuEntity(
                    judul_buku = judulbuku,
                    halaman = halaman,
                    jenisBuku = radioJenis.toString(),
                    penerbit = penerbit,
                    tahunTerbit = thnTerbit
                )
            )
        } finally {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}