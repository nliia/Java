<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Авторизация</title>
    <link href="css/logReg.css" rel="stylesheet" type="text/css"/>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<#if (Request.getRequest().cookies)??>
<script language='javascript'>
    var delay = 0;
    setTimeout("document.location.href='/login'", delay);
</script>
</#if>
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
</div>
</body>
</html>