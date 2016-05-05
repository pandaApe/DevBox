package com.pa.devbox.ui.aty;

import android.os.Bundle;
import android.view.View;

import com.pa.devbox.R;
import com.pa.devbox.domain.entity.Type;
import com.pa.devbox.ui.fragment.LibListFragment;

public class SpecificTypeActivity extends BaseActivity {

    public final static String SELECTEDITEM = "SELECTEDITEM";

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

        Type type = (Type) (getIntent().getSerializableExtra(SELECTEDITEM));
        this.setToolbarTitle(type.getEnDescription());

        LibListFragment listFrament = LibListFragment.newInstance(0);
        getSupportFragmentManager().beginTransaction().replace(R.id.type_fragment, listFrament).commit();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTEDITEM, type);

        listFrament.setArguments(bundle);
    }
}
