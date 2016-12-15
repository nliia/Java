<#include "menuTemplate.ftl">
<#macro content>
    <#if Message??>
    ${Message}
    </#if>
<h4>Добавить товар:</h4><br/>
<form action="AddItem" method="post" enctype="multipart/form-data">
    <br>
    <input type="text" class="input" name="name" placeholder="Название товара" required>
    <input type="text" class="input" name="description" placeholder="Описание" required>
    <input type="number" class="input" name="price" placeholder="Цена" required>
    <input type="text" class="input" name="genre" placeholder="Жанр" required>
    <input type="file" class="btn" name="image" size="50" required>
    <input type="submit" class="submit" value="Добавить"/>
</form>
</#macro>