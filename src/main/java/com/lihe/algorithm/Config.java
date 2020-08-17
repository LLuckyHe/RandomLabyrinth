package com.lihe.algorithm;

public class Config {

    //总行数
    private int rowCnt;
    //总列数
    private int colCnt;
    //方块边长
    private int edgeLen;
    //起点所在行
    private int startRow;
    //起点所在列
    private int startCol;
    //终点所在行
    private int endRow;
    //终点所在列
    private int endCol;

    private Config(Builder builder){
        rowCnt = builder.rowCnt;
        colCnt = builder.colCnt;
        edgeLen = builder.edgeLen;
        startRow = builder.startRow;
        startCol = builder.startCol;
        endRow = builder.endRow;
        endCol = builder.endCol;
    }

    public static class Builder{
        private int rowCnt;
        private int colCnt;
        private int edgeLen;
        private int startRow;
        private int startCol;
        private int endRow;
        private int endCol;

        public Builder(){
            this.rowCnt = 5;
            this.colCnt = 5;
            this.edgeLen = 10;
        }

        public Builder rowCnt(int rowCnt){
            if(rowCnt <=0){
                throw new RuntimeException("行数必须大于零");
            }
            this.rowCnt = rowCnt;
            return this;
        }

        public Builder colCnt(int colCnt){
            if(colCnt <=0){
                throw new RuntimeException("列数必须大于零");
            }
            this.colCnt = colCnt;
            return this;
        }

        public Builder edgeLen(int edgeLen){
            if(edgeLen <=0){
                throw new RuntimeException("边长必须大于零");
            }
            this.edgeLen = edgeLen;
            return this;
        }

        public Builder startRow(int startRow){
            this.startRow = startRow;
            return this;
        }

        public Builder startCol(int startCol){
            this.startCol = startCol;
            return this;
        }

        public Builder endRow(int endRow){
            this.endRow = endRow;
            return this;
        }

        public Builder endCol(int endCol){
            this.endCol = endCol;
            return this;
        }

        public Config build(){
            this.startRow = 0;
            this.startCol = 0;
            this.endRow = rowCnt - 1;
            this.endCol = colCnt - 1;
            check();
            return new Config(this);
        }

        private void check(){
            if(rowCnt <=0){
                throw new RuntimeException("行数必须大于零");
            }

            if(colCnt <=0){
                throw new RuntimeException("列数必须大于零");
            }

            if(edgeLen <=0){
                throw new RuntimeException("边长必须大于零");
            }

            if(!checkRangeForSquare(this.startRow, this.startCol)){
                throw new RuntimeException("起点坐标不合法");
            }

            if(!checkRangeForSquare(this.endRow, this.endCol)){
                throw new RuntimeException("终点坐标不合法");
            }

            if(startRow == endRow && startCol == endCol){
                throw new RuntimeException("起点与终点不能重合");
            }

        }

        private boolean checkRangeForSquare(int row, int col){
            if((row < 0 || row >= this.rowCnt) ||
                    (col < 0 || col >= this.colCnt)){
                return false;
            }

            return true;
        }


    }

    public int getRowCnt() {
        return rowCnt;
    }

    public int getColCnt() {
        return colCnt;
    }

    public int getEdgeLen() {
        return edgeLen;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }
}
