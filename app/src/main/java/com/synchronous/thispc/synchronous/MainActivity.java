package com.synchronous.thispc.synchronous;

import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.NoSuchPropertyException;
import android.view.InflateException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btn;
    public static EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button1);
        edt = (EditText) findViewById(R.id.editText1);
        final ThreadSynchornous th = new ThreadSynchornous();
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try
                    {
                        Thread t1 = new Thread(){
                            @Override
                            public void run(){
                                th.printTable(5);
                            }
                        };
                        Thread t2 = new Thread(){
                            @Override
                            public void run() {
                                th.printTable(10);
                            }
                        };
                        t1.start();
                        t2.start();
                    }
                    catch (InflateException e)
                    {
                        Log.w("Error","Error. . ."+ e);
                    }
                }

        });
    }
    public static class ThreadSynchornous{
       synchronized void printTable(int n){
           for (int i = 0; i <= 5; i++)
           {
               edt.setText(n*i + "\n");
               try {
                   Thread.sleep(1);
               }
               catch (InterruptedException e)
               {
                   e.printStackTrace();
                   //edt.setText("Error . . . "+ ex.toString());
               }
           }
       }
    }
}
