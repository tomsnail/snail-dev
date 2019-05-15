# CI&CD

## 代码检查

```xml
<!--自定义代码风格检查 -->
<checkstyle.skip>true</checkstyle.skip>
<!-- rat插件-->
<rat.skip>true</rat.skip>
<!-- 静态代码分析 -->
<findbugs.skip>true</findbugs.skip>
<!-- 覆盖率报告-->
<jacoco.skip>true</jacoco.skip>
<!-- 阿里代码规约检查-->
<pmd.skip>true</pmd.skip>
```

通过CI/CD自动化时，将true改为false,运行插件。

开发时，通过idea插件完成上述功能。

## CI

## CD