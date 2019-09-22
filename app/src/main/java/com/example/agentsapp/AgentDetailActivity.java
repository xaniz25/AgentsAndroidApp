package com.example.agentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgentDetailActivity extends AppCompatActivity {
    EditText etAgtId,etFirstName, etMidInitial, etLastName, etPhone, etEmail, etPosition, etAgcyId;
    Button btnUpdate, btnDelete;
    AgentDB data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_detail);

        etAgtId = findViewById(R.id.etAgtId);
        etFirstName = findViewById(R.id.etFirstName);
        etMidInitial = findViewById(R.id.etMidInitial);
        etLastName = findViewById(R.id.etLastName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPosition = findViewById(R.id.etPosition);
        etAgcyId = findViewById(R.id.etAgcyId);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        data = new AgentDB(getApplicationContext());

        //get data for the agent and show in EditText
        Agent agent = (Agent)getIntent().getSerializableExtra("agent");
        etAgtId.setText(agent.getAgentId().toString()+"");
        etFirstName.setText(agent.getFirstName());
        etMidInitial.setText(agent.getMidInitial());
        etLastName.setText(agent.getLastName());
        etPhone.setText(agent.getPhone());
        etEmail.setText(agent.getEmail());
        etPosition.setText(agent.getPosition());
        etAgcyId.setText(agent.getAgcyId().toString()+"");

        //updates the current agent
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate text fields first
                String strFname = etFirstName.getText().toString();
                if (TextUtils.isEmpty(strFname)) {
                    etFirstName.setError("Please enter a First Name!");
                    return;
                }

                String strLname = etLastName.getText().toString();
                if (TextUtils.isEmpty(strLname)) {
                    etAgcyId.setError("Please enter a Last Name!");
                    return;
                }

                String strPhone = etPhone.getText().toString();
                if (!Patterns.PHONE.matcher(strPhone).matches()) {
                    etPhone.setError("Please enter correct Phone format!");
                    return;
                }

                String strEmail = etEmail.getText().toString();
                if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
                    etEmail.setError("Please enter correct Email format!");
                    return;
                }

                String strPostn = etPosition.getText().toString();
                if (TextUtils.isEmpty(strPostn)) {
                    etPosition.setError("Please enter the Position!");
                    return;
                }

                String strAgcyId = etAgcyId.getText().toString();
                if (TextUtils.isEmpty(strAgcyId)) {
                    etAgcyId.setError("Please enter 1 for Calgary, or 2 for Okotoks!");
                    return;
                }

                //gets new content from EditText and update agent
                long result = data.updateAgent(
                        new Agent(Integer.parseInt(etAgtId.getText().toString()),
                                etFirstName.getText().toString(), etMidInitial.getText().toString(),
                                etLastName.getText().toString(), etPhone.getText().toString(),
                                etEmail.getText().toString(), etPosition.getText().toString(),
                                Integer.parseInt(etAgcyId.getText().toString())));

                //checks if successful and shows message to user
                if (result == 0)
                {
                    Log.e("agt", "Update failed");
                    Toast.makeText(getApplicationContext(), "Update failed.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Update successful!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //deletes the current agent
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //just needs agentid
                long result = data.deleteAgent(Integer.parseInt(etAgtId.getText().toString()));

                //checks if successful and shows message to user
                if (result == 0)
                {
                    Log.e("agt", "Delete failed");
                    Toast.makeText(getApplicationContext(), "Delete failed.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Delete successful!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
