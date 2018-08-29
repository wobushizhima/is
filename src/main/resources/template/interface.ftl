package ${package_name}.service;
import ${package_name}.po.${table_name};
/**
* 描述：${table_annotation} 服务实现层接口
* @author ${author}
* @date ${date}
*/
public interface I${table_name}Service{

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    ${table_name} getById(Integer id)throws Exception;

    ${table_name} save${table_name}(${table_name} ${table_name?uncap_first}) throws Exception;

    void delete${table_name}(Integer id) throws Exception;

    void update${table_name}(${table_name} ${table_name?uncap_first}) throws Exception;

}