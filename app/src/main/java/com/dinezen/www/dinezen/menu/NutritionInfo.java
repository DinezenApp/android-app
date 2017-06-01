package com.dinezen.www.dinezen.menu;

/**
 * Stores nutrition information for MenuItem
 */

public class NutritionInfo {
    private enum PortionType{EACH, OUNCE, GRAM, SLICE}

    /**
     * WARNING: CAN BE null!!
     */
    private int calories, fatcalories;
    /**
     * WARNING: CAN BE null!!
     */
    private double totalfat, carb, satfat, fiber, transfat,
            sugar, cholesterol, protein, sodium;
    private int portionNum;
    private String portionType;
    private String[] allergens;

    public NutritionInfo(){}

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFatcalories() {
        return fatcalories;
    }

    public void setFatcalories(int fatcalories) {
        this.fatcalories = fatcalories;
    }

    public double getTotalfat() {
        return totalfat;
    }

    public void setTotalfat(double totalfat) {
        this.totalfat = totalfat;
    }

    public double getCarb() {
        return carb;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getSatfat() {
        return satfat;
    }

    public void setSatfat(double satfat) {
        this.satfat = satfat;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getTransfat() {
        return transfat;
    }

    public void setTransfat(double transfat) {
        this.transfat = transfat;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public int getPortionNum() {
        return portionNum;
    }

    public void setPortionNum(int portionNum) {
        this.portionNum = portionNum;
    }

    public String getPortionType() {
        return portionType;
    }

    public void setPortionType(String portionType) {
        this.portionType = portionType;
    }

    public String[] getAllergens() {
        return allergens;
    }

    public void setAllergens(String[] allergens) {
        this.allergens = allergens;
    }

    @Override
    public String toString() {
        String allergensString = "";
        for(String a : allergens) {
            allergensString += a + " ";
        }
        return "Nutrition Facts\tTotal Fat " + totalfat + "\tTot. Carb. " + carb +
                "\nServing Size " + portionNum + " " + portionType + "\tSat. Fat " + satfat + "\tDietary Fiber " + fiber +
                "\nCalories " + calories + "\tTrans Fat " + transfat + "\tSugars " + sugar +
                "\nCalories from Fat " + fatcalories + "\tCholesterol " + cholesterol + "\tProtein " + protein +
                "\n\t\tSodium " + sodium +
                "\nAllergens: " + allergensString;
    }
}
