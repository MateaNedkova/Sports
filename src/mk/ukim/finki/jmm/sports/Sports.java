package mk.ukim.finki.jmm.sports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mk.ukim.finki.jmm.sports.adapter.SportsAdapter;
import mk.ukim.finki.jmm.sports.database.ToDoDao;
import mk.ukim.finki.jmm.sports.model.SportItem;
import mk.ukim.finki.jmm.sports.task.DownloadTask;
import mk.ukim.finki.jmm.sports.task.GetDataBaseTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

//activity so listata i kopceto
public class Sports extends Activity {

	private ListView mList;
	public SportsAdapter mAdapter;
	private  Button mbutton;
//	private String[] mStrings = {
//			"http://scni.media.clients.ellingtoncms.com/img/photos/2011/12/14/BasketballLOGO_t300.jpg?8aff03de2423e912a2467e97388a07f5331c05b6",
//			"http://2.bp.blogspot.com/_VoPn3PtbnRA/TBjgzA3jl6I/AAAAAAAAAt4/gkDbIXGy6MA/s1600/football-logo.jpg",
//			"http://www.sportpaint.de/uploads/tx_csprodukte/Power-Play_new_01.jpg",
//			"http://static.giantbomb.com/uploads/scale_small/0/140/376658-tennis_ball.46170428_std.jpg",
//			"http://2.bp.blogspot.com/-jFCSP62pEW4/TneqrmlqdEI/AAAAAAAAAps/xFtgjTjM04c/s1600/boom.jpg",
//			"http://t1.gstatic.com/images?q=tbn:ANd9GcQ7DuwmOwD5DYZKaZNDb4QIuaYwEQFUjw0_Pob9zkbXwB0yJKJK",
//			"http://t1.gstatic.com/images?q=tbn:ANd9GcQHYTaAgGZRrZItemJiv8iQsaYZMSkWu3uTIQaB3idfU6P5pL9m"
//	};
	
//	private String[] mTitles = { 
//			"Basketball",
//			"Football",
//			"Handball",
//			"Tenis",
//			"Ping-Pong",
//			"Golf",
//			"Rugby"
//	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);
		SharedPreferences sp = getSharedPreferences("Sportovi",MODE_PRIVATE);
		Long number = sp.getLong("userChoice", 0);
		Long currentTime = new Date().getTime();

		List<SportItem> sport_data = new ArrayList<SportItem>();
		mList=(ListView)findViewById(R.id.lista);
		mAdapter=new SportsAdapter(this, sport_data);

		//ako poslednoto vreme  na prevzemanje na podatoci e pogolemo od 24 casa
		if(currentTime - number>24*60*60*1000)
		   {
			//povikuvanje na servisot
			IntentFilter filter = new IntentFilter(DownloadTask.ITEMS_DOWNLOADED_ACTION);
			registerReceiver(new OnDownloadRefreshReceiver(), filter);
			startService(new Intent(Sports.this, DownloadService.class));
			Toast.makeText(this, "Data from service", Toast.LENGTH_LONG).show();
		   }
		   else
		   {
			   //prikazuvanje na podatocite od bazata
			   	GetDataBaseTask getdb = new GetDataBaseTask(this, mAdapter);
		   		getdb.execute();
		   		Toast.makeText(this, "Data from database", Toast.LENGTH_LONG).show();
		   }
		mList.setAdapter(mAdapter);
//		mList.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position,
//					long id) {
//				// TODO Auto-generated method stub
//				
//			}
//		});		
		mbutton = (Button)findViewById(R.id.kopce);
		mbutton.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			IntentFilter filter = new IntentFilter(DownloadTask.ITEMS_DOWNLOADED_ACTION);
			registerReceiver(new OnDownloadRefreshReceiver(), filter);
			startService(new Intent(Sports.this, DownloadService.class));
		}
	});
			}

	 @Override
	    public void onDestroy()
	    {
	        mList.setAdapter(null);
	        super.onDestroy();
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sports, menu);
		getMenuInflater().inflate(R.menu.lang, menu);
		return true;
	}
	
	
	class OnDownloadRefreshReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
//			ToDoDao dao = new ToDoDao(context);
//			dao.open();
//			
//			List<SportItem> result = dao.getAllItems();
//			mAdapter.addAll(result);
//			
//			dao.close();
//			Sports.this.unregisterReceiver(this);
			GetDataBaseTask gdb = new GetDataBaseTask(context, mAdapter);
			gdb.execute();
			Sports.this.unregisterReceiver(this);

		}
	}
}
