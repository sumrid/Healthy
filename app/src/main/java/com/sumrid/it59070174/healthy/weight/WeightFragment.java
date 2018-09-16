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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sumrid.it59070174.healthy.R;

import java.util.ArrayList;

import javax.xml.datatype.Duration;

public class WeightFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ArrayList<Weight> weights = new ArrayList<>();
    ListView weightList;

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
                        .replace(R.id.main_view, new WeightFormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    void getData(){
//        db.collection("test")
//                .document("testUser")
//                .collection("weight")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Weight obj = document.toObject(Weight.class);
//                                weights.add(obj);
//                            }
//                            disableProcessBar();
//                            ListView weightList = getView().findViewById(R.id.weight_list);
//                            WeightAdapter weightAdapter = new WeightAdapter(getActivity(),
//                                    R.layout.fragment_weight_item, weights);
//                            weightList.setAdapter(weightAdapter);
//                        } else {
//                            Log.d("Data", "Error getting documents: ", task.getException());
//                        }
//                    }
//                });

        db.collection("myfitness")
                .document(auth.getUid())
                .collection("weight")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Weight obj = document.toObject(Weight.class);
                            weights.add(obj);
                        }

                        WeightAdapter weightAdapter = new WeightAdapter(getActivity(),
                                R.layout.fragment_weight_item, weights);
                        weightList.setAdapter(weightAdapter);
                    }
                });
    }
}
