package xyz.lilei.cgou.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.lilei.cgou.common.entity.Result;
import xyz.lilei.cgou.oauth.util.UserJwt;
import xyz.lilei.cgou.user.feign.UserFeign;

/**
 * @ClassName UserDetailsServiceImpl
 * @Author lilei
 * @Date 11/07/2020 15:42
 * @Version 1.0
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private UserFeign userFeign;

    /**
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // ȡ��������Ϊ��˵��ľ����֤
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //û����֤ͳһ����httpbasic��֤��httpbasic�д洢��client_id��client_secret����ʼ��֤client_id��client_secret
        if (authentication == null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(userName);
            if (clientDetails != null){
                // ��Կ
                String clientSecret = clientDetails.getClientSecret();
                //��̬��ʽ
                //return new User(username,new BCryptPasswordEncoder().encode(clientSecret), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
                return new User(userName, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(userName)){
            return null;
        }

        Result<xyz.lilei.cgou.user.pojo.User> userResult = userFeign.findById(userName);
        String permissions = "salesman,accountant,user";
        return new UserJwt(userName, userResult.getData().getPassword() ,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
    }
}
