package net.is.ps.addameer;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Administrator on 11/7/2016.
 */

public class  MainCatagoriesFragmentAdapter extends ArrayAdapter {
    private Typeface typeFace;
    private List<MainCatagoriesItems> itemsModeList;
    private int resource;
    private LayoutInflater inflater;




    public MainCatagoriesFragmentAdapter(Context context, int resource, List<MainCatagoriesItems> objects, Typeface typeFace) {
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

            holder.name = (TextView) convertView.findViewById(R.id.textView44);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }


        holder.name.setText(itemsModeList.get(position).getName());
        holder.name.setTypeface(typeFace);




        return convertView;
    }



    class ViewHolder {

        private ImageView cover;
        //     private ImageButton floatingActionButton;
        private TextView catagory_name;
        private TextView title;
        private TextView createdat;

        private TextView name;

        //  private TextView views;
        //  private TextView writer;
        //  private TextView desc;

        private View btnDelete;

        //the buttons and action will show after swipe
        public ViewHolder(View v) {


        }
    }

}












