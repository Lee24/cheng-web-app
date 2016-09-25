$(document).ready(function(){

});

window.onload = function(){
    $('#upload').click(function(event){$('#fileupload_input').click();});
    $("#fileupload_input").change(uploadFileFunc);
}

function uploadFileFunc(event){
    var fileReader = new FileReader();
    var file = $('#fileupload_input')[0].files[0];
    fileReader.readAsText(file);   //还可以readas其它
    fileReader.onload = function(f){
        var fileData = fileReader.result;
        $.ajax({
            url: 'base/uploadfile',
            tpye: 'POST',
            contentTpye: 'application/json',
            data: fileReader.result
        }).done(function(data){

        }).fail(function(){

        })
    }
}