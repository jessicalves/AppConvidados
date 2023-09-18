package com.jessmobilesolutions.appconvidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jessmobilesolutions.appconvidados.constants.DataBaseConstants
import com.jessmobilesolutions.appconvidados.databinding.FragmentAllGuestsBinding
import com.jessmobilesolutions.appconvidados.view.adapter.GuestsAdapter
import com.jessmobilesolutions.appconvidados.view.listener.OnGuestListener
import com.jessmobilesolutions.appconvidados.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        //Layout - comportamento da recycleview
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)

        //Adapter - juncao do layout com a lista, faz eles se conectar
        binding.recyclerGuests.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerGuests.context,
            (binding.recyclerGuests.layoutManager as LinearLayoutManager).orientation
        )
        binding.recyclerGuests.addItemDecoration(dividerItemDecoration)

        //Classe Anonima
        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }

        }

        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}