package app.rama.posyandu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import app.rama.lib.SessionHandler;


public class MainActivity extends Activity {
	private SessionHandler sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartAnimations();//Menjalankan Method Start Animasi
        
        sh = new SessionHandler(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(sh.isRegis()){
					
					Intent i = new Intent(MainActivity.this, Dashboard.class);
					 // Closing all the Activities
		            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		             
		            // Add new Flag to start new Activity
		            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		            startActivity(i);
		            finish();
				} else {
					Intent i = new Intent(MainActivity.this, Welcome.class);
					startActivity(i);
					finish();
				}
			}
		}, 4000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
  //Disini Deklarasi Animasinya(StartAnimation)
  	private void StartAnimations() {
  		// TODO Auto-generated method stub
  		//Animasi untuk Frame Layout mengunakan alpha.xml
  		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
          anim.reset();
          RelativeLayout l=(RelativeLayout) findViewById(R.id.relative1);
          l.clearAnimation();
          l.startAnimation(anim);
          
          
        //Animasi untuk Gambar mengunakan Translate.xml
          anim = AnimationUtils.loadAnimation(this, R.anim.translate);
          anim.reset();
          ImageView iv = (ImageView) findViewById(R.id.splashimg);
          iv.clearAnimation();
          iv.startAnimation(anim);
          
        //Animasi untuk ProgressBar1 mengunakan alpha.xml
          Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.translate_left);
          ImageView iv2 = (ImageView) findViewById(R.id.imageView1);
          iv2.clearAnimation();
          iv2.startAnimation(anim2);
  	}
}
