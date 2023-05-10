package com.example.chain_finder.constants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateLocations {
  public static void main(String[] args) {
    List<JSONObject> locations = new ArrayList<>();
    Random random = new Random();

    for (int i = 0; i < 500; i++) {
      double lat = 22.2 + (random.nextDouble() * 3); // 緯度介於 22.5 ~ 27 度之間
      double lng = 119.5 + (random.nextDouble() * 3); // 經度介於 118.5 ~ 124.5 度之間
      JSONObject location = new JSONObject();
      location.put("lat", lat);
      location.put("lng", lng);
      location.put("title", "Marker " + (i + 1));
      locations.add(location);
    }

    try (FileWriter file = new FileWriter("locations.json")) {
      JSONArray jsonArray = new JSONArray();
      jsonArray.addAll(locations);
      file.write(jsonArray.toJSONString());
      System.out.println("Successfully wrote JSON to file: locations.json");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

