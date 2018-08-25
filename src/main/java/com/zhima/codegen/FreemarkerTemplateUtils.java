package com.zhima.codegen;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;

/**
 * Created by superz on 2018/8/22.
 */
public class FreemarkerTemplateUtils {
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);

    static{
        //这里比较重要，用来指定加载模板所在的路径
        CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(com.zhima.codegen.FreemarkerTemplateUtils.class, ""));
        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
    }

    public static Template getTemplate(String templateName) throws IOException {
        try {
            return CONFIGURATION.getTemplate(templateName);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }
}
