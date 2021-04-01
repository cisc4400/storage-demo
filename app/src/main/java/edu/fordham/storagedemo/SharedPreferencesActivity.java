package edu.fordham.storagedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class SharedPreferencesActivity extends AppCompatActivity {

    TextView keyValueTextView;
    EditText keyEditText;
    EditText valueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        keyValueTextView = findViewById(R.id.kvTextView);
        keyEditText = findViewById(R.id.keyEditText);
        valueEditText = findViewById(R.id.valueEditText);

        updateList();
    }

    void updateList() {
        SharedPreferences sharedPref =
                getSharedPreferences("name_of_the_shared_preferences_file", Context.MODE_PRIVATE);
        Map<String, ?> pairs = sharedPref.getAll();
        String str = "";
        for (String k : pairs.keySet()) {
            str += k + " : " + pairs.get(k) + "\n";
        }
        keyValueTextView.setText(str);
    }

    public void saveKeyValuePair(View view) {
        String key = keyEditText.getText().toString();
        String value = valueEditText.getText().toString();

        SharedPreferences sharedPref =
                getSharedPreferences("name_of_the_shared_preferences_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();

        updateList();
    }
}