<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    <link href="css/simplePagination.css" rel="stylesheet" type="text/css"/>
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
            <a href="/bucket">
                <div class="nav_button">Корзина</div>
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
    <c:when test="${not empty products}">

        <c:forEach items="${products}" var="item">

            <form action="/item" method="get">
                <input type="hidden" name="id" value="${item.id}"/>
                <a><input type="submit" class="itemName" value="${item.name}"></a>
            </form>
            ${item.price}<br>
            <img src="/images/${item.picture}" width="100" height="100" alt="${item.picture}">
            <c:if test="${sessionScope.session_uname == 'admin'}">
                <form action="/delete" method="post">
                    <button type="submit" name="id" value="${item.id}" onclick="return confirm('Вы уверены, что хотите удалить?')">Удалить</button>
                </form>
                <a href="/editItem?id=${item.id}">
                    <div>Изменить товар</div>
                </a>
            </c:if>

            <form action="/bucket" method="post">
                <button type="submit" name="id" value="${item.id}" onclick="alert('Добавлено!')">Добавить в корзину</button>
            </form>
        </c:forEach>

        <c:if test="${currentPage != 1}">
            <h3><a href="/?page=${currentPage - 1}">Предыдущая</a></h3>
        </c:if>

        <table class="dark-theme">
            <tr>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="/?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>

        <c:if test="${currentPage lt noOfPages}">
            <h3><a href="?page=${currentPage + 1}">Следующая</a></h3>
        </c:if>
    </c:when>
    <c:otherwise>
        <h3>Товары пока не добавлены</h3>
    </c:otherwise>
</c:choose>

</body>
</html>