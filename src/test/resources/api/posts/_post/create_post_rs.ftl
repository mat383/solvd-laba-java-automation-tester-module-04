{
<#if (post.user.id)??>"userId": ${post.user.id},</#if>
"id": "type:Integer",
<#if post.title??>"title": "${post.title}",</#if>
<#if post.body??>"body": "${post.body}"</#if>
}