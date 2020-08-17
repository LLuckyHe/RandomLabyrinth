package com.lihe.algorithm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 方格类
 */
public class Square extends Item {

    // 上级方块的index, 并查集操作用
    private Integer value;

    //bfs计算最短路径用---
    private Integer dist;
    private Square path;
    private List<Square> adj = new ArrayList<>();
    //-------------------

    //dfs计算最短路径用
    private boolean known;

    public Square(){
        //并查集操作前，初始化为-1
        this.value = -1;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public void setPath(Square path) {
        this.path = path;
    }

    public Square getPath() {
        return path;
    }

    public void addAdj(Square square){
        this.adj.add(square);
    }

    public List<Square> getAdj() {
        return adj;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }
}
