package com.tungbo.gradleplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Author: Tungbo Wang
 * Email: yongxiang.wang@ximalaya.com
 * Date: 2023/4/24
 * Description：
 */
public class TestGradlePlugin implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        System.out.println("++++++++++");
        System.out.println("Hello");
        System.out.println("++++++++++");
    }
}
