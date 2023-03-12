package com.example.classproject3.fragments;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.classproject3.R;

import java.io.IOException;
import java.io.InputStream;

public class LuckyChoiceFragment extends Fragment {

    View view;
    private ToggleButton toggleButton;
    private SeekBar seekBar;
    private Button playButton;

    private AudioTrack audioTrack;
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_lucky_choice , container , false);
        toggleButton = view.findViewById(R.id.toggleButton);
        seekBar = view.findViewById(R.id.music_seekBar);
        playButton = view.findViewById(R.id.button_play_music);

        try {
            AssetManager assetManager = getActivity().getAssets();
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd("audio/scatman.wav");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }





        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    float pitch = (float) Math.pow(2, (progress - 12) / 12.0);
                    if (mediaPlayer != null) {
                        if (pitch >= 0.5 && pitch <= 2.0) {
                            mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setPitch(pitch));
                        } else {
                            Toast.makeText(getContext(), "Invalid pitch value", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null && toggleButton.isChecked()) {
                    mediaPlayer.start();
                }
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (mediaPlayer != null) {
                    if (checked) {
                        mediaPlayer.start();
                    } else {
                        mediaPlayer.pause();
                    }
                }
            }
        });



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }








}}
