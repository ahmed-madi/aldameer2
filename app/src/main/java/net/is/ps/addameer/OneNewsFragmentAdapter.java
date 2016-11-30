package net.is.ps.addameer;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 11/7/2016.
 */

public class  OneNewsFragmentAdapter extends ArrayAdapter {
    private List<Catagories> itemsModeList;
    private int resource;
    private LayoutInflater inflater;




    public OneNewsFragmentAdapter(Context context, int resource, List<Catagories> objects) {
        super(context, resource, objects);
        itemsModeList=objects;
        this.resource=resource;
        inflater=(LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }
    public View getView(final int position, View convertView, final ViewGroup parent) {

        Context context = parent.getContext();
        final FragmentManager fm = ((Activity) context).getFragmentManager();

        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JF-Flat-regular.ttf");



        ViewHolder holder = null;

        if (convertView == null) {


            convertView = inflater.inflate(resource, null);

            holder = new ViewHolder(convertView);
            //because we pass R.layout.rowlist in argu " int resource " we can reference to it directly

            holder.share = (ImageButton) convertView.findViewById(R.id.share);
            holder.favoriteonenews = (ImageButton) convertView.findViewById(R.id.favoriteonenews);
            holder.back = (ImageButton) convertView.findViewById(R.id.back);
            holder.cover = (ImageView) convertView.findViewById(R.id.imageView26);
            holder.back = (ImageButton) convertView.findViewById(R.id.back);
            holder.category = (TextView) convertView.findViewById(R.id.textView47);
            holder.title = (TextView) convertView.findViewById(R.id.textView48);
            holder.created_at = (TextView) convertView.findViewById(R.id.textView45);
            holder.views_no = (TextView) convertView.findViewById(R.id.textView46);
            holder.writer = (TextView) convertView.findViewById(R.id.textView50);
            holder.description = (TextView) convertView.findViewById(R.id.textView51);



            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(Url_language.getImageUrl()+
                itemsModeList.get(position).getImage(), holder.cover); // Default options will be used

        holder.category.setText(itemsModeList.get(position).getCategory());
        holder.title.setText(itemsModeList.get(position).getTitle());
        holder.created_at.setText(itemsModeList.get(position).getCreated_at());
        holder.views_no.setText(String.valueOf(itemsModeList.get(position).getViews_no()));
        holder.writer.setText(itemsModeList.get(position).getWriter());
        holder.description.setText(itemsModeList.get(position).getDescription());
        holder.category.setTypeface(typeFace);
        holder.title.setTypeface(typeFace);
        holder.created_at.setTypeface(typeFace);
        holder.views_no.setTypeface(typeFace);
        holder.description.setTypeface(typeFace);
        holder.writer.setTypeface(typeFace);

////////////////////////////////////////////////////////////////////To Set the Favourite image ful or not


        //Creating a shared preference
        SharedPreferences mPrefs = getContext().getSharedPreferences("MYPREFS", MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();

        List<Catagories> itemsList = new ArrayList<>();
        String json = mPrefs.getString("MyObject","[]");


        // Now convert the JSON string back to your java object
        Type type = new TypeToken<List<Catagories>>(){}.getType();
        List<Catagories> inpList = new Gson().fromJson(json, type);

        for (int i=0;i<inpList.size();i++) {
            Catagories x = inpList.get(i);
            itemsList.add(x);
        }


        // / how to show the list and what item have been added to Favoirt and which not to set the picture of favourit

        boolean xbol = false;

        for (int i=0;i<inpList.size();i++) {
            Catagories x = inpList.get(i);
            if(x.getId() == itemsModeList.get(position).getId()){
                xbol=true;
            }
        }


        if (xbol==false){


            holder.favoriteonenews.setImageResource(R.mipmap.emptyfav);

        }else{
            holder.favoriteonenews.setImageResource(R.mipmap.fullfav);
        }



        // Convert the object to a JSON string
        String json2 = new Gson().toJson(itemsList);
        //  System.out.println(json2);



        prefsEditor.putString("MyObject", json2);
        prefsEditor.commit();




        ///////////////////////////////////////////////////////////
        holder.favoriteonenews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating a shared preference
                SharedPreferences mPrefs = getContext().getSharedPreferences("MYPREFS", MODE_PRIVATE);

                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();

                List<Catagories> itemsList = new ArrayList<>();
                String json = mPrefs.getString("MyObject","[]");


                // Now convert the JSON string back to your java object
                Type type = new TypeToken<List<Catagories>>(){}.getType();
                List<Catagories> inpList = new Gson().fromJson(json, type);

                for (int i=0;i<inpList.size();i++) {
                    Catagories x = inpList.get(i);
                    itemsList.add(x);
                }
                // / AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA


                boolean xbol = false;

                for (int i=0;i<inpList.size();i++) {
                    Catagories x = inpList.get(i);
                    if(x.getId() == itemsModeList.get(position).getId()){
                        xbol=true;
                        itemsList.remove(x);
                        notifyDataSetChanged();

                       /* Catagories CatObject = (Catagories) getItem(position);
                        String questionId= String.valueOf(CatObject.getId());
                        OneNewsFragment oneNewsFragment = new OneNewsFragment();
                        FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.container, oneNewsFragment );;
                        transaction.addToBackStack("cat");
                        Bundle bundle=new Bundle();
                        bundle.putString("key1",questionId);
                        oneNewsFragment.setArguments(bundle);
                        transaction.commit();
*/



                    }
                }


                if (xbol==false){

                    itemsList.add(itemsModeList.get(position));
                    notifyDataSetChanged();

                   /* Catagories CatObject = (Catagories) getItem(position);
                    String questionId= String.valueOf(CatObject.getId());
                    OneNewsFragment oneNewsFragment = new OneNewsFragment();
                    FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, oneNewsFragment );;
                    transaction.addToBackStack("cat");
                    Bundle bundle=new Bundle();
                    bundle.putString("key1",questionId);
                    oneNewsFragment.setArguments(bundle);
                    transaction.commit();*/
                }
                // Convert the object to a JSON string
                String json2 = new Gson().toJson(itemsList);



                prefsEditor.putString("MyObject", json2);
                prefsEditor.commit();

                /////////////////////////////////////////////////////////////////////////////////////
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                // Add data to the intent, the receiving app will decide
                // what to do with it.
                String title=itemsModeList.get(position).getTitle();

                share.putExtra(Intent.EXTRA_SUBJECT, title);

                String idq = String.valueOf(itemsModeList.get(position).getId());
                String url = "http://195.154.226.224/~aldameer/api/v1/one-news/"+idq+"/en";

                share.putExtra(Intent.EXTRA_TEXT, url);

                getContext().startActivity(Intent.createChooser(share, "Share link!"));

            }
        });

        holder.back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //  Toast.makeText(getContext(),questionId,Toast.LENGTH_LONG).show();


 /*               HomeFragment oneNewsFragment = new HomeFragment();
                FragmentTransaction transaction = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, oneNewsFragment );;
                transaction.addToBackStack(null);
                transaction.commit();
*/
                android.support.v4.app.FragmentManager fragmentManager =  ((FragmentActivity) getContext()).getSupportFragmentManager();
                fragmentManager.popBackStack();




            }
        });






        return convertView;
    }



    class ViewHolder {

        private ImageView cover;
        private ImageButton back;
        private ImageButton share;
        private ImageButton favoriteonenews;



        //     private ImageButton floatingActionButton;
        private TextView id ;
        private TextView  title ;
        private TextView  description ;
        private TextView  cat_id ;
        private TextView  lang ;
        //vedio : null
      //  private TextView  image;
        private TextView  is_active;
        private TextView  views_no ;
        private TextView  writer ;
        private TextView  deleted_at ;
        private TextView  created_at ;
        private TextView  updated_at;
        private TextView  category ;


        //the buttons and action will show after swipe
        public ViewHolder(View v) {


        }

    }


}