package app.rama.lib;

import java.util.List;

import com.mikhaellopez.circularimageview.CircularImageView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.rama.modal.JadwalClass;
import app.rama.modal.TipsClass;
import app.rama.posyandu.R;

public class JadwalAdapter extends BaseAdapter {
	private Activity activity;
    private LayoutInflater inflater;
    private List<JadwalClass> jadwal;
    
    public JadwalAdapter(Activity activity,List<JadwalClass> jadwal){
    	this.activity = activity;
    	this.jadwal = jadwal;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return jadwal.size();
	}

	@Override
	public Object getItem(int location) {
		// TODO Auto-generated method stub
		return jadwal.get(location);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(inflater == null)
			inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null)
			convertView = inflater.inflate(R.layout.jadwal_row, null);
		
		ImageView iconImun = (ImageView)convertView.findViewById(R.id.iconImun);
		TextView txtJudul = (TextView)convertView.findViewById(R.id.txtJudulImun);
		TextView txtDeskripsi = (TextView)convertView.findViewById(R.id.deskripsiImun);
		TextView txtJadwal = (TextView)convertView.findViewById(R.id.jadwalNya);
		
		JadwalClass jc = jadwal.get(position);
		
		if(jc.getJudul().toLowerCase().contains("hepatitis a")){
			iconImun.setImageResource(R.drawable.imun_ha);
		} else if(jc.getJudul().toLowerCase().contains("hepatitis b")){
			iconImun.setImageResource(R.drawable.imun_hb);
		} else if(jc.getJudul().toLowerCase().contains("bcg")){
			iconImun.setImageResource(R.drawable.imun_bcg);
		} else if(jc.getJudul().toLowerCase().contains("dtp")){
			iconImun.setImageResource(R.drawable.imun_dpt);
		} else if(jc.getJudul().toLowerCase().contains("campak")){
			iconImun.setImageResource(R.drawable.imun_campak);
		} else if(jc.getJudul().toLowerCase().contains("influenza")){
			iconImun.setImageResource(R.drawable.imun_flu);
		} else if(jc.getJudul().toLowerCase().contains("hib")){
			iconImun.setImageResource(R.drawable.imun_hib);
		} else if(jc.getJudul().toLowerCase().contains("polio")){
			iconImun.setImageResource(R.drawable.imun_polio);
		} else if(jc.getJudul().toLowerCase().contains("tifoid")){
			iconImun.setImageResource(R.drawable.imun_tifoid);
		} else if(jc.getJudul().toLowerCase().contains("varisela")){
			iconImun.setImageResource(R.drawable.imun_varisela);
		} else if(jc.getJudul().toLowerCase().contains("mmr")){
			iconImun.setImageResource(R.drawable.imun_mmr);
		}
		
		txtJudul.setText("Imunisasi " + jc.getJudul());
		txtDeskripsi.setText(jc.getContent());
		txtJadwal.setText("Diberikan saat : " + jc.getTanggal());
		
		return convertView;
	}

}
