(function () {
	"use strict";

	function validatePassword(password, confirmPassword) {
		if (confirmPassword === undefined) {
			return false;
		}
		return password === confirmPassword && confirmPassword.trim().length > 0;
	}

	$(".needs-validation").each(function (idx) {
		console.log("jquery .needs-validation for each function fired: ");
		$(this).on("submit", function (e) {
			console.log("submit event fired");
			const form = $(this).get(idx);
			if (!form.checkValidity()) {
				e.preventDefault();
				e.stopPropagation();
			}
			if (!validatePassword($("#password").val(), $("#confirm_password").val())) {
				e.preventDefault();
				e.stopPropagation();
			}
			$(this).addClass("was-validated");
		});
	});

	$(".passwords-match-validation").each(function () {
		$(this).on("keyup", function () {
			if (!validatePassword()) {
				$("#confirm_password").addClass("is-invalid").removeClass("is-valid");
			} else {
				$("#confirm_password").removeClass("is-invalid").addClass("is-valid");
			}
		})
	});
})();