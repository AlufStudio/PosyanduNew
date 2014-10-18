package app.rama.posyandu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Air extends Fragment {
	EditText BB, Umur;
	Button Hitung;
	TextView AIR;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.activity_air, container,
				false);

		BB = (EditText) rootView.findViewById(R.id.txt_bb_air);
		Umur = (EditText) rootView.findViewById(R.id.txt_umur);

		AIR = (TextView) rootView.findViewById(R.id.hasil_air);
		Hitung = (Button) rootView.findViewById(R.id.btn_air);
		Hitung.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (BB.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "Berat Badan Belum Diisi",
							Toast.LENGTH_SHORT).show();
					AIR.setText("");
				} else if (Umur.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "Umur Belum Diisi",
							Toast.LENGTH_SHORT).show();
					AIR.setText("");
				} else {
					double nilai1 = Integer.parseInt(BB.getText().toString());
					double nilai2 = Integer.parseInt(Umur.getText().toString());
					if (nilai2 <= 25) {
						if (nilai1 <= 10) {
							double hasil = nilai1 * 100;
							int hasilnya = (int) (hasil);
							AIR.setText("Kebutuhan Anda adalah " + hasilnya
									+ "ml/hari");
						} else if (nilai1 <= 20 && nilai1 > 10) {
							double satu = nilai1 - 10;
							double hasil = satu * 50 + 1000;
							int hasilnya = (int) (hasil);
							AIR.setText("Kebutuhan Anda adalah " + hasilnya
									+ "ml/hari");
						} else if (nilai1 > 20) {
							double satu = nilai1 - 20;
							double hasil = satu * 20 + 1500;
							int hasilnya = (int) (hasil);
							AIR.setText("Kebutuhan Anda adalah " + hasilnya
									+ "ml/hari");
						}
					} else if (nilai2 <= 55 && nilai2 > 25) {
						double hasil = nilai1 * 35;
						int hasilnya = (int) (hasil);
						AIR.setText("Kebutuhan Anda adalah " + hasilnya
								+ "ml/hari");
					} else if (nilai2 <= 65 && nilai2 > 55) {
						double hasil = nilai1 * 30;
						int hasilnya = (int) (hasil);
						AIR.setText("Kebutuhan Anda adalah " + hasilnya
								+ "ml/hari");
					} else if (nilai2 > 65) {
						double hasil = nilai1 * 25;
						int hasilnya = (int) (hasil);
						AIR.setText("Kebutuhan Anda adalah " + hasilnya
								+ "ml/hari");
					}

				}
			}
		});

		return rootView;
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
