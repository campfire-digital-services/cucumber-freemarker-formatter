<#assign base64decode = 'au.com.permeance.cucumber.Base64Decode'?new()>

<div class="embedding">
    <table>
        <tr>
            <th>mime_type</th>
            <td>${embedding.mime_type ! ""}</td>
        </tr>
        <tr>
            <th>data</th>
            <td>${embedding.data ! ""}</td>
        </tr>
        <tr>
            <th>data (decoded)</th>
            <td>${base64decode(embedding.data) ! ""}</td>
        </tr>
    </table>
</div>
