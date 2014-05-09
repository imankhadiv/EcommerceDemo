/**
 * 
 */
function submitBtn( id, type){
	
		method="POST";
		urlString="JDBServlet?action=delete_select_await&article_id="+id;
		connect(method,urlString);
}

function connect(method, url)
{
var http_request;
	
	try {
		http_request = new ActiveXObject('Msxml2.XMLHTTP');
	}
	catch (e) {
		try {
			http_request = new ActiveXObject('Microsoft.XMLHTTP');
		}
		catch (e2) {
			try {
				http_request = new XMLHttpRequest();
			}
			catch (e3) {
				http_request = false;
			}
		}
	}
	
	http_request = new XMLHttpRequest();
	
	http_request.open(method, url ,
			true);
	http_request.send();
	http_request.onreadystatechange = function() {
		if (http_request.readyState == 4) {
			location.reload();
		}
	};	
}