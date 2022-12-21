package com.example.studentsapp.model;

import java.util.LinkedList;
import java.util.List;

public class StudentsList {
    private List<Student> data = new LinkedList<>();

    private static final StudentsList _instance = new StudentsList();

    public static StudentsList instance(){
        return _instance;
    }

    private StudentsList(){
        for(int i=0;i<2;i++){
            addStudent(new Student("name " + i," " +i,"1","hello", false));
        }
    }

    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student st){
        data.add(st);
    }

    public void editStudent(String oldId, Student updatedStudent) {
        findStudentByIdAndDoSomething(oldId, index -> data.set(index, updatedStudent));
    }

    public void removeStudentById(String id) {
        findStudentByIdAndDoSomething(id, index -> data.remove(index));
    }

    interface StudentFunc {
        void action(int index);
    }

    private void findStudentByIdAndDoSomething(String id, StudentFunc func) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(id)) {
                func.action(i);
                break;
            }
        }
    }
}
