package utils;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class HttpUtils {

	public static String getStringResult(String path) {
		
		try {
			
			URL url = new URL(path);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			
			conn.connect();
			
			if(conn.getResponseCode() == 200)
			{
				InputStream is = conn.getInputStream();
				
				StringBuilder sBuilder = new StringBuilder();
				
				byte[] buffer = new byte[1024];
				int len = 0;
				
				while ((len = is.read(buffer))!=-1) {
					
					sBuilder.append(new String(buffer, 0, len));
				}
				
				return sBuilder.toString();
			}
			
		} catch (Exception e) {

		}
		
		return null;
	}
	


	public static Bitmap getBitmapResult(String imagePath) {
		
		try {
			
			URL url = new URL(imagePath);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			
			conn.connect();
			
			if(conn.getResponseCode() == 200)
			{
				InputStream is = conn.getInputStream();
				
				BufferedInputStream bis = new BufferedInputStream(is);
				
				Bitmap bitmap = BitmapFactory.decodeStream(bis);
				
				return bitmap;
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
