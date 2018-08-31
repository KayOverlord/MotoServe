package com.ttz.kmystro.motoserve;

import android.os.Bundle;
import android.app.Activity;

public class ServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
