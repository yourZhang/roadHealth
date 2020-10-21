package cn.zys.config;

/**
 * @program: Security02-anno
 * @description:
 * @author: xiaozhang6666
 * @create: 2020-10-07 16:24
 **/

import cn.zys.common.OftenFinalMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * 1.添加@EnableWebSecurity  创建过滤器执行链给spring容器管理 springSecurityFilterChain
 * 2.继承WebSecurityConfigurerAdapter 目的是重写父类的配置方法 应用自己添加的配置
 * 3.注解@EnableGlobalMethodSecurity  开启注解拦截方法的请求
 * prePostEnabled 设置在请求方法之前拦截auth方法，进行权限校验
 */
@Configuration
@EnableWebSecurity
@ComponentScan("cn.zys")
@Order(-1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    /**
     * configure(AuthenticationManagerBuilder auth)方法是用于配置
     * 权限验证登陆管理
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);

        /**
         * 自定义配置验证的userDetailsService类 : 目的是查询数据库读取所有的用户权限
         * 采用密文验证  :passwordEncoder
         * 加密方式为      :getBCryptPasswordEncoder()
         */
        auth.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }

    /**
     * 将加密方式的配置对象送入容器
     */
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * configure(HttpSecurity http) 用于请求回调拦截的配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                //处理跨域请求中的Preflight请求
//                .antMatchers(HttpMethod.OPTIONS, "/**")
//                .permitAll()
//                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
//                .permitAll()
                //添加不需要认证的路径
//                .antMatchers("/test/**").hasAnyAuthority("READER", "update")
                //任何请求需要认证
                .anyRequest().authenticated().and()
                //权限前缀不要给
                //关闭csrf 关闭跨域的验证
//                .and()
//                .cors()
//                .and()
//                .csrf().disable()
                //自定义配置登陆页面
                .formLogin().loginPage("http://127.0.0.1:8083/pages/login.html")
                //自定义登陆请求地址
                .loginProcessingUrl("/sec/login")
                //验证成功的跳转页面
//                .defaultSuccessUrl("/main.jsp", true)
                .successForwardUrl("/user/loginSuccess")
                //验证失败的跳转路径
                .failureForwardUrl("/user/loginFail")
                //退出的请求路径
                .and().logout().logoutUrl("/sec/logout")
                //退出请求后销毁session
                .invalidateHttpSession(true)
                //退出成功跳转的页面
                .logoutSuccessUrl("http://127.0.0.1:8083/pages/login.html");
    }

    //    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(OftenFinalMessage.Host_Addr));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "HEAD", "POST", "DELETE", "PATCH"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
