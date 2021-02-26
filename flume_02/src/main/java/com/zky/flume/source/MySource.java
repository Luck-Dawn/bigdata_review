package com.zky.flume.source;

import org.apache.flume.Context;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.util.HashMap;

/**
 * @Author Dawn
 * @Date 2021/2/23 17:27
 * @Desc 自定义source
 */
public class MySource extends AbstractSource implements Configurable, PollableSource {


    /**
     * 读取配置文件中将要读取的字段
     */
    private Long delay;
    private String field;

    /**
     * 初始化配置
     *
     * @param context
     */
    @Override
    public void configure(Context context) {
        delay = context.getLong("delay");
        field = context.getString("field", "Hello Dawn!");
    }

    /**
     * 将自定义的数据封装成一个event
     *
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        try {
            //创建event的头信息
            HashMap<String, String> headerMap = new HashMap<>();
            //创建event
            SimpleEvent event = new SimpleEvent();

            //循环封装事件
            for (int i = 0; i < 10; i++) {
                //事件设置头信息
                event.setHeaders(headerMap);
                //事件设置body信息
                event.setBody((field + i).getBytes());

                //将事件写入到channel中
                getChannelProcessor().processEvent(event);
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Status.BACKOFF;
        }
        return Status.READY;
    }

    @Override
    public long getBackOffSleepIncrement() {
        return 0;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 0;
    }

}
