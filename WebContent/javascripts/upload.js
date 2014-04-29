var numberOfFirstname = 0;
var numberOfLastname = 0;
var numberOfEmail = 0;
var numberOfAuthors = 1 ;

function addAuthor() {
 
  $('.authors').append('<div colspan="2" class="input-prepend"><span class="add-on"><i class="icon-user"></i></span>First name:<input type="text" id="'+numberOfFirstname+'" placeholder="Firstname" value="" name="f'+numberOfFirstname+'"><div class="input-prepend"><span class="add-on"><i class="icon-user"></i></span>Last name:<input type="text" value="" id="'+numberOfLastname+'" placeholder="Lastname" name="la'+numberOfLastname+'" /><div class="input-prepend"><span class="add-on"><i class="icon-envelope"></i></span>Email:<input type="text" value="" id="'+numberOfEmail+'" placeholder="Email" name="e'+numberOfEmail+'" />');
  $('#author').html('<span class="add-on">Add more authors<i class="icon-plus"></i></span>');

  $('#f'+numberOfFirstname).attr('name','f'+numberOfFirstname);
  $('#la'+numberOfLastname).attr('name','la'+numberOfLastname);
  $('#e'+numberOfEmail).attr('name','e'+numberOfEmail);
  $('#hiddenc').val(numberOfAuthors);
  
  numberOfFirstname+=1;
  numberOfLastname+=1;
  numberOfEmail+=1;
  numberOfAuthors+=1;
  
  
 }