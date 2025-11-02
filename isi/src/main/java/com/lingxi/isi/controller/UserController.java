package com.lingxi.isi.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

    @PostMapping("/login/account")
    public Map<String, Object> login(@RequestBody Map<String, String> loginParams) {
        String username = loginParams.get("username");
        String password = loginParams.get("password");
        Boolean autoLogin = Boolean.valueOf(loginParams.getOrDefault("autoLogin", "false"));
        String type = loginParams.getOrDefault("type", "account");

        // 这里应该添加实际的登录验证逻辑
        // 目前只是一个示例实现

        Map<String, Object> result = new HashMap<>();
        if ("admin".equals(username) && "ant.design".equals(password)) {
            result.put("status", "ok");
            result.put("type", type);
            result.put("currentAuthority", "admin");
        } else {
            result.put("status", "error");
            result.put("type", type);
            result.put("currentAuthority", "guest");
        }

        return result;
    }


    @GetMapping("/currentUser")
    public ResponseEntity<Map<String, Object>> getCurrentUser(HttpServletRequest request) {
        // 这里应该从session或token中获取用户信息
        // 示例中直接返回固定用户数据

        Map<String, Object> response = new HashMap<>();

        // 检查用户是否已登录（示例逻辑）
        // 实际项目中应从session或JWT中获取登录状态
        boolean isLoggedIn = true; // 示例值，实际应根据认证状态确定

        if (!isLoggedIn) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("isLogin", false);

            response.put("data", errorData);
            response.put("errorCode", "401");
            response.put("errorMessage", "请先登录！");
            response.put("success", true);

            return ResponseEntity.status(401).body(response);
        }

        // 构造用户信息数据
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", "Serati Ma");
        userData.put("avatar", "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
        userData.put("userid", "00000001");
        userData.put("email", "antdesign@alipay.com");
        userData.put("signature", "海纳百川，有容乃大");
        userData.put("title", "交互专家");
        userData.put("group", "蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED");

        List<Map<String, String>> tags = new ArrayList<>();
        tags.add(createTag("0", "很有想法的"));
        tags.add(createTag("1", "专注设计"));
        tags.add(createTag("2", "辣~"));
        tags.add(createTag("3", "大长腿"));
        tags.add(createTag("4", "川妹子"));
        tags.add(createTag("5", "海纳百川"));
        userData.put("tags", tags);

        userData.put("notifyCount", 12);
        userData.put("unreadCount", 11);
        userData.put("country", "China");
        userData.put("access", "admin"); // 应根据实际权限设置

        Map<String, Object> geographic = new HashMap<>();
        Map<String, String> province = new HashMap<>();
        province.put("label", "浙江省");
        province.put("key", "330000");
        geographic.put("province", province);

        Map<String, String> city = new HashMap<>();
        city.put("label", "杭州市");
        city.put("key", "330100");
        geographic.put("city", city);
        userData.put("geographic", geographic);

        userData.put("address", "西湖区工专路 77 号");
        userData.put("phone", "0752-268888888");

        response.put("success", true);
        response.put("data", userData);

        return ResponseEntity.ok(response);
    }

    private Map<String, String> createTag(String key, String label) {
        Map<String, String> tag = new HashMap<>();
        tag.put("key", key);
        tag.put("label", label);
        return tag;
    }

}
