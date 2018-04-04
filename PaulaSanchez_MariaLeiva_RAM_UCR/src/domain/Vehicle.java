/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Paula
 */
public class Vehicle {
    private String name;
    private int year;
    private float mileage;
    private boolean american;
    private int series;

    public Vehicle() {
        this.name = "";
        this.year = 0;
        this.mileage = 0;
        this.american = false;
        this.series = 0;
    }

    public Vehicle(String name, int year, float mileage, boolean american, int series) {
        this.name = name;
        this.year = year;
        this.mileage = mileage;
        this.american = american;
        this.series = series;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public boolean isAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "name=" + name + ", year=" + year + ", mileage=" + mileage + ", american=" + american + ", series=" + series + '}';
    }
     
    public int sizeInBytes(){
    return 13 + this.name.length()*2;
    }
    
}
