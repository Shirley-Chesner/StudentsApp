package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.StudentsList;

public class EditStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Student student = (Student) getIntent().getSerializableExtra("student");
        StudentsList studentsList =  StudentsList.instance();

        EditText nameEt = findViewById(R.id.editStudent_name_input);
        EditText idEt = findViewById(R.id.editStudent_id_input);
        EditText phoneEt = findViewById(R.id.editStudent_phone_input);
        EditText addressEt = findViewById(R.id.editStudent_address_input);
        CheckBox checkedCb = findViewById(R.id.editStudent_checkBox);
        ImageView avatarIv = findViewById(R.id.editStudent_image);

        nameEt.setText(student.getName());
        idEt.setText(student.getId());
        phoneEt.setText(student.getPhone());
        addressEt.setText(student.getAddress());
        checkedCb.setChecked(student.isChecked());
        avatarIv.setImageResource(R.drawable.avatar_male);

        Button cancelBtn = findViewById(R.id.editStudent_cancel_btn);
        cancelBtn.setOnClickListener(view -> finish());

        Button deleteBtn = findViewById(R.id.editStudent_delete_btn);
        deleteBtn.setOnClickListener(view -> {
            studentsList.removeStudentById(student.getId());
            finish();
        });

        Button saveBtn = findViewById(R.id.editStudent_save_btn);
        saveBtn.setOnClickListener(view -> {
            Student updatedStudent = new Student(nameEt, idEt, phoneEt, addressEt, checkedCb);
            studentsList.editStudent(student.getId(), updatedStudent);

            finish();
        });
    }
}