package com.sip.bean;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用报表bean
 */
@Component("sipReportBean")
public class SipReportBean {

    /**
     * 构建报表
     *
     * @param dsName      数据源名称
     * @param datasetName 数据集名称
     * @param parameters  参数
     * @return List<Map < String, Object>>
     */
    public List<Map<String, Object>> buildReport(String dsName, String datasetName, Map<String, Object> parameters) {
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("name", "aaaaa");
        tmp.put("age", "12");
        data.add(tmp);
        return data;
    }
}
