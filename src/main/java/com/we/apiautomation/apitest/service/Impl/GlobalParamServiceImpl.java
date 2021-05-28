package com.we.apiautomation.apitest.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.we.apiautomation.apitest.dao.GlobalParamMapper;
import com.we.apiautomation.apitest.model.GlobalParam;
import com.we.apiautomation.apitest.model.po.GlobalVar;
import com.we.apiautomation.apitest.service.GlobalParamService;
import com.we.apiautomation.apitest.utils.Convert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GlobalParamServiceImpl implements GlobalParamService {

    @Resource
    private GlobalParamMapper globalParamMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return globalParamMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(GlobalParam record) {
        return globalParamMapper.insertSelective(record);
    }

    @Override
    public GlobalParam selectByPrimaryKey(Long id) {
        return globalParamMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GlobalParam record) {
        return globalParamMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GlobalParam record) {
        return globalParamMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<GlobalParam> findByAllwithPage(int page, int pageSize, GlobalParam globalParam) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(globalParamMapper.findByAll(globalParam));
    }

    @Override
    public List<GlobalParam> findByParamNameAndProjectIdAndType(String paramName, Long projectId, Integer type) {
        return globalParamMapper.findByParamNameAndProjectIdAndType(paramName, projectId, type);
    }

    @Override
    public List<GlobalParam> findByParamNameAndProjectIdAndTypeAndIdNot(String paramName, Long projectId, Integer type, Long notId) {
        return globalParamMapper.findByParamNameAndProjectIdAndTypeAndIdNot(paramName, projectId, type, notId);
    }

    @Override
    public int insert(GlobalParam record) {
        return globalParamMapper.insert(record);
    }

    @Override
    public Map<String, Object> findByProjectIdAndTypeAndEnvId(Long projectId, Integer type, Long envId) {
        Map<String, Object> vars = new ConcurrentHashMap<String, Object>();
        List<GlobalParam> globalParams = globalParamMapper.findByProjectIdAndType(projectId, type);
        for (GlobalParam globalParam : globalParams) {
            List<GlobalVar> envVars = globalParam.getEnvVars();
            if (envVars != null) {
                GlobalVar globalVar = envVars.stream().filter(ev -> ev.getEnvId() == envId).findFirst().orElse(null);
                if (globalVar != null) {
                    globalParam.setParamValue(globalVar.getValue());
                }
            }
            if (globalParam.getParamType().equals(2)) {
                try {
                    vars.put(globalParam.getParamName(), Convert.toDouble(globalParam.getParamValue(), 0D));
                } catch (Exception e) {
                    vars.put(globalParam.getParamName(), null);
                }
            } else if (globalParam.getParamType().equals(3)) {
                try {
                    if (globalParam.getParamValue().toLowerCase().equals("true")) {
                        vars.put(globalParam.getParamName(), true);
                    } else {
                        vars.put(globalParam.getParamName(), false);
                    }
                } catch (Exception e) {
                    vars.put(globalParam.getParamName(), null);
                }
            } else {
                vars.put(globalParam.getParamName(), globalParam.getParamValue());
            }
        }
        return vars;
    }
}






