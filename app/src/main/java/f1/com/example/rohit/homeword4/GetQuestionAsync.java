package f1.com.example.rohit.homeword4;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rohit on 2/7/2017.
 */

public class GetQuestionAsync extends AsyncTask<String,Void,ArrayList<Question>>{

    Idata act;
    public GetQuestionAsync(Idata act)
    {
this.act=act;
    }


    @Override
    protected ArrayList<Question> doInBackground(String... params)
    {

        try {

            URL urllink=new URL(params[0]);
            HttpURLConnection con=(HttpURLConnection) urllink.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statuscode=con.getResponseCode();
            if(statuscode==HttpURLConnection.HTTP_OK) {

                BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));

                StringBuilder sb=new StringBuilder();
                String line=br.readLine();

                while(line!=null)
                {
                    sb.append(line);
                    line=br.readLine();
                }

                return Questionutil.QuestionJsonParser.getparsedquestions(sb.toString());

            }


            } catch (java.io.IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        act.setdata(questions);
        super.onPostExecute(questions);
    }

    static public interface  Idata
    {
        public void setdata(ArrayList<Question> data);
    }
}
