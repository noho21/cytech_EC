const registered = document.getElementById("registered-box");
const newBox = document.getElementById("new-box");

document.querySelectorAll('input[name="receivePlace"]').forEach(radio => {
	radio.addEventListener("change", () => {
		if (radio.value === "REGISTERED") {
			registered.style.display = "block";
			newBox.style.display = "none";
		} else {
			registered.style.display = "none";
			newBox.style.display = "block";
		}
	});
});

document.addEventListener("DOMContentLoaded", () => {
  registered.style.display = "block";
  newBox.style.display = "none";
});

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("purchaseForm");
  const error = document.getElementById("payMethodError");

  form.addEventListener("submit", (e) => {
    const selected = document.querySelector(
      'input[name="payMethod"]:checked'
    );
  });
});
