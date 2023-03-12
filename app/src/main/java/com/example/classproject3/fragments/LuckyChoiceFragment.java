package com.example.classproject3.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.classproject3.MainActivity;
import com.example.classproject3.R;

public class LuckyChoiceFragment extends Fragment {

    View view;
    private View[] colorViews;
    private int colorIndex = 0;
    private Handler handler;
    private Runnable colorChanger;
    private boolean isRunning = false;
    private int delayTime = 1000;
    private String[] colorOptions = {"Red", "Green", "Blue", "Yellow", "Magenta"};
    Button toggle_button;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lucky_choice , container , false);
        toggle_button = view.findViewById(R.id.btn_toggle_strip);




        toggle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning){
                    handler.removeCallbacks(colorChanger);
                    isRunning = false;
                }else{
                    handler.postDelayed(colorChanger, delayTime);
                    isRunning = true;
                }
            }
        });


        colorViews = new View[]{
                view.findViewById(R.id.color_dot_1),
                view.findViewById(R.id.color_dot_2),
                view.findViewById(R.id.color_dot_3),
                view.findViewById(R.id.color_dot_4),
                view.findViewById(R.id.color_dot_5)
        };

        SeekBar seekBar = view.findViewById(R.id.seek_bar_speed);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int speed =  (int)((progress + 5) / 10f);
                setDelayTime(speed);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar.setMax(30);
        seekBar.setProgress(10);



        Spinner colorSpinner = view.findViewById(R.id.color_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.colors_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);
        colorSpinner.setSelection(0);



        handler = new Handler(Looper.getMainLooper());

        colorChanger = new Runnable() {
            @Override
            public void run() {

                colorIndex = (colorIndex + 1) % colorViews.length;
                setColorForIndex(colorIndex, getSelectedColor());


//                setColorForIndex((colorIndex + 4) % colorViews.length, Color.WHITE);
//
//                setColorForIndex(colorIndex, Color.BLUE);
//
//                colorIndex = (colorIndex + 1) % colorViews.length;

                handler.postDelayed(colorChanger, delayTime);
            }
        };
        return view;
    }






    @Override
    public void onResume() {
        super.onResume();
        if (!isRunning) {
            handler.postDelayed(colorChanger, delayTime);
            isRunning = true;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(colorChanger);
        isRunning = false;
    }

    private void setColorForIndex(int index, int color) {
        for (int i = 0; i < colorViews.length; i++) {
            if (i == index) {
                colorViews[i].setBackgroundColor(color);
            } else {
                colorViews[i].setBackgroundColor(Color.WHITE);
            }
        }
    }



    private int getSelectedColor() {
        Spinner colorSpinner = getView().findViewById(R.id.color_spinner);
        Object selectedItem = colorSpinner.getSelectedItem();
        if (selectedItem != null) {
            int colorValue = Color.parseColor(selectedItem.toString());
            return colorValue;
        } else {
            return Color.BLACK;
        }
    }


    public void setDelayTime(int delayTime) {
        // ограничиваем задержку между изменениями цвета
        this.delayTime = Math.max(100, Math.min(delayTime * 1000, 5000));
        // перезапускаем задачу с новой задержкой
        if (isRunning) {
            handler.removeCallbacks(colorChanger);
            handler.postDelayed(colorChanger, this.delayTime);
        }
    }







}
