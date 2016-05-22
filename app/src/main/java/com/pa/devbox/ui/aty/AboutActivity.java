package com.pa.devbox.ui.aty;

import android.os.Bundle;

import com.pa.devbox.R;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 21/5/16 23:32.
 * Email: whailong2010@gmail.com
 */
public class AboutActivity extends BaseActivity {

    @Override
    void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        this.setContentView(R.layout.activity_about);

        setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, v -> {
            finish();

        });
    }
}
