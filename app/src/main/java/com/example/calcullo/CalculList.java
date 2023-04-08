package com.example.calcullo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CalculList implements Parcelable {
    private List<Calcul> calculs;
    private int currentCalculIndex;

    public CalculList() {
        calculs = new ArrayList<>();
    }

    public void add(Calcul calcul) {
        calculs.add(calcul);
    }

    public int size() {
        return calculs.size();
    }

    protected CalculList(Parcel in) {
        calculs = in.createTypedArrayList(Calcul.CREATOR);
        currentCalculIndex = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(calculs);
        dest.writeInt(currentCalculIndex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<CalculList> CREATOR = new Creator<CalculList>() {
        @Override
        public CalculList createFromParcel(Parcel in) {
            return new CalculList(in);
        }

        @Override
        public CalculList[] newArray(int size) {
            return new CalculList[size];
        }
    };

    public Calcul getNextCalcul() {

       if (currentCalculIndex >= calculs.size()) {
          return null;
       }

        Calcul nextCalcul = calculs.get(currentCalculIndex);
        currentCalculIndex++;

        return nextCalcul;
    }


    public Calcul getPreviousCalcul() {
        if (currentCalculIndex <= 0) {
            return null;
        }

        currentCalculIndex--;
        Calcul previousCalcul = calculs.get(currentCalculIndex-1);

        return previousCalcul;
    }

}
