package com.example.david.may2018lect1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import dbclasses.Database;
import dbclasses.DatabaseHandler;

public class ViewStudentsActivity extends AppCompatActivity {
    ArrayList<Student> studentArrayList;
    RecyclerView rvStudents;
    StudentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        studentArrayList = new ArrayList<>();
        rvStudents = (RecyclerView)findViewById(R.id.rvStudents);
        adapter = new StudentsAdapter(studentArrayList);

        rvStudents.setAdapter(adapter);
        RecyclerView.LayoutManager rManger = new LinearLayoutManager(getApplicationContext());
        rvStudents.setLayoutManager(rManger);

        readDatabase();
    }

    public void readDatabase(){
        try {
            DatabaseHandler dh = new DatabaseHandler(getApplicationContext());
            String[] columns = {Database.Student.SURNAME, Database.Student.OTHERNAME, Database.Student.SEX, Database.Student.SID};

            Cursor cursor = dh.read(Database.Student.NAME, columns, null, null, null, null, null, null);

            if (cursor.isBeforeFirst()) {
                while (cursor.moveToNext()) {

                    Student std = new Student();

                    String surname = cursor.getString(cursor.getColumnIndex(Database.Student.SURNAME));
                    String othername = cursor.getString(cursor.getColumnIndex(Database.Student.OTHERNAME));
                    String sex = cursor.getString(cursor.getColumnIndex(Database.Student.SEX));
                    String SID = cursor.getString(cursor.getColumnIndex(Database.Student.SID));

                    std.setSID(SID);
                    std.setSurname(surname);
                    std.setOthername(othername);
                    std.setSex(sex);

                    studentArrayList.add(std);
                    adapter.notifyDataSetChanged();
                }
            }
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
