package com.mirego.sherbook.viewdatas;

import android.content.Context;

import com.mirego.sherbook.models.Faculty;

public class FacultyViewData {

    private final Faculty faculty;
    private Context context;

    public FacultyViewData(Faculty faculty, Context context) {
        this.faculty = faculty;
        this.context = context;
    }

    public String nom() {
        return faculty.getNom();
    }
    public int score() {
        return faculty.getScore();
    }
    public String imageUrl() {
        return faculty.getUrl();
    }

}
