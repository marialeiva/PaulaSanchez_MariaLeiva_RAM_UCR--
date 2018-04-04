/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import domain.Vehicle;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Paula
 */
public class VehicleFile {
    
    public RandomAccessFile randomAccessFile;
    private int regsQuantity;
    private int regSize; 
    private String myFilePath; 

    public VehicleFile(File file) throws IOException {
        
        this.regSize = 79;
        this.myFilePath = file.getPath();
        
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " is an invalid file");

        } else {

            //crear una nueva instancia de RAF
            randomAccessFile = new RandomAccessFile(file, "rw");
                      
            this.regsQuantity = (int)Math.ceil((double)randomAccessFile.length() / (double)this.regSize);
            
        }        
        
    }//end method
    
    //Inserta un vehiculo en determinada posicion
    public boolean putValue(int position, Vehicle vehicleToInsert) throws IOException{

        if (position < 0 && position > this.regsQuantity) {
            System.err.println("1001 - Record position is out of bounds");
            return false;
        } else {
            if (vehicleToInsert.sizeInBytes() > this.regSize) {
                System.err.println("1002 - Record size is out of bounds");
                return false;
            } else {
                //BINGO
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(vehicleToInsert.getName());
                randomAccessFile.writeInt(vehicleToInsert.getYear());
                randomAccessFile.writeFloat(vehicleToInsert.getMileage());
                randomAccessFile.writeBoolean(vehicleToInsert.isAmerican());
                randomAccessFile.writeInt(vehicleToInsert.getSeries());
                return true;
            }
        }

    }//end method    
    
    //Inserta un vehiculo al final del archivo
    public boolean addEndRecord(Vehicle vehicle) throws IOException {
        boolean success = putValue(this.regsQuantity, vehicle);

        if (success) {
            ++this.regsQuantity;
        }
        return success;
    }
    
    //Muestra un vehiculo contenido en el archivo
    public Vehicle getVehicle(int position) throws IOException{
        
        if (position >=0 && position <=this.regsQuantity) {
            
            //colocar el brazo
            randomAccessFile.seek(position *this.regSize);
            
            Vehicle vehicleTemp= new Vehicle();
            vehicleTemp.setName(randomAccessFile.readUTF());
            vehicleTemp.setYear(randomAccessFile.readInt());
            vehicleTemp.setMileage(randomAccessFile.readFloat());
            vehicleTemp.setAmerican(randomAccessFile.readBoolean());
            vehicleTemp.setSeries(randomAccessFile.readInt());
            
            
//            if (vehicleTemp.getName().equals("deleted")) {
//                return null;
//            }else{
                return vehicleTemp;
            
            
        }else{
            System.err.println("1003- position is out of bounds");
            return null;
        }
    }//end method
    
    //Muestra arrayList de vehiculos contenidos en el archivo
    public ArrayList<Vehicle> getVehicleList() throws IOException{
    
        ArrayList<Vehicle> arrayListOfVehicles= new ArrayList<>();
        
        for (int i = 0; i < this.regsQuantity; i++) {
            Vehicle vehicleTemp= this.getVehicle(i);
            
    
            if (vehicleTemp!=null && !vehicleTemp.getName().equalsIgnoreCase("deleted")) {
                arrayListOfVehicles.add(vehicleTemp);
            }
            
        }
        
    return arrayListOfVehicles;
    }//end method    
    
    //Borra un registro de vehiculo segun serie
    public boolean deleteRecord(int serie) throws IOException{
        
        Vehicle vehicleTemp;
        ArrayList<Vehicle> myArrayList= getVehicleList();
        
        
        
        for (int i = 0; i < myArrayList.size() ; i++) {
            vehicleTemp= myArrayList.get(i);

            if (vehicleTemp.getSeries()==serie) {
                System.out.println("Borrar: "+ vehicleTemp.getName()+" i- "+ i);
                vehicleTemp.setName("deleted");
                JOptionPane.showMessageDialog(null, "Vehiculo Borrado");
                return this.putValue(i, vehicleTemp);
            }
            
        }
                         
        
    return false;
    }//end method 
    
    //Realiza la busqueda de un vehiculo por serie y retorna la informacion de este
    public String showSearch(int serie) throws IOException{
    
        Vehicle vehicleTemp;
        ArrayList<Vehicle> myArrayList= getVehicleList();
        String carSearched="";
        
        for (int i = 0; i <= this.regsQuantity; i++) {
            vehicleTemp= myArrayList.get(i);
            
            if (vehicleTemp.getSeries()==serie) {
                carSearched= "Nombre: "+vehicleTemp.getName()+"\nAño: "
                        +vehicleTemp.getYear()+"\nKilometraje: "
                        +vehicleTemp.getMileage()+"\nAmericano: "
                        +vehicleTemp.isAmerican()+"\nSerie: "
                        +vehicleTemp.getSeries()+"\n";
                break;
            }
            
        }
        
    return carSearched;    
    }
    
    //metodo que actualiza los atributos de un vehiculo
    public boolean updateRecord(int serie, Vehicle newVehicle) throws IOException {

        Vehicle vehicleTemp;
        ArrayList<Vehicle> myArrayList = getVehicleList();

        for (int i = 0; i < myArrayList.size(); i++) {
            vehicleTemp = myArrayList.get(i);

            if (vehicleTemp.getSeries() == serie) {
                System.out.println("Actualizar: " + vehicleTemp.getName() + " i- " + i);
                this.putValue(i, newVehicle);
                break;

            }

        }
        return false;
    }
    
}
