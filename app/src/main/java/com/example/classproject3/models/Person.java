package com.example.classproject3.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Person implements Parcelable {

    @Nullable
    String id , name , address , gender;
    @Nullable
    ArrayList<String> songPlaylist;
    @Nullable
    ArrayList<String> artistPlaylist;

    @Nullable
    Phone phoneList;


    protected Person(Parcel in) {
        id = in.readString();
        name = in.readString();
        address = in.readString();
        gender = in.readString();
        songPlaylist = in.createStringArrayList();
        artistPlaylist = in.createStringArrayList();
        phoneList = in.readParcelable(Phone.class.getClassLoader());
    }


    private Person(){

    }

    public Person(Phone phoneList , String id , String name , String address , String gender , ArrayList<String> songPlaylist ,  ArrayList<String> artistPlaylist){
       this.phoneList = phoneList ; this.id = id ; this.name = name ; this.address = address ; this.gender = gender;  this.songPlaylist = songPlaylist ; this.artistPlaylist = artistPlaylist;
    }

    @Nullable
    public Phone getPhoneList() {
        return phoneList;
    }

    @Nullable
    public void setPhoneList(@Nullable Phone phoneList) {
        this.phoneList = phoneList;
    }

    @Nullable
    public String getId() {
        return id;
    }
    @Nullable
    public void setId(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }
    @Nullable
    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getAddress() {
        return address;
    }
    @Nullable
    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    @Nullable
    public String getGender() {
        return gender;
    }

    @Nullable
    public void setGender(@Nullable String gender) {
        this.gender = gender;
    }

    @Nullable
    public ArrayList<String> getSongPlaylist() {
        return songPlaylist;
    }
    @Nullable
    public void setSongPlaylist(@Nullable ArrayList<String> songPlaylist) {
        this.songPlaylist = songPlaylist;
    }

    @Nullable
    public ArrayList<String> getArtistPlaylist() {
        return artistPlaylist;
    }
    @Nullable
    public void setArtistPlaylist(@Nullable ArrayList<String> artistPlaylist) {
        this.artistPlaylist = artistPlaylist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(gender);
        parcel.writeStringList(songPlaylist);
        parcel.writeStringList(artistPlaylist);
        parcel.writeParcelable(phoneList, i);
    }


    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };


    public static class Builder {
        String id, name , gender , address;
        ArrayList<String> songPlaylist;
        ArrayList<String> artistList;
        Phone phoneList;

       public Person.Builder withId(String id){
            this.id = id;

            return this;
        }

        public Person.Builder withName(String name){
            this.name = name;
            return this;
        }

        public Person.Builder withAddress(String address){
            this.address = address;
            return this;

        }
        public Person.Builder withGender(String gender){
            this.gender = gender;
            return this;
        }

        public Person.Builder withSongPlaylist(ArrayList<String> songPlaylist){
            this.songPlaylist = songPlaylist;
            return this;
        }

        public Person.Builder withArtistPlaylist(ArrayList<String> artistPlaylist){
            this.artistList = artistPlaylist;
            return this;
        }

        public Person.Builder withPhoneList(Phone phoneList){
            this.phoneList = phoneList;
            return this;
        }


       public Person build(){
            Person person = new Person();
            person.id = id;
            person.name = name;
            person.gender = gender;
            person.address = address;
            person.artistPlaylist = artistList;
            person.songPlaylist = songPlaylist;
            person.phoneList = phoneList;
            return person;
        }
    }
}
