package f1.com.example.rohit.homeword4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class TriviaStat extends AppCompatActivity {

    LinearLayout ls;
    ProgressBar pb;
    TextView tv;
    ArrayList<Question> finalq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_stat);
        ls =(LinearLayout)findViewById(R.id.statslayout);
        pb=(ProgressBar)findViewById(R.id.pbpec);
        tv=(TextView)findViewById(R.id.txtpercentage);

        if(getIntent().getExtras()!=null)
        {
            finalq=(ArrayList<Question>)getIntent().getExtras().getSerializable("finallist");
        }

        displaystats();
    }

public void displaystats()
{

    int ccount=0;
    LayoutInflater inflater = (LayoutInflater)TriviaStat.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    for(int i=0;i<finalq.size();i++) {

        Question q=finalq.get(i);
        View itemBox = inflater.inflate(R.layout.statchildrow, null);
        ((TextView)itemBox.findViewById(R.id.Qtext)).setText(q.getText());
        int ua=q.getUseranswer();
        int ca=q.getCanswer();
        if(ua==ca)
        {
            ccount++;
        }
        if(ua>0) {
            ((TextView) itemBox.findViewById(R.id.Uatext)).setText(q.getChoice().get(ua - 1));

        }
        if(ua!=ca)
        {
            ((TextView) itemBox.findViewById(R.id.Uatext)).setBackgroundColor( getResources().getColor(R.color.Redcolor));
            ((TextView)itemBox.findViewById(R.id.Catext)).setText(q.getChoice().get(ca-1));
            ls.addView(itemBox);
        }
    }

    pb.setMax(finalq.size());
    pb.setProgress(ccount);
    double percentage=((double)ccount/(double)finalq.size())*100;
    tv.setText(String.valueOf(percentage)+"%");
}


    public void finishapp(View v)
    {
        Intent i=new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
       finish();
        startActivity(i);
    }
}
