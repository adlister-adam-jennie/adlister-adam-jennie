(function () {
	"use strict";

	function validatePassword() {
		const password = $("#password").val();
		const confirmPassword = $("#confirm_password").val();
		return password === confirmPassword && confirmPassword.trim().length > 0;
	}

	function formatConfirmPassword() {
		if (!validatePassword()) {
			$("#confirm_password")
				.addClass("is-invalid")
				.removeClass("is-valid")
				.get()[0].setCustomValidity("Password must match.");
		} else {
			$("#confirm_password")
				.removeClass("is-invalid")
				.addClass("is-valid")
				.get()[0].setCustomValidity("");
		}
	}

	$(".needs-validation").each(function (idx) {
		$(this).on("submit", function (e) {
			const form = $(this).get(idx);
			if (!form.checkValidity()) {
				e.preventDefault();
				e.stopPropagation();
			}
			if (!validatePassword()) {
				e.preventDefault();
				e.stopPropagation();
			} else {
				$("#confirm_password").get()[0].setCustomValidity("");
			}
			$(this).addClass("was-validated");
		});
	});

	$(".passwords-match-validation").each(function () {
		$(this).on("keyup", function () {
			formatConfirmPassword();
		}).on("submit", function (e) {
			e.preventDefault();
			e.stopPropagation();
			formatConfirmPassword();
		})
	});
})();