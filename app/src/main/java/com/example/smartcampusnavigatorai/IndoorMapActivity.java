package com.example.smartcampusnavigatorai;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

public class IndoorMapActivity extends AppCompatActivity {

    private ImageView ivIndoorMap;
    private TextView tvRoomInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor_map);

        ivIndoorMap = findViewById(R.id.ivIndoorMap);
        tvRoomInfo = findViewById(R.id.tvRoomInfo);
        TabLayout tabFloorSelector = findViewById(R.id.tabFloorSelector);

        // Load ground floor by default
        loadFloorData("Ground Floor");

        tabFloorSelector.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText() != null) {
                    loadFloorData(tab.getText().toString());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void loadFloorData(String floorName) {
        // In a complete implementation, this would load a specific SVG/Image or trigger AR Navigation
        tvRoomInfo.setText("Showing: " + floorName + "\nFeatures: Classrooms, Labs, Emergency Exits, Washrooms.");
        
        switch(floorName) {
            case "Ground Floor":
                ivIndoorMap.setImageResource(R.drawable.floor_ground);
                break;
            case "First Floor":
                ivIndoorMap.setImageResource(R.drawable.floor_first);
                break;
            case "Second Floor":
                ivIndoorMap.setImageResource(R.drawable.floor_second);
                break;
        }
    }
}
