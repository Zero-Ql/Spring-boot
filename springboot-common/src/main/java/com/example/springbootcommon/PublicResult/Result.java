package com.example.springbootcommon.PublicResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

/**
 * @author: QSky
 * @date: 2024/4/22 上午9:03
 * @project: Spring-boot
 * @package: com.example.springbootcommon.PublicResult
 */

@Log4j2
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Result {
    private int code;
    private String message;
    private Object data;

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static Result success(int code, String message, Object data) {
        return new Result(code, message, data);
    }

    public static Result error(String message) {
        return new Result(400, message, null);
    }

    public static Result error(int code, String message) {
        return new Result(code, message, null);
    }

    public static Result error(String message, Object data) {
        return new Result(400, message, data);
    }

    public static Result error(int code, String message, Object data) {
        return new Result(code, message, data);
    }
}
