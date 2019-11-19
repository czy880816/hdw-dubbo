package com.hdw.dao;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @Author TuMinglong
 * @email tuminglong@126.com
 * @Date  2018/12/21 14:17
 */
public interface SysGeneratorDao {
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
}
