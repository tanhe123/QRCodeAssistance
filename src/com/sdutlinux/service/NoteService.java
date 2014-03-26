package com.sdutlinux.service;

import java.util.ArrayList;
import java.util.List;

import com.sdutlinux.domain.Note;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoteService {
	private String table = "notes";
	
	private NoteDatabaseHelper noteDatabaseHelper;
	
	public NoteService(Context context) {
		noteDatabaseHelper = new NoteDatabaseHelper(context);
	}
	
	public void save(Note note) {
		SQLiteDatabase db = noteDatabaseHelper.getWritableDatabase();
		db.execSQL("insert into notes(content) values(?)", 
				new Object[] {note.getContent()});
		db.close();
	}
	
	public void delete(int id) {
		SQLiteDatabase db = noteDatabaseHelper.getWritableDatabase();
		db.execSQL("delete from notes where noteid=?",
				new Object[] {id});
		db.close();
	}
	
	public void update(Note note) {
		SQLiteDatabase db = noteDatabaseHelper.getWritableDatabase();
		db.execSQL("update notes set content=? where noteid=?",
				new Object[] {note.getContent(), note.getId()});
		db.close();
	}
	
	public List<Note> getScrollData() {
		List<Note> notes = new ArrayList<Note>();
		
		SQLiteDatabase db = noteDatabaseHelper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery("select * from notes", null);
		
		while (cursor.moveToNext()) {
			Note note = readOneFromCursor(cursor);
			notes.add(note);
		}
		
		cursor.close();
		db.close();
		return notes;
	}
	
	public Note find(int id) {
		SQLiteDatabase db = noteDatabaseHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from notes where noteid=?", 
				new String[] {id+""});
		
		Note note = null;
		if (cursor.moveToFirst()) {
			note = readOneFromCursor(cursor);
			cursor.close();
		}
		db.close();
		return note;
	}
	
	private Note readOneFromCursor(Cursor cursor) {
		String content = cursor.getString(cursor.getColumnIndex("content")); 
		int id = cursor.getInt(cursor.getColumnIndex("noteid"));
		Note note = new Note(id, content);
		return note;
	}
}
