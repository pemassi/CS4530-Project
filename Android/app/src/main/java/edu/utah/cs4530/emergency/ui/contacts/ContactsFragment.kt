package edu.utah.cs4530.emergency.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import edu.utah.cs4530.emergency.R
import edu.utah.cs4530.emergency.abstract.LiveModelFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.provider.ContactsContract
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_contacts.view.*
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.fragment_history_detail.view.*
import java.util.ArrayList


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

            adapter =  ContactsAdapter()
        }

        root.btn_contacts.setOnClickListener(Object.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);


            }
        });
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if(resultCode == RESULT_OK) {
            Cursor cursor = getContentResolver().query(data.getData(),
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);

            cursor.moveToFirst();

            com.example.contactlisttest.data contactLists = new data();

            contactLists.setName(cursor.getString(0));
            contactLists.setNumber(cursor.getString(1));

            adapter.addItem(contactLists);
            recyclerView.setAdapter(adapter);
            cursor.close();

        }
    }

    }


}