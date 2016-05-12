package com.pa.devbox.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pa.devbox.R;
import com.pa.devbox.databinding.FragmentLibBinding;
import com.pa.devbox.di.component.DaggerFragmentComponent;
import com.pa.devbox.di.module.FragmentModule;
import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.viewModel.LibListFragModel;

import javax.inject.Inject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 14/1/16 10:01.
 * Email: whailong2010@gmail.com
 */
public class LibListFragment extends Fragment {

    @Inject
    LibListFragModel libListFragModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DaggerFragmentComponent
                .builder()
                .fragmentModule(new FragmentModule((MainActivity) getActivity()))
                .build()
                .inject(this);

        FragmentLibBinding libBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lib, container, false);
        libBinding.setViewModel(libListFragModel);

        return libBinding.getRoot();
    }
}
