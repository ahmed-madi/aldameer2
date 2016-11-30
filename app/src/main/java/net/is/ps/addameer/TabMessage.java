package net.is.ps.addameer;

/**
 * Created by iiro on 7.6.2016.
 */
public class TabMessage {
    public static String get(int menuItemId, boolean isReselection) {
        String message = "";

        switch (menuItemId) {
            case R.id.news:
                message += "news";
                break;
            case R.id.tab_call:
                message += "call";
                break;

            case R.id.tab_report:
                message += "report";
                break;
            case R.id.tab_categories:
                message += "categories";
                break;

            case R.id.tab_about:
                message += "About";
                break;
        }


        return message;
    }
}
