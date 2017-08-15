function showSuccess(mensagem) {
	toastr.success(mensagem, null, {
		closeButton : true,
		progressBar : true,
		positionClass : "toast-top-center"
	});
}
function showWarning(mensagem) {
	toastr.warning(mensagem, null, {
		closeButton : true,
		progressBar : true,
		positionClass : "toast-top-center",
		timeOut : 10000
	});
}
function showError(mensagem) {
	toastr.error(mensagem, null, {
		closeButton : true,
		progressBar : true,
		positionClass : "toast-top-center",
		timeOut : 15000
	});
}
function showInfo(mensagem) {
	toastr.info(mensagem, null, {
		closeButton : true,
		progressBar : true,
		positionClass : "toast-top-center",
		timeOut : 6000
	});
}

function validateEmail(userEmail) {
	if(userEmail===null || userEmail===undefined || userEmail==''){
		return false;
	}
		
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	return emailReg.test( userEmail );
}