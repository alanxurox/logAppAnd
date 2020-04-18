package com.example.logappand;
import java.lang.Math;

public class Log {
    private double largeDiameter, smallDiameter, length, moisture;
    private String species;


    public Log() {
        this.largeDiameter = 0;
        this.smallDiameter = 0;
        this.length = 0;
        this.moisture = 0;
        this.species = "";
    }

    public Log(double largeDiameter, double smallDiameter, double length, double moisture, String species) {
        this.largeDiameter = largeDiameter;
        this.smallDiameter = smallDiameter;
        this.length = length;
        this.moisture = moisture;
        this.species = species;
    }

    public double getLargeDiameter() {
        return largeDiameter;
    }

    public void setLargeDiameter(double largeDiameter) {
        this.largeDiameter = largeDiameter;
    }

    public double getSmallDiameter() {
        return smallDiameter;
    }

    public void setSmallDiameter(double smallDiameter) {
        this.smallDiameter = smallDiameter;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getMoisture() {
        return moisture;
    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double calculateVolume() {
        return (3.14 * length * ((largeDiameter * largeDiameter + smallDiameter * smallDiameter)/2));
    }


    @Override
    public String toString() {
        return "Log{" +
                "largeDiameter=" + largeDiameter +
                ", smallDiameter=" + smallDiameter +
                ", length=" + length +
                ", moisture=" + moisture +
                ", species='" + species + '\'' +
                '}';
    }
}
