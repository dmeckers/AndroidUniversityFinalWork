package com.example.classproject3.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.classproject3.GlobalVariables;
import com.example.classproject3.MainActivity;
import com.example.classproject3.R;
import com.example.classproject3.RequestPostData;

import java.util.ArrayList;
import java.util.HashMap;

public class FirstHomeworkJsonFragment extends ListFragment {

    //    ListView postListView;
//
    ArrayList<HashMap<String, String>> blogPostData;

    RequestPostData task;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (task != null) {
            task.cancel(true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_list , container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        task = new RequestPostData(new ProgressDialog(getContext()), new RequestPostData.OnDataReceived() {
            @Override
            public void onDataReceive(ArrayList<HashMap<String, String>> blogData) {
                blogPostData = blogData;


                ListAdapter listAdapter = new SimpleAdapter(
                        getContext() , blogPostData , R.layout.item_single_post ,
                        new String[] {GlobalVariables.TITLE , GlobalVariables.BODY , "imageResource"} ,
                        new int[] {R.id.tv_title , R.id.tv_body , R.id.iv_image });


                getListView().setAdapter(listAdapter);

                getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        HashMap<String,String> post = blogPostData.get(i);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("post_data" , post);
                        ((MainActivity) getActivity()).startFragment(PostDetailsFragment.NewInstance(bundle));
                    }
                });
            }
        });
        task.execute();



    }

    //    View view;
//

//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_post_list , container , false);
//
//        postListView = view.findViewById(R.id.lv_posts);
//
//
//        new RequestPostData(new ProgressDialog(getContext()), new RequestPostData.OnDataReceived() {
//            @Override
//            public void onDataReceive(ArrayList<HashMap<String, String>> blogData) {
//                blogPostData = blogData;
//
//                ListAdapter listAdapter = new SimpleAdapter(
//                        getContext() , blogData , R.layout.item_single_post ,
//                        new String[] {GlobalVariables.TITLE , GlobalVariables.BODY} ,
//                        new int[] {R.id.tv_title , R.id.tv_body });
//
//
//                postListView.setAdapter(listAdapter);
//            }
//        }).execute();
//
//
//
//        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                HashMap<String,String> post = blogPostData.get(i);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("post_data" , post);
//                ((MainActivity) getActivity()).startFragment(PostDetailsFragment.NewInstance(bundle));
//            }
//        });
//
//        return view;
//    }







}
