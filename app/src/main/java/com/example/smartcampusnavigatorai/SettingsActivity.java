package com.example.smartcampusnavigatorai;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;

public class SettingsActivity extends AppCompatActivity {

    private MaterialSwitch switchNotifications, switchLocation, switchVoice;
    private MaterialButton btnSaveSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchNotifications = findViewById(R.id.switchNotifications);
        switchLocation = findViewById(R.id.switchLocation);
        switchVoice = findViewById(R.id.switchVoice);
        btnSaveSettings = findViewById(R.id.btnSaveSettings);

        // Load saved preferences here if applicable (using SharedPreferences)
        switchNotifications.setChecked(true);
        switchLocation.setChecked(true);
        switchVoice.setChecked(true);

        btnSaveSettings.setOnClickListener(v -> {
            // Save preferences logic here
            Toast.makeText(this, "Settings Saved!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
