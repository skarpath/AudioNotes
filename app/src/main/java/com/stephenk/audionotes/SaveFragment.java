package com.stephenk.audionotes;


import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SaveFragment extends DialogFragment implements View.OnClickListener {

    Button mBtn_Save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this dialog fragment
        View view = inflater.inflate(R.layout.fragment_save_file, container, false);
        setCancelable(false);


        // Instantiate objects
        mBtn_Save = (Button) view.findViewById(R.id.btn_Save);

        // Click listeners
        mBtn_Save.setOnClickListener(this);

        return view;
    }

    public void onClick(View view) {
            dismiss();
        }
}