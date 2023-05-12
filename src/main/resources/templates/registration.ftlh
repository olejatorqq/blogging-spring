<#import "parts/common.ftlh" as common>
<#import "parts/login.ftlh" as login>

<@common.page >
    <strong>Add new user</strong>
    ${message!}
    <@login.login "/registration" />

</@common.page>