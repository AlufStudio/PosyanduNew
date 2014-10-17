package app.rama.posyandu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Air extends Activity {
	EditText BB, Umur;
	Button Hitung;
	TextView AIR;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_air);
		
		BB = (EditText)findViewById(R.id.txt_bb_air);
		Umur = (EditText)findViewById(R.id.txt_umur);
	
		AIR = (TextView) findViewById(R.id.hasil_air);
		Hitung = (Button)findViewById(R.id.btn_air);
		Hitung.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			if(BB.getText().toString().equals("")){
				Toast.makeText(getBaseContext(),"Berat Badan Belum Diisi",Toast.LENGTH_SHORT).show();
				AIR.setText("");
			}else if (Umur.getText().toString().equals("")) {
				Toast.makeText(getBaseContext(),"Umur Belum Diisi",Toast.LENGTH_SHORT).show();
				AIR.setText("");
			}
			else{
			double nilai1 = Integer.parseInt(BB.getText().toString());
			double nilai2 = Integer.parseInt(Umur.getText().toString());
					if (nilai2<=25){
						if (nilai1<=10){
							double hasil = nilai1*100;
							int hasilnya = (int)(hasil);
							AIR.setText("Kebutuhan Anda adalah "+ hasilnya +"ml/hari");
						}
						else if (nilai1<=20 && nilai1>10){
							double satu = nilai1-10;
							double hasil = satu*50+1000;
							int hasilnya = (int)(hasil);
							AIR.setText("Kebutuhan Anda adalah "+ hasilnya +"ml/hari");
						}else if (nilai1>20){
							double satu = nilai1-20;
							double hasil = satu*20+1500;
							int hasilnya = (int)(hasil);
							AIR.setText("Kebutuhan Anda adalah "+ hasilnya +"ml/hari");
						}	
					}else if (nilai2<=55 && nilai2>25){
						double hasil = nilai1*35;
						int hasilnya = (int)(hasil);
						AIR.setText("Kebutuhan Anda adalah "+ hasilnya +"ml/hari");
					}else if (nilai2<=65 && nilai2>55){
						double hasil = nilai1*30;
						int hasilnya = (int)(hasil);
						AIR.setText("Kebutuhan Anda adalah "+ hasilnya +"ml/hari");
					}else if (nilai2>65){
						double hasil = nilai1*25;
						int hasilnya = (int)(hasil);
						AIR.setText("Kebutuhan Anda adalah "+ hasilnya +"ml/hari");
					}
			
				}
			}
		});	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.air, menu);
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
