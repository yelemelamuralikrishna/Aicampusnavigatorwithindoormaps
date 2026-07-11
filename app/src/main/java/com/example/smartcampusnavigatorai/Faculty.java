package com.example.smartcampusnavigatorai;

public class Faculty {
    private String name;
    private String department;
    private String cabin;

    public Faculty() {}

    public Faculty(String name, String department, String cabin) {
        this.name = name;
        this.department = department;
        this.cabin = cabin;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getCabin() { return cabin; }
}
