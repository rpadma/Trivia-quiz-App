package f1.com.example.rohit.homeword4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Trivia extends AppCompatActivity implements GetImageAsync.Iimage {

    ArrayList<Question> qlist=new ArrayList<Question>();
    ImageView iv;
    TextView qnotxt;
    TextView noimage;
    RadioGroup rg;
    int curr=0;
    Button btnprev,btnnext;
    ProgressBar pb;
    LinearLayout ll;
    TextView pbtxti,timetxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        ll=(LinearLayout)findViewById(R.id.questionarea);
        iv=((ImageView)findViewById(R.id.qimageview));
        pbtxti=(TextView)findViewById(R.id.pbimagetxt);
        timetxt=(TextView)findViewById(R.id.timetxt);
        iv.setVisibility(View.INVISIBLE);
        noimage=(TextView)findViewById(R.id.Noimagetxt);
        btnprev=(Button)findViewById(R.id.btnprev);
        btnnext=((Button)findViewById(R.id.btnnext));
        qnotxt=(TextView)findViewById(R.id.Qno);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        if(getIntent().getExtras()!=null)
        {
            qlist=(ArrayList<Question>)getIntent().getExtras().getSerializable("qlist");
        }


        // String imageurl=qlist.get(curr).getImageurl();
       //  new GetImageAsync(this).execute(imageurl);
        displayquestions(0);
        btnprev.setEnabled(false);

        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                timetxt.setText("Time Left: "+ millisUntilFinished / 1000+" seconds");
            }

            public void onFinish() {
               ontimerfinish();
            }
        }.start();

    }


    public void ontimerfinish()
    {
        Intent i=new Intent(this,TriviaStat.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("finallist",qlist);
        startActivity(i);
    }

     public void displayquestions(int qnumber)
     {
             String imageurl = qlist.get(qnumber).getImageurl();
             if(imageurl.length()>0) {
                 new GetImageAsync(this).execute(imageurl);
                 noimage.setVisibility(View.INVISIBLE);
             }
             else
             {
                 pb.setVisibility(View.INVISIBLE);
                 pbtxti.setVisibility(View.INVISIBLE);
                 noimage.setVisibility(View.VISIBLE);
                 iv.setVisibility(View.INVISIBLE);
             }
             ll.removeAllViews();
             LayoutInflater inflater = (LayoutInflater) Trivia.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             View itemBox = inflater.inflate(R.layout.childrow, null);
             rg = (RadioGroup) itemBox.findViewById(R.id.radiogrp);
             Question q = qlist.get(qnumber);
         int temp=q.getId()+1;
             qnotxt.setText("Q"+String.valueOf(temp));
             TextView tv = new TextView(this);
         ActionBar.LayoutParams lp=new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
         tv.setLayoutParams(lp);
             tv.setText(q.getText());
             ll.addView(tv);
             ll.addView(itemBox);
             for (int k = 0; k < q.getChoice().size(); k++) {
                 RadioButton rb = new RadioButton(this);
                 rb.setText(q.getChoice().get(k));
                 rb.setId(k+1);
                 rg.addView(rb);
             }
         if(q.getUseranswer()>0)
         {
             //q.getUseranswer();
             rg.check(q.getUseranswer());
         }

            // Toast.makeText(this, qlist.get(qnumber).toString(), Toast.LENGTH_SHORT).show();
     }


    public void NextClick(View v)
    {
        getselectedoption(curr);
        if(curr==qlist.size()-1)
        {
            Toast.makeText(this,"Last Question",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,TriviaStat.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("finallist",qlist);
            startActivity(i);
        }
        else
        {
            btnprev.setEnabled(true);
            curr++;
            iv.setVisibility(View.INVISIBLE);
            pb.setVisibility(View.VISIBLE);
            pbtxti.setVisibility(View.VISIBLE);
            displayquestions(curr);

            if(curr==qlist.size()-1)
            {
                btnnext.setText("Finish");
            }
        }
    }

    public void PrevClick(View v)
    {
        getselectedoption(curr);
        if(curr<1)
        {
            btnprev.setEnabled(false);
            //Toast.makeText(this,"First Question",Toast.LENGTH_SHORT).show();
        }
        else
        {
            curr--;
            iv.setVisibility(View.INVISIBLE);
            pb.setVisibility(View.VISIBLE);
            pbtxti.setVisibility(View.VISIBLE);
            displayquestions(curr);
            if(btnnext.getText().equals("Finish"))
            {
                btnnext.setText("Next");
            }
            if(curr==0)
            {
                btnprev.setEnabled(false);
            }
        }
    }

    public void getselectedoption(int index)
    {
        if(rg.getCheckedRadioButtonId()>0) {
            qlist.get(index).setUseranswer(rg.getCheckedRadioButtonId());
        }
        else
        {
            qlist.get(index).setUseranswer(0);
        }
    }

    @Override
    public void setIImage(Bitmap bitmap)
    {
     pb.setVisibility(View.INVISIBLE);
        pbtxti.setVisibility(View.INVISIBLE);
     iv.setVisibility(View.VISIBLE);
     iv.setImageBitmap(bitmap);
     }
}
