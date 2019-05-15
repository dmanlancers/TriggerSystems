package com.wetrig.dev.wetrig;



/**
 * Created by darkangel on 28/06/16.
 */
public class RatingItems {



    private String image;
    private String name;
    private String rating;
    private String review;


    public RatingItems(String imageId, String name, String rating, String review) {
        this.image = imageId;
        this.name = name;
        this.rating = rating;
        this.review = review;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return name + "\n" + image + "\n"+rating+ "\n"+review;
    }
}





