<div class="result">
    <table>
        <tr>
            <th>id</th>
            <td>${result.id ! ""}</td>
        </tr>
        <tr>
            <th>tags</th>
            <td>
                <#if result.tags?has_content>
                    <#list result.tags as tag>
                        <#include "tag.ftl">
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <th>description</th>
            <td>${result.description ! ""}</td>
        </tr>
        <tr>
            <th>name</th>
            <td>${result.name ! ""}</td>
        </tr>
        <tr>
            <th>keyword</th>
            <td>${result.keyword ! ""}</td>
        </tr>
        <tr>
            <th>line</th>
            <td>${result.line ! ""}</td>
        </tr>
        <tr>
            <th>elements</th>
            <td>
                <#if result.elements?has_content>
                    <#list result.elements as element>
                        <#include "element.ftl">
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <th>uri</th>
            <td>${result.uri ! ""}</td>
        </tr>
        <tr>
            <th>comments</th>
            <td>
                <#if result.comments?has_content>
                    <#list result.comments as comment>
                        <#include "comment.ftl">
                    </#list>
                </#if>
            </td>
        </tr>
    </table>
</div>
