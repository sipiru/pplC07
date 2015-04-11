package ppl.sipiru4.adapter;

/**
 * Created by Gina on 4/9/2015.
 */
import ppl.sipiru4.GamesController;
import ppl.sipiru4.TopRatedController;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new TopRatedController();
            case 1:
                // Games fragment activity
                return new GamesController();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
}
