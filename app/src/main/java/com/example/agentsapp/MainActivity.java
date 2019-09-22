/*Created by Shanice Talan on Sept 19, 2019
CMPP 264 Java: Day 12 Assignment - Android App
Connects to Travel Experts DB to do Select, Insert, Update & Delete
on Agents table */

package com.example.agentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView lvAgents;
    Button btnAdd;
    ArrayAdapter<Agent> adapter;
    AgentDB data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvAgents = findViewById(R.id.lvAgents);
        btnAdd = findViewById(R.id.btnAdd);

        //connect to db and get agents
        data = new AgentDB(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data.getAllAgents());
        lvAgents.setAdapter(adapter);

        //when an agent is clicked,show the details page for the agent
        lvAgents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AgentDetailActivity.class);
                intent.putExtra("agent", adapter.getItem(position));
                startActivity(intent);
            }
        });

        //when the Add button is clicked, go to the add new agent page
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgentAddActivity.class);
                intent.putExtra("mode", "insert");
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        //reloads agents listview
        super.onStart();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data.getAllAgents());
        lvAgents.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        //reloads agents listview
        super.onResume();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data.getAllAgents());
        lvAgents.setAdapter(adapter);
    }
}
