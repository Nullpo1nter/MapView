package com.onlylemi.mapview.library.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * MapUtils
 *
 * @author onlylemi
 */
public final class MapUtils {

    private static final String TAG = "MapUtils: ";

    private static final float INF = Float.MAX_VALUE;
    private static int nodesSize;
    private static int nodesContactSize;

    private MapUtils() {}

    public static void init(int nodessize, int nodescontactsize) {
        nodesSize = nodessize;
        nodesContactSize = nodescontactsize;
    }

    /**
     * Get the distance between points
     *
     * @param nodes
     * @param list
     * @return
     */
    public static float getDistanceBetweenList(List<PointF> nodes,
                                               List<Integer> list) {
        float distance = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            distance += MapMath.getDistanceBetweenTwoPoints(nodes.get(list.get(i)),
                    nodes.get(list.get(i + 1)));
        }
        return distance;
    }

    /**
     * get degrees between two points(list) with horizontal plane
     *
     * @param routeList
     * @param nodes
     * @return
     */
    public static List<Float> getDegreeBetweenTwoPointsWithHorizontal(List<Integer> routeList,
                                                                      List<PointF> nodes) {
        List<Float> routeListDegrees = new ArrayList<>();
        for (int i = 0; i < routeList.size() - 1; i++) {
            routeListDegrees.add(MapMath.getDegreeBetweenTwoPointsWithHorizontal(nodes.get
                            (routeList.get(i)),
                    nodes.get(routeList.get(i + 1))));
        }
        return routeListDegrees;
    }

    /**
     * get degrees between two points(list) with vertical plane
     *
     * @param routeList
     * @param nodes
     * @return
     */
    public static List<Float> getDegreeBetweenTwoPointsWithVertical(List<Integer> routeList,
                                                                    List<PointF> nodes) {
        List<Float> routeListDegrees = new ArrayList<>();
        for (int i = 0; i < routeList.size() - 1; i++) {
            routeListDegrees.add(MapMath.getDegreeBetweenTwoPointsWithVertical(nodes.get
                            (routeList.get(i)),
                    nodes.get(routeList.get(i + 1))));
        }
        return routeListDegrees;
    }

    /**
     * get shortest path between two points
     *
     * @param start        start point
     * @param end          end point
     * @param nodes        nodes list
     * @param nodesContact nodesContact list
     * @return
     */
    public static List<Integer> getShortestPathBetweenTwoPoints(int start,
                                                                int end, List<PointF> nodes,
                                                                List<PointF> nodesContact) {
        float[][] matrix = getMatrixBetweenFloorPlanNodes(nodes, nodesContact);

        return MapMath.getShortestPathBetweenTwoPoints(start, end, matrix);
    }

    /**
     * get best path between points
     *
     * @param points
     * @param nodes
     * @param nodesContact
     * @return
     */
    public static List<Integer> getBestPathBetweenPoints(int[] points, List<PointF> nodes,
                                                         List<PointF> nodesContact) {
        // adjacency matrix
        float[][] matrix = new float[points.length][points.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                if (i == j) {
                    matrix[i][j] = INF;
                } else {
                    matrix[i][j] = getDistanceBetweenList(
                            nodes, getShortestPathBetweenTwoPoints(points[i],
                                    points[j], nodes, nodesContact));
                    matrix[j][i] = matrix[i][j];
                }
            }
        }

        // TSP to get best path
        List<Integer> routeList = new ArrayList<>();
        List<Integer> result = MapMath.getBestPathBetweenPointsByGeneticAlgorithm(matrix);
        for (int i = 0; i < result.size() - 1; i++) {
            int size = routeList.size();
            routeList.addAll(getShortestPathBetweenTwoPoints(
                    points[result.get(i)], points[result.get(i + 1)], nodes,
                    nodesContact));
            if (i != 0) {
                routeList.remove(size);
            }
        }
        return routeList;
    }


    /**
     * get best path between points
     *
     * @param pointList
     * @param nodes
     * @param nodesContact
     * @return
     */
    public static List<Integer> getBestPathBetweenPoints(List<PointF> pointList,
                                                         List<PointF> nodes, List<PointF>
                                                                 nodesContact) {
        if (nodesSize != nodes.size()) {
            int value = nodes.size() - nodesSize;
            for (int i = 0; i < value; i++) {
                nodes.remove(nodes.size() - 1);
            }
            value = nodesContact.size() - nodesContactSize;
            for (int i = 0; i < value; i++) {
                nodesContact.remove(nodesContact.size() - 1);
            }
        }

        //find the point on the nearest route
        int[] points = new int[pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            addPointToList(pointList.get(i), nodes, nodesContact);
            points[i] = nodes.size() - 1;
        }

        return getBestPathBetweenPoints(points, nodes, nodesContact);
    }

    /**
     * get shortest distance between two points
     *
     * @param start
     * @param end
     * @param nodes
     * @param nodesContact
     * @return
     */
    public static float getShortestDistanceBetweenTwoPoints(int start, int end,
                                                            List<PointF> nodes, List<PointF>
                                                                    nodesContact) {
        List<Integer> list = getShortestPathBetweenTwoPoints(start, end, nodes,
                nodesContact);
        return getDistanceBetweenList(nodes, list);
    }

    /**
     * adjacency matrix with points
     *
     * @param nodes
     * @param nodesContact
     * @return
     */
    public static float[][] getMatrixBetweenFloorPlanNodes(List<PointF> nodes, List<PointF>
            nodesContact) {
        // set default is INF
        float[][] matrix = new float[nodes.size()][nodes.size()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = INF;
            }
        }

        // set value for matrix
        for (int i = 0; i < nodesContact.size(); i++) {
            matrix[(int) nodesContact.get(i).x][(int) nodesContact.get(i).y] = MapMath
                    .getDistanceBetweenTwoPoints(nodes.get((int) nodesContact.get(i).x),
                            nodes.get((int) nodesContact.get(i).y));

            matrix[(int) nodesContact.get(i).y][(int) nodesContact.get(i).x] = matrix[(int)
                    nodesContact
                            .get(i).x][(int) nodesContact.get(i).y];
        }

        return matrix;
    }

    /**
     * get shortest distance between two points
     *
     * @param start
     * @param end
     * @param nodes
     * @param nodesContact
     * @return
     */
    public static List<Integer> getShortestDistanceBetweenTwoPoints(PointF start, PointF end,
                                                                    List<PointF> nodes,
                                                                    List<PointF> nodesContact) {
        if (nodesSize != nodes.size()) {
            int value = nodes.size() - nodesSize;
            for (int i = 0; i < value; i++) {
                nodes.remove(nodes.size() - 1);
            }
            value = nodesContact.size() - nodesContactSize;
            for (int i = 0; i < value; i++) {
                nodesContact.remove(nodesContact.size() - 1);
            }
        }

        addPointToList(start, nodes, nodesContact);
        addPointToList(end, nodes, nodesContact);

        return getShortestPathBetweenTwoPoints(nodes.size() - 2, nodes.size() - 1, nodes,
                nodesContact);
    }

    /**
     * get the shortest path from the position point to the target point in the map
     *
     * @param position
     * @param target
     * @param nodes
     * @param nodesContact
     * @return
     */
    public static List<Integer> getShortestDistanceBetweenTwoPoints(PointF position, int target,
                                                                    List<PointF> nodes,
                                                                    List<PointF> nodesContact) {
        if (nodesSize != nodes.size()) {
            int value = nodes.size() - nodesSize;
            for (int i = 0; i < value; i++) {
                nodes.remove(nodes.size() - 1);
            }
            value = nodesContact.size() - nodesContactSize;
            for (int i = 0; i < value; i++) {
                nodesContact.remove(nodesContact.size() - 1);
            }
        }

        addPointToList(position, nodes, nodesContact);

        return getShortestPathBetweenTwoPoints(nodes.size() - 1, target, nodes, nodesContact);
    }

    private static void addPointToList(PointF point, List<PointF> nodes, List<PointF> nodesContact){
        float min = INF;
        float dis;
        int pos = 0;
        for (PointF contact : nodesContact){
            PointF node = nodes.get((int)contact.x);
            dis = MapMath.getDistanceBetweenTwoPoints(point, node);
            if (dis < min){
                min = dis;
                pos = (int) contact.x;
            }
        }
        nodes.add(point);
        nodesContact.add(new PointF(pos, nodes.size()-1));
    }

    /**
     * add point to list
     *
     * @param point
     * @param nodes
     * @param nodesContact
     */
    //找到距离point最近的一个可能通行的点
