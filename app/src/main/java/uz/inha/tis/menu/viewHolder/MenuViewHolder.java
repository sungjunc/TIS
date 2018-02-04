package uz.inha.tis.menu.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import uz.inha.tis.R;
import uz.inha.tis.menu.listener.OnMenuItemClickListener;
import uz.inha.tis.menu.model.Menu;



public class MenuViewHolder extends RecyclerView.ViewHolder {

    TextView mTextView;

    public MenuViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.list_item_menu_text_view);
    }

    public void bind(final Menu menu, final OnMenuItemClickListener listener) {
        mTextView.setText(menu.getName());
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickedMenu(menu);
            }
        });

    }
}
