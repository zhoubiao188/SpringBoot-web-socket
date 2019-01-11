package cn.com.scitc.utils;

import cn.com.scitc.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class HrUtils {
    public static User getCurrentHr() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}