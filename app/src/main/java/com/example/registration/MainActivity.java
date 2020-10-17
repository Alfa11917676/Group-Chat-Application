package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
public static final String student_name="studentname";
public static final  String student_id="studentID";
public static  final String student_roll="studentRoll";

EditText name;
Button button;
Spinner spin;
DatabaseReference databaseStudents;
ListView listView;
List<students>studentsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);

        button=findViewById(R.id.button);
        spin=findViewById(R.id.spin);
        databaseStudents= FirebaseDatabase.getInstance().getReference("students");
        listView=findViewById(R.id.listView);
        studentsList=new ArrayList<>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addStudent();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                students student=studentsList.get(position);

                Intent intent=new Intent(getApplicationContext(),addTrackActivity.class);
                    intent.putExtra(student_id,student.getStudentId());
//
                    startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentsList.clear();
                for(DataSnapshot studentSnapshot:dataSnapshot.getChildren()){
                    students student=studentSnapshot.getValue(students.class);

                    studentsList.add(student);
                }
                studentList adaptor=new studentList(MainActivity.this,studentsList);
                listView.setAdapter(adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addStudent() {
        String Name=name.getText().toString().trim();
                String Section=spin.getSelectedItem().toString();
        if(!TextUtils.isEmpty(Name)){
            String id=databaseStudents.push().getKey();

            students student=new students(id,Name,Section);
            databaseStudents.child(id).setValue(student);

            Toast.makeText(this, "Project Updated", Toast.LENGTH_SHORT).show();

        }else
            {
            Toast.makeText(this, "Enter something to add to the project", Toast.LENGTH_SHORT).show();
        }
    }
}