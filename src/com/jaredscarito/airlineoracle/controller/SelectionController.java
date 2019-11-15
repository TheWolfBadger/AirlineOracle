package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class SelectionController {
    private Main main;

    public SelectionController(Main main) {
        this.main = main;
    }

    public void start() {
        GridPane mainPanel = new GridPane();
        mainPanel.setId("main");

        GridPane grid = new GridPane();
        grid.setId("SelectionGrid");

        Image deltaLogo = new Image("com/jaredscarito/airlineoracle/view/delta-airlines-logo.png");
        ImageView img = new ImageView(deltaLogo);
        img.setId("logo");
        img.setFitHeight(100);
        img.setFitWidth(240);
        grid.add(img, 0, 0);

        DatePicker picker = new DatePicker();
        picker.setId("DatePick");
        picker.setDayCellFactory(dp -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        picker.getEditor().setDisable(true);
        picker.getEditor().setStyle("-fx-opacity: 1.0;");
        picker.getEditor().setText("Select Departure Date");
        grid.add(picker, 0, 1);

        ComboBox passengers = new ComboBox();
        passengers.setPromptText("Select Passenger Amount");
        passengers.setId("PassengerSelect");
        passengers.getItems().add("1 Passenger");
        passengers.getItems().add("2 Passenger");
        passengers.getItems().add("3 Passenger");
        passengers.getItems().add("4 Passenger");
        passengers.getItems().add("5 Passenger");
        passengers.getItems().add("6 Passenger");
        passengers.getItems().add("7 Passenger");
        passengers.getItems().add("8 Passenger");
        passengers.getItems().add("9 Passenger");
        grid.add(passengers, 0, 2);

        Button submit = new Button("Submit");
        submit.setId("SubmitButton");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate val = picker.getValue();
                Object passengerVal = passengers.getValue();
                submit.setDisable(true);
                if (val == null) {
                    // They didn't put a date, bring an error
                    //TODO
                }
                if (passengerVal == null) {
                    // They didn't pick number of passengers, bring an error
                    //TODO
                }
                if (val !=null && passengerVal != null) {
                    // Move onto next screen
                    main.getLoadingControl().start();
                }
                submit.setDisable(false);
            }
        });
        grid.add(submit, 0, 3);

        grid.setVgap(40);

        mainPanel.add(grid, 0, 0);
        mainPanel.setAlignment(Pos.CENTER);
        double width, height;
        width = main.getScreenWidth();
        height = main.getScreenHeight();
        main.getPrimaryStage().setScene(new Scene(mainPanel, width, height));
        main.getPrimaryStage().getScene().getStylesheets().add("com/jaredscarito/airlineoracle/view/style.css");
        main.getPrimaryStage().show();
    }
}