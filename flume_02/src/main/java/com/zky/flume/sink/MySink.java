package com.zky.flume.sink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Dawn
 * @Date 2021/2/26 10:58
 * @Desc 自定义flume的sink
 */
public class MySink extends AbstractSink implements Configurable {

    /**
     * 创建logger对象
     */
    public static final Logger LOG = LoggerFactory.getLogger(AbstractSink.class);

    /**
     * 读取配置文件中的信息，
     */
    private String prefix;
    private String suffix;

    @Override
    public void configure(Context context) {
        //读取配置文件内容，有默认值
        prefix = context.getString("prefix", "hello:");

        //读取配置文件内容，无默认值
        suffix = context.getString("suffix");


    }


    @Override
    public Status process() throws EventDeliveryException {
        //声明返回值状态
        Status status;

        //获取当前sink邦定的channel
        Channel channel = getChannel();

        //获取事物
        Transaction transaction = channel.getTransaction();

        //声明Event
        Event event;

        //开启事物
        transaction.begin();

        //读取Channel中的事件，直到读取到事件结束循环
        while (true) {
            event = channel.take();
            if (event != null) {
                break;
            }
        }

        try {
            //处理事件（打印）
            LOG.info(prefix + new String(event.getBody()) + suffix);

            //事务提交
            transaction.commit();
            status = Status.READY;
        } catch (Exception e) {
            //遇到异常，事务回滚
            transaction.rollback();
            status = Status.BACKOFF;
        } finally {
            //关闭事务
            transaction.close();
        }
        return status;
    }


}
