package com.vickky.employee.model;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("InActive");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
