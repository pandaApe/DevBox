package com.pa.devbox.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pa.devbox.R;
import com.pa.devbox.databinding.FragmentAccountBinding;
import com.pa.devbox.di.component.DaggerFragmentComponent;
import com.pa.devbox.di.module.FragmentModule;
import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.viewModel.AccountFragModel;

import javax.inject.Inject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 15/1/16 11:39.
 * Email: whailong2010@gmail.com
 */
public class AccountFragment extends Fragment {

    @Inject
    AccountFragModel accountFragModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DaggerFragmentComponent
                .builder()
                .fragmentModule(new FragmentModule((MainActivity) getActivity()))
                .build()
                .inject(this);

        FragmentAccountBinding accountBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        accountBinding.setViewModel(accountFragModel);
        return accountBinding.getRoot();
    }
}
