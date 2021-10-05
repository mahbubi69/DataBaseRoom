package com.example.databaseroom.fragment


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.databaseroom.R
import com.example.databaseroom.databinding.FragmentAddRakBukuBinding
import com.example.databaseroom.entity.RakBukuEntity
import com.example.databaseroom.helper.RakBukuHelper
import com.example.databaseroom.injection.ViewModelRakBukuFactory
import com.example.databaseroom.value.Value
import com.example.databaseroom.viewmodel.AddRakBukuViewModel

class AddRakBukuFragment : Fragment() {

    private var _binding: FragmentAddRakBukuBinding? = null
    private val binding get() = _binding!!

    //date
    var date = ""

    //notif
    private lateinit var notificationManagerCompat: NotificationManagerCompat
    val textTitle = "data"
    val textDesc = "data berhasil disimpan"


    private val viewModel by lazy {
        val factory = context?.applicationContext?.let {
            ViewModelRakBukuFactory.getInstance(it)
        }
        ViewModelProvider(this, factory!!).get(AddRakBukuViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddRakBukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        //get notiv
        notificationManagerCompat = NotificationManagerCompat.from(requireContext())

        binding.btnSimpan.setOnClickListener {
            Toast.makeText(context, "data berhasial di simpan", Toast.LENGTH_SHORT).show()
            saveDataRakBuku(it)
            notifAddData()
        }
        initiateViewDate()
    }

    //view date
    fun initiateViewDate() {
        date = RakBukuHelper.getTodayDate()
        binding.tvTanggal.text = date
    }

    fun saveDataRakBuku(view: View) {
        val namaRakBuku = binding.edNamaRakBuku.text.toString()
        val kata = binding.addKataKata.text.toString()

        try {
//            if (kata.isNotEmpty()) {
//                Toast.makeText(
//                    requireContext(),
//                    "nama dan kata tidak boleh kosong",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//            if (namaRakBuku.isNotEmpty()) {
//                Toast.makeText(requireContext(), "nama rak tidak boleh kosong", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
            viewModel.nambahRakBuku(
                RakBukuEntity(
                    jenisBuku = namaRakBuku,
                    kata = kata,
                    tanggal = date
                )
            )

//            }
        } finally {
            view.findNavController().popBackStack()
        }


    }

    fun notifAddData() {
        val build = NotificationCompat.Builder(requireContext(), Value.CHANEL_1)
            .setSmallIcon(R.drawable.ic_notif)
            .setContentTitle(textTitle)
            .setContentText(textDesc)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
        val notification: Notification = build.build()
        notificationManagerCompat.notify(1, notification)


    }

//    private fun notif() {
//        val builder1 = NotificationCompat.Builder(requireContext(), Value.CHANEL_1)
//            .setSmallIcon(R.drawable.ic_notif)
//            .setContentTitle("notif1")
//            .setContentText("data sudah disimpan1")
//            .setStyle(
//                NotificationCompat.BigTextStyle()
//                    .bigText("text panjang pemberitahuan1")
//            )
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        val builder2 = NotificationCompat.Builder(requireContext(), Value.CHANEL_1)
//            .setSmallIcon(R.drawable.ic_notif)
//            .setContentTitle("notif2")
//            .setContentText("data sudah disimpan2")
//            .setStyle(
//                NotificationCompat.BigTextStyle()
//                    .bigText("text panjang pemberitahuan2")
//            )
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            val nama = getString(R.string.channel_nama)
//            val desc = getString(R.string.channel_desc)
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(Value.CHANEL_1, nama, importance).apply {
//                description = desc
//            }
//            val notificationManager: NotificationManager =
//                ContextCompat.getSystemService() as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}