<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="css/original.css"/>
    <title>Sign up</title>
</head>
<body>
<a href="/sign_in">Sign in</a>

<@sf.form action="/sign_up" method="post" modelAttribute="user">
<fieldset>
    <div class="field">
        <@sf.label path="email">Email</@sf.label>
        <@sf.input path="email" type="email" cssClass="form-control"/>
        <@sf.errors path="email"/>
    </div>
    <div class="field">
        <@sf.label path="password">Password</@sf.label>
        <@sf.input path="password" type="password" cssClass="form-control"/>
        <@sf.errors path="password"/>
    </div>

    <div>
        <input type="submit" value="Sign up">
    </div>
</fieldset>
</@sf.form>

</body>
</html>