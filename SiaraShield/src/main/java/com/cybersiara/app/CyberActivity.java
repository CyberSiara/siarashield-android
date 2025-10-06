package com.cybersiara.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

public class CyberActivity extends AppCompatActivity {

//    static SwipeButton swipeButton;
    public static FrameLayout frame, frame_without_active;
    static ImageView img_gif, captcha_image, img_refresh;
    static EditText ed_captcha;
    static AlertDialog alert;
    static ProgressBar progress;
    static String str_ip = "", str_id = "", str_width = "", str_height = "", str_name = "", RequestId = "", Visiter_Id = "",
            str_auth = "", str_protocol = "https", str_device_type = "Android",
            str_device_browser = "chrome", str_second = "8", str_bypass = "Netural", str_flag = "1";
    static CardView card_captcha;
//    public static LinearLayout l_submit;
    public static LinearLayout main_layout;
    static TextView txt_privacy, txt_terms, txt_no_auth;
    static String MASTER_URL = "";
    static String AUTH_KEY = "";
    static String str_package_name = "";
    static Timer T;
    static int str_timespent = 0;
    static Activity activity;
    static String verify_response = "";
    static Bitmap bitmap_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private static void showAlertDialog() {

      timerstart();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity, R.style.CustomAlertDialog);
        final View customLayout = activity.getLayoutInflater().inflate(R.layout.popup_layout, null);

        alertDialog.setView(customLayout);
        alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        ImageView img_close = (ImageView) customLayout.findViewById(R.id.img_close);
        captcha_image = (ImageView) customLayout.findViewById(R.id.img_captcha);
        ed_captcha = (EditText) customLayout.findViewById(R.id.ed_captcha);
        progress = (ProgressBar) customLayout.findViewById(R.id.progress_circular);
        Spinner spinner = (Spinner) customLayout.findViewById(R.id.spinner2);
        img_refresh = (ImageView) customLayout.findViewById(R.id.img_refresh);
        TextView txt_privacy = (TextView) customLayout.findViewById(R.id.txt_privacy);
        TextView txt_terms = (TextView) customLayout.findViewById(R.id.txt_terms);
        ImageView img_visibility = customLayout.findViewById(R.id.img_visiblity);
        ImageView img_menu = customLayout.findViewById(R.id.img_menu);
        ImageView icon_image = customLayout.findViewById(R.id.icon_image);

        icon_image.setImageBitmap(bitmap_logo);

