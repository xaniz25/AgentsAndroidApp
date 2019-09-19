package com.example.agentsapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgentAddActivity extends AppCompatActivity {
    EditText etFirstName, etMidInitial, etLastName, etPhone, etEmail, etPosition, etAgcyId;
    Button btnSave;
    AgentDB data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_add);

        etFirstName = findViewById(R.id.etFirstName);
        etMidInitial = findViewById(R.id.etMidInitial);
        etLastName = findViewById(R.id.etLastName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPosition = findViewById(R.id.etPosition);
        etAgcyId = findViewById(R.id.etAgcyId);
        btnSave = findViewById(R.id.btnSave);

        data = new AgentDB(getApplicationContext());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validate text fields first
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

                long result = data.insertAgent(
                        new Agent(0,
                                etFirstName.getText().toString(), etMidInitial.getText().toString(),
                                etLastName.getText().toString(), etPhone.getText().toString(),
                                etEmail.getText().toString(), etPosition.getText().toString(),
                                Integer.parseInt(etAgcyId.getText().toString())));
                if (result == 0)
                {
                    Log.e("agt", "Save failed");
                    Toast.makeText(getApplicationContext(), "Save failed.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Save successful!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
