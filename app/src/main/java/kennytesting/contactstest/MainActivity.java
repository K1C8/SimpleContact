package kennytesting.contactstest;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    /**
     * Logging tag.
     */
    private static final String TAG = "MainActivity";

    /**
     *  Navigation Drawer related items.
     */
    private String[] mFragmentsList;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /**
     * Used to store the title of the navigation drawer.
     */
    private CharSequence mDrawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inflate the overlay layer of the nav drawer
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DrawerLayout drawer = (DrawerLayout)inflater.inflate(R.layout.drawer_overlay, null);

        // HACK: "steal" the first child of decor view
        ViewGroup overlay = (ViewGroup)getWindow().getDecorView();
        View child = overlay.getChildAt(0);
        overlay.removeView(child);
        FrameLayout container = (FrameLayout) drawer.findViewById(R.id.layer_container); // This is the container we defined just now.
        container.addView(child);

        // Make the drawer replace the first child
        overlay.addView(drawer);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(mToolbar);

        mTitle = getTitle();
        mDrawerTitle = getTitle();

        // Set up the drawer.
        mFragmentsList = getResources().getStringArray(R.array.navigation_drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_navigation_item, mFragmentsList));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        // Set up the listener for open and close events.
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        menu.findItem(R.id.action_add).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_sectionContacts);
                break;
            case 2:
                mTitle = getString(R.string.title_sectionSearch);
                break;
            case 3:
                mTitle = getString(R.string.title_sectionAdd);
                break;
            case 4:
                mTitle = getString(R.string.title_section3);
                break;
        }
        Log.i(TAG, "Changing title to " + mTitle + " .");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        restoreActionBar();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (id) {
            case R.id.action_search:
                Log.i(TAG, "Switching to "
                        + getString(R.string.title_sectionSearch) + " in optionsMenu.");
                fragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment.newInstance(2))
                        .commit();
                setActionBarTitle(getString(R.string.title_sectionSearch));
                return true;
            case R.id.action_add:
                Log.i(TAG, "Switching to "
                        + getString(R.string.title_sectionAdd) + " in optionsMenu.");
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddFragment.newInstance(3))
                        .commit();
//                Toast.makeText(getApplication(), "Adding new contact", Toast.LENGTH_SHORT)
//                        .show();
                setActionBarTitle(getString(R.string.title_sectionAdd));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // update the main content by replacing fragments
    public void selectItem(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, ContactsFragment.newInstance(position + 1))
                        .commit();
                Log.v(TAG, "Item " + getString(R.string.title_sectionContacts)
                        + " in navigation drawer is selected, switching to ContactsFragment.");
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment.newInstance(position + 1))
                        .commit();
                Log.v(TAG, "Item " + getString(R.string.title_sectionSearch)
                        + " in navigation drawer is selected, switching to SearchFragment.");
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, AddFragment.newInstance(position + 1))
                        .commit();
                Log.v(TAG, "Item " + getString(R.string.title_sectionAdd)
                        + " in navigation drawer is selected, switching to AddFragment.");
                break;
            default:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
                Log.v(TAG, "Item " + getString(R.string.title_section3)
                        + " in navigation drawer is selected, switching to PlaceholderFragment.");
                break;
        }
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
