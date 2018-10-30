package com.example.a503_14.a1017thread;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HandlerUse extends AppCompatActivity {
    Button btn;

    //핸들러 생성
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            String str=(String)msg.obj;
            Toast.makeText(HandlerUse.this, str, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_use);

        //스레드가 한번만 실행되지 않고 여러번 실행되게(버튼을 누를 때마다) onCreate에 스레드 생성
        class ThreadEx extends Thread{
            String url;
            public ThreadEx(String url) {
                this.url = url;
            }
            public void run(){
                try{
                    //핸들러에게 데이터를 전달하면서 호출
                    Message message=new Message();
                    message.obj=url;
                    handler.sendMessageDelayed(message, 1000);
                    //handler.sendEmptyMessageAtTime(1,3000);
                }catch(Exception e){}
            }
        }

        //버튼을 눌렀을 때 스레드 시작
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ThreadEx th1=new ThreadEx("영화정보");
                ThreadEx th2=new ThreadEx("극장정보");
                th1.start();
                th2.start();
            }
        });
    }
}
