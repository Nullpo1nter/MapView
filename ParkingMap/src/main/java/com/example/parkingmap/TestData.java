package com.example.parkingmap;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public final class TestData {

    public static List<PointF> nodeList;
    public static List<PointF> nodesContactList;
    public static List<PointF> marks;
    public static List<String> marksName;

    private TestData() {}

    public static void init(){
        nodeList = getNodesList();
        nodesContactList = getNodesContactList();
        marks = getMarks();
        marksName = getMarksName();
    }

    private static List<PointF> getNodesList() {
        List<PointF> nodes = new ArrayList<>();
        nodes.add(new PointF(610, 210));
        nodes.add(new PointF(40, 330));
        nodes.add(new PointF(90, 330));
        nodes.add(new PointF(150, 330));
        nodes.add(new PointF(210, 330));
        nodes.add(new PointF(260, 330));
        nodes.add(new PointF(320, 330));
        nodes.add(new PointF(380, 330));
        nodes.add(new PointF(440, 330));
        nodes.add(new PointF(505, 330));
        nodes.add(new PointF(610, 330));

        nodes.add(new PointF(610, 435));
        nodes.add(new PointF(610, 485));
        nodes.add(new PointF(40, 695));
        nodes.add(new PointF(100, 695));
        nodes.add(new PointF(170, 695));
        nodes.add(new PointF(230, 695));
        nodes.add(new PointF(335, 695));
        nodes.add(new PointF(400, 695));
        nodes.add(new PointF(450, 695));
        nodes.add(new PointF(505, 695));
        nodes.add(new PointF(580,695));
        nodes.add(new PointF(610, 695));
        nodes.add(new PointF(640, 695));
        nodes.add(new PointF(720, 695));
        nodes.add(new PointF(770, 695));
        nodes.add(new PointF(830, 695));
        nodes.add(new PointF(900, 695));
        //0-28 above
        nodes.add(new PointF(40, 210));
        nodes.add(new PointF(90, 210));
        nodes.add(new PointF(150, 210));
        nodes.add(new PointF(210, 210));
        nodes.add(new PointF(260, 210));
        nodes.add(new PointF(320, 210));
        nodes.add(new PointF(380, 210));
        nodes.add(new PointF(440, 210));
        nodes.add(new PointF(40, 450));
        nodes.add(new PointF(100, 450));
        nodes.add(new PointF(170, 450));
        nodes.add(new PointF(230, 450));
        nodes.add(new PointF(335, 450));
        nodes.add(new PointF(400, 450));
        nodes.add(new PointF(450, 450));
        nodes.add(new PointF(505, 450));
        nodes.add(new PointF(740, 435));
        nodes.add(new PointF(740, 485));
        nodes.add(new PointF(40, 570));
        nodes.add(new PointF(100, 570));
        nodes.add(new PointF(450, 570));
        nodes.add(new PointF(505, 570));
        nodes.add(new PointF(720, 570));
        nodes.add(new PointF(770, 570));
        nodes.add(new PointF(40, 800));
        nodes.add(new PointF(100, 800));
        nodes.add(new PointF(170, 800));
        nodes.add(new PointF(230, 800));
        nodes.add(new PointF(335, 800));
        nodes.add(new PointF(400, 800));
        nodes.add(new PointF(450, 800));
        nodes.add(new PointF(505, 800));
        nodes.add(new PointF(580, 800));
        nodes.add(new PointF(640, 800));
        nodes.add(new PointF(720, 800));
        nodes.add(new PointF(770, 800));
        nodes.add(new PointF(830, 800));
        nodes.add(new PointF(900, 800));
        nodes.add(new PointF(450, 90));
        return nodes;
    }

    private static List<PointF> getNodesContactList() {
        List<PointF> nodesContact = new ArrayList<PointF>();
        nodesContact.add(new PointF(0, 10));
        nodesContact.add(new PointF(1, 2));
        nodesContact.add(new PointF(2, 3));
        nodesContact.add(new PointF(3, 4));
        nodesContact.add(new PointF(4, 5));
        nodesContact.add(new PointF(5, 6));
        nodesContact.add(new PointF(6, 7));
        nodesContact.add(new PointF(7, 8));
        nodesContact.add(new PointF(8, 9));
        nodesContact.add(new PointF(9, 10));
        nodesContact.add(new PointF(10, 11));
        nodesContact.add(new PointF(11,12));
        nodesContact.add(new PointF(12, 23));
        nodesContact.add(new PointF(13, 14));
        nodesContact.add(new PointF(14, 15));
        nodesContact.add(new PointF(15, 16));
        nodesContact.add(new PointF(16, 17));
        nodesContact.add(new PointF(17, 18));
        nodesContact.add(new PointF(18, 19));
        nodesContact.add(new PointF(19, 20));
        nodesContact.add(new PointF(20, 21));
        nodesContact.add(new PointF(21, 22));
        nodesContact.add(new PointF(22, 23));
        nodesContact.add(new PointF(23, 24));
        nodesContact.add(new PointF(24, 25));
        nodesContact.add(new PointF(25, 26));
        nodesContact.add(new PointF(26, 27));


        nodesContact.add(new PointF(1, 28));
        nodesContact.add(new PointF(2, 29));
        nodesContact.add(new PointF(3, 30));
        nodesContact.add(new PointF(4, 31));
        nodesContact.add(new PointF(5, 32));
        nodesContact.add(new PointF(6, 33));
        nodesContact.add(new PointF(7, 34));
        nodesContact.add(new PointF(8, 35));
        nodesContact.add(new PointF(1, 36));
        nodesContact.add(new PointF(2, 37));
        nodesContact.add(new PointF(3, 38));
        nodesContact.add(new PointF(4, 39));
        nodesContact.add(new PointF(6,40 ));
        nodesContact.add(new PointF(7,41 ));
        nodesContact.add(new PointF(8,42));
        nodesContact.add(new PointF(9, 43));
        nodesContact.add(new PointF(11, 44));
        nodesContact.add(new PointF(12, 45));
        nodesContact.add(new PointF(13, 46));
        nodesContact.add(new PointF(14, 47));
        nodesContact.add(new PointF(19, 48));
        nodesContact.add(new PointF(20, 49));
        nodesContact.add(new PointF(24, 50));
        nodesContact.add(new PointF(25, 51));
        nodesContact.add(new PointF(13, 52));
        nodesContact.add(new PointF(14, 53));
        nodesContact.add(new PointF(15, 54));
        nodesContact.add(new PointF(16, 55));
        nodesContact.add(new PointF(17, 56));
        nodesContact.add(new PointF(18, 57));
        nodesContact.add(new PointF(19, 58));
        nodesContact.add(new PointF(20, 59));
        nodesContact.add(new PointF(21, 60));
        nodesContact.add(new PointF(23, 61));
        nodesContact.add(new PointF(24, 62));
        nodesContact.add(new PointF(25, 63));
        nodesContact.add(new PointF(26, 64));
        nodesContact.add(new PointF(27, 65));
        nodesContact.add(new PointF(66, 0));

        return nodesContact;
    }

    private static List<PointF> getMarks() {
        List<PointF> marks = new ArrayList<>();
        marks.add(new PointF(40, 210));
        marks.add(new PointF(90, 210));
        marks.add(new PointF(150, 210));
        marks.add(new PointF(210, 210));
        marks.add(new PointF(260, 210));
        marks.add(new PointF(320, 210));
        marks.add(new PointF(380, 210));
        marks.add(new PointF(440, 210));
        marks.add(new PointF(40, 450));
        marks.add(new PointF(100, 450));
        marks.add(new PointF(170, 450));
        marks.add(new PointF(230, 450));
        marks.add(new PointF(335, 450));
        marks.add(new PointF(400, 450));
        marks.add(new PointF(450, 450));
        marks.add(new PointF(505, 450));
        marks.add(new PointF(740, 435));
        marks.add(new PointF(740, 485));
        marks.add(new PointF(40, 570));
        marks.add(new PointF(100, 570));
        marks.add(new PointF(450, 570));
        marks.add(new PointF(505, 570));
        marks.add(new PointF(720, 570));
        marks.add(new PointF(770, 570));
        marks.add(new PointF(40, 800));
        marks.add(new PointF(100, 800));
        marks.add(new PointF(170, 800));
        marks.add(new PointF(230, 800));
        marks.add(new PointF(335, 800));
        marks.add(new PointF(400, 800));
        marks.add(new PointF(450, 800));
        marks.add(new PointF(505, 800));
        marks.add(new PointF(580, 800));
        marks.add(new PointF(640, 800));
        marks.add(new PointF(720, 800));
        marks.add(new PointF(770, 800));
        marks.add(new PointF(830, 800));
        marks.add(new PointF(900, 800));
        marks.add(new PointF(450, 80));

        return marks;
    }


    private static List<String> getMarksName() {
        List<String> marksName = new ArrayList<>();
        for (int i = 0; i < getMarks().size(); i++) {
            marksName.add("Shop " + (i + 1));
        }
        return marksName;
    }
}