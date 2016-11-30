package net.is.ps.addameer;

/**
 * Created by PC on 30/10/2016.
 */



public class Products {
    String name;
    int img_pdf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg_pdf() {
        return img_pdf;
    }

    public void setImg_pdf(int img_pdf) {
        this.img_pdf = img_pdf;
    }

    public int getImg_arrow() {
        return img_arrow;
    }

    public void setImg_arrow(int img_arrow) {
        this.img_arrow = img_arrow;
    }

    int img_arrow;

    public Products(String name,int img_arrow,int img_pdf){
        this.name=name;
        this.img_arrow=img_arrow;
        this.img_pdf=img_pdf;
    }
}
