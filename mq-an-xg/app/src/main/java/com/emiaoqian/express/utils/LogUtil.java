package com.emiaoqian.express.utils;

import android.util.Log;

/**
 * Created by xiong on 2017/8/17.
 */

public class LogUtil {

        private static final String TAG = "LogUtil";

        //表示当时是否是debug模式(开发调试模式)，在项目开发完毕之后需要将这个变量置为false
        private static boolean isDebug = true;

        /**
         * 打印d级别的log
         * @param tag
         * @param msg
         */
        public static void d(String tag, String msg){
            if(isDebug){
                Log.d(tag, msg);
            }
        }

        public static void d(String msg){
            if(isDebug){
                Log.d(TAG, msg);
            }
        }

        public static void e(String tag, String msg){
            if(isDebug){
                Log.e(tag, msg);
            }
        }

        public static void e(String msg){
            if(isDebug){
                Log.e(TAG, msg);
            }
        }
    }

