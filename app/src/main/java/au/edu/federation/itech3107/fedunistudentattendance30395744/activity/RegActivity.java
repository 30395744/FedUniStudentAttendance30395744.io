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

import au.edu.federation.itech3107.fedunistudentattendance30395744.R;
import au.edu.federation.itech3107.fedunistudentattendance30395744.bean.Teacher;

/**
 * 注册
 */
public class RegActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText et_account, et_password, et_password2, et_teacherName;
    MaterialButton btn_return, btn_reg;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Locate the corresponding view from the layout file and assign the variable
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        et_password2 = findViewById(R.id.et_password2);
        et_teacherName = findViewById(R.id.et_teacherName);
        btn_return = findViewById(R.id.btn_return);
        btn_reg = findViewById(R.id.btn_reg);

        btn_return.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_return:
                finish();
                break;
            case R.id.btn_reg:
                // Get the account number, password and teacher name from the input box
                String StrAccount = et_account.getText().toString();
                String StrPassword = et_password.getText().toString();
                String StrPassword2 = et_password2.getText().toString();
                String StrTeacherName = et_teacherName.getText().toString();
                if (StrAccount.isEmpty() || StrPassword.isEmpty() || StrTeacherName.isEmpty()) {
                    // If the account, password, or teacher name input box is empty,
                    // a Toast message is displayed prompting the user to complete the input information.
                    Toast.makeText(RegActivity.this, "Input information is not perfect!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!StrPassword.equals(StrPassword2)){
                        // If the two passwords entered are inconsistent, a Toast message is
                        // displayed indicating that the two passwords entered are inconsistent.
                        Toast.makeText(RegActivity.this, "Inconsistency between two password entries", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Connector.getDatabase();
                    List<Teacher> teachers = LitePal.where("teacherId=?", StrAccount).find(Teacher.class);
                    if (teachers.size() > 0) {
                        // Look up the teacher information in the database according to the account entered.
                        // If the number of teacher information found is greater than 0,
                        // the account already exists. A Toast message is displayed indicating that the account already exists.
                        Toast.makeText(RegActivity.this, "The user already exists!", Toast.LENGTH_SHORT).show();
                    } else {
                        Teacher teacher = new Teacher(StrAccount, StrTeacherName, StrPassword);
                        teacher.save();
                        // Otherwise, create a new teacher object, save it to the database,
                        // and display a Toast message indicating that the user registered successfully.
                        Toast.makeText(RegActivity.this, "Successful registration！", Toast.LENGTH_SHORT).show();
                        intent = new Intent(RegActivity.this, LoginActivity.class);
                        intent.putExtra("account", StrAccount);
                        intent.putExtra("password", StrPassword);
                        startActivity(intent);
                        finish();
                    }

                }

                break;
        }
    }
}
