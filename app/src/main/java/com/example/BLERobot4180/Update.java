package com.example.BLERobot4180;

import java.util.LinkedList;

public class Update {

    public static LinkedList<Obstacle> obstacles = new LinkedList<Obstacle>();
    public static float[] robotPosition = {0, 0};

    private float distance;
    private float angle;

    public Update() {
        distance = 0;
        angle = 0;
    }

    public boolean updateMeasurements(String newMeasurements) {
        if (newMeasurements.contains("X")) {
            float newX = Float.parseFloat(newMeasurements.substring(2));
            updateRobotPosition(newX, robotPosition[1]);
        } else if (newMeasurements.contains("Y")) {
            float newY = Float.parseFloat(newMeasurements.substring(2));
            updateRobotPosition(robotPosition[0], newY);
            return true;
        } else if (newMeasurements.contains("D")) {
            float newDistance = Float.parseFloat(newMeasurements.substring(2));
            if (newDistance > 20 && newDistance < 200) {
                distance = newDistance;
                if (angle != 0) {
                    updateObstacles(robotPosition[0] + distance * Math.sin(Math.toRadians(angle)),
                            robotPosition[1] + distance * Math.cos(Math.toRadians(angle)));
                    distance = 0;
                    angle = 0;
                    return true;
                }
            }
        } else if (newMeasurements.contains("A")) {
            float newAngle = Float.parseFloat(newMeasurements.substring(2));
            if (newAngle > 0 && newAngle <= 360) {
                angle = newAngle;
                if (distance != 0) {
                    updateObstacles(robotPosition[0] + distance * Math.sin(Math.toRadians(angle)),
                            robotPosition[1] + distance * Math.cos(Math.toRadians(angle)));
                    distance = 0;
                    angle = 0;
                    return true;
                }
            }
        }
        return false;
    }

    private static void updateObstacles(double newX, double newY) {
        obstacles.add(new Obstacle((float)newX, (float)newY));
    }

    private static void updateRobotPosition(float x, float y) {
        robotPosition[0] = x;
        robotPosition[1] = y;
    }

    public static void clear() {
        obstacles = new LinkedList<Obstacle>();
        updateRobotPosition(0, 0);
    }
}
