<!DOCTYPE html>
<html lang="en">
<head>
    <title>Личный кабинет</title>
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
<#if Session.session_uname??>
<h2>Здравствуйте, ${Session.session_uname}!</h2>
<#else>
<script language='javascript'>
    var delay = 0;
    setTimeout("document.location.href='login.ftl'", delay);
</script>
</#if>

<#if update??>
<h3>Пароль и логин изменены</h3>
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

</body>
</html>