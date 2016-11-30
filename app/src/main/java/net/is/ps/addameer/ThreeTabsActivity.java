package net.is.ps.addameer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import net.is.ps.addameer.BottomBarLib.BottomBar;
import net.is.ps.addameer.BottomBarLib.OnTabReselectListener;
import net.is.ps.addameer.BottomBarLib.OnTabSelectListener;


/**
 * Created by iiro on 7.6.2016.
 */
public class ThreeTabsActivity extends AppCompatActivity {
    private TextView messageView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_three_tabs);

        //For get Notifications from server
        startService(new Intent(this, RegistrationService.class));


        //    messageView = (TextView) findViewById(R.id.messageView);


        //Toool BAR DEFINEATION HERE
        //TWO BUTTONS 1.About The application 2.Favourite


        toolbar=(Toolbar)findViewById(R.id.toolbar);

        //toolbar.setBackgroundColor(Color.RED);

        ImageButton about=(ImageButton)findViewById(R.id.btn1);
        ImageButton favourit=(ImageButton)findViewById(R.id.btn2) ;



        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppAbout appAbout = new AppAbout();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, appAbout);
                transaction.addToBackStack("about");

                transaction.commit();


                //Static Data For ABOUT APP
//                ToolBarButtonabout toolBarButtonabout = new ToolBarButtonabout();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container, toolBarButtonabout);
//                transaction.commit();
            }
        });

        favourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Favorite appAbout = new Favorite();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, appAbout);
                transaction.addToBackStack("fav");
                transaction.commit();

            }
        });

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                 if(TabMessage.get(tabId, false).equalsIgnoreCase("news")) {

                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, homeFragment);
                     transaction.commit();


                }else if(TabMessage.get(tabId, false).equalsIgnoreCase("call")){

                    ContactsFragment contactsFragment = new ContactsFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, contactsFragment);


                   transaction.commit();


                }else if(TabMessage.get(tabId, false).equalsIgnoreCase("about")) {

                    AboutFragment aboutFragment = new AboutFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, aboutFragment);

                    Bundle bundle=new Bundle();

                    bundle.putString("key1","ahmad");
                    aboutFragment.setArguments(bundle);


                    transaction.commit();

                }else if(TabMessage.get(tabId, false).equalsIgnoreCase("report")) {

                    ReportFragment reportFragment = new ReportFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, reportFragment);

                    transaction.commit();

                }else if(TabMessage.get(tabId, false).equalsIgnoreCase("categories")) {

                    MainCatagoriesFragment mainCatagoriesFragment = new MainCatagoriesFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container,mainCatagoriesFragment);
                    transaction.commit();


                }
            }

        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if(TabMessage.get(tabId, false).equalsIgnoreCase("news")) {

                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, homeFragment);
                    transaction.commit();


                }else if(TabMessage.get(tabId, false).equalsIgnoreCase("call")){

                    ContactsFragment contactsFragment = new ContactsFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, contactsFragment);


                    transaction.commit();


                }else if(TabMessage.get(tabId, false).equalsIgnoreCase("about")) {

                    AboutFragment aboutFragment = new AboutFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, aboutFragment);

                    Bundle bundle=new Bundle();

                    bundle.putString("key1","ahmad");
                    aboutFragment.setArguments(bundle);


                    transaction.commit();

                }else if(TabMessage.get(tabId, false).equalsIgnoreCase("report")) {

                    ReportFragment reportFragment = new ReportFragment();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, reportFragment);

                    transaction.commit();

                }else if(TabMessage.get(tabId, false).equalsIgnoreCase("categories")) {

                    MainCatagoriesFragment mainCatagoriesFragment = new MainCatagoriesFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container,mainCatagoriesFragment);
                    transaction.commit();


                }
            }
        });
    }


}