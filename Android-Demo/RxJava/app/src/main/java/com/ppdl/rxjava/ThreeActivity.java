package com.ppdl.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ppdl.rxjava.RxBus.OneEvent;
import com.ppdl.rxjava.RxBus.RxBus;
import com.ppdl.rxjava.RxBus.TestAsynRequest;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ThreeActivity extends BaseActivty implements View.OnClickListener{


    private TextView tvReceiver;
    private TestAsynRequest asynRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
    }

    @Override
    public void InitView() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        tvReceiver = (TextView) findViewById(R.id.tv_receiver);
    }

    @Override
    public void InitData() {
        asynRequest = new TestAsynRequest();

        onSubscribeString();
        onSubscribeInt();
        onSubscribeOneEvent();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                asynRequest.requset("NBA");
                break;
            case R.id.btn2:
                asynRequest.requset(110);
                break;
            case R.id.btn3:
                asynRequest.requset(new OneEvent("OneEvent"));
                break;
            case R.id.btn4:
                asynRequest.requset(tvReceiver,"Sure");
                break;
            case R.id.btn5:
                asynRequest.requset(tvReceiver,"Error");
                break;
            case R.id.btn6:
                break;
        }
    }

    public void setTvReceiver(String data,int num){
        tvReceiver.setText("接收到按钮"+num+":"+data);
    }

    public void setTvReceivers(String data){
        if(tvReceiver.getText().toString().isEmpty()){
            tvReceiver.setText(data);
        }else{
            tvReceiver.setText(tvReceiver.getText().toString()+" : "+data);
        }

    }

    public void onSubscribeString(){
        Subscription subscription=RxBus.getInstance()
                .toObservable(String.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        setTvReceivers("订阅String:"+s);
                    }
                });
        RxBus.getInstance().addSubscription(this,subscription);
    }

    public void onSubscribeInt(){
        Subscription subscription=RxBus.getInstance()
                .toObservable(Integer.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        setTvReceivers("订阅Integer:"+integer);
                    }
                });
        RxBus.getInstance().addSubscription(this,subscription);
    }

    public void onSubscribeOneEvent(){
        Subscription subscription=RxBus.getInstance()
                .toObservable(OneEvent.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<OneEvent>() {
                    @Override
                    public void call(OneEvent oneEvent) {
                        setTvReceivers("订阅OneEvent:"+oneEvent.getData());
                    }
                });
        RxBus.getInstance().addSubscription(this,subscription);
    }


    @Override
    protected void onDestroy() {
        RxBus.getInstance().unSubscribe(this);                          //取消订阅
        super.onDestroy();
    }
}