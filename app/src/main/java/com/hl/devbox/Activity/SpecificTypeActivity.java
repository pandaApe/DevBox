package com.hl.devbox.Activity;

import android.os.Bundle;
import android.view.View;

import com.hl.devbox.R;

public class SpecificTypeActivity extends BaseActivity {

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        setContentView(R.layout.activity_specific_type);
        setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpecificTypeActivity.this.finish();
            }
        });

    }
}
