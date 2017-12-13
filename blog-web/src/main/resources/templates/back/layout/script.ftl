<#macro defaultScript>
<!-- jQuery -->
<script src="/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $("#logout").click(function(){
            $.ajax({
                url: "/back/logout",
                type: "GET",
                success: function (data) {
                    debugger;
                    if(data == true){
                        window.location.href="/back";
                    }
                },
                error: function(data){
                    alert(data.responseText);
                }
            });
        })
    })
</script>
</#macro>