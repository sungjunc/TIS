package uz.inha.tis.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uz.inha.tis.R;
import uz.inha.tis.helper.Utils;
import uz.inha.tis.menu.adapter.MenuListAdapter;
import uz.inha.tis.menu.listener.OnMenuItemClickListener;
import uz.inha.tis.menu.model.Menu;

public class MenuListActivity extends AppCompatActivity implements OnMenuItemClickListener {


    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    MenuListAdapter mAdapter;
    int menuType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        parseIntent(getIntent());
        initUI();
        initRecyclerView();
        parseMenuType();
    }

    private void parseIntent(Intent intent) {
        Bundle args = intent.getExtras();
        menuType = args.getInt("menu-type", -1);
    }

    private void parseMenuType() {
        String title = null;
        List<String> menuNameList = new ArrayList<>();
        List<String> menuUrlList = new ArrayList<>();
        int[] menuHasSubMenu = new int[]{};
        int[] menuID = new int[]{};
        switch (menuType) {
            case Utils.ABOUT_TIS:
                title = getResources().getString(R.string.about_tis);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.about_tis));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.about_tis_url));
                menuHasSubMenu = getResources().getIntArray(R.array.about_tis_has_menu);
                menuID = getResources().getIntArray(R.array.about_tis_id);
                break;
            case Utils.ADMISSION:
                title = getResources().getString(R.string.admission);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.admission));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.admission_url));
                menuHasSubMenu = getResources().getIntArray(R.array.admission_has_menu);
                menuID = getResources().getIntArray(R.array.admission_id);
                break;
            case Utils.ELEMENTARY:
                title = getResources().getString(R.string.elementary);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.elementary));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.elementary_url));
                menuHasSubMenu = getResources().getIntArray(R.array.elementary_has_menu);
                menuID = getResources().getIntArray(R.array.elementary_id);
                break;
            case Utils.SECONDARY:
                title = getResources().getString(R.string.secondary);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.secondary));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.secondary_url));
                menuHasSubMenu = getResources().getIntArray(R.array.secondary_has_menu);
                menuID = getResources().getIntArray(R.array.secondary_id);
                break;
            case Utils.STUDENT_ACTIVIES:
                title = getResources().getString(R.string.student_activities);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.student_activity));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.student_activity_url));
                menuHasSubMenu = getResources().getIntArray(R.array.student_activity_has_menu);
                menuID = getResources().getIntArray(R.array.student_activity_id);
                break;
            case Utils.SUPPORT_SERVICE:
                title = getResources().getString(R.string.support_service);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.support_service));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.support_service_url));
                menuHasSubMenu = getResources().getIntArray(R.array.support_service_has_menu);
                menuID = getResources().getIntArray(R.array.support_service_id);
                break;
            case Utils.INFORMATION:
                title = getResources().getString(R.string.information);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.information));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.information_url));
                menuHasSubMenu = getResources().getIntArray(R.array.information_has_menu);
                menuID = getResources().getIntArray(R.array.information_id);
                break;
            case Utils.PRIMARY_YEARS_PROGRAMM:
                title = getResources().getString(R.string.primary_year_programm);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.primary_year_program));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.primary_year_program_url));
                menuHasSubMenu = getResources().getIntArray(R.array.primary_year_program_has_menu);
                menuID = getResources().getIntArray(R.array.primary_year_program_id);
                break;
            case Utils.SINGLE_SUBJECTS:
                title = getResources().getString(R.string.single_subjects);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.single_subjects));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.single_subjects_url));
                menuHasSubMenu = getResources().getIntArray(R.array.single_subjects_has_menu);
                menuID = getResources().getIntArray(R.array.single_subjects_id);
                break;
            case Utils.PARENT_RESOURCES:
                title = getResources().getString(R.string.parent_resources);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.parent_resources));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.parent_resources_url));
                menuHasSubMenu = getResources().getIntArray(R.array.parent_resources_has_menu);
                menuID = getResources().getIntArray(R.array.parent_resources_id);
                break;
            case Utils.DEPARMENTS:
                title = getResources().getString(R.string.departments);
                menuNameList = Arrays.asList(getResources().getStringArray(R.array.departments));
                menuUrlList = Arrays.asList(getResources().getStringArray(R.array.departments_url));
                menuHasSubMenu = getResources().getIntArray(R.array.departments_has_menu);
                menuID = getResources().getIntArray(R.array.departments_id);
                break;
        }

        setTitle(title);
        for (int i = 0; i < menuNameList.size(); i++) {
            Menu menu = new Menu();
            menu.setName(menuNameList.get(i));
            menu.setUrl(menuUrlList.get(i));
            menu.setHasSubMenu(menuHasSubMenu[i]);
            menu.setId(menuID[i]);
            mAdapter.addItem(menu);

        }
    }

    private void initUI() {
        mToolbar = (Toolbar) findViewById(R.id.activity_menu_list_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_menu_list_recycler_view);
    }

    private void initRecyclerView() {
        mAdapter = new MenuListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClickedMenu(Menu menu) {
        if (menu.getHasSubMenu() == 0) {
            startMenuDetailActivity(menu.getName(), menu.getUrl());
        } else {
            startMenuListActivity(menu.getId());
        }
    }

    private void startMenuListActivity(int id) {
        Intent intent = new Intent(MenuListActivity.this, MenuListActivity.class);
        intent.putExtra("menu-type", id);
        startActivity(intent);
    }

    private void startMenuDetailActivity(String title, String url) {
        Intent intent = new Intent(MenuListActivity.this, MenuDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
