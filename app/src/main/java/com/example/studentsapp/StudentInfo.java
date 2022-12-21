package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentsapp.model.Student;

public class StudentInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Student student = (Student) getIntent().getSerializableExtra("student");

        TextView name = findViewById(R.id.studentInfo_name_text);
        TextView id = findViewById(R.id.studentInfo_id_text);
        TextView phone = findViewById(R.id.studentInfo_phone_text);
        TextView address = findViewById(R.id.studentInfo_address_text);
        CheckBox checkBox = findViewById(R.id.studentInfo_checkBox);
        ImageView img = findViewById(R.id.studentInfo_image);

        name.setText(student.getName());
        id.setText(student.getId());
        phone.setText(student.getPhone());
        address.setText(student.getAddress());
        checkBox.setChecked(student.isChecked());
        img.setImageResource(R.drawable.avatar_male);

        Button goBackBtn = findViewById(R.id.studentInfo_go_back_btn);
        goBackBtn.setOnClickListener(view -> finish());

        Button editBtn = findViewById(R.id.studentInfo_edit_btn);
        editBtn.setOnClickListener(view -> {
            Intent EditStudentIndent = new Intent(this, EditStudent.class);
            EditStudentIndent.putExtra("student", student);
            startActivity(EditStudentIndent);
            finish();
        });
    }
}