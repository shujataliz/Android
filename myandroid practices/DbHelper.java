package com.example.utils;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper {

	/* Initializing the context below */
	private Context context;
	int DATABASE_VERSION = 1;
	String DATABASE_NAME = "";
	String dbName = "Map_Markers";

	private Databasehelper help;
	private SQLiteDatabase sq_db;

	/* Initializing the constructor below */
	public DbHelper(Context context) {
		/* Setting the context here */
		this.context = context;
		DATABASE_NAME = context.getDatabasePath(dbName +".sqlite")
				.getAbsolutePath();

		DATABASE_VERSION = 1;
	}

	public Boolean DeleteAFileWithID(String Id, String Path) {

		createAndOpenDB(dbName);
		String Query = "Delete from SavedFiles where ID=" + Id + " And Path="
				+ Path + "";
		Boolean basd = executeNonQuery(Query);
		closeDB();

		return basd;

	}

	public Boolean DeleteAFileWithPath(String Path) {

		createAndOpenDB(dbName);
		String Query = "Delete from SavedFiles where   Path=" + Path + "";
		Boolean basd = executeNonQuery(Query);
		closeDB();

		return basd;

	}

	public Boolean insertAFile(String Path, String Type, String Title) {

		createAndOpenDB(dbName);
		String Query = "INSERT INTO SavedFiles (Path,Type,Title ) VALUES("

		+ Path + "','" + Type + "','" + Title + "')";
		Boolean basd = executeNonQuery(Query);
		closeDB();
		return basd;
	}

	public ArrayList<HashMap<String, String>> getSavedFiles() {
		ArrayList<HashMap<String, String>> arrlist = null;
		createAndOpenDB(dbName);
		String sqlSelectQuery = "SELECT ID,Title,Type,Path FROM SavedFiles";
		String[][] arr = executeReader(sqlSelectQuery);
		// ID,path,type,title

		if (arr != null && arr.length > 0) {
			arrlist = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			for (int i = 0; i < arr.length; i++) {

				if (arr[i] != null && arr[i].length > 3) {
					map = new HashMap<String, String>();
					map.put("ID", "" + arr[0]);
					map.put("Title", "" + arr[1]);
					map.put("Type", "" + arr[2]);
					map.put("Path", "" + arr[3]);
					arrlist.add(map);
				}
			}
		}

		closeDB();
		return arrlist;
	}

	public void createDB(String db_name) {
		help = new Databasehelper(context, db_name, null, 1);
		help.getReadableDatabase();
	}

	public SQLiteDatabase createAndOpenDB(String db_name) {
		help = new Databasehelper(context, db_name, null, 1);

		sq_db = help.getWritableDatabase();
		return sq_db;
	}

	public boolean executeNonQuery(String command) {
		try {
			sq_db.execSQL(command);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean executeNonQueryWithASCIIS(String command) {
		try {
			sq_db.rawQuery(command, null);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String[][] executeReader(String command) {
		Cursor Cursor = sq_db.rawQuery(command, null);
		int Column = Cursor.getColumnCount();
		int Row = Cursor.getCount();
		String[][] str_arry = new String[Row][Column];
		int j = 0, i = 0;

		Cursor.moveToFirst();
		if (Row != 0) {
			for (i = 0; i < Row; i++) {

				for (j = 0; j < Column; j++) {
					str_arry[i][j] = Cursor.getString(j);
				}
				Cursor.moveToNext();
			}
			Cursor.close();

			return str_arry;
		}

		else
			return null;
	}

	public void closeDB() {
		sq_db.close();
	}

	public class Databasehelper extends SQLiteOpenHelper {

		public Databasehelper(Context context, String db_name, Object object,
				int i) {

			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public void onCreate(SQLiteDatabase db) {

			/*
			 * Map Marker Table contains the data that is neccessary for the
			 * info window of map marker that contains units,
			 * title,description,Mnemonic,wellcode,Wellname
			 */
			// because table is already created
			// db.execSQL("CREATE TABLE SavedFiles ("
			// + "ID bigint IDENTITY(1,1) NOT NULL, "
			// + "Path varchar(255) NULL, "
			// + "Type  varchar(255) NULL,"
			// + "Title  varchar(255) NULL)");

		}

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

		public void onDowngrade(SQLiteDatabase db, int oldVersion,
				int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}

		public String[][] sss(String query) {

			return null;
		}
	}
}
