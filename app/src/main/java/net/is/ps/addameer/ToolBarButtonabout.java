package net.is.ps.addameer;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolBarButtonabout extends Fragment {


    public ToolBarButtonabout() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tool_bar_buttonabout, container, false);

        ScrollView scrollView=(ScrollView) view.findViewById(R.id.AboutScrol);


        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/JF-Flat-regular.ttf");

        TextView textView=(TextView)view.findViewById(R.id.textView3);
        textView.setTypeface(typeFace);

        TextView textView4=(TextView)view.findViewById(R.id.textView4);
        textView4.setTypeface(typeFace);

        TextView textView5=(TextView)view.findViewById(R.id.textView5);
        textView5.setTypeface(typeFace);

        TextView textView6=(TextView)view.findViewById(R.id.textView6);
        textView6.setTypeface(typeFace);
        textView6.setText("الهدف الاستراتيجي :");

        TextView textView7=(TextView)view.findViewById(R.id.textView7);
        textView7.setTypeface(typeFace);

        TextView textView8 =(TextView)view.findViewById(R.id.textView8);
        textView8.setTypeface(typeFace);

        TextView textView9 =(TextView)view.findViewById(R.id.textView9);
        textView9.setTypeface(typeFace);

        TextView textView10 =(TextView)view.findViewById(R.id.textView10);
        textView10.setTypeface(typeFace);
        TextView textView11 =(TextView)view.findViewById(R.id.textView11);
        textView11.setTypeface(typeFace);

        TextView textView12 =(TextView)view.findViewById(R.id.textView12);
        textView12.setTypeface(typeFace);


        return view;
    }

}
