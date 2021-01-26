package com.nit.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.nit.vo.MockDataVO;
import com.nit.vo.MockNameAndUuidVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "trace_mock")
public class MockData implements Serializable {
    private static final long serialVersionUID = -3693594497168906310L;


    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    String name;

    String uuid;

    String conditions;

    String result;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * type: 0-只有名称和uuid  1-具体明细
     */
    private Integer type;


    public MockNameAndUuidVO convertToNameVO(){
        MockNameAndUuidVO mockNameAndUuidVO = new MockNameAndUuidVO();
        mockNameAndUuidVO.setId(id);
        mockNameAndUuidVO.setName(name);
        mockNameAndUuidVO.setUuid(uuid);
        return mockNameAndUuidVO;
    }

    public MockDataVO convertToVO(){
        MockDataVO mockDataVO = new MockDataVO();
        mockDataVO.setId(id);
        mockDataVO.setName(name);
        mockDataVO.setUuid(uuid);
        mockDataVO.setConditions(conditions);
        mockDataVO.setResult(result);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        mockDataVO.setCreateTime(sdf.format(new Date(createTime.getTime())));
        mockDataVO.setUpdateTime(sdf.format(new Date(updateTime.getTime())));
        return mockDataVO;
    }


}
