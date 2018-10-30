package com.example.a503_14.a1017thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btn1, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textView);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*
                new Thread() {
                    public void run() {
                        int i = 0;
                        while (i < 10) {
                            try {
                                //1초동안 스레드 일시정지
                                Thread.sleep(1000);

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("예외 발생:", e.getMessage());
                            }
                            //Logcat에서 실행하는지 확인하기 위한 코드
                            //UI 갱신을 하지 않는 코드이므로 작업을 수행합니다.
                            Log.e("value:", i + "");
                            i = i + 1;
                            //화면: 결과는 바로 10출력
                            //UI 갱신을 하는 코드이므로 모아서 한꺼번에 처리합니다.
                            textView.setText(String.format("value:%d", i));
                        }
                    }
                }.start();

                new Thread() {
                    public void run() {
                        int i = 0;
                        while (i < 10) {
                            try {
                                //1초동안 스레드 일시정지
                                Thread.sleep(1000);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Log.e("value:", i + "");
                            i = i + 1;
                            Log.e("태그", "안녕하세요");
                        }
                    }
                }.start();
                */


                class T implements Runnable{
                    String name;
                    int i;
                    public T(String name){
                        this.name=name;
                    }
                    public void run(){
                        for(int j=0; j<5; j++){
                            try{
                                //synchronized 괄호 안의 영역은 중간에 쉬는시간이 발생하더라도 무조건 한번에 수행합니다.
                                //다른 스레드의 접근을 막음
                                //동기화 시키지 않으면 출력값이 차례대로 되지 않고 바뀌는 경우가 생김(한 스레드가 번호를 4로 증가하고 출력할려고 하는데 다른 스레드가 방금 증가한 4에 하나를 더해서 먼저 5를 출력하는 경우)
                                //사용자가 의도한대로 차례대로 출력할려면 출력이 끝날 때 까지 add() 함수로 아무도 못들어오게 막아야 겠죠. 아add() 함수에 synchronized 적용
                                synchronized (this) {
                                    Log.e(name + "변경하기 전", i + "");
                                    i = i + 1;
                                    Thread.sleep(1000);
                                    Log.e(name + "변경한 후", i + "");
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
                T obj =new T("상호배제");
                Thread th1=new Thread(obj);
                th1.start();
                Thread th2=new Thread(obj);
                th2.start();


                btn2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        class T extends Thread{
                            public void run(){
                                int i=0;
                                while(i<10){
                                    try {
                                        Thread.sleep(1000);
                                        textView.setText(i+"");
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                    i=i+1;
                                }
                            }
                        }
                        new T().start();
                    }
                });

            }

        });

    }
}
