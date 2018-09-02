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

import com.sumrid.it59070174.healthy.R;

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
                WeightFragment weightFragment = new WeightFragment();

                EditText _date = getView().findViewById(R.id.weightForm_date);
                EditText _weight = getView().findViewById(R.id.weightForm_weight);

                if(_date.getText().toString().isEmpty() || _weight.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Fill date or weight", Toast.LENGTH_SHORT).show();
                } else {
                    String _dateStr = _date.getText().toString();
                    float _weightFloat = Float.parseFloat(_weight.getText().toString());
                    weightFragment.addWeight(new Weight(_dateStr, _weightFloat, ""));
                    Toast.makeText(getActivity(), "Your weight : " + _weightFloat, Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, weightFragment)
                            .addToBackStack(null)
                            .commit();
                }

            }
        });
    }
}
