package com.dungnt.thuc_hanh_03.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dungnt.thuc_hanh_03.R;
import com.dungnt.thuc_hanh_03.activity.StudentDetails;
import com.dungnt.thuc_hanh_03.entity.Fullname;
import com.dungnt.thuc_hanh_03.entity.Student;

import java.util.List;

public class StudentAdapter  extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    private List<Student> studentList;
    private Context context;

    public StudentAdapter(
            Context context,
            List<Student> studentList
    ) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.tvId.setText(student.getId());
        holder.tvFullName.setText(student.getFullnameStudent());
        holder.tvGpa.setText(String.valueOf(student.getGpa()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StudentDetails.class);
            intent.putExtra("studentInfo", student);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvFullName, tvGpa, tvId;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvGpa = itemView.findViewById(R.id.tvGpa);
        }
    }
}
