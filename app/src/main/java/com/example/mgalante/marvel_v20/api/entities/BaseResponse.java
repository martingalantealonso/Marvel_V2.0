package com.example.mgalante.marvel_v20.api.entities;

public class BaseResponse<T> {
    public int code;
    public String status;
    public String etag;
    public DataResponse<T> data;
}
