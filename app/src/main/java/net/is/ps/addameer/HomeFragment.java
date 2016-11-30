package net.is.ps.addameer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static net.is.ps.addameer.R.layout.listrow_catagories;
import static net.is.ps.addameer.R.layout.listrow_vote;


public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    ViewPager viewPager;
    TextView questionTV;
    SliderAdapter adapter23;

    public int counterfro;
    public int setCounterFro(int counterfro){
        return this.counterfro=counterfro;
    }

    ListView lst2,listVote;

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

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);




       // String url = "http://195.154.226.224/~aldameer/api/v1/home/en";
        String url = Url_language.getBaseUrl()+"home/"+getLanguageCurrent();
       // Log.d("url:",url2);
        final String device_type="android";
        final String user_token = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);


        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config); // Do it on Application start


        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JF-Flat-regular.ttf");
        TextView lastnews = (TextView) rootView.findViewById(R.id.lastnews);
        lastnews.setTypeface(typeFace);
        TextView catagories = (TextView) rootView.findViewById(R.id.catagories);
        catagories.setTypeface(typeFace);
        TextView vote = (TextView) rootView.findViewById(R.id.catagories);
        vote.setTypeface(typeFace);

        // For SLIDER ..

        final StringRequest postsrequest3 = new StringRequest(Request.Method.POST,url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                //     Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    String encodedstring = URLEncoder.encode(response, "ISO-8859-1");
                    response = URLDecoder.decode(encodedstring, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                List<Catagories> itemsList = null;
                boolean is_active = false;
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject items = object.getJSONObject("items");
                    JSONArray latest_news = items.getJSONArray("slider");


                    itemsList = new ArrayList<>();

                    Gson gson = new Gson();
                    for (int i = 0; i < latest_news.length(); i++) {

                        JSONObject finalObject = latest_news.getJSONObject(i);
                        Catagories item = gson.fromJson(finalObject.toString(), Catagories.class);
                        itemsList.add(item);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }

                //set data to View Page or something


                viewPager=(ViewPager) rootView.findViewById(R.id.view_pager);
                adapter23 = new SliderAdapter(getContext(),itemsList);

                viewPager.setAdapter(adapter23);



                class TapGestureListener extends GestureDetector.SimpleOnGestureListener{

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {


                        int y =0;
                        y=viewPager.getCurrentItem();
                        int ccd = adapter23.itemsModeList.get(y).getId();
                        String questionId = String.valueOf(ccd);
                        //String questionId= String.valueOf(y);
            //            Toast.makeText(getContext(),"this is "+questionId,Toast.LENGTH_LONG).show();


                        OneNewsFragment oneNewsFragment = new OneNewsFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, oneNewsFragment );;

                        transaction.addToBackStack(null);

                        Bundle bundle=new Bundle();
                        bundle.putString("key1",questionId);
                        oneNewsFragment.setArguments(bundle);
                        transaction.commit();

                        return false;
                    }

                }

                final GestureDetector  tapGestureDetector = new GestureDetector(getContext(), new TapGestureListener());

                viewPager.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        
                        tapGestureDetector.onTouchEvent(event);
                        return false;
                    }
                });


            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                HashMap map = new HashMap();
                map.put("device_type",device_type);
                map.put("user_token",user_token);
                return map;
            }

        };
        Volley.newRequestQueue(getContext()).add(postsrequest3);


        //For List Of latest_news  ,
        // LAST news from each CATAGORIES send by the server

        lst2=(ListView)rootView.findViewById(R.id.lst2);

        final StringRequest postsrequest2 = new StringRequest(Request.Method.POST,url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                //     Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                try {
                    String encodedstring = URLEncoder.encode(response, "ISO-8859-1");
                    response = URLDecoder.decode(encodedstring, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                List<Catagories> itemsList = null;
                boolean is_active = false;
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject items = object.getJSONObject("items");
                    JSONArray latest_news = items.getJSONArray("latest_news");


                    itemsList = new ArrayList<>();

                    Gson gson = new Gson();
                    for (int i = 0; i < latest_news.length(); i++) {

                        JSONObject finalObject = latest_news.getJSONObject(i);
                        Catagories item = gson.fromJson(finalObject.toString(), Catagories.class);
                        itemsList.add(item);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }

                //set data to list view or something

                Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JF-Flat-regular.ttf");


                CatagoriesFragmentAdapter adapter2 = new CatagoriesFragmentAdapter(getContext(), listrow_catagories,itemsList,typeFace);

                lst2.setAdapter(adapter2);
                Helper.getListViewSize(lst2);



            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                HashMap map = new HashMap();


                map.put("device_type",device_type);
                map.put("user_token",user_token);
                return map;
            }

        };
        Volley.newRequestQueue(getContext()).add(postsrequest2);



        lst2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Catagories CatObject = (Catagories) parent.getAdapter().getItem(position);
                String questionId= String.valueOf(CatObject.getId());
             //   Toast.makeText(getContext(),questionId,Toast.LENGTH_LONG).show();


                OneNewsFragment oneNewsFragment = new OneNewsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, oneNewsFragment );;

                transaction.addToBackStack("home");

                Bundle bundle=new Bundle();
                bundle.putString("key1",questionId);
                oneNewsFragment.setArguments(bundle);

                transaction.commit();


            }
        });


            /// FOR VOOOTTTEEE ..


        questionTV =(TextView)rootView.findViewById(R.id.question);
        questionTV.setTypeface(typeFace);

        listVote = (ListView)rootView.findViewById(R.id.ListVote);

        final StringRequest postsrequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                //     Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                int prog = 0;
                try {
                    String encodedstring = URLEncoder.encode(response, "ISO-8859-1");
                    response = URLDecoder.decode(encodedstring, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                List<Vote> itemsList = null;
                JSONObject questionnair = null;
                boolean is_active = false;
                String questionId = null;
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject items = object.getJSONObject("items");
                    questionnair = items.getJSONObject("questionnair");
                    JSONArray options = questionnair.getJSONArray("options");


                    String questionnairString = questionnair.getString("question");
                    questionTV.setText(questionnairString);


                    is_active = questionnair.getBoolean("is_active");

                    int id2 = questionnair.getInt("id");
                    questionId = String.valueOf(id2);


                    itemsList = new ArrayList<>();

                    Gson gson = new Gson();
                    for (int i = 0; i < options.length(); i++) {

                        JSONObject finalObject = options.getJSONObject(i);
                        Vote item = gson.fromJson(finalObject.toString(), Vote.class);
                        itemsList.add(item);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }

                //false not active for voting
                if (is_active==false) {
                    VoteAdapter adapter = new VoteAdapter(getContext(), listrow_vote, itemsList);
                    adapter.setData(questionId);
                    listVote.setAdapter(adapter);
                    Helper.getListViewSize(listVote);
//is active true it means we can vote and it will be active for voting
                }else if ((is_active==true)){

                    VoteAdapter2 adapter2 = new VoteAdapter2(getContext(), listrow_vote, itemsList);
                    adapter2.setData(questionId);
                    listVote.setAdapter(adapter2);
                    Helper.getListViewSize(listVote);

                }


            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                HashMap map = new HashMap();


                map.put("device_type",device_type);
                map.put("user_token",user_token);
                return map;
            }

        };
        Volley.newRequestQueue(getContext()).add(postsrequest);










        return rootView;
    }







    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public void onItemClick(AdapterView<?>arg0, View arg1 , int arg2, long arg3){


    }



}



class Helper {
    public static void getListViewSize(ListView myListView) {
        ListAdapter myListAdapter = myListView.getAdapter();
        if (myListAdapter == null) {
            //do nothing return null
            return;
        }
        //set listAdapter in loop for getting final size
        int totalHeight = 0;
        for (int size = 0; size < myListAdapter.getCount(); size++) {
            View listItem = myListAdapter.getView(size, null, myListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //setting listview item in adapter
        ViewGroup.LayoutParams params = myListView.getLayoutParams();
        params.height = totalHeight + (myListView.getDividerHeight() * (myListAdapter.getCount() - 1));
        myListView.setLayoutParams(params);
        // print height of adapter on log
      //  Log.i("height of listItem:", String.valueOf(totalHeight));
    }
}




