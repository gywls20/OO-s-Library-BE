document.addEventListener("DOMContentLoaded", function () {
  const idInput = document.getElementById("id");
  const passwordInput = document.getElementById("password");
  const loginButton = document.getElementById("login-button");

  idInput.addEventListener("input", toggleLoginButton);
  passwordInput.addEventListener("input", toggleLoginButton);

  document
    .getElementById("login-form")
    .addEventListener("submit", function (event) {
      event.preventDefault();
      const id = idInput.value;
      const password = passwordInput.value;

      axios
        .post("http://localhost:8080/login", { id, password })
        .then((response) => {
          const { data } = response;
          console.log(data);
          console.log("서버 fetch 성공");

          if (data.pk) {
            const { pk, memberName, memberId } = data;

            alert("로그인에 성공했습니다!");
            window.location.href = "/library.html";
          } else {
            alert("비밀번호 또는 아이디가 틀렸습니다.");
          }
        })
        .catch((error) => {
          console.error("서버 fetch 실패", error);
          alert("로그인에 실패했습니다.");
        });
    });
});

function toggleLoginButton() {
  const id = document.getElementById("id").value;
  const password = document.getElementById("password").value;
  const loginButton = document.getElementById("login-button");

  if (id.trim() !== "" && password.trim() !== "") {
    loginButton.removeAttribute("disabled");
  } else {
    loginButton.setAttribute("disabled", "true");
  }
}

function socialLogin(provider) {
  console.log("Social login with:", provider);
}
