package au.edu.federation.itech3107.fedunistudentattendance30395744.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import au.edu.federation.itech3107.fedunistudentattendance30395744.R;
import au.edu.federation.itech3107.fedunistudentattendance30395744.bean.StudentCourse;

public class CheckStudentAdapter extends RecyclerView.Adapter<CheckStudentAdapter.ViewHolder> {
    List<StudentCourse> studentCourses;
    Context context;

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public CheckStudentAdapter(List<StudentCourse> studentCourses, Context context) {
        this.studentCourses = studentCourses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.check_student_item,parent,false);
        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sid.setText(studentCourses.get(position).getsId());
        holder.sname.setText(studentCourses.get(position).getsName());
        // Set the corresponding status text according to the student's Attendance status,
        // attendance is "attendance" and non-attendance is "non-attendance".
        if (studentCourses.get(position).getsCheck()==1){
            holder.state.setText("Attendance");
        }else {
            holder.state.setText("non-attendance");
        }
        // Set the corresponding CheckBox status based on the student's attendance status.
        // The attendance status is selected, and the non-attendance status is not selected
        if (studentCourses.get(position).getsCheck()==1)
            holder.cb.setChecked(true);
        else
            holder.cb.setChecked(false);
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    // If CheckBox is selected, the student's attendance status is set to present
                    studentCourses.get(holder.getAdapterPosition()).setsCheck(1);
                else
                    studentCourses.get(holder.getAdapterPosition()).setsCheck(0);
            }
        });
    }



    @Override
    public int getItemCount() {
        return studentCourses.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder {
        TextView sname;
        TextView sid;
        TextView state;
        CheckBox cb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sname=itemView.findViewById(R.id.sname);
            sid=itemView.findViewById(R.id.sid);
            state=itemView.findViewById(R.id.state);
            cb=itemView.findViewById(R.id.cb);
        }
    }
}
