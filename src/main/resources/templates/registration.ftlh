<#import "parts/common.ftlh" as common>
<#import "parts/login.ftlh" as login>

<@common.page>
    <div class="mb-1">Add new user</div>
    ${message!}
    <@login.login "/registration" true />
</@common.page>