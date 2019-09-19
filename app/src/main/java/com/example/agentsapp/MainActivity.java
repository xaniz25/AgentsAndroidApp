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

        data = new AgentDB(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data.getAllAgents());
        lvAgents.setAdapter(adapter);

        lvAgents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AgentDetailActivity.class);
                intent.putExtra("agent", adapter.getItem(position));
                startActivity(intent);
            }
        });

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
        super.onStart();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data.getAllAgents());
        lvAgents.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data.getAllAgents());
        lvAgents.setAdapter(adapter);
    }
}
