package com.example.parkingmap;

import android.content.res.AssetManager;
import android.graphics.PointF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class ParkingLotsInfo{
    private int remain;
    static List<ParkingLotInfo> parkingLots;

    public static ParkingLotInfo getRandomLot(BufferedReader bfr) throws IOException {
        ParkingLotsInfo.init(bfr);
        int n = new Random().nextInt(37);
        while (parkingLots.get(n).hasCar == 1){
            n = new Random().nextInt(37);
        }
        return parkingLots.get(n);
    }

    public static void init(BufferedReader bfr) throws IOException {
        parkingLots = new ArrayList<>();
        String line = "";
        int number;
        float x, y;
        int hasCar;
        ParkingLotInfo info;
        while ((line = bfr.readLine()) != null) {
            System.out.println(line);
            String[] contents = line.split(", ");
            number = Integer.parseInt(contents[0]);
            x = Float.parseFloat(contents[1]);
            y = Float.parseFloat(contents[2]);
            hasCar = Integer.parseInt(contents[3]);
            info = new ParkingLotInfo(number, x, y, hasCar);
            parkingLots.add(info);
        }
    }
}
class ParkingLotInfo {
    public int number;
    public float x, y;
    int hasCar;
    public ParkingLotInfo(int number, float x, float y, int hasCar){
        this.number = number;
        this.x = x;
        this.y = y;
        this.hasCar = hasCar;
    }
}
