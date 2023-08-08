$(document).ready(function() {
    var changeNicknameError = $("#errorData").data("hasChangeNicknameError");
    var failCheckPassword = $("#errorData").data("hasFailCheckPassword");
	
    if (changeNicknameError || failCheckPassword) {
        $("#inputChangeNickname").show();
    }

    $("#NicknameButton").click(function(event) {
        $("#inputChangeNickname").show();
        $("#inputChangePassword").hide();
    });
});


$(document).ready(function() {
	var failCheckPasswordChange = $("#errorData").data("hasFailCheckPasswordChange");
    var passwordInputError = $("#errorData").data("hasPasswordInputError");
	
    if (failCheckPasswordChange || passwordInputError) {
        $("#inputChangePassword").show();
    }
	
	$("#passwordButton").click(function(event) {
		$("#inputChangeNickname").hide();
		$("#inputChangePassword").show();
	});
});