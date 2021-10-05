package com.example.databaseroom.fragment

import android.app.Notification
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databaseroom.R
import com.example.databaseroom.adapter.RakBukuAdapter
import com.example.databaseroom.databinding.FragmentListRakBukuBinding
import com.example.databaseroom.entity.RakBukuEntity
import com.example.databaseroom.handler.OnClikHandler
import com.example.databaseroom.injection.ViewModelRakBukuFactory
import com.example.databaseroom.value.Value
import com.example.databaseroom.viewmodel.AddRakBukuViewModel

class ListRakBukuFragment : Fragment(), OnClikHandler {

    private var _binding: FragmentListRakBukuBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RakBukuAdapter

    //notif
    private lateinit var notificationManagerCompat: NotificationManagerCompat
    val textTitle = "delet"
    val subText = " data berhasil d delet"

    val viewModelAdd by lazy {
        val factory = ViewModelRakBukuFactory.getInstance(requireContext().applicationContext)
        ViewModelProvider(this, factory).get(AddRakBukuViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListRakBukuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get notif
        notificationManagerCompat = NotificationManagerCompat.from(requireContext())

        binding.fabAddRakBuku.setOnClickListener {
            findNavController().navigate(R.id.action_listBukuFragment_to_addBukuFragment2)
        }

        viewModelAdd.mengambilRakBuku().observe(viewLifecycleOwner, Observer {
            setDataRakBuku(it)
        })
    }

    //menampilkan
    fun setDataRakBuku(list: List<RakBukuEntity>) {
        val rvRakDataBuku = binding.rvListBuku
        adapter = RakBukuAdapter(list, this)
        rvRakDataBuku.adapter = this.adapter
        rvRakDataBuku.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onclikRakBuku(RakBuku: RakBukuEntity) {
        val nextListJenis =
            ListRakBukuFragmentDirections.actionListBukuFragmentToListJenisBukuFragment()
        view?.findNavController()?.navigate(nextListJenis)
    }

    override fun onDelet(rakBuku: RakBukuEntity) {
        try {
            notifDelet()
            viewModelAdd.deletRakBuku(rakBuku)
        } catch (e: Exception) {

        } finally {
            Toast.makeText(context, "data berhasil dihapus", Toast.LENGTH_SHORT).show()
        }
    }

    fun notifDelet() {
        val build = NotificationCompat.Builder(requireContext(), Value.CHANEL_2)
            .setSmallIcon(R.drawable.ic_notif)
            .setContentTitle(textTitle)
            .setContentText(subText)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
        val notification: Notification = build.build()
        notificationManagerCompat.notify(1, notification)
    }
}