<#include "menuTemplate.ftl">
<#macro content>
    <#if !Session.session_uname??>
    <script language='javascript'>
        var delay = 0;
        setTimeout("document.location.href='login.ftl'", delay);
    </script>

    </#if>

<h4>Изменить товар:</h4><br/>
<form action="editItem" method="post" enctype="multipart/form-data">
    <br>
    <input type="hidden" name="id" value="${id}">
    <input type="text" class="input" name="name" placeholder="Название товара" required>
    <input type="text" class="input" name="description" placeholder="Описание" required>
    <input type="number" class="input" name="price" placeholder="Цена" required>
    <input type="file" class="btn" name="image" size="50" required>
    <input type="submit" class="submit" value="Изменить"/>
</form
</#macro>