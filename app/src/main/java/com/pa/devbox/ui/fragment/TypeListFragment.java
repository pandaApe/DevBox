package com.pa.devbox.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pa.devbox.R;
import com.pa.devbox.databinding.FragmentLibBinding;
import com.pa.devbox.domain.entity.Type;
import com.pa.devbox.ui.viewModel.TypeListFragModel;

import javax.inject.Inject;

/**
 * Description:
 *
 * Author: PandaApe.
 * CreatedAt: 22/1/16 00:03.
 * Email: whailong2010@gmail.com
 */
public class TypeListFragment extends Fragment {

    public static TypeListFragment newInstance(int num) {
        TypeListFragment f = new TypeListFragment();
        return f;
    }

    @Inject
    TypeListFragModel<Type> typeTypeListFragModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentLibBinding fragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lib, container, false);
        fragBinding.setViewModel(typeTypeListFragModel);
        return fragBinding.getRoot();
    }

    private void setupServerData() {

//        new WebActionImpl(getActivity()).getTypeList(new HttpCallback<List<Type>>() {
//            @Override
//            public void onSucess(List<Type> list) {
//
//                codeTypes.clear();
//                codeTypes.addAll(list);
//                adapter.notifyDataSetChanged();
//
//                progressBar.hide();
//            }
//
//            @Override
//            public void onFailure(final AppException e) {
//                if (e.getCode() == AppException.NETWORK_ERROR) {
//
//                    new Timer().schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            Snackbar.make(recyclerView, e.getMessage(), Snackbar.LENGTH_LONG).show();
//                        }
//                    }, 500);
//                }
//
//                progressBar.hide();
//            }
//        });
    }

//        adapter.setItemOnClickListenner(new TypeListAdapter.AdapterItemOnClickListenner() {
//            @Override
//            public void onClick(View v, int index) {
//
//                Intent intent = new Intent(getActivity(), SpecificTypeActivity.class);
//                intent.putExtra(SpecificTypeActivity.SELECTEDITEM, codeTypes.get(index));
//                getActivity().startActivity(intent);
//            }
//        });
//

}
