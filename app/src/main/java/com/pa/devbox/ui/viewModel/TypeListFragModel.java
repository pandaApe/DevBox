package com.pa.devbox.ui.viewModel;

import com.pa.devbox.domain.entity.Type;
import com.pa.devbox.ui.adapter.TypeListAdapter;
import com.pa.devbox.ui.aty.MainActivity;

/**
 * Description:
 * <p>
 * Author: PandaApe.
 * CreatedAt: 7/5/16 00:01.
 * Email: whailong2010@gmail.com
 */
public class TypeListFragModel extends ListBaseModel<Type> {

    public TypeListFragModel(MainActivity context) {
        super(context);
        swipeRefreshLayoutStatus = false;
        adapter = new TypeListAdapter(context, data);

        for (int index = 0; index < 10; index++) {

            Type type = new Type();
            type.setEnDescription("index" + index);
            data.add(type);
        }

        adapter.notifyDataSetChanged();


    }

}
