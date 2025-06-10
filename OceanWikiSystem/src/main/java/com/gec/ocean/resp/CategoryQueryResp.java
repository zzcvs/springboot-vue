package com.gec.ocean.resp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

/**
 * @author cr
 * @date 2023年11月13日 9:46
 * @description 分类检索返回对象
 */
@Data
@ToString
public class CategoryQueryResp {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    //父id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parent;
    //名称
    private String name;
    //顺序
    private Integer sort;
}
