package com.lihe.algorithm.model;

/**
 * 墙壁类
 */
public class Wall extends Item {

    //墙壁分隔的方格所在的index
    private Integer squareIndex;

    //墙壁分隔的另一个方格所在的index
    private Integer anotherSquareIndex;

    public Integer getSquareIndex() {
        return squareIndex;
    }

    public void setSquareIndex(Integer squareIndex) {
        this.squareIndex = squareIndex;
    }

    public Integer getAnotherSquareIndex() {
        return anotherSquareIndex;
    }

    public void setAnotherSquareIndex(Integer anotherSquareIndex) {
        this.anotherSquareIndex = anotherSquareIndex;
    }
}
