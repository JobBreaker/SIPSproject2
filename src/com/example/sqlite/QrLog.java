package com.example.sqlite;

public class QrLog {
	  private long id;
	  private String log;
	  private String time;

	  public long getId() {
	    return id;
	  }
	  public void setId(long id) {
	    this.id = id;
	  }

	  public String gettime(){
		  return time;
	  }
	  
	  public String getComment() {
	    return log;
	  }
	  
	  public void settime(String time){
		  this.time = time;
	  }
	  public void setComment(String log) {
	    this.log = log;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return this.log + " " + this.time;
	  }
	} 