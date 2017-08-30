package com.github.adam6806.catamaranindex.boat;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope(value = "session")
public class BoatRequest {

    private static final String URL = "http://www.yachtworld.com/core/listing/cache/searchResults.jsp?toPrice=175000&fromPrice=25000&enid=101&Ntk=boatsEN&type=%28Sail%29+Catamaran&searchtype=advancedsearch&hmid=0&sm=3&enid=0&cit=true&luom=126&currencyid=100&boatsAddedSelected=-1&ftid=0&slim=quick&No=0&rid=100&rid=104&rid=105&rid=107&rid=112&rid=115&rid=125&fracts=1&ps=2000&Ns=PBoat_sortByPriceDesc|1";
    private static final String MAIN_URL = "http://www.yachtworld.com";
    int lowPrice;
    private ArrayList<Boat> boats;
    private int highPrice;

    @RequestMapping(value = "Boats", method = RequestMethod.POST)
    public String report(ModelMap modelMap) {

        return "boats";
    }

    @RequestMapping(value = "Boats/setrating", method = RequestMethod.POST)
    protected void setRating(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rating = req.getParameter("rating");
        String username = req.getParameter("username");
        String id = req.getParameter("id");
        String combinedRating = "";
        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("update boat ");
            if ("adam".equals(username)) {
                stringBuilder.append("set adam_rating ");
            } else {
                stringBuilder.append("set doug_rating ");
            }
            stringBuilder.append(" = ").append(rating).append(" ");
            stringBuilder.append(" where id = ").append(id).append(";");
            statement.executeUpdate(stringBuilder.toString());
            String sql = "select adam_rating, doug_rating from boat where id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int adam_rating = resultSet.getInt("adam_rating");
            int doug_rating = resultSet.getInt("doug_rating");
            int combined = adam_rating + doug_rating;
            combinedRating = "" + combined;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("combinedRating", combinedRating);
        jsonObject.append("id", id);
        out.print(jsonObject.toString());
        out.flush();
    }

    public ArrayList<Boat> getBoats() throws IOException {
        boats = new ArrayList<>();
        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement();
                Statement imageStatement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM boat;")
        ) {

            while (resultSet.next()) {
                String location = resultSet.getString("location");
                String boatUrl = resultSet.getString("url");
                String make_model = resultSet.getString("make_model");
                long price = resultSet.getLong("price");
                int length = resultSet.getInt("length");
                int year = resultSet.getInt("year");
                int id = resultSet.getInt("id");
                int doug_rating = resultSet.getInt("doug_rating");
                int adam_rating = resultSet.getInt("adam_rating");
                String timestamp = resultSet.getDate("timestamp").toString();
                ResultSet imageResultSet = imageStatement.executeQuery("SELECT url FROM image WHERE boat = " + id);
                List<String> images = new ArrayList<>();
                while (imageResultSet.next()) {
                    images.add(imageResultSet.getString("url"));
                }
                imageResultSet.close();
                System.out.println(timestamp);
                Boat boat = new Boat(price, make_model, location, images, boatUrl, length, year, id, doug_rating, adam_rating, timestamp);
                boats.add(boat);
            }
            resultSet.close();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return boats;
    }


    public void setBoatRating(String username, int boatId) {
        System.out.println(username);
        System.out.println(boatId);
    }

    public void setBoats(ArrayList<Boat> boats) {
        this.boats = boats;
    }

    public int getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice = highPrice;
    }

    public int getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }

}
