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
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initLoginBtn();
        initRegisterBtn();
    }

    void initLoginBtn(){
        Button loginBtn = getView().findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _userId = getView().findViewById(R.id.login_user_id_input);
                EditText _password = getView().findViewById(R.id.login_password_input);
                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();

                if(_userIdStr.isEmpty() || _passwordStr.isEmpty()){
                    Toast.makeText(getActivity(),"Please fill userID or password.",Toast.LENGTH_SHORT).show();
                } else if (_userIdStr.equals("admin") && _userIdStr.equals("admin")){
                    Log.d("USER", "GOTO MENU");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new MenuFragment())
                            .addToBackStack(null)
                            .commit();
                } else {
                    Log.d("USER", "invalid user or password");
                    Toast.makeText(getActivity(), "invalid user or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void initRegisterBtn() {
        TextView _registerBtn = getView().findViewById(R.id.login_register_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
