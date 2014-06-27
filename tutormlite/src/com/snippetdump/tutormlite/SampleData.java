package com.snippetdump.tutormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "SampleData")
public class SampleData {

	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField(canBeNull = false, useGetSet = true)
	private String name;
	
	@DatabaseField(useGetSet = true)
	private long someNumber;
	
	/**
	 * ORMLite needs this.
	 */
	public SampleData(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSomeNumber() {
		return someNumber;
	}

	public void setSomeNumber(long someNumber) {
		this.someNumber = someNumber;
	}

	public int getId() {
		return id;
	}
	
}
