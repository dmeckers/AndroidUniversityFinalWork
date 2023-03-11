package com.example.classproject3.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classproject3.MainActivity;
import com.example.classproject3.R;
import com.example.classproject3.RequestPersonalData;
import com.example.classproject3.UserListAdapter;
import com.example.classproject3.models.Person;

import java.util.ArrayList;

public class AdvancedJsonFragment extends Fragment {

    View view;

    private ProgressDialog progressDialog ;
    UserListAdapter userListAdapter;
    RecyclerView recyclerView;

    ArrayList<Person> persons;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_advanced , container , false);


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data...Please wait...");
        progressDialog.setCancelable(false);


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new RequestPersonalData(progressDialog, new RequestPersonalData.OnPersonListDataReceived() {
            @Override
            public void onDataReceived(ArrayList<Person> list) {
                setAdapter(view , list);
            }
        }).execute();
    }

    private void setAdapter(View view, ArrayList<Person> list) {
        recyclerView = view.findViewById(R.id.rv_list);
        userListAdapter = new UserListAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);

        userListAdapter.setOnItemClickListener(new UserListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Person p = list.get(position);


                Fragment fragment = HomeWorkJsonFragment.NewInstance(p);
                ((MainActivity)getActivity()).startFragment(fragment);

            }
        });

        recyclerView.setAdapter(userListAdapter);



    }
}
