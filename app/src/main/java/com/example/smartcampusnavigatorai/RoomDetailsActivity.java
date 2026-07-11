package com.example.smartcampusnavigatorai;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class RoomDetailsActivity extends AppCompatActivity {

    private TextView tvRoomName, tvRoomNumber, tvRoomType, tvRoomFloor;
    private MaterialButton btnNavigateRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        tvRoomName = findViewById(R.id.tvRoomName);
        tvRoomNumber = findViewById(R.id.tvRoomNumber);
        tvRoomType = findViewById(R.id.tvRoomType);
        tvRoomFloor = findViewById(R.id.tvRoomFloor);
        btnNavigateRoom = findViewById(R.id.btnNavigateRoom);

        // Normally, you would extract intent extras here to dynamically populate this
        // e.g., String roomName = getIntent().getStringExtra("room_name");
        
        btnNavigateRoom.setOnClickListener(v -> {
            Toast.makeText(this, "Calculating route to " + tvRoomName.getText(), Toast.LENGTH_SHORT).show();
            // Start navigation logic
        });
    }
}
