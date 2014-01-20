<!DOCTYPE html>
<html>
    <head>
        <title>Cucumber Freemarker</title>
    </head>
    <body>
        <#if results?has_content>
            <#list results as result>
                <#include "result.ftl">
            </#list>
        </#if>
    </body>
</html>
