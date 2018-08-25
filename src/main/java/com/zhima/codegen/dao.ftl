package ${package_name}.mapper;
import ${package_name}.po.${table_name};

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* 描述：${table_annotation} mapper层
* @author ${author}
* @date ${date}
*/
@Mapper
public interface ${table_name}Mapper {

     ${table_name} getById(Integer id);

     int insert(${table_name} ${table_name?uncap_first});

     void update(${table_name} ${table_name?uncap_first});

     Page<${table_name}> list(Map<String,Object> param);

     void delete(Integer id);
}