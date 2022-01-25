package enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import dto.EmailContentModel;
import dto.ImContentModel;
import dto.PushContentModel;
import dto.SmsContentModel;

/**
 * 发送渠道类型枚举
 * @author gzf
 */

public enum ChqnnelType {
    //
    IM(10, "IM(站内信)", ImContentModel.class, "im"),
    PUSH(20, "push(通知栏)", PushContentModel.class, "push"),
    SMS(30, "sms(短信)", SmsContentModel.class, "sms"),
    EMAIL(40, "email(邮件)", EmailContentModel.class, "email"),
    OFFICIAL_ACCOUNT(50, "OfficialAccounts(服务号)", OfficialAccountsContentModel.class, "official_accounts"),
    MINI_PROGRAM(60, "miniProgram(小程序)", MiniProgramContentModel.class, "mini_program"),
    ;
    ;


    @JsonValue
    @EnumValue
    /**
     * 编码值
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String description;

    /**
     * 内容模型Class
     */
    private final Class contentModelClass;

    /**
     * 英文标识
     */
    private final String codeEn;

    ChqnnelType(Integer code, String description, Class contentModelClass, String codeEn) {
        this.code = code;
        this.description = description;
        this.contentModelClass = contentModelClass;
        this.codeEn = codeEn;
    }
}
