package net.is.ps.addameer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class AppAbout extends Fragment{


    WebView webView;
    public String getLanguageCurrent() {
        Locale current = getResources().getConfiguration().locale;

        if ("en_US".equals(current.toString()) || "en_us".equals(current.toString())) {
            return "en";

        } else {
            return "ar";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //  View rootView = inflater.inflate(R.layout.list0row0about, container, false);
        // ScrollView scrollView=(ScrollView) rootView.findViewById(R.id.sv);
        View rootView = inflater.inflate(R.layout.about_api_webview, container, false);

        webView = (WebView) rootView.findViewById(R.id.webView);


        //  String url = "http://195.154.226.224/~aldameer/api/v1/news-cat/"+s+"/en";
        String url = Url_language.getBaseUrl()+"about-app/"+getLanguageCurrent();
        //      new JSONTask().execute("http://195.154.226.224/~aldameer/api/v1/about-us/en");
    //    new JSONTask().execute(url);
      //  new JSONTask().execute("http://195.154.226.224/~aldameer/api/v1/about-app/en");


        final StringRequest postsrequest3 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //     Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    String encodedstring = URLEncoder.encode(response, "ISO-8859-1");
                    response = URLDecoder.decode(encodedstring, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                List<Catagories> itemsList = new ArrayList<>();
                try {


                    JSONObject parentObject = new JSONObject(response);
                    JSONObject parent2 =parentObject.getJSONObject("items");
                    String x=  parent2.getString("description");
                  //  String m = Html.fromHtml(x).toString();


                    webView.loadData(x, "text/html; charset=UTF-8", null);


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) ;
        Volley.newRequestQueue(getContext()).add(postsrequest3);


        return rootView;
    }



    public void onItemClick(AdapterView<?>arg0, View arg1 , int arg2, long arg3){
//        Toast.makeText(getContext(),"hello",Toast.LENGTH_LONG).show();

    }


    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJSON =buffer.toString();
                JSONObject parentObject = new JSONObject(finalJSON);
                JSONObject parent2 =parentObject.getJSONObject("items");

                String x=  parent2.getString("description");


                return x;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (connection != null) {

                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        public void onPostExecute(String result) {
            super.onPostExecute(result);

            //set data to list view or something

            webView.loadData(result, "text/html", null);


        }

    }





}
