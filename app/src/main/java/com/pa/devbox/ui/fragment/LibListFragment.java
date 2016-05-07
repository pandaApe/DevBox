package com.pa.devbox.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pa.devbox.R;
import com.pa.devbox.databinding.FragmentLibBinding;
import com.pa.devbox.di.component.DaggerLibFragComponent;
import com.pa.devbox.di.module.LibFragModule;
import com.pa.devbox.ui.aty.MainActivity;
import com.pa.devbox.ui.viewModel.LibListFragModel;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 14/1/16 10:01.
 * Email: whailong2010@gmail.com
 */
public class LibListFragment extends Fragment {

    private HashMap<String, String> parmMap;

    @Inject
    LibListFragModel libListFragModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DaggerLibFragComponent.builder().libFragModule(new LibFragModule((MainActivity) getActivity())).build().inject(this);

        FragmentLibBinding libBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lib, container, false);
        libBinding.setViewModel(libListFragModel);
        // TODO: 7/5/16
//
//        View view = inflater.inflate(R.layout.fragment_lib, container, false);
////        ButterKnife.bind(this, view);
//        Bundle bundle = this.getArguments();
//        parmMap = new HashMap<>();
//        if (bundle != null) {
//            Type currentType = (Type) bundle.getSerializable(SpecificTypeActivity.SELECTEDITEM);
//
//            if (currentType != null)
//                parmMap.put("objId", currentType.getObjectId());
//        }

        return libBinding.getRoot();
    }
//
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initeView();
//        requestServerData();
//    }

    private void initeView() {

//        adapter.setItemOnClickListenner(new LibListAdapter.AdapterItemOnClickListenner() {
//
//            @Override
//            public void onClick(View v, int index) {
//                Intent intent = new Intent(getActivity(), LibDetailActivity.class);
//                intent.putExtra(LibDetailActivity.SELECTEDITEM, codeLibs.get(index));
//                getActivity().startActivity(intent);
//            }
//        });
    }

    private void requestServerData() {
//        new WebActionImpl(getActivity()).getLibList(parmMap, 1, new HttpCallback<List<Library>>() {
//
//            @Override
//            public void onSucess(List<Library> list) {
//
//                KJDB kjdb = KJDB.create(getActivity());
//                for (Library lib : kjdb.findAll(Library.class)) {
//                    kjdb.delete(lib);
//                }
//
//                kjdb.save(list);
//
//                LibListFragment.this.codeLibs.clear();
//                LibListFragment.this.codeLibs.addAll(list);
//                LibListFragment.this.adapter.notifyDataSetChanged();
//
//                LibListFragment.this.progressBarContainer.hide();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(final AppException e) {
//
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
//                LibListFragment.this.progressBarContainer.hide();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
    }


    public void onRefresh() {
        requestServerData();
    }
}
