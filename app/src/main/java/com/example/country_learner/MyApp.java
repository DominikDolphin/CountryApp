package com.example.country_learner;

import android.app.Application;

import java.util.concurrent.ExecutorService;

public class MyApp extends Application {
    NetworkingManager networkingManager = new NetworkingManager();
    DatabaseManager databaseManager = new DatabaseManager();
}
