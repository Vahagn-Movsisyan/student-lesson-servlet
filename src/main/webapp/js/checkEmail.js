function checkEmailError() {
    const emailErrorSpan = document.getElementById("emailError");
    const emailInput = document.getElementById("studentEmail");

    if (emailInput.value && emailErrorSpan.style.display === "none") {
        fetch("/checkEmail", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: emailInput.value
            })
        })
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    emailErrorSpan.style.display = "inline";
                } else {
                    emailErrorSpan.style.display = "none";
                }
            })
            .catch(error => {
                console.error("Ошибка при проверке email:", error);
            });
    } else {
        emailErrorSpan.style.display = "none";
    }
}

document.getElementById("studentEmail").addEventListener("input", checkEmailError);
