package be;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Song {
    private static int lastAssignedID = 0;
    private int ID;
    private final StringProperty title, artist, genre, time;
    private String filePath;

    /**
     * Constructs a new Song with the specified attributes.
     *
     * This constructor creates a new Song instance with a unique ID, the given title, artist, genre,
     * time, and file path.
     *
     * @param title The title of the song.
     * @param artist The artist of the song.
     * @param genre The genre of the song.
     * @param time The duration of the song.
     * @param filePath The file path to the song.
     *
     * Each Song instance is assigned a unique ID upon creation.
     */
    public Song(String title, String artist, String genre, String time, String filePath) {
        this.ID = ++lastAssignedID;
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.genre = new SimpleStringProperty(genre);
        this.time = new SimpleStringProperty(time);
        this.filePath = filePath;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Retrieves the JavaFX StringProperty representing the title of the Song.
     *
     * This method returns the JavaFX StringProperty associated with the title of the Song.
     * It provides a way to observe and bind to changes in the title.
     *
     * @return The JavaFX StringProperty representing the title of the Song.
     *
     * This method allows external entities to observe and bind to changes in the title of the Song.
     */
    public StringProperty titleProperty() {
        return title;
    }

    /**
     * Retrieves the title of the Song.
     *
     * This method returns the title associated with the Song.
     *
     * @return The title of the Song.
     *
     * The title is stored as a JavaFX StringProperty, and this method provides a way
     * to obtain its actual string value.
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * Sets a new title for the Song.
     *
     * This method updates the title of the Song with the specified new title.
     *
     * @param newTitle The new title to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its title.
     * It uses a JavaFX StringProperty to store and manage the title.
     *
     * @param newTitle The new title to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its title.
     * It uses a JavaFX StringProperty to store and manage the title.
     *
     * @param newTitle The new title to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its title.
     * It uses a JavaFX StringProperty to store and manage the title.
     */
    public void setTitle(String newTitle) {
        this.title.set(newTitle);
    }

    /**
     * Retrieves the JavaFX StringProperty representing the artist of the Song.
     *
     * This method returns the JavaFX StringProperty associated with the artist of the Song.
     * It provides a way to observe and bind to changes in the artist.
     *
     * @return The JavaFX StringProperty representing the artist of the Song.
     *
     * This method allows external entities to observe and bind to changes in the artist of the Song.
     */
    public StringProperty artistProperty() {
        return artist;
    }

    public String getArtist() {
        return artist.get();
    }

    /**
     * Sets a new artist for the Song.
     *
     * This method updates the artist of the Song with the specified new artist.
     *
     * @param newArtist The new artist to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its artist.
     * It uses a JavaFX StringProperty to store and manage the artist.
     *
     * @param newArtist The new artist to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its artist.
     * It uses a JavaFX StringProperty to store and manage the artist.
     *
     * @param newArtist The new artist to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its artist.
     * It uses a JavaFX StringProperty to store and manage the artist.
     */
    public void setArtist(String newArtist) {
        this.artist.set(newArtist);
    }

    /**
     * Retrieves the JavaFX StringProperty representing the genre of the Song.
     *
     * This method returns the JavaFX StringProperty associated with the genre of the Song.
     * It provides a way to observe and bind to changes in the genre.
     *
     * @return The JavaFX StringProperty representing the genre of the Song.
     *
     * This method allows external entities to observe and bind to changes in the genre of the Song.
     */
    public StringProperty genreProperty() {
        return genre;
    }

    public String getGenre() {
        return genre.get();
    }

    /**
     * Sets a new genre for the Song.
     *
     * This method updates the genre of the Song with the specified new genre.
     *
     * @param newGenre The new genre to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its genre.
     * It uses a JavaFX StringProperty to store and manage the genre.
     *
     * @param newGenre The new genre to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its genre.
     * It uses a JavaFX StringProperty to store and manage the genre.
     *
     * @param newGenre The new genre to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its genre.
     * It uses a JavaFX StringProperty to store and manage the genre.
     */
    public void setGenre(String newGenre) {
        this.genre.set(newGenre);
    }

    /**
     * Retrieves the JavaFX StringProperty representing the duration of the Song.
     *
     * This method returns the JavaFX StringProperty associated with the duration of the Song.
     * It provides a way to observe and bind to changes in the duration.
     *
     * @return The JavaFX StringProperty representing the duration of the Song.
     *
     * This method allows external entities to observe and bind to changes in the duration of the Song.
     */
    public StringProperty timeProperty() {
        return time;
    }

    public String getTime() {
        return time.get();
    }

    /**
     * Sets a new duration for the Song.
     *
     * This method updates the duration of the Song with the specified new duration.
     *
     * @param newTime The new duration to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its duration.
     * It uses a JavaFX StringProperty to store and manage the duration.
     *
     * @param newTime The new duration to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its duration.
     * It uses a JavaFX StringProperty to store and manage the duration.
     *
     * @param newTime The new duration to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its duration.
     * It uses a JavaFX StringProperty to store and manage the duration.
     */
    public void setTime(String newTime) {
        this.time.set(newTime);
    }


    /**
     * Retrieves the file path of the Song.
     *
     * This method returns the file path associated with the Song.
     *
     * @return The file path of the Song.
     *
     * This method provides access to the file path associated with the Song.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets a new file path for the Song.
     *
     * This method updates the file path of the Song with the specified new file path.
     *
     * @param filePath The new file path to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its file path.
     *
     * @param filePath The new file path to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its file path.
     *
     * @param filePath The new file path to be set for the Song.
     *
     * This method modifies the internal state of the Song by updating its file path.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


/**
 * Returns a string representation of the Song.
 *
 * This method returns the string representation of the Song, which is its title.
 *
 * @return The title of the Song as a string.
 *
 * This method provides a convenient way to obtain a string representation of the Song.
 */
    @Override
    public String toString() {
        return title.getValue();
    }
}
