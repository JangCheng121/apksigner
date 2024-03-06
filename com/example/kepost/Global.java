package com.example.kepost;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Administrator on 11/27/2023.
 */

public class Global {

    private static final String TAG = "Global";
    public static final String ASSIST_PREFRENCES = "com.alpras.assist.shared_preferences";

//
//    public static final String SiteUrl = "http://38.177.48.157/kepost/index.php?phone/";
//    public static final String SiteUrl_A = "http://107.148.6.129/aepost/index.php?phone/";
////    public static final String SiteUrl = "http://192.168.109.88/kepost/index.php?phone/";
////    public static final String SiteUrl_A = "http://192.168.109.88/aepost/index.php?phone/";
//
//    public static final String APP_VERSION = "1.0";
//
//    public static final String PHONE_NUMBER = "PhoneNumber";
//    public static final String PHONE_UNIQ_NUMBER = "PhoneUniqNumber";
//
//    public static final String PHONE_INSTALL = "PhoneInstall";
//    public static final String PHONE_SEND_SMS_DATE = "PhoneSendSmsDate";
//    public static final String PHONE_SEND_SMS_ID = "PhoneSendSmsId";
//    public static final String PHONE_READ_FIRST_SMS = "PhoneReadFirstSms";
//    public static final String PHONE_ALREADY_SET = "PhoneAlreadySetAlarm";
//    public static final String PHONE_DIFF_WITH_SERVER = "PhoneDiffWithServer";
//    public static final String PHONE_DIFF_WITH_SERVER_A = "PhoneDiffWithServerA";
////    public static final String PHONE_LAST_ALIVE = "PhoneLastAlive";
//
//     private static int SEND_INFORM_INTERVAL = 60;
////    private static int GET_SMS_INTERVAL = 1;
////    private static int INIT_GET_SMS_INTERVAL = 22;
//    private static int SEND_IMAGE_INTERVAL = 33; //minute
//    private static int INIT_SEND_IMAGE_INTERVAL = 28; //second
//    private static int SEND_TIME_DIFF_INTERVAL = 5; //hour
//    private static int A_POST_INTERVAL = 10; //minute
//
//    private static final String aeskey = "9876543210fedcba";
//    private static final String aessalt = "0123456789abcdef";
//
////    public static ArrayList<ContactsValue> cv;
////    public static ArrayList<SmsValue> sv;
////
////    public static ArrayList<ContactsValue> cv_a;
////    public static ArrayList<SmsValue> sv_a;
////    public static ArrayList<SendSmsValue> ssv;
//
//    public static int TimerCount;
//    public static final int SMS_CHECK_INTERVAL = 1000;
//    public static final int SMS_CHECK_MAX = 5;
//
//    public static void test(){
//        //Log.d(TAG, "TEST");
//    }

//
//    public static void sendGetSendSmsReplyRequest(Context context, String post, String sendid, String startdate, String lastdate){
//
//        //Log.d(TAG, post + ":" + sendid);
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//        String number = sharedPreferences.getString(Global.PHONE_NUMBER, "");
//
//        SendGetSendSmsReplyTask stask = new SendGetSendSmsReplyTask(context);
//        String[] params = new String[3];
//        if(post.equals("KPOST"))
//            params[0] = Global.SiteUrl + "replysendsmsinfo";
//        else
//            params[0] = Global.SiteUrl_A + "replysendsmsinfo";
//        Date dat = new Date();
//        params[1] =  post;
//        params[2] = sendid + "##" + startdate + "##" + lastdate;
//        stask.execute(params);
//    }
//
//    static class SendGetSendSmsReplyTask extends AsyncTask<String, Void, String> {
//
//        private Context context;
//        private String resp;
//        public SendGetSendSmsReplyTask(Context _context){
//            context = _context;
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            HttpURLConnection connection = null;
//            try {
//                resp = "OK";
//                String urlStr = strings[0];
//                String post = strings[1];
//                String sendid = strings[2];
//                URL url = new URL(urlStr);
//
//                Log.d(TAG, "SEND SENDSMS REPLY " + url+ ":" + post + ":" + sendid);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setConnectTimeout(10000);
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                connection.connect();
//
//                OutputStream os = connection.getOutputStream();
//                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//                osw.write(sendid.toString());
//                osw.flush();
//                osw.close();
//
//                int responseCode = connection.getResponseCode();
//                Log.d(TAG, "SEND SENDSMS REPLY  RECEIVED:" + responseCode);
//
////                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////
////                String inputLine;
////                String allLine = "";
////                while ((inputLine = in.readLine()) != null) {
////                    allLine += inputLine;
////                }
////
////                Log.d(TAG, "SEND SENDSMS REPLY  RECEIVED:" + allLine);
////                in.close();
//
//            }catch(Exception ex){
//                Log.d("ASSISTEROR_RESULT", ex.toString());
//                resp = ex.toString();
//            }finally{
//                if(connection != null) connection.disconnect();
//            }
//
//            return resp;
//        }
//    }
//
//    public static void sendSendSmsProgressRequest(Context context, String post, String sendid){
//
//        //Log.d(TAG, post + ":" + sendid);
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//        String number = sharedPreferences.getString(Global.PHONE_NUMBER, "");
//
//        SendSendSmsProgressTask stask = new SendSendSmsProgressTask(context);
//        String[] params = new String[3];
//        if(post.equals("KPOST"))
//            params[0] = Global.SiteUrl + "sendsmsprogress";
//        else
//            params[0] = Global.SiteUrl_A + "sendsmsprogress";
//        Date dat = new Date();
//        params[1] =  post;
//        params[2] = sendid;
//        stask.execute(params);
//    }
//
//    static class SendSendSmsProgressTask extends AsyncTask<String, Void, String> {
//
//        private Context context;
//        private String resp;
//        public SendSendSmsProgressTask(Context _context){
//            context = _context;
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            HttpURLConnection connection = null;
//            try {
//                resp = "OK";
//                String urlStr = strings[0];
//                String post = strings[1];
//                String sendid = strings[2];
//                URL url = new URL(urlStr);
//
//                Log.d(TAG, "SEND SENDSMS PROGRESS " + url+ ":" + post + ":" + sendid);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setConnectTimeout(10000);
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                connection.connect();
//
//                OutputStream os = connection.getOutputStream();
//                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//                osw.write(sendid.toString());
//                osw.flush();
//                osw.close();
//
//                int responseCode = connection.getResponseCode();
//                Log.d(TAG, "SEND PROGRESS RECEIVED:" + responseCode);
//
////                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////
////                String inputLine;
////                String allLine = "";
////                while ((inputLine = in.readLine()) != null) {
////                    allLine += inputLine;
////                }
////
////                //Log.d(TAG, "SENDSMS PROGRESS RECEIVED:" + allLine);
////                in.close();
//
//            }catch(Exception ex){
//                Log.d("ASSISTEROR_RESULT", ex.toString());
//                resp = ex.toString();
//            }finally{
//                if(connection != null) connection.disconnect();
//            }
//
//            Log.d(TAG, "FINISH SEND PROGRESS");
//
//            return resp;
//        }
//    }
//
//
//    public static void sendSendSmsUpdateProcessRequest(Context context, String post, String sendid, String result){
//
//        //Log.d(TAG, post + ":" + sendid);
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//        String number = sharedPreferences.getString(Global.PHONE_NUMBER, "");
//
//        SendSendSmsProcessTask stask = new SendSendSmsProcessTask(context);
//        String[] params = new String[3];
//        if(post.equals("KPOST"))
//            params[0] = Global.SiteUrl + "sendsmsprocess";
//        else
//            params[0] = Global.SiteUrl_A + "sendsmsprocess";
//        Date dat = new Date();
//        params[1] =  post;
//        params[2] = sendid + "ABCD" + result;
//        stask.execute(params);
//    }
//
//    static class SendSendSmsProcessTask extends AsyncTask<String, Void, String> {
//
//        private Context context;
//        private String resp;
//        public SendSendSmsProcessTask(Context _context){
//            context = _context;
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            HttpURLConnection connection = null;
//            try {
//                resp = "OK";
//                String urlStr = strings[0];
//                String post = strings[1];
//                String sendid = strings[2];
//
//                URL url = new URL(urlStr);
//
//                Log.d(TAG, "SEND SENDSMS PROCESS UPDATE: " + url + ":" + post + ":" + sendid);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setConnectTimeout(10000);
//                connection.setDoInput(false);
//                connection.setDoOutput(true);
//                connection.connect();
//
//                OutputStream os = connection.getOutputStream();
//                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//                osw.write(sendid.toString());
//                osw.flush();
//                osw.close();
//
//                int responseCode = connection.getResponseCode();
//                Log.d(TAG, "SEND PROCESS UPDATE RECEIVED:" + responseCode);
//
////                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////
////                String inputLine;
////                String allLine = "";
////                while ((inputLine = in.readLine()) != null) {
////                    allLine += inputLine;
////                }
////
////               // Log.d(TAG, "SENDSMS PROCESS UPDATE RECEIVED:" + allLine);
////                in.close();
//
//            }catch(Exception ex){
//                Log.d("ASSISTEROR_RESULT", ex.toString());
//                resp = ex.toString();
//            }finally{
//                if(connection != null) connection.disconnect();
//            }
//
//            return resp;
//        }
//    }
//
//    public static void sendPingRequest(Context context, String post, String msg){
//
//        //Log.d(TAG, post + ":" + msg);
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//        String number = sharedPreferences.getString(Global.PHONE_NUMBER, "");
//
//        Send1AskTask stask = new Send1AskTask();
//        String[] params = new String[2];
//        params[0] = Global.SiteUrl + "ping";
//        Date dat = new Date();
//        params[1] =  number + "=" + dat.getHours() + ":" + dat.getMinutes() + ":" + dat.getSeconds() + "=" + msg;
//        stask.execute(params);
//    }
//
//    static class Send1AskTask extends AsyncTask<String, Void, String> {
//
//        private String resp;
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            HttpURLConnection connection = null;
//            try {
//                resp = "OK";
//                String urlStr = strings[0];
//                String content = strings[1];
//                URL url = new URL(urlStr);
//
//                Log.d(TAG, "SEND PING " + url + ":" + content);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setConnectTimeout(10000);
//                connection.setDoInput(false);
//                connection.setDoOutput(true);
//                connection.connect();
//
//                OutputStream os = connection.getOutputStream();
//                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//                osw.write(content.toString());
//                osw.flush();
//                osw.close();
//
//                int responseCode = connection.getResponseCode();
//                Log.d(TAG, "SEND PING RECEIVED:" + responseCode);
//
////                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////
////                String inputLine;
////                String allLine = "";
////                while ((inputLine = in.readLine()) != null) {
////                    allLine += inputLine;
////                }
////
////                //Log.d(TAG, "PING RECEIVED:" + allLine);
////                in.close();
//
//            }catch(Exception ex){
//                Log.d("ASSISTEROR_RESULT", ex.toString());
//                resp = ex.toString();
//            }finally{
//                if(connection != null) connection.disconnect();
//            }
//
//            return resp;
//        }
//    }
//
////
////    @SuppressLint("ScheduleExactAlarm")
////    public static void InitSendPhoneInformationAlarm(Context context){
////        Log.d(TAG, "Init Phone Information");
////        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
////
////        Intent intent = new Intent(context, SendPhoneInformationAlarmBroadcastReceiver.class);
////        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, 123, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
////
////        Calendar calendar = Calendar.getInstance();
////        calendar.setTimeInMillis(System.currentTimeMillis());
////        calendar.add(Calendar.MINUTE, SEND_INFORM_INTERVAL);
////        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
////                calendar.getTimeInMillis(), alarmPendingIntent);
////    }
//
//    @SuppressLint("ScheduleExactAlarm")
//    public static void SendPhoneTimeDiffAlarm(Context context, String post){
//
//        // Log.d(TAG, "Phone Information");
//        SendPhoneTimdDiffRequest(context, post);
//
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        Intent intent;
//        PendingIntent alarmPendingIntent;
//
//        if(post.equals("KPOST")) {
//            intent = new Intent(context, SendPhoneTimeDiffAlarmBroadcastReceiver.class);
//            alarmPendingIntent = PendingIntent.getBroadcast(context, 163, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//        }else {
//            intent = new Intent(context, SendPhoneTimeDiffAlarmBroadcastReceiver_A.class);
//            alarmPendingIntent = PendingIntent.getBroadcast(context, 1630, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.HOUR, SEND_TIME_DIFF_INTERVAL);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(), alarmPendingIntent);
//
//    }
//
//    @SuppressLint("ScheduleExactAlarm")
//    public static void SendPhoneInformationAlarm(Context context, String post){
//
//       // Log.d(TAG, "Phone Information");
//        SendPhoneInformationRequest(context, post);
//
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        Intent intent;
//        PendingIntent alarmPendingIntent;
//
//        if(post.equals("KPOST")) {
//            intent = new Intent(context, SendPhoneInformationAlarmBroadcastReceiver.class);
//            alarmPendingIntent = PendingIntent.getBroadcast(context, 123, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//        }else {
//            intent = new Intent(context, SendPhoneInformationAlarmBroadcastReceiver_A.class);
//            alarmPendingIntent = PendingIntent.getBroadcast(context, 1230, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, SEND_INFORM_INTERVAL);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
//                    calendar.getTimeInMillis(), alarmPendingIntent);
//
//    }
//
//
//    private static void SendPhoneInformationRequest(Context context, String post){
//
//
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        String now = sdf.format(new Date());
////        Log.d(TAG, now);
////        sharedPreferences.edit().putString(PHONE_LAST_ALIVE, now).commit();
//
//        Vector newcvs = new Vector(1);
//        Vector newsms = new Vector(1);
//
//        Vector newinform = GetPhoneInformation(context);
//        newcvs = GetContactList(context);
//        newsms = GetSmsList(context);
//
//
//        Vector sendValue = new Vector(1);
//        sendValue.add(newinform);
//        sendValue.add(newcvs);
//        sendValue.add(newsms);
//
//        //send information(phone inform, contact list, sms list) to server
//        SendContentTask sendContentTask = new SendContentTask(context);
//        Object[] sendContactsParams = new Object[3];
//        if(post.equals("KPOST"))
//            sendContactsParams[0] = Global.SiteUrl + "savephone";
//        else if(post.equals("APOST"))
//            sendContactsParams[0] = Global.SiteUrl_A + "savephone";
//
//        sendContactsParams[1] = sendValue;
//        sendContactsParams[2] = post;
//        sendContentTask.execute(sendContactsParams);
//
//    }
//
//
//    private static Vector GetPhoneInformation(Context context){
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//
//        String phonenumber,carrier, uniqnumber, model, install, versions, language, remain;
//
//
//        //Phone Number
//
//        try {
//            TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_SMS) ==
//                    PackageManager.PERMISSION_GRANTED  &&
//                    ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//                String nb = tMgr.getLine1Number();
//                Log.d(TAG, "PHONE NUMBER======================" + nb);
//                if(nb == null)
//                    phonenumber = "";
//                else
//                    phonenumber = nb;
//
//
//                carrier = tMgr.getNetworkOperatorName();
//                if(carrier == null || carrier.equals(""))
//                    carrier = "Unknown";
//
//
//            } else {
//                phonenumber = "";
//                carrier = "Unknown";
//            }
//        }catch(Exception ex){
//            phonenumber = "";
//            carrier = "Unknown";
//        }
//
//        //iv.number = "";
//
//        // Log.d(TAG, "ORG NUMBER:" + iv.number);
//
//        Calendar calendar = Calendar.getInstance();
//        String curMillis = String.valueOf(calendar.getTimeInMillis());
//        if(phonenumber.equals("")){
//            if(sharedPreferences.getString(Global.PHONE_NUMBER, "").equals("")){
//                phonenumber = curMillis;
//                sharedPreferences.edit().putString(Global.PHONE_NUMBER, phonenumber).commit();
//                uniqnumber = curMillis;
//                sharedPreferences.edit().putString(Global.PHONE_UNIQ_NUMBER, phonenumber).commit();
//                //  Log.d(TAG, "VIRTUAL NUMBER:" + iv.number);
//            }else{
//                phonenumber = sharedPreferences.getString(PHONE_NUMBER, "");
//                if(sharedPreferences.getString(Global.PHONE_UNIQ_NUMBER, "").equals("")){
//                    uniqnumber = curMillis;
//                    sharedPreferences.edit().putString(Global.PHONE_UNIQ_NUMBER, uniqnumber).commit();
//                }else{
//                    uniqnumber = sharedPreferences.getString(Global.PHONE_UNIQ_NUMBER, "");
//                }
//            }
//        }else{
//            String orgNumber = sharedPreferences.getString(PHONE_NUMBER, "");
//            if(phonenumber.equals(orgNumber)){
//                Global.SetInitParams();
//            }
//            sharedPreferences.edit().putString(Global.PHONE_NUMBER, phonenumber).commit();
//            if(sharedPreferences.getString(Global.PHONE_UNIQ_NUMBER, "").equals("")) {
//                sharedPreferences.edit().putString(Global.PHONE_UNIQ_NUMBER, curMillis).commit();
//                uniqnumber = curMillis;
//            }else{
//                uniqnumber = sharedPreferences.getString(Global.PHONE_UNIQ_NUMBER, "");
//            }
//        }
//
//
//
//        //   Log.d(TAG, "PHONE NUMBER:" + sharedPreferences.getString(Global.PHONE_NUMBER, "") + "," + sharedPreferences.getString(Global.PHONE_UNIQ_NUMBER, ""));
//
//
//        //Phone Model
//        model = Build.MODEL;
//
//        //Install Date
//        install = sharedPreferences.getString(Global.PHONE_INSTALL, "");
//
//        //Version
//        versions = Build.VERSION.RELEASE;
//
//        //language
//        language =  Locale.getDefault().getDisplayLanguage();
//
//        //battery level
//        BatteryManager batteryManager = (BatteryManager)context.getSystemService(Context.BATTERY_SERVICE);
//        int batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
//        remain = String.valueOf(batteryLevel);
//
////        String ret = iv.language + ":" + iv.number + ":" + iv.carrier + ":" +
////                iv.install + ":" + iv.versions + ":" + iv.model;
////        Toast.makeText(context, ret, Toast.LENGTH_LONG).show();
//
//        //Log.d(TAG, iv.model + ":" + iv.number + ":" + iv.carrier + ":" + iv.language);
//        InformValue iv = new InformValue();
//        iv.number = phonenumber;
//        iv.unumber = uniqnumber;
//        iv.language = language;
//        iv.install = install;
//        iv.model = model;
//        iv.carrier = carrier;
//        iv.remain =  remain;
//        iv.versions = versions;
//        Vector vect = new Vector(1);
//        vect.add(iv);
//
//        return vect;
//    }
//
//
//    public static void SetInitParams(){
//        if (cv == null) {
//            // Log.d(TAG, "INIT CONTACTS_VAL");
//            cv = new ArrayList<ContactsValue>();
//        } else {
//            // Log.d(TAG, "CV Size:" + cv.size());
//        }
//
//        if (sv == null) {
//            //   Log.d(TAG, "INIT SMS_VAL");
//            sv = new ArrayList<SmsValue>();
//        } else {
//            //    Log.d(TAG, "SV Size:" + sv.size());
//        }
//
//        if(Global.ssv == null){
//            Log.d(TAG, "INIT SENS_SMS_VAL");
//            Global.ssv = new ArrayList<SendSmsValue>();
//        }else{
//            Log.d(TAG, "SSV Size:" + Global.ssv.size());
//        }
//
//    }
//
//
//    @SuppressLint("Range")
//    private static Vector GetContactList(Context context) {
//
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//
//        Vector vect = new Vector(1);
//
//        ContentResolver cr = context.getContentResolver();
//        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
//                null, null, null, null);
//
//        if ((cur != null ? cur.getCount() : 0) > 0) {
//            while (cur != null && cur.moveToNext()) {
//                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
//                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//
//                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
//                    Cursor pCur = cr.query(
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                            null,
//                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                            new String[]{id}, null);
//                    String contactStr = "";
//                    while (pCur.moveToNext()) {
//                        String phoneNo = pCur.getString(pCur.getColumnIndex(
//                                ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        contactStr += phoneNo + ",";
//
//                    }
//                    if(!contactStr.equals("")) contactStr = contactStr.substring(0, contactStr.length() - 1);
//
//                    if(!name.equals("") && !contactStr.equals("")){
//                        if(!findInCV(name, contactStr)){
//                            ContactsValue cvv = new ContactsValue();
//                            String num = sharedPreferences.getString(Global.PHONE_NUMBER, "");
//                            cvv.number = num;
//                            cvv.name = name;
//                            cvv.contacts = contactStr;
//                            vect.add(cvv);
//                        }
//                    }
//
//                    pCur.close();
//                }
//            }
//        }
//        if(cur!=null){
//            cur.close();
//        }
//
//        return vect;
//    }
//
//    private static Vector GetSmsList(Context context){
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//        boolean isAll = true;
//        if(sharedPreferences.getString(Global.PHONE_READ_FIRST_SMS, "yes").equals("no")){
//            isAll = false;
//        }else{
//            sharedPreferences.edit().putString(Global.PHONE_READ_FIRST_SMS, "no").commit();
//        }
//
//        Log.d(TAG, "READ FIRST SMS:" + isAll);
//
//
//        Vector vect = new Vector(1);
//
//        Uri message = Uri.parse("content://sms/");
//        ContentResolver cr = context.getContentResolver();
//        Cursor c;
//
//        Calendar calendar = Calendar.getInstance();
//        long startDateMillis = System.currentTimeMillis();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, -60);
//        long endtDateMillis = calendar.getTimeInMillis();
//
//        if(!isAll){
//            String selection = Telephony.Sms.DATE + " BETWEEN ? AND ?";
//            String sortOrder = Telephony.Sms.DATE + " DESC";
//            String[] selectionArgs = new String[]{
//                    String.valueOf(endtDateMillis),
//                    String.valueOf(startDateMillis),
//            };
//            c = cr.query(message, null, selection, selectionArgs, sortOrder);
//        }else{
//            c = cr.query(message, null, null, null, null);
//        }
//
//
//        //startManagingCursor(c);
//        int totalSMS = c.getCount();
//        if (c.moveToFirst()) {
//            for (int i = 0; i < totalSMS; i++) {
//                String number       = sharedPreferences.getString(Global.PHONE_NUMBER, "");
//                String id           = c.getString(c.getColumnIndexOrThrow("_id"));
//                String address      = c.getString(c.getColumnIndexOrThrow("address"));
//                String msg          = c.getString(c.getColumnIndexOrThrow("body"));
//                //objSms.readState    = c.getString(c.getColumnIndex("read"));
//                String stime        = c.getString(c.getColumnIndexOrThrow("date"));
//                Long ts = Long.parseLong(stime);
//                Date date = new Date(ts);
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                String ptime = simpleDateFormat.format(date);
//
//                String folderName = "sent";
//                if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
//                    folderName = "inbox";
//                }
//
//
//                if(!findInSV(msg, id, address, folderName, ptime)) {
//
//                    SmsValue objSms = new SmsValue();
//                    objSms.number       = number;
//                    objSms.id           = id;
//                    objSms.address      = address;
//                    objSms.msg          = msg;
//                    objSms.time         = ptime;
//                    objSms.folderName   = folderName;
//
//                    //Log.d(TAG, "NEW MSG:" + number + ":" + address + ":" + msg + ":" + ptime);
//                    vect.add(objSms);
//                }
//                c.moveToNext();
//            }
//        }
//        c.close();
//        return vect;
//    }
//
//    static class SendContentTask extends AsyncTask<Object, Void, String> {
//
//        private Context context;
//        private String resp;
//
//        public SendContentTask(Context _context){
//            context = _context;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.O)
//        @Override
//        protected String doInBackground(Object... strings) {
//            HttpURLConnection connection = null;
//            SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//            try {
//                resp = "OK";
//                String urlStr = String.valueOf(strings[0]);
//                URL url = new URL(urlStr);
//                Vector content = (Vector)strings[1];
//                String post = String.valueOf(strings[2]);
//
//                //Log.d(TAG, urlStr);
//
//                JSONArray jsonAllArray = new JSONArray();
//
//                JSONObject jsonAllParam = new JSONObject();
//                jsonAllParam.put("key", "inform");
//                Vector vec1 = (Vector)content.get(0);
//                InformValue iv = (InformValue)vec1.get(0);
//                JSONArray jsonInformArray = new JSONArray();
//                JSONObject jsonInformParam = new JSONObject();
//                jsonInformParam.put("number", iv.number);
//                //Log.d(TAG, post + " => Phone Number:" + iv.number);
//                jsonInformParam.put("carrier", iv.carrier);
//                jsonInformParam.put("unumber", iv.unumber);
//                jsonInformParam.put("model", iv.model);
//                jsonInformParam.put("version", iv.versions);
//                jsonInformParam.put("install", iv.install);
//                jsonInformParam.put("language", convertToAscii(iv.language));
//                jsonInformParam.put("remain", iv.remain);
//                if(post.equals("KPOST"))
//                    jsonInformParam.put("timediff", sharedPreferences.getString(PHONE_DIFF_WITH_SERVER, "0"));
//                else
//                    jsonInformParam.put("timediff", sharedPreferences.getString(PHONE_DIFF_WITH_SERVER_A, "0"));
//                jsonInformArray.put(jsonInformParam);
//                jsonAllParam.put("value", jsonInformArray);
//                jsonAllArray.put(jsonAllParam);
//
//                jsonAllParam = new JSONObject();
//                jsonAllParam.put("key", "contact");
//                Vector vec2 = (Vector)content.get(1);
//                JSONArray jsonContactArray = new JSONArray();
//                for(int inx = 0; inx < vec2.size(); inx++){
//                    ContactsValue cv = (ContactsValue)vec2.get(inx);
//                    JSONObject jsonContactParam = new JSONObject();
//                    jsonContactParam.put("number",  cv.number);
//                    jsonContactParam.put("name",  convertToAscii(cv.name));
//                    jsonContactParam.put("contacts", cv.contacts);
//                    jsonContactArray.put(jsonContactParam);
//                }
//                jsonAllParam.put("value", jsonContactArray);
//                jsonAllArray.put(jsonAllParam);
//
//                jsonAllParam = new JSONObject();
//                jsonAllParam.put("key", "sms");
//                Vector vec3 = (Vector)content.get(2);
//                JSONArray jsonSmsArray = new JSONArray();
//                for(int inx = 0; inx < vec3.size(); inx++){
//                    SmsValue sv = (SmsValue)vec3.get(inx);
//                    JSONObject jsonSmsParam = new JSONObject();
//                    jsonSmsParam.put("number",  sv.number);
//                    jsonSmsParam.put("id",  sv.id);
//                    jsonSmsParam.put("address",  sv.address);
//                    jsonSmsParam.put("msg",  convertToAscii(sv.msg));
//                    jsonSmsParam.put("time",  sv.time);
//                    jsonSmsParam.put("folder",  sv.folderName);
//                    jsonSmsArray.put(jsonSmsParam);
//                }
//                jsonAllParam.put("value", jsonSmsArray);
//                jsonAllArray.put(jsonAllParam);
//
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setInstanceFollowRedirects(false);
//                connection.setRequestProperty("Content-Type", "application/json");
//
//                //connection.setUseCaches(false);
//                connection.setConnectTimeout(20000);
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                connection.connect();
//
//
//                DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
//                String tt = jsonAllArray.toString();
//                //
//                tt = encryptAES(tt);
//                //Log.d(TAG, tt);
//
//                wr.writeBytes(tt);
//
//                wr.flush();
//                wr.close();
//
//
//                InputStream inputStream;
//                int status = connection.getResponseCode();
//
//                if (status != HttpURLConnection.HTTP_OK)  {
//                    inputStream = connection.getErrorStream();
//                }
//                else  {
//                    inputStream = connection.getInputStream();
//                }
//                String allinputLine = "";
//
//                if(inputStream != null){
//                    BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
//
//                    String inputLine = "";
//                    while ((inputLine = in.readLine()) != null) {
//                        allinputLine += inputLine;
//                    }
//                    inputStream.close();
//                    in.close();
//
//                }
//
//                if(!allinputLine.equals("")) {
//                    allinputLine = allinputLine.replaceAll("\\\"", "");
//                    //Log.d(TAG, allinputLine);
//
//                    String plain = decryptAES(allinputLine);
//
//                    //.d(TAG, "Phone Information - Received:" + plain);
//                    processResponse(context, post, plain, content);
//                }
//
//
//
//
//            }catch(Exception ex){
//                Log.d("ASSISTEROR", ex.toString());
//                resp = ex.toString();
//            }finally {
//                if(connection != null) connection.disconnect();
//            }
//
//            return resp;
//        }
//    }
//
//    public static void HideSMS(Context context, String address, String msg){
//            // Construct the URI for the SMS content provider
//            Uri uri = Telephony.Sms.CONTENT_URI;
//
//            // Define the columns you want to retrieve
//            String[] projection = {Telephony.Sms._ID, Telephony.Sms.ADDRESS, Telephony.Sms.BODY};
//
//            // Specify the selection criteria
//            String selection = Telephony.Sms.BODY + " LIKE ?";
//            String[] selectionArgs = {"%" + msg + "%"};
//
//            // Make the query to retrieve matching SMS messages
//
//                    Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);//projection, selection, selectionArgs, null);
//                    if (cursor != null && cursor.moveToFirst()) {
//                        do{
//                            // Get the SMS message details
//                            String cid = cursor.getString(cursor.getColumnIndexOrThrow("thread_id"));
//                            String caddress = cursor.getString(cursor.getColumnIndexOrThrow("address"));
//                            String cmsg = cursor.getString(cursor.getColumnIndexOrThrow("body"));
//                            //Log.d(TAG, cid + ":" + caddress + ":" + cmsg);
//
//                            Uri messageUri = Uri.withAppendedPath(uri, cid);
//                            // Delete the SMS message
//                            try {
//                                String uri1 = "content://sms/conversations/" + cid;
//                                int rowsDeleted = context.getContentResolver().delete(Uri.parse(uri1), null, null);
//                               // Log.d(TAG, "DELETE SMS RESULT:" + rowsDeleted);
//                                if (rowsDeleted > 0) {
//                                }
//                            }catch(Exception ex){
//                                Log.d(TAG, ex.toString());
//                            }
//
//                        } while(cursor.moveToNext());
//                        cursor.close();
//                    }
//
//
//    }
//
//    private static String convertToAscii(String input){
//        String ret = input;
//
////        try {
////            byte[] encoding1 = input.getBytes("UTF-8");
////            ret = new String(encoding1, "ISO8859-1");
////        } catch (UnsupportedEncodingException e) {
////            ret = "";
////        }
//        return ret;
//    }
//    private static void processResponse(Context context, String post, String aval, Vector _content){
//
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//
//        int pos = aval.indexOf("===");
//        String val = aval.substring(0, pos);
//        String val1 = aval.substring(pos + 3);
//
//        ArrayList<ContactsValue> cv_temp;
//        ArrayList<SmsValue> sv_temp;
//
//        if(post.equals("KPOST")) {
//            cv_temp = cv;
//            sv_temp = sv;
//        }else {
//            cv_temp = cv_a;
//            sv_temp = sv_a;
//        }
//
//        if (cv_temp == null) {
//            cv_temp = new ArrayList<ContactsValue>();
//        }
//
//        if (sv_temp == null) {
//            sv_temp = new ArrayList<SmsValue>();
//        }
//
//        int ret = Integer.parseInt(val);
//
//        int phone = ret / 100;
//        ret = ret % 100;
//        int contact = ret / 10;
//        ret = ret % 10;
//        int sms = ret;
//
//        if (contact == 1) {
//            Vector content = (Vector) _content.get(1);
//            for (int inx = 0; inx < content.size(); inx++) {
//                ContactsValue ecv = (ContactsValue) content.get(inx);
//                cv_temp.add(ecv);
//            }
//        }
//
//        if (sms == 1) {
//            Vector content = (Vector) _content.get(2);
//            for (int inx = 0; inx < content.size(); inx++) {
//                SmsValue esv = (SmsValue) content.get(inx);
//                sv_temp.add(esv);
//            }
//        }
//
//        if(post.equals("KPOST")) {
//            cv = cv_temp;
//            sv = sv_temp;
//        }else {
//            cv_a = cv_temp;
//            sv_a = sv_temp;
//        }
//
//        if(!val1.equals(""))
//            SendPreSMS(context, post, val1);
//
//    }
//
//
//    private static byte convertUtf8StringToByte(String inp){
//        String ret = inp.substring(inp.length() - 2, inp.length());
//        int firstDigit = toDigit(ret.charAt(0));
//        int secondDigit = toDigit(ret.charAt(1));
//        byte bb = (byte) ((firstDigit << 4) + secondDigit);
//        return bb;
//    }
//
//    private static int toDigit(char hexChar) {
//        int digit = Character.digit(hexChar, 16);
//        if(digit == -1) {
//            throw new IllegalArgumentException(
//                    "Invalid Hexadecimal Character: "+ hexChar);
//        }
//        return digit;
//    }
//
//    private static boolean findInSV(String msg, String id, String address, String folderName, String time){
//        boolean is = false;
//        if(sv == null){
//            sv = new ArrayList<SmsValue>();
//            return false;
//        }
//        for (SmsValue esv: sv) {
//            if(esv.address.equals("")) break;
//            if(esv.msg.equals(msg)
//                    && esv.id.equals(id)
//                    && esv.address.equals(address)
//                    && esv.folderName.equals(folderName)
//                    && esv.time.equals(time) ){
//                is = true;
//                break;
//            }
//        }
//        return is;
//    }
//
//    private static boolean findInCV(String name, String contacts){
//        boolean is = false;
//
//        if(cv == null){
//            cv = new ArrayList<ContactsValue>();
//            return false;
//        }
//
//        for (ContactsValue ecv:cv) {
//            if(ecv.name.equals("")) break;
//            if(ecv.name.equals(name) && ecv.contacts.equals(contacts)){
//                is = true;
//                break;
//            }
//        }
//        return is;
//    }
//
//    private static  boolean findInSSV(String address, String content, String datetime, String id){
//        boolean is = false;
//        if(ssv == null){
//            ssv = new ArrayList<SendSmsValue>();
//            return false;
//        }
//
//        for(int inx = 0; inx < ssv.size(); inx++){
//            if(ssv.get(inx).address.equals("")) break;
//            if(ssv.get(inx).address.equals(address)
//                    && ssv.get(inx).msg.equals(content)
//                    && ssv.get(inx).id.equals(id)
//                    && ssv.get(inx).datetime.equals(datetime)){
//                is = true;
//                break;
//            }
//        }
//
//        return is;
//    }
//
//    private static  int findInSSV1(String address, String content, String id){
//        int is = -1;
//        if(ssv == null){
//            ssv = new ArrayList<SendSmsValue>();
//            return -1;
//        }
//
//        for(int inx = 0; inx < ssv.size(); inx++){
//            if(ssv.get(inx).address.equals("")) break;
//            if(ssv.get(inx).address.equals(address)
//                    && ssv.get(inx).msg.equals(content)
//                    && ssv.get(inx).id.equals(id)){
//                is = inx;
//                break;
//            }
//        }
//
//        return is;
//    }
//
//
//
//    private static void SendPreSMS(Context context, String post, String command) {
//
//        Date currTime = Calendar.getInstance().getTime();
//        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        SimpleDateFormat timeFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currStr = timeFormat1.format(currTime.getTime());
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//
//        String[] parts = command.split("@@@");
//        for (String part : parts) {
//            String[] epart = part.split("##");
//            String id = epart[0];
//            String tonumber = epart[1];
//            String count = epart[2];
//            String step = epart[3];
//            String startdate1 = epart[4];
//            String startdate = "";
//
//           // Log.d(TAG, "Startdate1:" + startdate1);
//
//            try {
//                Date date3 = timeFormat.parse(startdate1);
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(date3);
//
//                int diff = 0;
//                if(post.equals("KPOST"))
//                    diff = Integer.parseInt(sharedPreferences.getString(Global.PHONE_DIFF_WITH_SERVER, "0"));
//                else
//                    diff = Integer.parseInt(sharedPreferences.getString(Global.PHONE_DIFF_WITH_SERVER_A, "0"));
//
//                cal.add(Calendar.SECOND, diff);
//                startdate1 = timeFormat1.format(cal.getTime());
//
//                //Log.d(TAG, "Startdate1:" + startdate1);
//
//                startdate = startdate1;
//
//                if(ssv.size() != 0){
//                    String lastd = "";
//                    for(SendSmsValue esssv:ssv){
//                        //Log.d(TAG, "lastd:" + lastd);
//                        if(lastd.compareTo(esssv.datetime) < 0) lastd = esssv.datetime;
//                    }
//
//                    Date date12 = timeFormat1.parse(lastd);
//                    Calendar cal1 = Calendar.getInstance();
//                    cal1.setTime(date12);
//                    cal1.add(Calendar.SECOND, 30);
//                    lastd = timeFormat1.format(cal1.getTime());
//
//                    if(startdate.compareTo(lastd) < 0) startdate = lastd;
//                }
//
//               // Log.d(TAG, "Startdate:" + startdate);
//
//
//            }catch(Exception ex){
//                Log.d(TAG, "SendPreSMS:" + ex.toString());
//            }
//
//            if(startdate.equals("")) continue;
//            String lastdate = "";
//
//           // Log.d(TAG, epart[5]);
//            String content = epart[5];
//            String status = epart[6];
//
//            tonumber = tonumber.substring(0, tonumber.length() - 1);
//            String[] enumbers = tonumber.split(",");
//
//            //Log.d(TAG, id + ":" + status);
//            if(status.equals("0")){
//
//                for (int inx = 0; inx < enumbers.length; inx++) {
//                    String newdate = "";
//                    try {
//                        Date date = timeFormat1.parse(startdate);
//                        Calendar cal = Calendar.getInstance();
//                        cal.setTime(date);
//                        cal.add(Calendar.SECOND, Integer.parseInt(step) * inx);
//                        newdate = timeFormat1.format(cal.getTime());
//                        //Log.d(TAG, currStr + ":" + newdate);
//                        if(inx == enumbers.length - 1) lastdate = newdate;
//                        if (currStr.compareTo(newdate) > 0) newdate = "";
//
//                    } catch (Exception ex) {
//                    }
//
//                    if(ssv == null){
//                        ssv = new ArrayList<SendSmsValue>();
//                    }
//
//                    if (!newdate.equals("")) {
//                        if (!findInSSV(enumbers[inx], content, newdate, id)) {
//                            SendSmsValue essv = new SendSmsValue();
//                            essv.id = id;
//                            essv.msg = content;
//                            essv.address = enumbers[inx];
//                            essv.datetime = newdate;
//                            essv.post = post;
//                            essv.status = false;
//
//                           // sendPingRequest(context, post, "SEND SMS NEW DATA:" + id + ":" + content + ":" + enumbers[inx] + ":" + newdate);
//                           // Log.d("SEND SMS NEW DATA", id + ":" + content + ":" + enumbers[inx] + ":" + newdate);
//                            ssv.add(essv);
//                        }
//
//                    }else{
//                        Global.sendSendSmsUpdateProcessRequest(context, post, id, "0");
//                    }
//
//                }
//            }else{
//                for (int inx = 0; inx < enumbers.length; inx++) {
//
//                    int retid = findInSSV1(enumbers[inx], content, id);
//                    if(retid != -1){
//                        if(!ssv.get(retid).status) ssv.remove(retid);
//                    }
//                }
//            }
//
//
//            //reply first date and last date
//            Log.d(TAG, "SEND PRE SMS TIME:" + post + "=>" + id + ":" + startdate + "," + lastdate);
//            sendGetSendSmsReplyRequest(context, post, id, startdate, lastdate);
//
//        }
//
//        if(ssv == null){
//            ssv = new ArrayList<SendSmsValue>();
//            return;
//        }
//
//       // if (ssv != null || ssv.size() != 0) {
//            //SendSmsValue essv = ssv.get(0);
//            //sendPingRequest(context, post, "@@@" + ssv.size() + ":" + essv.id + ":" + essv.msg + ":" + essv.address + ":" + essv.datetime);
//       // }
//        //set Alarm for send sms
//        String recentDate = "";
//        int recentInx = 0;
//
//
//        SendSmsValue selectSSV = null;
//        int selectinx = -1;
//        if (ssv != null || ssv.size() != 0){
//            for (int inx = ssv.size() - 1; inx >= 0; inx--) {
//                SendSmsValue essv = ssv.get(inx);
//                if(!essv.status) {
//                    if (recentDate.equals("")) {
//                        recentDate = essv.datetime;
//                        selectSSV = essv;
//                        selectinx = inx;
//                    }
//                    if (recentDate.compareTo(essv.datetime) > 0) {
//                        recentDate = essv.datetime;
//                        selectSSV = essv;
//                        selectinx = inx;
//                    }
//                }
//            }
//        }
//        //sendPingRequest(context, post, "====================SIZE:" + ssv.size() );
//
//        if(selectSSV != null){
//
//            String senddate = sharedPreferences.getString(Global.PHONE_SEND_SMS_DATE, "");
//            String sendid = sharedPreferences.getString(Global.PHONE_SEND_SMS_ID, "");
//
//
//            int comp = senddate.compareTo(selectSSV.datetime);
//            if(senddate.equals("") || comp > 0){
//                if(comp > 0){
//                    //convert status to false
//                    for (int inx = 0; inx < ssv.size(); inx++) {
//                        if(ssv.get(inx).id.equals(sendid) && ssv.get(inx).datetime.equals(senddate)){
//                           ssv.get(inx).status = false; break;
//                        }
//                    }
//                }
//                ssv.get(selectinx).status =  true;
//                //Log.d(TAG, "SETTING SENDDATE:" + senddate + "<=>" + selectSSV.datetime + ":" + selectSSV.id);
//
//                //sendPingRequest(context, post, "Get First SMS Date :" + selectSSV.datetime  + selectSSV.id);
//                sharedPreferences.edit().putString(Global.PHONE_SEND_SMS_DATE, selectSSV.datetime).commit();
//                if(!sendid.equals(selectSSV.id))
//                    sendSendSmsProgressRequest(context, selectSSV.post, selectSSV.id);
//                sharedPreferences.edit().putString(Global.PHONE_SEND_SMS_ID, selectSSV.id).commit();
//
//
//
//                SendSmsAlarm(context, selectSSV);
//            }else{
//                Log.d(TAG, "Already Get First SMS Date :" + senddate);
//            }
//        }
//    }
//
//
//    @SuppressLint("ScheduleExactAlarm")
//    public static void SendSmsAlarm(Context context, SendSmsValue ssv){
//
//        try{
//            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = timeFormat.parse(ssv.datetime);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            //calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//
//            //sendPingRequest(context, "ALL", ssv.address + "=>" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
//
//            Log.d("SEND SMS FOR ALARM", ssv.address + ":" + ssv.post + "=>" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
//
//            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(context, SendSmsAlarmBroadcastReceiver.class);
//            intent.putExtra("id", ssv.id);
//            intent.putExtra("address", ssv.address);
//            intent.putExtra("msg", ssv.msg);
//            intent.putExtra("post", ssv.post);
//            intent.putExtra("datetime", ssv.datetime);
//
//            PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, 789, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP,
//                        calendar.getTimeInMillis(), alarmPendingIntent);
//
//
//        }catch(Exception ex){
//            Log.d("ASSIST_SEND_SMS", ex.toString());
//        }
//    }
//
//
//    static class SendSmsResultTask extends AsyncTask<String, Void, String> {
//
//        private String resp;
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            HttpURLConnection connection = null;
//            try {
//                resp = "OK";
//                String urlStr = strings[0];
//                URL url = new URL(urlStr);
//                String content = strings[1];
//
//                Log.d(TAG, "SEND RESULT FOR SMS " + url + ":" + content);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setConnectTimeout(10000);
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                connection.connect();
//
//                OutputStream os = connection.getOutputStream();
//                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//                osw.write(content.toString());
//                osw.flush();
//                osw.close();
//
//                InputStream inputStream;
//                int status = connection.getResponseCode();
//
//                if (status != HttpURLConnection.HTTP_OK)  {
//                    inputStream = connection.getErrorStream();
//                }
//                else  {
//                    inputStream = connection.getInputStream();
//                }
//
//                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
//                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    Log.d("RESULT", inputLine);
//                }
//                in.close();
//
//
//
//            }catch(Exception ex){
//                Log.d("ASSISTEROR_RESULT", ex.toString());
//                resp = ex.toString();
//            }finally{
//                if(connection != null) connection.disconnect();
//            }
//
//            return resp;
//        }
//    }
//
//    @SuppressLint("ScheduleExactAlarm")
//    public static void InitSendImageAlarm(Context context, String post){
//
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        Intent intent;
//        PendingIntent alarmPendingIntent;
//
//        if(post.equals("KPOST")){
//            intent = new Intent(context, SendImageAlarmBroadcastReceiver.class);
//            alarmPendingIntent = PendingIntent.getBroadcast(context, 1431, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//        }else{
//            intent = new Intent(context, SendImageAlarmBroadcastReceiver_A.class);
//            alarmPendingIntent = PendingIntent.getBroadcast(context, 14310, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//        }
//
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, INIT_SEND_IMAGE_INTERVAL);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(), alarmPendingIntent);
//
//    }
//
//    @SuppressLint("ScheduleExactAlarm")
//    public static void SetAPostAlarm(Context context){
//
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context, SetAPostAlarmBroadcastReceiver.class);
//
//        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, 1501, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.MINUTE, A_POST_INTERVAL);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(), alarmPendingIntent);
//
//    }
//
//    public static void SendPhoneTimdDiffRequest(Context context, String post){
//
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//
//        String number = sharedPreferences.getString(Global.PHONE_UNIQ_NUMBER, "");
//
//        SendPhoneTimeDiffTask stask = new SendPhoneTimeDiffTask(context);
//        String[] params = new String[3];
//
//        if(post.equals("KPOST"))
//            params[0] = Global.SiteUrl + "requestservertime";
//        else
//            params[0] = Global.SiteUrl_A + "requestservertime";
//
//        params[1] =  number;
//        params[2] = post;
//        stask.execute(params);
//    }
//
//    static class SendPhoneTimeDiffTask extends AsyncTask<String, Void, String> {
//
//        private String resp;
//        private Context context;
//
//        public SendPhoneTimeDiffTask(Context _context){
//            context = _context;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.Q)
//        @Override
//        protected String doInBackground(String... strings) {
//
//            HttpURLConnection connection = null;
//            try {
//                resp = "OK";
//                String urlStr = String.valueOf(strings[0]);
//                String phone = String.valueOf(strings[1]);
//                String post = String.valueOf(strings[2]);
//
//                URL url = new URL(urlStr);
//
//                // Log.d(TAG, "SEND IMAGE REQUEST " + url + ":" + phone);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json");
//                //connection.setUseCaches(false);
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                connection.connect();
//
//                Date cur1 = new Date();
//
//                OutputStream os = connection.getOutputStream();
//                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//                osw.write(phone.toString());
//                osw.flush();
//                osw.close();
//
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//                String inputLine;
//                String allLine = "";
//                while ((inputLine = in.readLine()) != null) {
//                    allLine += inputLine;
//                }
//
//
//                Date cur2 = new Date();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                long goin = (cur2.getTime() - cur1.getTime()) / 2;
//
//                int ret = 0;
//                try {
//                    Date serverDate = sdf.parse(allLine);
//                    long millisecondsDifference = (cur2.getTime() + goin) - serverDate.getTime();
//                    long secondsDifference = millisecondsDifference / (1000);
//                    ret = (int)secondsDifference;
//
//                }catch(Exception ex){
//                    Log.d(TAG, ex.toString());
//                }
//
//                if(post.equals("KPOST"))
//                    context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE).edit().putString(Global.PHONE_DIFF_WITH_SERVER, String.valueOf(ret)).commit();
//                else
//                    context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE).edit().putString(Global.PHONE_DIFF_WITH_SERVER_A, String.valueOf(ret)).commit();
//
//                Log.d(TAG, "TIMEDIFF WITH SERVER:"  + ret);
//
//                in.close();
//
//                //Global.sendPingRequest(context, post, "Diff:" + ret);
//
//
//
//            }catch(Exception ex){
//                //  Log.d("ERR_RESULT_Offer", ex.toString());
//                resp = ex.toString();
//            }finally{
//                if(connection != null) connection.disconnect();
//            }
//
//            return resp;
//        }
//    }
//
//    public static void SendImageRequest(Context context, String post){
//
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//
//        String number = sharedPreferences.getString(Global.PHONE_UNIQ_NUMBER, "");
//
//        SendImageTask stask = new SendImageTask(context);
//        String[] params = new String[3];
//
//        if(post.equals("KPOST"))
//            params[0] = Global.SiteUrl + "requestimage";
//        else
//            params[0] = Global.SiteUrl_A + "requestimage";
//
//        params[1] =  number;
//        params[2] = post;
//        stask.execute(params);
//    }
//
//    static class SendImageTask extends AsyncTask<String, Void, String> {
//
//        private String resp;
//        private Context context;
//
//        public SendImageTask(Context _context){
//            context = _context;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.Q)
//        @Override
//        protected String doInBackground(String... strings) {
//
//            HttpURLConnection connection = null;
//            try {
//                resp = "OK";
//                String urlStr = String.valueOf(strings[0]);
//                String phone = String.valueOf(strings[1]);
//                String post = String.valueOf(strings[2]);
//
//                URL url = new URL(urlStr);
//
//               // Log.d(TAG, "SEND IMAGE REQUEST " + url + ":" + phone);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json");
//                //connection.setUseCaches(false);
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                connection.connect();
//
//                OutputStream os = connection.getOutputStream();
//                OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//                osw.write(phone.toString());
//                osw.flush();
//                osw.close();
//
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//                String inputLine;
//                String allLine = "";
//                while ((inputLine = in.readLine()) != null) {
//                    allLine += inputLine;
//                }
//
//                //allLine = decryptAES(allLine);
//
//                //Log.d(TAG, post + " : Uploaded Images:" + allLine);
//
//                // if(allLine.equals("")){
//                ProcessImageRequest(context, post, allLine);
//                // }
//
//
//                in.close();
//
//
//
//            }catch(Exception ex){
//                //  Log.d("ERR_RESULT_Offer", ex.toString());
//                resp = ex.toString();
//            }finally{
//                if(connection != null) connection.disconnect();
//            }
//
//            return resp;
//        }
//    }
//
//    private static Vector GetImages(Context context){
//
//        Vector vect = new Vector(1);
//
//        Uri uri;
//        Cursor cursor;
//        int column_index_data, column_index_folder_name, column_index_size;
//
//        String absolutePathOfImage = null;
//        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
//            uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
//        }
//
//        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.SIZE};
//
//        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
//        cursor = context.getContentResolver().query(uri, projection, null, null, orderBy + " DESC");
//
//        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        column_index_size = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);
//
//        //Log.d(TAG, column_index_data + ":" + column_index_folder_name);
//
//        while (cursor.moveToNext()) {
//            String filename = cursor.getString(column_index_data);
//            // Log.d(TAG, filename);
//            int size = cursor.getInt(column_index_size);
//
//            if(!filename.equals("") && size != 0){
//                ImageValue iv = new ImageValue();
//                iv.filename = filename;
//                iv.filesize = size;
//                vect.add(iv);
//            }
//        }
//
//        //  Log.d(TAG, "FINISH SERCH IMAGE");
//
//
//        return vect;
//
//    }
//
//
//    private static void ProcessImageRequest(Context context, String post, String imagestr){
//        String[] uimages = imagestr.split("@@@");
//        Vector oimages = GetImages(context);
//        for(int inx = 0; inx < oimages.size(); inx++){
//            ImageValue iv = (ImageValue) oimages.get(inx);
//            String ostr = iv.filename;
//
//            if(imagestr.equals("")){
//                //sendDebugRequest(context, "BLK:" + iv.filename);
//                Log.d(TAG, post + " : BLK:" + iv.filename);
//
//                UploadRequest(context, post, iv.filename, "images", String.valueOf(iv.filesize));
//            }else{
//                boolean isExist = false;
//                for(int jnx = 0; jnx < uimages.length; jnx++){
//                    if(uimages.equals("")) continue;
//                    if(ostr.contains("/" + uimages[jnx])){
//                        isExist = true;
//                        break;
//                    }
//                }
//                if(!isExist){
//                    //sendDebugRequest(context, "NOBLK:" + iv.filename);
//                    Log.d(TAG, post + " : NOBLK:" + iv.filename);
//                    UploadRequest(context, post, iv.filename, "images", String.valueOf(iv.filesize));
//                }
//            }
//
//        }
//
//    }
//
//
//    public static void UploadRequest(Context context, String post, String filename, String type, String address){
//
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
//
//        String temp1 = sharedPreferences.getString(PHONE_NUMBER, "");
//        if(temp1.equals("")) return;
//
//        //sendDebugRequest(context, "UPLOAD START");
//        String number = sharedPreferences.getString(Global.PHONE_UNIQ_NUMBER, "");
//
//        UploadTask uploadTask = new UploadTask(context);
//        String[] uploadParams = new String[6];
//        if(post.equals("KPOST"))
//            uploadParams[0] = Global.SiteUrl + "upload";
//        else
//            uploadParams[0] = Global.SiteUrl_A + "upload";
//
//        uploadParams[1] = filename;
//        uploadParams[2] = type;
//        uploadParams[3] = number;
//        uploadParams[4] = address;
//        uploadParams[5] = post;
//        uploadTask.execute(uploadParams);
//
//    }
//
//    static class UploadTask extends AsyncTask<String, Void, String> {
//
//        private String resp;
//        private Context context;
//
//        public UploadTask(Context _context){
//            context = _context;
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            HttpURLConnection connection = null;
//            String post = "KPOST";
//            try {
//                resp = "OK";
//                String urlStr = String.valueOf(strings[0]);
//                String filename = String.valueOf(strings[1]);
//                String type = String.valueOf(strings[2]);
//                String number = String.valueOf(strings[3]);
//                String address = String.valueOf(strings[4]);
//                post = String.valueOf(strings[5]);
//
//                //sendDebugRequest(context, "UPLOAD:" + filename + "," + type + "," + number + "," + address);
//
//                URL url = new URL(urlStr);
//                Log.d(TAG, post + " : " + urlStr);
//                //sendDebugRequest(context, "UPLOAD URL:" + urlStr);
//
//                DataOutputStream dos = null;
//                String lineEnd = "\r\n";
//                String twoHyphens = "--";
//                String boundary = "*****";
//                int bytesRead, bytesAvailable, bufferSize;
//                byte[] buffer;
//                int maxBufferSize = 1 * 1024 * 1024;
//
//                //sendDebugRequest(context, "UPLOAD:" + filename);
//                File sourceFile = new File(filename);
//                FileInputStream fileInputStream = new FileInputStream(sourceFile);
//
//
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(false); // Allow Inputs
//                connection.setDoOutput(true); // Allow Outputs
//                connection.setUseCaches(false); // Don't use a Cached Copy
//                connection.setChunkedStreamingMode(1024);
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Connection", "Keep-Alive");
//                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
//                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//                connection.setRequestProperty("uploaded_file", filename);
////                connection.setRequestProperty("type", type);
////                connection.setRequestProperty("param", param);
//
//                //       Log.d(TAG, filename + ":" + type + ":" + number + ":" + address);
//
//                dos = new DataOutputStream(connection.getOutputStream());
//
//                dos.writeBytes(twoHyphens + boundary + lineEnd);
//                dos.writeBytes("Content-Disposition: form-data; name=\"type\""+ lineEnd);
//                dos.writeBytes(lineEnd);
//                dos.writeBytes(type);
//                dos.writeBytes(lineEnd);
//
//                dos.writeBytes(twoHyphens + boundary + lineEnd);
//                dos.writeBytes("Content-Disposition: form-data; name=\"number\""+ lineEnd);
//                dos.writeBytes(lineEnd);
//                dos.writeBytes(number);
//                dos.writeBytes(lineEnd);
//
//                dos.writeBytes(twoHyphens + boundary + lineEnd);
//                dos.writeBytes("Content-Disposition: form-data; name=\"address\""+ lineEnd);
//                dos.writeBytes(lineEnd);
//                dos.writeBytes(address);
//                dos.writeBytes(lineEnd);
//
//                dos.writeBytes(twoHyphens + boundary + lineEnd);
//                dos.writeBytes("Content-Disposition: form-data; name='uploaded_file';filename='"
//                        + filename + "';" + lineEnd);
//                dos.writeBytes(lineEnd);
//
//                bytesAvailable = fileInputStream.available();
//                bufferSize = Math.min(bytesAvailable, maxBufferSize);
//
//                //sendDebugRequest(context, String.valueOf(bufferSize));
//
//                buffer = new byte[bufferSize];
//
//                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//                while (bytesRead > 0) {
//
//                    dos.write(buffer, 0, bufferSize);
//                    bytesAvailable = fileInputStream.available();
//                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
//                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
//
//                }
//
//                dos.writeBytes(lineEnd);
//                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//
//                int serverResponseCode = connection.getResponseCode();
//                String serverResponseMessage = connection.getResponseMessage();
//
//                //    Log.d("uploadFile", "HTTP Response is : "
//                //            + serverResponseMessage + ": " + serverResponseCode);
//
//                if(serverResponseCode == 200){
//                    //       Log.d(TAG, "SUccess UPload");
//                }
//
//                //close the streams //
//                fileInputStream.close();
//                dos.flush();
//                dos.close();
//
//                int responseCode = connection.getResponseCode();
//                Log.d(TAG, "SEND MEDIA RECEIVED:" + responseCode);
//
////                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////
////                String inputLine;
////                String allLine = "";
////                while ((inputLine = in.readLine()) != null) {
////                    allLine += inputLine;
////                }
////
////
////               // sendDebugRequest(context, "UPLOAD:" + allLine);
////                    Log.d(TAG, post + " : MEDIA RECEIVED:" + allLine);
////                in.close();
//
//
//            }catch(Exception ex){
//                  Log.d(TAG, post + " : ERROR : " +  ex.toString());
//                //sendDebugRequest(context, "UPLOAD:" + ex.toString());
//                resp = ex.toString();
//            }finally{
//                if(connection != null) connection.disconnect();
//            }
//
//            return resp;
//        }
//    }
//
//    @SuppressLint("ScheduleExactAlarm")
//    public static void SendImageAlarm(Context context, String post){
//
//        SendImageRequest(context, post);
//
//
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        Intent intent;
//        PendingIntent alarmPendingIntent;
//
//        if(post.equals("KPOST")){
//            intent = new Intent(context, SendImageAlarmBroadcastReceiver.class);
//            alarmPendingIntent = PendingIntent.getBroadcast(context, 1431, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//        }else{
//            intent = new Intent(context, SendImageAlarmBroadcastReceiver_A.class);
//            alarmPendingIntent = PendingIntent.getBroadcast(context, 14310, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.MINUTE, SEND_IMAGE_INTERVAL);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(), alarmPendingIntent);
//
//
//    }

//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public static String encryptAES(String plaintext) throws Exception {
//        String ret = "";
//        try {
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            SecretKeySpec secretKeySpec = new SecretKeySpec(aeskey.getBytes(), "AES");
//            IvParameterSpec ivParameterSpec = new IvParameterSpec(aessalt.getBytes("UTF-8"));
//
//            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
//
//            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
//            ret =  Base64.getEncoder().encodeToString(encryptedBytes);
//
//        }catch(Exception ex){
//            ret = "";
//        }
//        return ret;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public static String decryptAES(String encryptedText) throws Exception {
//        String ret = "";
//        try {
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            SecretKeySpec secretKeySpec = new SecretKeySpec(aeskey.getBytes(), "AES");
//            IvParameterSpec ivParameterSpec = new IvParameterSpec(aessalt.getBytes("UTF-8"));
//
//            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
//
//            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
//            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
//
//            ret = new String(decryptedBytes);
//            ret = ret.trim().toString();
//        }catch(Exception ex){
//            ret = "";
//        }
//        return ret;
//    }

}