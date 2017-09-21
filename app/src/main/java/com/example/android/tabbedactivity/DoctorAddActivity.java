package com.example.android.tabbedactivity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.tabbedactivity.Util.LogToast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;


public class DoctorAddActivity extends AppCompatActivity {

    ContactsDatabaseAdapter.ContactsDatabaseHelper contactsDatabaseHelper;

    EditText doctorName;
    EditText speciality;
    EditText phoneNumber;
    EditText email;
    EditText schedule;
    EditText address;


    ContactsDatabaseAdapter contactsDatabaseAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        ActionBar actionBar=getSupportActionBar();
        //actionBar.hide();

        doctorName= (EditText) findViewById(R.id.d_name_edit_text);
        speciality= (EditText) findViewById(R.id.d_speciality_edit_text);
        phoneNumber= (EditText) findViewById(R.id.d_phone_number_edit_text);
        email= (EditText) findViewById(R.id.d_email_edit_text);
        schedule= (EditText) findViewById(R.id.d_schedule_edit_text);
        address= (EditText) findViewById(R.id.d_address_edit_text);

        contactsDatabaseHelper=new ContactsDatabaseAdapter.ContactsDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=contactsDatabaseHelper.getWritableDatabase();

        contactsDatabaseAdapter=new ContactsDatabaseAdapter(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_close:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Toast.makeText(this,"dfrom favor",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_done:
                 long id=contactsDatabaseAdapter.insertDoctorData(doctorName.getText().toString(),speciality.getText().toString(),
                         phoneNumber.getText().toString(),email.getText().toString(),schedule.getText().toString(),address.getText().toString());
                if(id<0){
                    LogToast.T(this,"unsuccess");
                }else{
                    LogToast.T(this,"success");
                }

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor, menu);
        return true;
    }

    private void setInfo(){

    }


}
