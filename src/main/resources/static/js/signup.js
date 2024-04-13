document.addEventListener("DOMContentLoaded", function () {
  const idInput = document.getElementById("id");
  const emailInput = document.getElementById("email");
  const nameInput = document.getElementById("name");
  const passwordInput = document.getElementById("password");
  const password1Input = document.getElementById("password1");
  const signupButton = document.getElementById("signup-button");

  const showError = (input, message) => {
    const errorElement = document.getElementById(`${input.id}-error`);
    errorElement.innerText = message;
  };

  const hideError = (input) => {
    const errorElement = document.getElementById(`${input.id}-error`);
    errorElement.innerText = "";
  };

  const checkInputs = () => {
    const idValue = idInput.value.trim();
    const emailValue = emailInput.value.trim();
    const nameValue = nameInput.value.trim();
    const passwordValue = passwordInput.value.trim();
    const password1Value = password1Input.value.trim();

    if (idValue === "") {
      showError(idInput, "아이디를 입력하세요.");
    } else {
      hideError(idInput);
    }

    if (emailValue === "") {
      showError(emailInput, "이메일을 입력하세요.");
    } else {
      hideError(emailInput);
    }

    if (nameValue === "") {
      showError(nameInput, "이름을 입력하세요.");
    } else {
      hideError(nameInput);
    }

    if (passwordValue === "") {
      showError(passwordInput, "비밀번호를 입력하세요.");
    } else {
      hideError(passwordInput);
    }

    if (password1Value === "") {
      showError(password1Input, "비밀번호를 다시 입력하세요.");
    } else if (password1Value !== passwordValue) {
      showError(password1Input, "비밀번호가 일치하지 않습니다.");
    } else {
      hideError(password1Input);
    }
  };

  const isFormValid = () => {
    return document.querySelectorAll(".error-message").length === 0;
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    checkInputs();

    if (isFormValid()) {
      alert("회원가입 성공!");
      window.location.href = "library.html";
    }
  };

  signupButton.addEventListener("click", handleSubmit);
});
