$(document).ready(function() {
	$("#NicknameButton").click(function(event) {
		$("#inputChangeNickname").show();
		$("#inputChangePassword").hide();
	});
});




$(document).ready(function() {
	$("#passwordButton").click(function(event) {
		$("#inputChangeNickname").hide();
		$("#inputChangePassword").show();
	});
});