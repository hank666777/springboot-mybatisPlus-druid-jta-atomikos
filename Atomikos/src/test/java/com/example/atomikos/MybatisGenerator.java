package com.example.atomikos;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * 請參閱官方說明文件
 * @see https://baomidou.com/pages/981406/#%E5%9F%BA%E7%A1%80%E9%85%8D%E7%BD%AE
 */
public class MybatisGenerator {

    @Test
    public void autoGenerator() {
        // 配置資料庫
        DataSourceConfig dataSourceConfig = new DataSourceConfig
                .Builder("jdbc:mysql://127.0.0.1:3306/atomikos_test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                "root",
                "123456")
//                .Builder("jdbc:sqlserver://localhost:1433; databaseName=atomikos_test",
//                "sa",
//                "<asdf1234")
//                .schema("product")
                .build();

        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);

        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir(projectPath + "/src/main/java")
                .disableOpenDir()
                .author("Hank")
                // 使用openAPI 請勿開啟
//                .enableSwagger()
                // 日期格式使用java 8 java.time
                .dateType(DateType.TIME_PACK)
                .build();

        PackageConfig packageConfig = new PackageConfig.Builder()
                .moduleName("demo1")
                .parent("com.example.atomikos")
                .entity("vo")
                .service("service.secondary")
                .serviceImpl("service.secondary.impl")
//                .controller("controller")
                .other("common")
//                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\mygithub\\mybatis_demo\\src\\main\\resources\\mapper"))
                .build();

        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY)
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
//                .mapperXml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();

        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode()
                .enableSkipView()
                .disableSqlFilter()
                .likeTable(new LikeTable("USER"))
                .addInclude("t_simple")
                .addTablePrefix("t_", "c_")
                .addFieldSuffix("_flag")
                .build();

        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig)
                .global(globalConfig)
                .packageInfo(new PackageConfig.Builder()
                        .moduleName("demo1")
                        .parent("com.example.mybatis_demo")
                        .entity("vo")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .other("commons")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\mygithub\\mybatis_demo\\src\\main\\resources\\mapper"))
                        .build())
//                .template(new TemplateConfig.Builder()
//                        .disable(TemplateType.ENTITY)
//                        .entity("/templates/entity.java")
//                        .service("/templates/service.java")
//                        .serviceImpl("/templates/serviceImpl.java")
//                        .mapper("/templates/mapper.java")
//                        .mapperXml("/templates/mapper.xml")
//                        .controller("/templates/controller.java")
//                        .build())
                .strategy(new StrategyConfig.Builder()
                        // 設置需要生成的表名
                        .addInclude("users")
                        .entityBuilder()
                        .enableTableFieldAnnotation()
                        .mapperBuilder()
                        .enableMapperAnnotation()

                        .serviceBuilder()
                        // Service去除I(Interface)
                        .formatServiceFileName("%sService")
                        // ServiceImpl去除I(Interface)
                        .formatServiceImplFileName("%sServiceImp")
                        
                        .build());

        autoGenerator.execute();

//        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/test","root","123456").build();
//        String projectPath = System.getProperty("user.dir");
//        GlobalConfig globalConfig = new GlobalConfig.Builder().outputDir(projectPath+"/src/main/java").openDir(false).build();
//        PackageConfig packageConfig = new PackageConfig.Builder().moduleName("test").parent("com.example.test").build();
//        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
//        autoGenerator.global(globalConfig).packageInfo(packageConfig);
//        autoGenerator.execute();


//        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/springbatch?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "123456")
//                .globalConfig(builder -> {
//                    builder.author("Hank") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("D://"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.example.mybatis_demo.generator") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "src/main/resources/mapper")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("t_simple"); // 设置需要生成的表名
////                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//
//        PackageConfig packageConfig = new PackageConfig.Builder()
//                .parent("com.example.mybatis_demo.generator")
//                .moduleName("sys")
//                .entity("po")
//                .service("service")
//                .serviceImpl("service.impl")
//                .mapper("mapper")
//                .xml("mapper.xml")
//                .controller("controller")
//                .other("other")
//                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://"))
//                .build();
    }
}
