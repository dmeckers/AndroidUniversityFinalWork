package com.example.classproject3.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classproject3.MusicListAdapter;
import com.example.classproject3.R;
import com.example.classproject3.models.Person;


import java.io.FileInputStream;
import java.io.IOException;

public class HomeWorkJsonFragment extends Fragment {


    View view;
    MusicListAdapter songPlaylistAdapter ,  artistPlaylistAdapter ;



    RecyclerView favoriteArtistRecyclerView , favoriteSongRecyclerView;
    private Parcelable person;

    public static HomeWorkJsonFragment NewInstance(Parcelable person){
        HomeWorkJsonFragment fragment = new HomeWorkJsonFragment();
        Bundle args = new Bundle();
        args.putParcelable("person_data",person);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null){
            person = getArguments().getParcelable("person_data");
            TextView tv_name = getView().findViewById(R.id.tv_profile_name);
            tv_name.setText(((Person)person).getName());
            TextView tv_address = getView().findViewById(R.id.tv_profile_address);
            tv_address.setText(((Person)person).getAddress());
            TextView tv_phone = getView().findViewById(R.id.tv_phone_number);
            tv_phone.setText("Mobile:"+((Person)person).getPhoneList().getMobile());






            favoriteSongRecyclerView = view.findViewById(R.id.rv_song_list);
            songPlaylistAdapter = new MusicListAdapter(((Person) person).getSongPlaylist() ,1  , getContext());
            LinearLayoutManager songLinearLayoutManager = new LinearLayoutManager(getContext() , RecyclerView.HORIZONTAL , false);
            favoriteSongRecyclerView.setLayoutManager(songLinearLayoutManager);
            favoriteSongRecyclerView.setAdapter(songPlaylistAdapter);

            favoriteArtistRecyclerView = view.findViewById(R.id.rv_artist_list);
            artistPlaylistAdapter = new MusicListAdapter(((Person) person).getArtistPlaylist() , 2 , getContext());
            LinearLayoutManager artistLinearLayoutManager = new LinearLayoutManager(getContext() , RecyclerView.HORIZONTAL , false);
            favoriteArtistRecyclerView.setLayoutManager(artistLinearLayoutManager);
            favoriteArtistRecyclerView.setAdapter(artistPlaylistAdapter);

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user_details , container , false);




        return view;
    }





}
