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

public class BBI extends Activity {
	private EditText txtBB,txtTB;
	private Button btnBBI;
	private TextView txtHasil;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bbi);
		txtBB = (EditText)findViewById(R.id.txtBB);
		txtTB = (EditText)findViewById(R.id.txtTB);
		txtHasil = (TextView)findViewById(R.id.txtHasil);
		btnBBI = (Button)findViewById(R.id.btnBBI);
		
		btnBBI.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				
				if(txtBB.getText().toString().equalsIgnoreCase("")){
					Toast.makeText(getApplicationContext(), "Mohon isi berat badan anda ...!", Toast.LENGTH_SHORT).show();
				} else if(txtTB.getText().toString().equalsIgnoreCase("")){
					Toast.makeText(getApplicationContext(), "Mohon isi tinggi badan anda ...!", Toast.LENGTH_SHORT).show();
				} else {
					double nilai1 = Integer.parseInt(txtBB.getText().toString());
					double nilai2 = Integer.parseInt(txtTB.getText().toString());
					double nilai3 = nilai2 - 100;
					double pangkat = nilai2 / 100;
					double bmi1 = pangkat*pangkat;					
					double hasil = nilai3*0.90;
					double BMI = nilai1/bmi1;
					int hasilnya = (int)(hasil);
					
					if (nilai2<100){
						Toast.makeText(getBaseContext(),"Perlu diingat BMI tidak dapat diaplikasikan kepada anak-anak, wanita hamil, orang berbadan berotot, dan orang tua.",Toast.LENGTH_SHORT).show();
						//BBI.setText("Berat Anda Termasuk Kurus Berat Badan Ideal Anda"+ hasilnya);
					}else if (nilai1<10){
						Toast.makeText(getBaseContext(),"Perlu diingat BMI tidak dapat diaplikasikan kepada anak-anak, wanita hamil, orang berbadan berotot, dan orang tua.",Toast.LENGTH_SHORT).show();
						//BBI.setText("Berat Anda Termasuk Kurus Berat Badan Ideal Anda"+ hasilnya);
					}else{
		
						if (BMI<=18.5){
							//Toast.makeText(getBaseContext(),"Berat Anda Termasuk Kurus Berat Badan Ideal Anda Adalah "+hasilnya+"kg",Toast.LENGTH_SHORT).show();
							txtHasil.setText("Berat Anda Termasuk Kurus Berat Badan Ideal Anda  Adalah "+ hasilnya+"kg");
						}else if (BMI>18.5 && BMI<=25){
							//Toast.makeText(getBaseContext(),"Berat Anda Termasuk Normal Berat Badan Ideal Anda Adalah "+hasilnya+"kg",Toast.LENGTH_SHORT).show();
							txtHasil.setText("Berat Anda Termasuk Normal Berat Badan Ideal Anda Adalah "+ hasilnya +"kg");
						}else if (BMI>25 && BMI<=29){
							//Toast.makeText(getBaseContext(),"Berat Anda Termasuk Gemuk Berat Badan Ideal Anda Adalah "+hasilnya+"kg",Toast.LENGTH_SHORT).show();
							txtHasil.setText("Berat Anda Termasuk Gemuk Berat Badan Ideal Anda Adalah "+ hasilnya+"kg");
						}else if (BMI>30){
							//Toast.makeText(getBaseContext(),"Berat Anda Termasuk Obesitas Berat Badan Ideal Anda Adalah "+hasilnya+"kg",Toast.LENGTH_SHORT).show();
							txtHasil.setText("Berat Anda Termasuk Obesitas Berat Badan Ideal Anda Adalah "+ hasilnya+"kg");
						}
				
					}
			}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bbi, menu);
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
