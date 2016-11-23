<!DOCTYPE html>
<html>
<head>
    <title>Добавить товар</title>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h3>Добавить книгу</h3>



<#if Session.session_uname?? && Session.session_uname == "admin">
    <#assign user = Session.session_uname??>
<#else>
<script language='javascript'>
    var delay = 0;
    setTimeout("document.location.href='login.ftl'", delay);
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
<#if Message??>
${Message}
</#if>
<h4>Добавить товар:</h4><br/>
<form action="AddItem" method="post" enctype="multipart/form-data">
    <br>
    <input type="text" class="input" name="name" placeholder="Название товара" required>
    <input type="text" class="input" name="description" placeholder="Описание" required>
    <input type="number" class="input" name="price" placeholder="Цена" required>
    <input type="file" class="btn" name="image" size="50" required>
    <input type="submit" class="submit" value="Добавить"/>
</form>

<form action="AddItem" method=" post"
</body>
</html>