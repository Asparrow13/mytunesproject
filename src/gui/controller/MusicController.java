package gui.controller;

import be.Model;
import be.Playlist;
import be.Song;

import gui.styles.Styles;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class MusicController {
    // FXML elements
    @FXML private TableView<Song> SongTableView;
    @FXML private TableColumn<Song, String> TitleColumn, ArtistColumn, GenreColumn, TimeColumn;
    @FXML private Button deletePlaylistBtn, skipBtnB, playBtn, skipBtnF, searchBtn, newPlaylistBtn, editPlaylistBtn
            ,upBtn, downBtn, deleteSongOnPlaylistBtn, addToPlaylistBtn, newSongBtn, editSongBtn, deleteSongBtn, closeBtn;
    @FXML private Label sliderLbl, filterLbl, playlistLbl, songOnPlaylistLbl, songsLbl;
    private ObservableList<Song> songs = FXCollections.observableArrayList();
    private final PlaySongHelper playSongController = new PlaySongHelper();
    @FXML
    private Slider volumeSlider;
    private MediaPlayer mediaPlayer;

    private ObservableList<Playlist> playlists = FXCollections.observableArrayList();
    private final ObservableList<Song> songsOnPlaylist = FXCollections.observableArrayList();

    @FXML private ListView<Playlist> playlistView;
    @FXML private ListView<Song> songsOnPlaylistView;

    private final Model musicModel = Model.getInstance();

    /**
     * Initializes the application by setting up the initial state, loading sample songs, and configuring UI elements.
     *
     * This method is typically called during the initialization phase of the application. It initializes the songs
     * and playlists by retrieving data from the music model. Additionally, it sets up event handlers for the playlist
     * view and loads a few sample songs from the media file to showcase the application. The method also creates
     * sample instances of songs and a playlist, adds songs to the playlist, initializes table columns, playlist view,
     * and applies styles to the UI.
     *
     * This method relies on specific UI components like 'playlistView', 'songsOnPlaylistView', and uses the
     * 'musicModel' to manage songs and playlists. It also showcases loading sample songs from the media file
     * and initializing table columns and playlist view.
     */
    public void initialize() {
        songs = musicModel.getAllSongs();
        playlists = musicModel.getAllPlaylists();

        playlistView.setOnMouseClicked(event -> {
            songsOnPlaylistView.getItems().clear();
            initializePlaylistSongsView();
        });

        // Loading a few songs from the media file to showcase the application
        String relativePath = "media/LostSkyNeedYou.mp3";
        String relativePath1 = "media/CultureCodeFairytale(feat.AmandaCollis).mp3";
        String relativePath2 = "media/Halcyon&ValentinaFrancoRunaway(TARIRemix).mp3";

        URL resourceUrl = Objects.requireNonNull(getClass().getClassLoader().getResource(relativePath));
        String absolutePath = resourceUrl.toExternalForm();

        URL resourceUrl1 = Objects.requireNonNull(getClass().getClassLoader().getResource(relativePath1));
        String absolutePath1 = resourceUrl.toExternalForm();

        URL resourceUrl2 = Objects.requireNonNull(getClass().getClassLoader().getResource(relativePath2));
        String absolutePath2 = resourceUrl.toExternalForm();

        try {
            URI uri = resourceUrl.toURI();
            absolutePath = uri.getPath();
            URI uri1 = resourceUrl1.toURI();
            absolutePath1 = uri1.getPath();
            URI uri2 = resourceUrl2.toURI();
            absolutePath2 = uri2.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(absolutePath);

        Song song = new Song("Need You", "Lost Sky", "Future Bass", "278", absolutePath);
        Song song1 = new Song("Culture Code", "Fairy Tale", "Future Bass", "290", absolutePath1);
        Song song2 = new Song("Runaway", "Halcyon & Valentina Franco", "Future Bass", "248", absolutePath2);
        songs.addAll(song, song1, song2);

        Playlist playlist1 = new Playlist("Future Bass");
        playlists.add(playlist1);
        musicModel.addSongToPlaylist(song, playlist1);

        initializeTableColumns();
        initializePlaylistView();
        initialiseStyle();
    }

    /**
     * Initializes the volume slider for controlling the media player's volume.
     *
     * This method sets up the volume slider with minimum and maximum values, and attaches a listener to handle volume changes.
     * If a media player instance is available, it adjusts the media player's volume based on the slider's value when changed.
     *
     * This method assumes the existence of a volume slider named 'volumeSlider' and uses 'PlaySongHelper'
     * to obtain a media player instance.
     */
    public void initializeMediaPlayerSlider() {
        mediaPlayer = PlaySongHelper.getMediaPlayerInstance();
        volumeSlider.setMin(0); // Minimum value for volume (e.g., 0%)
        volumeSlider.setMax(100); // Maximum value for volume (e.g., 100%)

        if (mediaPlayer != null) {
            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (mediaPlayer.getStatus() == MediaPlayer.Status.READY ||
                        mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaPlayer.setVolume(newValue.doubleValue() / 100.0); // Normalize volume to range 0.0 - 1.0
                }
            });
        }
    }

    /**
     * Initializes the table columns for displaying song information.
     *
     * This method sets up the cell value factories for each column, binding them to the corresponding properties of a Song.
     * It associates the 'TitleColumn' with the title property, 'ArtistColumn' with the artist property,
     * 'GenreColumn' with the genre property, and 'TimeColumn' with the time property. Additionally, it populates the
     * 'SongTableView' with the provided list of songs.
     *
     * This method assumes the existence of table columns named 'TitleColumn', 'ArtistColumn', 'GenreColumn',
     * 'TimeColumn', and a table view named 'SongTableView'. It uses JavaFX properties for binding and populating data.
     */
    private void initializeTableColumns() {
        TitleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        ArtistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        GenreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        TimeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        SongTableView.setItems(songs);
    }

    @FXML
    private void openAddSongForm() {
        try {
            // Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/newSongWin.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage
            Stage addSongStage = new Stage();
            addSongStage.setTitle("Add Song");

            // Set the scene onto the stage
            addSongStage.setScene(new Scene(root));

            InputStream icon = ClassLoader.getSystemResourceAsStream("media/Icons&Images/appIcon.png");
            Image iconImage = new Image(Objects.requireNonNull(icon));
            addSongStage.getIcons().add(iconImage);

            // Show the stage
            addSongStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }
    }
    @FXML
    private void handleDeleteSong() {
        Song selectedSong = SongTableView.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            musicModel.deleteSong(selectedSong);
        }else{
            AlertHelper.alertDialogBuilder("Please select a song");
        }
    }
    @FXML
    private void openEditSongForm() {
        Song selectedSong = SongTableView.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            musicModel.setSelectedSong(selectedSong);
            try {
                // Load the new FXML file
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/editSongWin.fxml"));
                Parent root = fxmlLoader.load();
                // Create a new stage
                Stage editSongStage = new Stage();
                editSongStage.setTitle("Edit Song");
                // Set the scene onto the stage
                editSongStage.setScene(new Scene(root));

                InputStream icon = ClassLoader.getSystemResourceAsStream("media/Icons&Images/appIcon.png");
                Image iconImage = new Image(Objects.requireNonNull(icon));
                editSongStage.getIcons().add(iconImage);
                // Show the stage
                editSongStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception accordingly
            }
        }else {
            AlertHelper.alertDialogBuilder("Please select a song");
        }
    }

    @FXML
    private void handlePlaySong() {
        Song selectedSong = SongTableView.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            playSong(selectedSong);
        }else {
            AlertHelper.alertDialogBuilder("Select a song to play");
        }
    }
    /**
     * Plays or stops the selected song, toggles the play/stop state, and initializes the media player slider.
     *
     * This method takes a selected song and passes it to the 'playSongController' to toggle between playing and stopping.
     * It then calls 'initializeMediaPlayerSlider' to set up the volume slider for controlling the media player's volume.
     *
     * @param selectedSong The song to be played or stopped.
     *
     * This method assumes the existence of a 'playSongController' for managing song playback and a method
     * named 'togglePlayStop' to handle the play/stop functionality. It also relies on 'initializeMediaPlayerSlider'
     * to set up the volume slider.
     */
    private void playSong(Song selectedSong) {
        playSongController.togglePlayStop(selectedSong);
        initializeMediaPlayerSlider();
    }

    @FXML
    private void handleSkipF() {
        playSongController.skipForward();
    }

    @FXML
    private void handleSkipB() {
        playSongController.skipBackward();
    }


    /**
     * Initializes the playlist view with the provided list of playlists.
     *
     * This method sets up the playlist view by populating it with the provided list of playlists.
     * It associates the 'playlistView' with the 'playlists' list to display the available playlists.
     *
     * This method assumes the existence of a list view named 'playlistView' and a list of playlists named 'playlists'.
     * It uses JavaFX properties for binding and displaying data.
     */
    private void initializePlaylistView() {
        playlistView.setItems(playlists);
    }

    /**
     * Initializes the view displaying songs for the selected playlist.
     *
     * This method retrieves the currently selected playlist from the 'playlistView' and updates the music model
     * to set it as the selected playlist. It then sets the songs on the selected playlist to the 'songsOnPlaylistView'
     * to display them in the corresponding view.
     *
     * This method assumes the existence of a playlist view named 'playlistView',
     * a list view for songs on the playlist named 'songsOnPlaylistView',
     * and utilizes the 'musicModel' to manage playlists and songs.
     */
    private void initializePlaylistSongsView() {
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();
        musicModel.setSelectedPlaylist(selectedPlaylist);
        musicModel.setAllSongsOnPlaylist();
        songsOnPlaylistView.setItems(musicModel.getAllSongsOnPlaylist());
    }

    @FXML
    private void openAddPlaylistForm() {
        try {
            // Load the new FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/newPlaylistWin.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage
            Stage addPlaylistStage = new Stage();
            addPlaylistStage.setTitle("Add Playlist");

            // Set the scene onto the stage
            addPlaylistStage.setScene(new Scene(root));

            InputStream icon = ClassLoader.getSystemResourceAsStream("media/Icons&Images/appIcon.png");
            Image iconImage = new Image(Objects.requireNonNull(icon));
            addPlaylistStage.getIcons().add(iconImage);

            // Show the stage
            addPlaylistStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDeletePlaylist() {
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            musicModel.deletePlaylist(selectedPlaylist);
        }else {
            AlertHelper.alertDialogBuilder("Please select a playlist");
        }
    }
    @FXML
    private void openEditPlaylistForm() {
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();
        if (selectedPlaylist != null) {
            musicModel.setSelectedPlaylist(selectedPlaylist);
            try {
                // Load the new FXML file
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/editPlaylistWin.fxml"));
                Parent root = fxmlLoader.load();
                // Create a new stage
                Stage editPlaylistStage = new Stage();
                editPlaylistStage.setTitle("Edit Playlist");
                // Set the scene onto the stage
                editPlaylistStage.setScene(new Scene(root));

                InputStream icon = ClassLoader.getSystemResourceAsStream("media/Icons&Images/appIcon.png");
                Image iconImage = new Image(Objects.requireNonNull(icon));
                editPlaylistStage.getIcons().add(iconImage);
                // Show the stage
                editPlaylistStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            AlertHelper.alertDialogBuilder("Please select a playlist");
        }
    }

    @FXML
    private void handleAddToPlaylist() {
        Song selectedSong = SongTableView.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();
        boolean alreadyOnPlaylist = selectedPlaylist.getSongs().contains(selectedSong);

        if (selectedSong != null && selectedPlaylist != null) {
            if(!alreadyOnPlaylist) {
                songsOnPlaylistView.getItems().clear();
                musicModel.addSongToPlaylist(selectedSong, selectedPlaylist);
                System.out.println("ADDED");
                initializePlaylistSongsView();
            }else{
                AlertHelper.alertDialogBuilder("The song is already on the playlist");
            }
        }else{
            AlertHelper.alertDialogBuilder("Please select a song and the playlist you want to add the song to!");
        }
    }

    @FXML
    private void handleRemoveFromPlaylist() {
        Song selectedSong = SongTableView.getSelectionModel().getSelectedItem();
        Playlist selectedPlaylist = playlistView.getSelectionModel().getSelectedItem();

        if (selectedSong != null && selectedPlaylist != null) {
            musicModel.removeSongFromPlaylist(selectedSong, selectedPlaylist);
        }else{
            AlertHelper.alertDialogBuilder("Select a song to remove");
        }
    }

    @FXML
    private void handleClose(){
        Platform.exit(); // Close the application
    }

    //Setting buttons style
    private void initialiseStyle() {
        newPlaylistBtn.setStyle(Styles.idle);
        newPlaylistBtn.setOnMouseEntered(e -> newPlaylistBtn.setStyle(Styles.hover));
        newPlaylistBtn.setOnMouseExited(e -> newPlaylistBtn.setStyle(Styles.idle));

        deletePlaylistBtn.setStyle(Styles.idle);
        deletePlaylistBtn.setOnMouseEntered(e -> deletePlaylistBtn.setStyle(Styles.hover));
        deletePlaylistBtn.setOnMouseExited(e -> deletePlaylistBtn.setStyle(Styles.idle));

        skipBtnB.setStyle(Styles.playIdle);
        skipBtnB.setOnMouseEntered(e -> skipBtnB.setStyle(Styles.playHover));
        skipBtnB.setOnMouseExited(e -> skipBtnB.setStyle(Styles.playIdle));

        playBtn.setStyle(Styles.playIdle);
        playBtn.setPrefSize(70, 70);
        playBtn.setOnMouseEntered(e -> playBtn.setStyle(Styles.playHover));
        playBtn.setOnMouseExited(e -> playBtn.setStyle(Styles.playIdle));

        skipBtnF.setStyle(Styles.playIdle);
        skipBtnF.setOnMouseEntered(e -> skipBtnF.setStyle(Styles.playHover));
        skipBtnF.setOnMouseExited(e -> skipBtnF.setStyle(Styles.playIdle));

        searchBtn.setStyle(Styles.idle);
        searchBtn.setOnMouseEntered(e -> searchBtn.setStyle(Styles.hover));
        searchBtn.setOnMouseExited(e -> searchBtn.setStyle(Styles.idle));

        editPlaylistBtn.setStyle(Styles.idle);
        editPlaylistBtn.setOnMouseEntered(e -> editPlaylistBtn.setStyle(Styles.hover));
        editPlaylistBtn.setOnMouseExited(e -> editPlaylistBtn.setStyle(Styles.idle));

        upBtn.setStyle(Styles.idle);
        upBtn.setOnMouseEntered(e -> upBtn.setStyle(Styles.hover));
        upBtn.setOnMouseExited(e -> upBtn.setStyle(Styles.idle));

        downBtn.setStyle(Styles.idle);
        downBtn.setOnMouseEntered(e -> downBtn.setStyle(Styles.hover));
        downBtn.setOnMouseExited(e -> downBtn.setStyle(Styles.idle));

        deleteSongOnPlaylistBtn.setStyle(Styles.idle);
        deleteSongOnPlaylistBtn.setOnMouseEntered(e -> deleteSongOnPlaylistBtn.setStyle(Styles.hover));
        deleteSongOnPlaylistBtn.setOnMouseExited(e -> deleteSongOnPlaylistBtn.setStyle(Styles.idle));

        addToPlaylistBtn.setStyle(Styles.idle);
        addToPlaylistBtn.setOnMouseEntered(e -> addToPlaylistBtn.setStyle(Styles.hover));
        addToPlaylistBtn.setOnMouseExited(e -> addToPlaylistBtn.setStyle(Styles.idle));

        newSongBtn.setStyle(Styles.idle);
        newSongBtn.setOnMouseEntered(e -> newSongBtn.setStyle(Styles.hover));
        newSongBtn.setOnMouseExited(e -> newSongBtn.setStyle(Styles.idle));

        editSongBtn.setStyle(Styles.idle);
        editSongBtn.setOnMouseEntered(e -> editSongBtn.setStyle(Styles.hover));
        editSongBtn.setOnMouseExited(e -> editSongBtn.setStyle(Styles.idle));

        deleteSongBtn.setStyle(Styles.idle);
        deleteSongBtn.setOnMouseEntered(e -> deleteSongBtn.setStyle(Styles.hover));
        deleteSongBtn.setOnMouseExited(e -> deleteSongBtn.setStyle(Styles.idle));

        closeBtn.setStyle(Styles.idle);
        closeBtn.setOnMouseEntered(e -> closeBtn.setStyle(Styles.hover));
        closeBtn.setOnMouseExited(e -> closeBtn.setStyle(Styles.idle));

        //setup label
        sliderLbl.setStyle(Styles.label);
        filterLbl.setStyle(Styles.label);
        playlistLbl.setStyle(Styles.viewStyleHeadingLabels);
        playlistLbl.setOnMouseEntered(e -> playlistLbl.setStyle(Styles.headingLabelsMouseOn));
        playlistLbl.setOnMouseExited(e -> playlistLbl.setStyle(Styles.headingLabelsMouseOff));
        songOnPlaylistLbl.setStyle(Styles.viewStyleHeadingLabels);
        songOnPlaylistLbl.setOnMouseEntered(e -> songOnPlaylistLbl.setStyle(Styles.headingLabelsMouseOn));
        songOnPlaylistLbl.setOnMouseExited(e -> songOnPlaylistLbl.setStyle(Styles.headingLabelsMouseOff));
        songsLbl.setStyle(Styles.viewStyleHeadingLabels);
        songsLbl.setOnMouseEntered(e -> songsLbl.setStyle(Styles.headingLabelsMouseOn));
        songsLbl.setOnMouseExited(e -> songsLbl.setStyle(Styles.headingLabelsMouseOff));

        //setup List and Table views
        SongTableView.setStyle(Styles.viewStyle);
        SongTableView.setPadding(new Insets(10, 10, 10, 10));
        playlistView.setStyle(Styles.viewStyle);
        playlistView.setPadding(new Insets(10, 10, 10, 10));
        songsOnPlaylistView.setPadding(new Insets(10, 10, 10, 10));
        songsOnPlaylistView.setStyle(Styles.viewStyle);
    }

}
