package com.pa.devbox.ui.viewModel;

import android.content.Intent;
import android.view.View;

import com.pa.devbox.domain.entity.Library;
import com.pa.devbox.ui.adapter.BaseAdapter;
import com.pa.devbox.ui.adapter.LibListAdapter;
import com.pa.devbox.ui.aty.LibDetailActivity;
import com.pa.devbox.ui.aty.MainActivity;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 10:01.
 * Email: whailong2010@gmail.com
 */
public class LibListFragModel extends ListBaseModel<Library> implements BaseAdapter.OnItemClickListener {

    private MainActivity context;

    public LibListFragModel(MainActivity context) {
        super(context);
        swipeRefreshLayoutStatus = true;
        this.context = context;
        adapter = new LibListAdapter(context, data);
        adapter.setItemClickListener(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this.context, LibDetailActivity.class);
        intent.putExtra(LibDetailAtyModel.SELECTEDITEM, data.get(position));
        this.context.startActivity(intent);
    }

}
