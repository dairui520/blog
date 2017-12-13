<#include "../layout/layout.ftl">
<#macro overrideHead>
    <title>分录管理</title>
</#macro>
<#macro overrideContainer>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">分录编辑</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input type="hidden" name="id" id="categoryId">
                    <div class="form-group">
                        <input type="text"  name="name" class="form-control" placeholder="分录名称" id="categoryName">
                    </div>
                    <div class="form-group">
                        <input type="text"  name="sortIndex" class="form-control" placeholder="排序" id="categorySortIndex">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div>
    </div>
</div>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <ol class="breadcrumb">
                    <li class="active">
                        分录
                    </li>
                </ol>
                <hr>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-10">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>排序</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list categories as category>
                            <tr>
                                <input type="hidden" value="${category.id}">
                                <td><a class="editCls">${category.name}</a></td>
                                <td>${category.sortIndex}</td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-2">
                <div class="r-menu-item">
                    <button type="button" class="btn btn-primary" id="newCategory">
                        新建分录
                    </button>
                </div>
                <div class="r-menu-item">
                </div>
                <div class="r-menu-item">
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#macro overrideScript>
<script>
    function init(id,name,sortIndex) {
        $("#categoryId").val(id);
        $("#categoryName").val(name);
        $("#categorySortIndex").val(sortIndex);
    }
    $(function(){
        $(".side-nav").children("li").eq(1).addClass("active");
        $("#newCategory").on("click",function () {
            init("","","");
            $('#myModal').modal({show:true});
        })
        $(".editCls").on("click",function () {
            var name = $(this).text();
            var id = $(this).parent().prev().val();
            var sortIndex = $(this).parent().next().text();
            init(id,name,sortIndex);
            $('#myModal').modal({show:true});
        })
        $("#saveBtn").click(function(){
            var params = $("#editForm").serializeArray();
            $.post("/back/category/save",params,function(result){
                if(result){
                    location.reload();
                }
            });
        });
    })
</script>
</#macro>
<@layout head=overrideHead container=overrideContainer script=overrideScript>
</@layout>