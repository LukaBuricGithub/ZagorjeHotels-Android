package hr.tvz.android.lukaburicproject.Main.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Hotel implements Parcelable {
    private String name;
    private String description;
    private String rating;
    private String img;
    private String x;
    private String y;
    private String web;


    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        @Override
        public Hotel createFromParcel(Parcel in) {
            return new Hotel(in);
        }

        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public String getX() { return x; }
    public void setX(String x) { this.x = x; }

    public String getY() { return y; }
    public void setY(String y) { this.y = y; }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Hotel(){}

    public Hotel(Parcel source){
        this.name=source.readString();
        this.description=source.readString();
        this.rating=source.readString();
        this.img=source.readString();
        this.x=source.readString();
        this.y=source.readString();
        this.web=source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeString(this.rating);
        parcel.writeString(this.img);
        parcel.writeString(this.x);
        parcel.writeString(this.y);
        parcel.writeString(this.web);
    }

}
