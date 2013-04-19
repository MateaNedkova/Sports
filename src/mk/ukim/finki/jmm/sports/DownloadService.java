package mk.ukim.finki.jmm.sports;
//package mk.ukim.finki.jmm.sports;

import mk.ukim.finki.jmm.sports.task.DownloadTask;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		DownloadTask task = new DownloadTask(this);
		task.execute("http://www.alpinaut.com/api/json?lat=41.051866&lon=0.871659&rad=25&num=10");
		return super.onStartCommand(intent, flags, startId);
	}

}
