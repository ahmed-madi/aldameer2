package net.is.ps.addameer;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ContactsFragment extends Fragment{

    private static boolean switcher;
    TextView facebook;
    TextView twitter;
    TextView mobile;
    TextView phone;
    TextView email;
    TextView fax;
    TextView postbox;
    TextView adress;
    TextView website;
    TextView setting;
    TextView sms;
    TextView mailtv;
    TextView dameer;
    TextView version;
    Switch switch1;
    RelativeLayout langu;


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
        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JF-Flat-regular.ttf");


        //  View rootView = inflater.inflate(R.layout.list0row0about, container, false);
        // ScrollView scrollView=(ScrollView) rootView.findViewById(R.id.sv);
        View rootView = inflater.inflate(R.layout.contacts, container, false);
         switch1 = (Switch) rootView.findViewById(R.id.switch1);
        langu = (RelativeLayout) rootView.findViewById(R.id.langu);
         facebook = (TextView)  rootView.findViewById(R.id.facebook);
         twitter = (TextView)  rootView.findViewById(R.id.twittertv);
        mobile  = (TextView)  rootView.findViewById(R.id.mobiletv);
         phone=(TextView)  rootView.findViewById(R.id.telphonetv);
         email= (TextView)  rootView.findViewById(R.id.emailtv);
         fax=(TextView)  rootView.findViewById(R.id.faxtv);
         postbox=(TextView)  rootView.findViewById(R.id.mailboxtv);
         adress=(TextView)  rootView.findViewById(R.id.addresstv);
         website=(TextView)  rootView.findViewById(R.id.websitetv);
        mailtv=(TextView)  rootView.findViewById(R.id.mailtv);
        sms = (TextView) rootView.findViewById(R.id.smstv);
        setting=(TextView)  rootView.findViewById(R.id.setttting);
        dameer = (TextView) rootView.findViewById(R.id.dameer);
        version = (TextView) rootView.findViewById(R.id.version);

        facebook.setTypeface(typeFace);
        twitter.setTypeface(typeFace);
        mobile.setTypeface(typeFace);
        phone.setTypeface(typeFace);
        email.setTypeface(typeFace);
        fax.setTypeface(typeFace);
        postbox.setTypeface(typeFace);
        adress.setTypeface(typeFace);
        website.setTypeface(typeFace);
        mailtv.setTypeface(typeFace);
        sms.setTypeface(typeFace);
        setting.setTypeface(typeFace);
        dameer.setTypeface(typeFace);
        version.setTypeface(typeFace);


        String url = Url_language.getBaseUrl()+"contacts/"+getLanguageCurrent();

       // new JSONTask().execute("http://195.154.226.224/~aldameer/api/v1/contacts/en");

        new JSONTask().execute(url);


        return rootView;
    }





    public void onItemClick(AdapterView<?>arg0, View arg1 , int arg2, long arg3){
//        Toast.makeText(getContext(),"hello",Toast.LENGTH_LONG).show();

    }

    public static boolean getSwitcher() {
        return switcher;
    }


    public class JSONTask extends AsyncTask<String, String, List<Contacts>> {

        @Override
        protected List<Contacts> doInBackground(String... params) {
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




                List<Contacts> itemsList= new ArrayList<>();

                String finalJSON =buffer.toString();
                JSONObject parentObject = new JSONObject(finalJSON);
                JSONArray parentArray = parentObject.getJSONArray("items");

                Gson gson = new Gson();
                for(int i=0;i<parentArray.length();i++){

                    JSONObject finalObject = parentArray.getJSONObject(i);
                    Contacts item = gson.fromJson(finalObject.toString(),Contacts.class);

                    itemsList.add(item);

                }
                return itemsList;

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

        public void onPostExecute(List<Contacts> result) {
            super.onPostExecute(result);

            //set data to list view or something
            Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JF-Flat-regular.ttf");

            facebook.setText( result.get(0).getValue());
            facebook.setTypeface(typeFace);
             twitter.setText( result.get(1).getValue());
            twitter.setTypeface(typeFace);

            mobile.setText( result.get(2).getValue());
            mobile.setTypeface(typeFace);
             phone.setText( result.get(3).getValue());
            phone.setTypeface(typeFace);
             email.setText( result.get(4).getValue());
            email.setTypeface(typeFace);
             fax.setText( result.get(5).getValue());
            fax.setTypeface(typeFace);
             postbox.setText( result.get(6).getValue());
            postbox.setTypeface(typeFace);
             adress.setText( result.get(7).getValue());
            adress.setTypeface(typeFace);
             website.setText( result.get(8).getValue());
            website.setTypeface(typeFace);

            switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    switcher=isChecked;
                    Log.v("Switch State=", ""+switcher);
                }

            });



            langu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Locale current = getResources().getConfiguration().locale;
                    Log.d("ddd",current.toString());
            if("en_US".equals(current.toString())||"en_us".equals(current.toString())){
                String languageToLoad  = "Ar";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getContext().getResources().updateConfiguration(config,getContext().getResources().getDisplayMetrics());

                Intent intent = new Intent(getActivity(), ThreeTabsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }else{
                String languageToLoad  = "en_US";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getContext().getResources().updateConfiguration(config,getContext().getResources().getDisplayMetrics());

                Intent intent = new Intent(getActivity(), ThreeTabsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }


                }
            });


        }

    }



}
