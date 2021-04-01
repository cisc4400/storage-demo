package edu.fordham.storagedemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {

    EditText fileContentEditText;
    EditText fileNameEditText;
    TextView fileListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        fileContentEditText = findViewById(R.id.fileContentEditText);
        fileNameEditText = findViewById(R.id.fileNameEditText);
        fileListTextView = findViewById(R.id.fileListTextView);

        updateFileList();
    }

    void updateFileList() {
        String fileList = "";
        String[] files = fileList();
        for (String f : files) {
            fileList += f + "\n";
        }
        fileListTextView.setText(fileList);
    }

    public void saveFile(View view) throws IOException {
        String fileName = fileNameEditText.getText().toString();
        String fileContent = fileContentEditText.getText().toString();

        FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
        fos.write(fileContent.getBytes());

        updateFileList();
    }

    public void deleteFile(View view) {
        String fileName = fileNameEditText.getText().toString();

        File file = new File(getFilesDir(), fileName);
        file.delete();

        updateFileList();
    }
}