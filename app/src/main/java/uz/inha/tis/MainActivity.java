package uz.inha.tis;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import uz.inha.tis.helper.Utils;
import uz.inha.tis.home.adapter.CarouselAdapter;
import uz.inha.tis.home.model.Article;
import uz.inha.tis.menu.MenuDetailActivity;
import uz.inha.tis.menu.MenuListActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private NavigationView mNavigationView;
    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;


    CarouselAdapter mCarouselAdapter;
    LinearLayout mContactBtn, mAboutTisBtn, mAdmissionBtn, mElementaryBtn, mSecondaryBtn, mStudentActivityBtn, mSupportServiceBtn, mInformationBtn, mCalendarBtn, mMorningNewsBtn, mWeeklyNews, mLunchMenu;
    CountDownTimer mTimer;
    TextView pcVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mViewPager = (ViewPager) findViewById(R.id.content_main_view_pager);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        mContactBtn = (LinearLayout) findViewById(R.id.fragment_contact_container);
        mAboutTisBtn = (LinearLayout) findViewById(R.id.fragment_home_about_tis_container);
        mAdmissionBtn = (LinearLayout) findViewById(R.id.fragment_home_admission_container);
        mElementaryBtn = (LinearLayout) findViewById(R.id.fragment_home_elementary_container);
        mSecondaryBtn = (LinearLayout) findViewById(R.id.fragment_home_secondary_container);
        mStudentActivityBtn = (LinearLayout) findViewById(R.id.fragment_home_student_activity_container);
        mSupportServiceBtn = (LinearLayout) findViewById(R.id.fragment_home_support_service_container);
        mInformationBtn = (LinearLayout) findViewById(R.id.fragment_home_information_container);
        mCalendarBtn = (LinearLayout) findViewById(R.id.fragment_calendar_container);
        mMorningNewsBtn = (LinearLayout) findViewById(R.id.fragment_morning_news_container);
        mWeeklyNews = (LinearLayout) findViewById(R.id.fragment_weekly_news_container);
        mLunchMenu = (LinearLayout) findViewById(R.id.fragment_launch_menu_container);
        mTabLayout = (TabLayout) findViewById(R.id.content_main_tab_layout);
        pcVersion = (TextView) findViewById(R.id.content_main_pc_version_btn);
        mNavigationView.setItemIconTintList(null);
        initCarousel();
        pcVersion.setOnClickListener(this);
        mContactBtn.setOnClickListener(this);
        mAboutTisBtn.setOnClickListener(this);
        mAdmissionBtn.setOnClickListener(this);
        mElementaryBtn.setOnClickListener(this);
        mSecondaryBtn.setOnClickListener(this);
        mStudentActivityBtn.setOnClickListener(this);
        mSupportServiceBtn.setOnClickListener(this);
        mInformationBtn.setOnClickListener(this);
        mCalendarBtn.setOnClickListener(this);
        mMorningNewsBtn.setOnClickListener(this);
        mWeeklyNews.setOnClickListener(this);
        mLunchMenu.setOnClickListener(this);
        mTabLayout.setOnClickListener(this);


    }

    private void initCarousel() {

        mCarouselAdapter = new CarouselAdapter(this);

        mCarouselAdapter.addItem(new Article("", R.drawable.home));
        mCarouselAdapter.addItem(new Article("", R.drawable.home_image_2));
        mCarouselAdapter.addItem(new Article("", R.drawable.home_image_3));
        mViewPager.setAdapter(mCarouselAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCarouselAdapter.tabSelected(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mCarouselAdapter.tabUnselected(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mCarouselAdapter.getTabView(i));
            if (i == mViewPager.getCurrentItem()) {
                mCarouselAdapter.tabSelected(tab);
            }
        }

        mTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                int count = mViewPager.getCurrentItem();
                if (count == mTabLayout.getTabCount() - 1)
                    count = 0;
                else
                    count++;
                mViewPager.setCurrentItem(count);
                mTimer.start();
            }
        };
        mTimer.start();

    }

    private void startActivity(int id) {
        Intent intent = new Intent(MainActivity.this, MenuListActivity.class);
        switch (id) {
            case Utils.CONTACT:
                startDetailActivity(getString(R.string.contact), Utils.CONTACT_URL);
                return;
            case Utils.ABOUT_TIS:
                intent.putExtra("menu-type", Utils.ABOUT_TIS);
                break;
            case Utils.ADMISSION:
                intent.putExtra("menu-type", Utils.ADMISSION);
                break;
            case Utils.ELEMENTARY:
                intent.putExtra("menu-type", Utils.ELEMENTARY);
                break;
            case Utils.SECONDARY:
                intent.putExtra("menu-type", Utils.SECONDARY);
                break;
            case Utils.STUDENT_ACTIVIES:
                intent.putExtra("menu-type", Utils.STUDENT_ACTIVIES);
                break;
            case Utils.SUPPORT_SERVICE:
                intent.putExtra("menu-type", Utils.SUPPORT_SERVICE);
                break;
            case Utils.INFORMATION:
                intent.putExtra("menu-type", Utils.INFORMATION);
                break;
            case Utils.CALENDAR:
                startDetailActivity(getString(R.string.calendar), Utils.CALENDAR_URL);
                return;
            case Utils.MORNING_NEWS:
                startDetailActivity(getString(R.string.morning_news), Utils.MORNING_NEWS_URL);
                return;
            case Utils.Weekly_NEWS:
                startPDFActivity(getString(R.string.weekly_news), Utils.Weekly_NEWS_URL);
                return;
            case Utils.LUNCH_NEWS:
                startPDFActivity(getString(R.string.lunch_menu), Utils.LUNCH_NEWS_URL);
                return;
        }
        startActivity(intent);
    }

    private void startPDFActivity(String title, String url) {
        Intent intent = new Intent(MainActivity.this, PDFViewerActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void startDetailActivity(String title, String url) {
        Intent intent = new Intent(MainActivity.this, MenuDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_menu).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(Utils.CONTACT);
        } else if (id == R.id.nav_about_tis) {
            startActivity(Utils.ABOUT_TIS);
        } else if (id == R.id.nav_admission) {
            startActivity(Utils.ADMISSION);
        } else if (id == R.id.nav_elementary) {
            startActivity(Utils.ELEMENTARY);
        } else if (id == R.id.nav_secondary) {
            startActivity(Utils.SECONDARY);
        } else if (id == R.id.nav_student_activity) {
            startActivity(Utils.STUDENT_ACTIVIES);
        } else if (id == R.id.nav_support_service) {
            startActivity(Utils.SUPPORT_SERVICE);
        } else if (id == R.id.nav_information) {
            startActivity(Utils.INFORMATION);
        } else if (id == R.id.nav_calendar) {
            startActivity(Utils.CALENDAR);
        } else if (id == R.id.nav_morning_news) {
            startActivity(Utils.MORNING_NEWS);
        } else if (id == R.id.nav_weekly_news) {
            startActivity(Utils.Weekly_NEWS);
        } else if (id == R.id.nav_lunch_menu) {
            startActivity(Utils.LUNCH_NEWS);
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_contact_container:
                startActivity(Utils.CONTACT);
                break;
            case R.id.fragment_home_about_tis_container:
                startActivity(Utils.ABOUT_TIS);
                break;
            case R.id.fragment_home_admission_container:
                startActivity(Utils.ADMISSION);
                break;
            case R.id.fragment_home_elementary_container:
                startActivity(Utils.ELEMENTARY);
                break;
            case R.id.fragment_home_secondary_container:
                startActivity(Utils.SECONDARY);
                break;
            case R.id.fragment_home_student_activity_container:
                startActivity(Utils.STUDENT_ACTIVIES);
                break;
            case R.id.fragment_home_support_service_container:
                startActivity(Utils.SUPPORT_SERVICE);
                break;
            case R.id.fragment_home_information_container:
                startActivity(Utils.INFORMATION);
                break;
            case R.id.fragment_calendar_container:
                startActivity(Utils.CALENDAR);
                break;
            case R.id.fragment_morning_news_container:
                startActivity(Utils.MORNING_NEWS);
                break;
            case R.id.fragment_weekly_news_container:
                startActivity(Utils.Weekly_NEWS);
                break;
            case R.id.fragment_launch_menu_container:
                startActivity(Utils.LUNCH_NEWS);
                break;
            case R.id.content_main_pc_version_btn:
                gotToUri();
                break;
        }
    }

    public void gotToUri() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pictureofadad.com/"));
        startActivity(intent);
    }

}
