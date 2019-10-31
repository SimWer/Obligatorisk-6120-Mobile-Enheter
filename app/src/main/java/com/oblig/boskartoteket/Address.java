package com.oblig.boskartoteket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Address {

    private String name, house_number, letter, zip_code, postal_location, route_id;
    private int id;

    private static final String TABLE_NAME = "Address";
    private static final String KOL_ID = "ID";
    private static final String KOL_NAMEL = "name";
    private static final String KOL_HOUSE_NUMBER = "house_number";
    private static final String KOL_LETTER = "letter";
    private static final String KOL_ZIP_CODE = "zip_code";
    private static final String KOL_POSTAL_LOCATION = "postal_location";
    private static final String KOL_ROUTE_ID = "route_id";


    public Address(String name, String house_number, String letter, String zip_code, String postal_location, String route_id) {
        this.name = name;
        this.house_number = house_number;
        this.letter = letter;
        this.zip_code = zip_code;
        this.postal_location = postal_location;
        this.route_id = route_id;
    }

    public Address(JSONObject jsonAddress) {
        this.id = jsonAddress.optInt(KOL_ID);
        this.name = jsonAddress.optString(KOL_NAMEL);
        this.house_number = jsonAddress.optString(KOL_HOUSE_NUMBER);
        this.letter = jsonAddress.optString(KOL_LETTER);
        this.zip_code = jsonAddress.optString(KOL_ZIP_CODE);
        this.postal_location = jsonAddress.optString(KOL_POSTAL_LOCATION);
        this.route_id = jsonAddress.optString(KOL_ROUTE_ID);

    }

    public static ArrayList<Address> addToAddressList(String jsonAddress) throws JSONException, NullPointerException{
        ArrayList<Address> addressList = new ArrayList<>();

        JSONObject jsonData = new JSONObject(jsonAddress);

        JSONArray addressTable = jsonData.optJSONArray(TABLE_NAME);

        for(int i = 0; i < addressTable.length(); i++) {
            JSONObject addressObject = (JSONObject) addressTable.get(i);
            Address address = new Address(addressObject);
            addressList.add(address);
        }

        return addressList;

    }

    public static ArrayList<Address> addToAddressList(JSONArray jsonAddress) throws JSONException{
        ArrayList<Address> addressArrayList = new ArrayList<>();


        for(int i = 0; i < jsonAddress.length(); i++) {
            JSONObject addressObject = (JSONObject) jsonAddress.get(i);
            Address address = new Address(addressObject);
            addressArrayList.add(address);
        }

        return addressArrayList;

    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", house_number='" + house_number + '\'' +
                ", letter='" + letter + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", postal_location='" + postal_location + '\'' +
                ", route_id='" + route_id + '\'' +
                ", id=" + id +
                '}';
    }
}
