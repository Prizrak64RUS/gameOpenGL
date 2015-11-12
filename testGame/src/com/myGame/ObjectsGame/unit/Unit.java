package com.myGame.ObjectsGame.unit;

import java.util.ArrayList;

/**
 * Created by user on 10.11.2015.
 * Абстрактный класс, символизирующий юнита
 */
abstract public class Unit {
    /***
     * Жизни
     */
    int hitPointsThis;
    int hitPointsReal;
    /***
     * Статы
     */
    Stats nativeStats;
    Stats thisStats;
    /***
     * Тело
     */
    Body body;
    /***
     * Бафы
     */
    ArrayList<Buff> buffs =new ArrayList<Buff>();

}
