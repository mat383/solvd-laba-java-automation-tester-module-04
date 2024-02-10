<#-- expects two object:
- original_post - post before modification
- post_modification - post with all fields null,
  except the ones that you wish to modify in patch call
 -->
{
"id": ${original_post.user.id},
<#if (post_modification.user.id)??>
    "userId": ${post_modification.user.id},
<#else>
    "userId": ${original_post.user.id},
</#if>
<#if post_modification.title??>
    "title": "${post_modification.title}",
<#else>
    "title": "${original_post.title}",
</#if>
<#if post_modification.body??>
    "body": "${post_modification.body}"
<#else>
    "body": "${original_post.body}"
</#if>
}
