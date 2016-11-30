package net.is.ps.addameer;

/**
 * Created by Administrator on 11/11/2016.
 */
public class Vote {


    int id;
    String option;
    int option_percent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getOption_percent() {
        return option_percent;
    }

    public void setOption_percent(int option_percent) {
        this.option_percent = option_percent;
    }
}
