/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

/**
 *
 * @author Paula
 */
public class Window {
    
    VBox vbox = new VBox();
    windowsInterfaces wi;
    
    public Scene menu(){
        wi = new windowsInterfaces();
        
        Scene scene = new Scene(new VBox(), 600, 300);
        vbox.setAlignment(Pos.TOP_RIGHT);
        vbox.setPadding(new Insets(10, 10, 0, 10));
        
        MenuBar menubar = new MenuBar();
        
        //MenuBar System
        
        Menu insert = new Menu("Insertar");
        MenuItem carInsert = new MenuItem("Vehiculo");
        carInsert.setOnAction((event) -> {
            vbox.getChildren().clear();
            try {
                vbox.getChildren().addAll(wi.carInsert());
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Menu update = new Menu("Actualizar");
        MenuItem carUpdate = new MenuItem("Datos vehiculo");
        carUpdate.setOnAction((event) -> {
            try {
                vbox.getChildren().clear();
                vbox.getChildren().addAll(wi.carUpdate());
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Menu delete = new Menu("Eliminar");
        MenuItem carDelete = new MenuItem("Vehiculo");
        carDelete.setOnAction((event) -> {
            vbox.getChildren().clear();
            try {
                vbox.getChildren().addAll(wi.carDeleted());
            } catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Menu list = new Menu("Listado");
        MenuItem carList = new MenuItem("Lista de vehiculos");
        carList.setOnAction((event) -> {
            vbox.getChildren().clear();
            vbox.getChildren().addAll(wi.showTableView());
        });
        
        
        insert.getItems().add(carInsert);
        update.getItems().add(carUpdate);
        delete.getItems().add(carDelete);
        list.getItems().add(carList);
        menubar.getMenus().addAll(insert, update, delete, list);
        
        
        ((VBox) scene.getRoot()).getChildren().addAll(menubar, vbox);
        
    return scene;
    
    }//fin scene
    
    
    
}
