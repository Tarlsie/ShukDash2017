package com.app.shukdash;

import android.app.Application;

public class DashPassData extends Application {

	public int points, cat, task;

	public DashPassData(int points, int cat, int task) {

		this.points = points;
		this.cat = cat;
		this.task = task;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getCat() {
		return cat;
	}

	public void setCat(int cat) {
		this.cat = cat;
	}

	public int getTask() {
		return task;
	}

	public void setTask(int task) {
		this.task = task;
	}

}
