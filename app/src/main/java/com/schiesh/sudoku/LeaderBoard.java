package com.schiesh.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class LeaderBoard extends AppCompatActivity {
    Button btn_add, btn_viewAll;
    EditText et_name, et_score;
    ListView lv_playerlist;
    Button backButton;
    public static final String LeaderBoard = "com.schiesh.sudoku";
    String Score = "n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        backButton = findViewById(R.id.switchButton2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openLeaderBoardIntent = new Intent(LeaderBoard.this, MainActivity.class);
                openLeaderBoardIntent.putExtra("message", Score);
                startActivity(openLeaderBoardIntent);
                finish();
            }
        });

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_name = findViewById(R.id.et_name);
        et_score = findViewById(R.id.et_score);
        lv_playerlist = findViewById(R.id.lv_playerlist);


        // button listeners
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlayerModel PlayerModel;

                try {
                    PlayerModel = new PlayerModel(-1, et_name.getText().toString(), Integer.parseInt(et_score.getText().toString()));
                    Toast.makeText(LeaderBoard.this, PlayerModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(LeaderBoard.this, "Error", Toast.LENGTH_SHORT).show();
                    PlayerModel = new PlayerModel(-1, "error", 0);

                }
                DatabaseModel databaseModel = new DatabaseModel(LeaderBoard.this);
                boolean success = databaseModel.addOne(PlayerModel);
                Toast.makeText(LeaderBoard.this, "Success= " + success, Toast.LENGTH_SHORT).show();

            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatabaseModel databasehelper = new DatabaseModel(LeaderBoard.this);
                List<PlayerModel> everyone = databasehelper.getEveryone();
                ArrayAdapter playerArrayAdapter = new ArrayAdapter<PlayerModel>(LeaderBoard.this, android.R.layout.simple_list_item_1, everyone);
                lv_playerlist.setAdapter(playerArrayAdapter);

            }
        });
    }}
