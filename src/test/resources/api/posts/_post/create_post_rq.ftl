{
<#if (post.user.id)??>"userId": ${post.user.id},</#if>
<#if post.title??>"title": "${post.title}",</#if>
<#if post.body??>"body": "${post.body}"</#if>
}