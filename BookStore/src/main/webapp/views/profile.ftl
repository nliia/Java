<#include "menuTemplate.ftl">
<#macro content>
<title>Profile</title>

    <#if Session.session_uname??>
    <h2>Hello, ${Session.session_uname}!</h2>
    </#if>

    <#if update??>
    <h3>Password, login and email were changed</h3>
    </#if>
    <#if reg??>
    ${reg}
    </#if>

<form class="form-signin" action="profile" method="post">
    Change login
    <input type="text" class="input" name="newUsername" required class="form-control"><br><br>
    Change password
    <input type="password" class="input" name="newPassword" required class="form-control"><br><br>
    Change email
    <input type="text" class="input" name="newEmail" required class="form-control"><br><br>
    <button type="submit" class="btn btn-primary" id="add">Change</button>
</form>
</#macro>