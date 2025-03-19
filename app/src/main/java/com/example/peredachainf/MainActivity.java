package com.example.peredachainf;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText subjectEditText;
    private EditText messageEditText;
    private Button sendEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        subjectEditText = findViewById(R.id.subjectEditText);
        messageEditText = findViewById(R.id.messageEditText);
        sendEmailButton = findViewById(R.id.sendEmailButton);

        final String username = "alec11nik@gmail.com";
        final String password = "smdq jaev uzvh zgwd";

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = emailEditText.getText().toString();
                String subject = subjectEditText.getText().toString();
                String textBody = messageEditText.getText().toString();

                new SendEmailTask().execute(username, password, to, subject, textBody);
            }
        });
    }

    private class SendEmailTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            String to = params[2];
            String subject = params[3];
            String textBody = params[4];

            Sender sender = new Sender();
            try {
                sender.sendEmail(username, password, to, textBody, subject);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(MainActivity.this, "Email sent successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Failed to send email.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}