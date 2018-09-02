package com.sumrid.it59070174.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.sumrid.it59070174.healthy.R;

import java.util.ArrayList;

public class WeightFragment extends Fragment {

    ArrayList<Weight> weights = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // add weight item
        weights.add(new Weight("01 Jan 2018", 63, ""));
        weights.add(new Weight("02 Jan 2018", 64, "UP"));
        weights.add(new Weight("03 Jan 2018", 66, "UP"));

        // get ListView
        ListView weightList = getView().findViewById(R.id.weight_list);

        // new adapter
        WeightAdapter weightAdapter = new WeightAdapter(getActivity(),
                R.layout.fragment_weight_item, weights);

        // set adapter to ListView
        weightList.setAdapter(weightAdapter);

        onClickAddBtn();
    }

    void onClickAddBtn(){
        Button addWeight = getView().findViewById(R.id.weight_add_weight);
        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new WeightFormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void addWeight(Weight weight){
        weights.add(weight);
    }

    public float getLastWeight(){
        return weights.size();
    }
}
