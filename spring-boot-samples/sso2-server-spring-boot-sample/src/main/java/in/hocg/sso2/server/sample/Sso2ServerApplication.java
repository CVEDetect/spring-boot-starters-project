package in.hocg.sso2.server.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by hocgin on 2020/8/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@SpringBootApplication
public class Sso2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sso2ServerApplication.class, args);
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal me(Principal principal) {
        return principal;
    }
}
