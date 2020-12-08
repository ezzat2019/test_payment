package com.example.testpayment.mew_bottom_nav;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.testpayment.R;

public class BottomNavActivity extends AppCompatActivity {
    private MeowBottomNavigation bottomNavigation;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        navController= Navigation.findNavController(this,R.id.fragment);

        bottomNavigation=findViewById(R.id.meow2020);
        MeowBottomNavigation.Model model1 = new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_4k_24);
        bottomNavigation.add(model1);
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_3d_rotation_24));
        bottomNavigation.setCount(1, "2");

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Log.d("eeeeeeeeeee", "onClickItem: "+item.getId());
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // your codes.
                Log.d("eeeeeeeeeee", "onShowItem: "+item.getId());
                if (item.getId()==1)
                {
                    navController.navigate(R.id.blank1Fragment);
                    getSupportActionBar().setTitle("frag1");
                }else
                {
                    navController.navigate(R.id.blank2Fragment);
                    getSupportActionBar().setTitle("frag2");

                }
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                // your codes
                Log.d("eeeeeeeeeee", "onReselectItem: "+item.getId());
            }
        });
        bottomNavigation.show(1,false);
    }
}