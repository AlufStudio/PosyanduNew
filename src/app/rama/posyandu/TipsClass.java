package app.rama.posyandu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class TipsClass extends Activity {
	public static final String M_CURRENT_TAB = "M_CURRENT_TAB";
	private TabHost mTabHost;
	private String mCurrentTab;

	public static final String TAB_UMUM = "umum";
	public static final String TAB_ANAK = "anak";
	public static final String TAB_IBU = "ibu";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tips_class);
		
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
	
	@SuppressLint("InflateParams") private View createTabView(final int id, final String text) {
		View view = LayoutInflater.from(this).inflate(R.layout.tags_icon, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
		imageView.setImageDrawable(getResources().getDrawable(id));
		TextView textView = (TextView) view.findViewById(R.id.tab_text);
		textView.setText(text);
		return view;
	}

	public void initializeTabs() {

		TabHost.TabSpec spec;

		spec = mTabHost.newTabSpec(TAB_UMUM);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(R.id.realtabcontent);
			}
		});
		spec.setIndicator(createTabView(R.drawable.bottom_icon_umum,
				getString(R.string.title_umum)));
		mTabHost.addTab(spec);

		spec = mTabHost.newTabSpec(TAB_IBU);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(R.id.realtabcontent);
			}
		});
		spec.setIndicator(createTabView(R.drawable.bottom_icon_ibu,
				getString(R.string.title_ibu)));
		mTabHost.addTab(spec);

		spec = mTabHost.newTabSpec(TAB_ANAK);
		spec.setContent(new TabHost.TabContentFactory() {
			public View createTabContent(String tag) {
				return findViewById(R.id.realtabcontent);
			}
		});
		spec.setIndicator(createTabView(R.drawable.bottom_icon_anak,
				getString(R.string.title_anak)));
		mTabHost.addTab(spec);

	}

	TabHost.OnTabChangeListener listener = new TabHost.OnTabChangeListener() {
		public void onTabChanged(String tabId) {

			mCurrentTab = tabId;
			
			setTabColor(mTabHost);

			if (tabId.equals(TAB_UMUM)) {
				pushFragments(new Tips(), false, false, null);
			} else if (tabId.equals(TAB_IBU)) {
				pushFragments(new Tips(), false, false,
						null);
			} else if (tabId.equals(TAB_ANAK)) {
				pushFragments(new Tips(), false, false,
						null);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tips_class, menu);
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
	
	public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.layer_tab); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundResource(R.drawable.bg_pressed); // selected
    }
}
