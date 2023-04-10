package com.example.intropage;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class IntroPage extends Application {
    private static MediaPlayer mediaPlayer = MusicPlayer.play();

    public void start(Stage primaryStage) {

        Image gameLogo = new Image("logo.png");
        ImageView logoView = new ImageView(gameLogo);
        logoView.setFitHeight(200);
        logoView.setFitWidth(200);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(5), logoView);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.setCycleCount(Timeline.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();


        //Play Button
        Button playButton = new Button("PLAY");
        playButton.setPrefSize(Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        playButton.setFont(Const.BUTTON_FONT);
        ScaleTransition scalePlay = new ScaleTransition(Duration.seconds(5), playButton);
        scalePlay.setToX(1.1);
        scalePlay.setToY(1.1);
        scalePlay.setCycleCount(Timeline.INDEFINITE);
        scalePlay.setAutoReverse(true);
        scalePlay.play();

        //Level Button
        Button levelButton = new Button("LEVEL");
        levelButton.setPrefSize(Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        levelButton.setFont(Const.BUTTON_FONT);
        ScaleTransition scaleLevel = new ScaleTransition(Duration.seconds(5), levelButton);
        scaleLevel.setToX(1.1);
        scaleLevel.setToY(1.1);
        scaleLevel.setCycleCount(Timeline.INDEFINITE);
        scaleLevel.setAutoReverse(true);
        scaleLevel.play();


        //Settings Button
        Button music = new Button("Music");
        music.setPrefSize(Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        music.setFont(Const.BUTTON_FONT);
        music.setStyle(Const.BUTTON_STYLE);
        playButton.setStyle(Const.BUTTON_STYLE);
        levelButton.setStyle(Const.BUTTON_STYLE);
        ScaleTransition scaleMusic = new ScaleTransition(Duration.seconds(5), music);
        scaleMusic.setToX(1.1);
        scaleMusic.setToY(1.1);
        scaleMusic.setCycleCount(Timeline.INDEFINITE);
        scaleMusic.setAutoReverse(true);
        scaleMusic.play();



        //Adding vBox
        VBox buttonBox = new VBox(20, playButton, levelButton, music);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, logoView, buttonBox);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Intro Page");


        Stage levelSelectionStage = new Stage();


        //Back Button
        Image backButtonImage = new Image("back.png");
        ImageView backButtonView = new ImageView(backButtonImage);
        backButtonView.setFitHeight(30);
        backButtonView.setFitWidth(30);
        ScaleTransition scaleBack = new ScaleTransition(Duration.seconds(5), backButtonView);
        scaleBack.setToX(1.1);
        scaleBack.setToY(1.1);
        scaleBack.setCycleCount(Timeline.INDEFINITE);
        scaleBack.setAutoReverse(true);
        scaleBack.play();

        Button backButton = new Button("", backButtonView);
        backButton.setPrefSize(50, 50);

        //Easy Button
        Button easyButton = new Button("EASY");
        easyButton.setPrefSize(Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        easyButton.setFont(Const.BUTTON_FONT);
        ScaleTransition scaleEasy = new ScaleTransition(Duration.seconds(5), easyButton);
        scaleEasy.setToX(1.1);
        scaleEasy.setToY(1.1);
        scaleEasy.setCycleCount(Timeline.INDEFINITE);
        scaleEasy.setAutoReverse(true);
        scaleEasy.play();

        //Medium Button
        Button mediumButton = new Button("MEDIUM");
        mediumButton.setPrefSize(Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        mediumButton.setFont(Const.BUTTON_FONT);
        ScaleTransition scaleMedium = new ScaleTransition(Duration.seconds(5), mediumButton);
        scaleMedium.setToX(1.1);
        scaleMedium.setToY(1.1);
        scaleMedium.setCycleCount(Timeline.INDEFINITE);
        scaleMedium.setAutoReverse(true);
        scaleMedium.play();

        //Hard Button
        Button hardButton = new Button("HARD");
        hardButton.setPrefSize(Const.BUTTON_WIDTH, Const.BUTTON_HEIGHT);
        hardButton.setFont(Const.BUTTON_FONT);
        ScaleTransition scaleHard = new ScaleTransition(Duration.seconds(5), hardButton);
        scaleHard.setToX(1.1);
        scaleHard.setToY(1.1);
        scaleHard.setCycleCount(Timeline.INDEFINITE);
        scaleHard.setAutoReverse(true);
        scaleHard.play();

        easyButton.setStyle(Const.BUTTON_STYLE);
        mediumButton.setStyle(Const.BUTTON_STYLE);
        hardButton.setStyle(Const.BUTTON_STYLE);
        backButton.setStyle(Const.BUTTON_STYLE);

        primaryStage.setResizable(false);

        Label levelSelectionLabel = new Label("Choose a Difficulty Level");


        //Adding HBox
        HBox topBox = new HBox(20, backButton, levelSelectionLabel);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(20, 20, 20, 20));

        //Adding VBox
        VBox levelButtonBox = new VBox(20, easyButton, mediumButton, hardButton);
        levelButtonBox.setAlignment(Pos.CENTER);

        VBox levelSelectionRoot = new VBox(20, topBox, levelButtonBox);


        Scene levelSelectionScene = new Scene(levelSelectionRoot, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
        levelSelectionRoot.setBackground(Background.fill(Color.SADDLEBROWN));
        levelSelectionStage.setScene(levelSelectionScene);
        levelSelectionStage.setTitle("Level Selection");

        primaryStage.show();

        //Adding Action to Buttons
        levelButton.setOnAction(event -> levelSelectionStage.show());
        backButton.setOnAction(event -> levelSelectionStage.close());
        playButton.setOnAction(e->{
            Play puzzle = new Play();
            try {
                puzzle.start(primaryStage);
                // close the current window
                ((Stage) easyButton.getScene().getWindow()).close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        easyButton.setOnAction(e -> {
            Easy puzzle = new Easy();
            try {
                puzzle.start(primaryStage);
                // close the current window
                ((Stage) easyButton.getScene().getWindow()).close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        mediumButton.setOnAction(e->{
            Medium puzzle = new Medium();
            try {
                puzzle.start(primaryStage);
                // close the current window
                ((Stage) easyButton.getScene().getWindow()).close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        hardButton.setOnAction(e->{
            Hard puzzle = new Hard();
            try {
                puzzle.start(primaryStage);
                // close the current window
                ((Stage) easyButton.getScene().getWindow()).close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        music.setOnAction(e -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                music.setText("Music Off");
            } else {
                mediaPlayer.play();
                music.setText("Music On");
            }
        });




        //Adding Color
        root.setBackground(Background.fill(Color.SADDLEBROWN));
        backButton.setBackground(Background.fill(Color.GOLD));
        easyButton.setBackground(Background.fill(Color.GOLD));
        mediumButton.setBackground(Background.fill(Color.GOLD));
        hardButton.setBackground(Background.fill(Color.GOLD));
        levelButton.setBackground(Background.fill(Color.GOLD));
        playButton.setBackground(Background.fill(Color.GOLD));
        music.setBackground(Background.fill(Color.GOLD));
    }

    public static void main(String[] args) {
        launch(args);
    }


}
