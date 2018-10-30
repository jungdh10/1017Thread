package com.example.a503_14.a1017thread;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Handler;
import android.os.Message;

public class HandlerExample extends AppCompatActivity {
    Button download;
    //진행율을 표시하기 위한 대화상자
    ProgressDialog progressDialog;
    //값을 나타낼 변수
    int value;

    final Handler handler=new Handler(){
        public void handleMessage(Message message){
            value=value+1;
            try{
                Thread.sleep(500);
                if(value<100){
                    progressDialog.setProgress(value);
                    handler.sendEmptyMessage(0);
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(HandlerExample.this, "다운로드 완료", Toast.LENGTH_LONG).show();
                }
            }catch(Exception e){}
        }
    };

    /*
    public void download(){
        try{
            Thread.sleep(5000);
            Toast.makeText(HandlerExample.this, "다운로드 완료", Toast.LENGTH_LONG).show();
        }catch(Exception e){}
    }
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_example);

        download=(Button)findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //대화상자를 띄우면 다운로드 확인을 클릭했을 때 대화상자가 안사라짐(이렇게 만들면 엑스), 다운로드 할 때 대화상자를 닫으려면 Handler 사용
                new AlertDialog.Builder(HandlerExample.this)
                        .setTitle("다운로드")
                        .setMessage("다운로드 하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        value=0;
                        progressDialog =new ProgressDialog(HandlerExample.this);
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setTitle("다운로드");
                        progressDialog.setMessage("기다리세요......");
                        //Back 버튼을 눌렀을 때 대화상자를 닫을 수 없게 설정
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        handler.sendEmptyMessage(0);
                    }
                }).setNegativeButton("아니오", null).show();
            }
        });

    }
}
