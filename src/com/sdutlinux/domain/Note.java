package com.sdutlinux.domain;

public class Note {
	public String content;
	public int id;
	
	public Note(int id, String content) {
		this.content = content;
		this.id = id;
	}

	public Note(String content) {
		this.content = content;
	}

		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return "id: " + id + " content: " + content;
	}
}
