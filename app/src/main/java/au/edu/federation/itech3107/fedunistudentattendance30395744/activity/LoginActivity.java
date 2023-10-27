package au.edu.federation.itech3107.fedunistudentattendance30395744.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

import au.edu.federation.itech3107.fedunistudentattendance30395744.Application;
import au.edu.federation.itech3107.fedunistudentattendance30395744.R;
import au.edu.federation.itech3107.fedunistudentattendance30395744.actynew.ManagerCourseActivity;
import au.edu.federation.itech3107.fedunistudentattendance30395744.bean.Teacher;

/**
 * 登录
 */
// Define an Activity named LoginActivity, inherited from AppCompatActivity,
// that implements the Viet.onClickListener interface to handle button clicks
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText et_account, et_password;
    MaterialButton btn_login, btn_reg;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Call the getDatabase method of the Connector class to start connecting to the database.
        // Any database operation will create the bookshop.db database
        Connector.getDatabase();
        // Bind the control using the findViewById method to return the corresponding control object
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_reg = findViewById(R.id.btn_reg);
        btn_login.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
        intent = getIntent();
        et_account.setText(intent.getStringExtra("account"));
        et_password.setText(intent.getStringExtra("password"));


    }
    // Overrides the onClick method of the View.OnClickListener interface,
    // which is called when a button is clicked
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                // Get the account and password entered by the user in the input box.
                String StrAccount = et_account.getText().toString();
                String StrPassword = et_password.getText().toString();
                if (StrAccount.isEmpty() || StrPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Input information is not perfect!", Toast.LENGTH_SHORT).show();
                } else {
// Use LitePal to check if there is a matching teacher record in the database.
// The query condition is that teacherId and teacherPassword match the entered account and password.
                    List<Teacher> teachers = LitePal.where("teacherId=? and teacherPassword=?", StrAccount, StrPassword).find(Teacher.class);
                    // If a matching teacher record is found, get the record's teacherId and save it to the loginAccount variable in the Application.
                    // Then jump to ManagerCourseActivity.
                    if (teachers.size() > 0) {
                        Application.loginAccount = teachers.get(0).getTeacherId();
                        intent = new Intent(LoginActivity.this, ManagerCourseActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect account number or password！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_reg:
                //注册按钮点击事件
                intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;
        }
    }
}