//    private static void addPointToList(PointF point, List<PointF> nodes, List<PointF>
//            nodesContact) {
//        if (point != null) {
//            PointF pV = point;
//            int po1 = 0, po2 = 0;
//            float min1 = INF;
//            for (int i = 0; i < nodesContact.size() - 1; i++) {
//                PointF p1 = nodes.get((int) nodesContact.get(i).x);
//                PointF p2 = nodes.get((int) nodesContact.get(i).y);
//                //point和另外两个点形成三角形
//                //如果除了point所在的角的两个角都是锐角或直角
//                if (!MapMath.isObtuseAnglePointAndLine(point, p1, p2)) {
//                    //minDis是point到对边的垂直距离
//                    float minDis = MapMath.getDistanceFromPointToLine(point, p1, p2);
//                    if (min1 > minDis) {
//                        System.out.println("*************************");
//                        System.out.print("更新点，从" + pV.x +", " + pV.y + "到");
//                        //pv是point到对边的垂足
//                        pV = MapMath.getIntersectionCoordinatesFromPointToLine(point, p1, p2);
//                        System.out.println( pV.x +", " + pV.y);
//                        System.out.println("三角形的另外两个点：" + p1.x + ", " + p1.y + ",, " + p2.x + ", " + p2.y);
//                        System.out.println("*************************");
//                        min1 = minDis;
//                        po1 = (int) nodesContact.get(i).x;
//                        po2 = (int) nodesContact.get(i).y;
//                    }
//                }
//            }
//            // get intersection
//            nodes.add(pV);
//            System.out.println("********************************");
//            System.out.println("add node: " + pV.x + ", " + pV.y);
//            System.out.println("********************************");
//            //Log.i(TAG, "node=" + (nodes.size() - 1) + ", po1=" + po1 + ", po2=" + po2);
//            nodesContact.add(new PointF(po1, nodes.size() - 1));
//            nodesContact.add(new PointF(po2, nodes.size() - 1));
//        }
//    }

    /**
     * bitmap to picture
     *
     * @param bitmap
     * @return
     */
    public static Picture getPictureFromBitmap(Bitmap bitmap) {
        Picture picture = new Picture();
        Canvas canvas = picture.beginRecording(bitmap.getWidth(),
                bitmap.getHeight());
        canvas.drawBitmap(
                bitmap,
                null,
                new RectF(0f, 0f, (float) bitmap.getWidth(), (float) bitmap
                        .getHeight()), null);
        picture.endRecording();
        return picture;
    }
}
