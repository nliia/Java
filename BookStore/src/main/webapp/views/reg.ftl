<!DOCTYPE html>
<html lang="en">
<head>
    <link href="css/logReg.css" rel="stylesheet" type="text/css"/>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    <title>Registration</title>
</head>
<body>
<div id="top_bar_black">
    <div id="logo_container">
        <div id="logo_image"></div>
        <div id="nav_block">
            <a href="/" class="selected">
                <div class="nav_button">Товары</div>
            </a>
        <#if Session.session_uname??>
            <a href="/bucket">
                <div class="nav_button">Корзина</div>
            </a>
            <a href="/profile">
                <div class="nav_button">Профиль</div>
            </a>
            <a href="/logout">
                <div class="nav_button">Выйти</div>
            </a>
            <#if Session.session_uname == "admin">
                <a href="/AddItem">
                    <div class="nav_button">Добавить товар</div>
                </a>
            </#if>
        <#else>

            <a href="/reg">
                <div class="nav_button">Регистрация</div>
            </a>
            <a href="/login">
                <div class="nav_button">Войти</div>
            </a>
        </#if>
        </div>
    </div>
</div>
<#if pswFormat??>
<h3>Неверный формат пароля (Пароль должен содержать не менее 6 символов)</h3>
</#if>

<#if emailFormat??>
<h3>Неверныый формат email</h3>
</#if>
<#if errorEmail??>
<h3>Такой email уже существует</h3>
</#if>

<#if errorLogin??>
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
</body>
</html>