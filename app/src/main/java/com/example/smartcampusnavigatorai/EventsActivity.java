package com.example.smartcampusnavigatorai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    private RecyclerView rvEvents;
    private EventsAdapter adapter;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        rvEvents = findViewById(R.id.rvEvents);
        rvEvents.setLayoutManager(new LinearLayoutManager(this));

        // Load dummy events data
        loadEventsData();

        adapter = new EventsAdapter(eventList);
        rvEvents.setAdapter(adapter);
    }

    private void loadEventsData() {
        eventList = new ArrayList<>();
        eventList.add(new Event("Tech Symposium 2026", "Oct 15, 2026 - 10:00 AM", "Main Auditorium"));
        eventList.add(new Event("AI Workshop", "Oct 18, 2026 - 02:00 PM", "CS Lab 1"));
        eventList.add(new Event("Annual Sports Meet", "Oct 22, 2026 - 09:00 AM", "Sports Complex"));
        eventList.add(new Event("Cultural Fest", "Nov 05, 2026 - 06:00 PM", "Open Air Theatre"));
    }

    private class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

        private List<Event> list;

        EventsAdapter(List<Event> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
            return new EventViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
            Event event = list.get(position);
            holder.tvTitle.setText(event.getTitle());
            holder.tvDate.setText(event.getDate());
            holder.tvVenue.setText("Venue: " + event.getVenue());
            
            holder.btnNavigate.setOnClickListener(v -> {
                Toast.makeText(EventsActivity.this, "Navigating to " + event.getVenue(), Toast.LENGTH_SHORT).show();
                // In a real implementation, you would pass the venue to CampusMapActivity
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class EventViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDate, tvVenue;
            View btnNavigate;

            EventViewHolder(View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvEventTitle);
                tvDate = itemView.findViewById(R.id.tvEventDate);
                tvVenue = itemView.findViewById(R.id.tvEventVenue);
                btnNavigate = itemView.findViewById(R.id.btnNavigateEvent);
            }
        }
    }
}
