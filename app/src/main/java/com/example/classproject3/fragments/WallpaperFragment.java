package com.example.classproject3.fragments;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.classproject3.EquationCheckBottomSheetDialog;
import com.example.classproject3.R;

import java.io.IOException;
import java.io.InputStream;

public class WallpaperFragment extends Fragment {

    View view;
    LinearLayout linearLayout;
    ImageView bigPicture;
    int toPhone;
    Button btnChangeWallpaper;

    View.OnClickListener openDialogListener;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wallpaper , container , false);
        bigPicture = view.findViewById(R.id.iv_bigpicture);
        linearLayout = view.findViewById(R.id.ll_view);
        btnChangeWallpaper = view.findViewById(R.id.btn_changewallpaper);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupImageList();
        openDialogListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EquationCheckBottomSheetDialog bottomSheetDialog = new EquationCheckBottomSheetDialog(new EquationCheckBottomSheetDialog.OnClickCalculationListener() {
                    @Override
                    public void OnCalculationClicked(boolean isCalculationCorrect) {
                        if(isCalculationCorrect){

                            InputStream is = getResources().openRawResource(toPhone);
                            Bitmap pic = BitmapFactory.decodeStream(is);
                            WallpaperManager myWallpaper = WallpaperManager.getInstance(getContext());
                            try {
                                myWallpaper.setBitmap(pic);
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                });

                bottomSheetDialog.show(getActivity().getSupportFragmentManager() , "");

            }
        };

        btnChangeWallpaper.setOnClickListener(openDialogListener);

    }

    protected void setupImageList(){
        for(int i=1 ; i<= 13 ; i++)
        {
            final ImageView iv = new ImageView(getContext());
            String fileName = "spic" + i;
            int id = getResources().getIdentifier(fileName , "drawable" , getActivity().getPackageName());

            iv.setImageResource(id);
            iv.setId(i);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(500 , ViewGroup.LayoutParams.WRAP_CONTENT);


            iv.setLayoutParams(lp);
            linearLayout.addView(iv);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    int ide = iv.getId();
//                    String b = "pic" + ide;
//                    int id = getResources().getIdentifier(b , "drawable" , getActivity().getPackageName());
//                    bigPicture.setImageResource(id);
//                    toPhone = id;

                    int ide = iv.getId();
                    String b = "pic" + ide;
                    int id = getResources().getIdentifier(b , "drawable" , getActivity().getPackageName());

                    InputStream is = getResources().openRawResource(id);
                    Bitmap pic = BitmapFactory.decodeStream(is);

                    int width = pic.getWidth();
                    int height = pic.getHeight();
                    int newWidth = 500;
                    int newHeight = (int) Math.floor((double) height * ((double) newWidth / (double) width));
                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(pic, newWidth, newHeight, true);
                    bigPicture.setImageBitmap(resizedBitmap);

                    toPhone = id;
                }
            });


        }
    }



}
