package ppl.sipiru4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
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
import ppl.sipiru4.Entity.User;
import ppl.sipiru4.adapter.NavDrawerListAdapter;
import ppl.sipiru4.model.NavDrawerItem;

public class MainActivityMR extends FragmentActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    final Context context = this;
    Intent i;
    Bundle b;
    int navPosition;
    private String[] menuMR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();

        User user;

        SharedPreferences setting = getSharedPreferences(LoginActivity.PREFS_NAME,0);

        // mendapatkan nilai-nilai yang dioper
        b = getIntent().getExtras();
        if (b!=null){
            user = b.getParcelable("user");
            navPosition = b.getInt("navPosition");
            Log.e("user", user.getUsername() + " " + user.getNama() + " " + user.getKodeOrg()+" "+user.getRole() + " " + user.getKodeIdentitas());

            // simpan username, nama dan role ke SharedPreferences
            // dibuat untuk mengatasi bug penyimpanan  nilai-nilai di SharedPreferences saat user sudah melakukan login pertama kali, kemudian logout dan
            // login untuk kedua kalinya atau lebih (tanpa menutup aplikasi selama proses).
            SharedPreferences.Editor edit = setting.edit();
            edit.putString(LoginActivity.KEY_USERNAME, user.getUsername());
            edit.putString(LoginActivity.KEY_NAMA, user.getNama());
            edit.putString(LoginActivity.KEY_ROLE, user.getRole());
            edit.apply();
        }
        Log.e("mainAct MR ",setting.getString(LoginActivity.KEY_USERNAME,null)+" "
                +setting.getString(LoginActivity.KEY_NAMA,null) + " " + setting.getString(LoginActivity.KEY_ROLE,null));

        menuMR = getResources().getStringArray(R.array.nav_drawer_items_mr);
        TypedArray navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons_mgr);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        ArrayList<NavDrawerItem> navDrawerItems = new ArrayList<>();

            // adding nav drawer items to array
            // Daftar Pending
            navDrawerItems.add(new NavDrawerItem(menuMR[0], navMenuIcons.getResourceId(0, -1)));
            // Daftar History
            navDrawerItems.add(new NavDrawerItem(menuMR[1], navMenuIcons.getResourceId(1, -1)));
            // Lihat Jadwal Ruangan
            navDrawerItems.add(new NavDrawerItem(menuMR[2], navMenuIcons.getResourceId(2, -1)));
            // PesanBaru
            navDrawerItems.add(new NavDrawerItem(menuMR[3], navMenuIcons.getResourceId(3, -1)));
            // Logout
            navDrawerItems.add(new NavDrawerItem(menuMR[4], navMenuIcons.getResourceId(4, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        NavDrawerListAdapter adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
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
                fragment = new DaftarPendingMR();
                break;
            case 1:
                fragment = new DaftarHistoryMR();
                break;
            case 2:
                fragment = new CariRuanganRuang();
                break;
            case 3:
                fragment = new KirimPesan();
                break;
            case 4:
                logout();
            default:
                break;
        }

        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(menuMR[position]);

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
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // pilihan 'ya' akan menghapus semua SharedPreferences yang ada dan mengarahkan ke
                        // halaman Login dan mengakhiri semua proses yang ada di stack
                        SharedPreferences setting = getSharedPreferences(LoginActivity.PREFS_NAME,0);
                        Log.e("sebelum logout", setting.getString(LoginActivity.KEY_USERNAME,null) + " "
                                + setting.getString(LoginActivity.KEY_NAMA,null) + " " + setting.getString(LoginActivity.KEY_ROLE,null));

                        SharedPreferences.Editor edit = setting.edit();
                        edit.clear();
                        edit.apply();

                        Log.e("setelah logout", setting.getString(LoginActivity.KEY_USERNAME,null) + " "
                                + setting.getString(LoginActivity.KEY_NAMA,null) + " " + setting.getString(LoginActivity.KEY_ROLE,null));

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
        getActionBar().setTitle(mTitle);
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
}
