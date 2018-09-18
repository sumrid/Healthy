package com.sumrid.it59070174.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sumrid.it59070174.healthy.R;

import java.util.ArrayList;

import javax.xml.datatype.Duration;

public class WeightFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ArrayList<Weight> weights = new ArrayList<>();
    WeightAdapter weightAdapter;
    ListView weightList;
    WeightFormFragment weightFormFragment = new WeightFormFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getData();
        initAddBtn();
        weightList = getView().findViewById(R.id.weight_list);
        weightAdapter = new WeightAdapter(getActivity(),
                R.layout.fragment_weight_item, weights);
        weightList.setAdapter(weightAdapter);
        weights.clear();
    }

    void initAddBtn(){
        Button addWeight = getView().findViewById(R.id.weight_add_weight);
        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("USER", "go to add weight");
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, weightFormFragment)
//                        .replace(R.id.main_view, new WeightFormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    void getData(){
        db.collection("myfitness")
                .document(auth.getUid())
                .collection("weight").orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Weight obj = document.toObject(Weight.class);
                            weights.add(obj);
                        }
                        weightAdapter.notifyDataSetChanged();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("weight", weights);
                        weightFormFragment.setArguments(bundle);
                    }
                });
    }
}
