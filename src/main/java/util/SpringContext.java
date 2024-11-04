package util;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"service"})
public class SpringContext {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }


    public static void close() {
        context.close();
    }
}
