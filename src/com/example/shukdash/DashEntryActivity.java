package com.example.shukdash;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DashEntryActivity extends Activity {
	public int numPoints, numCat, numTask;
	//Activity a;
	/*
	 * public DashEntryActivity(int points, int cat, int task) { numPoints =
	 * points; numCat = cat; numTask = task; }
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dash_entry);
		//sort this out
		//a.getApplication().getApplicationContext();
		
		numCat = getIntent().getExtras().getInt("cat");
		numTask = getIntent().getExtras().getInt("task");
		numPoints = getIntent().getExtras().getInt("points");

		TextView dashEntryPoints = (TextView) findViewById(R.id.txtVdashEntryPoints);
		if (numPoints == 1) {
			dashEntryPoints.setText(R.string.Points1);
		} else if (numPoints == 2) {
			dashEntryPoints.setText(R.string.Points2);
		} else if (numPoints == 3) {
			dashEntryPoints.setText(R.string.Points3);
		} else if (numPoints == 4) {
			dashEntryPoints.setText(R.string.Points4);
		} else if (numPoints == 5) {
			dashEntryPoints.setText(R.string.Points5);
		}

		TextView dashEntryCat = (TextView) findViewById(R.id.txtVdashEntryCat);
		TextView dashEntryTask = (TextView) findViewById(R.id.txtVdashEntryTask);
		TextView dashEntryTaskNum = (TextView)findViewById(R.id.txtVDashEntryTaskNumber);
		if (numCat == 1) {
			dashEntryCat.setText(R.string.category1);
			if (numTask == 1) {
				dashEntryTask.setText(R.string.PATask1);
				dashEntryTaskNum.setText(R.string.TaskNum1);
			} else if (numTask == 2) {
				dashEntryTask.setText(R.string.PATask2);
				dashEntryTaskNum.setText(R.string.TaskNum2);
			} else if (numTask == 3) {
				dashEntryTask.setText(R.string.PATask3);
				dashEntryTaskNum.setText(R.string.TaskNum3);
			}

		} else if (numCat == 2) {
			dashEntryCat.setText(R.string.category2);
			if (numTask == 1) {
				dashEntryTask.setText(R.string.SDSTask1);
				dashEntryTaskNum.setText(R.string.TaskNum1);
			} else if (numTask == 2) {
				dashEntryTask.setText(R.string.SDSTask2);
				dashEntryTaskNum.setText(R.string.TaskNum2);

			}
		} else if (numCat == 3) {
			dashEntryCat.setText(R.string.category3);
			if (numTask == 1) {
				dashEntryTask.setText(R.string.MGTask1);
				dashEntryTaskNum.setText(R.string.TaskNum1);
			} else if (numTask == 2) {
				dashEntryTask.setText(R.string.MGTask2);
				dashEntryTaskNum.setText(R.string.TaskNum2);
			} else if (numTask == 3) {
				dashEntryTask.setText(R.string.MGTask3);
				dashEntryTaskNum.setText(R.string.TaskNum3);
			} else if (numTask == 4) {
				dashEntryTask.setText(R.string.MGTask4);
				dashEntryTaskNum.setText(R.string.TaskNum4);
			} else if (numTask == 5) {
				dashEntryTask.setText(R.string.MGTask5);
				dashEntryTaskNum.setText(R.string.TaskNum5);
			} else if (numTask == 6) {
				dashEntryTask.setText(R.string.MGTask6);
				dashEntryTaskNum.setText(R.string.TaskNum6);
			} else if (numTask == 7) {
				dashEntryTask.setText(R.string.MGTask7);
				dashEntryTaskNum.setText(R.string.TaskNum7);
			} else if (numTask == 8) {
				dashEntryTask.setText(R.string.MGTask8);
				dashEntryTaskNum.setText(R.string.TaskNum8);
			}
		}

		else if (numCat == 4) {
			dashEntryCat.setText(R.string.category4);
			if (numTask == 1) {
				dashEntryTask.setText(R.string.ShukTTask1);
				dashEntryTaskNum.setText(R.string.TaskNum1);
			} else if (numTask == 2) {
				dashEntryTask.setText(R.string.ShukTTask2);
				dashEntryTaskNum.setText(R.string.TaskNum2);
			} else if (numTask == 3) {
				dashEntryTask.setText(R.string.ShukTTask3);
				dashEntryTaskNum.setText(R.string.TaskNum3);
			} else if (numTask == 3) {
				dashEntryTask.setText(R.string.ShukTTask4);
				dashEntryTaskNum.setText(R.string.TaskNum4);
			}
		}

		else if (numCat == 5) {
			dashEntryCat.setText(R.string.category5);
			if (numTask == 1) {
				dashEntryTask.setText(R.string.GR8Task1);
				dashEntryTaskNum.setText(R.string.TaskNum1);
			} else if (numTask == 2) {
				dashEntryTask.setText(R.string.GR8Task2);
				dashEntryTaskNum.setText(R.string.TaskNum2);
			} else if (numTask == 3) {
				dashEntryTask.setText(R.string.GR8Task3);
				dashEntryTaskNum.setText(R.string.TaskNum3);
			} else if (numTask == 4) {
				dashEntryTask.setText(R.string.GR8Task4);
				dashEntryTaskNum.setText(R.string.TaskNum4);
			} else if (numTask == 5) {
				dashEntryTask.setText(R.string.GR8Task5);
				dashEntryTaskNum.setText(R.string.TaskNum5);
			} else if (numTask == 6) {
				dashEntryTask.setText(R.string.GR8Task6);
				dashEntryTaskNum.setText(R.string.TaskNum6);
			} else if (numTask == 7) {
				dashEntryTask.setText(R.string.GR8Task7);
				dashEntryTaskNum.setText(R.string.TaskNum7);
			}

		}
		
		Button btnSave = (Button)findViewById(R.id.btnDataEntrySave);
		
		//save entered data
		OnClickListener saveClick = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText dataEntry = (EditText)findViewById(R.id.edTxtDashEntry);
				String textData = dataEntry.getText().toString();
				//a= (Activity) v.getContext();
				DashDataBase dataB= new DashDataBase(v.getContext());
				
				SQLiteDatabase db = dataB.getWritableDatabase();

				ContentValues cv = new ContentValues();
				cv.put(dataB.CATEGORY, String.valueOf(numCat));
				cv.put(dataB.TASK, String.valueOf(numTask));
				cv.put(dataB.POINTS_NUMBER, String.valueOf(numPoints));
				cv.put("editTextData", textData);
				db.insert(dataB.TABLE_NAME, null, cv);
				
				//return to previous activity
				Intent iReturn = new Intent();
				if (!textData.matches("")){
					
					iReturn.putExtra("Message", "Your answers were saved");
					iReturn.putExtra("cat", String.valueOf(numCat));
					iReturn.putExtra("task",String.valueOf(numTask));
					iReturn.putExtra("points", String.valueOf(numPoints));
					Log.e("Return ActivityForResult", numCat+" "+numPoints+" "+numTask);
					setResult(Activity.RESULT_OK,iReturn);
					finish();
				
				}
				else
				{
				
					iReturn.putExtra("Message", "Remember to come back to this later and complete the answers");
					iReturn.putExtra("cat", String.valueOf(numCat));
					iReturn.putExtra("task",String.valueOf(numTask));
					iReturn.putExtra("points", String.valueOf(numPoints));
					setResult(Activity.RESULT_CANCELED,iReturn);
					finish();
				
				}
				
			}
		};
		
		btnSave.setOnClickListener(saveClick);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dash_entry, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
