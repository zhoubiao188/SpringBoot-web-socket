package cn.com.scitc.service.impl;

import cn.com.scitc.base.BusinessException;
import cn.com.scitc.entity.Role;
import cn.com.scitc.entity.User;
import cn.com.scitc.repository.RoleRepository;
import cn.com.scitc.repository.UserRepository;
import cn.com.scitc.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    @Override
    public User addUser(String username, String password, String nickname) {


        if (userRepository.findByUsername(username) !=null){

            throw new BusinessException("用户名重复:" + username);
        }
        User user = new User();
        user.setId(user.getId());
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));//密码才用md5加密
        user.setHeadimg("http://www.gravatar.com/avatar/");//默认头像
        user.setUserType(user.getUserType());//默认为普通用户
        Date now = new Date();
        user.setCreateTime(now);
        user.setLastLoginTime(now);
        user = userRepository.save(user);

        Role role = new Role();
        role.setName("USER");//默认为USER
        role.setUserId(user.getId());

        roleRepository.save(role);

        user.setAuthorityList(Lists.newArrayList(new SimpleGrantedAuthority("ROLE_USER")));


        return user;
    }

    @Override
    public User findByUsernameUser(String username) {
        return userRepository.findByUsername(username);
    }
}