        img_visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.cybersiara.com/accessibility"));
                activity.startActivity(browserIntent);
            }
        });

        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initializing the popup menu and giving the reference as current context
                Context wrapper = new ContextThemeWrapper(activity, R.style.popupMenuStyle);
                PopupMenu popupMenu = new PopupMenu(wrapper, v);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.accessibility) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                            browserIntent.setData(Uri.parse("https://www.cybersiara.com/accessibility"));
                            activity.startActivity(browserIntent);
                            return true;
                        } else if (id == R.id.report_image) {
                            Intent browserIntent1 = new Intent(Intent.ACTION_VIEW);
                            browserIntent1.setData(Uri.parse("https://mycybersiara.com/ReportBug"));
                            activity.startActivity(browserIntent1);
                            return true;
                        } else if (id == R.id.report_bug) {
                            Intent browserIntent2 = new Intent(Intent.ACTION_VIEW);
                            browserIntent2.setData(Uri.parse("https://mycybersiara.com/ReportBug"));
                            activity.startActivity(browserIntent2);
                            return true;
                        }
                        return false;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });

        txt_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.cybersiara.com/privacy"));
                activity.startActivity(browserIntent);
            }
        });

        txt_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.cybersiara.com/terms"));
                activity.startActivity(browserIntent);
            }
        });

        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.cancel();
                timerstart();
                if (ConnectivityDetector.isConnectingToInternet(activity)) {
                    new GenerateCaptcha().execute();
                } else {
                    Toast.makeText(activity.getApplicationContext(),activity.getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                }
            }
        });

        if (ConnectivityDetector.isConnectingToInternet(activity)) {
            new GenerateCaptcha().execute();
        } else {
            Toast.makeText(activity.getApplicationContext(),activity.getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }

        String[] years = {"English","Hindi","Gujarati","Marathi"};
        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(activity, R.layout.spinner_text, years );
        langAdapter.setDropDownViewResource(R.layout.spinner_text);
        spinner.setAdapter(langAdapter);

        spinner.setSelection(0, true);
        View view = spinner.getSelectedView();
        ((TextView) view).setTextColor(Color.parseColor("#FFFFFF"));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)view).setTextColor(Color.parseColor("#FFFFFF"));
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.cancel();
                alert.dismiss();
                frame.setVisibility(View.GONE);
                frame_without_active.setVisibility(View.GONE);

            }
        });

        ed_captcha.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
               if (s.length() == 4){
                   T.cancel();
                   if (ConnectivityDetector.isConnectingToInternet(activity)) {
                       new CaptchaVerify().execute();
                   } else {
                       Toast.makeText(activity.getApplicationContext(),activity.getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                   }
               }
            }
        });
    }

    public static String submitData(Activity c){
        activity = c;
        if (ConnectivityDetector.isConnectingToInternet(activity)) {
            new SubmitCaptcha().execute();
        } else {
            Toast.makeText(activity.getApplicationContext(),activity.getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }
        return verify_response;
    }

    public static String dataPass(String master, String auth, String packageName, Activity c){
        MASTER_URL = master;
        AUTH_KEY = auth;
        str_package_name = packageName;
        activity = c;
        verify_response = "";

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.activity_cybersiara, (ViewGroup) activity.findViewById(R.id.main_layout), true);  // this row
        txt_privacy = layout.findViewById(R.id.txt_privacy);
        frame = layout.findViewById(R.id.frame_active);
        img_gif = layout.findViewById(R.id.img_gif);
        card_captcha = layout.findViewById(R.id.card_captcha);
        frame_without_active = layout.findViewById(R.id.frame_without_active);
        main_layout = layout.findViewById(R.id.main_layout);
        txt_terms = layout.findViewById(R.id.txt_terms);
        txt_no_auth = layout.findViewById(R.id.txt_no_auth);

        frame.setVisibility(View.GONE);
        frame_without_active.setVisibility(View.GONE);

        WifiManager wifiManager = (WifiManager) activity.getApplicationContext().getSystemService(WIFI_SERVICE);
        str_ip = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());

        str_id = Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;
        str_width = String.valueOf(screenWidth);
        str_height = String.valueOf(screenHeight);

        str_name = Build.MODEL;

        if(MASTER_URL != null && AUTH_KEY != null){
            if (ConnectivityDetector.isConnectingToInternet(activity)) {
                new Login().execute();
            } else {
                Toast.makeText(activity,activity.getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(activity, "Master Key and Auth Key Not Found", Toast.LENGTH_LONG).show();
        }

        txt_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.cybersiara.com/privacy"));
                activity.startActivity(browserIntent);
            }
        });

        txt_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse("https://www.cybersiara.com/terms"));
                activity.startActivity(browserIntent);
            }
        });

        return verify_response;
    }

    private static void timerstart() {
        str_timespent = 0;
        T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        str_timespent++;
                    }
                });
            }
        }, 1000, 1000);
    }

    @SuppressLint("StaticFieldLeak")
    public static class Login extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog = new ProgressDialog(activity);

        protected void onPreExecute() {
            Dialog.show();
            Dialog.setMessage(activity.getResources().getString(R.string.please_wait));
            Dialog.setCancelable(false);
        }

        @SuppressLint("WrongThread")
        protected String doInBackground(String... arg0) {
            try {

                URL url = new URL(ApiConfig.Login_URL);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("MasterUrlId", MASTER_URL);
                postDataParams.put("RequestUrl", str_package_name);
                postDataParams.put("BrowserIdentity", str_id);
                postDataParams.put("DeviceIp", str_ip);
                postDataParams.put("DeviceType", str_device_type);
                postDataParams.put("DeviceBrowser", str_device_browser);
                postDataParams.put("DeviceName", str_name);
                postDataParams.put("DeviceHeight", str_height);
                postDataParams.put("DeviceWidth", str_width);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {

                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Dialog.dismiss();
            LoginResponse(result);

        }
    }

    private static void LoginResponse(String response) {

        if (!response.equals("")) {
            try {
                JSONObject jsobjectcategory = new JSONObject(response);

                String message = jsobjectcategory.getString("Message");
                RequestId = jsobjectcategory.getString("RequestId");
                Visiter_Id = jsobjectcategory.getString("Visiter_Id");
                if (message.equals("success")){
                }else{
                    txt_no_auth.setText(message);
                    txt_no_auth.setVisibility(View.VISIBLE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public static class SubmitCaptcha extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        @SuppressLint("WrongThread")
        protected String doInBackground(String... arg0) {
            try {

                URL url = new URL(ApiConfig.SUBMIT_CAPTCHA_URL);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("MasterUrl", MASTER_URL);
                postDataParams.put("BrowserIdentity", str_id);
                postDataParams.put("DeviceIp", str_ip);
                postDataParams.put("DeviceName", str_name);
                postDataParams.put("VisiterId", Visiter_Id);
                postDataParams.put("RequestID", RequestId);
                postDataParams.put("second", str_second);
                postDataParams.put("Protocol", str_protocol);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());

            }
        }

        @Override
        protected void onPostExecute(String result) {
            SubmitCaptchaResponse(result);
        }
    }

    private static void SubmitCaptchaResponse(String response) {

        if (!response.equals("")) {
            try {
                JSONObject jsobjectcategory = new JSONObject(response);

                String message = jsobjectcategory.getString("Message");
                String logo = jsobjectcategory.getString("CustomLogo");
                str_auth = jsobjectcategory.getString("data");
                if (message.equals("success")){
                    verify_response = "true";
                    Toast.makeText(activity, "Verified", Toast.LENGTH_LONG).show();
                    if (ConnectivityDetector.isConnectingToInternet(activity)) {
                        new Validatetoken().execute();
                    } else {
                        Toast.makeText(activity.getApplicationContext(),activity.getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                    }
                }else if (message.equals("fail")){
                    verify_response = "false";

                    // show not verified message here
                    Toast.makeText(activity, "Captcha Not Verified ", Toast.LENGTH_LONG).show();

                    if (logo != null && !logo.isEmpty()) {
                        try {
                            // 🔹 Remove prefix first
                            if (logo.startsWith("data:image")) {
                                logo = logo.substring(logo.indexOf(",") + 1);
                            }

                            // 🔹 Decode Base64
                            byte[] decodedBytes = Base64.decode(logo, Base64.DEFAULT);

                            // 🔹 Convert to Bitmap
                            bitmap_logo = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    showAlertDialog();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public static class GenerateCaptcha extends AsyncTask<String, Void, String> {


        protected void onPreExecute() {
            progress.setVisibility(View.VISIBLE);
            captcha_image.setVisibility(View.GONE);
        }

        @SuppressLint("WrongThread")
        protected String doInBackground(String... arg0) {
            try {

                URL url = new URL(ApiConfig.GENERATE_CAPTCHA_URL);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("MasterUrlId", MASTER_URL);
                postDataParams.put("RequestUrl", str_package_name);
                postDataParams.put("BrowserIdentity", str_id);
                postDataParams.put("DeviceIp", str_ip);
                postDataParams.put("DeviceType", str_device_type);
                postDataParams.put("DeviceBrowser", str_device_browser);
                postDataParams.put("DeviceName", str_name);
                postDataParams.put("DeviceHeight", str_height);
                postDataParams.put("DeviceWidth", str_width);
                postDataParams.put("VisiterId", Visiter_Id);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            GenerateCaptchaResponse(result);
        }
    }

    private static void GenerateCaptchaResponse(String response) {

        if (!response.equals("")) {
            try {
                JSONObject jsobjectcategory = new JSONObject(response);

                String message = jsobjectcategory.getString("Message");
                String gif = jsobjectcategory.getString("HtmlFormate");
                String logo = jsobjectcategory.getString("CustomLogo");
                if (message.equals("success")){
                    progress.setVisibility(View.GONE);
                    captcha_image.setVisibility(View.VISIBLE);
                    Glide.with(activity).load(gif).into(captcha_image);

                    if (logo != null && !logo.isEmpty()) {
                        try {
                            // 🔹 Remove prefix first
                            if (logo.startsWith("data:image")) {
                                logo = logo.substring(logo.indexOf(",") + 1);
                            }

                            // 🔹 Decode Base64
                            byte[] decodedBytes = Base64.decode(logo, Base64.DEFAULT);

                            // 🔹 Convert to Bitmap
                            bitmap_logo = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else{
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public static class CaptchaVerify extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog = new ProgressDialog(activity);

        protected void onPreExecute() {
            Dialog.show();
            Dialog.setMessage(activity.getResources().getString(R.string.please_wait));
            Dialog.setCancelable(false);
        }

        @SuppressLint("WrongThread")
        protected String doInBackground(String... arg0) {
            try {

                URL url = new URL(ApiConfig.CAPTCHA_VERIFY_URL);
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("MasterUrl", MASTER_URL);
                postDataParams.put("BrowserIdentity", str_id);
                postDataParams.put("DeviceIp", str_ip);
                postDataParams.put("DeviceType", str_device_type);
                postDataParams.put("DeviceName", str_name);
                postDataParams.put("VisiterId", Visiter_Id);
                postDataParams.put("UserCaptcha", ed_captcha.getText().toString());
                postDataParams.put("ByPass", str_bypass);
                postDataParams.put("Timespent", str_timespent);
                postDataParams.put("Protocol", str_protocol);
                postDataParams.put("Flag", str_flag);
                postDataParams.put("second", str_second);
                postDataParams.put("RequestID", RequestId);
                postDataParams.put("fillupsecond", str_timespent);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Dialog.dismiss();
            CaptchaVerifyResponse(result);

        }
    }

    private static void CaptchaVerifyResponse(String response) {

        if (!response.equals("")) {
            try {
                JSONObject jsobjectcategory = new JSONObject(response);

                String message = jsobjectcategory.getString("Message");
                String gif = jsobjectcategory.getString("HtmlFormate");
                str_auth = jsobjectcategory.getString("data");
                if (message.equals("success")){
                    alert.dismiss();
                    verify_response = "true";
                    Toast.makeText(activity, "Verified", Toast.LENGTH_LONG).show();
                    if (ConnectivityDetector.isConnectingToInternet(activity)) {
                        new Validatetoken().execute();
                    } else {
                        Toast.makeText(activity.getApplicationContext(),activity.getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                    }
                }else{
                    verify_response = "false";

                    Toast.makeText(activity, "Captcha Not Verified", Toast.LENGTH_LONG).show();
                    ed_captcha.setText("");
                    T.cancel();
                    timerstart();
                    if (ConnectivityDetector.isConnectingToInternet(activity)) {
                        new GenerateCaptcha().execute();
                    } else {
                        Toast.makeText(activity.getApplicationContext(),activity.getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Validatetoken extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            String result = "";
            try {
                URL url = new URL(ApiConfig.VALIDATE_TOKEN_URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("ip", str_ip);
                urlConnection.setRequestProperty("Authorization", "Bearer "+str_auth);
                urlConnection.setRequestProperty("key", AUTH_KEY);

                int code = urlConnection.getResponseCode();

                if(code==200){
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    if (in != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line = "";

                        while ((line = bufferedReader.readLine()) != null)
                            result += line;
                    }
                    in.close();
                }

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            finally {
                urlConnection.disconnect();
            }
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            ValidatetokenResponse(result);
            super.onPostExecute(result);
        }
    }

        private static void ValidatetokenResponse(String response) {

        if (!response.equals("")) {
            try {
                JSONObject jsobjectcategory = new JSONObject(response);

                String message = jsobjectcategory.getString("Message");
                if (message.equals("Verified")){
                    verify_response = "true";
                    str_auth = "";
                }else{
                    verify_response = "fail";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


}