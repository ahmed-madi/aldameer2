package net.is.ps.addameer;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import net.is.ps.addameer.BottomBarLib.BottomBar;
import net.is.ps.addameer.BottomBarLib.BottomBarTab;
import net.is.ps.addameer.BottomBarLib.OnTabReselectListener;
import net.is.ps.addameer.BottomBarLib.OnTabSelectListener;


/**
 * Created by iiro on 7.6.2016.
 */
public class BadgeActivity extends AppCompatActivity {
    private TextView messageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_tabs);

        messageView = (TextView) findViewById(R.id.messageView);

        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                messageView.setText(TabMessage.get(tabId, false));
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });

        BottomBarTab nearby = bottomBar.getTabWithId(R.id.tab_nearby);
        nearby.setBadgeCount(5);
    }
}
