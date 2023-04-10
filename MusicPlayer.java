package com.example.intropage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer {
    private static MediaPlayer mediaPlayer;
    private static boolean isPlaying = true;

    public static MediaPlayer play() {
        if (mediaPlayer == null) {
            String musicFile = "music_bg.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        if (!isPlaying) {
            mediaPlayer.play();
            isPlaying = true;
        }
        return mediaPlayer;
    }

    public static void pause() {
        if (mediaPlayer != null && isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public static void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            isPlaying = false;
        }
    }
}