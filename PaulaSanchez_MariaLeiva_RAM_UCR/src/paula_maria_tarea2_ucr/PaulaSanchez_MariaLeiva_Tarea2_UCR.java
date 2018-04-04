/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paula_maria_tarea2_ucr;


import javafx.application.Application;
import interfaceGraphic.Window;
import javafx.stage.Stage;

/**
 *
 * @author Paula
 */
public class PaulaSanchez_MariaLeiva_Tarea2_UCR extends Application {
      Window window;
    
    @Override
    public void start(Stage primaryStage) {
        window = new Window();
        primaryStage.setTitle("Tarea 2");
        primaryStage.setScene(window.menu());
        primaryStage.show();
        
    }

   
    public static void main(String[] args) {
        launch(args);
 
    }
    
}
