package com.example.android.tabbedactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import com.example.android.tabbedactivity.Util.LogToast;

import static android.R.attr.password;
import static android.R.attr.theme;
import static android.R.attr.version;
import static com.example.android.tabbedactivity.ContactsDatabaseAdapter.ContactsDatabaseHelper.TAG;
import static com.example.android.tabbedactivity.R.drawable.contacts;

/**
 * Created by shanto on 9/21/17.
 */

public class ContactsDatabaseAdapter {

    ContactsDatabaseHelper contactsDatabaseHelper;
    private SQLiteDatabase db;
    Context context;

    public ContactsDatabaseAdapter(Context context){
        contactsDatabaseHelper=new ContactsDatabaseHelper(context);
        this.context=context;
    }

    public long insertDoctorData(String name,String speciality,String phone,String email,String schedule,String address){
        SQLiteDatabase database=contactsDatabaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(contactsDatabaseHelper.DOCTOR_NAME,name);
        contentValues.put(contactsDatabaseHelper.SPECIALITY,speciality);
        contentValues.put(contactsDatabaseHelper.MOBILE,phone);
        contentValues.put(contactsDatabaseHelper.EMAIL,email);
        contentValues.put(contactsDatabaseHelper.SCHEDULE,schedule);
        contentValues.put(contactsDatabaseHelper.ADDRESS,address);

        long id=database.insert(contactsDatabaseHelper.DOCTOR_TABLE,contactsDatabaseHelper.ADDRESS,contentValues);

        return id;

    }

    public String loadData(){



        SQLiteDatabase sqLiteDatabase=contactsDatabaseHelper.getWritableDatabase();


        String columns[]={contactsDatabaseHelper.UID,contactsDatabaseHelper.DOCTOR_NAME};

        Cursor cursor= sqLiteDatabase.query(contactsDatabaseHelper.DOCTOR_TABLE,columns,null,null,null,null,null);
        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext()){

            int cid=cursor.getInt(cursor.getColumnIndex(contactsDatabaseHelper.UID));
            String name=cursor.getString(cursor.getColumnIndex(contactsDatabaseHelper.DOCTOR_NAME));


            stringBuffer.append(cid+" "+name+"\n");

        }


        return stringBuffer.toString();

    }



    public SimpleCursorAdapter populateView(){
        Cursor cursor=this.getAllRows();
        //startManagingCursor(cursor);

        LogToast.L(TAG,"form populate 1");

        String fromFieldName[]={contactsDatabaseHelper.DOCTOR_NAME,contactsDatabaseHelper.UID};
        int toViewIDs[]={R.id.name,R.id.id};

        SimpleCursorAdapter mCursorAdapter=
                new SimpleCursorAdapter(context,R.layout.list_view_template,
                        cursor,
                        fromFieldName, toViewIDs);
        if(mCursorAdapter==null)
        LogToast.L(TAG,"form populate 2"+mCursorAdapter);
        return mCursorAdapter;
    }

    public Cursor getAllRows(){
        SQLiteDatabase db=contactsDatabaseHelper.getWritableDatabase();
        String where=null;
        String columns[]={"rowid _id",contactsDatabaseHelper.UID,contactsDatabaseHelper.DOCTOR_NAME,contactsDatabaseHelper.SPECIALITY,
                          };
        Cursor cursor=db.query(contactsDatabaseHelper.DOCTOR_TABLE,columns,where,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void close(){
        contactsDatabaseHelper.close();
    }

    public

    static class ContactsDatabaseHelper extends SQLiteOpenHelper{


        Context context;
        public static String TAG="Tag";

        //private static final String CREATE_TABLE="CREATE TABLE";

        private static final String CONTACTS_DATABASE="ContactsDatabase.db";
        private static final int DATABASE_VER=1;

        private static final String DOCTOR_TABLE="doctortable";
        private static final String UID="_uid";
        private static final String DOCTOR_NAME="name";
        private static final String SPECIALITY="speciality";
        private static final String MOBILE="mobile_num";
        private static final String EMAIL="email";
        private static final String ADDRESS="address";
        private static final String SCHEDULE="schedule";
        private static final String COMMA_SEP=" , ";

        private static final String CREATE_DOCTOR_TABLE="CREATE TABLE "+DOCTOR_TABLE+" ("+
                UID+" INTEGER PRIMARY KEY AUTOINCREMENT"+COMMA_SEP+DOCTOR_NAME+" VARCHAR(20)"+
                COMMA_SEP+SPECIALITY+" VARCHAR(20)"+COMMA_SEP+MOBILE+" VARCHAR(15)"+COMMA_SEP+
                EMAIL+" VARCHAR(25)"+COMMA_SEP+SCHEDULE+" VARCHAR(30)"+COMMA_SEP+ADDRESS+" VARCHAR(40));";


        public ContactsDatabaseHelper(Context context) {
            super(context, CONTACTS_DATABASE,null, DATABASE_VER);
            this.context=context;
            LogToast.T(context,"constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try{
                sqLiteDatabase.execSQL(CREATE_DOCTOR_TABLE);
                LogToast.T(context,"successfull");
            }catch (SQLException e){
                LogToast.L(TAG,e.getMessage());
                LogToast.T(context,e.getMessage());
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }


    }

}
