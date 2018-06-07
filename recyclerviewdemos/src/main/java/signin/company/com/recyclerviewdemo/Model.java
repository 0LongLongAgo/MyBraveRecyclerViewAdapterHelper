package signin.company.com.recyclerviewdemo;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by liuqun on 2017/6/1.
 */

public class Model implements Parcelable {
    @JsonSerialize
    String username;

    public Model(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
    }

    protected Model(Parcel in) {
        this.username = in.readString();
    }

    public static final Parcelable.Creator<Model> CREATOR = new Parcelable.Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel source) {
            return new Model(source);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    @Override
    public String toString() {
        return "Model{" +
                "username='" + username + '\'' +
                '}';
    }
}
