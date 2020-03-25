package edu.utah.cs4530.emergency.ui.contacts

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.gun0912.tedonactivityresult.TedOnActivityResult
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.abstract.LiveModelFragment
import edu.utah.cs4530.emergency.dao.ContactDAO
import kotlinx.android.synthetic.main.fragment_contacts.view.*


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
            root.contactsRecyclerView.adapter = ContactsAdapter(it)
        })

        root.btn_contacts.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

            TedOnActivityResult.with(context)
                .setIntent(intent)
                .setListener { resultCode, data ->
                    if(resultCode == RESULT_OK)
                    {
                        val cursor = context!!.contentResolver.query(data.data!!, arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null)!!

                        cursor.moveToFirst()

                        viewModel.addContactList(ContactDAO(
                            name =  cursor.getString(0),
                            phoneNumber = cursor.getString(1),
                            photoUri = ""
                        ))

                        cursor.close()
                    }
                }
                .startActivityForResult()
        }
    }
}