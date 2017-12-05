package com.ssru.travel;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectAPI {
    public static String URL = "http://192.168.1.45:8989";

    public void getTravelAll(final Context mContext) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/travel")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTemple " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((TravelAllActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getNewsId(final Context mContext, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/news/" + id)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getNewsId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((DetailNewsActivity) mContext).setView(string, URL);
                }
            }
        }.execute();
    }

    public void getGalleryId(final Context mContext, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/gallery/" + id)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getGalleryId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((ViewPagerActivity) mContext).setView(string, URL);
                }
            }
        }.execute();
    }


    public void getTravelMap(final Context mContext, final GoogleMap mMap) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/travel/map")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTravelMap " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((MapWatActivity) mContext).addMarker(mMap, string, URL);
                    ((MapWatActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getNewsAll(final Context mContext) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/news")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .addHeader("postman-token", "a8bf0dca-f75d-ba16-329e-54be1d474ee0")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTemple " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound(mContext);
                } else {
                    ((NewsActivity) mContext).setAdap(string, URL);
                }
            }
        }.execute();
    }

    public void getImgTravelAll(final Context mContext) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/travel/image")
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getImgTravelAll " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else if (string.equals("[]")) {
                    dialogNotfound2(mContext);
                } else {
                    ((MainActivity) mContext).setHeader(string, URL);
                }
            }
        }.execute();
    }

    public void getTravelId(final Context mContext, final int id) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(URL + "/api/travel/id/" + id)
                        .get()
                        .addHeader("cache-control", "no-cache")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        return response.body().string();
                    } else {
                        return "Not Success - code : " + response.code();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error - " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String string) {
                super.onPostExecute(string);
                Log.d("ConnectAPI : ", "getTempleId " + string);
                String[] temp = string.split(" ");
                if (temp[0].equals("Error") || temp[0].equals("Not")) {
                    dialogErrorNoIntent(mContext, string);
                } else {
                    ((TravelDetailActivity) mContext).setView(string, URL);
//                    new GsonBuilder().create().toJson(new Gson().fromJson(string, ModelTravelDetail.class))
                }
            }
        }.execute();
    }

    private static void dialogError(final Context mContext, String string) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ไม่พบข้อมูล กรุณาลองใหม่ภายหลัง error code = " + string)
                .setNegativeButton("OK", null)
                .show();
    }

    private static void NoApart(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ขออภัย ท่านยังไม่มีหอพัก หรือกรุณาลองใหม่อีกครั้ง")
                .setNegativeButton("OK", null)
                .show();
    }

    private static void ActiveError1(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ขออภัย ท่านไม่สามารถส่งซ้ำได้")
                .setNegativeButton("OK", null)
                .show();
    }

    private static void ActiveError2(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ขออภัย รหัสยืนยันไม่ถูกต้องกรุณาลองใหม่อีกครั้ง")
                .setNegativeButton("OK", null)
                .show();
    }

    private static void ActiveError3(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Failed")
                .setMessage("ขออภัย รหัสยืนยันถูกใช้งานแล้ว")
                .setNegativeButton("OK", null)
                .show();
    }


    private static void dialogErrorNoIntent(final Context mContext, String string) {
        new AlertDialog.Builder(mContext)
                .setTitle("The system temporarily")
                .setMessage("ไม่สามารถเข้าใช้งานได้ กรุณาลองใหม่ภายหลัง error code = " + string)
                .setNegativeButton("OK", null)
                .show();
    }

    private static void dialogNotfound(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Not Found")
                .setMessage("ไม่พบข้อมูล")
                .setNegativeButton("OK", null)
                .show();
    }

    private static void dialogNotfound2(final Context mContext) {
        new AlertDialog.Builder(mContext)
                .setTitle("Not Found")
                .setMessage("ไม่พบข้อมูล กรุณาลองใหม่ภายหลัง")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        ((MainActivity) mContext).finish();
                    }
                })
                .show();
    }

}
