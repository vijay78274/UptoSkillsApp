package com.example.uptoskills;

public class CourseModel {
    String name, image_url,content,duration,count_students,rating,price_rendered,origin_price_rendered,typeofprograme;
    public CourseModel(String name, String typeofprograme, String image_url){
        this.name=name;
        this.typeofprograme=typeofprograme;
        this.image_url=image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCount_students() {
        return count_students;
    }

    public void setCount_students(String count_students) {
        this.count_students = count_students;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice_rendered() {
        return price_rendered;
    }

    public void setPrice_rendered(String price_rendered) {
        this.price_rendered = price_rendered;
    }

    public String getOrigin_price_rendered() {
        return origin_price_rendered;
    }

    public void setOrigin_price_rendered(String origin_price_rendered) {
        this.origin_price_rendered = origin_price_rendered;
    }

    public String getTypeofprograme() {
        return typeofprograme;
    }

    public void setTypeofprograme(String typeofprograme) {
        this.typeofprograme = typeofprograme;
    }
}
