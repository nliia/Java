<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Авторизация</title>
    <link href="css/logReg.css" rel="stylesheet" type="text/css"/>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
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
<@content></@content>
</body>
</html>