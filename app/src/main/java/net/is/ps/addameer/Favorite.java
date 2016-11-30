package net.is.ps.addameer;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.swipe.SwipeLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static net.is.ps.addameer.R.layout.rowlist_favorite;


public class Favorite extends Fragment implements AdapterView.OnItemClickListener{

    ListView listView;

    private SwipeLayout swipeLayout;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.favorite_fragment, container, false);

        listView = (ListView) rootView.findViewById(R.id.lst1);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config); // Do it on Application start


        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JF-Flat-regular.ttf");

        SharedPreferences mPrefs = getContext().getSharedPreferences("MYPREFS", MODE_PRIVATE);

        String json = mPrefs.getString("MyObject","[]");
        Log.d("tag",json);

        List<Catagories> itemsList = new ArrayList<>();



        // Now convert the JSON string back to your java object
        Type type = new TypeToken<List<Catagories>>(){}.getType();
        List<Catagories> inpList = new Gson().fromJson(json, type);
        for (int i=0;i<inpList.size();i++) {
            Catagories x = inpList.get(i);
            itemsList.add(x);
        }







        //without FAVORIT   CatagoriesFragmentAdapter adapter = new CatagoriesFragmentAdapter(getContext(), trylistrowforcatagories,itemsList,typeFace);
        favoirtAdapter adapter = new favoirtAdapter(getContext(), rowlist_favorite,itemsList,typeFace);

        listView.setAdapter(adapter);




        return rootView;
    }



    public void onItemClick(AdapterView<?>arg0, View arg1 , int arg2, long arg3){
//        Toast.makeText(getContext(),"hello",Toast.LENGTH_LONG).show();


    }



}











