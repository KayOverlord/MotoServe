package com.ttz.kmystro.motoserve;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Collection;
import java.util.HashMap;


public class ProfileActivity extends Activity {

    private ProgressBar ProgBar;
    private static final int RESULT_LOAD_IMAGE = 11;
    private ImageButton picButton;
    private Button subButton;
    private EditText username;
    private Uri imgurl;
    private FirebaseAuth firebaseAuth;
    private StorageReference storageRef;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();


        ProgBar = findViewById(R.id.progressProfile);

        picButton = findViewById(R.id.propickBtn);
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        username = findViewById(R.id.usernametext);



        subButton = findViewById(R.id.submitbutton);

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             final String getusername = username.getText().toString();
                if(!TextUtils.isEmpty(getusername) && imgurl!=null ){

                    ProgBar.setVisibility(View.VISIBLE);

                    String userID = firebaseAuth.getCurrentUser().getUid();
                    final StorageReference imagePath = storageRef.child("profileImage").child(userID+".jpg");
                    imagePath.putFile(imgurl).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            ProgBar.setVisibility(View.INVISIBLE);
                            Uri imgDownload = Uri.parse(imagePath.getDownloadUrl().toString());
                            String userid = firebaseAuth.getCurrentUser().getUid();
                            String userEm = firebaseAuth.getCurrentUser().getEmail();

                            if(task.isSuccessful()){

                                HashMap<String,Object>userdata = new HashMap<>();
                                userdata.put("UserName",getusername);
                                userdata.put("ProfileImage",imgDownload);
                                userdata.put("Email",userEm);

                                firebaseFirestore.collection("Users").document(userid).set(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("ERROR",e.getMessage());
                                    }
                                });



                            }else {
                                String error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),"ERROR:"+error,Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK && data != null){
            imgurl = data.getData();
            Glide.with(this).load(imgurl).into(picButton);
        }
    }
}
