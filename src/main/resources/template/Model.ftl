package ${package_name}.model;
import com.evada.inno.common.domain.BaseModel;
import javax.persistence.*;
import java.util.Date;

/**
* 描述：${table_annotation}模型
* @author ${author}
* @date ${date}
*/
public class ${table_name}{

<#if model_column?exists>
    <#list model_column as model>
    /**
    *${model.columnComment!}
    */
        <#if (model.columnType?lower_case = 'varchar' || model.columnType?lower_case = 'text')>
        private String ${model.changeColumnName?uncap_first};

        </#if>

        <#if (model.columnType?lower_case = 'int' || model.columnType?lower_case = 'integer')>
        private Integer ${model.changeColumnName?uncap_first};

        </#if>

        <#if model.columnType?lower_case = 'timestamp' >
        private Date ${model.changeColumnName?uncap_first};

        </#if>
    </#list>
</#if>

<#if model_column?exists>
    <#list model_column as model>
        <#if (model.columnType?lower_case = 'varchar' || model.columnType?lower_case = 'text')>
        public String get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
        }

        public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
        }

        </#if>
        <#if (model.columnType?lower_case = 'int' || model.columnType?lower_case = 'integer')>
        public Integer get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
        }

        public void set${model.changeColumnName}(Integer ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
        }

        </#if>

        <#if model.columnType?lower_case = 'timestamp' >
        public Date get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
        }

        public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
        }

        </#if>
    </#list>
</#if>

}