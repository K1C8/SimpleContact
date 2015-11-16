package kennytesting.contactstest;

import android.provider.BaseColumns;

/**
 * Created by Kenny on 2015/11/13.
 */
public class ContactsReaderContract {

    public ContactsReaderContract() {}

    public static abstract class ContactsEntry implements BaseColumns {
        public static final String TABLE_NAME = "contacts_entry";
        public static final String _ID = "_ID";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_NAME_PINYIN = "pinyin";
    }

    public static abstract class ContactsTagsEntry implements BaseColumns {
        public static final String TABLE_NAME = "contacts_tag";
        public static final String _ID = "_ID";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TAG = "tag";
    }
}
