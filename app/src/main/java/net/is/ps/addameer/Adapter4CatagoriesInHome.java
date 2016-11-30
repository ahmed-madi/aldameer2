package net.is.ps.addameer;

import android.content.Context;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class  Adapter4CatagoriesInHome extends ArrayAdapter {
    private List<Catagories> itemsModeList;
    private int resource;
    private Typeface typeFace;
    private LayoutInflater inflater;


    public Adapter4CatagoriesInHome(Context context, int resource, List<Catagories> objects, Typeface typeFace) {
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
        ImageLoader.getInstance().displayImage("http://195.154.226.224/~aldameer/uploads/news/"+
                itemsModeList.get(position).getImage(), holder.cover); // Default options will be used


      //  holder.btnDelete.setOnClickListener(onDeleteListener(position, holder));
        //holder.linearswap2.setOnClickListener(onClickItem2(position, holder));



        // holder.cover.setOnClickListener(listblistneree(position,holder));
        return convertView;
    }
    private View.OnClickListener onDeleteListener(final int position, final ViewHolder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   Toast.makeText(getContext(), "this is my Toast message!!! =)", Toast.LENGTH_LONG).show();


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

          //      Toast.makeText(getContext(), "when i clicked the items! =)", Toast.LENGTH_LONG).show();


//                friends.remove(position);
                //  holder.swipeLayout.close();
//                activity.updateAdapter();
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
        //  private TextView views;
        //  private TextView writer;
        //  private TextView desc;

       // private View btnDelete;
        // private SwipeLayout swipeLayout;

        //the buttons and action will show after swipe
        public ViewHolder(View v) {
         /*   SwipeLayout swipeLayout = (SwipeLayout) v.findViewById(R.id.swipe_layout);
            btnDelete = v.findViewById(R.id.delete);
            linearswap2 =v.findViewById(R.id.linearswap);*/

           // View starBottView = swipeLayout.findViewById(R.id.bottom_wrapper);

            // swipeLayout.addDrag(SwipeLayout.DragEdge.Left, starBottView);


            //      swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

            //    swipeLayout.addDrag(SwipeLayout.DragEdge.Left, v.findViewById(R.id.bottom_wrapper));
//


        }
    }

}












