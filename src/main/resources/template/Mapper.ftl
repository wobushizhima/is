<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package_name}.mapper.${table_name}Mapper">

    <resultMap id="${table_name}ResultMap" type="${package_name}.po.${table_name}"></resultMap>

    <sql id="findSql">
        select t.* from  ${table_name_small} t
    </sql>

    <insert id="insert" parameterType="${package_name}.po.${table_name}">
        insert into ${table_name_small} (
    <#if model_column?exists>
        <#list model_column as model>
            ${model.columnName?uncap_first}<#if model_has_next>,</#if>
        </#list>
    </#if>
        )
        values (
    <#if model_column?exists>
        <#list model_column as model>
        ${r'#{'}${model.changeColumnName?uncap_first},jdbcType=<#if model.columnType=='INT'>INTEGER<#elseif  model.columnType=='LONGTEXT'>LONGVARCHAR<#else>${model.columnType}</#if>}<#if model_has_next>,</#if>
        </#list>
    </#if>
        )
    </insert>

    <update id="update" parameterType="${package_name}.po.${table_name}">
        update ${table_name_small}
        <set>
    <#if model_column?exists>
         <#list model_column as model>
         <if test="${model.changeColumnName?uncap_first} != null">  ${model.columnName?uncap_first} = ${r'#{'}${model.changeColumnName?uncap_first},jdbcType=<#if model.columnType=='INT'>INTEGER<#elseif  model.columnType=='LONGTEXT'>LONGVARCHAR<#else>${model.columnType}</#if>}</if>
         </#list>
    </#if>
        </set>
    </update>

    <delete id="deleteById" parameterType="java.lang.Integer" >
        delete from ${table_name_small}
        <where>
            and t.${id} = ${r'#{'}${id},jdbcType=INTEGER}
        </where>
    </delete>

    <select id="getById" parameterType="java.lang.Integer" resultMap="${table_name}ResultMap">
        <include refid="findSql"></include>
        <where>
            and t.${id} = ${r'#{'}${id},jdbcType=INTEGER}
        </where>
    </select>

    <select id="list" resultMap="${table_name}ResultMap">
        <include refid="findSql" />
        <where>
    <#if model_column?exists>
        <#list model_column as model>
            <if test="${model.changeColumnName?uncap_first} != null"> and t.${model.columnName?uncap_first} = ${r'#{'}${model.changeColumnName?uncap_first},jdbcType=${(model.columnType=='INT')?string('INTEGER',model.columnType)}}</if>
        </#list>
     </#if>
        </where>
    </select>


</mapper>