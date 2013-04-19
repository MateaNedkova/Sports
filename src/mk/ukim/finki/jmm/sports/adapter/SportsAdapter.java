package mk.ukim.finki.jmm.sports.adapter;
import java.util.List;
import mk.ukim.finki.jmm.sports.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import mk.ukim.finki.jmm.sports.model.SportItem;

//za definiranje na itemite vo listata
public class SportsAdapter extends BaseAdapter {
    
    private Activity activity;
    private String[] data;
    //private String[] titles;
    private LayoutInflater inflater; 
    //public ImageLoader imageLoader; 
    private List<SportItem> items;
    public SportsAdapter(Activity a, List<SportItem> lista  ) {
    	activity = a;
    	items=lista;
	    //titles = t;
	    inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    //klasa za toa sto ke sodrzi eden item od listata
    class TodoHoler {
	     public View vi;	 
	     public TextView text_category;
	     public TextView text_name;
	    
	   }
    
    public View getView(int position, View convertView, ViewGroup parent) {
    	
        View vi=convertView;
        if(convertView==null)
        {
        	vi = inflater.inflate(R.layout.item_todo, null);
        }
		
        SportItem si = items.get(position);
		TextView text_category = (TextView) vi.findViewById(R.id.category);
		TextView text_name = (TextView) vi.findViewById(R.id.name);
		
		//slikata e za prvata laboratoriska
	    //ImageView img = (ImageView) vi.findViewById(R.id.image);
	    
		text_category.setText(si.getCategory());
        text_name.setText(si.getName());
        
        //imageLoader.DisplayImage(data[position], img);
	    
	    return vi;
     
    }
    public void addAll(List<SportItem> items) {
    	clear();
		this.items.addAll(items);
		notifyDataSetChanged();
	}
    
	public void clear(){
		items.clear();
		notifyDataSetInvalidated();
	}
}