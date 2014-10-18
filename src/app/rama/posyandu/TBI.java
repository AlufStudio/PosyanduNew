package app.rama.posyandu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TBI extends Fragment {
	EditText BB;
	Button RTambah;
	TextView jumlah;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.activity_tbi, container,
				false);

		BB = (EditText) rootView.findViewById(R.id.txtBBS);

		jumlah = (TextView) rootView.findViewById(R.id.hasil);
		RTambah = (Button) rootView.findViewById(R.id.RTambah);
		RTambah.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (BB.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "Berat Badan Belum Diisi",
							Toast.LENGTH_SHORT).show();

				} else {
					int nilai1 = Integer.parseInt(BB.getText().toString());
					jumlah.setText(("Tinggi Ideal Anda Adalah ")
							+ Integer.toString(nilai1 * 10 / 9 + 100) + "cm");
				}
			}
		});

		return rootView;
	}
}
