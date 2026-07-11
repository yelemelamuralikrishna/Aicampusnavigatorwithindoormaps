package com.example.smartcampusnavigatorai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FacultyActivity extends AppCompatActivity {

    private RecyclerView rvFaculty;
    private FacultyAdapter adapter;
    private List<Faculty> facultyList;
    private List<Faculty> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        rvFaculty = findViewById(R.id.rvFaculty);
        SearchView searchView = findViewById(R.id.searchViewFaculty);

        rvFaculty.setLayoutManager(new LinearLayoutManager(this));

        // Load dummy faculty data
        loadFacultyData();

        adapter = new FacultyAdapter(filteredList);
        rvFaculty.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void loadFacultyData() {
        facultyList = new ArrayList<>();
        // Creating placeholder objects. Make sure Faculty model supports these fields
        facultyList.add(new Faculty("Dr. Alice Smith", "Computer Science", "C-203"));
        facultyList.add(new Faculty("Prof. Bob Jones", "Electrical Eng", "E-105"));
        facultyList.add(new Faculty("Dr. Carol White", "Mechanical Eng", "M-402"));
        facultyList.add(new Faculty("Mr. David Brown", "Mathematics", "A-301"));
        
        filteredList = new ArrayList<>(facultyList);
    }

    private void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(facultyList);
        } else {
            text = text.toLowerCase();
            for (Faculty faculty : facultyList) {
                if (faculty.getName().toLowerCase().contains(text) || 
                    faculty.getDepartment().toLowerCase().contains(text)) {
                    filteredList.add(faculty);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    // Inner class adapter for simplicity
    private class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {

        private List<Faculty> list;

        FacultyAdapter(List<Faculty> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faculty, parent, false);
            return new FacultyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FacultyViewHolder holder, int position) {
            Faculty faculty = list.get(position);
            holder.tvName.setText(faculty.getName());
            holder.tvDept.setText(faculty.getDepartment());
            holder.tvCabin.setText("Cabin: " + faculty.getCabin());
            
            holder.btnNavigate.setOnClickListener(v -> {
                Toast.makeText(FacultyActivity.this, "Navigating to " + faculty.getCabin(), Toast.LENGTH_SHORT).show();
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class FacultyViewHolder extends RecyclerView.ViewHolder {
            TextView tvName, tvDept, tvCabin;
            View btnNavigate;

            FacultyViewHolder(View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tvFacultyName);
                tvDept = itemView.findViewById(R.id.tvFacultyDept);
                tvCabin = itemView.findViewById(R.id.tvFacultyCabin);
                btnNavigate = itemView.findViewById(R.id.btnNavigateFaculty);
            }
        }
    }
}
