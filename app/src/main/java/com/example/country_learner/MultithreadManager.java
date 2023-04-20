package com.example.country_learner;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadManager {
    static ExecutorService executorService = Executors.newFixedThreadPool(4);
    static Handler handler = new Handler(Looper.getMainLooper());
}
