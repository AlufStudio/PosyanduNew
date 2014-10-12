package app.rama.posyandu;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import app.rama.lib.ListAdapter;
import app.rama.modal.TipsClass;

public class Tips extends Activity {
    private ListView listView;
    private ListAdapter listAdapter;
    private List<TipsClass> tipsItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tips);
		
		listView = (ListView)findViewById(R.id.list);
		tipsItems = new ArrayList<TipsClass>();
		populatingData();
		listAdapter = new ListAdapter(this, tipsItems);
		listView.setAdapter(listAdapter);
		
		
		//mengautr navbar
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.actiobar_title);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tips, menu);
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
	
	private void populatingData(){
		tipsItems.add(new TipsClass("Tips pertama", R.drawable.tips1, "Peranan Ibu untuk menentukan “Apa yang akan dimakan” anak sangat penting. Tingkatkan pengetahuan tentang kebutuhan gizi balita, jenis, makanan, susunan menu yang kreatif serta ciptakan suasana yang menyenangkan di saat makan.", "",R.drawable.icon_1));
		tipsItems.add(new TipsClass("Tips Kedua", R.drawable.tips2, "Jangan langsung pasrah atau menyerah saat disajikan makanan, anak berkata, “aku tidak menyukainya”. Penelitian membuktikan bahwa untuk menawari anak makanan baru, diperlukan 10 kesempatan pada saat yang berbeda dan baru berhasil. Moto “Coba dan Coba lagi” harus selalu diterapkan.", "",R.drawable.icon_1));
		tipsItems.add(new TipsClass("Tips Ketiga", R.drawable.tips3, "Perkenalkan rasa baru kepada anak secara rutin. Mulai dari dalam kandungan dengan mengkonsumsi makanan ibu hamil, ASI dan makanan padat", "",R.drawable.icon_1));
		tipsItems.add(new TipsClass("Tips Keempat", 0, "Jadilah teladan, panutan, dan idola yang baik bagi Si Kecil. Sajikan dan makanlah berbagai macam makanan. Biarkan anak melihat ibu dan anggota keluarga lain menikmati makanan. Dudukanlah Si Kecil di samping Anda dan biarkan dia bereaksi.", "",R.drawable.icon_1));
		tipsItems.add(new TipsClass("Tips Kelima", 0, "Perkuat sikap positif makan anak dengan cara memberikan komentar positif setiap kali anak Anda mengkonsumsi makanan yang sehat dan mencoba makan dengan benar.", "",R.drawable.icon_1));
		tipsItems.add(new TipsClass("Tips Keenam", 0, "Manfaatkan selera makan Si Kecil. Kembangkan selera makannya dan berikan makanan sesuai waktu yang dia inginkan dan tentu saja berikan pada saat Si Kecil lapar.", "",R.drawable.icon_1));
		tipsItems.add(new TipsClass("Tips Ketujuh", 0,"Lingkungan dan suasana makan harus tenang dan bebas emosi.\n1.Jangan melarang dan memaksakan makanan tertentu karena sikap seperti itu akan berdampak negatif terhadap pola makan anak.\n2.Jangan terlalu dan selalu menekankan masalah makanan.\n3.Izinkan Si Kecil untuk sekali-kali mengkonsumsi minuman dan makanan yang disukainya, dengan catatan: setelah semua makanan sehat dan baik dikonsumsinya.", "",R.drawable.icon_1));
		tipsItems.add(new TipsClass("Tips Kedelapan", 0, "Ubahlah letak penyimpanan makanan.\n1.Makanan sehat disimpan di tempat yang mudah terlihat dan dijangkau.\n2.Simpan makanan kudapan ditempat yang tersembunyi sehingga Ibu bisa memantau jenis dan jumlah yang dimakan oleh anak.", "",R.drawable.icon_1));
		tipsItems.add(new TipsClass("Tips Kesembilan",0,"Tetap santai, tenang dan konsisten dan jangan menyerah pada tuntutan anak dan emosi mereka.","",R.drawable.icon_1));
		tipsItems.add(new TipsClass("Tips Kesepuluh", 0, "Tumbuhkan rasa bangga dan ucapkan selamat pada diri sendiri karena sudah berhasil memerankan tugas dengan baik untuk membentuk sumber daya manusia yang berkualitas dan cerdas, kunci keberhasilan di masa depan.", "",R.drawable.icon_1));
	}
}
