package com.agonaika.agonaika;


public class DataAdapter
{
    public String ImageURL;
    public String ImageTitle;
    public String ImageTitle2;

    public String getImageUrl() {

        return ImageURL;
    }

    public void setImageUrl(String imageServerUrl) {

        this.ImageURL = imageServerUrl;
    }

    public String getImageTitle() {

        return ImageTitle;
    }

    public String getImageTitle2() {

        return ImageTitle2;
    }

    public void setImageTitle(String Imagetitlename) {

        this.ImageTitle = Imagetitlename;
    }

    public void setImageTitle2(String Imagetitlename) {

        this.ImageTitle2 = Imagetitlename;
    }

}
