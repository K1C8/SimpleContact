package kennytesting.contactstest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kenny on 2015/9/22.
 */
public class AddFragment extends Fragment {

    /**
     * Logging tag.
     */
    private static final String TAG = "AddFragment";

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private static SQLiteDatabase mSQLiteDB;

    private View view;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AddFragment newInstance(int sectionNumber, MainActivity activity) {
        AddFragment fragment = new AddFragment();
        mSQLiteDB = activity.getMSQLiteDB();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.fragment_add, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_fragment_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_check:
                String tableName = ContactsReaderContract.ContactsEntry.TABLE_NAME;
                String[] projection = {ContactsReaderContract.ContactsEntry._ID};
                String sortOrder = ContactsReaderContract.ContactsEntry.COLUMN_NAME_ENTRY_ID + " DESC";
                Cursor c = mSQLiteDB.query(
                        tableName,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder);
                c.moveToFirst();
                try {
                    long itemId = c.getLong(c.getColumnIndexOrThrow(projection[0])) + 1;
                    ContentValues values = new ContentValues();
                    TextInputLayout nameText =
                            (TextInputLayout)view.findViewById(R.id.fragment_add_TextInputLayout_name);
                    TextInputLayout phoneText =
                            (TextInputLayout)view.findViewById(R.id.fragment_add_TextInputLayout_phone_number_1);
                    TextInputLayout tagsText =
                            (TextInputLayout)view.findViewById(R.id.fragment_add_TextInputLayout_tag);
                    String nameString = (String) (CharSequence) nameText.getEditText().getText();
                    Log.i(TAG, "Inserting item with ItemID of " + itemId);
                    Log.i(TAG, nameString);
                    //values.put(ContactsReaderContract.ContactsEntry._ID, itemId);

                } catch (IllegalArgumentException e) {
                    Log.i(TAG, "Cannot get the ItemID of the last item.");
                    if (null != view) {
                        Snackbar
                                .make(view.findViewById(R.id.container), e.toString(), Snackbar.LENGTH_LONG)
                                .show();
                    }
                }
                c.close();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
