package com.sumrid.it59070174.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button registerBtn = getView().findViewById(R.id.register_register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _userID = getView().findViewById(R.id.register_userID);
                EditText _userName = getView().findViewById(R.id.register_userName);
                EditText _age = getView().findViewById(R.id.register_age);
                EditText _password = getView().findViewById(R.id.register_password);
                String userIdStr = _userID.getText().toString();
                String userNameStr = _userName.getText().toString();
                String ageStr = _age.getText().toString();
                String passwordStr = _password.getText().toString();

                if(userIdStr.isEmpty() || userNameStr.isEmpty() || ageStr.isEmpty() || passwordStr.isEmpty()){
                    Toast.makeText(getActivity(), "กรุณาระบุข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                } else if (userIdStr.equals("admin")){
                    Toast.makeText(getActivity(), "ชื่อผู้ใช้ไม่สามารถใช้ได้", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("USER", "GOTO BMI");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BmiFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}
