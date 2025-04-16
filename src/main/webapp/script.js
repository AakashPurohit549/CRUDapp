document
	.getElementById('UpdateModal')
	.addEventListener(
		'show.bs.modal',
		function(event) {
			var button = event.relatedTarget;

			var userId = button.getAttribute('data-id');
			var userName = button
				.getAttribute('data-name');
			var userEmail = button
				.getAttribute('data-email');
			var userCountry = button
				.getAttribute('data-country');
			var userNumber = button
				.getAttribute('data-number');

			console.log(userId);
			console.log(userName);
			console.log(userEmail);
			console.log(userCountry);
			console.log(userNumber);

			document.getElementById('id').value = userId;
			document.getElementById('n').value = userName;
			document.getElementById('email').value = userEmail;
			document.getElementById('country').value = userCountry;
			document.getElementById('m').value = userNumber;

		});

document
	.getElementById('updateform')
	.addEventListener(
		'submit',
		function(event) {
			event.preventDefault();

			var name = document.getElementById('n').value;
			var num = document.getElementById('m').value;
			console.log(name);
			console.log(num);

			let isValid = true;
			var nameRegx = /^[A-Za-z\s]+$/;
			if (!nameRegx.test(name)) {
				alert("No digits allowed, please enter the name");
				isValid = false;
			}

			var mobilePattern = /^\d{10}$/;
			if (!mobilePattern.test(num)) {
				alert("Please enter a valid 10-digit mobile number.");
				isValid = false;
			}

			if (isValid) {
				document.getElementById('updateform')
					.submit();
			}

		});

function validate() {
	var num = document.getElementById('number').value.trim();
	console.log(num);

	let isValid = true;
	var mobilePattern = /^\d{10}$/;
	if (!mobilePattern.test(num)) {
		alert("Please enter a valid 10-digit mobile number.");
		isValid = false;
	}

	var name = document.getElementById('name').value.trim();
	console.log(name);

	var nameRegx = /^[A-Za-z\s]+$/;
	if (!nameRegx.test(name)) {
		alert("No digits allowed, please enter the name");
		isValid = false;
	}

	return isValid;
}