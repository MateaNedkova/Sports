package mk.ukim.finki.jmm.sports.utils;

public interface OnContentDownloaded<T> {
	
	public void onContentDownloaded(String content, int httpStatus) throws Exception;
	
	public T getResult();

}
