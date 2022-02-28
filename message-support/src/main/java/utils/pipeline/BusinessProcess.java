package utils.pipeline;

/**
 * 业务处理
 * @author gzg
 */
public interface BusinessProcess {
    /**
     * 执行逻辑
     * @param processContext 责任链上下文
     */
    void process(ProcessContext processContext);
}
