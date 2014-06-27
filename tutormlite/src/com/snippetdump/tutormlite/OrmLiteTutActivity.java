package com.snippetdump.tutormlite;

import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class OrmLiteTutActivity extends Activity {

	private DatabaseHelper databaseHelper = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orm_lite_tut);
		
		/**
		 * A simple TextView to display the fetched database entries
		 */
		TextView inputTextView = new TextView(this);
		this.databaseInteraction("onCreate", inputTextView);
		setContentView(inputTextView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.orm_lite_tut, menu);
		
		return true;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		/**
		 * Disposes the DatabaseHelper.
		 */
		if(this.databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			this.databaseHelper = null;
		}
	}
	
	private DatabaseHelper getDatabaseHelper() {
		if(this.databaseHelper == null) {
			this.databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		}
		
		return this.databaseHelper;
	}
	
	private void databaseInteraction(String action, TextView tv) {
		try {
			Dao<SampleData, Integer> sampleDataDao = this.getDatabaseHelper().getSampleDataDao();
			
			SampleData sampleData1 = new SampleData();
			sampleData1.setName("Name 1");
			sampleData1.setSomeNumber(111L);
			
			SampleData sampleData2 = new SampleData();
			sampleData2.setName("Name 2");
			sampleData2.setSomeNumber(222L);
			
			sampleDataDao.create(sampleData1);
			sampleDataDao.create(sampleData2);
			
			List<SampleData> list = sampleDataDao.queryForAll();
			StringBuilder sb = new StringBuilder();
			for(SampleData s : list) {
				sb.append(s.getId()).append(" ").append(s.getName()).append(" ").append(s.getSomeNumber()).append("\n");
			}
			
			tv.setText(sb.toString());
		} catch(SQLException e) {
			tv.setText(e.toString());
			e.printStackTrace();
		}
	}
}

