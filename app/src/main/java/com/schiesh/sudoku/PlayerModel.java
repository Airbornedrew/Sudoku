package com.schiesh.sudoku;

import android.support.v7.app.AppCompatActivity;

public class PlayerModel extends AppCompatActivity {

    private int ID;
    private String name;
    private int Score;
    //constructor

    public PlayerModel(int id, String name, int Score) {
        this.ID = ID;
        this.name = name;
        this.Score = Score;
    }

    public PlayerModel() {
    }
    //toString is needed to print

    @Override
    public String toString() {
        return "LeaderBoard{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", Score=" + Score +
                '}';
    }

    //Getters and Setters

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
