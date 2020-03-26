package edu.utah.cs4530.emergency.ui.contacts

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gun0912.tedonactivityresult.TedOnActivityResult
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.abstract.LiveModelFragment
import edu.utah.cs4530.emergency.dao.ContactDAO
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_contacts.view.*
import kotlinx.android.synthetic.main.fragment_contacts.view.contactsRecyclerView


class ContactsFragment : LiveModelFragment<ContactsViewModel>(ContactsViewModel::class, R.layout.fragment_contacts) {

    override fun onCreateViewModel(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    {

        root.contactsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }

        viewModel.contactList.observe(this, Observer {
            val adapter = ContactsAdapter(it, viewModel)
            root.contactsRecyclerView.adapter = adapter
            ItemTouchHelper(ItemTouchHelperCallback(adapter));
        })

        root.btn_addContacts.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            TedOnActivityResult.with(context)
                .setIntent(intent)
                .setListener { resultCode, data ->
                    if(resultCode == RESULT_OK)
                    {
                        applicationContext.contentResolver.query(data.data!!, arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null)!!.use {
                            it.moveToFirst()
                            viewModel.addContactList(ContactDAO(
                                name =  it.getString(0),
                                phoneNumber = it.getString(1),
                                photoUri = null
                            ))
                        }
                    }
                }
                .startActivityForResult()
        }

    }
}