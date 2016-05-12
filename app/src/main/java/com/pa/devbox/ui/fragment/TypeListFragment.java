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
import com.pa.devbox.ui.viewModel.TypeListFragModel;

import javax.inject.Inject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 22/1/16 00:03.
 * Email: whailong2010@gmail.com
 */
public class TypeListFragment extends Fragment {

    @Inject
    TypeListFragModel typeTypeListFragModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DaggerFragmentComponent
                .builder()
                .fragmentModule(new FragmentModule((MainActivity) getActivity()))
                .build()
                .inject(this);

        FragmentLibBinding fragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lib, container, false);
        fragBinding.setViewModel(typeTypeListFragModel);
        return fragBinding.getRoot();
    }

}
