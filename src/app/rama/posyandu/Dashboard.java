package app.rama.posyandu;

import com.mikhaellopez.circularimageview.CircularImageView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import app.rama.lib.DatabaseHandler;
import app.rama.lib.SessionHandler;
import app.rama.modal.UserClass;

public class Dashboard extends Activity {
	private DatabaseHandler dh;
	private TextView txNama,txtSubNama;
	private SessionHandler sh;
	private Button btnLogout;
	private ImageButton btnTips,btnKalkulator,btnJadwal,btnAbout,btnBantuan,btnKMS;
	private CircularImageView potoPropils;
	private String[] blnIndonesia = {"Jan","Feb","Maret","April","Mei","Juni","Juli","Agustus","Sept","Okt","Nov","Des"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		dh = new DatabaseHandler(this);
		
		sh = new SessionHandler(getApplicationContext());
		if(!sh.isRegis()){
			Intent i = new Intent(Dashboard.this, Daftar.class);
			 // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
		}
		
		txNama = (TextView)findViewById(R.id.txtNama);
		txtSubNama = (TextView)findViewById(R.id.txtSubNama);
		btnTips = (ImageButton)findViewById(R.id.btnTips);
		btnKalkulator = (ImageButton)findViewById(R.id.btnKalkulator);
		btnJadwal = (ImageButton)findViewById(R.id.btnJadwal);
		btnAbout = (ImageButton)findViewById(R.id.btnAbout);
		btnBantuan = (ImageButton)findViewById(R.id.btnBantuan);
		btnKMS = (ImageButton)findViewById(R.id.btnKMS);
		potoPropils = (CircularImageView)findViewById(R.id.potoPropils);
		btnLogout = (Button)findViewById(R.id.btnLogout);
		
		
		UserClass uc = dh.getUser(1);
		Log.i("INFO", uc.getNama());
		Log.i("INFO", uc.getNamaortu());
		Log.i("INFO", uc.getGambar());
		
		potoPropils.setImageBitmap(decodeSampledBitmapFromPath(uc.getGambar(), 250, 250));
		txNama.setText(uc.getNama());
		String[] tanggal = uc.getTgl().split("-");
		txtSubNama.setText(tanggal[1] + " " + blnIndonesia[Integer.valueOf(tanggal[0]) - 1] + " " + tanggal[2] + " | " + uc.getJK());
		
		btnTips.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Dashboard.this,Tips.class);
				startActivity(i);
			}
		});
		
		btnKalkulator.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Dashboard.this,PedomanGizi.class);
				startActivity(i);
			}
		});
		
		btnJadwal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Dashboard.this,Jadwal.class);
				startActivity(i);
			}
		});
		
		btnAbout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Dashboard.this, About.class);
				startActivity(i);
				
			}
		});
		
		btnBantuan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Dashboard.this, Bantuan.class);
				startActivity(i);
			}
		});
		
		btnKMS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Dashboard.this, KMS.class);
				startActivity(i);
			}
		});
		
		btnLogout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//DELETE DATABASE
				dh.resetDetailJadwal();
				dh.resetDetailTips();
				dh.resetDetailUser();
				dh.resetDetailWOA();
				
				sh.logoutUser();
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
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
	
	public static Bitmap decodeSampledBitmapFromPath(String path, int reqWidth,
            int reqHeight) {
 
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
 
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
 
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeFile(path, options);
        return bmp;
        }
 
    public static int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
 
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
 
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
             }
         }
         return inSampleSize;
        }
	
}
