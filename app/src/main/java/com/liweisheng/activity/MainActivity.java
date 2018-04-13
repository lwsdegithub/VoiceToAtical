package com.liweisheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liweisheng.R;
import com.liweisheng.activity.Dao.LoginActivity;
import com.liweisheng.activity.Note.NoteActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private Toolbar mainToolBar;
    private FloatingActionButton addNewNoteFab;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private LinearLayout navHeadLinLay;
    private TextView tvUserName;
    private TextView tvUserWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    /**
     * 初始化界面
     */
    private void initView(){
        mainToolBar = findViewById(R.id.head_tool_bar);
        setSupportActionBar(mainToolBar);

        addNewNoteFab = findViewById(R.id.fab_add);
        addNewNoteFab.setOnClickListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, mainToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //获取NavigationView中Head
        navHeadLinLay = (LinearLayout) navigationView.getHeaderView(0);
        tvUserName = navHeadLinLay.findViewById(R.id.tv_user_name);
        tvUserWord = navHeadLinLay.findViewById(R.id.tv_user_word);
        tvUserName.setOnClickListener(this);
        tvUserWord.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 实现NavigationView中的item选择方法
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_about) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onClick(View view) {
        int id=view.getId();
        if (id == R.id.fab_add) {
            startActivity(new Intent(MainActivity.this, NoteActivity.class));
        }
        if (id == R.id.tv_user_name) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}
