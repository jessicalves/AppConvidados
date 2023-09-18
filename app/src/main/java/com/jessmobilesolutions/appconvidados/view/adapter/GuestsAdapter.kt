package com.jessmobilesolutions.appconvidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jessmobilesolutions.appconvidados.databinding.RowGuestBinding
import com.jessmobilesolutions.appconvidados.model.GuestModel
import com.jessmobilesolutions.appconvidados.view.listener.OnGuestListener
import com.jessmobilesolutions.appconvidados.view.viewholder.GuestViewHolde

class GuestsAdapter: RecyclerView.Adapter<GuestViewHolde>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolde {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolde(item, listener)
    }

    override fun onBindViewHolder(holder: GuestViewHolde, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updateGuests(list: List<GuestModel>){
        guestList = list
        notifyDataSetChanged()
    }

    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener
    }
}