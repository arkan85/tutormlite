package com.snippetdump.tutormlite;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{
	
	private static final String DATABASE_NAME = "database.db";
	private static final int DATABASE_VERSION = 1;
	
	private Dao<SampleData, Integer> sampleDataDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			/**
			 * Dropping table in order to clean up db on restart.
			 * Normally you wouldn't drop the table every time you run the onCreate.
			 */
			TableUtils.dropTable(connectionSource, SampleData.class, true);
			
			/**
			 * Creates the table.
			 */
			TableUtils.createTable(connectionSource, SampleData.class);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			/**
			 * In case of an update.
			 */
			TableUtils.dropTable(connectionSource, SampleData.class, true);
			this.onCreate(database, connectionSource);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Dao<SampleData, Integer> getSampleDataDao() throws SQLException {
		if(this.sampleDataDao == null) {
			sampleDataDao = getDao(SampleData.class);
		}
		
		return this.sampleDataDao;
	}

}
