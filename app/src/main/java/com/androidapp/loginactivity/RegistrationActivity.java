package com.androidapp.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    private EditText eRegName;
    private EditText eRegPassword;
    private Button eRegButton;

    public static Credentials credentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        eRegName = findViewById(R.id.etRegName);
        eRegPassword = findViewById(R.id.etRegPassword);
        eRegButton = findViewById(R.id.btnRegister);

        /* ver E04 */
        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        eRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regUsername = eRegName.getText().toString();
                String regPassword = eRegPassword.getText().toString();

                if(validate(regUsername, regPassword)){
                    credentials = new Credentials(regUsername, regPassword);

                    /* Store the credentials as KEY-Value Pair */
                    sharedPreferencesEditor.putString("Username", regUsername);
                    sharedPreferencesEditor.putString("Password", regPassword);
                     /*Commits the changes and adds them to the file*/
                    sharedPreferencesEditor.apply();

                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private boolean validate(String username, String password){

        if(username.isEmpty() || password.length()<8){
            Toast.makeText(this, "Invalid input! Password must be atleast 8 characters long!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
