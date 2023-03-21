(function () {
	"use strict";
	const baseUrl = "http://localhost:8080";
	const validateEndpoint = "/validate";
	const minLength = 3;
	const maxLength = 256;

	async function usernameExistsAlready(element) {
		const query = `?username=${encodeURIComponent(element.val())}`;
		return await fetch(baseUrl + validateEndpoint + query)
			.then(response => response.json())
			.then(result => result);
	}

	function containsSpaces(element) {
		return element.val().includes(" ");
	}

	function lengthIsInBounds(element) {
		return element.val().length >= minLength && element.val().length <= maxLength;
	}

	function passwordsMatch(password, confirmPassword) {
		if (confirmPassword.length > 0) {
			return password.val() === confirmPassword.val() && confirmPassword.val().trim().length > 0;
		} else {
			return true;
		}
	}

	function passwordIsSecure(password) {
		//Regex taken from: https://stackoverflow.com/questions/32311081/check-for-special-characters-in-string
		const special = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
		const numbers = /[0-9]/;
		const uppercase = /[A-Z]/;
		return special.test(password.val()) && numbers.test(password.val()) && uppercase.test(password.val());
	}

	function formatValid(element) {
		element.addClass("is-valid").removeClass("is-invalid").removeClass("is-loading");
	}

	function formatInvalid(element) {
		element.addClass("is-invalid").removeClass("is-valid").removeClass("is-loading");
	}

	function formatLoading(element) {
		element.addClass("is-loading").removeClass("is-invalid").removeClass("is-valid");
	}

	function setAndDisplayValidityMessage(element, message) {
		element.get()[0].setCustomValidity(message);
		element.siblings(".invalid-feedback").first().text(message);
	}

	$("#username.register").on("keyup",async function () {
		formatInvalid($(this));
		if (!lengthIsInBounds($(this))) {
			setAndDisplayValidityMessage($(this),"Username is too short or too long");
			return;
		}
		if (containsSpaces($(this))) {
			setAndDisplayValidityMessage($(this),"Username must not contain spaces");
			return;
		}
		formatLoading($(this));
		const user = await usernameExistsAlready($(this));
		if (user !== null) {
			formatInvalid($(this));
			setAndDisplayValidityMessage($(this), "Username " + user.username + " is already taken.");
			return;
		}
		formatValid($(this));
		setAndDisplayValidityMessage($(this), "");
	});
	$("#email.register").on("keyup", function () {
		formatInvalid($(this));
		if (!lengthIsInBounds($(this))) {
			setAndDisplayValidityMessage($(this),"Email is too short or too long");
		} else if (containsSpaces($(this))) {
			setAndDisplayValidityMessage($(this),"Email must not contain spaces");
		} else {
			formatValid($(this));
			setAndDisplayValidityMessage($(this), "");
		}
	})

	$("#password.register").on("keyup", function () {
		formatInvalid($(this));
		if (!lengthIsInBounds($(this))) {
			setAndDisplayValidityMessage($(this),"Password is too short or too long");
		} else if (containsSpaces($(this))) {
			setAndDisplayValidityMessage($(this),"Password must not contain spaces");
		} else if (!passwordIsSecure($(this))) {
			setAndDisplayValidityMessage($(this),"Password must contain at least one special character, one number, and one uppercase letter.");
		} else {
			formatValid($(this));
			setAndDisplayValidityMessage($(this), "");
		}
	});

	$(".passwords-match-validation").each(function () {
		$(this).on("keyup", function () {
			let confirmPassword = $("#confirm_password");
			if (!passwordsMatch($("#password"), confirmPassword)) {
				formatInvalid(confirmPassword);
				setAndDisplayValidityMessage(confirmPassword, "Passwords do not match");
			} else {
				formatValid(confirmPassword);
				setAndDisplayValidityMessage(confirmPassword, "");
			}
		});
	});

	$(".needs-validation").each(function (idx) {
		$(this).on("submit", function (e) {
			const form = $(this).get(idx);
			if (!form.checkValidity()) {
				e.preventDefault();
				e.stopPropagation();
			}
			$(this).addClass("was-validated");
		});
	});
})();