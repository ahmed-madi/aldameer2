package net.is.ps.addameer;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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


import static net.is.ps.addameer.R.layout.listrow_catagories;


public class cat2 extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private SwipeLayout swipeLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_catagories, container, false);

        new JSONTask().execute("http://195.154.226.224/~aldameer/api/v1/category/en");
        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        final DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(defaultOptions).build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        listView = (ListView) rootView.findViewById(R.id.lst1);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch (position) {

                    case 0:

                        Toast.makeText(getContext(),String.valueOf(position),Toast.LENGTH_LONG).show();

                        break;

                    case 1:
                        Toast.makeText(getContext(),String.valueOf(position),Toast.LENGTH_LONG).show();
//
//                        fragmentTransaction.replace(R.id.container, catagoriesFragment);
//                        fragmentTransaction.commit();
                        break;
                    default:
                        Toast.makeText(getContext(),String.valueOf(position),Toast.LENGTH_LONG).show();
//
//                        fragmentTransaction.replace(R.id.container, catagoriesFragment);
//                        fragmentTransaction.commit();
                        break;
                }

            }
        });
        return rootView;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public void onItemClick(AdapterView<?>arg0, View arg1 , int arg2, long arg3){


    }

    public class JSONTask extends AsyncTask<String, String, List<Catagories>>  {

        @Override
        protected List<Catagories> doInBackground(String... params) {
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
                JSONArray parentArray =parentObject.getJSONArray("items");

                List<Catagories> itemsList= new ArrayList<>();

                Gson gson = new Gson();
                for(int i=0;i<parentArray.length();i++){

                    JSONObject finalObject = parentArray.getJSONObject(i);
                    Catagories item = gson.fromJson(finalObject.toString(),Catagories.class);

                   /* Items items = new Items();
                    items.setId(finalObject.getInt("id"));
                    items.setTitle(finalObject.getString("title"));
                    items.setDescription(finalObject.getString("description"));
                    items.setCat_id(finalObject.getString("cat_id"));
                    items.setLang(finalObject.getString("lang"));
                    items.setVedio(finalObject.getString("vedio"));
                    items.setImage(finalObject.getString("image"));
                    items.setIs_active(finalObject.getString("is_active"));
                    items.setViews_no(finalObject.getString("views_no"));
                    items.setWriter(finalObject.getString("writer"));
                    items.setDeleted_at(finalObject.getString("deleted_at"));
                    items.setCreated_at(finalObject.getString("created_at"));
                    items.setUpdated_at(finalObject.getString("updated_at"));
                    items.setCategory(finalObject.getString("category"));

*/                  //  itemsList.add(items);

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

        public void onPostExecute(List<Catagories> result) {
            super.onPostExecute(result);

            //set data to list view or something
            Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JF-Flat-regular.ttf");

            CatagoriesFragmentAdapter adapter = new CatagoriesFragmentAdapter(getContext(),listrow_catagories,result, typeFace);
            listView.setAdapter(adapter);



        }

    }

}
