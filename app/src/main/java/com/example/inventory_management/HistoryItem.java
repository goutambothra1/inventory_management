package com.example.inventory_management;

public class HistoryItem {
    private int itemId;
    private String itemName;
    private String department;
    private int quantity;
    private String date;

    public HistoryItem() {
    }


    public HistoryItem(String itemName, int itemId, String department, int quantity, String date) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.department = department;
        this.quantity = quantity;
        this.date = date;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

