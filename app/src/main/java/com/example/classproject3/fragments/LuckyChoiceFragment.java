package com.example.classproject3.fragments;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.classproject3.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Date;

public class LuckyChoiceFragment extends Fragment {


    View view;
    ImageView iv_qr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lucky_choice , container , false);
        iv_qr = view.findViewById(R.id.iv_qr);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        long unixTime = new Date().getTime() / 1000L;

        QRCodeWriter writer = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = writer.encode(String.valueOf(unixTime), BarcodeFormat.QR_CODE, 512, 512);
            Bitmap bitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);

            for (int i = 0; i < bitMatrix.getWidth(); i++) {
                for (int j = 0; j < bitMatrix.getHeight(); j++) {
                    if (bitMatrix.get(i, j)) {
                        int color = ((int) ((unixTime + i) * (unixTime + j)) % 3) == 0 ? 0xff000000 : 0xffffffff;
                        bitmap.setPixel(i, j, color);
                    }
                }
            }

            iv_qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


    }
}
