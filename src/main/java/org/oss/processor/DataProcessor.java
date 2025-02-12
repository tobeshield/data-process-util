package org.oss.processor;


import com.alibaba.fastjson.JSONObject;
import org.oss.util.CommonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据处理
 */
@Component
public class DataProcessor {

    @Autowired
    @Qualifier("sourceJdbcTemplate")
    protected JdbcTemplate sourceJdbcTemplate;

    @Autowired
    @Qualifier("targetJdbcTemplate")
    protected JdbcTemplate targetJdbcTemplate;

    public void run() throws Exception {
        processData();
    }

    /**
     * 处理数据并保存到文件中
     * @throws IOException
     */
    private void processData() throws IOException {
        List<Map<String, Object>> sourceDataList = sourceJdbcTemplate.queryForList("查询源数据库的sql");
        List<Map<String, Object>> targetDataList = targetJdbcTemplate.queryForList("查询目的数据库的sql");

//        FileUtils.writeLines(new File("你的数据存储文件路径"), "UTF-8", lines, false);
    }


}
