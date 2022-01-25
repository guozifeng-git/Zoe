package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信
 * @author gzf
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsContentModel extends ContentModel{
    /**
     * 短信发送内容
     */
    private String content;

    /**
     * 短信发送链接
     * 前端分开写，后续拼接到content
     */
    private String url;
}
