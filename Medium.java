package com.example.intropage;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

public class Medium extends Application {

    private static final int TILE_SIZE = 150;
    private static final int NUM_COLS = 5; //800
    private static final int NUM_ROWS = 4; //600
    private ImageView[][] tiles = new ImageView[NUM_COLS][NUM_ROWS];
    private int emptyCol = NUM_COLS - 1;
    private int emptyRow = NUM_ROWS - 1;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
// Load the image
        Image image = new Image("https://fastly.picsum.photos/id/870/800/600.jpg?hmac=STbSqyAVi-aEXbnSJs0O1mLHKifGGjOe7PVinLfiMVc");

// Create pane to hold tiles
        Pane tilePane = new Pane();
        tilePane.setPrefSize(NUM_COLS * TILE_SIZE, NUM_ROWS * TILE_SIZE);
        tilePane.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 20;");
        BorderPane.setAlignment(tilePane, Pos.CENTER);

// Add the puzzle label to the top center
        Label puzzleLabel = new Label("Puzzle Quest");
        puzzleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        BorderPane.setAlignment(puzzleLabel, Pos.TOP_CENTER);
        BorderPane.setMargin(puzzleLabel, new Insets(10, 0, 0, 0));

// Create the tiles
        for (int col = 0; col < NUM_COLS; col++) {
            for (int row = 0; row < NUM_ROWS; row++) {
                // Get the sub-image for this tile
                Rectangle2D rect = new Rectangle2D(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                PixelReader reader = image.getPixelReader();
                WritableImage subImage = new WritableImage(reader, (int) rect.getMinX(), (int) rect.getMinY(), (int) rect.getWidth(), (int) rect.getHeight());
                ImageView tile = new ImageView(subImage);

                tile.setX(col * TILE_SIZE);
                tile.setY(row * TILE_SIZE);
                tiles[col][row] = tile;
                tilePane.getChildren().add(tile);
            }
        }

// Shuffle the tiles
        shuffleTiles();

// Add the "Back" button to the bottom center
        Button backButton = new Button("Back");
        BorderPane.setAlignment(backButton, Pos.BOTTOM_CENTER);
        backButton.setStyle(Const.BUTTON_STYLE);
        backButton.setFont(Const.BUTTON_FONT);
        backButton.setPrefSize(Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        BorderPane.setMargin(backButton, new Insets(0, 0, 20, 0));
        tilePane.getChildren().add(backButton);

        backButton.setOnAction(event -> {
            // Create a confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Go back");
            alert.setHeaderText("Are you sure you want to go back?");
            alert.setContentText("Any unsaved progress will be lost.");

            // Show the dialog and wait for a response
            Optional<ButtonType> result = alert.showAndWait();

            // If the user clicks OK, go back to the intro page
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Handle button click to go back
                IntroPage back = new IntroPage();
                try {
                    back.start(primaryStage);
                    // close the current window
                    ((Stage) backButton.getScene().getWindow()).close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

// Create a BorderPane to hold the tile pane, label and back button
        BorderPane root = new BorderPane();
        root.setCenter(tilePane);
        root.setTop(puzzleLabel);
        root.setBottom(backButton);
        root.setBackground(Background.fill(Color.SADDLEBROWN));

// Create a group to hold the BorderPane and add it to the scene
        Group group = new Group(root);
        Scene scene = new Scene(group, 750, Const.SCREEN_HEIGHT);


        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();

        // Add a mouse listener to handle tile movements
        tilePane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int clickedCol = (int) event.getX() / TILE_SIZE;
                int clickedRow = (int) event.getY() / TILE_SIZE;
                if (canMoveTile(clickedCol, clickedRow)) {
                    moveTile(clickedCol, clickedRow);
                    if (isPuzzleSolved()) {
                        System.out.println("Congratulations, you solved the puzzle!");
                    }
                    Media media = new Media(new File("music_sound.mp3").toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                }
            }
        });


    }


    private void shuffleTiles() {
        for (int i = 0; i < 100; i++) {
            int direction = (int) (Math.random() * 4);
            switch (direction) {
                case 0: // move left
                    if (emptyCol > 0) {
                        moveTile(emptyCol - 1, emptyRow);
                    }
                    break;
                case 1: // move right
                    if (emptyCol < NUM_COLS - 1) {
                        moveTile(emptyCol + 1, emptyRow);
                    }
                    break;
                case 2: // move up
                    if (emptyRow > 0) {
                        moveTile(emptyCol, emptyRow - 1);
                    }
                    break;
                case 3: // move down
                    if (emptyRow < NUM_ROWS - 1) {
                        moveTile(emptyCol, emptyRow + 1);
                    }
                    break;
            }
        }
    }

    private boolean canMoveTile(int col, int row) {

// Play if the tile is adjacent to the empty tile
        return ((col == emptyCol && Math.abs(row - emptyRow) == 1) ||
                (row == emptyRow && Math.abs(col - emptyCol) == 1));
    }

    private void moveTile(int col, int row) {
        // Swap the positions of the clicked tile and the empty tile
        ImageView clickedTile = tiles[col][row];
        clickedTile.setX(emptyCol * TILE_SIZE);
        clickedTile.setY(emptyRow * TILE_SIZE);
        tiles[emptyCol][emptyRow] = clickedTile;
        tiles[col][row] = null;
        emptyCol = col;
        emptyRow = row;
    }

    private boolean isPuzzleSolved() {
        // Play if all the tiles are in their original positions
        for (int col = 0; col < NUM_COLS; col++) {
            for (int row = 0; row < NUM_ROWS; row++) {
                ImageView tile = tiles[col][row];
                if (tile == null || tile.getX() != col * TILE_SIZE || tile.getY() != row * TILE_SIZE) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

