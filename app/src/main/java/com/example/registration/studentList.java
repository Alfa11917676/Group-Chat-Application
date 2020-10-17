package com.example.registration;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class studentList extends ArrayAdapter<students> {

    private Activity context;
    private List<students>studentnamelist;

    public studentList(Activity context,List<students>studentnamelist){
        super(context,R.layout.layout_manager,studentnamelist);
        this.context=context;
        this.studentnamelist=studentnamelist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewNames  =inflater.inflate(R.layout.layout_manager,null,true);

        TextView textViewName=listViewNames.findViewById(R.id.student);

        TextView textSpinner=listViewNames.findViewById(R.id.section);

        students student=studentnamelist.get(position);
        textViewName.setText(student.getStudentName());

        textSpinner.setText(student.getStudentSection());
        return  listViewNames;
    }
}
