package handler;

import domain.TaskInfo;

/**
 * 消息处理器
 * @author gzf
 */
public interface Handler {
    /**
     * 处理器
     * @param taskInfo 任务信息
     */
    void doHandler(TaskInfo taskInfo);
}
