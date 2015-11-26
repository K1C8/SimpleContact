package kennytesting.contactstest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Used for SQLite operations.
 * Created by Kenny on 2015/11/19.
 */
public class ContactsReaderDbHelper extends SQLiteOpenHelper {
    /**
     * singleton pattern
     */
    private static ContactsReaderDbHelper contactsReaderDbHelper;
    /**
     * SQLite related strings.
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_TABLE_ENTRY =
            "CREATE TABLE " + ContactsReaderContract.ContactsEntry.TABLE_NAME + " ("
                    + ContactsReaderContract.ContactsEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP
                    + ContactsReaderContract.ContactsEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP
                    + ContactsReaderContract.ContactsEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP
                    + ContactsReaderContract.ContactsEntry.COLUMN_NAME_PHONE_NUMBER + TEXT_TYPE + COMMA_SEP
                    + ContactsReaderContract.ContactsEntry.COLUMN_NAME_PINYIN + TEXT_TYPE + " )";
    private static final String SQL_CREATE_TABLE_TAG =
            "CREATE TABLE " + ContactsReaderContract.TagsEntry.TABLE_NAME + " ("
                    + ContactsReaderContract.TagsEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP
                    + ContactsReaderContract.TagsEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP
                    + ContactsReaderContract.TagsEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP
                    + ContactsReaderContract.TagsEntry.COLUMN_NAME_TAG + TEXT_TYPE + ")";
    private static final String SQL_DROP_TABLE_ENTRY =
            "DROP TABLE IF EXISTS " + ContactsReaderContract.ContactsEntry.TABLE_NAME;
    private static final String SQL_DROP_TABLE_TAG =
            "DROP TABLE IF EXISTS " + ContactsReaderContract.TagsEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactsReader.db";

    private ContactsReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ContactsReaderDbHelper getInstance(Context context) {
        if (null == contactsReaderDbHelper) {
            contactsReaderDbHelper = new ContactsReaderDbHelper(context);
        }
        return contactsReaderDbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ENTRY);
        db.execSQL(SQL_CREATE_TABLE_TAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: please implement this method when it is needed.
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: please implement this method when it is needed.
    }
}
