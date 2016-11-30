 package net.is.ps.addameer;

 import android.content.Intent;
 import android.graphics.Typeface;
 import android.os.Bundle;
 import android.support.annotation.Nullable;
 import android.support.v4.app.Fragment;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.AdapterView;
 import android.widget.ListView;
 import android.widget.Toast;

 import net.is.ps.addameer.Pdf.PDF10_;
 import net.is.ps.addameer.Pdf.PDF2_;
 import net.is.ps.addameer.Pdf.PDF3_;
 import net.is.ps.addameer.Pdf.PDF4_;
 import net.is.ps.addameer.Pdf.PDF5_;
 import net.is.ps.addameer.Pdf.PDF6_;
 import net.is.ps.addameer.Pdf.PDF7_;
 import net.is.ps.addameer.Pdf.PDF8_;
 import net.is.ps.addameer.Pdf.PDF9_;
 import net.is.ps.addameer.Pdf.PDFViewActivity_;


 public class ReportFragment extends Fragment implements AdapterView.OnItemClickListener{



     @Override
     public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_report, container, false);
         ListView listview = (ListView) rootView.findViewById(R.id.listview1);

         Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/JF-Flat-regular.ttf");

         listview.setAdapter(new ListAdapterExample(getContext(),typeFace));

         listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

/*
                 ListAdapterExample xx = new ListAdapterExample(getContext());
                 Products product = (Products) xx.getItem(position);
                 String bookName= product.getName();*/

                 switch (position) {

                     case 0:
                         Intent intent = new Intent(getActivity(),PDFViewActivity_.class);
                         startActivity(intent);
                    break;

                     case 1:
                         Intent pdf2 = new Intent(getActivity(),PDF2_.class);
                         startActivity(pdf2);

                         break;

                     case 2:
                         Intent intent3 = new Intent(getActivity(),PDF3_.class);
                         startActivity(intent3);

                         break;

                     case 3:
                         Intent intent4 = new Intent(getActivity(),PDF4_.class);
                         startActivity(intent4);
                         break;

                     case 4:
                         Intent intent5 = new Intent(getActivity(),PDF5_.class);
                         startActivity(intent5);

                         break;
                     case 5:
                         Intent intent6 = new Intent(getActivity(),PDF6_.class);
                         startActivity(intent6);

                         break;

                     case 6:
                         Intent intent7 = new Intent(getActivity(),PDF7_.class);
                         startActivity(intent7);

                         break;

                     case 7:
                         Intent intent8 = new Intent(getActivity(),PDF8_.class);
                         startActivity(intent8);

                         break;

                     case 8:
                         Intent intent9 = new Intent(getActivity(),PDF9_.class);
                         startActivity(intent9);

                         break;
                     case 9:
                         Intent intent10 = new Intent(getActivity(),PDF10_.class);
                         startActivity(intent10);

                         break;

                     default:
                         Toast.makeText(getContext(), "ddefaiaolt", Toast.LENGTH_LONG).show();

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




 }
