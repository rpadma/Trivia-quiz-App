package f1.com.example.rohit.homeword4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rohit on 2/8/2017.
 */

public class GetImageAsync extends AsyncTask<String,Void,Bitmap> {
    Iimage act;

    public GetImageAsync(Iimage idata)
    {
        this.act=idata;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        InputStream in=null;
        try {
            URL imageurl=new URL(params[0]);
            HttpURLConnection con=(HttpURLConnection)imageurl.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            in=con.getInputStream();
            Bitmap b= BitmapFactory.decodeStream(in);
            return b;

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        finally {
            if(in!=null)
            {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
         act.setIImage(bitmap);
        super.onPostExecute(bitmap);
    }


    static public interface Iimage{

        public void setIImage(Bitmap b);
    }


}
