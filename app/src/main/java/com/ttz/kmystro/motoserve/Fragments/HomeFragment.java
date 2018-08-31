package com.ttz.kmystro.motoserve.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.ttz.kmystro.motoserve.PartsListActivity;
import com.ttz.kmystro.motoserve.R;
import com.ttz.kmystro.motoserve.ServiceActivity;



import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor

    }
    private TextView greetingsTxt;
    private CircleImageView propic;
    private Button partsbtn;
    private Button servicebtn;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseStorage firebaseStorage;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseAuth =FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        greetingsTxt = view.findViewById(R.id.hitext);
        partsbtn = view.findViewById(R.id.carpartsBtn);
        servicebtn = view.findViewById(R.id.carserviceBtn);
        propic = view.findViewById(R.id.profilepicTxt);

     FileDownloadTask MYpic = firebaseStorage.getReference("profileImage").getFile(Uri.parse(firebaseAuth.getUid()+".jpg"));
     MYpic.addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
         @Override
         public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
             Glide.with(view.getContext()).load(task).into(propic);
         }
     });

        partsbtn = view.findViewById(R.id.carpartsBtn);
        servicebtn = view.findViewById(R.id.carserviceBtn);

        partsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PartsListActivity.class);
                startActivity(intent);
            }
        });

        servicebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ServiceActivity.class);
                startActivity(intent);
            }
        });




        return view;
    }

}
