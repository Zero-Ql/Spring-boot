/**
 * projectName: Spring-boot
 * fileName: controller.java
 * packageName: com.example.springboot
 * date: 2024-04-01 16:39
 */
package com.example.springbootapi.controller;

import com.example.springbootservice.GetStudent;
import com.example.springbootservice.Login;
import com.example.springbootservice.Score;
import fun.zero.li.JsonResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author: QSky
 * @date: 2024-04-01 16:39
 **/

@RestController // 定义一个RESTful控制器
class GetStudentController {
    @Resource // 注入GetStudent服务
    GetStudent getstudent; // 用于获取学生信息的服务

    /**
     * 处理GET请求，获取学生信息。
     *
     * @return JsonResult<String> 包含学生信息的JSON结果，其中数据类型为String。
     */
    @RequestMapping(path = "/GetStudent", method = RequestMethod.GET)
    public JsonResult<String> getStudent() {
        // 调用getStudent服务，将结果封装在JsonResult中返回
        return new JsonResult<>(getstudent.getStudent());
    }
}

@RestController
// GetTheNumberOfOnlineUsers类用于处理获取当前在线用户数量的请求
class GetTheNumberOfOnlineUsers {
    /**
     * 处理获取在线用户数量的请求。
     *
     * @param request  HttpServletRequest对象，用于获取请求信息。
     * @param response HttpServletResponse对象，用于设置响应信息，如cookie。
     * @return JsonResult<String> 包含当前在线用户数量信息的JSON结果。
     */
    @RequestMapping(path = "/getTheNumberOfOnlineUsers", method = RequestMethod.GET)
    public JsonResult<String> getTheNumberOfOnlineUsers(HttpServletRequest request, HttpServletResponse response) {
        //  创建一个Cookie用于存储JSESSIONID
        Cookie cookie;
        cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), StandardCharsets.UTF_8));
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        // 将Cookie添加到响应中，以便客户端可以接收
        response.addCookie(cookie);
        // 从会话中获取当前在线用户数量
        var count = request.getSession().getServletContext().getAttribute("count") != null ?
                (Integer) request.getSession().getServletContext().getAttribute("count") : -1;

        // 返回包含当前在线用户数量的JSON结果
        return new JsonResult<>("当前在线人数：" + count);
    }
}

@RestController
public class controller {
    @RequestMapping(path = "/form", method = RequestMethod.POST)
    public JsonResult<HashMap<String, String>> test_form(@RequestParam HashMap<String, String> params) {
        return new JsonResult<>(params);
    }
}

@RestController
@RequestMapping(path = "/upload")
class uploadController {
    // 用于格式化日期的两个SimpleDateFormat实例
    private final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat HH_mm_ss = new SimpleDateFormat("HH-mm-ss");

    /**
     * 上传文本文件的方法
     *
     * @param uploadFile 用户上传的文件
     * @param request    HTTP请求对象，用于获取服务器路径等信息
     * @return JsonResult 包含文件上传成功后的URL或者错误信息
     */
    @RequestMapping(path = "/txt", method = RequestMethod.POST)
    public JsonResult uploadTxt(MultipartFile uploadFile, HttpServletRequest request) {
        // 获取上传文件的基础目录
        String uploadPath = request.getSession().getServletContext().getRealPath("/upload/txt");
        // 根据当前时间生成文件存放的子目录
        String timePath = YYYY_MM_DD.format(new Date());
        // 构建完整的文件上传目录路径
        File folderPath = new File(uploadPath, timePath);
        // 获取上传文件的原始名称
        String uploadFileName = uploadFile.getOriginalFilename();
        // 拼接新文件名，包含UUID和时间戳以避免重复
        String newFileName = "";

        String fileUrl;
        // 如果目标文件夹不存在，则创建该文件夹
        if (!folderPath.isDirectory()) {
            folderPath.mkdirs();
        }
        // 生成并设置新文件名
        if (uploadFileName != null) {
            newFileName = UUID.randomUUID() + HH_mm_ss.format(System.currentTimeMillis()) + uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
        }
        try {
            // 将上传文件移动到目标文件夹
            uploadFile.transferTo(new File(folderPath, newFileName));
            // 构建并返回文件的访问URL
            fileUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/upload/txt/" + timePath + "/" + newFileName;
            return new JsonResult<>(fileUrl);
        } catch (IOException e) {
            // 文件上传失败，返回错误信息
            e.printStackTrace();
            return new JsonResult<>("400", "Error uploading file");
        }
    }
}

@Controller
@RequestMapping("/user")
class LoginController {
    /**
     * 登录处理控制器
     *
     * @param request  HttpServletRequest对象，用于接收客户端请求数据
     * @param response HttpServletResponse对象，用于向客户端发送响应
     * @return 返回登录成功后重定向到的URL或登录失败后重新回到登录页面的URL
     */
    @RequestMapping(path = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginController(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, String> hashMap = new HashMap<>(); // 用于存储用户输入的登录信息
        var session = request.getSession(); // 获取当前会话对象
        Cookie cookieUserName;
        Cookie cookiePassword;
        // 从请求中获取用户名和密码，并存入hashMap中
        hashMap.put("username", request.getParameter("username"));
        hashMap.put("password", request.getParameter("password"));
        // 调用Login类的login方法进行登录验证
        if (Login.login(hashMap)) {
            // 登录成功，创建并设置用户名和密码的Cookie
            cookieUserName = new Cookie("username", hashMap.get("username"));
            cookiePassword = new Cookie("password", hashMap.get("password"));
            cookieUserName.setMaxAge(60 * 60 * 24); // 设置Cookie有效期为1天
            cookiePassword.setMaxAge(60 * 60 * 24);
            // 设置会话有效期为1天
            session.setMaxInactiveInterval(60 * 60 * 24);
            response.addCookie(cookieUserName);
            response.addCookie(cookiePassword);
            // 在会话中设置用户名，以便在其他页面中使用
            session.setAttribute("username", hashMap.get("username"));
            // 登录成功，重定向到首页
            return "redirect:/index";
        } else {
            // 登录失败，重定向回登录页面
            return "redirect:/user/login.html";
        }
    }
}

@Controller
class Index {
    /**
     * 处理访问首页的请求。
     * 如果用户已登录（即会话中包含用户名），则显示欢迎页面；
     * 否则，重定向到登录页面。
     *
     * @param request  HttpServletRequest对象，用于获取请求信息
     * @param response HttpServletResponse对象，用于设置响应信息和发送响应
     * @throws IOException 如果发生I/O错误
     */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        var session = request.getSession();
        var username = session.getAttribute("username"); // 尝试从会话中获取用户名
        if (username != null) {
            model.addAttribute("username", username);
        } else {
            // 用户未登录，重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/user/login.html");
        }
        return "index";
    }
}

@RestController
@RequestMapping(path = "/json")
class jsonController {

    @GetMapping(path = "/student")
    public JsonResult<String> getUser(HttpServletRequest request) {
        var application = request.getServletContext();
        return new JsonResult<>((String) application.getAttribute("student"));
    }

    @RequestMapping(path = "/Score")
    public JsonResult<Score> getScore() {
        var score = new Score();
        score.setChinese("68");
        score.setEnglish("78");
        score.setMath("98");
        return new JsonResult<>(score, "成绩");
    }
}