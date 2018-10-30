package com.example.a503_14.a1017thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HandlerActivity extends AppCompatActivity {
    TextView textView;
    //화면을 주기적으로 갱신하기 위해 생성
    Handler handler = new Handler(){
        int i = 0;
        public void handleMessage(Message message){
            textView.setText(i + "");
            i = i + 1;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        textView=(TextView)findViewById(R.id.textView);
        new Thread(){
            public void run() {
                for (int i = 0; i < 10; i++){
                    try {
                        Thread.sleep(1000);
                        textView.setText(i + "");
                        handler.sendEmptyMessage(0);
                    } catch (Exception e) { }
                }
            }
        }.start();


    }
}
