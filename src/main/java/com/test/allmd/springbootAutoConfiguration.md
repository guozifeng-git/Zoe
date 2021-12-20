#SpringBootConfiguration
##@SpringBootApplication:
    @SpringBootConfiguration：底层是@Configuration：使用Configuration配置类等同于XML文件
    @EnableAutoConfiguration：开启自动配置功能
    @ComponentScan：将@Controller/@Service/@Component/@Repository等注解加载到IOC容器中。