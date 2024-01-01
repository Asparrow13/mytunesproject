package be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Playlist {
    private static int lastAssignedID = 0;
    private int ID;
    private final StringProperty name;
    private ObservableList<Song> songs; // Use a List to store multiple songs

    /**
     * Constructs a new Playlist with the specified name.
     *
     * This constructor creates a new Playlist instance with a unique ID, the given name,
     * and an initially empty list of songs.
     *
     * @param name The name of the playlist.
     *
     * Each Playlist instance is assigned a unique ID upon creation.
     */
    public Playlist(String name) {
        this.ID = ++lastAssignedID;
        this.name = new SimpleStringProperty(name);
        this.songs = FXCollections.observableArrayList();
    }


    /**
     * Retrieves the unique identifier (ID) of the Playlist.
     *
     * This method returns the unique ID assigned to the Playlist instance upon its creation.
     *
     * @return The unique identifier (ID) of the Playlist.
     *
     * The ID is automatically assigned during the creation of a Playlist instance.
     */
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Retrieves the name of the Playlist.
     *
     * This method returns the name associated with the Playlist.
     *
     * @return The name of the Playlist.
     *
     * The name is stored as a SimpleStringProperty and accessed using its 'get' method.
     */
    public String getName() {
        return name.get();
    }

    /**
     * Sets a new name for the Playlist.
     *
     * This method updates the name of the Playlist with the specified new name.
     *
     * @param newName The new name to be set for the Playlist.
     *
     * This method modifies the internal state of the Playlist by updating its name.
     * It uses a SimpleStringProperty to store and manage the name.
     *
     * @param newName The new name to be set for the Playlist.
     *
     * This method modifies the internal state of the Playlist by updating its name.
     * It uses a SimpleStringProperty to store and manage the name.
     *
     * @param newName The new name to be set for the Playlist.
     *
     * This method modifies the internal state of the Playlist by updating its name.
     * It uses a SimpleStringProperty to store and manage the name.
     */
    public void setName(String newName) {
        this.name.set(newName);
    }

    /**
     * Retrieves the collection of songs in the Playlist.
     *
     * This method returns an ObservableList containing all the songs associated with the Playlist.
     *
     * @return An ObservableList of songs in the Playlist.
     *
     * This method provides access to the collection of songs associated with the Playlist.
     */
    public ObservableList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ObservableList<Song> songs) {
        this.songs = songs;
    }

    /**
     * Adds a song to the Playlist.
     *
     * This method appends the specified song to the list of songs associated with the Playlist.
     *
     * @param song The song to be added to the Playlist.
     *
     * This method modifies the internal state of the Playlist by adding a song to its collection.
     *
     * @param song The song to be added to the Playlist.
     *
     * This method modifies the internal state of the Playlist by adding a song to its collection.
     *
     * @param song The song to be added to the Playlist.
     *
     * This method modifies the internal state of the Playlist by adding a song to its collection.
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Removes a song from the Playlist.
     *
     * This method removes the specified song from the list of songs associated with the Playlist.
     *
     * @param song The song to be removed from the Playlist.
     *
     * This method modifies the internal state of the Playlist by removing a song from its collection.
     *
     * @param song The song to be removed from the Playlist.
     *
     * This method modifies the internal state of the Playlist by removing a song from its collection.
     *
     * @param song The song to be removed from the Playlist.
     *
     * This method modifies the internal state of the Playlist by removing a song from its collection.
     */
    public void removeSong(Song song) {
        songs.remove(song);
    }

/**
 * Returns a string representation of the Playlist.
 *
 * This method returns the string representation of the Playlist, which is its name.
 *
 * @return The name of the Playlist as a string.
 *
 * This method provides a convenient way to obtain a string representation of the Playlist.
 */
    @Override
    public String toString() {
        return name.getValue();
    }
}
