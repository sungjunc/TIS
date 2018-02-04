package uz.inha.tis.menu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import uz.inha.tis.R;
import uz.inha.tis.menu.listener.OnMenuItemClickListener;
import uz.inha.tis.menu.model.Menu;
import uz.inha.tis.menu.viewHolder.MenuViewHolder;



public class MenuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Menu> mList;
    OnMenuItemClickListener mListener;


    public MenuListAdapter(OnMenuItemClickListener listener) {
        mList = new ArrayList<>();
        mListener = listener;
    }

    public void addItem(Menu menu) {
        mList.add(menu);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Menu menu = mList.get(position);
        MenuViewHolder menuViewHolder = (MenuViewHolder) holder;
        menuViewHolder.bind(menu, mListener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
