<#import "parts/common.ftlh" as common>
<#import "parts/login.ftlh" as login>


<@common.page>
    <strong>Login Page</strong>

    <@login.login "/login"/>
    <a href="/registration">Registration</a>

</@common.page>