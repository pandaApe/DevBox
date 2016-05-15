package com.pa.devbox.ui.aty;

import android.os.Bundle;

import com.pa.devbox.R;
import com.pa.devbox.domain.entity.Type;
import com.pa.devbox.ui.fragment.LibListFragment;

public class SpecificTypeActivity extends BaseActivity {

    public final static String SELECTEDITEM = "SELECTEDITEM";

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        setContentView(R.layout.activity_specific_type);
        setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, v ->
                SpecificTypeActivity.this.finish()
        );

        Type type = (Type) (getIntent().getSerializableExtra(SELECTEDITEM));
        this.setToolbarTitle(type.getEnDescription());

        LibListFragment listFragment = new LibListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.type_fragment, listFragment).commit();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SELECTEDITEM, type);

        listFragment.setArguments(bundle);
    }
}
