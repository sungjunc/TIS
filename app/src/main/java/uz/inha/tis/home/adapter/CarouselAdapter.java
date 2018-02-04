package uz.inha.tis.home.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uz.inha.tis.R;
import uz.inha.tis.home.model.Article;



public class CarouselAdapter extends PagerAdapter {

    private List<Article> articleList;
    private Context context;

    public CarouselAdapter( Context context) {
        articleList = new ArrayList<>();
        this.context = context;
    }

    public void addItem(Article item){
        articleList.add(item);
        notifyDataSetChanged();
    }





    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Article article = articleList.get(position);
        View view = inflater.inflate(R.layout.view_tab_item, container,false);
        ImageView imageView = view.findViewById(R.id.view_tab_item_image);
        TextView textView = view.findViewById(R.id.view_tab_item_title);
        imageView.setImageResource(article.getImageId());
        textView.setText(article.getTitle());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    public View getTabView(int position) {


        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout_item_view, null);
        return view;
    }


    public void tabSelected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if (view != null) {
            View tabLayoutView = view.findViewById(R.id.tab_layout_item_view);
            tabLayoutView.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_background_drawable));
        }
    }

    public void tabUnselected(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if (view != null) {
            View tabLayoutView = view.findViewById(R.id.tab_layout_item_view);
            tabLayoutView.setBackground(ContextCompat.getDrawable(context, R.drawable.tab_background_transparent_drawable));
        }
    }

}
