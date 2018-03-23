package com.june.testrx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {
    public String tag="aaa";
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // map map()操作符就是用于变换Observable对象的，map操作符返回一个Observable  就可以把东西一直map下去
        Observable.just("Hello, world!")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "-ssssss";
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(tag, "mapmapmap" + s);
            }
        });
        tv = (TextView) findViewById(R.id.tv);
        ///////////////////////////////////////////Scheduler
        Observable.just("hehe").subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                        tv.setText(s);
            }
        });
        Log.e(tag, "q111111111111111111111");
        ////////////////////////////////////////   Observable.from  接收数组  会把每一个元素发给观察者
       final  ArrayList<String> arr = new ArrayList<String>();
        arr.add("aa");
        arr.add("bb");
        arr.add("cc");
        arr.add("dd");
        arr.add("ee");
        Observable.from(arr).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(tag, "hehe" + s);
            }
        });
    //////////////////////////////flatMap接收


        Observable.just("hehe").flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                Log.e(tag, "flatmapppp====" + s);
                return Observable.from(arr);
            }
        }).filter(new Func1<String, Boolean>() {//过滤 返回真就是不过滤
            @Override
            public Boolean call(String s) {
                if(!"cc".equals(s)){
                    return true;
                }
                return false;
            }
        }).take(5).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(tag, "重flatmap====" + s);
            }
        });
        /////////////////////////////////////

        ////////////////////////////////////////
//        Observable<String> myObservable = Observable.create(
//                new Observable.OnSubscribe<String>() {
//                    @Override
//                    public void call(Subscriber<? super String> sub) {
//                            Log.e(tag,"asdfsadfa");
//                        sub.onNext("Hello, world!=========================================");
//                        sub.onCompleted();
//                    }
//                }
//        );
//        Subscriber<String> mySubscriber = new Subscriber<String>() {
//            @Override
//            public void onNext(String s) {
//                Log.e(tag,"zainextlimian"+s);
//            }
//
//            @Override
//            public void onCompleted() { }
//
//            @Override
//            public void onError(Throwable e) { }
//        };
//        myObservable.subscribe(mySubscriber);
//        OkHttpClient oc=new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://api.laifudao.com/open/tupian.json")
//                .build();
//        oc.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    //Log.e("aaaaa",response.body().string());
//                    String s=response.body().string();
//                    tv.setText(s);
//                   // JSONArray jsonArray = new JSONArray(s);
        ///////////////////////////////////////(2)
//                    Observable<String> myObservable = Observable.just(s);
//                    myObservable.subscribe(onNextAction);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
        ////////////////////////////////////(2)
//    Action1<String> onNextAction = new Action1<String>() {
//        @Override
//        public void call(String s) {
//            Log.e(tag,"zainextlimian"+s);
//           // tv.setText(s);
//        }
//    };
    }



}
