package be;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Model {
    private static Model instance;
    private ObservableList<Song> songs;
    private ObservableList<Playlist> playlists;
    private ObservableList<Song> songsOnSelectedPlaylist;
    private Song selectedSong;
    private Playlist selectedPlaylist;

    /**
     * Constructs a new Model, initializing collections for songs, playlists, and songs on the selected playlist.
     *
     * Serves as the central data model for the music application.
     */
    private Model() {
        this.songs = FXCollections.observableArrayList();
        this.playlists = FXCollections.observableArrayList();
        this.songsOnSelectedPlaylist = FXCollections.observableArrayList();
    }

    /**
     * Retrieves the singleton instance of the Model class.
     *
     * If no instance exists, a new instance of the Model class is created.
     * Subsequent calls return the existing instance.
     *
     * Follows the Singleton design pattern to ensure a single point
     * of access to the Model instance throughout the application.
     *
     * @return The singleton instance of the Model class.
     */
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    // Add a song to the list of songs
    /**
     * Adds a song to the collection of songs in the Model.
     *
     * This method appends the specified song to the list of songs managed by the Model.
     *
     * @param song The song to be added to the collection.
     *
     * This method modifies the internal state of the Model by adding a song to its collection.
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Sets the selected song in the Model.
     *
     * This method updates the Model's internal state by setting the specified song
     * as the currently selected song.
     *
     * @param song The song to be set as the selected song.
     *
     * This method is used to manage the currently selected song within the Model.
     */
    public void setSelectedSong(Song song) {
        this.selectedSong = song;
    }

    /**
     * Retrieves the currently selected song from the Model.
     *
     * This method returns the song that has been set as the currently selected song
     * within the Model.
     *
     * @return The currently selected song.
     *
     * This method provides access to the currently selected song in the Model.
     */
    public Song getSelectedSong() {
        return selectedSong;
    }

    // Remove a song from the list of songs
    /**
     * Deletes a song from the Model's collection.
     *
     * This method removes the specified song from the list of songs managed by the Model.
     * Additionally, you might want to consider removing the song from any playlists it belongs to.
     *
     * @param song The song to be deleted from the collection.
     *
     * This method modifies the internal state of the Model by removing a song from its collection.
     * It also suggests removing the song from playlists for more comprehensive data management.
     */
    public void deleteSong(Song song) {
        songs.remove(song);
        // You might also want to remove this song from any playlists it belongs to
    }

    // Add a playlist to the list of playlists
    /**
     * Updates a playlist in the Model's collection.
     *
     * This method searches for the playlist with the specified ID in the Model's collection.
     * If found, it replaces the existing playlist with the provided updated playlist.
     *
     * @param updatedPlaylist The updated playlist to be applied.
     *
     * This method modifies the internal state of the Model by updating a playlist in its collection.
     * It uses the playlist's ID to locate the existing playlist.
     *
     * @param updatedPlaylist The updated playlist to be applied.
     *
     * This method modifies the internal state of the Model by updating a playlist in its collection.
     * It uses the playlist's ID to locate the existing playlist.
     *
     * @param updatedPlaylist The updated playlist to be applied.
     *
     * This method modifies the internal state of the Model by updating a playlist in its collection.
     * It uses the playlist's ID to locate the existing playlist.
     */
    public void updatePlaylist(Playlist updatedPlaylist) {
        int index = findPlaylistIndex(updatedPlaylist.getID());
        if (index != -1) {
            playlists.set(index, updatedPlaylist);
        }
    }

    // Helper method to find the index of a playlist by its ID
    /**
     * Finds the index of a playlist with the specified ID in the Model's collection.
     *
     * This method iterates through the playlists in the Model, searching for a playlist
     * with the given ID. If found, it returns the index of the playlist; otherwise, it
     * returns -1 to indicate that the playlist isn't found.
     *
     * @param playlistID The ID of the playlist to search for.
     * @return The index of the playlist with the specified ID, or -1 if not found.
     *
     * This method aids in locating the index of a playlist within the Model's collection.
     */
    private int findPlaylistIndex(int playlistID) {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getID() == playlistID) {
                return i;
            }
        }
        return -1; // Playlist isn't found
    }

    /**
     * Adds a playlist to the Model's collection.
     *
     * This method appends the specified playlist to the list of playlists managed by the Model.
     *
     * @param playlist The playlist to be added to the collection.
     *
     * This method modifies the internal state of the Model by adding a playlist to its collection.
     */
    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    // Remove a playlist from the list of playlists
    /**
     * Deletes a playlist from the Model's collection.
     *
     * This method removes the specified playlist from the list of playlists managed by the Model.
     *
     * @param playlist The playlist to be deleted from the collection.
     *
     * This method modifies the internal state of the Model by removing a playlist from its collection.
     */
    public void deletePlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    /**
     * Sets the selected playlist in the Model.
     *
     * This method updates the Model's internal state by setting the specified playlist
     * as the currently selected playlist.
     *
     * @param playlist The playlist to be set as the selected playlist.
     *
     * This method is used to manage the currently selected playlist within the Model.
     */
    public void setSelectedPlaylist(Playlist playlist) {
        this.selectedPlaylist = playlist;
    }

    /**
     * Retrieves the currently selected playlist from the Model.
     *
     * This method returns the playlist that has been set as the currently selected playlist
     * within the Model.
     *
     * @return The currently selected playlist.
     *
     * This method provides access to the currently selected playlist in the Model.
     */
    public Playlist getSelectedPlaylist() {
        return selectedPlaylist;
    }

    // Get all songs
    /**
     * Retrieves the collection of all songs managed by the Model.
     *
     * This method returns an ObservableList containing all the songs stored in the Model.
     *
     * @return An ObservableList of all songs in the Model.
     *
     * This method provides access to the entire collection of songs in the Model.
     */
    public ObservableList<Song> getAllSongs() {
        return songs;
    }

    // Get all playlists
    /**
     * Retrieves the collection of all playlists managed by the Model.
     *
     * This method returns an ObservableList containing all the playlists stored in the Model.
     *
     * @return An ObservableList of all playlists in the Model.
     *
     * This method provides access to the entire collection of playlists in the Model.
     */
    public ObservableList<Playlist> getAllPlaylists() {
        return playlists;
    }

    // Add a song to a playlist
    /**
     * Adds a song to a specified playlist in the Model.
     *
     * This method appends the specified song to the list of songs in the provided playlist.
     *
     * @param song The song to be added to the playlist.
     * @param playlist The playlist to which the song will be added.
     *
     * This method modifies the internal state of the Model by adding a song to a specific playlist.
     *
     * @param song The song to be added to the playlist.
     * @param playlist The playlist to which the song will be added.
     *
     * This method modifies the internal state of the Model by adding a song to a specific playlist.
     *
     * @param song The song to be added to the playlist.
     * @param playlist The playlist to which the song will be added.
     *
     * This method modifies the internal state of the Model by adding a song to a specific playlist.
     */
    public void addSongToPlaylist(Song song, Playlist playlist) {
        playlist.addSong(song);
    }

    // Remove a song from a playlist
    /**
     * Removes a song from a specified playlist in the Model.
     *
     * This method removes the specified song from the list of songs in the provided playlist.
     *
     * @param song The song to be removed from the playlist.
     * @param playlist The playlist from which the song will be removed.
     *
     * This method modifies the internal state of the Model by removing a song from a specific playlist.
     *
     * @param song The song to be removed from the playlist.
     * @param playlist The playlist from which the song will be removed.
     *
     * This method modifies the internal state of the Model by removing a song from a specific playlist.
     *
     * @param song The song to be removed from the playlist.
     * @param playlist The playlist from which the song will be removed.
     *
     * This method modifies the internal state of the Model by removing a song from a specific playlist.
     */
    public void removeSongFromPlaylist(Song song, Playlist playlist) {
        playlist.removeSong(song);
    }

    /**
     * Retrieves the collection of all songs on the currently selected playlist in the Model.
     *
     * This method returns an ObservableList containing all the songs on the currently selected playlist.
     *
     * @return An ObservableList of all songs on the currently selected playlist in the Model.
     *
     * This method provides access to the collection of songs on the currently selected playlist in the Model.
     */
    public ObservableList<Song> getAllSongsOnPlaylist() {
        return songsOnSelectedPlaylist;
    }

    /**
     * Sets the collection of songs on the currently selected playlist in the Model.
     *
     * This method populates the internal list of songs on the currently selected playlist
     * with the songs from the selected playlist.
     *
     * This method modifies the internal state of the Model by updating the list of songs
     * on the currently selected playlist.
     */
    public void setAllSongsOnPlaylist() {
        songsOnSelectedPlaylist.addAll(selectedPlaylist.getSongs());
    }
}
