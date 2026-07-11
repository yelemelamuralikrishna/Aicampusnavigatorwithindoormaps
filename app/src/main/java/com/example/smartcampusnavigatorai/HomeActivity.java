package com.example.smartcampusnavigatorai;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MaterialCardView cardNavigate = findViewById(R.id.cardNavigate);
        MaterialCardView cardIndoor = findViewById(R.id.cardIndoor);
        MaterialCardView cardAi = findViewById(R.id.cardAi);
        MaterialCardView cardQR = findViewById(R.id.cardQR);

        if (cardNavigate != null) {
            cardNavigate.setOnClickListener(v -> {
                startActivity(new Intent(HomeActivity.this, CampusMapActivity.class));
            });
        }

        if (cardIndoor != null) {
            cardIndoor.setOnClickListener(v -> {
                startActivity(new Intent(HomeActivity.this, IndoorMapActivity.class));
            });
        }

        if (cardAi != null) {
            cardAi.setOnClickListener(v -> {
                startActivity(new Intent(HomeActivity.this, AIAssistantActivity.class));
            });
        }
        
        if (cardQR != null) {
            cardQR.setOnClickListener(v -> {
                startActivity(new Intent(HomeActivity.this, QRCodeScannerActivity.class));
            });
        }
    }
}
