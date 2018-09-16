package com.sumrid.it59070174.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sumrid.it59070174.healthy.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeightFormFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form, container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onClickBackBtn();
        onClickSaveBtn();

        EditText _date = getView().findViewById(R.id.weightForm_date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = sdf.format(new Date());
        _date.setText(currentDate);
    }

    void onClickBackBtn(){
        Button backBtn = getView().findViewById(R.id.weightForm_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    void onClickSaveBtn(){
        Button saveBtn = getView().findViewById(R.id.weightForm_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText _date = getView().findViewById(R.id.weightForm_date);
                EditText _weight = getView().findViewById(R.id.weightForm_weight);

                if(_date.getText().toString().isEmpty() || _weight.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Fill date or weight", Toast.LENGTH_SHORT).show();
                } else {
                    String _dateStr = _date.getText().toString();
                    float _weightFloat = Float.parseFloat(_weight.getText().toString());
                    Toast.makeText(getActivity(), "Your weight : " + _weightFloat, Toast.LENGTH_SHORT).show();
                    setObjectToFireBase(new Weight(_dateStr, _weightFloat, ""));
                }

            }
        });
    }

    void setObjectToFireBase(Weight weight){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("myfitness")
                .document(auth.getUid())
                .collection("weight")
                .document(weight.getDate()).set(weight)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        goToWeightFragment();
                    }
                });
    }

    void goToWeightFragment(){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new WeightFragment())
                .addToBackStack(null)
                .commit();
    }
}
