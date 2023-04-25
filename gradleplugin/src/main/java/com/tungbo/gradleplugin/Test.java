package com.tungbo.gradleplugin;

import org.gradle.api.plugins.ExtensionContainer;

/**
 * Author: Tungbo Wang
 * Email: yongxiang.wang@ximalaya.com
 * Date: 2023/4/25
 * Description：
 */
public class Test {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 将获取扩展对象的代码封装为静态方法
    public static Test getConfig(ExtensionContainer extensionContainer) {
        // 从 ExtensionContainer 容器获取扩展对象
        Test extension = (Test) extensionContainer.findByType(Test.class);
        System.out.println("get is " + extension);
        // 配置缺省的时候，赋予默认值
        if (null == extension) {
            extension = new Test();
        }
        return extension;
    }

    /**
     * 检查扩展配置是否有效
     *
     * @return true：valid
     */
    boolean checkParams() {
        return true;
    }

}
