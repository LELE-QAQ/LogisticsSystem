package com.kl.manager.web.realm;

import com.kl.manager.pojo.Role;
import com.kl.manager.pojo.User;
import com.kl.manager.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA
 * Description:
 * Created By KL
 * Date: 2019/12/10
 * Time: 20:37
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) principalCollection.getPrimaryPrincipal();

        List<Role> roleList = userService.queryRoleByUserId(user.getUserId());
        Set<String> roles = new HashSet<>();
        if (roleList != null && roleList.size() > 0) {
            for (Role role : roleList) {
                roles.add(role.getRoleName());
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken tk = (UsernamePasswordToken) token;
        User user = new User();
        user.setUserName(tk.getUsername());
        List<User> userList = userService.query(user);
        if (userList == null || userList.size() != 1) {
            return null;
        }
        user = userList.get(0);
        ByteSource salt = ByteSource.Util.bytes(user.getU1());
        String realm = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), salt, realm);
        return info;
    }

    public static void main(String[] args) {
        String salt = UUID.randomUUID().toString().replace("-","");
        System.out.println(salt);
        SimpleHash md5 = new SimpleHash("MD5", "123456", "123456", 1024);
        System.out.println(md5);
    }
}
