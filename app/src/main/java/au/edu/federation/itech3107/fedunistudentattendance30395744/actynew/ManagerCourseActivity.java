package au.edu.federation.itech3107.fedunistudentattendance30395744.actynew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import java.util.List;

import au.edu.federation.itech3107.fedunistudentattendance30395744.R;
import au.edu.federation.itech3107.fedunistudentattendance30395744.adapter.CourseAdapter;
import au.edu.federation.itech3107.fedunistudentattendance30395744.bean.Course;
import au.edu.federation.itech3107.fedunistudentattendance30395744.bean.StudentCourse;

public class ManagerCourseActivity extends AppCompatActivity {

    List<Course> courseList;
    CourseAdapter adapter;
    GridView gv;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_course);
        // Query all Course objects in the database using the LitePal ORM library and store the results in courseList
        courseList = LitePal.findAll(Course.class);
        adapter = new CourseAdapter(courseList, this);
        gv = findViewById(R.id.gv);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flag = position;
                Toast.makeText(ManagerCourseActivity.this, "The course you have currently selected is ：" + courseList.get(flag).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ManagerCourseActivity.this, CheckStudentActivity.class);
                intent.putExtra("title", courseList.get(flag).getTitle());
                intent.putExtra("stime", courseList.get(flag).getStartDate());
                startActivity(intent);
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view2 = View.inflate(ManagerCourseActivity.this, R.layout.activity_dialog, null);
                final EditText title = (EditText) view2.findViewById(R.id.title);
                final EditText teacher = (EditText) view2.findViewById(R.id.teacher);
                final DatePicker time1 = (DatePicker) view2.findViewById(R.id.time1);
                final DatePicker time2 = (DatePicker) view2.findViewById(R.id.time2);
                AlertDialog.Builder builder = new AlertDialog.Builder(ManagerCourseActivity.this);
                builder.setTitle("Enter course information").setIcon(android.R.drawable.ic_dialog_info).setView(view2)
                        // Set the cancel button of the dialog box. Click the Cancel button to close the dialog box.
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                // Set the OK button of the dialog box. When the OK button is clicked, execute the following code block.
                builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String text = title.getText().toString();
                        String text2 = teacher.getText().toString();
                        String text3 = time1.getYear() + "-" + (time1.getMonth() + 1) + "-" + time1.getDayOfMonth();
                        String text4 = time2.getYear() + "-" + (time2.getMonth() + 1) + "-" + time2.getDayOfMonth();

                        Course course = new Course(text, text2, text3, text4);
                        course.save();
                        courseList.add(course);
                        adapter.setCourses(courseList);
                        adapter.notifyDataSetChanged();
                        // Displays a Toast message notifying the user that the course was successfully added.
                        Toast.makeText(ManagerCourseActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                dialog = new AlertDialog.Builder(ManagerCourseActivity.this)
                        .setTitle("请问")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("are you sure？")
                        .setNegativeButton("cancel", null)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LitePal.deleteAll(Course.class, "title = ?", courseList.get(flag).getTitle());
                                LitePal.deleteAll(StudentCourse.class, "title = ?", courseList.get(flag).getTitle());
                                courseList.remove(flag);
                                adapter.setCourses(courseList);
                                adapter.notifyDataSetChanged();

                            }
                        })
                        .create();
                dialog.show();

            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view2 = View.inflate(ManagerCourseActivity.this, R.layout.activity_dialog, null);
                final EditText title = (EditText) view2.findViewById(R.id.title);
                final EditText teacher = (EditText) view2.findViewById(R.id.teacher);
                final DatePicker time1 = (DatePicker) view2.findViewById(R.id.time1);
                final DatePicker time2 = (DatePicker) view2.findViewById(R.id.time2);
                title.setText(courseList.get(flag).getTitle());
                title.setEnabled(false);
                teacher.setText(courseList.get(flag).getTeacher());
                String[] split = courseList.get(flag).getStartDate().split("-");
                String[] split2 = courseList.get(flag).getEndDate().split("-");
                time1.updateDate(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                time2.updateDate(Integer.parseInt(split2[0]), Integer.parseInt(split2[1]), Integer.parseInt(split2[2]));


                AlertDialog.Builder _builder = new AlertDialog.Builder(ManagerCourseActivity.this);
                _builder.setTitle("Enter course information").setIcon(android.R.drawable.ic_dialog_info).setView(view2)
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                _builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Course course = new Course();
                        course.setTeacher(teacher.getText().toString());
                        course.setStartDate(time1.getYear() + "-" + (time1.getMonth() + 1) + "-" + time1.getDayOfMonth());
                        course.setEndDate(time2.getYear() + "-" + (time2.getMonth() + 1) + "-" + time2.getDayOfMonth());
                        course.update(courseList.get(flag).getId());

                        Toast.makeText(ManagerCourseActivity.this, "Successfully modified", Toast.LENGTH_SHORT).show();
                    }
                });
                _builder.show();
            }
        });
    }
}
