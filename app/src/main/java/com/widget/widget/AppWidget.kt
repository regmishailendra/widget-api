package com.widget.widget

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


@Suppress("DEPRECATION")
class AppWidget : AppWidgetProvider() {
    private val TOP_CLICK = "top"
    private val RIGHT_CLICK = "right"
    private val LEFT_CLICK = "left"
    private val BOTTOM_CLICK = "bottom"

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (TOP_CLICK.equals(intent?.getAction())) {
            Toast.makeText(context, "Clicked top", Toast.LENGTH_SHORT).show();
            MyAsyncTop(context).execute()


        } else if (LEFT_CLICK.equals(intent?.getAction())) {
            Toast.makeText(context, "Clicked left", Toast.LENGTH_SHORT).show();
            MyAsyncLeft(context).execute()
        } else if (RIGHT_CLICK.equals(intent?.getAction())) {
            Toast.makeText(context, "Clicked right", Toast.LENGTH_SHORT).show();
            MyAsyncRight(context).execute()
        } else if (BOTTOM_CLICK.equals(intent?.getAction())) {
            Toast.makeText(context, "Clicked bottom", Toast.LENGTH_SHORT).show();
            MyAsyncBottom(context).execute()
        }
    }

    protected fun getPendingSelfIntent(context: Context?, action: String?): PendingIntent? {
        val intent = Intent(context, javaClass)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
      //login


        appWidgetIds?.forEach {
            val intent = Intent(context, MainActivity::class.java)
            val pIntent = PendingIntent.getActivity(context, 0, intent, 0)
            val views: RemoteViews = RemoteViews(context?.packageName, R.layout.layout_widget)
            views.setOnClickPendingIntent(R.id.btnTop, getPendingSelfIntent(context, TOP_CLICK))
            views.setOnClickPendingIntent(R.id.btnLeft, getPendingSelfIntent(context, LEFT_CLICK))
            views.setOnClickPendingIntent(R.id.btnRight, getPendingSelfIntent(context, RIGHT_CLICK))
            views.setOnClickPendingIntent(
                R.id.btnBottom,
                getPendingSelfIntent(context, BOTTOM_CLICK)
            )
            appWidgetManager?.updateAppWidget(it, views)
        }

//            val views: RemoteViews = RemoteViews(context?.packageName, R.layout.layout_widget)
//            views.setOnClickPendingIntent(R.id.btnTop)




    }


    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTop(val context: Context?) :
        AsyncTask<Void?, Void, String>() {
        var urlConnection: HttpURLConnection? = null
        var url: URL? = null
        var obj: JSONObject? = null
        var myArray: JSONArray? = null
        var inStream: InputStream? = null
        override fun doInBackground(vararg p0: Void?): String {
            try {
                val url = URL("https://jsonkeeper.com/b/NSKS")
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection!!.setRequestMethod("GET")
                urlConnection!!.setDoOutput(false)
                urlConnection!!.setDoInput(true)
                urlConnection!!.connect()
                inStream = urlConnection!!.getInputStream()

                val bReader = BufferedReader(InputStreamReader(inStream))
                var temp: String? = ""
                var response: String? = ""
                while (bReader.readLine().also({ temp = it }) != null) {
                    response += temp
                }
                obj = JSONTokener(response).nextValue() as JSONObject
                val obj1 = obj!!.getBoolean("0")
                val obj2 = obj!!.getBoolean("1")
                val obj3 = obj!!.getBoolean("2")
                Log.v("checkingchk", "the obj is " + obj1 + " " + obj2 + " " + obj3)

            } catch (e: Exception) {
                e.toString()

            } finally {
                if (inStream != null) {
                    try {
                        inStream!!.close()
                    } catch (ignored: IOException) {
                    }
                }
                if (urlConnection != null) {
                    urlConnection!!.disconnect()
                }
            }
            return obj.toString()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            obj = JSONTokener(result).nextValue() as JSONObject
            val obj1 = obj!!.getBoolean("0")
            val obj2 = obj!!.getBoolean("1")
            val obj3 = obj!!.getBoolean("2")
            Log.v("checkingchk", "the obj is " + obj1 + " " + obj2 + " " + obj3)

            val views: RemoteViews = RemoteViews(context?.packageName, R.layout.layout_widget)
            views.setTextViewText(R.id.btnTop, "" + obj1)
           AppWidgetManager.getInstance(context).updateAppWidget(ComponentName(context!!, AppWidget::class.java), views)



//            appWidgetManager?.updateAppWidget(R.id.tvApi, views);
            Log.v("checkingchk", "m here")


        }

    }

    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncRight(val context: Context?) :
        AsyncTask<Void?, Void, String>() {

        var urlConnection: HttpURLConnection? = null
        var url: URL? = null
        var obj: JSONObject? = null
        var myArray: JSONArray? = null
        var inStream: InputStream? = null
        override fun doInBackground(vararg p0: Void?): String {
            try {
                val url = URL("https://jsonkeeper.com/b/NSKS")
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection!!.setRequestMethod("GET")
                urlConnection!!.setDoOutput(false)
                urlConnection!!.setDoInput(true)
                urlConnection!!.connect()
                inStream = urlConnection!!.getInputStream()

                val bReader = BufferedReader(InputStreamReader(inStream))
                var temp: String? = ""
                var response: String? = ""
                while (bReader.readLine().also({ temp = it }) != null) {
                    response += temp
                }
                obj = JSONTokener(response).nextValue() as JSONObject
                val obj1 = obj!!.getBoolean("0")
                val obj2 = obj!!.getBoolean("1")
                val obj3 = obj!!.getBoolean("2")
                Log.v("checkingchk", "the obj is " + obj1 + " " + obj2 + " " + obj3)

            } catch (e: Exception) {
                e.toString()

            } finally {
                if (inStream != null) {
                    try {
                        inStream!!.close()
                    } catch (ignored: IOException) {
                    }
                }
                if (urlConnection != null) {
                    urlConnection!!.disconnect()
                }
            }
            return obj.toString()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            obj = JSONTokener(result).nextValue() as JSONObject
            val obj1 = obj!!.getBoolean("0")
            val obj2 = obj!!.getBoolean("1")
            val obj3 = obj!!.getBoolean("2")
            Log.v("checkingchk", "the obj is " + obj1 + " " + obj2 + " " + obj3)

            val views: RemoteViews = RemoteViews(context?.packageName, R.layout.layout_widget)
            views.setTextViewText(R.id.btnRight, "" + obj1)
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(
                    context!!,
                    AppWidget::class.java
                ), views
            )
//            appWidgetManager?.updateAppWidget(R.id.tvApi, views);
            Log.v("checkingchk", "m here")


        }

    }

    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncLeft(val context: Context?) :
        AsyncTask<Void?, Void, String>() {

        var urlConnection: HttpURLConnection? = null
        var url: URL? = null
        var obj: JSONObject? = null
        var myArray: JSONArray? = null
        var inStream: InputStream? = null
        override fun doInBackground(vararg p0: Void?): String {
            try {
                val url = URL("https://jsonkeeper.com/b/NSKS")
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection!!.setRequestMethod("GET")
                urlConnection!!.setDoOutput(false)
                urlConnection!!.setDoInput(true)
                urlConnection!!.connect()
                inStream = urlConnection!!.getInputStream()

                val bReader = BufferedReader(InputStreamReader(inStream))
                var temp: String? = ""
                var response: String? = ""
                while (bReader.readLine().also({ temp = it }) != null) {
                    response += temp
                }
                obj = JSONTokener(response).nextValue() as JSONObject
                val obj1 = obj!!.getBoolean("0")
                val obj2 = obj!!.getBoolean("1")
                val obj3 = obj!!.getBoolean("2")
                Log.v("checkingchk", "the obj is " + obj1 + " " + obj2 + " " + obj3)

            } catch (e: Exception) {
                e.toString()

            } finally {
                if (inStream != null) {
                    try {
                        inStream!!.close()
                    } catch (ignored: IOException) {
                    }
                }
                if (urlConnection != null) {
                    urlConnection!!.disconnect()
                }
            }
            return obj.toString()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            obj = JSONTokener(result).nextValue() as JSONObject
            val obj1 = obj!!.getBoolean("0")
            val obj2 = obj!!.getBoolean("1")
            val obj3 = obj!!.getBoolean("2")
            Log.v("checkingchk", "the obj is " + obj1 + " " + obj2 + " " + obj3)

            val views: RemoteViews = RemoteViews(context?.packageName, R.layout.layout_widget)
            views.setTextViewText(R.id.btnLeft, "" + obj1)
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(
                    context!!,
                    AppWidget::class.java
                ), views
            )
//            appWidgetManager?.updateAppWidget(R.id.tvApi, views);
            Log.v("checkingchk", "m here")


        }

    }

    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncBottom(val context: Context?) :
        AsyncTask<Void?, Void, String>() {

        var urlConnection: HttpURLConnection? = null
        var url: URL? = null
        var obj: JSONObject? = null
        var myArray: JSONArray? = null
        var inStream: InputStream? = null
        override fun doInBackground(vararg p0: Void?): String {
            try {
                val url = URL("https://jsonkeeper.com/b/NSKS")
                urlConnection = url.openConnection() as HttpURLConnection
                urlConnection!!.setRequestMethod("GET")
                urlConnection!!.setDoOutput(false)
                urlConnection!!.setDoInput(true)
                urlConnection!!.connect()
                inStream = urlConnection!!.getInputStream()

                val bReader = BufferedReader(InputStreamReader(inStream))
                var temp: String? = ""
                var response: String? = ""
                while (bReader.readLine().also({ temp = it }) != null) {
                    response += temp
                }
                obj = JSONTokener(response).nextValue() as JSONObject
                val obj1 = obj!!.getBoolean("0")
                val obj2 = obj!!.getBoolean("1")
                val obj3 = obj!!.getBoolean("2")
                Log.v("checkingchk", "the obj is " + obj1 + " " + obj2 + " " + obj3)

            } catch (e: Exception) {
                e.toString()

            } finally {
                if (inStream != null) {
                    try {
                        inStream!!.close()
                    } catch (ignored: IOException) {
                    }
                }
                if (urlConnection != null) {
                    urlConnection!!.disconnect()
                }
            }
            return obj.toString()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            obj = JSONTokener(result).nextValue() as JSONObject
            val obj1 = obj!!.getBoolean("0")
            val obj2 = obj!!.getBoolean("1")
            val obj3 = obj!!.getBoolean("2")
            Log.v("checkingchk", "the obj is " + obj1 + " " + obj2 + " " + obj3)

            val views: RemoteViews = RemoteViews(context?.packageName, R.layout.layout_widget)
            views.setTextViewText(R.id.btnBottom, "" + obj1)
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(
                    context!!,
                    AppWidget::class.java
                ), views
            )
//            appWidgetManager?.updateAppWidget(R.id.tvApi, views);
            Log.v("checkingchk", "m here")


        }

    }


}