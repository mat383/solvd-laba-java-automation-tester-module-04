{
<#if (post_modification.user.id)??>"userId": ${post_modification.user.id},</#if>
<#if post_modification.title??>"title": "${post_modification.title}",</#if>
<#if post_modification.body??>"body": "${post_modification.body}"</#if>
}
