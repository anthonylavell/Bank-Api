$(function() {
    $("input[name='dipForm']").click(function() {
        if($("#newForm").is(":checked")) {
            $("#showSqlFile").hide();
            $("#dipFile").prop('required',false);
            $("#showSFormName").show();
            $("#jBformname").prop('required',true);
            $("#showProgramId").show();
            $("#progamId").val('05');
            $("#programId").prop('required',true);
        }else{
            $("#showSqlFile").show();
            $("#dipFile").prop('required',true);
            $("#showSFormName").hide();
            $("#jBformname").prop('required',false);
            $("#jBformname").val("");
            $("#showProgramId").hide();
            $("#progamId").val('');
            $("#programId").prop('required',false);
            $("#dipFile").tooltip();
        }
    });
    $("#newForm").click();

});