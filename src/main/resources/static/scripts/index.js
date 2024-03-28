function SignUpRedirect() {

    window.location.href = '/api/teamplayer/register';
  }


  function LogInRedirect() {

    window.location.href = '/api/teamplayer/login';
  }
  

  document.getElementById('firstButton').onclick = SignUpRedirect;
  document.getElementById('secondButton').onclick = LogInRedirect;