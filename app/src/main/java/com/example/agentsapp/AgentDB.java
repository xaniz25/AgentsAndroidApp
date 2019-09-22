/*Created by Shanice Talan on Sept 19, 2019
CMPP 264 Java: Day 12 Assignment - Android App
Connects to Travel Experts DB to do Select, Insert, Update & Delete
on Agents table */

package com.example.agentsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

//methods to retrieve and update agents table
public class AgentDB implements Serializable {
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public AgentDB(Context context) {
        this.context = context;
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    //to be used to show agents in ListView
    public ArrayList<Agent> getAllAgents()
    {
        ArrayList<Agent> list = new ArrayList<>();
        //column names of agent table
        String [] columns = { "AgentId", "AgtFirstName", "AgtMiddleInitial", "AgtLastName",
        "AgtBusPhone", "AgtEmail", "AgtPosition", "AgencyId"};
        //read each row of agents table
        Cursor cursor = db.query("agents", columns, null, null, null, null, null);
        while (cursor.moveToNext())
        {   //get data from each column to instantiate an agent
            list.add(new Agent(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getString(5), cursor.getString(6), cursor.getInt(7)));
        }
        cursor.close();
        return list;
    }

    public long insertAgent(Agent a)
    {   /*pass agent object that contains data from EditText
        and assign data as values to be inserted*/
        ContentValues cv = new ContentValues();
        cv.put("AgtFirstName", a.getFirstName());
        cv.put("AgtMiddleInitial", a.getMidInitial());
        cv.put("AgtLastName", a.getLastName());
        cv.put("AgtBusPhone", a.getPhone());
        cv.put("AgtEmail", a.getEmail());
        cv.put("AgtPosition", a.getPosition());
        cv.put("AgencyId", a.getAgcyId());
        //inserts new agent and returns its AgentID
        long numRows = db.insert("agents", null, cv);

        return numRows;
    }

    public long updateAgent(Agent a)
    {   /*pass agent object that contains data from EditText
        and assign data as values to update agent*/
        ContentValues cv = new ContentValues();
        cv.put("AgtFirstName", a.getFirstName());
        cv.put("AgtMiddleInitial", a.getMidInitial());
        cv.put("AgtLastName", a.getLastName());
        cv.put("AgtBusPhone", a.getPhone());
        cv.put("AgtEmail", a.getEmail());
        cv.put("AgtPosition", a.getPosition());
        cv.put("AgencyId", a.getAgcyId());
        //AgentId of agent being updated
        String where = "AgentId=?";
        String [] args = { a.getAgentId() + "" };
        //updates agent and returns 1 if successful
        long numRows = db.update("agents", cv, where, args);

        return numRows;
    }

    public long deleteAgent(int AgentId)
    {   //deletes all data of agent
        String where = "AgentId=?";
        String [] args = { AgentId + "" };
        long numRows = db.delete("agents", where, args);

        return numRows;
    }
}
