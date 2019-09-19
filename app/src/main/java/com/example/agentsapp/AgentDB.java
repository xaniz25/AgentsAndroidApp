package com.example.agentsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class AgentDB implements Serializable {
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public AgentDB(Context context) {
        this.context = context;
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public ArrayList<Agent> getAllAgents()
    {
        ArrayList<Agent> list = new ArrayList<>();
        String [] columns = { "AgentId", "AgtFirstName", "AgtMiddleInitial", "AgtLastName",
        "AgtBusPhone", "AgtEmail", "AgtPosition", "AgencyId"};
        Cursor cursor = db.query("agents", columns, null, null, null, null, null);
        while (cursor.moveToNext())
        {
            list.add(new Agent(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getInt(7)));
        }
        cursor.close();
        return list;
    }

    public long insertAgent(Agent a)
    {
        ContentValues cv = new ContentValues();
        cv.put("AgtFirstName", a.getFirstName());
        cv.put("AgtMiddleInitial", a.getMidInitial());
        cv.put("AgtLastName", a.getLastName());
        cv.put("AgtBusPhone", a.getPhone());
        cv.put("AgtEmail", a.getEmail());
        cv.put("AgtPosition", a.getPosition());
        cv.put("AgencyId", a.getAgcyId());
        long numRows = db.insert("agents", null, cv);

        return numRows;
    }

    public long updateAgent(Agent a)
    {
        ContentValues cv = new ContentValues();
        cv.put("AgtFirstName", a.getFirstName());
        cv.put("AgtMiddleInitial", a.getMidInitial());
        cv.put("AgtLastName", a.getLastName());
        cv.put("AgtBusPhone", a.getPhone());
        cv.put("AgtEmail", a.getEmail());
        cv.put("AgtPosition", a.getPosition());
        cv.put("AgencyId", a.getAgcyId());
        String where = "AgentId=?";
        String [] args = { a.getAgentId() + "" };
        long numRows = db.update("agents", cv, where, args);

        return numRows;
    }

    public long deleteAgent(int AgentId)
    {
        String where = "AgentId=?";
        String [] args = { AgentId + "" };
        long numRows = db.delete("agents", where, args);

        return numRows;
    }
}
