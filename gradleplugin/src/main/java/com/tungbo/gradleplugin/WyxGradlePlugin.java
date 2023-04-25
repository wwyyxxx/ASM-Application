package com.tungbo.gradleplugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Author: Tungbo Wang
 * Email: yongxiang.wang@ximalaya.com
 * Date: 2023/4/25
 * Description：
 */
public class WyxGradlePlugin implements Plugin<Project> {

    // 扩展名
    public static final String WYX_EXTENSION_NAME = "test";

    @Override
    public void apply(Project project) {
        System.out.println("Helloooooooooooo");
        System.out.println("apply project.getExtensions()" + project.getExtensions().hashCode());
        // 添加扩展
        applyExtension(project);
        // 添加 Maven 发布能力
        applyMavenFeature(project);
    }

    private void applyExtension(Project project) {
        // 创建扩展，并添加到 ExtensionContainer
        Test t = project.getExtensions().create(WYX_EXTENSION_NAME, Test.class);

        System.out.println("applyExtension project.getExtensions()" + project.getExtensions().hashCode());
        System.out.println("create is" + t);
    }

    private void applyMavenFeature(Project project) {
        // 构建逻辑
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                System.out.println("applyMavenFeature project.getExtensions()" + project.getExtensions().hashCode());
                Test config = Test.getConfig(project.getExtensions());
                System.out.println("name :" + config.getName());
            }
        });
    }
}
