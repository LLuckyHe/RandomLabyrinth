package com.lihe.algorithm.model;

/**
 * 方格、墙壁父类
 */
public class Item {

    //元素所在索引
    private Integer index;

    //元素宽度
    private Integer width;

    //元素高度
    private Integer height;

    //是否显示
    private Boolean display;

    //背景颜色
    private String color;

    //css样式，供页面显示
    private String style;

    Item(){
        //默认显示
        this.display = true;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    //根据各属性生成style
    public String generateStyle(){
        StringBuilder sb = new StringBuilder();
        sb.append("width:").append(width).append("px;");
        sb.append("height:").append(height).append("px;");
        sb.append("background-color: ").append(color).append(";");
        if(!display){
            sb.append("visibility:hidden");
        }

        this.style = sb.toString();
        return this.style;
    }

    public String getStyle() {
        return style;
    }

}
