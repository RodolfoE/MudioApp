//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.example.rodolfo.mudioapp.fragment;


import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import com.example.rodolfo.mudioapp.R;


public class NavigationDrawerFragment extends Fragment {
    private static final String TAG = "NavigationDrawerFragment";
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    public static final boolean LOG_ON = true;
    private NavigationDrawerFragment.NavigationDrawerCallbacks mCallbacks;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ListView listView;
    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    public NavigationDrawerFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        this.mUserLearnedDrawer = sp.getBoolean("navigation_drawer_learned", false);
        if(savedInstanceState != null) {
            this.mCurrentSelectedPosition = savedInstanceState.getInt("selected_navigation_drawer_position");
            this.mFromSavedInstanceState = true;
        }

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("temp", "onCreateView CONTAINER : " + container);
        if(this.mCallbacks != null) {
            NavigationDrawerFragment.NavDrawerListView navView = this.mCallbacks.getNavDrawerView(this, inflater, container);
            if(navView != null) {
                View view = navView.view;
                if(view == null) {
                    return this.createDefaultView("The method getNavDrawerView should return a not null View object.");
                } else {
                    this.listView = (ListView)view.findViewById(navView.listViewId);
                    return this.listView == null?this.createDefaultView("The ListView with the specified id was not found. Please review the view returned by the method getNavDrawerView."):view;
                }
            } else {
                return null;
            }
        } else {
            return this.createDefaultView("A activity precisa implementar a interface NavigationDrawerCallbacks.");
        }
    }

    private View createDefaultView(String s) {
        TextView t = new TextView(this.getActivity());
        t.setText(s);
        t.setGravity(17);
        return t;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //this.log("NavigationDrawerFragment.onViewCreated: " + this.listView);
        if(this.mCallbacks != null && this.listView != null) {
            this.listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //NavigationDrawerFragment.this.log("listView.onItemClick: " + position);
                    NavigationDrawerFragment.this.selectItem(position, true);
                }
            });
            this.listView.setAdapter(this.mCallbacks.getNavDrawerListAdapter(this));
            this.listView.setItemChecked(this.mCurrentSelectedPosition, true);
        }

        this.selectItem(this.mCurrentSelectedPosition, savedInstanceState == null);
    }

    public void setUp(DrawerLayout drawerLayout) {
        this.mDrawerLayout = drawerLayout;
        //this.mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, 8388611);
        ActionBar actionBar = this.getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        this.mDrawerToggle = new ActionBarDrawerToggle(this.getActivity(), this.mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                //NavigationDrawerFragment.this.log("onDrawerClosed()");
                if(NavigationDrawerFragment.this.isAdded()) {
                    NavigationDrawerFragment.this.getActivity().supportInvalidateOptionsMenu();
                }
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if(NavigationDrawerFragment.this.isAdded()) {
                    if(!NavigationDrawerFragment.this.mUserLearnedDrawer) {
                        NavigationDrawerFragment.this.mUserLearnedDrawer = true;
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(NavigationDrawerFragment.this.getActivity());
                        sp.edit().putBoolean("navigation_drawer_learned", true).apply();
                    }

                    NavigationDrawerFragment.this.getActivity().supportInvalidateOptionsMenu();
                }
            }
        };
        if(!this.mUserLearnedDrawer && !this.mFromSavedInstanceState) {
            this.openDrawer();
        }

        this.mDrawerLayout.post(new Runnable() {
            public void run() {
                NavigationDrawerFragment.this.mDrawerToggle.syncState();
            }
        });
        this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);
    }

    private void selectItem(int position, boolean call) {
        this.mCurrentSelectedPosition = position;
        if(this.listView != null) {
            this.listView.setItemChecked(position, true);
        }

        if(this.mDrawerLayout != null) {
            this.closeDrawer();
        }

        if(this.mCallbacks != null && call) {
            this.mCallbacks.onNavDrawerItemSelected(this, position);
        }

    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            //this.log("NavigationDrawerFragment.onAttach: " + activity);
            this.mCallbacks = (NavigationDrawerFragment.NavigationDrawerCallbacks)activity;
        } catch (ClassCastException var3) {
            Log.d("NavigationDrawerFrag", "Activity must implement NavigationDrawerCallbacks.");
        }

    }

    public void onDetach() {
        super.onDetach();
        this.mCallbacks = null;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selected_navigation_drawer_position", this.mCurrentSelectedPosition);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return this.mDrawerToggle != null && this.mDrawerToggle.onOptionsItemSelected(item)?true:super.onOptionsItemSelected(item);
    }

    public void setActionBarTitle(int title) {
        this.setActionBarTitle(this.getString(title));
    }

    public void setActionBarTitle(CharSequence title) {
        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(0);
        actionBar.setTitle(title);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity)this.getActivity()).getSupportActionBar();
    }

    public boolean isDrawerOpen() {
        return this.mDrawerLayout != null && this.mDrawerLayout.isDrawerOpen(8388611);
    }

    public boolean isDrawerClosed() {
        return !this.isDrawerOpen();
    }

    public void openDrawer() {
        if(this.mDrawerLayout != null) {
            this.mDrawerLayout.openDrawer(8388611);
        }

    }

    public void closeDrawer() {
        if(this.mDrawerLayout != null) {
            this.mDrawerLayout.closeDrawer(8388611);
        }

    }

    public interface NavigationDrawerCallbacks {
        NavigationDrawerFragment.NavDrawerListView getNavDrawerView(NavigationDrawerFragment var1, LayoutInflater var2, ViewGroup var3);

        ListAdapter getNavDrawerListAdapter(NavigationDrawerFragment var1);

        void onNavDrawerItemSelected(NavigationDrawerFragment var1, int var2);
    }

    public static class NavDrawerListView {
        View view;
        int listViewId;

        public NavDrawerListView(View view, int listViewId) {
            this.view = view;
            this.listViewId = listViewId;
        }
    }
}
