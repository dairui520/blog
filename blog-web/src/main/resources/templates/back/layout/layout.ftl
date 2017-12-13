<#import "head.ftl" as headTemplate>
<#import "header.ftl" as headerTemplate>
<#import "script.ftl" as scriptTemplate>
<#macro defaultHead></#macro>
<#macro defaultContainer></#macro>
<#macro defaultScript></#macro>
<#macro layout head=defaultHead allHead=headTemplate.defaultHead header=headerTemplate.defaultHeader container=defaultContainer script=defaultScript allScript=scriptTemplate.defaultScript>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <@allHead/>
            <@head/>
        </head>
        <body>
            <div id="wrapper">
                <@header/>
                <@container/>
            </div>
            <@allScript/>
            <@script/>
        </body>
    </html>
</#macro>