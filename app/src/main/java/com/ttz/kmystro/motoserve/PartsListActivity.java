package com.ttz.kmystro.motoserve;

import android.os.Bundle;
import android.app.Activity;

public class PartsListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
