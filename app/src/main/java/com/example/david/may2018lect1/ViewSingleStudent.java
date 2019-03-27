package com.example.david.may2018lect1;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import dbclasses.Database;
import dbclasses.DatabaseHandler;

public class ViewSingleStudent extends AppCompatActivity {

    TextView tvSID;
    EditText etSurname, etOthername;
    RadioButton rdbMale, rdbFemale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_student);

        final String strSID = getIntent().getStringExtra("sid");
        String strSex = getIntent().getStringExtra("sex");
        final String strSurName =  getIntent().getStringExtra("surname");
        String strOtherName = getIntent().getStringExtra("othername");

        tvSID = (TextView) findViewById(R.id.tvSID);
        etOthername = (EditText)findViewById(R.id.etOthername);
        etSurname = (EditText) findViewById(R.id.etSurname);
        rdbMale = (RadioButton)findViewById(R.id.rdbMale);
        rdbFemale = (RadioButton)findViewById(R.id.rdbFemale);

        tvSID.setText(strSID);
        etOthername.setText(strOtherName);
        etSurname.setText(strSurName);

        if (strSex.equals("Male"))
            rdbMale.setChecked(true);
        else
            rdbFemale.setChecked(true);


        Button btnUpdate = (Button)findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSurname, strOthername, strSex;
                strSurname = etSurname.getText().toString();
                strOthername = etOthername.getText().toString();

                if(strSurname.isEmpty()) {
                    etSurname.setError("Sur Name Required!");
                    etSurname.requestFocus();
                    return;
                }

                if(strOthername.isEmpty()){
                    etOthername.setError("Other Name Required!");
                    etOthername.requestFocus();
                    return;
                }

                if(rdbFemale.isChecked())
                    strSex = "Female";
                else if(rdbMale.isChecked())
                    strSex = "Male";
                else{
                    Toast.makeText(getApplicationContext(), "Select Sex!", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    DatabaseHandler dh = new DatabaseHandler(getApplicationContext());

                    ContentValues values = new ContentValues();

                    values.put(Database.Student.SURNAME, strSurname);
                    values.put(Database.Student.OTHERNAME, strOthername);
                    values.put(Database.Student.SEX, strSex);

                    String whereClause = Database.Student.SID + " = ?";
                    String whereArgs[] = {strSID};

                    dh.update(Database.Student.NAME, values, whereClause, whereArgs);

                    Toast.makeText(getApplicationContext(), "Successfully Updated!", Toast.LENGTH_SHORT).show();


                    Intent intent =  new Intent(getApplicationContext(), ViewStudentsActivity.class);
                    startActivity(intent);


                } catch (Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}
