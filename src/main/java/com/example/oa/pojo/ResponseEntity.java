package com.example.oa.pojo;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    private String code; // 响应码 例如:200 404 500
    private String msg; // 描述信息
    private T data; // 响应数据

    public ResponseEntity(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseEntity(T data) {
        this.data = data;
    }
}
