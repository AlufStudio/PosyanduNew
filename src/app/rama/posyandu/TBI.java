package app.rama.posyandu;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TBI extends Activity {
	EditText BB;
	Button RTambah;
	TextView jumlah;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tbi);
		
		BB = (EditText)findViewById(R.id.txtBBS);
		
		jumlah = (TextView) findViewById(R.id.hasil);
		RTambah = (Button)findViewById(R.id.RTambah);
		RTambah.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
					if(BB.getText().toString().equals("")){
						Toast.makeText(getBaseContext(),"Kosong",Toast.LENGTH_SHORT).show();
						
					}else{
					int nilai1 = Integer.parseInt(BB.getText().toString());
					jumlah.setText(("Tinggi Ideal Anda Adalah ")+Integer.toString(nilai1*10/9+100)+"cm");
					}
				}
			});
			}
	}
