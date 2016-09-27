package com.example.ludde.timerproject;

import android.app.Activity;
import android.content.Context;

/**
 * Created by ludde on 9/27/2016.
 */

public class ChessTimer {
    private boolean mRunning = false;
    private Thread mThread;

    private boolean mIsPlayerOne = true;
    private long mPlayerOneTime = 0;
    private long mPlayerTwoTime = 0;
    private long mLastTime = 0;
    private ChessTimerListener mListener;
    private Activity mContext;

    public ChessTimer(ChessTimerListener listener, Activity context) {
        this.mListener = listener;
        this.mContext = context;

    }

    public void switchPlayer() {
        this.mIsPlayerOne = !this.mIsPlayerOne;
    }

    public boolean isPlayerOne() {
        return this.mIsPlayerOne;
    }

    public long getmPlayerTwoTime() {
        return this.mPlayerTwoTime;
    }


    public long getmPlayerOneTime() {
        return this.mPlayerOneTime;
    }


    public void start() {
        this.mRunning = true;
        this.mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mLastTime = System.currentTimeMillis();
                while(mRunning) {
                    if(mIsPlayerOne) {
                        mPlayerOneTime += (System.currentTimeMillis() - mLastTime);
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListener.onPlayerOneTimeUpdate(mPlayerOneTime);
                            }
                        });
                    }else {
                        mPlayerTwoTime += (System.currentTimeMillis() - mLastTime);
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mListener.onPlayerTwoTimeUpdate(mPlayerTwoTime);
                            }
                        });
                    }
                    mLastTime = System.currentTimeMillis();

                    try {
                        Thread.currentThread().sleep(10);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.mThread.start();
    }


    public void stop() {
        this.mRunning = false;
    }

    public interface ChessTimerListener {
        void onPlayerOneTimeUpdate(long time);
        void onPlayerTwoTimeUpdate(long time);
    }

}
