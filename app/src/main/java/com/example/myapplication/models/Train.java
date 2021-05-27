package com.example.myapplication.models;

public class Train implements Comparable {
    protected String id,name,type,level,equipment,desc;
    protected int time;
    protected double rating;
    protected int numOfWatched;
    protected User user;
    protected String videoRef;

    public Train() {

    }

    public Train(String id, String name, String type, String level, String equipment, String desc, double rating, int numOfWatched, User user, String videoRef, int time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.equipment = equipment;
        this.desc = desc;
        this.rating = rating;
        this.numOfWatched = numOfWatched;
        this.user = user;
        this.videoRef = videoRef;
        this.time=time;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLevel() {
        return level;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getDesc() {
        return desc;
    }

    public double getRating() {
        return rating;
    }

    public int getNumOfWatched() {
        return numOfWatched;
    }

    public User getUser() {
        return user;
    }

    public String getVideoRef() {
        return videoRef;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNumOfWatched(int numOfWatched) {
        this.numOfWatched = numOfWatched;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVideoRef(String videoRef) {
        this.videoRef = videoRef;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", level='" + level + '\'' +
                ", equipment='" + equipment + '\'' +
                ", desc='" + desc + '\'' +
                ", time=" + time +
                ", rating=" + rating +
                ", numOfWatched=" + numOfWatched +
                ", user=" + user +
                ", videoRef='" + videoRef + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object other) {
        int compareage=((Train)other).getNumOfWatched();
        return compareage-this.numOfWatched;
    }
}
