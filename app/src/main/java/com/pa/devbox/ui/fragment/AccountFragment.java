package com.pa.devbox.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pa.devbox.R;
import com.pa.devbox.databinding.FragmentAccountBinding;
import com.pa.devbox.di.component.DaggerAccountFragComponent;
import com.pa.devbox.di.module.AccountFragModule;
import com.pa.devbox.ui.aty.CollectionActivity;
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
        DaggerAccountFragComponent.builder().accountFragModule(new AccountFragModule((MainActivity) getActivity())).build().inject(this);

        FragmentAccountBinding accountBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);
        accountBinding.setViewModel(accountFragModel);
        return accountBinding.getRoot();
    }

    //    @OnClick({R.id.cv_collction, R.id.cv_feedback})
    public void viewOnClick(View v) {
        switch (v.getId()) {
            case R.id.cv_collction:
                getActivity().startActivity(new Intent(getContext(), CollectionActivity.class));
                break;

            case R.id.cv_feedback:

                break;
        }
    }

    @Override
    public void onResume() {

//        if (User.getCurrentUser() != null) {
//            this.tvNickName.setText(User.getCurrentUser().getString("nickName"));
//
//        } else {
//            this.tvNickName.setText("点击登录");
//        }
        super.onResume();
    }

}
