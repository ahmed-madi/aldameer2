package net.is.ps.addameer;

import android.content.Context;
import android.graphics.Typeface;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

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

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static net.is.ps.addameer.R.layout.listrow_vote;


/**
 * Created by Administrator on 11/9/2016.
 */

/**
 * Created by Administrator on 11/12/2016.
 */
public class VoteAdapter2 extends ArrayAdapter {
    private List<Vote> itemsModeList;
    private int resource;
    private LayoutInflater inflater;
    private int id2;
    ListView view;
    String x=null;

    Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JF-Flat-regular.ttf");

    public VoteAdapter2(Context context, int resource, List<Vote> objects) {
        super(context, resource, (List) objects);
        itemsModeList=objects;
        this.resource=resource;
        inflater=(LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }


    String questionId = null;
    public String getLanguageCurrent() {
        Locale current = getContext().getResources().getConfiguration().locale;

        if ("en_US".equals(current.toString()) || "en_us".equals(current.toString())) {
            return "en";

        } else {
            return "ar";
        }
    }

    public void setData(String data) {
        questionId = data;
    }
    public View getView(int position, View convertView, ViewGroup parent) {



        view = (ListView)parent;


        ViewHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(resource, null);

            holder = new ViewHolder(convertView);
            //because we pass R.layout.rowlist in argu " int resource " we can reference to it directly

            holder.ButtonName = (RadioButton) convertView.findViewById(R.id.radioButton3);
            holder.prog = (ProgressBar) convertView.findViewById(R.id.progressBar3);
            holder.Precentage = (TextView) convertView.findViewById(R.id.textView7);

            convertView.setTag(holder);

        }else{
            holder=(ViewHolder)convertView.getTag();
        }


        holder.ButtonName.setText(itemsModeList.get(position).getOption());
        //   holder.prog.setProgress(itemsModeList.get(position).getOption_percent());
        //   holder.Precentage.setText(itemsModeList.get(position).getOption_percent()+"%");

        holder.prog.setVisibility(View.INVISIBLE);


        holder.Precentage.setText(x);

        holder.Precentage.setTypeface(typeFace);
        holder.ButtonName.setTypeface(typeFace);




        holder.ButtonName.setOnClickListener(onClickRadio(position,holder));

        return convertView;

    }
    private View.OnClickListener onClickRadio(final int position, final ViewHolder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final String device_type="android";

                final String user_token = Settings.Secure.getString(getContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);


                String url = Url_language.getBaseUrl()+"vote/"+getLanguageCurrent();


            //    String url = "http://195.154.226.224/~aldameer/api/v1/vote/en";


                StringRequest postsrequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {

                        // Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();

                        try {
                            String encodedstring = URLEncoder.encode(response, "ISO-8859-1");
                            response = URLDecoder.decode(encodedstring, "UTF-8");

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        List<Vote> itemsList = null;
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject items = object.getJSONObject("items");
                            JSONArray options = items.getJSONArray("options");


                            itemsList = new ArrayList<>();

                            Gson gson = new Gson();
                            for (int i = 0; i < options.length(); i++) {

                                JSONObject finalObject = options.getJSONObject(i);
                                Vote item = gson.fromJson(finalObject.toString(), Vote.class);
                                itemsList.add(item);

                            }


                            VoteAdapter3 adapter3 = new VoteAdapter3(getContext(), listrow_vote, itemsList);
                            adapter3.setData(questionId);

                            view.setAdapter(adapter3);
                            Helper.getListViewSize(view);



                        } catch (JSONException e) {
                            e.printStackTrace();

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

                        String x= String.valueOf(itemsModeList.get(position).getId());

                        map.put("qustion_id",questionId);
                        map.put("option_id",x);

                        map.put("device_type",device_type);
                        map.put("user_token",user_token);
                        return map;
                    }

                };
                Volley.newRequestQueue(getContext()).add(postsrequest);

            }

        };

    }




    class ViewHolder {

        //     private ImageButton floatingActionButton;
        private RadioButton ButtonName;
        private ProgressBar prog;
        private TextView Precentage;

        //  private TextView views;
        //  private TextView writer;
        //  private TextView desc;

        // private View btnDelete;
        // private SwipeLayout swipeLayout;

        //the buttons and action will show after swipe
        public ViewHolder(View v) {

        }
    }

}












