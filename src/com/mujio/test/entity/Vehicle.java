package com.mujio.test.entity;

public class Vehicle {
    public int id;//车辆编号
    public int start_node;//车辆起始配送中心点
    public int vehicle_load;//车辆载重容量限制
    public int start_tm;//车辆开始时间窗
    public int end_tm;//车辆最迟开始时间窗
    public int max_ml;//车辆行驶里程


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", start_node=" + start_node +
                ", vehicle_load=" + vehicle_load +
                ", start_tm=" + start_tm +
                ", end_tm=" + end_tm +
                ", max_ml=" + max_ml +
                '}';
    }
}
