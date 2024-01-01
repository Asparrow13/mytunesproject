package gui.controller;

import be.Song;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class PlaySongHelper {

    /**
     * Singleton instance of the MediaPlayer for managing audio playback.
     *
     * This field represents a static instance of the MediaPlayer, ensuring that only one instance exists
     * throughout the application. It is used for managing audio playback, allowing centralized control
     * over the playback functionality.
     *
     * This field is declared as 'static' to enforce a singleton pattern, and it is commonly accessed
     * through a method like 'getMediaPlayerInstance()' to ensure a single instance is used globally.
     */
    private static MediaPlayer mediaPlayerInstance;

    private boolean isPlaying = false;

    /**
     * Retrieves the singleton instance of the MediaPlayer for managing audio playback.
     *
     * This method returns the static instance of the MediaPlayer, ensuring that only one instance exists
     * throughout the application. It provides a centralized way to access the MediaPlayer for controlling
     * audio playback.
     *
     * @return The singleton instance of the MediaPlayer.
     *
     * This method is commonly used to obtain the global instance of the MediaPlayer and is often
     * called when interacting with audio playback functionality.
     */
    public static MediaPlayer getMediaPlayerInstance() {
        return mediaPlayerInstance;
    }

    /**
     * Sets the singleton instance of the MediaPlayer for managing audio playback.
     *
     * This method allows updating the static instance of the MediaPlayer, providing a centralized way
     * to change the global instance used for controlling audio playback.
     *
     * @param mediaPlayerInstance The new instance of the MediaPlayer to be set.
     *
     * This method is commonly used when initializing the application or changing the MediaPlayer
     * instance based on specific requirements.
     */
    public static void setMediaPlayerInstance(MediaPlayer mediaPlayerInstance) {
        PlaySongHelper.mediaPlayerInstance = mediaPlayerInstance;
    }

    /**
     * Toggles between playing and stopping the selected song using the global MediaPlayer instance.
     *
     * This method takes a 'selectedSong' and checks the current status of the MediaPlayer instance.
     * If the MediaPlayer is currently playing a song, it stops the playback. Otherwise, it creates a new
     * MediaPlayer instance for the selected song, sets it as the global instance, and starts playing the song.
     * The method also handles the case when the song file cannot be found.
     *
     * @param selectedSong The song to be played or stopped.
     *
     * This method relies on a global MediaPlayer instance ('mediaPlayerInstance') for managing audio playback.
     * It uses JavaFX classes such as 'MediaPlayer' and 'Media' for handling media playback.
     */
    public void togglePlayStop(Song selectedSong) {
        String songFilePath = selectedSong.getFilePath();

        if (mediaPlayerInstance != null && mediaPlayerInstance.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayerInstance.stop(); // If the song is playing, stop it
            isPlaying = false;
        } else {
            File file = new File(songFilePath);
            if (file.exists()) {
                Media media = new Media(file.toURI().toString());
                mediaPlayerInstance = new MediaPlayer(media);
                setMediaPlayerInstance(mediaPlayerInstance);

                mediaPlayerInstance.setOnReady(() -> {
                    mediaPlayerInstance.play(); // Play the song
                    isPlaying = true;
                });

                mediaPlayerInstance.setOnEndOfMedia(() -> {
                    mediaPlayerInstance.stop(); // Stop the player when the song ends
                    isPlaying = false;
                });
            } else {
                AlertHelper.alertDialogBuilder("Could not find the file");
            }
        }
    }


    /**
     * Skips forward by a specified duration in the currently playing song.
     *
     * This method checks if the global MediaPlayer instance is playing a song. If it is, it seeks
     * the current playback position forward by 5 seconds. This allows the user to skip forward in
     * the currently playing song.
     *
     * This method relies on a global MediaPlayer instance ('mediaPlayerInstance') for managing audio playback.
     * It uses JavaFX classes such as 'MediaPlayer' and 'Duration' for handling media playback and seeking.
     */
    public void skipForward() {
        if (mediaPlayerInstance != null && mediaPlayerInstance.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayerInstance.seek(mediaPlayerInstance.getCurrentTime().add(javafx.util.Duration.seconds(5))); // Skip forward by 5 seconds
        }
    }


    /**
     * Skips backward by a specified duration in the currently playing song.
     *
     * This method checks if the global MediaPlayer instance is playing a song. If it is, it seeks
     * the current playback position backward by 5 seconds. This allows the user to skip backward in
     * the currently playing song.
     *
     * This method relies on a global MediaPlayer instance ('mediaPlayerInstance') for managing audio playback.
     * It uses JavaFX classes such as 'MediaPlayer' and 'Duration' for handling media playback and seeking.
     */
    public void skipBackward() {
        if (mediaPlayerInstance != null && mediaPlayerInstance.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayerInstance.seek(mediaPlayerInstance.getCurrentTime().subtract(javafx.util.Duration.seconds(5))); // Skip backward by 5 seconds
        }
    }
}
