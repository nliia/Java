<#include "menuTemplate.ftl">
<#macro content>
    <#if error??>
    <h3>Неверный логин или пароль</h3>
    </#if>
<div id="login-form">

    <h1>АВТОРИЗАЦИЯ</h1>
    <div id="somediv"></div>
    <fieldset>
        <form action="login" method="post" class="auth-forms">
            <div id="auth-info"></div>
            <input class="input" type="text" name="username" id="username " placeholder="login">
            <input class="input" type="password" name="password" id="password" placeholder="password">
            <label class="remember"><input type="checkbox" name="remember" value="true"> Запомнить меня</label>
            <button class="btn" type="submit">ВОЙТИ</button>
        </form>
        <h3><a href="reg">Перейти к регистрации</a></h3>
    </fieldset>
</div>
</#macro>