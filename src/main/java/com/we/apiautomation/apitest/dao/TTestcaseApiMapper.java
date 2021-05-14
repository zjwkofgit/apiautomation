package com.we.apiautomation.apitest.dao;

import com.we.apiautomation.apitest.model.TTestcaseApi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TTestcaseApiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TTestcaseApi record);

    int insertSelective(TTestcaseApi record);

    TTestcaseApi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TTestcaseApi record);

    int updateByPrimaryKey(TTestcaseApi record);

    List<TTestcaseApi> findByAll(TTestcaseApi tTestcaseApi);

    List<TTestcaseApi> findByCaseTypeAndProjectId(@Param("caseType") Integer caseType, @Param("projectId") Long projectId);

    TTestcaseApi selectByIdAndCaseType(@Param("id") Long id, @Param("caseType") Integer caseType);

    List<TTestcaseApi> findByProjectIdAndName(@Param("projectId") Long projectId, @Param("name") String name);

    List<TTestcaseApi> findByProjectIdAndNameAndIdNot(@Param("projectId") Long projectId, @Param("name") String name, @Param("notId") Long notId);

    @Select(value = "select t.* from t_testcase_api t ,t_suite_case_api ta where ta.case_id = t.id and ta.suite_id = #{id} order by ta.sort")
    List<TTestcaseApi> findBySuiteId(Long id);
}
