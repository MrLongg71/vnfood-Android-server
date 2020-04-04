package vn.mrlongg71.vnfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.module.explore.view.ExploreFragment;
import vn.mrlongg71.vnfood.src.module.explore.view.ProductDetailsFragment;
import vn.mrlongg71.vnfood.src.module.favourite.view.FavouriteFragment;
import vn.mrlongg71.vnfood.src.module.myorder.view.MyOrderFragment;
import vn.mrlongg71.vnfood.src.module.profile.view.ProfileFragment;

public class NavigationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new ProductDetailsFragment());
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    loadFragment(new ExploreFragment());

                    return true;
                case R.id.navigation_my_order:
                    loadFragment(new MyOrderFragment());

                    return true;
                case R.id.navigation_favourite:
                    loadFragment(new FavouriteFragment());

                    return true;
                case R.id.navigation_profile:
                    loadFragment(new ProfileFragment());

                    return true;
            }
            return false;
        }

    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
