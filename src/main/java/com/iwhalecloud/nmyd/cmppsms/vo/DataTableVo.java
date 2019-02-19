package com.iwhalecloud.nmyd.cmppsms.vo;

import java.util.List;

public class DataTableVo<T> {

    //获取请求次数
    private int draw;

    //总记录数
    private long recordsTotal;

    //过滤后记录数
    private long recordsFiltered;

    //具体数据
    List<T> data;


    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
