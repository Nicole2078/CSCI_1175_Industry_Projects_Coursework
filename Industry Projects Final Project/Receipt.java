import java.io.Serializable;

/*
Author: Nicole Hackler
Date: 1/23/2024

Class for the Receipts with getter and setter methods.
*/
public class Receipt implements Serializable {
    private double housing = 0.0;
    private double utilities = 0.0;
    private double transportGas = 0.0;
    private double groceriesHouseItems = 0.0;
    private double internetCellphone = 0.0;
    private double entertainment = 0.0;
    private double birthHoliday = 0.0;
    private double healthCare = 0.0;

    //default constructor
    Receipt(){
        this(0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0);
    }

    //constructor with set parameters
    Receipt( double housing, double utilities, double transportGas, double groceriesHouseItems,
             double internetCellphone, double entertainment, double birthHoliday, double healthCare){
        this.housing = housing;
        this.utilities = utilities;
        this.transportGas = transportGas;
        this.groceriesHouseItems = groceriesHouseItems;
        this.internetCellphone = internetCellphone;
        this.entertainment = entertainment;
        this.birthHoliday = birthHoliday;
        this.healthCare = healthCare;
    }
    //Method for returning the Receipt total
    public double getReceiptTotal(){
        return housing + utilities + transportGas + groceriesHouseItems + internetCellphone +
                entertainment + birthHoliday + healthCare;
    }

    public double getHousing() {
        return housing;
    }

    public void setHousing(double housing) {
        this.housing = housing;
    }

    public double getUtilities() {
        return utilities;
    }

    public void setUtilities(double utilities) {
        this.utilities = utilities;
    }

    public double getTransportGas() {
        return transportGas;
    }

    public void setTransportGas(double transportGas) {
        this.transportGas = transportGas;
    }

    public double getGroceriesHouseItems() {
        return groceriesHouseItems;
    }

    public void setGroceriesHouseItems(double groceriesHouseItems) {
        this.groceriesHouseItems = groceriesHouseItems;
    }

    public double getInternetCellphone() {
        return internetCellphone;
    }

    public void setInternetCellphone(double internetCellphone) {
        this.internetCellphone = internetCellphone;
    }

    public double getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(double entertainment) {
        this.entertainment = entertainment;
    }

    public double getBirthHoliday() {
        return birthHoliday;
    }

    public void setBirthHoliday(double birthHoliday) {
        this.birthHoliday = birthHoliday;
    }

    public double getHealthCare() {
        return healthCare;
    }

    public void setHealthCare(double healthCare) {
        this.healthCare = healthCare;
    }
}

