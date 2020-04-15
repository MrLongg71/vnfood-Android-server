package vn.mrlongg71.vnfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import vn.mrlongg71.vnfood.src.model.OrderProvisional;
import vn.mrlongg71.vnfood.src.module.cate.view.CateFragment;
import vn.mrlongg71.vnfood.src.module.explore.view.ExploreFragment;
import vn.mrlongg71.vnfood.src.module.favourite.view.FavouriteFragment;
import vn.mrlongg71.vnfood.src.module.myorder.view.MyOrderActivity;
import vn.mrlongg71.vnfood.src.module.profile.view.ProfileFragment;
import vn.mrlongg71.vnfood.src.utils.ViewPagerNavigationAdapter;

public class NavigationActivity extends AppCompatActivity {

    public static ArrayList<OrderProvisional> orderDetails;
    public static int numberBadge;
    private ViewPager viewPager;
    public static BottomNavigationView navigation;
    private MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager = findViewById(R.id.viewNavigation);
        viewPager.setOffscreenPageLimit(1);

        //order Details
        if (orderDetails == null) {
            orderDetails = new ArrayList<>();
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("LONgKUTE", "onPageSelected: " + position);
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }

                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        setupViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerNavigationAdapter adapter = new ViewPagerNavigationAdapter(getSupportFragmentManager());
        ExploreFragment exploreFragment = new ExploreFragment();
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        CateFragment cateFragment = new CateFragment();
        ProfileFragment profileFragment = new ProfileFragment();
        adapter.addFragment(exploreFragment);
        adapter.addFragment(cateFragment);
        adapter.addFragment(favouriteFragment);
        adapter.addFragment(profileFragment);
        viewPager.setAdapter(adapter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {

        switch (item.getItemId()) {
            case R.id.navigation_explore:
                viewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_my_order:
                viewPager.setCurrentItem(1);

                return true;
            case R.id.navigation_favourite:
                viewPager.setCurrentItem(2);

                return true;
            case R.id.navigation_profile:
                viewPager.setCurrentItem(3);

                return true;
        }
        return false;
    };


    public void onClickBadge(View view) {
        startActivity(new Intent(getApplicationContext(), MyOrderActivity.class));
    }



}
