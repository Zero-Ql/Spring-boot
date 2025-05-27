package com.example.springbootdao.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: QSky
 * @date: 2024/5/15 上午10:27
 * @project: Spring-boot
 * @package: com.example.springbootdao
 */

@Data
//@Repository
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @TableId(value = "id")
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private int age;

    @TableField(value = "email")
    private String email;

    public String getCount() {
        return "count(age)";
    }
}
