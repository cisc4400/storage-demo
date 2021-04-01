package edu.fordham.storagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import edu.fordham.storagedemo.db.AppDatabase;
import edu.fordham.storagedemo.db.User;
import edu.fordham.storagedemo.db.UserDao;

public class DatabaseActivity extends AppCompatActivity {

    TextView usersTextView;
    EditText firstNameEditText;
    EditText lastNameEditText;
    AppDatabase database;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        usersTextView = findViewById(R.id.usersTextView);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);

        database = AppDatabase.getDatabase(this);
        userDao = database.userDao();
    }

    void updateUsers() {
        List<User> users = userDao.getAll();
        String str = "";
        for (User u : users) {
            str += u.lastName + ", " + u.firstName + "\n";
        }
        usersTextView.setText(str);
    }

    public void saveUser(View view) {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();

        User u = new User();
        u.firstName = firstName;
        u.lastName = lastName;
        userDao.insert(u);

        updateUsers();
    }
}