package com.oblig.boskartoteket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    private String email, password, fNavn, eNavn, address;

    // Brukes bare når du skal søke opp i databasen etter bruker med ID.
    private int id;


    private static final String TABLE_NAME = "User_new";
    private static final String KOL_ID = "ID";
    private static final String KOL_EMAIL = "email";
    private static final String KOL_PASSWORD = "password";
    private static final String KOL_FNAME = "first_name";
    private static final String KOL_LNAME = "last_name";
    private static final String KOL_ADDRESS = "address";

    /**
     *
     * Constructor for adding user from view
     *
     * @param email
     * @param fNavn
     * @param eNavn
     * @param password
     *
     *
     * */
    public User(String fNavn, String eNavn, String email, String password, String address) {
        this.fNavn = fNavn;
        this.eNavn = eNavn;
        this.email = email;
        this.password = password;
        this.address = address;
    }


    public User(JSONObject jsonUser) {
        this.email = jsonUser.optString(KOL_EMAIL);
        this.password = jsonUser.optString(KOL_PASSWORD);
        this.fNavn = jsonUser.optString(KOL_FNAME);
        this.eNavn = jsonUser.optString(KOL_LNAME);
        this.address = jsonUser.optString(KOL_ADDRESS);

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
        JSONObject jsonUser = new JSONObject();
        try {
            jsonUser.put(KOL_EMAIL, this.email);
            jsonUser.put(KOL_FNAME, this.fNavn);
            jsonUser.put(KOL_LNAME, this.eNavn);
            jsonUser.put(KOL_PASSWORD, this.password);
            jsonUser.put(KOL_ADDRESS, this.address);

        } catch (JSONException e) {return null;}
        return jsonUser;
    }

    /**
     *
     * Metode for å sjekke om brukeren stemmer overens med det som er i databasen.
     *
     * */

    public static boolean isUser(String email, String password, ArrayList<User> userList) {
        boolean isUser = true;
        for(User uInArr: userList){
            if(uInArr.getEmail().equals(email) && uInArr.getPassword().equals(password)) {
                isUser = true;
                break;
            } else {
                isUser = false;
            }

        }
        return isUser;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getfNavn() {
        return fNavn;
    }

    public String geteNavn() {
        return eNavn;
    }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Email:" + email + "\n" +
                "Password: " + password;
    }
}
