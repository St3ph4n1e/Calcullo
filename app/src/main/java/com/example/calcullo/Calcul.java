package com.example.calcullo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Calcul implements Parcelable {
    public String num1;
    public String num2;
    public String operation;
    public String result;
    public List<Integer> answers;

    public Calcul(int num1, int num2, String operation, int result, List<Integer> answers) {
        this.num1 = String.valueOf(num1);
        this.num2 = String.valueOf(num2);
        this.operation = operation;
        this.result = String.valueOf(result);
        this.answers = answers;
    }

    protected Calcul(Parcel in) {
        num1 = in.readString();
        num2 = in.readString();
        operation = in.readString();
        result = in.readString();
        answers = in.readArrayList(Integer.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(num1);
        dest.writeString(num2);
        dest.writeString(operation);
        dest.writeString(result);
        dest.writeList(answers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Calcul> CREATOR = new Creator<Calcul>() {
        @Override
        public Calcul createFromParcel(Parcel in) {
            return new Calcul(in);
        }

        @Override
        public Calcul[] newArray(int size) {
            return new Calcul[size];
        }
    };

    public String getQuestion() {
        return num1 + " " + operation + " " + num2 + " = ?";
    }

    public boolean isCorrect(String answer) {
        return answer.equals(result);
    }
}
