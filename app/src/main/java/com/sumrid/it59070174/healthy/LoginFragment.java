package com.sumrid.it59070174.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView _appLogo = getActivity().findViewById(R.id.logo);
        Animation fade = new AlphaAnimation(0,1);
        fade.setDuration(2000);
        _appLogo.setAnimation(fade);

        initLoginBtn();
        initRegisterBtn();
        checkCurrentUser(firebaseAuth);
    }

    void initLoginBtn() {
        Button loginBtn = getView().findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText _userId = getView().findViewById(R.id.login_user_id_input);
                EditText _password = getView().findViewById(R.id.login_password_input);
                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();

                if (_userIdStr.isEmpty() || _passwordStr.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill E-mail or password.", Toast.LENGTH_SHORT).show();
                } else {
                    setLoading(true);
                    signIn(_userIdStr, _passwordStr);
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

    void signIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                setLoading(false);
                Toast.makeText(getActivity(), "Sign in fail : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                checkVerified(firebaseAuth);
            }
        });
    }

    void goToMenu() {
        Log.d("USER", "GOTO MENU");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new MenuFragment())
                .commit();
    }

    void checkVerified(FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser().isEmailVerified()) {
            goToMenu();
        } else {
            firebaseAuth.signOut();
            setLoading(false);
            Toast.makeText(getActivity(), "Please verify your email", Toast.LENGTH_SHORT).show();
        }
    }

    void checkCurrentUser(FirebaseAuth firebaseAuth){
        if(firebaseAuth.getCurrentUser() != null) {
            goToMenu();
        }
    }

    private void setLoading(boolean setToLoading){
        ProgressBar _loading = getView().findViewById(R.id.login_loading);
        Button loginButton = getView().findViewById(R.id.login_btn);
        if(setToLoading){
            _loading.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
        } else {
            _loading.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
        }
    }
}
