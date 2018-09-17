package ${package_name}.service.impl;
import ${package_name}.po.${table_name};
import ${package_name}.service.I${table_name}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
* 描述：${table_annotation} 服务实现层
* @author ${author}
* @date ${date}
*/
@Service
public class ${table_name}ServiceImpl implements I${table_name}Service {

    @Autowired
    private ${table_name}Mapper ${table_name?uncap_first}Mapper;


    @Override
    public ${table_name} getById(Integer id) throws Exception {
        ${table_name} ${table_name?uncap_first} = ${table_name?uncap_first}Mapper.getById(id);
        return ${table_name?uncap_first};
    }

    @Override
    public ${table_name} save${table_name}(${table_name} ${table_name?uncap_first}) throws Exception {
        ${table_name?uncap_first}Mapper.insert(${table_name?uncap_first});
        return ${table_name?uncap_first};
    }

    @Override
    public void update${table_name}(${table_name} ${table_name?uncap_first})throws Exception {
        ${table_name?uncap_first}Mapper.update(${table_name?uncap_first});
    }

    @Override
    public void delete${table_name}(Integer id) throws Exception{
        ${table_name?uncap_first}Mapper.deleteById(id);
    }

    @Override
    public Page<${table_name}> list(Map<String, Object> param) throws Exception{
        int pageNo=param.get("pageNo")==null?1:Integer.valueOf(param.get("pageNo")+"");
        int pageSize=param.get("pageSize")==null?1:Integer.valueOf(param.get("pageSize")+"");
        PageHelper.startPage(pageNo,pageSize);
        return ${table_name?uncap_first}Mapper.list(param);
    }
}