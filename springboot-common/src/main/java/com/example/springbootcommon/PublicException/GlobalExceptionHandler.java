package com.example.springbootcommon.PublicException;

import com.example.springbootcommon.PublicResult.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: QSky
 * @date: 2024/4/19 下午5:28
 * @project: Spring-boot
 * @package: com.example.springbootcommon.PublicException
 */

@Log4j2 // 使用Log4j2进行日志记录
@RestControllerAdvice // 为全局异常处理提供的注解，使得下面的方法能处理所有Controller中抛出的异常
public class GlobalExceptionHandler {

    /**
     * 处理缺少必要请求参数的异常
     *
     * @param e MissingServletRequestParameterException 异常对象，指示哪个请求参数缺失
     * @return Result 返回一个错误结果对象，提示缺少必要的请求参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 设置HTTP响应状态为400 - Bad Request
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少必要的请求参数：{}", e.getMessage()); // 记录异常信息
        return Result.error("缺少必要的请求参数"); // 返回错误结果
    }

    /**
     * 处理空指针异常
     *
     * @param e NullPointerException 异常对象，指示发生空指针的位置
     * @return Result 返回一个错误结果对象，提示发生了空指针异常
     */
    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, code = HttpStatus.INTERNAL_SERVER_ERROR)
    // 设置HTTP响应状态为500 - Internal Server Error
    public Result handleNullPointerException(NullPointerException e) {
        log.error("空指针异常：{}", e.getMessage()); // 记录异常信息
        return Result.error("空指针异常"); // 返回错误结果
    }


    /**
     * 处理所有未被其他异常处理器捕获的异常。
     *
     * @param e 抛出的异常对象
     * @return 返回一个包含错误信息的ResponseEntity对象，状态码为500（内部服务器错误）
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<String> handleException(Exception e) {
//        // 记录异常信息
//        log.error("系统异常：{}", e.getMessage());
//        // 构建响应体，包含错误状态码、自定义错误信息和异常信息
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .headers(httpheaders -> {
//                    // 设置响应头内容类型为JSON
//                    httpheaders.setContentType(MediaType.APPLICATION_JSON);
//                    // 这里硬编码了access-token，实际应用中应根据需求设置
//                    httpheaders.set("access-token", "123456");
//                })
//                // 将错误信息封装成JSON格式作为响应体的内容
//                .body(Result.error(500, "系统异常", e.getMessage()).toString());
//    }
}
