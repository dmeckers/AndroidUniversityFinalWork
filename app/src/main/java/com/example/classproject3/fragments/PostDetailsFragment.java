package com.example.classproject3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.classproject3.GlobalVariables;
import com.example.classproject3.R;

import java.util.HashMap;

public class PostDetailsFragment extends Fragment {


    View view;
    int[] iconResources = {R.drawable.baseline_audiotrack_24 , R.drawable.baseline_cabin_24 , R.drawable.baseline_bakery_dining_24 ,
            R.drawable.baseline_beach_access_24 , R.drawable.baseline_sports_volleyball_24 ,
            R.drawable.baseline_style_24,R.drawable.baseline_pix_24 , R.drawable.baseline_storm_24 ,
            R.drawable.baseline_bakery_dining_24 , R.drawable.baseline_sports_football_24};

    private TextView tv_post_body;

    private boolean toExpand = false;

    private Button button_expand;





    public static PostDetailsFragment NewInstance(Bundle bundle){
        PostDetailsFragment fragment = new PostDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_post_details , container , false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();

        if(args != null){

            @SuppressWarnings("unchecked")
            HashMap<String , String> postData = (HashMap<String, String>) args.getSerializable("post_data");

            TextView tv_title = view.findViewById(R.id.title_textview);
            tv_post_body = view.findViewById(R.id.post_body);
            Button button_back = view.findViewById(R.id.back_button);
            tv_title.setText(postData.get(GlobalVariables.TITLE));
            tv_post_body.setText(postData.get(GlobalVariables.BODY));
            button_expand = view.findViewById(R.id.button_expand);
            ImageView header_icon = view.findViewById(R.id.header_icon);
            TextView tv_author_n_post_id = view.findViewById(R.id.tv_author);
            String postText = "Post nr." + postData.get(GlobalVariables.ID) + ". Posted by user nr" + postData.get(GlobalVariables.USER_ID);
            tv_author_n_post_id.setText(postText);
            header_icon.setImageResource(iconResources[Integer.parseInt(postData.get(GlobalVariables.USER_ID)) - 1]);

            if(tv_post_body.getText().length() > 100){
                button_expand.setVisibility(View.VISIBLE);
            }else{
                button_expand.setVisibility(View.GONE);
            }


            button_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getParentFragmentManager().popBackStack();
                }
            });


            button_expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(toExpand == false){
                        tv_post_body.setMaxLines(Integer.MAX_VALUE);
                        button_expand.setText("Collapse");
                    }else{
                        tv_post_body.setMaxLines(5);
                        button_expand.setText("Expand");
                    }
                    toExpand = !toExpand;
                }
            });






        }


    }
}
