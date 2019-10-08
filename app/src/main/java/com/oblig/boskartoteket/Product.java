package com.oblig.boskartoteket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Product {

    private String name;
    private int id;
    private double price;

    private static final String TABLE_NAME = "Product";
    private static final String KOL_NAME = "name";
    private static final String KOL_ID = "id";
    private static final String KOL_PRICE = "price";


    public Product(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public Product(JSONObject jsonObject) {
        this.id = jsonObject.optInt(KOL_ID);
        this.name = jsonObject.optString(KOL_NAME);
        this.price = jsonObject.optDouble(KOL_PRICE);
    }

    public static ArrayList<Product> addToProductList(String jsonProduct) throws JSONException, NullPointerException{
        ArrayList<Product> userList = new ArrayList<>();

        JSONObject jsonData = new JSONObject(jsonProduct);

        JSONArray productTable = jsonData.optJSONArray(TABLE_NAME);

        for(int i = 0; i < productTable.length(); i++) {
            JSONObject productObject = (JSONObject) productTable.get(i);
            Product product = new Product(productObject);
            userList.add(product);
        }

        return userList;

    }

    public JSONObject toJSONObject(){
        JSONObject jsonProduct = new JSONObject();
        try {
            jsonProduct.put(KOL_ID, this.id);
            jsonProduct.put(KOL_NAME, this.name);
            jsonProduct.put(KOL_PRICE, this.price);

        } catch (JSONException e) {return null;}
        return jsonProduct;
    }


}
