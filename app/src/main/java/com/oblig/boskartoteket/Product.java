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

    public static ArrayList<User> addToUserList(String jsonUser) throws JSONException, NullPointerException{
        ArrayList<User> userList = new ArrayList<>();

        JSONObject jsonData = new JSONObject(jsonUser);

        JSONArray userTable = jsonData.optJSONArray(TABLE_NAME);

        for(int i = 0; i < userTable.length(); i++) {
            JSONObject userObject = (JSONObject) userTable.get(i);
            User user = new User(userObject);
            userList.add(user);
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
