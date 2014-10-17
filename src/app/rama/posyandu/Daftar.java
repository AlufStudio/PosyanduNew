package app.rama.posyandu;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Months;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import app.rama.lib.DatabaseHandler;
import app.rama.lib.SessionHandler;
import app.rama.modal.JadwalClass;
import app.rama.modal.UserClass;

public class Daftar extends Activity {
	private EditText txtNamaAnak, txtNamaOrtu, txtTglLahir;
	private Spinner txtJK;
	private Button btn1,btn2;
	private DatabaseHandler dh;
	private SessionHandler sh;
	private ProgressDialog pDialog;
	private ImageView potoPropil;
	private int year, month, day;
	private String picturePath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daftar);
		dh = new DatabaseHandler(this);
		sh = new SessionHandler(getApplicationContext());
		
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.navbar_daftar);

		txtNamaAnak = (EditText) findViewById(R.id.txtNamaAnak);
		txtTglLahir = (EditText) findViewById(R.id.txtTglLahir);
		txtJK = (Spinner) findViewById(R.id.spinJK);
		txtNamaOrtu = (EditText) findViewById(R.id.txtNamaOrtu);

		btn1 = (Button) findViewById(R.id.btnDaftar);
		btn2 = (Button)findViewById(R.id.btnGantiFoto);
		potoPropil = (ImageView) findViewById(R.id.imgPhoto);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.JK_arrray, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		txtJK.setAdapter(adapter);

		setCurrentDateOnView();

		txtTglLahir.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(999);

			}
		});

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(txtNamaAnak.getText().toString().isEmpty() || txtNamaOrtu.getText().toString().isEmpty() || txtTglLahir.getText().toString().isEmpty()){
					Toast.makeText(getApplicationContext(), "Form harus terisi dengan benar", Toast.LENGTH_SHORT).show();
				} else 
					new daftarIN().execute();
			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Intent.ACTION_GET_CONTENT, null);
				i.setType("image/*");
				i.putExtra("return-data", true);
				startActivityForResult(i, 1000);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1000 && resultCode == RESULT_OK && null != data) {
			Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            picturePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            
            BitmapFactory.Options options=null;
            options = new BitmapFactory.Options();
            options.inSampleSize = 2;
             
            
            // Now we need to set the GUI ImageView data with data read from the picked file.
            potoPropil.setImageBitmap(decodeSampledBitmapFromPath(picturePath, 250, 250));
             
            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();
		}
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
	

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case 999:
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	// display current date
	public void setCurrentDateOnView() {

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			txtTglLahir.setText(new StringBuilder().append(month + 1)
					.append("-").append(day).append("-").append(year)
					.append(" "));

			// set selected date into datepicker also
			// dpResult.init(year, month, day, null);

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.daftar, menu);
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

	private void fillWOA(String jk) {
		if (jk.equals("Perempuan")) {
			// woa Perempuan
			dh.addDetailWOA(0, "Perempuan", 2.0, 2.4, 2.8, 3.2, 3.7, 4.2, 4.8);
			dh.addDetailWOA(1, "Perempuan", 2.9, 3.4, 3.9, 4.5, 5.1, 5.8, 6.6);
			dh.addDetailWOA(2, "Perempuan", 3.8, 4.3, 4.9, 5.6, 6.3, 7.1, 8.0);
			dh.addDetailWOA(3, "Perempuan", 4.4, 5.0, 5.7, 6.4, 7.2, 8.0, 9.0);
			dh.addDetailWOA(4, "Perempuan", 4.9, 5.6, 6.2, 7.0, 7.8, 8.7, 9.7);
			dh.addDetailWOA(5, "Perempuan", 5.3, 6.0, 6.7, 7.5, 8.4, 9.3, 10.4);
			dh.addDetailWOA(6, "Perempuan", 5.7, 6.4, 7.1, 7.9, 8.8, 9.8, 10.9);
			dh.addDetailWOA(7, "Perempuan", 5.9, 6.7, 7.4, 8.3, 9.2, 10.3, 11.4);
			dh.addDetailWOA(8, "Perempuan", 6.2, 6.9, 7.7, 8.6, 9.6, 10.7, 11.9);
			dh.addDetailWOA(9, "Perempuan", 6.4, 7.1, 8.0, 8.9, 9.9, 11.0, 12.3);
			dh.addDetailWOA(10, "Perempuan", 6.6, 7.4, 8.2, 9.2, 10.2, 11.4,
					12.7);
			dh.addDetailWOA(11, "Perempuan", 6.8, 7.6, 8.4, 9.4, 10.5, 11.7,
					13.0);
			dh.addDetailWOA(12, "Perempuan", 6.9, 7.7, 8.6, 9.6, 10.8, 12.0,
					13.3);
			dh.addDetailWOA(13, "Perempuan", 7.1, 7.9, 8.8, 9.9, 11.0, 12.3,
					13.7);
			dh.addDetailWOA(14, "Perempuan", 7.2, 8.1, 9.0, 10.1, 11.3, 12.6,
					14.0);
			dh.addDetailWOA(15, "Perempuan", 7.4, 8.3, 9.2, 10.3, 11.5, 12.8,
					14.3);
			dh.addDetailWOA(16, "Perempuan", 7.5, 8.4, 9.4, 10.5, 11.7, 13.1,
					14.6);
			dh.addDetailWOA(17, "Perempuan", 7.7, 8.6, 9.6, 10.7, 12.0, 13.4,
					14.9);
			dh.addDetailWOA(18, "Perempuan", 7.8, 8.8, 9.8, 10.9, 12.2, 13.7,
					15.3);
			dh.addDetailWOA(19, "Perempuan", 8.0, 8.9, 10.0, 11.1, 12.5, 13.9,
					15.6);
			dh.addDetailWOA(20, "Perempuan", 8.1, 9.1, 10.1, 11.3, 12.7, 14.2,
					15.9);
			dh.addDetailWOA(21, "Perempuan", 8.2, 9.2, 10.3, 11.5, 12.9, 14.5,
					16.2);
			dh.addDetailWOA(22, "Perempuan", 8.4, 9.4, 10.5, 11.8, 13.2, 14.7,
					16.5);
			dh.addDetailWOA(23, "Perempuan", 8.5, 9.5, 10.7, 12.0, 13.4, 15.0,
					16.8);
			dh.addDetailWOA(24, "Perempuan", 8.6, 9.7, 10.8, 12.2, 13.6, 15.3,
					17.1);
			dh.addDetailWOA(25, "Perempuan", 8.8, 9.8, 11.0, 12.4, 13.9, 15.5,
					17.5);
			dh.addDetailWOA(26, "Perempuan", 8.9, 10.0, 11.2, 12.5, 14.1, 15.8,
					17.8);
			dh.addDetailWOA(27, "Perempuan", 9.0, 10.1, 11.3, 12.7, 14.3, 16.1,
					18.1);
			dh.addDetailWOA(28, "Perempuan", 9.1, 10.2, 11.5, 12.9, 14.5, 16.3,
					18.4);
			dh.addDetailWOA(29, "Perempuan", 9.2, 10.4, 11.7, 13.1, 14.8, 16.6,
					18.7);
			dh.addDetailWOA(30, "Perempuan", 9.4, 10.5, 11.8, 13.3, 15.0, 16.9,
					19.0);
			dh.addDetailWOA(31, "Perempuan", 9.5, 10.7, 12.0, 13.5, 15.2, 17.1,
					19.3);
			dh.addDetailWOA(32, "Perempuan", 9.6, 10.8, 12.1, 13.7, 15.4, 17.4,
					19.6);
			dh.addDetailWOA(33, "Perempuan", 9.7, 10.9, 12.3, 13.8, 15.6, 17.6,
					19.9);
			dh.addDetailWOA(34, "Perempuan", 9.8, 11.0, 12.4, 14.0, 15.8, 17.8,
					20.2);
			dh.addDetailWOA(35, "Perempuan", 9.9, 11.2, 12.6, 14.2, 16.0, 18.1,
					20.4);
			dh.addDetailWOA(36, "Perempuan", 10.0, 11.3, 12.7, 14.3, 16.2,
					18.3, 20.7);
			dh.addDetailWOA(37, "Perempuan", 10.1, 11.4, 12.9, 14.5, 16.4,
					18.6, 21.0);
			dh.addDetailWOA(38, "Perempuan", 10.2, 11.5, 13.0, 14.7, 16.6,
					18.8, 21.3);
			dh.addDetailWOA(39, "Perempuan", 10.3, 11.6, 13.1, 14.8, 16.8,
					19.0, 21.6);
			dh.addDetailWOA(40, "Perempuan", 10.4, 11.8, 13.3, 15.0, 17.0,
					19.3, 21.9);
			dh.addDetailWOA(41, "Perempuan", 10.5, 11.9, 13.4, 15.2, 17.2,
					19.5, 22.1);
			dh.addDetailWOA(42, "Perempuan", 10.6, 12.0, 13.6, 15.3, 17.4,
					19.7, 22.4);
			dh.addDetailWOA(43, "Perempuan", 10.7, 12.1, 13.7, 15.5, 17.6,
					20.0, 22.7);
			dh.addDetailWOA(44, "Perempuan", 10.8, 12.2, 13.8, 15.7, 17.8,
					20.2, 23.0);
			dh.addDetailWOA(45, "Perempuan", 10.9, 12.4, 14.0, 15.8, 18.0,
					20.5, 23.3);
			dh.addDetailWOA(46, "Perempuan", 11.0, 12.5, 14.1, 16.0, 18.2,
					20.7, 23.6);
			dh.addDetailWOA(47, "Perempuan", 11.1, 12.6, 14.3, 16.2, 18.4,
					20.9, 23.9);
			dh.addDetailWOA(48, "Perempuan", 11.2, 12.7, 14.4, 16.3, 18.6,
					21.2, 24.2);
			dh.addDetailWOA(49, "Perempuan", 11.3, 12.8, 14.5, 16.5, 18.8,
					21.4, 24.5);
			dh.addDetailWOA(50, "Perempuan", 11.4, 12.9, 14.7, 16.7, 19.0,
					21.7, 24.8);
			dh.addDetailWOA(51, "Perempuan", 11.5, 13.1, 14.8, 16.8, 19.2,
					21.9, 25.1);
			dh.addDetailWOA(52, "Perempuan", 11.6, 13.2, 15.0, 17.0, 19.4,
					22.2, 25.4);
			dh.addDetailWOA(53, "Perempuan", 11.7, 13.3, 15.1, 17.2, 19.6,
					22.4, 25.7);
			dh.addDetailWOA(54, "Perempuan", 11.8, 13.4, 15.2, 17.3, 19.8,
					22.7, 26.0);
			dh.addDetailWOA(55, "Perempuan", 11.9, 13.5, 15.4, 17.5, 20.0,
					22.9, 26.3);
			dh.addDetailWOA(56, "Perempuan", 12.0, 13.6, 15.5, 17.7, 20.2,
					23.2, 26.6);
			dh.addDetailWOA(57, "Perempuan", 12.1, 13.7, 15.6, 17.8, 20.4,
					23.4, 26.9);
			dh.addDetailWOA(58, "Perempuan", 12.2, 13.8, 15.8, 18.0, 20.6,
					23.7, 27.2);
			dh.addDetailWOA(59, "Perempuan", 12.3, 14.0, 15.9, 18.2, 20.8,
					23.9, 27.6);
			dh.addDetailWOA(60, "Perempuan", 12.4, 14.1, 16.0, 18.3, 21.0,
					24.2, 27.9);

		} else {

			// woa Laki - Laki
			dh.addDetailWOA(1, "Laki - Laki", 2.9, 3.4, 3.9, 4.5, 5.1, 5.8, 6.6);
			dh.addDetailWOA(2, "Laki - Laki", 3.8, 4.3, 4.9, 5.6, 6.3, 7.1, 8.0);
			dh.addDetailWOA(3, "Laki - Laki", 4.4, 5.0, 5.7, 6.4, 7.2, 8.0, 9.0);
			dh.addDetailWOA(4, "Laki - Laki", 4.9, 5.6, 6.2, 7.0, 7.8, 8.7, 9.7);
			dh.addDetailWOA(5, "Laki - Laki", 5.3, 6.0, 6.7, 7.5, 8.4, 9.3,
					10.4);
			dh.addDetailWOA(6, "Laki - Laki", 5.7, 6.4, 7.1, 7.9, 8.8, 9.8,
					10.9);
			dh.addDetailWOA(7, "Laki - Laki", 5.9, 6.7, 7.4, 8.3, 9.2, 10.3,
					11.4);
			dh.addDetailWOA(8, "Laki - Laki", 6.2, 6.9, 7.7, 8.6, 9.6, 10.7,
					11.9);
			dh.addDetailWOA(9, "Laki - Laki", 6.4, 7.1, 8.0, 8.9, 9.9, 11.0,
					12.3);
			dh.addDetailWOA(10, "Laki - Laki", 6.6, 7.4, 8.2, 9.2, 10.2, 11.4,
					12.7);
			dh.addDetailWOA(11, "Laki - Laki", 6.8, 7.6, 8.4, 9.4, 10.5, 11.7,
					13.0);
			dh.addDetailWOA(12, "Laki - Laki", 6.9, 7.7, 8.6, 9.6, 10.8, 12.0,
					13.3);
			dh.addDetailWOA(13, "Laki - Laki", 7.1, 7.9, 8.8, 9.9, 11.0, 12.3,
					13.7);
			dh.addDetailWOA(14, "Laki - Laki", 7.2, 8.1, 9.0, 10.1, 11.3, 12.6,
					14.0);
			dh.addDetailWOA(15, "Laki - Laki", 7.4, 8.3, 9.2, 10.3, 11.5, 12.8,
					14.3);
			dh.addDetailWOA(16, "Laki - Laki", 7.5, 8.4, 9.4, 10.5, 11.7, 13.1,
					14.6);
			dh.addDetailWOA(17, "Laki - Laki", 7.7, 8.6, 9.6, 10.7, 12.0, 13.4,
					14.9);
			dh.addDetailWOA(18, "Laki - Laki", 7.8, 8.8, 9.8, 10.9, 12.2, 13.7,
					15.3);
			dh.addDetailWOA(19, "Laki - Laki", 8.0, 8.9, 10.0, 11.1, 12.5,
					13.9, 15.6);
			dh.addDetailWOA(20, "Laki - Laki", 8.1, 9.1, 10.1, 11.3, 12.7,
					14.2, 15.9);
			dh.addDetailWOA(21, "Laki - Laki", 8.2, 9.2, 10.3, 11.5, 12.9,
					14.5, 16.2);
			dh.addDetailWOA(22, "Laki - Laki", 8.4, 9.4, 10.5, 11.8, 13.2,
					14.7, 16.5);
			dh.addDetailWOA(23, "Laki - Laki", 8.5, 9.5, 10.7, 12.0, 13.4,
					15.0, 16.8);
			dh.addDetailWOA(24, "Laki - Laki", 8.6, 9.7, 10.8, 12.2, 13.6,
					15.3, 17.1);
			dh.addDetailWOA(25, "Laki - Laki", 8.8, 9.8, 11.0, 12.4, 13.9,
					15.5, 17.5);
			dh.addDetailWOA(26, "Laki - Laki", 8.9, 10.0, 11.2, 12.5, 14.1,
					15.8, 17.8);
			dh.addDetailWOA(27, "Laki - Laki", 9.0, 10.1, 11.3, 12.7, 14.3,
					16.1, 18.1);
			dh.addDetailWOA(28, "Laki - Laki", 9.1, 10.2, 11.5, 12.9, 14.5,
					16.3, 18.4);
			dh.addDetailWOA(29, "Laki - Laki", 9.2, 10.4, 11.7, 13.1, 14.8,
					16.6, 18.7);
			dh.addDetailWOA(30, "Laki - Laki", 9.4, 10.5, 11.8, 13.3, 15.0,
					16.9, 19.0);
			dh.addDetailWOA(31, "Laki - Laki", 9.5, 10.7, 12.0, 13.5, 15.2,
					17.1, 19.3);
			dh.addDetailWOA(32, "Laki - Laki", 9.6, 10.8, 12.1, 13.7, 15.4,
					17.4, 19.6);
			dh.addDetailWOA(33, "Laki - Laki", 9.7, 10.9, 12.3, 13.8, 15.6,
					17.6, 19.9);
			dh.addDetailWOA(34, "Laki - Laki", 9.8, 11.0, 12.4, 14.0, 15.8,
					17.8, 20.2);
			dh.addDetailWOA(35, "Laki - Laki", 9.9, 11.2, 12.6, 14.2, 16.0,
					18.1, 20.4);
			dh.addDetailWOA(36, "Laki - Laki", 10.0, 11.3, 12.7, 14.3, 16.2,
					18.3, 20.7);
			dh.addDetailWOA(37, "Laki - Laki", 10.1, 11.4, 12.9, 14.5, 16.4,
					18.6, 21.0);
			dh.addDetailWOA(38, "Laki - Laki", 10.2, 11.5, 13.0, 14.7, 16.6,
					18.8, 21.3);
			dh.addDetailWOA(39, "Laki - Laki", 10.3, 11.6, 13.1, 14.8, 16.8,
					19.0, 21.6);
			dh.addDetailWOA(40, "Laki - Laki", 10.4, 11.8, 13.3, 15.0, 17.0,
					19.3, 21.9);
			dh.addDetailWOA(41, "Laki - Laki", 10.5, 11.9, 13.4, 15.2, 17.2,
					19.5, 22.1);
			dh.addDetailWOA(42, "Laki - Laki", 10.6, 12.0, 13.6, 15.3, 17.4,
					19.7, 22.4);
			dh.addDetailWOA(43, "Laki - Laki", 10.7, 12.1, 13.7, 15.5, 17.6,
					20.0, 22.7);
			dh.addDetailWOA(44, "Laki - Laki", 10.8, 12.2, 13.8, 15.7, 17.8,
					20.2, 23.0);
			dh.addDetailWOA(45, "Laki - Laki", 10.9, 12.4, 14.0, 15.8, 18.0,
					20.5, 23.3);
			dh.addDetailWOA(46, "Laki - Laki", 11.0, 12.5, 14.1, 16.0, 18.2,
					20.7, 23.6);
			dh.addDetailWOA(47, "Laki - Laki", 11.1, 12.6, 14.3, 16.2, 18.4,
					20.9, 23.9);
			dh.addDetailWOA(48, "Laki - Laki", 11.2, 12.7, 14.4, 16.3, 18.6,
					21.2, 24.2);
			dh.addDetailWOA(49, "Laki - Laki", 11.3, 12.8, 14.5, 16.5, 18.8,
					21.4, 24.5);
			dh.addDetailWOA(50, "Laki - Laki", 11.4, 12.9, 14.7, 16.7, 19.0,
					21.7, 24.8);
			dh.addDetailWOA(51, "Laki - Laki", 11.5, 13.1, 14.8, 16.8, 19.2,
					21.9, 25.1);
			dh.addDetailWOA(52, "Laki - Laki", 11.6, 13.2, 15.0, 17.0, 19.4,
					22.2, 25.4);
			dh.addDetailWOA(53, "Laki - Laki", 11.7, 13.3, 15.1, 17.2, 19.6,
					22.4, 25.7);
			dh.addDetailWOA(54, "Laki - Laki", 11.8, 13.4, 15.2, 17.3, 19.8,
					22.7, 26.0);
			dh.addDetailWOA(55, "Laki - Laki", 11.9, 13.5, 15.4, 17.5, 20.0,
					22.9, 26.3);
			dh.addDetailWOA(56, "Laki - Laki", 12.0, 13.6, 15.5, 17.7, 20.2,
					23.2, 26.6);
			dh.addDetailWOA(57, "Laki - Laki", 12.1, 13.7, 15.6, 17.8, 20.4,
					23.4, 26.9);
			dh.addDetailWOA(58, "Laki - Laki", 12.2, 13.8, 15.8, 18.0, 20.6,
					23.7, 27.2);
			dh.addDetailWOA(59, "Laki - Laki", 12.3, 14.0, 15.9, 18.2, 20.8,
					23.9, 27.6);
			dh.addDetailWOA(60, "Laki - Laki", 12.4, 14.1, 16.0, 18.3, 21.0,
					24.2, 27.9);
		}

	}

	private void fillJadwal(int month) {
		if (month <= 1) {
			
			// UMUR BARU LAHIR
						dh.addDetailJadwal(new JadwalClass("Hepatitis B1", "Lahir",
								"Mencegah penyakit Hepatitis B"));
						dh.addDetailJadwal(new JadwalClass("Polio 0", "Lahir",
								"Mencegah penyakit Polio"));
						
			// UMUR 1 BULAN
			dh.addDetailJadwal(new JadwalClass("Hepatitis B2", "1 Bulan",
					"Mencegah penyakit Hepatitis B"));

			if (month <= 2) {
				// UMUR 2 BULAN
				dh.addDetailJadwal(new JadwalClass("Polio 1", "2 Bulan",
						"Mencegah penyakit Polio"));
				dh.addDetailJadwal(new JadwalClass("BCG", "2 Bulan",
						"Mencegah penyakit Tuberkolosis (TBC)"));
				dh.addDetailJadwal(new JadwalClass("DTP 1", "2 Bulan",
						"Mencegah penyakit Difteri,Tetanus,Pertusis"));
				dh.addDetailJadwal(new JadwalClass("Hib 1", "2 Bulan",
						"Mencegah penyakit Hib"));
				dh.addDetailJadwal(new JadwalClass("PVC 1", "2 Bulan",
						"Mencegah penyakit Meningitis"));
				dh.addDetailJadwal(new JadwalClass("Rotavirus 1", "2 Bulan",
						"Mencegah penyakit Rotavirus"));

				if (month <= 4) {
					// UMUR 4 BULAN
					dh.addDetailJadwal(new JadwalClass("Polio 2", "4 Bulan",
							"Mencegah penyakit Polio"));
					dh.addDetailJadwal(new JadwalClass("DTP 2", "4 Bulan",
							"Mencegah penyakit Difteri,Tetanus,Pertusis"));
					dh.addDetailJadwal(new JadwalClass("Hib 2", "4 Bulan",
							"Mencegah penyakit Hib"));
					dh.addDetailJadwal(new JadwalClass("PVC 2", "4 Bulan",
							"Mencegah penyakit Meningitis"));
					dh.addDetailJadwal(new JadwalClass("Rotavirus 2",
							"4 Bulan", "Mencegah penyakit Rotavirus"));

					if (month <= 6) {
						// UMUR 6 BULAN
						dh.addDetailJadwal(new JadwalClass("Hepatitis B3",
								"6 Bulan", "Mencegah penyakit hepatitis"));
						dh.addDetailJadwal(new JadwalClass("Polio 3",
								"4 Bulan", "Mencegah penyakit Polio"));
						dh.addDetailJadwal(new JadwalClass("DTP 3", "6 Bulan",
								"Mencegah penyakit Difteri,Tetanus,Pertusis"));
						dh.addDetailJadwal(new JadwalClass("Hib 3", "6 Bulan",
								"Mencegah penyakit Hib"));
						dh.addDetailJadwal(new JadwalClass("PVC 3", "6 Bulan",
								"Mencegah penyakit Meningitis"));
						dh.addDetailJadwal(new JadwalClass("Rotavirus 3",
								"6 Bulan", "Mencegah penyakit Rotavirus"));
						dh.addDetailJadwal(new JadwalClass("Influenza",
								"6 Bulan", "Mencegah penyakit Influenza"));

						if (month <= 9) {
							// UMUR 9 BULAN
							dh.addDetailJadwal(new JadwalClass("Campak 1",
									"9 Bulan", "Mencegah penyakit Campak"));

							if (month <= 12) {
								// UMUR 12 BULAN
								dh.addDetailJadwal(new JadwalClass("PVC 4",
										"12 Bulan",
										"Mencegah penyakit Pertusis"));
								dh.addDetailJadwal(new JadwalClass("Varisela",
										"12 Bulan",
										"Mencegah penyakit Varisela/Cacar Air"));

								if (month <= 15) {
									// UMUR 15 BULAN
									dh.addDetailJadwal(new JadwalClass("Hib 4",
											"15 Bulan", "Mencegah penyakit Hib"));
									dh.addDetailJadwal(new JadwalClass("MMR 1",
											"15 Bulan",
											"Mencegah penyakit Mumps/Gondong, Rubella"));

									if (month <= 18) {
										// UMUR 18 BULAN
										dh.addDetailJadwal(new JadwalClass(
												"Polio 4", "18 Bulan",
												"Mencegah penyakit Polio"));
										dh.addDetailJadwal(new JadwalClass(
												"DTP 4", "18 Bulan",
												"Mencegah penyakit Difteri,Tetanus,Pertusis"));

										if (month <= 24) {
											// UMUR 24 BULAN
											dh.addDetailJadwal(new JadwalClass(
													"Tifoid", "24 Bulan",
													"Mencegah Penyakit Tifoid"));
											dh.addDetailJadwal(new JadwalClass(
													"Hepatitis A", "24 Bulan",
													"Mencegah penyakit Hepatitis A"));

											if (month <= 60) {
												// UMUR 5 TAHUN
												dh.addDetailJadwal(new JadwalClass(
														"Polio 5", "5 Tahun",
														"Mencegah penyakit Polio"));
												dh.addDetailJadwal(new JadwalClass(
														"DTP 5", "5 Tahun",
														"Mencegah penyakit Difteri,Tetanus,Pertusis"));
												dh.addDetailJadwal(new JadwalClass(
														"MMR 2", "5 Tahun",
														"Mencegah penyakit Mumps/Gondong, Rubella"));
											}
										}
									}
								}
							}
						}
					}
				}

			}
		}

	}

	class daftarIN extends AsyncTask<Integer, String, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			dh.addDetailUser(new UserClass(txtNamaAnak.getText().toString(),
					txtTglLahir.getText().toString(), txtNamaOrtu.getText()
							.toString(), txtJK.getSelectedItem().toString(),picturePath));
			
			Log.i("INFO", txtNamaAnak.getText().toString());
			Log.i("INFO", txtTglLahir.getText().toString());
			Log.i("INFO", txtNamaOrtu.getText().toString());
			Log.i("INFO", txtJK.getSelectedItem().toString());
			Log.i("INFO", picturePath);
			
			

			// Session
			sh.createRegisSession("asdhajhfa309182391jkahskjayr941");

			fillWOA(txtJK.getSelectedItem().toString());

			String[] tanggal = txtTglLahir.getText().toString().split("-");

			DateTime x = new DateTime().withDate(
					Integer.parseInt(tanggal[2].replaceAll("\\s+", "")),
					Integer.parseInt(tanggal[0].replaceAll("\\s+", "")),
					Integer.parseInt(tanggal[1].replaceAll("\\s+", "")));

			Months d = Months.monthsBetween(x, new DateTime());
			int monthsDiff = d.getMonths();
			fillJadwal(monthsDiff);

			return null;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			// Passing to the dashboard activity
			Intent i = new Intent(Daftar.this, Dashboard.class);
			startActivity(i);
			finish();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			// Showing Progress dialog
			pDialog = new ProgressDialog(Daftar.this);
			pDialog.setMessage("Analyzing and Generating Data ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
			super.onPreExecute();
		}

	}
}
