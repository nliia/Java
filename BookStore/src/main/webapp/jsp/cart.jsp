<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    <title>BookStore</title>
</head>
<body>
<div id="top_bar_black">
    <div id="logo_container">
        <div id="logo_image"></div>
        <div id="nav_block">
            <a href="/" class="selected">
                <div class="nav_button">Товары</div>
            </a>
            <c:if test="${sessionScope.session_uname == null}">
                <a href="/reg">
                    <div class="nav_button">Регистрация</div>
                </a>
                <a href="/login">
                    <div class="nav_button">Войти</div>
                </a>
            </c:if>
            <c:if test="${sessionScope.session_uname != null}">
                <a href="/bucket">
                    <div class="nav_button">Корзина</div>
                </a>
                <a href="/profile">
                    <div class="nav_button">Профиль</div>
                </a>
                <a href="/logout">
                    <div class="nav_button">Выйти</div>
                </a>
                <c:if test="${sessionScope.session_uname == 'admin'}">
                    <a href="/AddItem">
                        <div class="nav_button">Добавить товар</div>
                    </a>
                </c:if>
            </c:if>
        </div>
    </div>
</div>

<c:choose>
    <c:when test="${not empty cart}">
        <c:forEach items="${cart}" var="item">
            ${item.name}<br>
            ${item.price}<br>
            <img src=/images/${item.picture} width="100" height="100" alt="${item.picture}">
            <form action="/deleteCart" method="post">
                <button type="submit" name="id" value="${item.id}">Удалить</button>
            </form>
        </c:forEach>
        <center>
            <button type="submit" onclick="alert('Вы заказали товар на сумму: ${sessionScope.cost} рублей')">Оформить
                заказ
            </button>
        </center>
    </c:when>
    <c:otherwise>
        <h3>Товары пока не добавлены</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
