/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphic;

import domain.Vehicle;
import file.VehicleFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Paula
 */
public class windowsInterfaces {
    File file= new File("./vehicle.dat");
    
    //Interfaz para ingresar un auto
    public GridPane carInsert() throws IOException{
        GridPane gridpane = new GridPane();
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.TOP_LEFT);
        
        Label lblname = new Label("Nombre");
        gridpane.add(lblname, 0, 0);
        Label lblyear = new Label("Año");
        gridpane.add(lblyear, 0, 3);
        Label lblmileage = new Label("Kilometraje");
        gridpane.add(lblmileage, 0, 6);
        Label lblamerican = new Label("Americano");
        gridpane.add(lblamerican, 0, 9);
        Label lblserie = new Label("Serie");
        gridpane.add(lblserie, 0, 12);
        
        TextField txtname = new TextField();
        gridpane.add(txtname, 3, 0);
        TextField txtyear = new TextField();
        gridpane.add(txtyear, 3, 3);
        TextField txtmileage = new TextField();
        gridpane.add(txtmileage, 3, 6);
        TextField txtserie = new TextField();
        gridpane.add(txtserie, 3, 12);
        
        ToggleGroup group = new ToggleGroup();
        
        RadioButton rbyes = new RadioButton("Si");
        rbyes.setToggleGroup(group);
        gridpane.add(rbyes, 3, 9);
                
        RadioButton rbno = new RadioButton("No");
        rbno.setToggleGroup(group);
        gridpane.add(rbno, 4, 9);
        
        Button btninsert = new Button("Insertar");
        gridpane.add(btninsert, 3, 14);
        
        btninsert.setOnAction((event) -> {
            
            try {

                String name = txtname.getText();
                float mileage = Float.parseFloat(txtmileage.getText());
                int year = Integer.parseInt(txtyear.getText());
                int series = Integer.parseInt(txtserie.getText());

                boolean american = false;

                if (rbyes.isSelected()) {
                    american = true;

                }

                Vehicle vehicleToInsert = new Vehicle(name, year, mileage, american, series);

                VehicleFile vehicleFile = new VehicleFile(file);
                vehicleFile.addEndRecord(vehicleToInsert);
                JOptionPane.showMessageDialog(null, "Ingresado");
                txtname.clear();
                txtyear.clear();
                txtserie.clear();
                txtmileage.clear();
                rbyes.setSelected(false);
                rbno.setSelected(false);
            } catch (IOException ex) {
                Logger.getLogger(windowsInterfaces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException nfe) {
                txtname.clear();
                txtyear.clear();
                txtserie.clear();
                txtmileage.clear();
                JOptionPane.showMessageDialog(null,"Ingresar correctamente los datos", "", JOptionPane.WARNING_MESSAGE);

            }

        });

        return gridpane;
    }//carInsert
    
    //Interfaz para actualizar un vehiculo
    public GridPane carUpdate() throws IOException{
        GridPane gridpane = new GridPane();
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.TOP_LEFT);
        
        Label lblsearch = new Label("Buscar serie del vehiculo");
        gridpane.add(lblsearch, 0, 0);
        Label lblname = new Label("Nombre");
        gridpane.add(lblname, 0, 2);
        Label lblyear = new Label("Año");
        gridpane.add(lblyear, 0, 4);
        Label lblmileage = new Label("Kilometraje");
        gridpane.add(lblmileage, 0, 6);
        Label lblamerican = new Label("Americano");
        gridpane.add(lblamerican, 0, 9);
        Label lblserie = new Label("Serie");
        gridpane.add(lblserie, 0, 12);
        
        
        TextField txtsearch = new TextField();
        gridpane.add(txtsearch, 3, 0);
        TextField txtname = new TextField();
        gridpane.add(txtname, 3, 2);
        TextField txtyear = new TextField();
        gridpane.add(txtyear, 3, 4);
        TextField txtmileage = new TextField();
        txtmileage.setEditable(false);
        gridpane.add(txtmileage, 3, 6);
        TextField txtserie = new TextField();
        txtserie.setEditable(false);
        gridpane.add(txtserie, 3, 12);
        
        txtsearch.setTooltip(new Tooltip("Enter para buscar"));
        
