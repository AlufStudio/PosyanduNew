package app.rama.posyandu;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import app.rama.lib.DatabaseHandler;
import app.rama.lib.JadwalAdapter;

public class Jadwal extends ActionBarActivity {
	private DatabaseHandler dh;
	private JadwalAdapter ja;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jadwal);
		
		dh = new DatabaseHandler(getApplicationContext());
		ja = new JadwalAdapter(this, dh.getAllJadwal());
		
		lv = (ListView)findViewById(R.id.listnyoo);
				
		lv.setAdapter(ja);

		// mengautr navbar
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.act_title);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jadwal, menu);
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
