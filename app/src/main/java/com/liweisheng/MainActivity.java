package com.liweisheng;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.liweisheng.EditNoteActivity.EditNoteMainActivityBasedXunFei;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private Toolbar toolbar;
    private FloatingActionButton addNewNoteFab;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    //默认设置科大讯飞为识别引擎
    private Boolean isXunFei=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //调用初始化方法
        initView();
    }
    /**
     * 初始化界面
     */
    private void initView(){
        toolbar = (Toolbar) findViewById(R.id.head_tool_bar);
        setSupportActionBar(toolbar);

        addNewNoteFab = (FloatingActionButton) findViewById(R.id.add_new_note_FAB);
        addNewNoteFab.setOnClickListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            this.finish();
            return true;
        }else if (id==R.id.baiDu){
            isXunFei=false;
        }else if (id==R.id.xunFei){
            isXunFei=true;
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
        if (id==R.id.add_new_note_FAB){
            if (isXunFei){
                startActivity(new Intent(MainActivity.this, EditNoteMainActivityBasedXunFei.class));
            }else if (!isXunFei){
                Toast.makeText(this,"选择了百度",Toast.LENGTH_LONG).show();
            }
        }
    }
}
