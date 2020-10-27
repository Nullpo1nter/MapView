package com.example.parkingmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class ParkingLotInfo{
    public static String name;
    public static String location;
    public static int floors;
    public static int remain;
    public static String[] floorsName;
    static List<LotInfo> lotList;

    public static LotInfo getRandomLot(){
//        ParkingLotInfo.init(bfr);
        int n = new Random().nextInt(37*floors);
        while (lotList.get(n).hasCar == 1){
            n = new Random().nextInt(37*floors);
        }
        return lotList.get(n);
    }

    public static void init(BufferedReader bfr) throws IOException {
        lotList = new ArrayList<>();
        String line = "";
        while ((line = bfr.readLine()) != null && (line.split(" ")[0]).equals("#")){
//            System.out.println("文件读取1：" + line);
        }
//        System.out.println("文件读取2："+line);
        String[] contents = line.split(", ");
        name = contents[0];
        location = contents[1];
        floors = Integer.parseInt(contents[2]);
        remain = Integer.parseInt(contents[3]);
        floorsName = new String[floors];
        int i = -1;
        int number;
        float x, y;
        int hasCar;
        LotInfo lot;
        while ((line = bfr.readLine()) != null) {
//            System.out.println("文件读取3："+line);
            contents = line.split(", ");
            if (i < 0 || !floorsName[i].equals(contents[0])){
                i++;
                floorsName[i] = contents[0];
            }
            number = Integer.parseInt(contents[1]);
            x = Float.parseFloat(contents[2]);
            y = Float.parseFloat(contents[3]);
            hasCar = Integer.parseInt(contents[4]);
            lot = new LotInfo(contents[0], number, x, y, hasCar);
            lotList.add(lot);
        }
    }
}
class LotInfo {
    public String floor;
    public int number;
    public float x, y;
    int hasCar;
    public LotInfo(String floor, int number, float x, float y, int hasCar){
        this.floor = floor;
        this.number = number;
        this.x = x;
        this.y = y;
        this.hasCar = hasCar;
    }
}
