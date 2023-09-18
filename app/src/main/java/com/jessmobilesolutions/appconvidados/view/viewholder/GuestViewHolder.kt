package com.jessmobilesolutions.appconvidados.view.viewholder

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.jessmobilesolutions.appconvidados.R
import com.jessmobilesolutions.appconvidados.databinding.RowGuestBinding
import com.jessmobilesolutions.appconvidados.model.GuestModel
import com.jessmobilesolutions.appconvidados.view.listener.OnGuestListener

class GuestViewHolde(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    //atribui informacoes aos items da recycle

    fun bind(guest: GuestModel) {
//        itemView.findViewById<TextView>(R.id.text_name).text = guest.name
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.textName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton(
                    "Sim"
                ) { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()

            true
        }
    }

}