package f1.com.example.rohit.homeword4;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rohit on 2/7/2017.
 */

public class Questionutil {

    public static class QuestionJsonParser{

        public static ArrayList<Question> getparsedquestions(String in)
        {
            ArrayList<Question> questions = new ArrayList<Question>();

            try {
                JSONObject root =new JSONObject(in);
                JSONArray qarra=root.getJSONArray("questions");

                for(int i=0;i<qarra.length();i++)
                {

                    JSONObject jsonobj=qarra.getJSONObject(i);
                    Question q=new Question();
                    q.setId(jsonobj.getInt("id"));
                    q.setText(jsonobj.getString("text"));

                    if(jsonobj.has("image")) {
                        q.setImageurl(jsonobj.getString("image"));
                    }else
                    {
                        q.setImageurl("");
                    }
                    JSONObject answerobject = jsonobj.getJSONObject("choices");
                   JSONArray carray= answerobject.getJSONArray("choice");
                    q.setCanswer(answerobject.getInt("answer"));

                    ArrayList<String> choicearray=new ArrayList<String>();
                    for(int j=0;j<carray.length();j++)
                    {
                        choicearray.add(carray.getString(j));

                    }

                     q.setChoice(choicearray);

                   questions.add(q);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return questions;
        }
    }
}
