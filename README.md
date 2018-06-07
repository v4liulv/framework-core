# framework-core
框架基础部分

提供Java基础的框架和工具类使用，其中包含
- 工具类-日期转换
- DB Oracle数据连接池
- File Properties文件读取工具
- UUID生成工具
- 内置WebService连接工具等

## core-site.xml

基础核心的配置文件
```xml
<configuration>
 <property>
        <name>o_db_url</name>
        <value>jdbc:oracle:thin:@//localhost:1521/orcl</value>
    </property>
    <property>
        <name>o_db_username</name>
        <value>YY_GXKSH_ZSB</value>
    </property>
    <property>
        <name>o_db_password</name>
        <value>123456</value>
    </property>
    <property>
        <name>MODEL_TYPE_CODE</name>
        <value>002</value>
    </property>
    <property>
        <name>CONFIG_TABLE_NAME_CODE</name>
        <value>CONFIG</value>
    </property>
</configuration>
```

## 