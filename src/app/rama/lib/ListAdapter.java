package app.rama.lib;


import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.rama.modal.TipsClass;
import app.rama.posyandu.R;

public class ListAdapter extends BaseAdapter {
	private Activity activity;
    private LayoutInflater inflater;
    private List<TipsClass> tipsNya;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 
    public ListAdapter(Activity activity, List<TipsClass> tipsNya) {
        this.activity = activity;
        this.tipsNya = tipsNya;
    }
 
    @Override
    public int getCount() {
        return tipsNya.size();
    }
 
    @Override
    public Object getItem(int location) {
        return tipsNya.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @SuppressLint("InflateParams") @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);
 
        TextView name = (TextView) convertView.findViewById(R.id.name);
        //TextView timestamp = (TextView) convertView
        //        .findViewById(R.id.timestamp);
        TextView statusMsg = (TextView) convertView
                .findViewById(R.id.txtStatusMsg);
        //TextView url = (TextView) convertView.findViewById(R.id.txtUrl);
        ImageView profilePic = (ImageView) convertView.findViewById(R.id.profilePic);
        ImageView feedImageView = (ImageView) convertView.findViewById(R.id.feedImage1);
 
        //tipsNya item = feedItems.get(position);
        TipsClass tips = tipsNya.get(position);
        
        name.setText(tips.getTitle());
 
        // Converting timestamp into x ago format
        //CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
        //        Long.parseLong(item.getTimeStamp()),
        //        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        //timestamp.setText(timeAgo);
 
        // Chcek for empty status message
        if (!TextUtils.isEmpty(tips.getContent())) {
            statusMsg.setText(tips.getContent());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }
 
        // user profile pic
        profilePic.setImageResource(tips.getIcon());
        
        if(tips.getGambar() != 0){
        	feedImageView.setImageResource(tips.getGambar());
        } else {
        	feedImageView.setVisibility(View.GONE);
        }
        
        return convertView;
    }
}
