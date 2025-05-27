package com.example.springbootservice.Iservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootdao.Mapper.UserMapper;
import com.example.springbootdao.pojo.User;
import com.example.springbootservice.Iservice.UserIService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: QSky
 * @date: 2024/5/15 上午10:40
 * @project: Spring-boot
 * @package: com.example.springbootservice
 */

@Service // 标示一个Spring框架的服务组件
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserIService {
    @Resource // 引入UserMapper资源
    private UserMapper userMapper;

    /**
     * 查找所有用户信息
     *
     * @return 返回用户列表
     */
    public List<User> findAllUser(QueryWrapper queryWrapper) {
        return super.getBaseMapper().selectList(queryWrapper);
    }

    /**
     * 保存用户信息
     *
     * @param userId    用户ID
     * @param userName  用户名
     * @param userAge   用户年龄
     * @param userEmail 用户邮箱
     * @return 返回保存结果，成功为true，失败为false
     */
    public boolean saveUser(Long userId, String userName, int userAge, String userEmail) {
        User user = new User(userId, userName, userAge, userEmail);
        return super.save(user); // 调用父类的保存方法
    }

    public boolean saveBatchUser(List<User> userList) {
        return super.saveBatch(userList);
    }

    public boolean saveBatchUser(List<User> userList, int batchSize) {
        return batchSize > 0 ? super.saveBatch(userList, batchSize) : super.saveBatch(userList);
    }

    public boolean saveOrUpdateUser(Long userId, String userName, int userAge, String userEmail) {
        User user = new User(userId, userName, userAge, userEmail);
        return super.saveOrUpdate(user);
    }

    public boolean saveOrUpdateBatchUser(List<User> user, int batchSize) {
        return super.saveOrUpdateBatch(user, batchSize);
    }

    /**
     * 从当前对象中移除一个特定的查询包装器。
     * 这个方法通过构建一个包含特定 "id" 和 "name" 键值对的HashMap，然后调用父类的removeByMap方法来实现。
     *
     * @return boolean 如果成功移除查询包装器则返回true，否则返回false。
     */

    public boolean remove(QueryWrapper queryWrapper) {
        return super.remove(queryWrapper);
    }

    public boolean removeQueryWrapper() {
        // 使用双重括号初始化一个HashMap，并立即在内部填充键值对
        Map<String, Object> map = new HashMap<>();
        map.put("id", 20);
        map.put("name", "QSky");
        return super.removeByMap(map);
//        return super.removeByMap(new HashMap<>() {{ put("id", 17); put("name", "QSky"); }});
    }

    public boolean removeByIDs(List<Integer> list) {
        return super.removeByIds(list);
    }

    public List<Map<String, Object>> getMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age", 20);
        return super.listMaps();
    }

    public List<User> lists(Map<String, Object> map) {
        return super.listByMap(map);
    }

    public List<Map<String, Object>> getPages(QueryWrapper queryWrapper) {
        IPage<Map<String, Object>> userPageMaps = super.pageMaps(new Page<>(1, 10), queryWrapper);
        List<Map<String, Object>> userMapList = userPageMaps.getRecords();
        return userMapList;
    }

    /**
     * 根据年龄查找用户
     *
     * @param age 年龄条件
     * @return 返回年龄大于指定条件的用户列表
     */
    @Override
    public List<User> findUsersByAgeGreaterThan(int age) {
        // 使用QueryWrapper构建查询条件，查询年龄大于指定值的用户
        return userMapper.selectList(new QueryWrapper<User>().gt("age", age));
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }
}

