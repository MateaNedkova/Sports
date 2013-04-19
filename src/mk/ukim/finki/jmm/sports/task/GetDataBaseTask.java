package mk.ukim.finki.jmm.sports.task;

import java.util.List;

import mk.ukim.finki.jmm.sports.adapter.SportsAdapter;
import mk.ukim.finki.jmm.sports.database.ToDoDao;
import mk.ukim.finki.jmm.sports.model.SportItem;
import android.content.Context;
import android.os.AsyncTask;

public class GetDataBaseTask extends AsyncTask<Void, Void, List<SportItem>> {

	public SportsAdapter mAdapter;
	public ToDoDao todao;
	protected Context context;
	
	public GetDataBaseTask(Context context, SportsAdapter sa) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.mAdapter=sa;
		this.todao = new ToDoDao(context);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		todao.open();
	}
	
	@Override
	protected List<SportItem> doInBackground(Void... params) {
		List<SportItem> sport_data = todao.getAllItems();
		return sport_data;
	}
	
	@Override
	protected void onPostExecute(List<SportItem> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		mAdapter.clear();
		todao.close();
		mAdapter.addAll(result);
	}
	
}
