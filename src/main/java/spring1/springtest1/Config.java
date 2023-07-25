package spring1.springtest1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  //此类是一个配置类(这个类也会被spring创建),声明容器运行时的一些配置信息(1.扫描的路径,这个路劲所有带有
// @Component,@Repository,@Service,@Controller  会被Spring 托管)

@ComponentScan(basePackages ={"spring1"})

public class Config {
}
