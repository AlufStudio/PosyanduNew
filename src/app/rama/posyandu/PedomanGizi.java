package app.rama.posyandu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class PedomanGizi extends Activity {
	public static final String M_CURRENT_TAB = "M_CURRENT_TAB";
	private TabHost mTabHost;
	private String mCurrentTab;

	public static final String TAB_BBI = "bbi";
	public static final String TAB_TBI = "tbi";
	public static final String TAB_AIR = "air";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedoman_gizi);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);

		mTabHost.setup();

		if (savedInstanceState != null) {
			mCurrentTab = savedInstanceState.getString(M_CURRENT_TAB);
			initializeTabs();
			mTabHost.setCurrentTabByTag(mCurrentTab);
			/*
			 * when resume state it's important to set listener after
			 * initializeTabs
			 */
			mTabHost.setOnTabChangedListener(listener);
		} else {
			mTabHost.setOnTabChangedListener(listener);
			initializeTabs();
		}

	}

	@SuppressLint("InflateParams")
	private View createTabView(final int id, final String text) {
		View view = LayoutInflater.from(this).inflate(R.layout.tags_icon, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
		imageView.setImageDrawable(getResources().getDrawable(id));
		TextView textView = (TextView) view.findViewById(R.id.tab_text);
		textView.setText(text);
		return view;
	}

	public void initializeTabs() {

		TabHost.TabSpec spec;

		spec = mTabHost.newTabSpec(TAB_BBI);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(R.id.realtabcontent);
			}
		});
		spec.setIndicator(createTabView(R.drawable.bottom_icon_berat,
				getString(R.string.title_activity_bbi)));
		mTabHost.addTab(spec);

		spec = mTabHost.newTabSpec(TAB_TBI);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(R.id.realtabcontent);
			}
		});
		spec.setIndicator(createTabView(R.drawable.bottom_icon_tinggi,
				getString(R.string.title_activity_tbi)));
		mTabHost.addTab(spec);

		spec = mTabHost.newTabSpec(TAB_AIR);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(R.id.realtabcontent);
			}
		});
		spec.setIndicator(createTabView(R.drawable.bottom_icon_air,
				getString(R.string.title_activity_air)));
		mTabHost.addTab(spec);

	}

	TabHost.OnTabChangeListener listener = new TabHost.OnTabChangeListener() {
		public void onTabChanged(String tabId) {

			mCurrentTab = tabId;
			
			setTabColor(mTabHost);

			if (tabId.equals(TAB_BBI)) {
				pushFragments(new BBI(), false, false, null);
			} else if (tabId.equals(TAB_TBI)) {
				pushFragments(new TBI(), false, false, null);
			} else if (tabId.equals(TAB_AIR)) {
				pushFragments(new Air(), false, false, null);
			}

		}
	};

	public void pushFragments(Fragment fragment, boolean shouldAnimate,
			boolean shouldAdd, String tag) {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();

		ft.replace(R.id.realtabcontent, fragment, tag);

		if (shouldAdd) {
			/*
			 * here you can create named backstack for realize another logic.
			 * ft.addToBackStack("name of your backstack");
			 */
			ft.addToBackStack(null);
		} else {
			/*
			 * and remove named backstack:
			 * manager.popBackStack("name of your backstack",
			 * FragmentManager.POP_BACK_STACK_INCLUSIVE); or remove whole:
			 * manager.popBackStack(null,
			 * FragmentManager.POP_BACK_STACK_INCLUSIVE);
			 */
			manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
		ft.commit();
	}

	/*
	 * If you want to start this activity from another
	 */
	public static void startUrself(Activity context) {
		Intent newActivity = new Intent(context, PedomanGizi.class);
		newActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(newActivity);
		context.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pedoman_gizi, menu);
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

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString(M_CURRENT_TAB, mCurrentTab);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.layer_tab); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundResource(R.drawable.bg_pressed); // selected
    }
}
