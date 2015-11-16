package kennytesting.contactstest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

    /**
     * SQLite related strings.
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_TABLE_ENTRY =
            "CREATE TABLE" + ContactsReaderContract.ContactsEntry.TABLE_NAME + " ("
            + ContactsReaderContract.ContactsEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP
            + ContactsReaderContract.ContactsEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP
            + ContactsReaderContract.ContactsEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP
            + ContactsReaderContract.ContactsEntry.COLUMN_NAME_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP
            + ContactsReaderContract.ContactsEntry.COLUMN_NAME_PINYIN + " )";
    private static final String SQL_CREATE_TABLE_TAG =
            "CREATE TABLE" + ContactsReaderContract.ContactsTagsEntry.TABLE_NAME + " ("
            + ContactsReaderContract.ContactsTagsEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP
            + ContactsReaderContract.ContactsTagsEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP
            + ContactsReaderContract.ContactsTagsEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP
            + ContactsReaderContract.ContactsTagsEntry.COLUMN_NAME_TAG + " )";
    private static final String SQL_DROP_TABLE_ENTRY =
            "DROP TABLE IF EXISTS " + ContactsReaderContract.ContactsEntry.TABLE_NAME;
    private static final String SQL_DROP_TABLE_TAG =
            "DROP TABLE IF EXISTS " + ContactsReaderContract.ContactsTagsEntry.TABLE_NAME;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AddFragment newInstance(int sectionNumber) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}
