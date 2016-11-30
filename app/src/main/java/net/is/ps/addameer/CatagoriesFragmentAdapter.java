package net.is.ps.addameer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class  CatagoriesFragmentAdapter extends ArrayAdapter {
    private List<Catagories> itemsModeList;
    private int resource;
    private Typeface typeFace;
    private LayoutInflater inflater;


    public CatagoriesFragmentAdapter(Context context, int resource, List<Catagories> objects, Typeface typeFace) {
        super(context, resource, objects);
        itemsModeList=objects;
        this.resource=resource;
        this.typeFace=typeFace;
        inflater=(LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

    }
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;


        if (convertView == null) {

            convertView = inflater.inflate(resource, null);
            holder = new ViewHolder(convertView);
            //because we pass R.layout.rowlist in argu " int resource " we can reference to it directly
            holder.cover = (ImageView) convertView.findViewById(R.id.cover);
            holder.catagory_name = (TextView) convertView.findViewById(R.id.catagory_name);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.createdat = (TextView) convertView.findViewById(R.id.createdat);

            holder.fav_txt = (TextView) convertView.findViewById(R.id.fav_txt);
            holder.fav_img = (ImageView) convertView.findViewById(R.id.delete);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.catagory_name.setText(itemsModeList.get(position).getCategory());
        holder.title.setText(itemsModeList.get(position).getTitle());
        holder.createdat.setText(getDate2(position));
        holder.catagory_name.setTypeface(typeFace);
        holder.title.setTypeface(typeFace);
        holder.createdat.setTypeface(typeFace);


        // Then later, when you want to display image
        ImageLoader.getInstance().displayImage(Url_language.getImageUrl()+
                itemsModeList.get(position).getImage(), holder.cover); // Default options will be used




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


                                            holder.fav_txt.setText(getContext().getString(R.string.addfav));
                                            holder.fav_txt.setTypeface(typeFace);
                                            holder.fav_img.setImageResource(R.mipmap.fav2cell2out2line);

                                        }else{
                                            holder.fav_txt.setText(getContext().getString(R.string.delfav));
                                            holder.fav_txt.setTypeface(typeFace);
                                            holder.fav_img.setImageResource(R.mipmap.fav_fill);
                                        }



        // Convert the object to a JSON string
        String json2 = new Gson().toJson(itemsList);
      //  System.out.println(json2);



        prefsEditor.putString("MyObject", json2);
        prefsEditor.commit();






        holder.btnDelete.setOnClickListener(onDeleteListener(position, holder));
        holder.linearswap2.setOnClickListener(onClickItem2(position, holder));




        // holder.cover.setOnClickListener(listblistneree(position,holder));
        return convertView;
    }



    private View.OnClickListener onDeleteListener(final int position, final ViewHolder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Toast.makeText(getContext(), "this is my Toast message!!! =)", Toast.LENGTH_LONG).show();
                //Creating a shared preference

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


                      /*  Catagories CatObject = (Catagories) getItem(position);
                        String questionId= String.valueOf(CatObject.getCat_id());
                        CatagoriesFragment oneNewsFragment = new CatagoriesFragment();
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

                  //  System.out.println(xbol);



                if (xbol==false){

                    itemsList.add(itemsModeList.get(position));
                        notifyDataSetChanged();

/*
                    Catagories CatObject = (Catagories) getItem(position);
                    String questionId= String.valueOf(CatObject.getCat_id());
                    CatagoriesFragment oneNewsFragment = new CatagoriesFragment();
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
         //       System.out.println(json2);



                prefsEditor.putString("MyObject", json2);
                prefsEditor.commit();

                /////////////////////////////////////////////////////////////////////////////////////



            /*    jsonArray.put(itemsModeList.get(position));

                JSONObject studentsObj = new JSONObject();
                try {
                    studentsObj.put("Fav", jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
*/





//                friends.remove(position);
              //  holder.swipeLayout.close();
//                activity.updateAdapter();
            }
        };
    }
    private View.OnClickListener onClickItem2(final int position, final ViewHolder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Catagories CatObject = (Catagories) getItem(position);
                String questionId= String.valueOf(CatObject.getId());

                OneNewsFragment oneNewsFragment = new OneNewsFragment();
                FragmentTransaction transaction = ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, oneNewsFragment );;

                transaction.addToBackStack("cat");

                Bundle bundle=new Bundle();
                bundle.putString("key1",questionId);
                oneNewsFragment.setArguments(bundle);

                transaction.commit();


            }
        };
    }


    public String getDate2 (int position) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String x = itemsModeList.get(position).getCreated_at();
        try {
            date = fmt.parse(x);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy,dd MMMM");
        return fmtOut.format(date);
    }




    class ViewHolder {

        private ImageView cover;
        //     private ImageButton floatingActionButton;
        private TextView catagory_name;
        private TextView title;
        private TextView createdat;
        private View linearswap2;
        private TextView views;
        private TextView fav_txt;
        private ImageView fav_img;

        //  private TextView writer;
        //  private TextView desc;

       private View btnDelete;
       // private SwipeLayout swipeLayout;

        //the buttons and action will show after swipe
        public ViewHolder(View v) {
            SwipeLayout swipeLayout = (SwipeLayout) v.findViewById(R.id.swipe_layout);
             btnDelete = v.findViewById(R.id.delete);
             linearswap2 =(LinearLayout)v.findViewById(R.id.linearswap);


           // View starBottView = swipeLayout.findViewById(R.id.bottom_wrapper);

           // swipeLayout.addDrag(SwipeLayout.DragEdge.Left, starBottView);


      //      swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        //    swipeLayout.addDrag(SwipeLayout.DragEdge.Left, v.findViewById(R.id.bottom_wrapper));
//


        }
    }

}












