package com.example.tic_tac_toe_fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TicTacToeController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void switchToMenu(ActionEvent e) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("title_view.fxml")));
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button przycisk1;
    @FXML
    private Button przycisk2;
    @FXML
    private Button przycisk3;
    @FXML
    private Button przycisk4;
    @FXML
    private Button przycisk5;
    @FXML
    private Button przycisk6;
    @FXML
    private Button przycisk7;
    @FXML
    private Button przycisk8;
    @FXML
    private Button przycisk9;

    @FXML
    private Button przyciskPowrotu;

    @FXML
    private Label tekst;

    @FXML
    private Line linia1;

    @FXML
    private Line linia2;

    @FXML
    private Line linia3;

    @FXML
    private Line linia4;

    @FXML
    private Line linia5;

    @FXML
    private Line linia6;

    @FXML
    private Line linia7;

    @FXML
    private Line linia8;

    private int ktoryGracz = 0;

    private int ilerund = 0;

    ArrayList<Button> przyciski;

    public void ustalSymbol(Button przycisk){
        if(ktoryGracz == 0){
            przycisk.setText("X");
            tekst.setText("Kolej kółka!");
            ktoryGracz = 1;
        }
        else {
            przycisk.setText("O");
            tekst.setText("Kolej krzyżyka!");
            ktoryGracz = 0;
        }
    }

    public void ustawPrzycisk(Button przycisk){
        przycisk.setOnMouseClicked(mouseEvent -> {
            ustalSymbol(przycisk);
            przycisk.setDisable(true);
            ilerund++;
            czyKoniec();
        });
    }

    public void initialize(){
        przyciskPowrotu.setVisible(false);
        niePokazujLinii();
        przyciski = new ArrayList<>(Arrays.asList(przycisk1,przycisk2,przycisk3,przycisk4,przycisk5,przycisk6,przycisk7,przycisk8,przycisk9));

        przyciski.forEach(przycisk -> {
            ustawPrzycisk(przycisk);
            przycisk.setFocusTraversable(false);
        });
    }

    public void czyKoniec(){
        for(int i=1; i < 9;i++){
            String linia = switch (i){
                case 1 -> przycisk7.getText()+przycisk8.getText()+przycisk9.getText();
                case 2 -> przycisk4.getText()+przycisk5.getText()+przycisk6.getText();
                case 3 -> przycisk1.getText()+przycisk2.getText()+przycisk3.getText();
                case 4 -> przycisk7.getText()+przycisk4.getText()+przycisk1.getText();
                case 5 -> przycisk8.getText()+przycisk5.getText()+przycisk2.getText();
                case 6 -> przycisk9.getText()+przycisk6.getText()+przycisk3.getText();
                case 7 -> przycisk7.getText()+przycisk5.getText()+przycisk3.getText();
                case 8 -> przycisk9.getText()+przycisk5.getText()+przycisk1.getText();
                default -> null;
            };

            if(linia.equals("XXX")){
                przyciski.forEach(przycisk -> {
                    ustawPrzycisk(przycisk);
                    przycisk.setDisable(true);
                });
                rysujLinie(i);
                tekst.setText("Wygrał krzyżyk!");
                przyciskPowrotu.setVisible(true);
            }
            else
                if(linia.equals("OOO")){
                    przyciski.forEach(przycisk -> {
                        ustawPrzycisk(przycisk);
                        przycisk.setDisable(true);
                    });
                    rysujLinie(i);
                    tekst.setText("Wygrało kółko!");
                    przyciskPowrotu.setVisible(true);
            }
            else
                if (ilerund>=9){
                tekst.setText("REMIS!");
                przyciskPowrotu.setVisible(true);
                }
        }
    }

    public void niePokazujLinii(){
        linia1.setVisible(false);
        linia2.setVisible(false);
        linia3.setVisible(false);
        linia4.setVisible(false);
        linia5.setVisible(false);
        linia6.setVisible(false);
        linia7.setVisible(false);
        linia8.setVisible(false);
    }
    public void rysujLinie(int i){
        switch (i){
            case 1 -> linia1.setVisible(true);
            case 2 -> linia2.setVisible(true);
            case 3 -> linia3.setVisible(true);
            case 4 -> linia4.setVisible(true);
            case 5 -> linia5.setVisible(true);
            case 6 -> linia6.setVisible(true);
            case 7 -> linia7.setVisible(true);
            case 8 -> linia8.setVisible(true);
        }
    }
}
