package com.lihe.algorithm;

import com.lihe.algorithm.model.Square;
import com.lihe.algorithm.model.Item;
import com.lihe.algorithm.model.Wall;

import java.util.*;

/**
 * 生成随机迷宫
 */
public class RandomLabyrinth {

    private List<Square> slist = new ArrayList<>();

    //墙壁列表(只包括墙壁)
    private List<Wall> walls = new ArrayList<>();
    //item列表(包括方块和墙壁)
    private List<Item> items = new ArrayList<>();

    public List<List<String>> generateLabyrinth(Config config){
        //方块总行数
        final int rowCnt = config.getRowCnt();
        //方块总列数
        final int colCnt = config.getColCnt();
        //方块边长
        final int edgeLen = config.getEdgeLen();
        //总行数(包括墙壁)
        final int totalRowCnt = 2 * config.getRowCnt() - 1;
        //总列数(包括墙壁)
        final int totalColCnt = 2 * config.getColCnt() - 1;
        //起点位置下标
        final int startIndex = (config.getStartRow() * 2) * totalColCnt + config.getStartCol() * 2;
        //终点位置下标
        final int endIndex = (config.getEndRow() * 2) * totalColCnt + config.getEndCol() * 2;

        int count = 0;
        for(int i=0;i<totalRowCnt;i++){
            if(i%2 == 0){
                //方块和墙壁混合区域
                for(int j = 0;j < totalColCnt;j++){
                    if(j%2==0){
                        //如果是方块
                        Square square = new Square();
                        square.setHeight(edgeLen);
                        square.setWidth(edgeLen);
                        square.setColor("black");
                        square.setIndex(count++);
                        items.add(square);
                        slist.add(square);

                    } else {
                        Wall wall = new Wall();
                        wall.setHeight(edgeLen);
                        wall.setWidth(1);
                        wall.setColor("red");
                        wall.setIndex(count++);
                        wall.setSquareIndex(wall.getIndex() - 1);
                        wall.setAnotherSquareIndex(wall.getIndex() + 1);
                        items.add(wall);
                        walls.add(wall);
                    }
                }
            }else{
                //只是墙壁
                for(int j=0;j<totalColCnt;j++){
                    Wall wall = new Wall();
                    wall.setHeight(1);
                    wall.setWidth(j%2==0?edgeLen:1);
                    wall.setColor("red");
                    wall.setIndex(count++);
                    wall.setSquareIndex(wall.getIndex() - totalColCnt);
                    wall.setAnotherSquareIndex(wall.getIndex() + totalColCnt);
                    items.add(wall);
                    if(j%2==0){
                        walls.add(wall);
                    }
                }
            }
        }

        //并查集生成迷宫
        while(true){
            Random random = new Random();
            int i = random.nextInt(walls.size());

            Wall wall = walls.get(i);
            Integer wallIndex = wall.getSquareIndex();
            Integer otherWallIndex = wall.getAnotherSquareIndex();

            int i1 = find(wallIndex);
            int i2 = find(otherWallIndex);
            if(i1 == i2){
                int i3 = find(startIndex);
                int i4 = find(endIndex);
                if(i3 == i4){
                    break;
                }
            } else {
                wall.setDisplay(false);
                union(i1, i2);
                walls.remove(wall);

                Square square1 = (Square)items.get(wallIndex);
                Square square2 = (Square)items.get(otherWallIndex);
                square1.addAdj(square2);
                square2.addAdj(square1);
            }
        }

        //bfs最短路----
        findWayByBfs((Square)items.get(startIndex));
        printPath((Square)items.get(endIndex));
        //------------

        //dfs最短路----
//        for (Square s : slist) {
//            s.setDist(-1);
//        }
//        findAllWayByDfs(null, (Square)items.get(startIndex), 0);
//        printDfsAllPath((Square)items.get(endIndex));
        //------------

        // 生成最终样式
        List<List<String>> uiStyles = new ArrayList<>();
        List<String> uiStyle = null;
        for(int i=0;i<items.size();i++){
            if(i % totalColCnt == 0){
                uiStyles.add(uiStyle);
                uiStyle = new ArrayList<>();
            }

            uiStyle.add(items.get(i).generateStyle());
        }
        uiStyles.add(uiStyle);

        return uiStyles;
    }

    //compare方法，设-1为无限
    private static boolean compare(int dist1, int dist2) {
        if (dist1 == dist2) {
            return true;
        }
        if (dist1 >= 0 && dist2 < 0) {
            return false;
        } else if (dist1 < 0 && dist2 >= 0) {
            return true;
        } else {
            return dist1 > dist2;
        }
    }

    //*****************
    //**disjoint sets**
    //*****************
    //查找
    private int find(int x){
        Square square = (Square)items.get(x);

        if (square.getValue() < 0) {
            return x;
        } else {
            //普通查找
//              return find(square.getValue(), items);
            //路径压缩查找
            square.setValue(find(square.getValue()));
            return square.getValue();
        }
    }

    //合并
    private void union(int root1, int root2) {
        // 普通求并
//        		s[root2] = root1;
//              items.get(root2).setValue(root1);

        //按高度求并
        Square square2 = (Square)items.get(root2);
        Square square1 = (Square)items.get(root1);

        if (square2.getValue() < square1.getValue()) {
            square1.setValue(root2);
        } else {
            if (square1.getValue().intValue() == square2.getValue().intValue()) {
                square1.setValue(square1.getValue() - 1);
            }

            square2.setValue(root1);
        }
    }

    //*******
    //**bfs**
    //*******
    /**
     * 搜索路径
     * @param start
     */
    private void findWayByBfs(Square start){
        Queue<Square> q = new LinkedList<>();

        for (Square s : slist) {
            s.setDist(-1);
        }

        start.setDist(0);
        q.offer(start);

        while (!q.isEmpty()) {
            Square s = q.remove();
            for (Square w : s.getAdj()) {
                if (w.getDist() == -1) {
                    w.setDist(s.getDist() + 1);
                    w.setPath(s);
                    q.offer(w);
                }
            }
        }
    }

    //查找出路，设置为绿色
    private void printPath(Square end) {
        if (end.getPath() != null) {
            printPath(end.getPath());
        }

        Item item = items.get(end.getIndex());
        item.setColor("green");
    }

    //*******
    //**dfs**
    //*******
    private void findAllWayByDfs(Square from, Square to, int step){

        if (!compare(step, to.getDist())) {
            to.setDist(step);
            to.setPath(from);
        }else

        {
            return;
        }

        for (Square v : to.getAdj()) {
            if (!v.isKnown()) {
                v.setKnown(true);
                findAllWayByDfs(to, v, step);
                v.setKnown(false);
            }
        }
    }

    private void printDfsAllPath(Square e){

        if (e.getPath() != null) {
            printDfsAllPath(e.getPath());
        }
        Item item = items.get(e.getIndex());
        item.setColor("green");
    }
}
