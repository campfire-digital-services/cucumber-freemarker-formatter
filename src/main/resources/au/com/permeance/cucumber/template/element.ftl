<div class="element">
    <table>
        <tr>
            <th>before</th>
            <td>
                <#if element.before?has_content>
                    <#list element.before as before>
                        <#include "before.ftl">
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <th>after</th>
            <td>
                <#if element.after?has_content>
                    <#list element.after as after>
                        <#include "after.ftl">
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <th>id</th>
            <td>${element.id ! ""}</td>
        </tr>
        <tr>
            <th>description</th>
            <td>${element.description ! ""}</td>
        </tr>
        <tr>
            <th>name</th>
            <td>${element.name ! ""}</td>
        </tr>
        <tr>
            <th>keyword</th>
            <td>${element.keyword ! ""}</td>
        </tr>
        <tr>
            <th>line</th>
            <td>${element.line ! ""}</td>
        </tr>
        <tr>
            <th>steps</th>
            <td>
                <#if element.steps?has_content>
                    <#list element.steps as step>
                        <#include "step.ftl">
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <th>tags</th>
            <td>
                <#if element.tags?has_content>
                    <#list element.tags as tag>
                        <#include "tag.ftl">
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <th>type</th>
            <td>${element.type ! ""}</td>
        </tr>
    </table>
</div>
