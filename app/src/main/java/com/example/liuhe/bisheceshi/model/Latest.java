package com.example.liuhe.bisheceshi.model;

import java.util.List;

/**
 * Created by liuhe on 16/5/23.
 */
public class Latest {

    private List<TopStoriesEntity> top_stories;
    private List<StoriesEntity> stories;
    private String date;

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;

    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public String getDate() {
        return date;
    }

    public static class TopStoriesEntity{

        private int id;
        private String title;
        private String ga_prefix;
        private String image;
        private int type;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {

            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public String getImage() {
            return image;
        }

        public int getType() {
            return type;
        }

        @Override
        public String toString() {
            return "TopStoriesEntity{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", image='" + image + '\'' +
                    ", type=" + type +
                    '}';
        }
    }

    public static class StoriesEntity
        {
        private int id;
        private String title;
        private String ga_prefix;
        private List<String> images;
        private int type;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {

            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public List<String> getImages() {
            return images;
        }

        public int getType() {
            return type;
        }

        @Override
        public String toString() {
            return "StoriesEntity{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", images=" + images +
                    ", type=" + type +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Latest{" +
                "top_stories=" + top_stories +
                ", stories=" + stories +
                ", date='" + date + '\'' +
                '}';
    }
}
