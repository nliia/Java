<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <link href="css/stylesheet.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="css/simplePagination.css"/>
    <title>BookStore</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="../js/addToCart.js"></script>
    <script src="../js/countCharacters.js"></script>
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

<h2>${item.name}</h2><br>
<center><img src="/images/${item.picture}" width="400" height="300" alt="${item.picture}"></center>
<h2>${item.description}</h2><br>
<h2>${item.price} рублей</h2><br>
<center>
    <form id="addToCartForm" action="/bucket" method="post">
        <input type="hidden" name="id" value="${item.id}"/>
        <input type="submit" name="submit" class="submit" value="Добавить в корзину"
               onclick="this.value='Добавлено'"/>
    </form>
    <h3>Комментарии пользователей:</h3>
    <c:choose>
        <c:when test="${sessionScope.session_uname != null}">
            <form action="/addComment" method="post">
                <input type="text" class="input" id="comment1" name="comment" oninput="ready()"
                       placeholder="Ваш комментарий" required>
                <input type="hidden" value="${sessionScope.session_uname}" name="username">
                <input type="hidden" value="${item.id}" name="item_id">
                <div class="counter">Осталось символов: <span id="counter"></span></div>
                <input type="submit" class="submit" value="Добавить">
            </form>
        </c:when>
        <c:otherwise>
            Авторизуйтесь, чтобы добавить комментарий
        </c:otherwise>
    </c:choose>
</center>

<c:choose>
    <c:when test="${not empty comments}">
        <c:forEach items="${comments}" var="comment">
            <div class="comment">
                <h3>
                        ${comment.login}
                        ${comment.date}
                        ${comment.text}
                </h3>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h3>Комментарии пока не добавлены</h3>
    </c:otherwise>
</c:choose>
</center>
</body>
</html>