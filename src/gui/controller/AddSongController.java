package gui.controller;

import be.Model;
import be.Song;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AddSongController {

    @FXML
    private TextField songTitleField, songArtistField, songTimeField, songFilePathField;
    @FXML
    private Button addSongBtn, chooseSong, editSongBtn;
    @FXML
    private ChoiceBox<String> songGenreFieldChoiceBox;
    private final Model musicModel = Model.getInstance();

    /**
     * Initializes the associated object by setting up required components and resources.
     *
     * This method is typically called during the initialization phase of an object's lifecycle.
     * In this context, it initializes a choice box by calling the 'initializeChoiceBox' method.
     *
     * This method can be extended to include additional initialization tasks specific to the object.
     * It currently focuses on initializing the choice box.
     */
    public void initialize() {
        initializeChoiceBox();
    }

    /**
     * Initializes the associated choice box with a predefined list of genres.
     *
     * This method populates the choice box with a list of music genres.
     * The list is predefined and includes genres such as Rock, Pop, Hip-hop, Jazz, Electronic, Classical,
     * R&B, Country, Reggae, and Folk. The choice box is set to display these genres if it exists.
     *
     * This method assumes the existence of a choice box named 'songGenreFieldChoiceBox'.
     *           It uses predefined genres and sets them as items in the choice box.
     */
    private void initializeChoiceBox() {
        List<String> genres = Arrays.asList(
                "Rock", "Pop", "Hip-hop", "Jazz", "Electronic",
                "Classical", "R&B", "Country", "Reggae", "Folk"
        );
        if (songGenreFieldChoiceBox != null) {
            songGenreFieldChoiceBox.setItems(FXCollections.observableArrayList(genres));
        }
    }

    @FXML
    private void handleAddSong() {
        String title = songTitleField.getText();
        String artist = songArtistField.getText();
        String time = songTimeField.getText();
        String genre = songGenreFieldChoiceBox.getSelectionModel().getSelectedItem();
        String filepath = songFilePathField.getText();
        boolean exists = false;
        for (Song song : musicModel.getAllSongs()) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                exists = true;
            }
        }
        if (!title.isEmpty() && !artist.isEmpty() && !time.isEmpty() && !genre.isEmpty() && !filepath.isEmpty()) {
            if (!exists) {
                Song newSong = new Song(title, artist, genre, time, filepath);
                musicModel.addSong(newSong);
                Stage stage = (Stage) addSongBtn.getScene().getWindow();
                stage.close();
                AlertHelper.successDialogBuilder("Song Added");
            } else {
                AlertHelper.alertDialogBuilder("Song already exists");
            }
        } else {
            AlertHelper.alertDialogBuilder("Please fill the entire form");
        }
    }

    // Handle editing a song

    @FXML
    public void handleEditSong() {
        Song selectedSong = musicModel.getSelectedSong();
        if (selectedSong != null) {
            // Retrieve updated data from the frontend fields
            String updatedTitle = songTitleField.getText();
            String updatedArtist = songArtistField.getText();
            String updatedGenre = songGenreFieldChoiceBox.getValue();
            String updatedTime = songTimeField.getText();
            String updatedFilePath = songFilePathField.getText();

            boolean exists = false;
            for (Song song : musicModel.getAllSongs()) {
                if (song.getTitle().equalsIgnoreCase(updatedTitle)) {
                    exists = true;
                }
            }

            if (!exists) {
                // Update the selected song
                selectedSong.setTitle(updatedTitle);
                selectedSong.setArtist(updatedArtist);
                selectedSong.setGenre(updatedGenre);
                selectedSong.setTime(updatedTime);
                selectedSong.setFilePath(updatedFilePath);
                Stage stage = (Stage) editSongBtn.getScene().getWindow();
                stage.close();
                AlertHelper.successDialogBuilder("Done");
            } else {
                AlertHelper.alertDialogBuilder("Another Song with the same title already exists");
            }
        }
    }

    /**
     * Handles the action of choosing a song file using a file chooser.
     *
     * This method opens a file chooser dialog for selecting a song file with supported audio formats
     * (e.g., mp3, wav, aac). The selected file's path is then set to the associated text field.
     *
     * This method assumes the existence of a button named 'chooseSong', a text field named 'songFilePathField',
     * and uses JavaFX's FileChooser for selecting the file.
     */
    public void chooseSongBtn() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Song File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        Stage stage = (Stage) chooseSong.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            String filePath = selectedFile.getPath();
            songFilePathField.setText(filePath);
        }
    }
}