        txtsearch.setOnAction((event) -> {
            try {
                VehicleFile vf= new VehicleFile(file);
                ArrayList<Vehicle> vehicleArrays=  vf.getVehicleList();
                
                for (int i = 0; i < vehicleArrays.size(); i++) {
                    if(!vehicleArrays.get(i).getName().equalsIgnoreCase("deleted")){
                    if(vehicleArrays.get(i).getSeries()==Integer.parseInt(txtsearch.getText())){
                        txtname.setText(vehicleArrays.get(i).getName());
                        txtyear.setText(""+vehicleArrays.get(i).getYear());
                        txtmileage.setText(""+vehicleArrays.get(i).getMileage());
                        txtserie.setText(""+vehicleArrays.get(i).getSeries());
          
                    }//end if
                    }//if
                    else {
                        JOptionPane.showMessageDialog(null, "El vehiculo no existe");
                        break;
                    }
                }//end for
            } catch (IOException ex) {
                Logger.getLogger(windowsInterfaces.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException nfe){
                txtsearch.clear();
                JOptionPane.showMessageDialog(null,"Ingresar correctamente los datos", "", JOptionPane.WARNING_MESSAGE);
            }
        });
       
        ToggleGroup group = new ToggleGroup();
        
        RadioButton rbyes = new RadioButton("Si");
        rbyes.setToggleGroup(group);
        gridpane.add(rbyes, 3, 9);
        
        RadioButton rbno = new RadioButton("No");
        rbno.setToggleGroup(group);
        gridpane.add(rbno, 4, 9);
        
        Button btnchanges= new Button("Guardar cambios");
        gridpane.add(btnchanges, 3, 14);
        
        btnchanges.setOnAction((event) -> {
            try {
                
                boolean american = false;

                if (rbyes.isSelected()) {
                    american = true;

                }
                Vehicle updateVehicle= new Vehicle(txtname.getText(), Integer.parseInt(txtyear.getText()),
                        Float.parseFloat(txtmileage.getText()), american, Integer.parseInt(txtserie.getText()));
                
                VehicleFile vf= new VehicleFile(file);
                vf.updateRecord(Integer.parseInt(txtsearch.getText()), updateVehicle);
                JOptionPane.showMessageDialog(null,"Cambios gurdados");
                txtsearch.clear();
                txtname.clear();
                txtyear.clear();
                txtserie.clear();
                txtmileage.clear();
                rbno.setSelected(false);
                rbyes.setSelected(false);
            } catch (IOException ex) {
                Logger.getLogger(windowsInterfaces.class.getName()).log(Level.SEVERE, null, ex);
            }  catch(NumberFormatException nfe){
                txtname.clear();
                txtyear.clear();
                txtserie.clear();
                txtmileage.clear();
                JOptionPane.showMessageDialog(null,"Ingresar correctamente los datos", "", JOptionPane.WARNING_MESSAGE);
            }          
        });
        
        return gridpane;
    }//fin carupdate
    
    //Interfaz para borrar un vehiculo
    public GridPane carDeleted() throws IOException{
        
        GridPane gridpane = new GridPane();
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        gridpane.setAlignment(Pos.TOP_LEFT);
        
        Label lblsearch = new Label("Buscar serie del vehiculo a eliminar");
        gridpane.add(lblsearch, 0, 0);
        
        TextField txtsearch = new TextField();
        gridpane.add(txtsearch, 1, 0);
        
        TextArea details = new TextArea();
        gridpane.add(details, 0, 3, 2, 1);
        
        txtsearch.setTooltip(new Tooltip("Enter para buscar"));
        
        txtsearch.setOnAction((event) -> {
            VehicleFile vehicleFile;
            try {
                vehicleFile = new VehicleFile(file);
                details.setText(vehicleFile.showSearch(Integer.parseInt(txtsearch.getText())));
                
            } catch (IOException ex) {
                Logger.getLogger(windowsInterfaces.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NumberFormatException nfe){
            txtsearch.clear();
            JOptionPane.showMessageDialog(null,"Ingresar correctamente los datos", "", JOptionPane.WARNING_MESSAGE);
            }
        });
                        
        Button btndelete= new Button("Borrar");
        gridpane.add(btndelete, 0, 4);
        btndelete.setOnAction((event) -> {
            VehicleFile vf;
            try {
                vf = new VehicleFile(file);
                vf.deleteRecord( Integer.parseInt(txtsearch.getText()));
                txtsearch.clear();
                details.clear();
            } catch (IOException ex) {
                Logger.getLogger(windowsInterfaces.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NumberFormatException nfe){
            
            }
        });

          
        return gridpane;
        
    }//fin carDeleted
    
    //Interfaz que implementa TableView para mostrar los datos de vehiculos registrados.
    public VBox showTableView() {

        TableView<Vehicle> table = new TableView<>();

        TableColumn nameColumn = new TableColumn("Nombre");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn yearColumn = new TableColumn("Año");
        yearColumn.setCellValueFactory(new PropertyValueFactory("year"));

        TableColumn mileageColumn = new TableColumn("Kilometraje");
        mileageColumn.setCellValueFactory(new PropertyValueFactory("mileage"));

        TableColumn americanColumn = new TableColumn("Americano");
        americanColumn.setCellValueFactory(new PropertyValueFactory("american"));

        TableColumn seriesColumn = new TableColumn("Serie");
        seriesColumn.setCellValueFactory(new PropertyValueFactory("series"));
        
        Button btnList= new Button("Mostrar Vehiculos");
        btnList.setOnAction(e->{
            
            try {
                VehicleFile vehicleFile = new VehicleFile(file);
                ObservableList<Vehicle> data= FXCollections.observableArrayList(vehicleFile.getVehicleList());
            table.setItems(data);
            } catch (IOException ex) {
                Logger.getLogger(windowsInterfaces.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        });       
        
        table.getColumns().addAll(mileageColumn, nameColumn, yearColumn, americanColumn, seriesColumn);
        table.setEditable(false);
        
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, btnList);

        return vbox;
    } 
    
    
    
}
