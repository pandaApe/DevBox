package com.pa.devbox.ui.viewModel;

import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.ui.adapter.LibListAdapter;
import com.pa.devbox.ui.aty.MainActivity;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 10:01.
 * Email: whailong2010@gmail.com
 */
public class LibListFragModel extends ListBaseModel<Library> {

    public LibListFragModel(MainActivity context) {
        super(context);
        swipeRefreshLayoutStatus = true;
        adapter = new LibListAdapter(context, data);
    }

    @Override
    public void onRefresh() {

    }
}
