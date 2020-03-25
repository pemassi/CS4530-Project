package edu.utah.cs4530.emergency.ui.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.utah.cs4530.emergency.R;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ItemViewHolder> {

    private ArrayList<data> listData = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(data data) {
        listData.add(data);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView number;
        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_contacts_name);
            number = itemView.findViewById(R.id.txt_contacts_number);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void onBind(data data) {
            name.setText(data.getName());
            number.setText(data.getNumber());
            imageView.setImageResource(data.getPhoto());
        }
    }
}
