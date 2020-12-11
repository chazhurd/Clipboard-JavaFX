/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package churdhw3;


import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author vanil
 */
public class CHurdHW3 extends Application {
    Label mStatus = new Label ("Everything is Copacetic");
    Panel mUpperSP = new Panel();
    Panel mLowerSP = new Panel();
    RadioMenuItem mJavafxMenuItem;
    RadioMenuItem mDarkulaMenuItem;
    ToolBar mToolBar;
    MenuBar mMenuBar;
    
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane root = new BorderPane();
        VBox vb = new VBox();
        vb.getChildren().addAll(mUpperSP, mLowerSP);
        
        //onAbout();
        root.setTop(buildMenuBar());
        root.setCenter(vb);
        mToolBar = new ToolBar(mStatus);
        root.setBottom(mToolBar);
        
        
        Scene scene = new Scene(root, 400, 350);
        
        primaryStage.setTitle("System Clipboard and CSS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("About");
        alert.setHeaderText("Chaz C Hurd, CSCD 370 Homework 3, Wtr 2020");
        alert.showAndWait();
    }

    private MenuBar buildMenuBar() {
        mMenuBar = new MenuBar();
        //file menu with quit
        Menu fileMenu = new Menu("_File");
        MenuItem quitMenuItem = new MenuItem("_Quit");
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().add(quitMenuItem);
        
        // next is a help menu
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        
        //new view meny
        Menu viewMenu = new Menu("_View");
        mJavafxMenuItem = new RadioMenuItem("JavaFX");
        mDarkulaMenuItem = new RadioMenuItem("Darkula");
        mJavafxMenuItem.setOnAction(actionEvent -> onFX());
        mDarkulaMenuItem.setOnAction(actionEvent -> onDark());
        viewMenu.getItems().addAll(mJavafxMenuItem, mDarkulaMenuItem);
        
        mMenuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu);
        
        return mMenuBar;
    }

    private void setStatus(String input) {
        
       mStatus.setText(input);
    }

 

    private void onFX() {
         mJavafxMenuItem.setSelected(true);
         mDarkulaMenuItem.setSelected(false);
         mToolBar.setStyle("");
         mMenuBar.setStyle("");
         
    }

    private void onDark() {
        mDarkulaMenuItem.setSelected(true);
        mJavafxMenuItem.setSelected(false);
        mToolBar.setStyle("-fx-background-color:MIDNIGHTBLUE");
        mMenuBar.setStyle("-fx-background-color:MIDNIGHTBLUE");

    }

}
