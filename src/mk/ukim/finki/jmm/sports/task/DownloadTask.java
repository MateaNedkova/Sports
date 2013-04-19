package mk.ukim.finki.jmm.sports.task;

import java.util.Date;
import java.util.List;

import mk.ukim.finki.jmm.sports.DownloadService;
import mk.ukim.finki.jmm.sports.Sports;
import mk.ukim.finki.jmm.sports.task.DownloadTask;
import mk.ukim.finki.jmm.sports.database.ToDoDao;
import mk.ukim.finki.jmm.sports.model.SportItem;
import mk.ukim.finki.jmm.sports.utils.Downloader;
import mk.ukim.finki.jmm.sports.utils.OnContentDownloaded;
import mk.ukim.finki.jmm.sports.utils.OnToDoItemsDownloaded;
//import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
//import android.os.Handler;
import android.widget.Toast;

//Task za prevzemanje na podatocite od servisot
public class DownloadTask extends AsyncTask<String, Void, List<SportItem>> {
 
	public GetDataBaseTask getdb;
	public static final String ITEMS_DOWNLOADED_ACTION = "mk.ukim.finki.jmm.sports.ITEMS_DOWNLOADED_ACTION";
	private static final int MODE_PRIVATE = 0;
	private Exception exception = null;
	protected Context context;

	public DownloadTask(Context context) {
		this.context = context;
	}

	@Override
	//ja imame listata od SportItemi so podatocite od servisot
	protected List<SportItem> doInBackground(String... params) {
		if (params.length < 1) {
			exception = new IllegalArgumentException(
					"At least one argument for the download url expected. ");
			return null;
		} else {

			String url = params[0];
			OnContentDownloaded<List<SportItem>> handler = new OnToDoItemsDownloaded();
			try {
				Downloader.getFromUrl(url, handler);
				return handler.getResult();
			} catch (Exception ex) {
				exception = ex;
				return null;
			}
		}
	}

	@Override
	protected void onPostExecute(List<SportItem> result) {
		super.onPostExecute(result);
		if (exception != null) {
			Toast.makeText(context, "Error: " + exception.getMessage(),
					Toast.LENGTH_LONG).show();
		} else {

			ToDoDao dao = new ToDoDao(context);
			dao.open();
			dao.delete();
			for (SportItem item : result) {
				dao.insert(item);
			}
			dao.close();
			
			//shared preferences za vremeto koga se zimaat podatocite od servisot
			SharedPreferences sp = context.getSharedPreferences("Sportovi",MODE_PRIVATE);
			SharedPreferences.Editor prefEditor = sp.edit();
		    prefEditor.putLong("userChoice",new Date().getTime()); //ja stavam promenlivata sto sakam da mi se zacuva
		    prefEditor.commit();
		    
			Intent intent=new Intent(ITEMS_DOWNLOADED_ACTION);
			context.sendBroadcast(intent);
		}
	}

}
