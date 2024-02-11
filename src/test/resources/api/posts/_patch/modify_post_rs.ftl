{
<#if (modified_post.id)??>"id": ${modified_post.id},</#if>
<#if (modified_post.user.id)??>"userId": ${modified_post.user.id},</#if>
<#if modified_post.title??>"title": "${modified_post.title}",</#if>
<#if modified_post.body??>"body": "${modified_post.body}"</#if>
}