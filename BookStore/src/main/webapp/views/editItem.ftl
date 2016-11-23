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
</body>
</html>