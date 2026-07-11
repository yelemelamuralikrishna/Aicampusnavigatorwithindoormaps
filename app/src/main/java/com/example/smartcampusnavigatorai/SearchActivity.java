package com.example.smartcampusnavigatorai;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SearchView searchView = findViewById(R.id.searchViewGlobal);
        // Using a basic ListView for simplicity since we don't have a complex item layout for global search yet
        // In a real implementation you would replace the RecyclerView in XML with a ListView or use an adapter
        
        locationList = new ArrayList<>(Arrays.asList(
                "Main Library", "CS Department", "Mechanical Lab", "Cafeteria",
                "Administration Block", "Auditorium", "Sports Complex", "Student Center"
        ));

        // Note: The XML currently has a RecyclerView, we should bind it with a simple adapter
        // For the sake of scaffolding, we'll just set up the SearchView listener.
        
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchActivity.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter logic would go here
                return false;
            }
        });
    }
}
