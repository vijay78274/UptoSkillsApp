package com.example.uptoskills.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class coursedatabase {
    public static List<String> name = new ArrayList<>();
    public static List<String>image_url = new ArrayList<>();
    public static List<String>content = new ArrayList<>();
    public static List<String>duration = new ArrayList<>();
    public static List<String>count_students = new ArrayList<>();
    public static List<String>rating = new ArrayList<>();
    public static List<String>price_rendered = new ArrayList<>();
    public static List<String>origin_price_rendered = new ArrayList<>();
    public static List<String>typeofprograme = new ArrayList<>();
    public static Map<String,List<String>> map = new HashMap<>() {};

    public static void initializeDatabase(){
//        coursedatabase.name.clear();
//        coursedatabase.typeofprograme.clear();
//        coursedatabase.name.add("Visual communication design for digital media");
//        coursedatabase.name.add("Urban land use and transport palnning");
//        coursedatabase.name.add("Environmental impact assessment");
//        coursedatabase.name.add("Architectural Acoustics");
//        coursedatabase.name.add("Dairy and Food Process and products technology");
//        coursedatabase.name.add("Soil and Water conserving Engineering");
//        coursedatabase.name.add("Thermal Processing of Food");
//        coursedatabase.name.add("Organic Farming");
//
//        coursedatabase.typeofprograme.add("Architecture");
//        coursedatabase.typeofprograme.add("Architecture");
//        coursedatabase.typeofprograme.add("Architecture");
//        coursedatabase.typeofprograme.add("Architecture");
//        coursedatabase.typeofprograme.add("Agriculture");
//        coursedatabase.typeofprograme.add("Agriculture");
//        coursedatabase.typeofprograme.add("Agriculture");
//        coursedatabase.typeofprograme.add("Agriculture");
        for (int i = 0; i < coursedatabase.name.size(); i++) {
            String programType = coursedatabase.typeofprograme.get(i);
            String courseName = coursedatabase.name.get(i);
            map.putIfAbsent(programType, new ArrayList<>());

            map.get(programType).add(courseName);
        }
    }
    public static List<String> getCoursesByType(String type) {
        return map.getOrDefault(type, new ArrayList<>());
    }
}
