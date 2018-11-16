package com.zhima.codegen;

import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by superz on 2018/8/22.
 */
public class CodeGenerateUtils {
    private  String author = "superz";
    private  String tableName = "t_funds";
    private  String packageName = "com.tangshixiong.funds";
    private  String tableAnnotation = "";
    private  String url = "jdbc:mysql://rm-m5e105ep54w2y9i792o.mysql.rds.aliyuncs.com/tsx";
    private  String user = "tsxdba";
    private  String password = "@Tsx2017";
    private  String driver = "com.mysql.jdbc.Driver";
    private  String diskPath = "D:/myCodeGen/";
    private  String changeTableName = replaceUnderLineAndUpperCase(convertTableNameToClassName(tableName));

    public Connection getConnection() throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(1);
        Class.forName(driver);
        Connection connection= DriverManager.getConnection(url, user, password);
        return connection;
    }

//    public static void main(String[] args) throws Exception{
//        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
//        codeGenerateUtils.generate();
//    }

    public void generate() throws Exception{
        try {
            System.out.println(tableName);
            Connection connection = getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet idSet=databaseMetaData.getPrimaryKeys(connection.getCatalog(),connection.getSchema(),tableName);
            idSet.next();
            String id=idSet.getString("COLUMN_NAME");

            //TODO 默认id 为Integer

            ResultSet resultSet = databaseMetaData.getColumns(null,"%", tableName,"%");
            //生成Mapper文件
            generateMapperFile(resultSet,id);
            //生成Dao文件
            generateDaoFile(resultSet);
            //生成Repository文件
//            generateRepositoryFile(resultSet);
            //生成服务层接口文件
            generateServiceInterfaceFile(resultSet);
            //生成服务实现层文件
            generateServiceImplFile(resultSet);
            //生成Controller层文件
            generateControllerFile(resultSet);
            //生成DTO文件
           // generateDTOFile(resultSet);
            //生成Model文件
            generateModelFile(resultSet,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{

        }
    }

    private void generateModelFile(ResultSet resultSet,String id) throws Exception{

        final String suffix = ".java";
        final String path = diskPath + "po/"+changeTableName + suffix;
        final String templateName = "Model.ftl";
        File mapperFile = new File(path);
        if(!mapperFile.getParentFile().exists()){
            mapperFile.getParentFile().mkdirs();
        }
        List<ColumnClass> columnClassList = new ArrayList<>();
        ColumnClass columnClass = null;
        resultSet.beforeFirst();
        while(resultSet.next()){
            //id字段略过
           // if(resultSet.getString("COLUMN_NAME").equals(id)) continue;
            columnClass = new ColumnClass();
            //获取字段名称
            columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
            //获取字段类型
            columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
            //转换字段名称，如 sys_name 变成 SysName
            columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
            //字段在数据库的注释
            columnClass.setColumnComment(resultSet.getString("REMARKS"));
            columnClassList.add(columnClass);
        }
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column",columnClassList);
        System.out.println(columnClassList);
        dataMap.put("id",id);
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }

    private void generateDTOFile(ResultSet resultSet) throws Exception{
        final String suffix = "DTO.java";
        final String path = "D://" + changeTableName + suffix;
        final String templateName = "DTO.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateControllerFile(ResultSet resultSet) throws Exception{
        final String suffix = "Controller.java";
        final String path = diskPath + "controller/"+changeTableName + suffix;
        final String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        if(!mapperFile.getParentFile().exists()){
            mapperFile.getParentFile().mkdirs();
        }
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceImplFile(ResultSet resultSet) throws Exception{
        final String suffix = "ServiceImpl.java";
        final String path = diskPath + "service/"+changeTableName + suffix;
        final String templateName = "Service.ftl";
        File mapperFile = new File(path);
        if(!mapperFile.getParentFile().exists()){
            mapperFile.getParentFile().mkdirs();
        }
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceInterfaceFile(ResultSet resultSet) throws Exception{
        final String prefix = "I";
        final String suffix = "Service.java";
        final String path = diskPath + "service/"+prefix + changeTableName + suffix;
        final String templateName = "interface.ftl";
        File mapperFile = new File(path);
        if(!mapperFile.getParentFile().exists()){
            mapperFile.getParentFile().mkdirs();
        }
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateRepositoryFile(ResultSet resultSet) throws Exception{
        final String suffix = "Repository.java";
        final String path = diskPath + changeTableName + suffix;
        final String templateName = "Repository.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateDaoFile(ResultSet resultSet) throws Exception{
        final String suffix = "Mapper.java";
        final String path = diskPath + "mapper/"+changeTableName + suffix;
        final String templateName = "DAO.ftl";
        File mapperFile = new File(path);
        if(!mapperFile.getParentFile().exists()){
            mapperFile.getParentFile().mkdirs();
        }
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }

    private void generateMapperFile(ResultSet resultSet,String id) throws Exception{
        final String suffix = "Mapper.xml";
        final String path = diskPath + convertTableNameToClassName(tableName)+"/"+changeTableName + suffix;
        final String templateName = "Mapper.ftl";
        File mapperFile = new File(path);

        if(!mapperFile.getParentFile().exists()){
            mapperFile.getParentFile().mkdirs();
        }
            ColumnClass columnClass = null;
        List<ColumnClass> columnClassList = new ArrayList<>();
        while(resultSet.next()){
            //id字段略过
             if(resultSet.getString("COLUMN_NAME").equals(id)) continue;
            columnClass = new ColumnClass();
            //获取字段名称
            columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
            //获取字段类型
            columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
            //转换字段名称，如 sys_name 变成 SysName
            columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
            //字段在数据库的注释
            columnClass.setColumnComment(resultSet.getString("REMARKS"));
            columnClassList.add(columnClass);
        }
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("model_column",columnClassList);
        dataMap.put("id",id);
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }

    private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap) throws Exception{
        Template template = com.zhima.codegen.FreemarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name_small",tableName);
        dataMap.put("table_name",changeTableName);
        dataMap.put("author",author);
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        dataMap.put("date",sdf.format(date));
        dataMap.put("package_name",packageName);
        dataMap.put("table_annotation",tableAnnotation);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }

    public String convertTableNameToClassName(String str){
        //去除t_前缀
        if(str.startsWith("t_")){
            str=str.substring(2);
        }
        return str;
    }

    public String replaceUnderLineAndUpperCase(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count , count + 1,ia + "");
            }
        }
        String result = sb.toString().replaceAll("_","");
        return StringUtils.capitalize(result);
    }



    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        changeTableName = replaceUnderLineAndUpperCase(convertTableNameToClassName(tableName));
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTableAnnotation() {
        return tableAnnotation;
    }

    public void setTableAnnotation(String tableAnnotation) {
        this.tableAnnotation = tableAnnotation;
    }

    public String getDiskPath() {
        return diskPath;
    }

    public void setDiskPath(String diskPath) {
        this.diskPath = diskPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getChangeTableName() {
        return changeTableName;
    }

    public void setChangeTableName(String changeTableName) {
        this.changeTableName = changeTableName;
    }

}
