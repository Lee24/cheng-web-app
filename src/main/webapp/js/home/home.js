$(document).ready(function(){

});

window.onload = function(){
    $('#upload').click(function(event){$('#fileupload_input').click();});
    $("#fileupload_input").change(uploadFileFunc);
}

function uploadFileFunc(event){
    console.log("hello here2");
    var fileReader = new FileReader();
    var file = $('#fileupload_input')[0].files[0];
    console.log("hello here1");
    console.log(fileReader.result);
    fileReader.readAsText(file);   //还可以readas其它
    console.log("hello here0");
    console.log(fileReader.result);

    $.ajax({
        url: '',
        tpye: 'POST',
        contentTpye: 'application/json',
        data: fileReader.result
    }).done(function(data){

    }).fail(function(){

    })
}