package ppl.sipiru4;

import ppl.sipiru4.Entity.User;
import ppl.sipiru4.adapter.NavDrawerListAdapter;
import ppl.sipiru4.model.NavDrawerItem;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
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
    //    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        session.checkLogin();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();

        // mendapatkan nilai-nilai yang dioper dari LoginActivity.class
        User user;

        SharedPreferences setting = getSharedPreferences(LoginActivity.PREFS_NAME,0);

        b = getIntent().getExtras();
        if(b!=null) {
            user = b.getParcelable("user");
            navPosition = b.getInt("navPosition");
            Log.e("user", user.getUsername() + " " + user.getNama() + " " + user.getKodeOrg()+" "+user.getRole() + " " + user.getKodeIdentitas());

            // simpan username, nama dan role ke SharedPreferences
            // dibuat untuk mengatasi bug penyimpanan  nilai-nilai di SharedPreferences saat user sudah melakukan login pertama kali, kemudian logout dan
            // login untuk kedua kalinya atau lebih (tanpa menutup aplikasi selama proses).
            SharedPreferences.Editor edit = setting.edit();
            edit.putString(LoginActivity.KEY_USERNAME, user.getUsername());
            edit.putString(LoginActivity.KEY_NAMA, user.getNama());
            edit.putString(LoginActivity.KEY_KODE_ORG, user.getKodeOrg());
            edit.putString(LoginActivity.KEY_ROLE, user.getRole());
            edit.putString(LoginActivity.KEY_KODE_IDENTITAS, user.getKodeIdentitas());
            edit.apply();
        }
        Log.e("mainAct P ",setting.getString(LoginActivity.KEY_USERNAME,null)+" "
                +setting.getString(LoginActivity.KEY_NAMA,null) + " " + setting.getString(LoginActivity.KEY_ROLE,null));

        // load slide menu items
        menuPeminjam = getResources().getStringArray(R.array.nav_drawer_items_peminjam);

        // nav drawer icons from resources
        TypedArray navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        ArrayList<NavDrawerItem> navDrawerItems = new ArrayList<>();

            // adding nav drawer items to array
            // Cari Ruangan Waktu
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[0], navMenuIcons.getResourceId(0, -1)));
            // Lihat Jadwal Ruangan
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[1], navMenuIcons.getResourceId(1, -1)));
            // Daftar Pending
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[2], navMenuIcons.getResourceId(2, -1)));
            // Daftar History
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[3], navMenuIcons.getResourceId(3, -1)));
//            // Daftar Pesan
//            navDrawerItems.add(new NavDrawerItem(menuPeminjam[3], navMenuIcons.getResourceId(3, -1)));
            // PesanBaru
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[4], navMenuIcons.getResourceId(4, -1)));
            // Logout
        navDrawerItems.add(new NavDrawerItem(menuPeminjam[5], navMenuIcons.getResourceId(5, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        NavDrawerListAdapter adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                 //nav menu toggle icon
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
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
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
                fragment = new DaftarPermohonanP();
                fragment.setArguments(b);
                break;
            case 3:
                fragment = new DaftarPeminjamanP();
                fragment.setArguments(b);
                break;
            case 4:
                Intent i = new Intent(getApplicationContext(), KirimPesan.class);
                startActivity(i);
                break;
            case 5:
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
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences setting = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
//                        pilihan 'ya' akan menghapus semua SharedPreferences yang ada dan mengarahkan ke
//                        halaman Login dan mengakhiri semua proses yang ada di stack

//                        Log.e("sebelum logout", setting.getString(LoginActivity.KEY_USERNAME,null) + " "
//                                + setting.getString(LoginActivity.KEY_NAMA,null) + " " + setting.getString(LoginActivity.KEY_ROLE,null));

                        SharedPreferences.Editor edit = setting.edit();
                        edit.clear();
                        edit.apply();

//                        Log.e("setelah logout", setting.getString(LoginActivity.KEY_USERNAME,null) + " "
//                                + setting.getString(LoginActivity.KEY_NAMA,null) + " " + setting.getString(LoginActivity.KEY_ROLE,null));

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
