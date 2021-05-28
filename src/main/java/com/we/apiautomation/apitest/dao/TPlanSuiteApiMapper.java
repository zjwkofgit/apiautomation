package com.we.apiautomation.apitest.dao;

import com.we.apiautomation.apitest.dto.TPlanSuiteApiDto;
import com.we.apiautomation.apitest.model.TPlanSuiteApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TPlanSuiteApiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TPlanSuiteApi record);

    int insertSelective(TPlanSuiteApi record);

    TPlanSuiteApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TPlanSuiteApi record);

    int updateByPrimaryKey(TPlanSuiteApi record);

    int deleteByJobId(@Param("jobId") Long jobId);

    List<TPlanSuiteApi> findByJobId(@Param("jobId") Long jobId);

    int deleteBySuiteId(@Param("suiteId")Long suiteId);

    @Select("SELECT tf.name AS suite_name ,ta.* FROM t_plan_suite_api ta INNER JOIN t_testsuite_api tf ON ta.suite_id = tf.id AND ta.job_id = #{jobId}")
    List<TPlanSuiteApiDto> findDtoByJobId(Long jobId);

    Long countByJobId(@Param("jobId")Long jobId);

}
