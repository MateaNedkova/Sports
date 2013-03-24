package mk.ukim.finki.jmm.sports.adapter;
import mk.ukim.finki.jmm.sports.ImageLoader;
import mk.ukim.finki.jmm.sports.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SportsAdapter extends BaseAdapter {
    
    private Activity activity;
    private String[] data;
    private String[] titles;
    private LayoutInflater inflater;
    public ImageLoader imageLoader; 
     
    public SportsAdapter(Activity a, String[] d, String[] t) {
        activity = a;
        data=d;
        titles = t;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    class TodoHoler {
	     public View vi;	 
	     public ImageView img;
	     public TextView text;
	    
	   }
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	
        View vi=convertView;
        if(convertView==null)
        {
        	vi = inflater.inflate(R.layout.item_todo, null);
        }
		    
		TextView text = (TextView) vi.findViewById(R.id.text);
	    ImageView img = (ImageView) vi.findViewById(R.id.image);
        text.setText(titles[position]);
        imageLoader.DisplayImage(data[position], img);
	    
	    return vi;
     
    }
}