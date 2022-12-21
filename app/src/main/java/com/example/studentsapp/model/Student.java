package com.example.studentsapp.model;

import android.widget.CheckBox;
import android.widget.EditText;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private String phone;
    private String address;
    private boolean isChecked;

    public Student(String name, String id, String phone, String address, boolean isChecked) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.isChecked = isChecked;
    }

    public Student(EditText name, EditText id, EditText phone, EditText address, CheckBox cb) {
        this(
            name.getText().toString(),
            id.getText().toString(),
            phone.getText().toString(),
            address.getText().toString(),
            cb.isChecked()
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
