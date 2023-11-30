package com.example.dto;

import com.example.entity.CheckInLog;

import java.util.List;

public class CheckinLogResponse {

    private Integer total;
    private List<CheckInLog> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<CheckInLog> getData() {
        return data;
    }

    public void setData(List<CheckInLog> data) {
        this.data = data;
    }

    public CheckinLogResponse(Integer total, List<CheckInLog> data) {
        this.total = total;
        this.data = data;
    }
}
