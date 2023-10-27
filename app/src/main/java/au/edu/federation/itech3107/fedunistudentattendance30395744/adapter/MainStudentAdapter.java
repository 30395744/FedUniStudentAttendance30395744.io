package au.edu.federation.itech3107.fedunistudentattendance30395744.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import au.edu.federation.itech3107.fedunistudentattendance30395744.R;
import au.edu.federation.itech3107.fedunistudentattendance30395744.bean.StudentCourse;

public class MainStudentAdapter extends RecyclerView.Adapter<MainStudentAdapter.ViewHolder> {
    List<StudentCourse> studentCourses;
    Context context;

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public MainStudentAdapter(List<StudentCourse> studentCourses, Context context) {
        this.studentCourses = studentCourses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.main_student_item,parent,false);
        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.sid.setText(studentCourses.get(position).getsId());
        holder.sname.setText(studentCourses.get(position).getsName());

        if (studentCourses.get(position).getsCheck()==1)
            holder.itemView.setBackgroundColor(Color.rgb(0,255,0));

        else
        holder.itemView.setBackgroundColor(Color.rgb(255,0,0));

    }



    @Override
    public int getItemCount() {
        return studentCourses.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder {
        TextView sname;
        TextView sid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sname=itemView.findViewById(R.id.sname);
            sid=itemView.findViewById(R.id.sid);

        }
    }
}
