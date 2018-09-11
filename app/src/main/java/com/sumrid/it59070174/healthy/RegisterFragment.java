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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sumrid.it59070174.healthy.weight.WeightFormFragment;


public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_register, container, false);

    }


    void ver_btn(){
        Button verBtn = getView().findViewById(R.id.chkver_btn);
        verBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FirebaseAuth curUser = FirebaseAuth.getInstance();
//                curUser.signOut();
                if(FirebaseAuth.getInstance().getCurrentUser()!=null) Log.d("LOGIN", "YES");
                else Log.d("LOGIN", "NO");
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ver_btn();

        Button registerBtn = getView().findViewById(R.id.register_register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _userID = getView().findViewById(R.id.register_userID);
                EditText _RePassword = getView().findViewById(R.id.register_re_pass);
                EditText _password = getView().findViewById(R.id.register_password);

                String userIdStr = _userID.getText().toString();
                String passwordStr = _password.getText().toString();
                String re_passwordStr = _RePassword.getText().toString();


                if (userIdStr.isEmpty() || re_passwordStr.isEmpty() || passwordStr.isEmpty()){
                    Toast.makeText(getActivity(), "กรุณาระบุข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                }
                else if (passwordStr.equals(re_passwordStr) && passwordStr.length() > 6) {
                    createAccount(userIdStr,passwordStr);
                } else{
                    Toast.makeText(getActivity(), "Password Invalid", Toast.LENGTH_SHORT).show();
                }

//                if (userIdStr.isEmpty() || re_passwordStr.isEmpty() || passwordStr.isEmpty()) {
//                    Toast.makeText(getActivity(), "กรุณาระบุข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
//                } else if (userIdStr.equals("admin")) {
//                    Toast.makeText(getActivity(), "ชื่อผู้ใช้ไม่สามารถใช้ได้", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.d("USER", "GOTO BMI");
//                    getActivity().getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.main_view, new BmiFragment())
//                            .addToBackStack(null)
//                            .commit();
//                }
            }
        });

    }


    void createAccount(String user, String password) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(user, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d("Register", ""+authResult);
                FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
                sendVerifiedEmail(curUser);
                ver_btn();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Register", "Fail : "+e);
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void sendVerifiedEmail(FirebaseUser _user) {
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener < Void > () {
            @Override public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(), "Send Verified Email", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
