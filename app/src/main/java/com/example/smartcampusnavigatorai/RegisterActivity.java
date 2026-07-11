package com.example.smartcampusnavigatorai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etRegName, etRegEmail, etRegPassword;
    private Button btnRegister;
    private TextView tvGoToLogin;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        etRegName = findViewById(R.id.etRegName);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        btnRegister.setOnClickListener(v -> {
            String name = etRegName.getText().toString().trim();
            String email = etRegEmail.getText().toString().trim();
            String password = etRegPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Store user in database
                            String userId = mAuth.getCurrentUser().getUid();
                            User user = new User(userId, name, email);
                            mDatabase.child(userId).setValue(user)
                                .addOnCompleteListener(dbTask -> {
                                    if (dbTask.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        } else {
                            String errorMsg = task.getException() != null ? task.getException().getMessage() : "Registration Failed.";
                            Toast.makeText(RegisterActivity.this, errorMsg, Toast.LENGTH_LONG).show();
                        }
                    });
        });

        tvGoToLogin.setOnClickListener(v -> {
            finish(); // Go back to login
        });
    }
}
