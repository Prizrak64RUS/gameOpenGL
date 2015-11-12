package com.myGame.ObjectsGame.unit;

import java.util.ArrayList;

/**
 * Created by user on 10.11.2015.
 * ����������� �����, ��������������� �����
 */
abstract public class Unit {
    /***
     * �����
     */
    int hitPointsThis;
    int hitPointsReal;
    /***
     * �����
     */
    Stats nativeStats;
    Stats thisStats;
    /***
     * ����
     */
    Body body;
    /***
     * ����
     */
    ArrayList<Buff> buffs =new ArrayList<Buff>();

}
