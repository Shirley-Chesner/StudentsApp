package com.example.studentsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.StudentsList;
import com.example.studentsapp.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Student> studentList;
    StudentRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentList = StudentsList.instance().getAllStudents();
        RecyclerView list = findViewById(R.id.main_student_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentRecyclerAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(pos -> {
            Log.d("TAG", "Row was clicked " + pos);
            Intent studentIntent = new Intent(MainActivity.this, StudentInfo.class);
            studentIntent.putExtra("student", studentList.get(pos));
            startActivity(studentIntent);
        });

        Button createNewStudentBtn = findViewById(R.id.main_add_student_btn);
        createNewStudentBtn.setOnClickListener(view ->
                startActivity(new Intent(this, AddStudent.class)));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            studentList = StudentsList.instance().getAllStudents();
            adapter.notifyDataSetChanged();
        }
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox cb;
        public StudentViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentList_name);
            idTv = itemView.findViewById(R.id.studentList_id);
            cb = itemView.findViewById(R.id.studentList_cb);

            cb.setOnClickListener(view -> {
                int pos = (int)cb.getTag();
                Student st = studentList.get(pos);
                st.setChecked(cb.isChecked());
            });
            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                listener.onItemClick(pos);
            });
        }

        public void bind(Student st, int pos) {
            nameTv.setText(st.getName());
            idTv.setText(st.getId());
            cb.setChecked(st.isChecked());
            cb.setTag(pos);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{
        OnItemClickListener listener;
        void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_row,parent,false);
            return new StudentViewHolder(view,listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = studentList.get(position);
            holder.bind(st,position);
        }

        @Override
        public int getItemCount() {
            return studentList.size();
        }
    }
}