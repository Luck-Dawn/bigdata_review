package com.zky.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;

/**
 * @Author Dawn
 * @Date 2021/2/23 17:25
 * @Desc 自定义拦截器
 */
public class CustomInterceptor implements Interceptor {
    public void initialize() {

    }

    /**
     * 单event处理
     *
     * @param event
     * @return
     */
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        if (body[0] < 'z' && body[0] > 'a') {
            event.getHeaders().put("type", "letter");
        } else {
            event.getHeaders().put("type", "number");
        }
        return event;
    }

    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    public void close() {

    }

    public static class Builder implements Interceptor.Builder {
        public Interceptor build() {
            return new CustomInterceptor();
        }

        public void configure(Context context) {

        }
    }
}
