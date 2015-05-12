package ppl.sipiru4;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public  class CariRuangan extends Fragment
{


    private TabHost mTabHost;
    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;

    public CariRuangan() {
    }

    @Override
    public void onCreate(Bundle instance)
    {
        super.onCreate(instance);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_ui, container, false);

        mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mTabsAdapter = new TabsAdapter(getActivity(),mTabHost, mViewPager );

        // Here we load the content for each tab.
        mTabsAdapter.addTab(mTabHost.newTabSpec("Waktu Pinjam").setIndicator("Waktu Pinjam"), CariRuanganWaktu.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("Ruangan").setIndicator("Ruangan"), CariRuanganRuang.class, null);

        return v;
    }

    public static class TabsAdapter extends FragmentStatePagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener
    {
        Map<Integer,Fragment> integerFragmentMap = new HashMap<Integer,Fragment>();
        private final Context mContext;
        private final TabHost mTabHost;
        private final ViewPager mViewPager;
        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

        static final class TabInfo
        {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;

            TabInfo(String _tag, Class<?> _class, Bundle _args)
            {
                tag = _tag;
                clss = _class;
                args = _args;
            }
        }

        static class DummyTabFactory implements TabHost.TabContentFactory
        {
            private final Context mContext;

            public DummyTabFactory(Context context)
            {
                mContext = context;
            }

            public View createTabContent(String tag)
            {
                View v = new View(mContext);
                v.setMinimumWidth(0);
                v.setMinimumHeight(0);
                return v;
            }
        }

        public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager)
        {
            super(activity.getSupportFragmentManager());
            mContext = activity;
            mTabHost = tabHost;
            mViewPager = pager;
            mTabHost.setOnTabChangedListener(this);
            mViewPager.setAdapter(this);
            mViewPager.setOnPageChangeListener(this);
        }

        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args)
        {
            tabSpec.setContent(new DummyTabFactory(mContext));
            String tag = tabSpec.getTag();

            TabInfo info = new TabInfo(tag, clss, args);
            mTabs.add(info);
            mTabHost.addTab(tabSpec);
            notifyDataSetChanged();
        }

        @Override
        public int getCount()
        {
            return mTabs.size();
        }

        @Override
        public Fragment getItem(int position)
        {
            Fragment fragment=null;
            Bundle args = new Bundle();
            if(position==0)
            {
                fragment = new CariRuanganWaktu();
                args.putString("text", "This is TAB0");
                fragment.setArguments(args);
            }
            else if(position==1)
            {
                fragment = new CariRuanganRuang();
                args.putString("text", "This is TAB1");
                fragment.setArguments(args);
            }

            integerFragmentMap.put(Integer.valueOf(position),fragment);//when getItem, put it into the Map

            return fragment;
            //TabInfo info = mTabs.get(position);

            //return Fragment.instantiate(mContext, info.clss.getName(), info.args);

        }

        public void onTabChanged(String tabId)
        {
            int position = mTabHost.getCurrentTab();
            mViewPager.setCurrentItem(position);
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {
        }

        public void onPageSelected(int position)
        {
            // Unfortunately when TabHost changes the current tab, it kindly
            // also takes care of putting focus on it when not in touch mode.
            // The jerk.
            // This hack tries to prevent this from pulling focus out of our
            // ViewPager.
            TabWidget widget = mTabHost.getTabWidget();
            int oldFocusability = widget.getDescendantFocusability();
            widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            mTabHost.setCurrentTab(position);
            widget.setDescendantFocusability(oldFocusability);
        }

        public void onPageScrollStateChanged(int state)
        {
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);

            integerFragmentMap.remove(Integer.valueOf(position));//when destroyItem, remove it from Map
        }

        public void removeAll()
        {
            Iterator iterator = integerFragmentMap.entrySet().iterator();
            while (iterator.hasNext())
            {
                Map.Entry entry = (Map.Entry)iterator.next();
                Fragment fragment = (Fragment) entry.getValue();
                fragment.onDestroyView();//Trigger inner fragment's DestroyView() method !
            }
        }
    }
}