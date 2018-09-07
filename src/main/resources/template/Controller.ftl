package ${package_name}.controller;
import ${package_name}.service.I${table_name}Service;
import ${package_name}.po.${table_name};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* 描述：${table_annotation}控制层
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping(value = "/${table_name?uncap_first}", produces = "application/json;charset=UTF-8")
public class ${table_name}Controller {

    @Autowired
    private I${table_name}Service ${table_name?uncap_first}Service;

    /**
    * 描述：根据Id 查询
    * @param id  ${table_annotation}id
    */
    @PostMapping(value = "/findById")
    public JsonResult findById(@RequestParam(value="id") String id)throws Exception {
        JsonResult jsonResult=new JsonResult();
        ${table_name} ${table_name?uncap_first} = ${table_name?uncap_first}Service.getById(Integer.valueOf(id));
        jsonResult.setData(${table_name?uncap_first});
        return jsonResult;
    }

    /**
    * 描述:创建${table_annotation}
    * @param ${table_name?uncap_first}  ${table_annotation}
    */
    @PostMapping(value = "/save")
    public JsonResult save(@RequestBody ${table_name} ${table_name?uncap_first}) throws Exception {
        JsonResult jsonResult=new JsonResult();
        ${table_name?uncap_first}=${table_name?uncap_first}Service.save${table_name}(${table_name?uncap_first});
        return jsonResult;
    }

    /**
    * 描述：删除${table_annotation}
    * @param id ${table_annotation}id
    */
    @PostMapping(value = "/delete")
    public JsonResult deleteById(@RequestParam(value="id") String id) throws Exception {
        JsonResult jsonResult=new JsonResult();
        ${table_name?uncap_first}Service.delete${table_name}(Integer.valueOf(id));
        return jsonResult;
    }

    /**
    * 描述：更新${table_annotation}
    * @param  ${table_annotation} ${table_name?uncap_first}
    */
    @PostMapping(value = "/update")
    public JsonResult update${table_name}(@RequestBody ${table_name} ${table_name?uncap_first}) throws Exception {
        JsonResult jsonResult=new JsonResult();
        ${table_name?uncap_first}Service.update${table_name}(${table_name?uncap_first});
        return jsonResult;
    }

}