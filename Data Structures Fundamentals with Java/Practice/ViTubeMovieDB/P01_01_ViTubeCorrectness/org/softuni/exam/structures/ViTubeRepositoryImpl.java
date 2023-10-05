package org.softuni.exam.structures;

import org.softuni.exam.entities.User;
import org.softuni.exam.entities.Video;

import java.util.*;
import java.util.stream.Collectors;

public class ViTubeRepositoryImpl implements ViTubeRepository {
    private List<User> users;
    private List<Video> videos;
    private Map<User, int[]> activity;
    // array[0]: viewsCount, array[1]: likesCount, array[2]: dislikesCount

    public ViTubeRepositoryImpl() {
        this.users = new ArrayList<>();
        this.videos = new ArrayList<>();
        this.activity = new LinkedHashMap<>();
    }

    @Override
    public void registerUser(User user) {
        this.users.add(user);
        this.activity.put(user, new int[3]);
    }

    @Override
    public void postVideo(Video video) {
        this.videos.add(video);
    }

    @Override
    public boolean contains(User user) {
        return this.users.contains(user);
    }

    @Override
    public boolean contains(Video video) {
        return this.videos.contains(video);
    }

    @Override
    public Iterable<Video> getVideos() {
        return this.videos;
    }

    @Override
    public void watchVideo(User user, Video video) throws IllegalArgumentException {
        if (!this.contains(user) || !this.contains(video)) {
            throw new IllegalArgumentException();
        }

        int[] array = this.activity.get(user);
        array[0] = array[0] + 1;
        video.setViews(video.getViews() + 1);
    }

    @Override
    public void likeVideo(User user, Video video) throws IllegalArgumentException {
        if (!this.contains(user) || !this.contains(video)) {
            throw new IllegalArgumentException();
        }

        int[] array = this.activity.get(user);
        array[1] = array[1] + 1;
        video.setLikes(video.getLikes() + 1);
    }

    @Override
    public void dislikeVideo(User user, Video video) throws IllegalArgumentException {
        if (!this.contains(user) || !this.contains(video)) {
            throw new IllegalArgumentException();
        }

        int[] array = this.activity.get(user);
        array[2] = array[2] + 1;
        video.setDislikes(video.getDislikes() + 1);
    }

    @Override
    public Iterable<User> getPassiveUsers() {
        return this.users.stream().filter((User user) -> {
            int[] array = this.activity.get(user);
            return array[0] == 0 && array[1] == 0 && array[2] == 0;
        }).collect(Collectors.toList());
    }

    @Override
    public Iterable<Video> getVideosOrderedByViewsThenByLikesThenByDislikes() {
        return this.videos.stream().sorted(
                Comparator.comparing(Video::getViews).reversed()
                        .thenComparing(Video::getLikes).reversed()
                        .thenComparing(Video::getDislikes))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<User> getUsersByActivityThenByName() {
        return this.users.stream()
                .sorted(Comparator.comparing((User u) -> this.activity.get(u)[0]).reversed()
                        .thenComparing((User u) -> this.activity.get(u)[1] + this.activity.get(u)[2]).reversed()
                        .thenComparing(User::getUsername))
                .collect(Collectors.toList());
    }
}
