package com.pa.devbox.ui.aty;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pa.devbox.R;
import com.pa.devbox.databinding.ActivityLibdetailBinding;
import com.pa.devbox.di.component.DaggerLibDetailAtyComponent;
import com.pa.devbox.di.module.LibDetailAtyModule;
import com.pa.devbox.ui.viewModel.LibDetailAtyModel;

import javax.inject.Inject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 23/1/16 12:39.
 * Email: whailong2010@gmail.com
 */
public class LibDetailActivity extends BaseActivity {

    @Inject
    LibDetailAtyModel libDetailAtyModel;

    private PermissionRequestCallback permissionRequestCallback;
    private OptionsItemSelectedCallback optionsItemSelectedCallback;

    @Override
    void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        DaggerLibDetailAtyComponent
                .builder()
                .libDetailAtyModule(new LibDetailAtyModule(this))
                .build()
                .inject(this);

        ActivityLibdetailBinding libDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_libdetail);
        libDetailAtyModel.parseArguments(getIntent());
        libDetailBinding.setViewModel(libDetailAtyModel);

        setToolbarDisplayHomeAsUpEnabledAndClickListenner(true, (v) -> {
            LibDetailActivity.this.finish();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        permissionRequestCallback.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public interface PermissionRequestCallback {
        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    }

    public interface OptionsItemSelectedCallback {
        void onOptionsItemSelected(MenuItem item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_aty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        optionsItemSelectedCallback.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    public void setPermissionRequestCallback(PermissionRequestCallback permissionRequestCallback) {
        this.permissionRequestCallback = permissionRequestCallback;
    }

    public void setOptionsItemSelectedCallback(OptionsItemSelectedCallback optionsItemSelectedCallback) {
        this.optionsItemSelectedCallback = optionsItemSelectedCallback;
    }

}