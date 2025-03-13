package vip.lycheer.langchain4j.json;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface SearchParamService {

    @SystemMessage("你是一个专业的房产信息提取助手，需严格按规则从文本中提取结构化数据。")
    @UserMessage("""
        从以下输入中提取信息，生成 JSON 格式：
        - 小区名称: 提取小区、项目、楼盘名称，未找到填 null
        - 楼栋: 提取楼栋、幢，未找到填 null
        - 单元: 提取单元，未找到填 null
        - 楼层: 提取楼层，未找到填 null
        - 房间号: 格式如 301、A201 等，未找到填 null
        - 面积: 提取数字，如 100.5 平方，未找到填 null
        - 室的数量: 提取整数，如 3室，未找到填 null

        输入内容：{{msg}}
        """)
    SearchParam json(String msg);
}
