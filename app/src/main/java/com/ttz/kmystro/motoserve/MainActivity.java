package com.ttz.kmystro.motoserve;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ttz.kmystro.motoserve.Fragments.BookingsFragment;
import com.ttz.kmystro.motoserve.Fragments.HomeFragment;
import com.ttz.kmystro.motoserve.Fragments.Profileragment;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   switchToHomeFragment();
                    return true;
                case R.id.navigation_dashboard:
                    switchToProfileFragment();
                    return true;
                case R.id.navigation_notifications:
                    switchToBookingsFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_home);// change to whichever id should be default
        }
    }
    public void switchToHomeFragment() {

        HomeFragment mainF = new HomeFragment();
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container,mainF,"fragmant");
         // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }
    public  void switchToProfileFragment(){
        Profileragment profileragment = new Profileragment();
        FragmentTransaction Profileft = getSupportFragmentManager().beginTransaction();
        Profileft.replace(R.id.fragment_container,profileragment,"fragmant");
        Profileft.commit();
    }
    public  void switchToBookingsFragment(){
        BookingsFragment bookingsFragment = new BookingsFragment();
        FragmentTransaction Bookingft = getSupportFragmentManager().beginTransaction();
        Bookingft.replace(R.id.fragment_container,bookingsFragment,"fragmant");
        Bookingft.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
    }
}
