package com.example.david.may2018lect1;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 9/11/2018.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.MyViewHolder> {

    ArrayList<Student> studentsList;

    public StudentsAdapter(ArrayList<Student> studentsList){
        this.studentsList = studentsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvSID, tvSex, tvName;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvSID = (TextView)itemView.findViewById(R.id.tvSID);
            tvSex = (TextView)itemView.findViewById(R.id.tvSex);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Student student = studentsList.get(position);
        holder.tvSex.setText(student.getSex());
        holder.tvSID.setText(student.getSID());
        holder.tvName.setText(student.getSurname() + " " + student.getOthername());

        /*
        TextView sid = (TextView) holder.itemView.findViewById(R.id.tvSID);
        sid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSID = studentsList.get(position).getSID();
                String strSex =   studentsList.get(position).getSex();
                String strSurName =  studentsList.get(position).getSurname();
                String strOtherName = studentsList.get(position).getOthername();


                Intent intent = new Intent(v.getContext(), ViewSingleStudent.class);

                intent.putExtra("sid", strSID);
                intent.putExtra("sex", strSex);
                intent.putExtra("surname", strSurName);
                intent.putExtra("othername", strOtherName);

                v.getContext().startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return studentsList.size();
    }
}
