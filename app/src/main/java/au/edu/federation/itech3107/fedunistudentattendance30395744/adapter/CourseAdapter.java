package au.edu.federation.itech3107.fedunistudentattendance30395744.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import au.edu.federation.itech3107.fedunistudentattendance30395744.R;
import au.edu.federation.itech3107.fedunistudentattendance30395744.bean.Course;

public class CourseAdapter extends BaseAdapter {
    List<Course> courses = new ArrayList<>();

    Context context;

    public CourseAdapter(List<Course> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.course_item, null);
        try {
            TextView title = view.findViewById(R.id.title);
            TextView teacher = view.findViewById(R.id.teacher);
            TextView time1 = view.findViewById(R.id.time1);
            TextView time2 = view.findViewById(R.id.time2);
            title.setText(courses.get(position).getTitle());
            teacher.setText(courses.get(position).getTeacher());
            time1.setText(courses.get(position).getStartDate());
//            time2.setText(courses.get(position).getEndDate());

        } catch (Exception e) {
            Toast.makeText(context, "It's the end of the line.", Toast.LENGTH_SHORT).show();

        }
        return view;
    }
}
