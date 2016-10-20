package de.hermes.model;

public class MyResult
{
	private int rows;

	public MyResult()
	{
	}

	public MyResult(int rows)
	{
		this.rows = rows;
	}

	public int getRows()
	{
		return rows;
	}

	public void setRows(int rows)
	{
		this.rows = rows;
	}

	@Override
	public String toString()
	{
		return "MyResult [rows=" + rows + "]";
	}
}