package mk.ukim.finki.jmm.sports;

import java.util.List;

import mk.ukim.finki.jmm.sports.adapter.SportsAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Sports extends Activity {

	private ListView mList;
	private SportsAdapter mAdapter;
	private String[] mStrings = {
			"http://scni.media.clients.ellingtoncms.com/img/photos/2011/12/14/BasketballLOGO_t300.jpg?8aff03de2423e912a2467e97388a07f5331c05b6",
			"http://2.bp.blogspot.com/_VoPn3PtbnRA/TBjgzA3jl6I/AAAAAAAAAt4/gkDbIXGy6MA/s1600/football-logo.jpg",
			"http://www.sportpaint.de/uploads/tx_csprodukte/Power-Play_new_01.jpg",
			"http://static.giantbomb.com/uploads/scale_small/0/140/376658-tennis_ball.46170428_std.jpg",
			"http://2.bp.blogspot.com/-jFCSP62pEW4/TneqrmlqdEI/AAAAAAAAAps/xFtgjTjM04c/s1600/boom.jpg",
			"http://t1.gstatic.com/images?q=tbn:ANd9GcQ7DuwmOwD5DYZKaZNDb4QIuaYwEQFUjw0_Pob9zkbXwB0yJKJK",
			"http://t1.gstatic.com/images?q=tbn:ANd9GcQHYTaAgGZRrZItemJiv8iQsaYZMSkWu3uTIQaB3idfU6P5pL9m"
	};
	
	private String[] mTitles = { 
			"Basketball",
			"Football",
			"Handball",
			"Tenis",
			"Ping-Pong",
			"Golf",
			"Rugby"
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);
		

		mList=(ListView)findViewById(R.id.lista);
		mAdapter=new SportsAdapter(this, mStrings, mTitles);
		mList.setAdapter(mAdapter);
		
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				
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

}
