package com.example.classproject3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classproject3.models.Person;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Person> list;

    public UserListAdapter(ArrayList<Person> list){
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener ;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new ItemPersonOneViewHolder( LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_person_list , parent , false));
        }
        else if(viewType == 1){
            return new ItemPersonTwoViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_person_two_list , parent , false));
        }else{
            return new ItemPersonOneViewHolder( LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_person_list , parent , false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {




        if(holder instanceof ItemPersonOneViewHolder){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
            String name = list.get(position).getName();
            ((ItemPersonOneViewHolder) holder).name.setText(name);
            ((ItemPersonOneViewHolder) holder).phone.setText("Mobile:" + list.get(position).getPhoneList().getMobile());
            ((ItemPersonOneViewHolder) holder).song.setText(list.get(position).getSongPlaylist().get(0));
            ((ItemPersonOneViewHolder) holder).icon.setImageResource(R.drawable.baseline_person_24);

        }
        if(holder instanceof  ItemPersonTwoViewHolder){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });

            ((ItemPersonTwoViewHolder) holder).name.setText(list.get(position).getName());
            ((ItemPersonTwoViewHolder) holder).phone.setText("Mobile" + list.get(position).getPhoneList().getMobile());


            if(Integer.parseInt( list.get(position).getId()) % 3 == 0){
                ((ItemPersonTwoViewHolder) holder).icon.setImageResource(R.drawable.baseline_image_24);
            }else if(Integer.parseInt( list.get(position).getId()) % 3 == 1){
                ((ItemPersonTwoViewHolder) holder).icon.setImageResource(R.drawable.baseline_data_object_24);
            }else if(Integer.parseInt( list.get(position).getId()) % 3 == 2){
                ((ItemPersonTwoViewHolder) holder).icon.setImageResource(R.drawable.baseline_home_24);
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemPersonOneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name , phone , song ;
        ImageView icon;

        ItemPersonOneViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            phone = itemView.findViewById(R.id.tv_phone);
            song = itemView.findViewById(R.id.tv_best_song);
            icon = itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    class ItemPersonTwoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name , phone , song ;
        ImageView icon;

        ItemPersonTwoViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.tv_nameTwo);
            phone = itemView.findViewById(R.id.tv_phoneTwo);
            song = itemView.findViewById(R.id.tv_best_songTwo);
            icon = itemView.findViewById(R.id.iv_iconTwo);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }

}
