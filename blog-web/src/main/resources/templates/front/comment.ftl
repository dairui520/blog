<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">评论</h4>
        </div>
        <div class="modal-body">
            <form role="form" id="modalForm">
                <input type="hidden" name="blogId" value="${comment.blogId!''}">
                <input type="hidden" name="pid" value="${comment.pid!''}">
                <input type="hidden" name="receiverId" value="${comment.receiverId!''}">
                <input type="hidden" name="level" value="${comment.level!''}">
                <div class="form-group">
                    <label for="comment" class="control-label">评论</label>
                    <textarea class="form-control" name="comment" id="comment"></textarea>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="replyBtn">评论</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $("#replyBtn").click(function () {
            var params = $("#modalForm").serializeArray();
            $.post("/comment/create",params,function(data){
                if(data.flag){
                    initComments();
                    $('#myModal').modal('hide');
                    layer.msg('评论成功 QAQ', {icon: 6,time: 2000});
                }else{
                    alert(data.msg);
                }
            })
        });
    })
</script>