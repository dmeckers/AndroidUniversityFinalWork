package com.example.classproject3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.classproject3.fragments.AdvancedJsonFragment;
import com.example.classproject3.fragments.FirstHomeworkJsonFragment;
import com.example.classproject3.fragments.HomeFragment;
import com.example.classproject3.fragments.HomeWorkJsonFragment;
import com.example.classproject3.fragments.LuckyChoiceFragment;
import com.example.classproject3.fragments.WallpaperFragment;
import com.example.classproject3.models.Person;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    
    public static String url = "https://sinka.lv/android_end_work.html";
    public static String urlPosts = "https://jsonplaceholder.typicode.com/posts";

//    public static String apiKey = "4GI2ZJDezFFqYuZkhYn4jEiKk96IudKvNQVkYgXhusx029tSIevCJdUw";
//    public static String baseUrl = "https://api.pexels.com/v1/search";
//    private static String clientId = "535449202354-n92u1tdl266bog901fph7vt524iieck0.apps.googleusercontent.com";

    BottomNavigationView bottomNavigationView;


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }

    }

    public void startFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter , R.anim.exit , R.anim.pop_enter , R.anim.pop_exit);
        transaction.replace(R.id.fragment_container , fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void startLuckyChoiceFragment() {
                startFragment(new LuckyChoiceFragment());
    }

    private void startJsonAdvancedFragment() {
                startFragment(new AdvancedJsonFragment());
    }

    private void startJsonHomeworkFragment() {
                startFragment(new FirstHomeworkJsonFragment());
    }

    private void startWallpaperFragment() {
                startFragment(new WallpaperFragment());

    }

    private void startHomeFragment() {
        startFragment(new HomeFragment());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation_view);




        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.isChecked()){
                    return false;
                }
                switch(item.getItemId()){

                    case R.id.nav_home:{
                        startHomeFragment();
                        return true;
                    }

                    case R.id.nav_wallpaper:{
                        startWallpaperFragment();
                        return true;
                    }

                    case R.id.nav_json_homework:
                    {
                        startJsonHomeworkFragment();
                        return true;
                    }
                    case R.id.nav_json_advanced:{
                        startJsonAdvancedFragment();
                        return true;
                    }
                    case R.id.nav_lucky_choise:{
                        startLuckyChoiceFragment();
                        return true;
                    }
                }
            return false;
            }

        });
        startHomeFragment();
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
    }
}