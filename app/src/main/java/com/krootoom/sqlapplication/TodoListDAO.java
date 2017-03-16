package com.krootoom.sqlapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ASUS on 2/9/2017.
 */

public class TodoListDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public TodoListDAO (Context context){
        //get context and sent to helper
        dbHelper = new DbHelper(context);

    }
    //open database
    public void open(){
        database =dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public ArrayList<TodoList> getAlltodoList(){
        ArrayList<TodoList> todoList = new ArrayList<TodoList>();
        Cursor cursor = database.rawQuery("SELECT * FROM tbtodo_list;",null);
        cursor.moveToFirst();

        //add TodoList
        TodoList todoList1;


        while (!cursor.isAfterLast()){

            //add TodoList
            todoList1 = new TodoList();
            todoList1.setTaskid(cursor.getInt(0));
            todoList1.setTaskname(cursor.getString(1));

            todoList.add(todoList1);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }

    public void add(TodoList todoList){
        //object was threw and recieve it
        TodoList newTodoList = new TodoList();
         newTodoList = todoList;

        //after that GET value from object Todolist and send to ContentValues
        ContentValues values = new ContentValues();
        //put table name and value to put it
        values.put("taskname", newTodoList.getTaskname());
        this.database.insert("tbtodo_list",null,values);
        //to show result message
        Log.d("Todo List Demo :::", "Add OK");
    }

    public void update(TodoList todoList){
        TodoList updateTodoList = todoList;
        ContentValues values2 = new ContentValues();
        values2.put("taskname",updateTodoList.getTaskname());
       // values.put("taskid",updateTodoList.getTaskid());
        String where = "taskid=" + updateTodoList.getTaskid();

        this.database.update("tbtodo_list",values2,where, null);
        Log.d("Todo_list Demo", "Update OK");
    }
}
