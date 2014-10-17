package app.rama.lib;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.rama.posyandu.Daftar;
import app.rama.posyandu.R;

public class FullScreenImageAdapter extends PagerAdapter {
	private Activity _activity;
    private int[] _imageNya = new int[] {
    		R.drawable.bullet1,
    		R.drawable.bullet2,
    		R.drawable.bullet3
    };
    
    private String[] _textnya = new String[] {
    		"- Untuk memastikan Peningkatan Gizi pada si Balita dan Ibu yang lagi menyusui -",
    		"- Menekan tingkat kematian bayi mulai dari kota hingga pelosok -",
    		"- Gratis, cepat, dan juga bersahabat untuk ibu dan anak  -",
    		""
    };
    private LayoutInflater inflater;
 
    // constructor
    public FullScreenImageAdapter(Activity activity) {
        this._activity = activity;
    }
 
    @Override
    public int getCount() {
        return _textnya.length;
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
     
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;
        Button btnMulai;
        TextView textSlide;
  
        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_welcome, container,
                false);
  
        textSlide = (TextView)viewLayout.findViewById(R.id.textSlide);
        btnMulai = (Button)viewLayout.findViewById(R.id.btnClose);
        imgDisplay = (ImageView)viewLayout.findViewById(R.id.imgNya);
        
        
        if(position < 3) {
        	textSlide.setText(_textnya[position]);
	        imgDisplay.setImageResource(_imageNya[position]);
        }
         
        
        if(position == 3){
        	imgDisplay.setVisibility(View.INVISIBLE);
        	textSlide.setVisibility(View.INVISIBLE);
        	btnMulai.setVisibility(View.VISIBLE);
        	
        	// close button click event
            btnMulai.setOnClickListener(new View.OnClickListener() {            
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(_activity,Daftar.class);
                    _activity.startActivity(i);
                    _activity.finish();
                }
            });
        }
        
  
        ((ViewPager) container).addView(viewLayout);
  
        return viewLayout;
    }
     
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
  
    }
}
