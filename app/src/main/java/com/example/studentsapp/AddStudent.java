package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.StudentsList;

public class AddStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        StudentsList studentsList =  StudentsList.instance();

        EditText nameEt = findViewById(R.id.addStudent_name_input);
        EditText idEt = findViewById(R.id.addStudent_id_input);
        EditText phoneEt = findViewById(R.id.addStudent_phone_input);
        EditText addressEt = findViewById(R.id.addStudent_address_input);
        CheckBox checkedCb = findViewById(R.id.addStudent_checkBox);

        ImageView avatarIv = findViewById(R.id.addStudent_image);
        avatarIv.setImageResource(R.drawable.avatar_male);

        Button saveBtn = findViewById(R.id.addStudent_save_btn);
        saveBtn.setOnClickListener(view -> {
            studentsList.addStudent(new Student(nameEt, idEt, phoneEt, addressEt, checkedCb));
            finish();
        });

        Button cancelBtn = findViewById(R.id.addStudent_cancel_btn);
        cancelBtn.setOnClickListener(view -> finish());
    }
}