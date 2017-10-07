package f1.com.example.rohit.homeword4;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetQuestionAsync.Idata {

    ArrayList<Question> qlist;
    ImageView iv;
    ProgressBar pb;
    Button btnexit,btnstart;

    String BaseURL="http://dev.theappsdr.com/apis/trivia_json/index.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=(ProgressBar)findViewById(R.id.progressBar2);

        iv=(ImageView)findViewById(R.id.imageView);
        iv.setVisibility(View.INVISIBLE);
        btnexit=(Button)findViewById(R.id.btnexit);
        btnstart=(Button)findViewById(R.id.btnstart);
        ((TextView)findViewById(R.id.textView2)).setVisibility(View.INVISIBLE);
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        if(ni!=null && ni.isConnected()) {
            new GetQuestionAsync(this).execute(BaseURL);
       }
        else
        {
            Toast.makeText(this,"No internet connection",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void setdata(ArrayList<Question> data) {

        qlist=data;
        pb.setVisibility(View.GONE);
        ((TextView)findViewById(R.id.pbtxtview)).setVisibility(View.INVISIBLE);
        iv.setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.textView2)).setVisibility(View.VISIBLE);
        btnstart.setBackgroundColor( getResources().getColor(R.color.Customcolor));
/*
        String temps="";
        for (int i=0;i<data.size();i++)
        {
            temps+=data.get(i).toString();
        }

        Toast.makeText(this,temps,Toast.LENGTH_LONG).show();
*/
    }

    public void ExitClick(View v)
    {

        finish();
    }


    public void StartClick(View v)
    {
        Intent i=new Intent(this,Trivia.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("qlist",qlist);
        startActivity(i);
    }
}
