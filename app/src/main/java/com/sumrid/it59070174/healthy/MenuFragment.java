package com.sumrid.it59070174.healthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sumrid.it59070174.healthy.weight.Weight;
import com.sumrid.it59070174.healthy.weight.WeightFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment{

    ArrayList<String> menu = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  map view with fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        menu.clear();
        menu.add("BMI");
        menu.add("Weight");
        menu.add("Setup");

        ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_list_item_1, menu
        );

        // get ListView from fragment_menu.xml
        ListView menuList = getView().findViewById(R.id.menu_list);
        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MENU", "Click on menu = " + menu.get(position));

                Fragment fragment[] = {new BmiFragment(), new WeightFragment()};
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, fragment[position])
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
