package handler;

import domain.AnchorInfo;
import domain.TaskInfo;
import enums.AnchorState;
import org.springframework.beans.factory.annotation.Autowired;
import utils.LogUtils;

import javax.annotation.PostConstruct;

/**
 * @author gzf
 * 发送给各个渠道的handler
 */
public abstract class BaseHandler implements Handler {
    /**
     * 标示渠道code
     * 子类初始化时指导
     */
    protected Integer channelCode;

    @Autowired
    HandlerHolder handlerHolder;

    /**
     * 初始化渠道与handler的关系
     */
    @PostConstruct
    public void init() {
        handlerHolder.putHandler(channelCode, this);
    }

    @Override
    public void doHandler(TaskInfo taskInfo) {
        if (handler(taskInfo)){
            LogUtils.print(AnchorInfo.builder().state(AnchorState.SEND_SUCCESS.getCode()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
            return;
        }
        LogUtils.print(AnchorInfo.builder().state(AnchorState.SEND_SUCCESS.getCode()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
    }

    /**
     * 统一处理handler接口
     *
     * @param taskInfo 任务信息
     * @return
     */
    public abstract boolean handler(TaskInfo taskInfo);
}
