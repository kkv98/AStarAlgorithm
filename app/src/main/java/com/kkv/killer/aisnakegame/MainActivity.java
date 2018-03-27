package com.kkv.killer.aisnakegame;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
   TableLayout tl;
   TableRow tr;
   TextView tv;
   int i,blocklist[][],bc;
   AStar aStar;
   Button sel,re;
   int s[],e[];
   static int rouc,rou[][];
   int blockflag;
   static MainActivity jjj;
   static TableLayout temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tl=findViewById(R.id.tl1);
        temp=findViewById(R.id.tl1);
        sel=findViewById(R.id.sel);
        re=findViewById(R.id.re);
        rou=new int[100][2];
        s=new int[2];
        //e=new int[2];
        i=0;
        blockflag=0;
        aStar=new AStar();
        rouc=0;
        bc=0;
        jjj=MainActivity.this;
        blocklist=new int[100][2];
        for(int to=0;to<=1;to++)
            Toast.makeText(this, "Select Your Blocked Block Then Press Start", Toast.LENGTH_SHORT).show();
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=0;
                blockflag=0;
                clear(rou,rouc);
                clear(blocklist,bc);
                blocklist=new int[100][2];
                rou=new int[100][2];
                bc=0;
                rouc=0;
            }
        });
        sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int to=0;to<=1;to++)
                    Toast.makeText(getApplicationContext(), "Select Your Start", Toast.LENGTH_SHORT).show();
                blockflag=1;
            }
        });
        tr = (TableRow) tl.getChildAt(0);
        for (int i = 0; i < tl.getChildCount(); i++)
        {
            tr = (TableRow) tl.getChildAt(i);
            for (int j = 0; j < tr.getChildCount(); j++)
            {
                tv = (TextView) tr.getChildAt(j);
                registerForContextMenu(tv);
                tv.setId(j);
                tv.setOnClickListener(onClick(tv,i,j));
            }
        }
    }

    View.OnClickListener onClick(final TextView tc,final int row,final int col)  {
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(blockflag!=0) {
                    if (i == 0) {
                        for(int to=0;to<=1;to++)
                            Toast.makeText(getApplicationContext(), "Select Your Destination", Toast.LENGTH_SHORT).show();
                        tc.setText("*");
                        s[0] = row;
                        s[1] = col;
                        ++i;
                    }
                    if (i == 1) {
                        try {
                            tc.setText("*");
                            aStar.test(10, 10, s[0], s[1], row, col, blocklist);
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    blocklist[bc][0]=row;
                    blocklist[bc][1]=col;
                    ++bc;
                    tc.setBackgroundColor(Color.BLACK);

                }
            }
        };
        return onClickListener;
    }
    public static void highlight(int i,int j){
        TableRow trs;
        trs=(TableRow) temp.getChildAt(i);
        TextView ttt= (TextView) trs.getChildAt(j);
        ttt.setBackgroundColor(Color.CYAN);
    }
public void clear(int cl[][],int len){
        for(int to=0;to<=len;to++){
            tr=(TableRow) temp.getChildAt(cl[to][0]);
            TextView ttt= (TextView) tr.getChildAt(cl[to][1]);
            ttt.setBackgroundColor(Color.WHITE);
            ttt.setText("");
    }
}
}