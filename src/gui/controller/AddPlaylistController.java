package gui.controller;

import be.Model;
import be.Playlist;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AddPlaylistController {
    @FXML
    private TextField playlistNameField;
    @FXML
    private Button addPlaylistBtn, editPlaylistBtn;
    private final Model musicModel = Model.getInstance();

    /**
     * Initializes the components and resources when the associated object is created or loaded.
     *
     * This method is typically called during the initialization phase of an object's lifecycle.
     * It is meant for setting up necessary components, configuring properties, and performing any
     * initialization tasks required by the object.
     *
     * This method can be overridden by subclasses to provide custom initialization logic.
     */
    public void initialize() {
        // Implementation details for initialization can be added here
    }


    @FXML
    private void handleAddPlaylist() {
        String name = playlistNameField.getText();
        Playlist newPlaylist = new Playlist(name);
        List<Playlist> playlists = musicModel.getAllPlaylists();
        boolean exists = false;
        for (Playlist playlist : playlists) {
            if (playlist.getName().equalsIgnoreCase(name)) {
                exists = true;
            }
        }
        if (!exists) {
            musicModel.addPlaylist(newPlaylist);
            Stage stage = (Stage) addPlaylistBtn.getScene().getWindow();
            stage.close();
            AlertHelper.successDialogBuilder("Playlist Added");
        } else {
            AlertHelper.alertDialogBuilder("Playlist Already Exists");
        }
    }


    @FXML
    public void handleEditPlaylist() {
        Playlist selectedPlaylist = musicModel.getSelectedPlaylist();

        if (selectedPlaylist != null) {
            // Retrieve updated data from the frontend fields
            String updatedName = playlistNameField.getText();

            boolean exists = false;
            for (Playlist playlist : musicModel.getAllPlaylists()) {
                if (playlist.getName().equalsIgnoreCase(updatedName)) {
                    exists = true;
                }
            }
            if (!exists) {
                // Update the selected playlist
                selectedPlaylist.setName(updatedName);
                Stage stage = (Stage) editPlaylistBtn.getScene().getWindow();
                stage.close();
                AlertHelper.successDialogBuilder("Playlist name changed");
            } else {
                AlertHelper.alertDialogBuilder("Playlist with the same name already exists");
            }
        }
    }
}
