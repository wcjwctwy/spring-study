package cn.sf80.study.spring.application.context.lifecycle;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.Lifecycle;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;

/**
 * Spring Shutdown Hook 线程示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Lifecycle
 * @since
 */
public class SpringShutdownHookThreadDemo {

    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();

        context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.printf("[线程 %s] ContextClosedEvent 处理\n", Thread.currentThread().getName());
            }
        });

        // 刷新 Spring 应用上下文
        context.refresh();

        // 注册 Shutdown Hook
        context.registerShutdownHook();

        System.out.println("按任意键继续并且关闭 Spring 应用上下文");
        System.in.read();

        // 关闭 Spring 应用（同步）
        context.close();
    }
}
