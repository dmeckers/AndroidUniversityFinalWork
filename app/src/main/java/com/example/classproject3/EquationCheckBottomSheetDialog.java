package com.example.classproject3;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

public class EquationCheckBottomSheetDialog  extends BottomSheetDialogFragment {


    private OnClickCalculationListener listener;



    public EquationCheckBottomSheetDialog(OnClickCalculationListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bottom_sheet_layout, container , false);
        TextView tv_Operator = v.findViewById(R.id.tv_operator);
        EditText et_SecondOperand = v.findViewById(R.id.et_second_operand);
        EditText et_FirstOperand = v.findViewById(R.id.et_first_operand);
        TextView tv_OperationResult = v.findViewById(R.id.tv_operation_result);
        Button btn = v.findViewById(R.id.btn_validate);
        Button btn_cancel = v.findViewById(R.id.btn_cancel);

        int randomResult = new Random().nextInt(100);
        int randomOperationIndex = ((new Random().nextInt(3)) + 1);
//        randomOperationIndex = randomOperationIndex < 1 ? randomOperationIndex++ : randomOperationIndex > 4 ? randomOperationIndex-- : randomOperationIndex;

        this.setCancelable(false);

        Map<Integer ,Tuple<String , BiFunction<Integer , Integer , Integer>>>
                operations = new HashMap<>();

        operations.put(1, new Tuple<>("+" , (a, b) -> a + b));
        operations.put(2 , new Tuple<>("-" , (a, b) -> a - b));
        operations.put(3 , new Tuple<>("*" , (a, b) -> a * b));
        operations.put(4 , new Tuple<>("/" , (a, b) -> a / b));

        tv_OperationResult.setText(" = " + randomResult);
        tv_Operator.setText(operations.get(randomOperationIndex).key);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        int finalRandomOperationIndex = randomOperationIndex;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int firstOperand = Integer.parseInt(et_FirstOperand.getText().toString());
                    int secondOperand = Integer.parseInt(et_SecondOperand.getText().toString());

                    Integer userResult =   operations.get(finalRandomOperationIndex).value.apply(firstOperand , secondOperand);


                    if(userResult.equals(randomResult)){
                        Toast.makeText(requireContext().getApplicationContext(), "Enjoy your new wallpapers", Toast.LENGTH_SHORT).show();
                        listener.OnCalculationClicked(true);
                        dismiss();
                    }else{
                        Toast.makeText(requireContext().getApplicationContext(), "You didn't pass the test lol", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }

                }catch(NumberFormatException e){
                    Toast.makeText(requireContext().getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });

        return v;
    }

    public interface OnClickCalculationListener {
        void OnCalculationClicked(boolean isCalculationCorrect);
    }

    class Tuple<X,Y> {
        public final X key;
        public final Y value;
        public Tuple(X key , Y value){
            this.key = key;
            this.value = value;
        }
    }

}


