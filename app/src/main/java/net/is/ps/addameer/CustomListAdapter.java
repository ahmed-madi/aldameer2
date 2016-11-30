package net.is.ps.addameer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by Administrator on 11/9/2016.
 */
public class CustomListAdapter extends ArrayAdapter {
    private List<Catagories> itemsModeList;
    private int resource;
    private LayoutInflater inflater;


    public CustomListAdapter(Context context, int resource, List<Catagories> objects) {
        super(context, resource, objects);
        itemsModeList=objects;
        this.resource=resource;
        inflater=(LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {

            convertView = inflater.inflate(resource, null);
            holder = new ViewHolder(convertView);
            //because we pass R.layout.rowlist in argu " int resource " we can reference to it directly
            holder.cover = (ImageView) convertView.findViewById(R.id.cover);
            //  holder.floatingActionButton = (ImageButton) convertView.findViewById(R.id.floatingActionButton);
            holder.catagory_name = (TextView) convertView.findViewById(R.id.catagory_name);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.createdat = (TextView) convertView.findViewById(R.id.createdat);
//            holder.views = (TextView) convertView.findViewById(R.id.views);
//            holder.writer = (TextView) convertView.findViewById(R.id.writer);
//            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.catagory_name.setText(itemsModeList.get(position).getCategory());
        holder.title.setText(itemsModeList.get(position).getTitle());
        holder.createdat.setText(itemsModeList.get(position).getCreated_at());
        // holder.views.setText(itemsModeList.get(position).getViews_no());
        //holder.writer.setText(itemsModeList.get(position).getWriter());
        //holder.desc.setText(itemsModeList.get(position).getDescription());
        // Then later, when you want to display image
        ImageLoader.getInstance().displayImage("http://195.154.226.224/~aldameer/uploads/news/"+
                itemsModeList.get(position).getImage(), holder.cover); // Default options will be used


        // holder.btnDelete.setOnClickListener(onDeleteListener(position, holder));


        // holder.cover.setOnClickListener(listblistneree(position,holder));
        return convertView;
    }
    private View.OnClickListener onDeleteListener(final int position, final ViewHolder holder) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "this is my Toast message!!! =)",
                        Toast.LENGTH_LONG).show();


//                friends.remove(position);
                //  holder.swipeLayout.close();
//                activity.updateAdapter();
            }
        };
    }




    class ViewHolder {

        private ImageView cover;
        //     private ImageButton floatingActionButton;
        private TextView catagory_name;
        private TextView title;
        private TextView createdat;

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












