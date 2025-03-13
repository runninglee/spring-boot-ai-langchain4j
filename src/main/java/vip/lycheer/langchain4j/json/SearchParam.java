package vip.lycheer.langchain4j.json;

import jdk.jfr.Description;
import lombok.Data;

@Data
public class SearchParam {

    @Description("提取小区名称、项目名称、楼盘名称, 未提取为null")
    private String communityName;

    @Description("提取楼栋、幢, 未提取为null")
    private String ridgepoleName;

    @Description("提取单元，未提取为null")
    private String unityName;

    @Description("提取楼层, 未提取为null")
    private String floorName;

    @Description("提取房间号,房间号格式类似:301、A201、1007、03、001等组合， 未提取为null")
    private String doorName;

    @Description("提取面积,包含平方、m²，未提取为null")
    private String areaName;

    @Description("提取室，未提取为null")
    private String roomNum;
}
