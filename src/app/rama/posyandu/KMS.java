	package app.rama.posyandu;

import org.joda.time.DateTime;
import org.joda.time.Months;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import app.rama.lib.DatabaseHandler;
import app.rama.modal.UserClass;
import app.rama.modal.WoaClass;

public class KMS extends Activity {
	private DatabaseHandler dh;
	private int bedaBulan;
	private EditText textBerat;
	private Button btnOK;
	private TextView hasil;
	private WoaClass wc;
	private String status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kms);
		dh = new DatabaseHandler(this);
		
		UserClass uc = dh.getUser(1);
		
		textBerat = (EditText)findViewById(R.id.txtBerat);
		btnOK  = (Button)findViewById(R.id.btnHasilKMS);
		hasil = (TextView)findViewById(R.id.hasilKMS);
		
		String[] tanggal = uc.getTgl().split("-");

		DateTime x = new DateTime().withDate(
				Integer.parseInt(tanggal[2].replaceAll("\\s+","")),
				Integer.parseInt(tanggal[0].replaceAll("\\s+","")),
				Integer.parseInt(tanggal[1].replaceAll("\\s+","")));
		
		Months d = Months.monthsBetween(x, new DateTime());
		bedaBulan = d.getMonths();
		
		wc = dh.getWoa(bedaBulan);

		
		btnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				double median = wc.getMedian();
				double sd1 = wc.getSD1();
				double minsd1 = wc.getMinSD1();
				double sd2 = wc.getSD2();
				double minsd2 = wc.getMinSD2();
				double sd3 = wc.getSD3();
				double minsd3 = wc.getMinSD3();
				double berat = Double.valueOf(textBerat.getText().toString());
				double hasil1 = 0;
				
				Log.v("BERAT", berat + "");
				Log.v("MEDIAN", median + "");
				Log.v("SD1", sd1 + "");
				Log.v("MINSD1", minsd1 + "");
				Log.v("SD2", sd2 + "");
				Log.v("MINSD2", minsd2 + "");
				Log.v("SD3", sd3 + "");
				Log.v("MINSD3", minsd3 + "");
				Log.v("BULAN",bedaBulan + "");
				
				if(berat > median){
					hasil1 = (berat - median) / (sd1 - median);
				} else if(berat < median){
					hasil1 = (berat - median) / (median - minsd3);
				} else if(berat == median){
					hasil1 = (berat - median) / median;
				}
				
				Log.v("HASIL", hasil1 + "");
				
				if(hasil1 < -3.0){
					status = "Status gizi buah hati anda adalah : Gizi Buruk"; 
				} else if(hasil1  > -3.0 && hasil1 <= -2.0){
					status = "Status gizi buah hati anda adalah : Gizi Kurang";
				} else if(hasil1 > -2.0 && hasil1 <= 2.0){
					status = "Status gizi buah hati anda adalah : Gizi Baik";
				} else if(hasil1 > 2.0){
					status = "Status gizi buah hati anda adalah : Gizi Lebih";
				}
				
				if(textBerat.getText().toString().equals("")){
					Toast.makeText(getBaseContext(),"Berat Badan Belum Diisi",Toast.LENGTH_SHORT).show();
					
				}else{
					hasil.setText(status);
				}
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.km, menu);
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
