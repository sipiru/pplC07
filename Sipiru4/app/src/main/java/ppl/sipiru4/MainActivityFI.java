package ppl.sipiru4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import ppl.sipiru4.Entity.SessionManager;
import ppl.sipiru4.adapter.NavDrawerListAdapter;
import ppl.sipiru4.controller.PenggunaController;
import ppl.sipiru4.model.NavDrawerItem;

public class MainActivityFI extends FragmentActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    final Context context = this;
    Intent i;
    Bundle b;
    int navPosition;
    private String[] menuFI;
    PenggunaController penggunaController;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();

        // mendapatkan nilai-nilai yang dioper
        b = getIntent().getExtras();
        if (b!=null){
            navPosition = b.getInt("navPosition");
        }

        menuFI = getResources().getStringArray(R.array.nav_drawer_items_fasumitf);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        ArrayList<NavDrawerItem> navDrawerItems = new ArrayList<>();

        // adding nav drawer items to array
        // Daftar Permohonan
        navDrawerItems.add(new NavDrawerItem(menuFI[0]));
        // Daftar Pengembalian Alat
        navDrawerItems.add(new NavDrawerItem(menuFI[1]));
        // Daftar History
        navDrawerItems.add(new NavDrawerItem(menuFI[2]));
        // Daftar Ditolak
        navDrawerItems.add(new NavDrawerItem(menuFI[3]));
        // Lihat Jadwal Ruangan
        navDrawerItems.add(new NavDrawerItem(menuFI[4]));
        // Logout
        navDrawerItems.add(new NavDrawerItem(menuFI[5]));

//        // Recycle the typed array
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
                getActionBar().setTitle(mDrawerTitle + " : ITF/Fasum");
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        displayView(navPosition);
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
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
                fragment = new DaftarPendingFI();
                break;
            case 1:
                fragment = new DaftarPengembalianAlatFI();
                break;
            case 2:
                fragment = new DaftarHistoryFI();
                break;
            case 3:
                fragment = new DaftarRejected();
                break;
            case 4:
                fragment = new CariRuanganRuang();
                break;
            case 5:
                logout();
                break;
            default:
                break;
        }

        if (fragment != null) {
            //FragmentManager fragmentManager = getFragmentManager();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
                setTitle(menuFI[position]);


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
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // pilihan 'ya' akan menghapus semua SharedPreferences yang ada dan mengarahkan ke
                        // halaman Login dan mengakhiri semua proses yang ada di stack
                        session.logoutUser();

                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);

                        finish();
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        // memunculkan alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        if (getActionBar()!=null){
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
        logout();
    }
}
