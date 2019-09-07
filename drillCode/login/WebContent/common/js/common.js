$(document).ready(function()
{
  var fileTarget = $('.filebox .upload-hidden');
  fileTarget.on('change', function()
  {
    if(window.FileReader)
    {
      // 파일명 추출
      var filename = $(this)[0].files[0].name;
    }
    else
    {
      // Old IE 파일명 추출
      var filename = $(this).val().split('/').pop().split('\\').pop();
    }
    ;
    $(this).siblings('.upload-name').val(filename);
  });

  // preview image
  var imgTarget = $('.preview-image .upload-hidden');

  imgTarget.on('change', function()
  {
    var parent = $(this).parent();
    parent.children('.upload-display').remove();

    if(window.FileReader)
    {
      // image 파일만
      if(!$(this)[0].files[0].type.match(/image\//))
        return;

      var reader = new FileReader();
      reader.onload = function(e)
      {
        var src = e.target.result;
        parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img src="' + src + '" class="upload-thumb"></div></div>');
      }
      reader.readAsDataURL($(this)[0].files[0]);
    }
    else
    {
      $(this)[0].select();
      $(this)[0].blur();
      var imgSrc = document.selection.createRange().text;
      parent.prepend('<div class="upload-display"><div class="upload-thumb-wrap"><img class="upload-thumb"></div></div>');
      var img = $(this).siblings('.upload-display').find('img');
      img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\"" + imgSrc + "\")";
    }
  });
});

function page_move(url, some_data)
{
  var form = document.createElement("form");
  var param = new Array();
  var input = new Array();

  var cnt = 0;
  
  form.action = url;
  form.method = "post";

  param.push( ['some_key1', 'some_value1'] );
  param.push( ['some_key2', 'some_value2'] );
  param.push( ['some_data', some_data] );

  for (var i = 0; i < param.length; i++) {
      input[i] = document.createElement("input");
      input[i].setAttribute("type", "hidden");
      input[i].setAttribute("name", param[i][0]);
      input[i].setAttribute("value", param[i][1]);
      form.appendChild(input[i]);
      cnt++;
  }
  document.body.appendChild(form);
  
  form.submit();
}