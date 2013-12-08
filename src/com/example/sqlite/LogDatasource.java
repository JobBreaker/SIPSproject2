package com.example.sqlite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LogDatasource {

  // Database fields
  private SQLiteDatabase database;
  private QRHistory dbHelper;
  private String[] allColumns = { QRHistory.COLUMN_ID,
		  QRHistory.COLUMN_COMMENT,QRHistory.COLUMN_TIME };

  public LogDatasource(Context context) {
    dbHelper = new QRHistory(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }


  public long createComment(String comment) {
    ContentValues values = new ContentValues();
    values.put(QRHistory.COLUMN_COMMENT, comment);
    values.put(QRHistory.COLUMN_TIME, getDateTime());
    long insertId = database.insert(QRHistory.TABLE_COMMENTS, null,
        values);
    return insertId;
  }

  private String getDateTime() {
      SimpleDateFormat dateFormat = new SimpleDateFormat(
              "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
      Date date = new Date();
      return dateFormat.format(date);
  }


public void deleteComment(QrLog comment) {
    long id = comment.getId();
    System.out.println("Comment deleted with id: " + id);
    database.delete(QRHistory.TABLE_COMMENTS, QRHistory.COLUMN_ID
        + " = " + id, null);
  }
  public void deleteAllComment(){
	  Cursor cursor = database.query(QRHistory.TABLE_COMMENTS,
		        allColumns, null, null, null, null, null);
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	QrLog comment = cursorToComment(cursor);
		      cursor.moveToNext();
		      deleteComment(comment);
		    }
		    // make sure to close the cursor
		    cursor.close();
  }

  public List<QrLog> getAllComments() {
    List<QrLog> comments = new ArrayList<QrLog>();

    Cursor cursor = database.query(QRHistory.TABLE_COMMENTS,
        allColumns, null, null, null, null, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
    	QrLog comment = cursorToComment(cursor);
      comments.add(comment);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return comments;
  }

  private QrLog cursorToComment(Cursor cursor) {
	QrLog comment = new QrLog();
    comment.setId(cursor.getLong(0));
    comment.setComment(cursor.getString(1));
    comment.settime(cursor.getString(2));
    return comment;
  }
} 
