<#include "menuTemplate.ftl">
<#macro content>
    <#if errorPswFormat??>
    <h3>Неверный формат пароля (Пароль должен содержать не менее 6 символов)</h3>
    </#if>

    <#if errorEmailFormat??>
    <h3>Неверныый формат email</h3>
    </#if>
    <#if errorEmailExist??>
    <h3>Такой email уже существует</h3>
    </#if>

    <#if errorLoginExist??>
    <h3> Такой логин уже существует</h3>
    </#if>
<div id="login-form">
    <h1>РЕГИСТРАЦИЯ</h1>
    <fieldset>
        <form action="reg" method="post">
            <input class="input" type="text" name="login" placeholder="login">
            <input class="input" type="password" name="password" placeholder="password">
            <input class="input" type="text" name="email" placeholder="email">
            <input class="btn" type="submit" value="ВОЙТИ">
        </form>
    </fieldset>
</div>
</#macro>