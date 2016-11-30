package net.is.ps.addameer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by PC on 30/10/2016.
 */

public class ListAdapterExample extends BaseAdapter {

    ArrayList<Products> mydata;

    Context context;
    private Typeface typeFace;


    ListAdapterExample(Context context, Typeface typeFace){
        this.context=context;
        this.typeFace=typeFace;
        mydata=new ArrayList<Products>();

        String txt1 = context.getString(R.string.txt1);
        String txt2 = context.getString(R.string.txt2);
        String txt3 = context.getString(R.string.txt3);
        String txt4 = context.getString(R.string.txt4);
        String txt5 = context.getString(R.string.txt5);
        String txt6 = context.getString(R.string.txt6);
        String txt7 = context.getString(R.string.txt7);
        String txt8 = context.getString(R.string.txt8);
        String txt9 = context.getString(R.string.txt9);
        String txt10 = context.getString(R.string.txt10);



        mydata.add(new Products(txt1,R.drawable.path,R.drawable.oval));
        mydata.add(new Products(txt2,R.drawable.path,R.drawable.oval));
        mydata.add(new Products(txt3,R.drawable.path,R.drawable.oval));
        mydata.add(new Products(txt4,R.drawable.path,R.drawable.oval));
        mydata.add(new Products(txt5,R.drawable.path,R.drawable.oval));
        mydata.add(new Products(txt6,R.drawable.path,R.drawable.oval));
        mydata.add(new Products(txt7,R.drawable.path,R.drawable.oval));
        mydata.add(new Products(txt8,R.drawable.path,R.drawable.oval));
        mydata.add(new Products(txt9,R.drawable.path,R.drawable.oval));
        mydata.add(new Products(txt10,R.drawable.path,R.drawable.oval));



    }

    @Override
    public int getCount() {
        return mydata.size();
    }

    @Override
    public Object getItem(int i) {
        return mydata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // LayoutInflater inflater =  getLayoutInflater();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listrow,viewGroup,false);

        TextView title = (TextView)row.findViewById(R.id.textView);
        title.setTypeface(typeFace);

        //   ImageView imageView1 = (ImageView)row.findViewById(R.id.imageView);
     //   ImageView imageView2 = (ImageView)row.findViewById(R.id.imageView2);
        Products temp = mydata.get(i);

        title.setText(temp.name);
    //    imageView1.setImageResource(temp.img_pdf);
    //    imageView2.setImageResource(temp.img_arrow);


        return row;




    }
}

























