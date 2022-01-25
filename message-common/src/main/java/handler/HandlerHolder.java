package handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * channel -> handler 映射关系
 *
 * @author gzf
 */
@Component
public class HandlerHolder {
    private Map<Integer, Handler> handlers = new HashMap<>(32);
    public void putHandler(Integer channelCode,Handler handler){
        handlers.put(channelCode, handler);
    }
    public Handler route(Integer channelCode){
        return handlers.get(channelCode);
    }
}
