package com.example.david.may2018lect1;

import android.app.Application;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import dbclasses.Database;
import dbclasses.DatabaseHandler;

import static java.lang.System.exit;

public class MainActivity extends AppCompatActivity {
    Button btnNext, btnViewSaved;
    EditText etSurname, etOthername;
    RadioButton rdbMale, rdbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents(){
        btnNext = (Button) findViewById(R.id.btnNext);
        btnViewSaved = (Button)findViewById(R.id.btnViewSaved);
        etSurname = (EditText)findViewById(R.id.etSurname);
        etOthername = (EditText)findViewById(R.id.etOthername);
        rdbMale = (RadioButton)findViewById(R.id.rdbMale);
        rdbFemale = (RadioButton)findViewById(R.id.rdbFemale);

        btnNext.setOnClickListener(onClick);
        btnViewSaved.setOnClickListener(onClick);

    }

    View.OnClickListener onClick = new View.OnClickListener(){
        @Override
        public  void onClick(View view){

            if(view.equals(btnViewSaved)){
                //Toast.makeText(MainActivity.this, "Cancel Clicked", Toast.LENGTH_LONG).show();
                //DatabaseHandler dh =  new DatabaseHandler(getApplicationContext());
                Intent intent = new Intent(getApplicationContext(), ViewStudentsActivity.class);
                startActivity(intent);
            }

            if(view.equals(btnNext)){
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
                    Toast.makeText(MainActivity.this, "Select Sex!", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    DatabaseHandler dh = new DatabaseHandler(getApplicationContext());

                    ContentValues values = new ContentValues();

                    values.put(Database.Student.SURNAME, strSurname);
                    values.put(Database.Student.OTHERNAME, strOthername);
                    values.put(Database.Student.SEX, strSex);

                    dh.insert(Database.Student.NAME, values);

                    Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();

                    etSurname.setText("");
                    etOthername.setText("");
                    rdbMale.setChecked(false);
                    rdbFemale.setChecked(false);

                    Intent intent =  new Intent(getApplicationContext(), ViewStudentsActivity.class);
                    startActivity(intent);


                } catch (Exception ex){
                   Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

        }
    };





}
