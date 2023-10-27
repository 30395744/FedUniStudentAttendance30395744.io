package au.edu.federation.itech3107.fedunistudentattendance30395744.actynew;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class SetStudentCourseActivity extends AppCompatActivity {
    List<Course> courseList;
    CourseAdapter adapter;
    GridView gv;
    int flag = 0;
    EditText student, studentName, studentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_student_course);
        String title = getIntent().getStringExtra("title");
        student = findViewById(R.id.student);
        studentName = findViewById(R.id.studentName);
        studentClass = findViewById(R.id.studentClass);
        courseList = LitePal.findAll(Course.class);
        adapter = new CourseAdapter(courseList, this);
        gv = findViewById(R.id.gv);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flag = position;
                Toast.makeText(SetStudentCourseActivity.this, "Your current course selection is:" + courseList.get(flag).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create StudentCourse objects as instructed and save them to the database
                StudentCourse studentCourse = new StudentCourse(title, student.getText().toString(),
                        // Call the save method of the StudentCourse object to save the file.
                        // After the file is saved successfully, a message is displayed
                        studentName.getText().toString(), 0, studentClass.getText().toString());
                studentCourse.save();
                Toast.makeText(SetStudentCourseActivity.this, "The operation was successful.", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
