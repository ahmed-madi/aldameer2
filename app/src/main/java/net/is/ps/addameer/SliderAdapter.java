package net.is.ps.addameer;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 11/9/2016.
 */
public class SliderAdapter extends PagerAdapter {
    private Context ctx;
    public List<Catagories> itemsModeList;


    public SliderAdapter(Context ctx, List<Catagories> itemsModeList) {
        this.ctx = ctx;
        this.itemsModeList = itemsModeList;
    }


    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    private LayoutInflater inflater;


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.swipe,container,false);


        ImageView cover = (ImageView) v.findViewById(R.id.cover);
        ImageView dot1 = (ImageView) v.findViewById(R.id.dot1);
        ImageView dot2 = (ImageView) v.findViewById(R.id.dot2);
        ImageView dot3 = (ImageView) v.findViewById(R.id.dot3);
        ImageView dot4 = (ImageView) v.findViewById(R.id.dot4);
        TextView catagory_name = (TextView) v.findViewById(R.id.catagory_name);
        TextView title = (TextView) v.findViewById(R.id.title);
        TextView createdat = (TextView) v.findViewById(R.id.createdat);
     //   TextView tv = (TextView) v.findViewById(R.id.counterTV);

       // String URL = "http://195.154.226.224/~aldameer/uploads/news/";
        String URL = Url_language.getImageUrl();

        Typeface typeFace = Typeface.createFromAsset(ctx.getAssets(), "fonts/JF-Flat-regular.ttf");


        // img.setImageResource(imgs[position]);
        if(position==0){
            catagory_name.setText(itemsModeList.get(position).getCategory());
            catagory_name.setTypeface(typeFace);


            title.setText(itemsModeList.get(position).getTitle());
            title.setTypeface(typeFace);

            createdat.setText(getDate2(position));
            createdat.setTypeface(typeFace);

            // createdat.setText(itemsModeList.get(position).getCreated_at());
            Picasso.with(ctx).load(URL+itemsModeList.get(position).getImage()).into(cover);
          //  tv.setText(String.valueOf(position));
            dot1.setImageResource(R.drawable.ic_red_dot);

          /*  v.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    //this will log the page number that was click
                    //Log.i("TAG", "This page was clicked: " + position);
                    HomeFragment dd = new HomeFragment();

                    dd.setCounterFro(0);


                }
            });*/

      /*      v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    HomeFragment dd = new HomeFragment();

                    dd.setCounterFro(0);
                    return false;
                }
            });*/

        }else if(position==1){
            catagory_name.setText(itemsModeList.get(position).getCategory());
            title.setText(itemsModeList.get(position).getTitle());
            createdat.setText(getDate2(position));
            //  createdat.setText(itemsModeList.get(position).getCreated_at());
            Picasso.with(ctx).load(URL+itemsModeList.get(position).getImage()).into(cover);
            dot2.setImageResource(R.drawable.ic_red_dot);

        //    tv.setText(String.valueOf(position));

/*
            v.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    //this will log the page number that was click
                    Log.i("TAG", "This page was clicked: " + position);


                   *//* HomeFragment dd = new HomeFragment();

                    dd.setCounterFro(1);

*//*

                }
            });*/
/*

            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    HomeFragment dd = new HomeFragment();

                    dd.setCounterFro(1);
                    return false;
                }
            });
*/



        }else if (position==2){
            catagory_name.setText(itemsModeList.get(position).getCategory());
            title.setText(itemsModeList.get(position).getTitle());
      //      createdat.setText(itemsModeList.get(position).getCreated_at());
            createdat.setText(getDate2(position));

            Picasso.with(ctx).load(URL+itemsModeList.get(position).getImage()).into(cover);
          //  tv.setText(String.valueOf(position));
            dot3.setImageResource(R.drawable.ic_red_dot);


        /*    v.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    //this will log the page number that was click
                    //Log.i("TAG", "This page was clicked: " + position);
                    HomeFragment dd = new HomeFragment();

                    dd.setCounterFro(2);


                }
            });
*/
        }
        else if (position==3){
            catagory_name.setText(itemsModeList.get(position).getCategory());
            title.setText(itemsModeList.get(position).getTitle());
            createdat.setText(getDate2(position));
//            createdat.setText(itemsModeList.get(position).getCreated_at());
            Picasso.with(ctx).load(URL+itemsModeList.get(position).getImage()).into(cover);
         //   tv.setText(String.valueOf(position));
            dot4.setImageResource(R.drawable.ic_red_dot);


/*

            v.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    //this will log the page number that was click
                    //Log.i("TAG", "This page was clicked: " + position);
                    HomeFragment dd = new HomeFragment();

                    dd.setCounterFro(3);


                }
            });
*/


        }

        container.addView(v);



        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.invalidate();

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

}
