package enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 信息状态枚举
 * @author gzf
 */
@Getter
public enum AnchorState {
    //
    RECEIVE(10, "成功消费Kafka"),
    DISCARD(20, "消费被丢弃"),
    CONTENT_DEDUPLICATION(30, "消息被内容去重"),
    RULE_DEDUPLICATION(40, "消息被频次去重"),
    WHITE_LIST(50, "白名单过滤"),
    SEND_SUCCESS(60, "消息下发成功"),
    SEND_FAIL(70, "消息下发失败"),
    CLICK(0100, "消息被点击"),
    ;


    @JsonValue
    @EnumValue
    private final int code;
    private final String description;

    AnchorState(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
