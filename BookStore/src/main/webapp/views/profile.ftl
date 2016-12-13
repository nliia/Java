<#include "menuTemplate.ftl">
<#macro content>
    <#if Session.session_uname??>
    <h2>Здравствуйте, ${Session.session_uname}!</h2>
    </#if>

    <#if update??>
    <h3>Пароль, логин и email изменены</h3>
    </#if>
    <#if reg??>
    ${reg}
    </#if>

<form class="form-signin" action="profile" method="post">
    Изменить логин
    <input type="text" class="input" name="newUsername" required class="form-control">
    Изменить пароль
    <input type="password" class="input" name="newPassword" required class="form-control">
    Изменить email
    <input type="text" class="input" name="newEmail" required class="form-control">
    <button type="submit">Изменить</button>
</form>
</#macro>