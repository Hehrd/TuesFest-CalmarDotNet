function SignUpRedirect() {

    window.location.href = '/api/hacktues/signup';
  }


  function LogInRedirect() {

    window.location.href = '/api/hacktues/login';
  }
  

  document.getElementById('firstButton').onclick = SignUpRedirect;
  document.getElementById('secondButton').onclick = LogInRedirect;