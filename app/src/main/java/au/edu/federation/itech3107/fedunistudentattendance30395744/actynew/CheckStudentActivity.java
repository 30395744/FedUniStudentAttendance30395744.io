package au.edu.federation.itech3107.fedunistudentattendance30395744.actynew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import au.edu.federation.itech3107.fedunistudentattendance30395744.R;
import au.edu.federation.itech3107.fedunistudentattendance30395744.adapter.CheckStudentAdapter;
import au.edu.federation.itech3107.fedunistudentattendance30395744.bean.Kaoqin;
import au.edu.federation.itech3107.fedunistudentattendance30395744.bean.StudentCourse;

public class CheckStudentActivity extends AppCompatActivity {
    public static String studentCourse = "";
    private String stime = "";
    CheckStudentAdapter checkStudentAdapter;
    RecyclerView over_student;
    List<String> allCourse;
    List<StudentCourse> studentCourseList;
    Spinner sp;
    String kqtime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_student);
        studentCourse = getIntent().getStringExtra("title");
        stime = getIntent().getStringExtra("stime");
        initView();

        initRecycle();
        flush();
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentCourseList = checkStudentAdapter.getStudentCourses();
                // If the course is already selected, create a Kaoqin object and set the relevant properties, then save to the database.
                for (StudentCourse s :
                        studentCourseList) {

                    if (s.getsCheck() == 1) {
                        Kaoqin kaoqin = new Kaoqin();
                        kaoqin.setCname(studentCourse);
                        kaoqin.setSname(s.getsName());
                        kaoqin.setKqtime(kqtime);
                        kaoqin.save();
                    }
                }
                // Check Kaoqin data according to course name, exam time and student name.
                for (StudentCourse course : studentCourseList) {
                    List<Kaoqin> kaoqins = LitePal.where("cname=? and kqtime=? and sname=?", studentCourse, kqtime, course.getsName()).find(Kaoqin.class);
                    // If the query result is greater than 0, the course is marked as selected; Otherwise marked as unselected
                    if (kaoqins.size() > 0) {
                        course.setsCheck(1);
                    } else {
                        course.setsCheck(0);
                    }
                }

                checkStudentAdapter.notifyDataSetChanged();
                Toast.makeText(CheckStudentActivity.this, "Recording Success ！", Toast.LENGTH_SHORT).show();

            }
        });
        findViewById(R.id.flush).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flush();
            }
        });
    }
    // Define the flush method to query the student course list from the database and update the adapter.
    private void flush() {
        studentCourseList = LitePal.where("title=?", studentCourse).find(StudentCourse.class);
        checkStudentAdapter.setStudentCourses(studentCourseList);
        checkStudentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        flush();

        sp = findViewById(R.id.sp);
        List<String> list = new ArrayList<>();
        SimpleDateFormat DateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        //当前日期的七天后的日期
        Calendar mCalendar = Calendar.getInstance();
        try {
            mCalendar.setTime(DateFormat.parse(stime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mCalendar.set(Calendar.DATE, mCalendar.get(Calendar.DATE));

        Date SevenLaterTime = mCalendar.getTime();
        String timInfo2 = DateFormat.format(SevenLaterTime);
        list.add(timInfo2);
        for (int i = 1; i < 12; i++) {
            // Loop to add the date string for the next twelve weeks to the list (starting with the current date)
            mCalendar.set(Calendar.DATE, mCalendar.get(Calendar.DATE) + 7);// Set the date in Calendar to the week following the current date (add one week per cycle)
            Date SevenLaterTime2 = mCalendar.getTime();
            String timInfo22 = DateFormat.format(SevenLaterTime2);
            list.add(timInfo22);
        }
        // Create an ArrayAdapter instance to display the list data in the Spinner control (create the drop-down list using the specified layout file)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(CheckStudentActivity.this, R.layout.support_simple_spinner_dropdown_item, list);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kqtime = list.get(i);


                for (StudentCourse course : studentCourseList) {
                    List<Kaoqin> kaoqins = LitePal.where("cname=? and kqtime=? and sname=?", studentCourse, kqtime, course.getsName()).find(Kaoqin.class);
                    // If the data that meets the criteria is found
                    // the check status for the course is set to 1
                    if (kaoqins.size() > 0) {
                        course.setsCheck(1);
                    } else {
                        course.setsCheck(0);
                    }
                }
                checkStudentAdapter.notifyDataSetChanged();
            }
            // This method is called when the user does not select any items on the adapter. The passed parameter is the adapter view.
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    // Initialize the adapter for RecyclerView.
    // This adapter will be used to display the data in RecyclerView. The data source is the student course list
    private void initRecycle() {
        checkStudentAdapter = new CheckStudentAdapter(studentCourseList, this);
        over_student.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        over_student.setAdapter(checkStudentAdapter);
    }


    private void initView() {

        over_student = findViewById(R.id.over_student);
        Button addstudent = findViewById(R.id.addstudent);
        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckStudentActivity.this, SetStudentCourseActivity.class);
                intent.putExtra("title", studentCourse);
                startActivity(intent);
            }
        });
    }

}
