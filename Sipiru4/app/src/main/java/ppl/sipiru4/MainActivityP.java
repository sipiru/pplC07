package ppl.sipiru4;

import ppl.sipiru4.Entity.SessionManager;
import ppl.sipiru4.adapter.NavDrawerListAdapter;
import ppl.sipiru4.controller.PenggunaController;
import ppl.sipiru4.model.NavDrawerItem;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivityP extends FragmentActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle; // nav drawer title
    private CharSequence mTitle; // used to store app title
    final Context context = this;
    Intent i;
    Bundle b;
    int navPosition;
    private String[] menuPeminjam; // slide menu items
    PenggunaController penggunaController;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();

        session = new SessionManager(getApplicationContext());

        b = getIntent().getExtras();
        if(b!=null) {
            navPosition = b.getInt("navPosition");
        }
        // load slide menu items
        menuPeminjam = getResources().getStringArray(R.array.nav_drawer_items_peminjam);

        // nav drawer icons from resources
//        TypedArray navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        ArrayList<NavDrawerItem> navDrawerItems = new ArrayList<>();

            // adding nav drawer items to array
            // Cari Ruangan Waktu
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[0]));
            // Lihat Jadwal Ruangan
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[1]));
            // Daftar Pending
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[2]));
            // Daftar History
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[3]));
//            // Daftar Pesan
//            navDrawerItems.add(new NavDrawerItem(menuPeminjam[3], navMenuIcons.getResourceId(3, -1)));
            // Daftar Ditolak
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[4]));
            // Logout
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[5]));

        // Recycle the typed array
//        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        NavDrawerListAdapter adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        if (getActionBar()!=null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_sidemenu, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle + " : Mahasiswa");
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//        if (navPosition == 0) {
//            // on first time display view for first nav item
//            displayView(0);
//        }
        displayView(navPosition);
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
//        // Handle action bar actions click
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
        return false;
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CariRuanganWaktu();
                break;
            case 1:
                fragment = new CariRuanganRuang();
                break;
            case 2:
                fragment = new DaftarPendingP();
                break;
            case 3:
                fragment = new DaftarHistoryP();
                break;
            case 4:
                fragment = new DaftarRejectedP();
                break;
            case 5:
                logout();
                break;
            default:
                break;
        }

        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(menuPeminjam[position]);

            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    private void logout() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set title
        alertDialogBuilder.setTitle("Apakah anda yakin untuk logout?");

        // set dialog message
        alertDialogBuilder
                .setMessage("Tekan Ya untuk logout")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        pilihan 'ya' akan menghapus semua SharedPreferences yang ada dan mengarahkan ke
//                        halaman Login dan mengakhiri semua proses yang ada di stack
                        Log.e("sebelum logout session" , session.getUserDetails()+"");
//                        Log.e("sebelum logout", setting.getString(LoginActivity.KEY_USERNAME,null) + " "
//                                + setting.getString(LoginActivity.KEY_NAMA,null) + " " + setting.getString(LoginActivity.KEY_ROLE,null));

                        session.logoutUser();

//                        Log.e("setelah logout", setting.getString(LoginActivity.KEY_USERNAME,null) + " "
//                                + setting.getString(LoginActivity.KEY_NAMA,null) + " " + setting.getString(LoginActivity.KEY_ROLE,null));

                        Log.e("sesudah logout session" , session.getUserDetails()+"");
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);

                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        if (getActionBar()!=null) {
            getActionBar().setTitle(mTitle);
        }
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        // saat user menekan tombol back, lakukan konfirmasi logout
        logout();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Main activity P start", "start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Main Activity P restart", "restart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Main Activity P pause", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Main Activity P resume", "resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Main Activity P stop", "stop");
    }
}
