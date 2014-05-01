var numberOfFirstname = 0;
var numberOfLastname = 0;
var numberOfEmail = 0;
var numberOfAuthors = 1;

function addAuthor() {

	$('.authors')
			.append(
					'<div><div style="width:200px;display:inline-block"><b>Firstname:</b> </div><div class="input-prepend" style="width:250px;display:inline-block"><span class="add-on"><i class="icon-user"></i></span> <input type="text" size="30" name="f'
							+ numberOfFirstname
							+ '" placeholder="Firstname" id="'
							+ numberOfFirstname
							+ '" /></div></div><div><div style="width:200px;display:inline-block"><b>Lastname:</b> </div><div class="input-prepend" style="width:250px;display:inline-block"><span class="add-on"><i class="icon-user"></i></span> <input type="text" size="30" name="la'
							+ numberOfLastname
							+ '" id="'
							+ numberOfLastname
							+ '" placeholder="Lastname" /></div></div><div><div style="width:200px;display:inline-block"><b>Email:</b> </div><div class="input-prepend" style="width:250px;display:inline-block"><span class="add-on"><i class="icon-envelope"></i></span> <input type="text" size="30" name="e'
							+ numberOfEmail
							+ '" placeholder="Email" id="'
							+ numberOfEmail + '" /></div></div><br><br>');
	$('#author')
			.html(
					'<span class="add-on">Add more authors<i class="icon-plus"></i></span>');
	$('#f' + numberOfFirstname).attr('name', 'f' + numberOfFirstname);
	$('#la' + numberOfLastname).attr('name', 'la' + numberOfLastname);
	$('#e' + numberOfEmail).attr('name', 'e' + numberOfEmail);
	$('#hiddenc').val(numberOfAuthors);

	numberOfFirstname += 1;
	numberOfLastname += 1;
	numberOfEmail += 1;
	numberOfAuthors += 1;

}