<div class="step">
    <table>
        <tr>
            <th>result</th>
            <td>
                <table>
                    <tr>
                        <th>duration</th>
                        <td>${step.result.duration ! ""}</td>
                    </tr>
                    <tr>
                        <th>status</th>
                        <td>${step.result.status ! ""}</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <th>embeddings</th>
            <td>
                <#if step.embeddings?has_content>
                    <#list step.embeddings as embedding>
                        <#include "embedding.ftl">
                    </#list>
                </#if>
            </td>
        </tr>
        <tr>
            <th>name</th>
            <td>${step.name ! ""}</td>
        </tr>
        <tr>
            <th>keyword</th>
            <td>${step.keyword ! ""}</td>
        </tr>
        <tr>
            <th>line</th>
            <td>${step.line ! ""}</td>
        </tr>
        <tr>
            <th>match</th>
            <td>
                <table>
                    <tr>
                        <th>arguments</th>
                        <td>
                        <#if step.match.arguments?has_content>
                            <#list step.match.arguments as argument>
                                <table>
                                    <tr>
                                        <th>val</th>
                                        <td>${argument.val ! ""}</td>
                                    </tr>
                                    <tr>
                                        <th>offset</th>
                                        <td>${argument.offset ! ""}</td>
                                    </tr>
                                </table>
                            </#list>
                        </#if>
                        </td>
                    </tr>
                    <tr>
                        <th>location</th>
                        <td>${step.match.location ! ""}</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <th>location</th>
            <td>${step.location ! ""}</td>
        </tr>
        <tr>
            <th>matchedColumns</th>
            <td>
                <ul>
                <#if step.matchedColumns?has_content>
                    <#list step.matchedColumns as matchedColumn>
                        <li>${matchedColumn}</li>
                    </#list>
                </#if>
                </ul>
            </td>
        </tr>
        <tr>
            <th>rows</th>
            <td>
            <#if step.rows?has_content>
                <#list step.rows as row>
                    <table>
                        <tr>
                            <th>cells</th>
                            <td>
                                <ul>
                                    <#if row.cells?has_content>
                                        <#list row.cells as cell>
                                            <li>${cell ! ""}</li>
                                        </#list>
                                    </#if>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <th>line</th>
                            <td>${row.line ! ""}</td>
                        </tr>
                    </table>
                </#list>
            </#if>
            </td>
        </tr>
    </table>
</div>
