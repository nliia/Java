<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>

<head>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="css/original.css"/>
    <title>Sign in</title>
</head>

<body>

<a href="/sign_up">Sign up</a>

<#if error??>
Bad creds
</#if>
<#if afterSignUp ??>
You signed up successfully!
</#if>
<h4>Sign in:</h4>

<@sf.form role="form" action='/login/process' method="post" modelAttribute="authForm">
<fieldset>
    <@sf.label path="email">Email</@sf.label>
    <@sf.input path="email" cssClass="form-control" type="email"/>
    <@sf.errors path="email" cssClass="help-block"/>

    <@sf.label path="password">Password</@sf.label>
    <@sf.input path="password" cssClass="form-control" type="password"/>
    <@sf.errors path="password" cssClass="help-block"/>

    <input class="btn btn-info btn-outline" type="submit" value="Sing in">

</fieldset>

</@sf.form>

</body>
</html>