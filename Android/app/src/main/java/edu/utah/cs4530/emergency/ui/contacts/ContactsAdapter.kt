package edu.utah.cs4530.emergency.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.dao.ContactDAO
import edu.utah.cs4530.emergency.ui.contacts.ContactsAdapter.ItemViewHolder
import java.util.*

class ContactsAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    private val listData = ArrayList<ContactDAO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contacts, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun addItem(ContactDAO: ContactDAO) {
        listData.add(ContactDAO)
    }

    inner class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val name: TextView
        private val number: TextView
        private val imageView: ImageView

        fun onBind(contactDAO: ContactDAO) {
            name.text = contactDAO.name
            number.text = contactDAO.number

            imageView.setImageResource(contactDAO.photo)
        }

        init {
            name = itemView.findViewById(R.id.txt_contacts_name)
            number = itemView.findViewById(R.id.txt_contacts_number)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }
}