<template>
  <div>
      <div class="text-title">
          <h1>About web-site</h1>
          <h4>"One two, three four five six seven - eigth nine ten, nine eight seven..."</h4>
          <h5>"Someone`s interesting words..."</h5>
      </div>
      <hr>
      <div class="text-body">
          <h4 style="padding-bottom:30px">Company`s information:</h4>
          <p>Different company information</p>
          <p>Such as phone-number</p>
          <p>Bank information</p>
          <p>Maybe some images</p>
          <p>And etc.</p>
      </div>
      <hr>
      <div class="contact-mail">
          <p style="text-align:center">If you have any business question`s, feel free to contact us: <a href="#">mail@gmail.com</a></p>
      </div>
  </div>
</template>
<script>
export default {
  name: 'About',
}
</script>
<style>
 .text-title {
     text-align: center;
     padding-bottom: 30px;

 }
 .text-body {
     position: relative;
     padding-top: 10px;
     padding-bottom: 30px;
 }
 hr {
    display: block;
    unicode-bidi: isolate;
    -webkit-margin-before: 0.5em;
    -webkit-margin-after: 0.5em;
    -webkit-margin-start: auto;
    -webkit-margin-end: auto;
    overflow: hidden;
    border-style: inset;
    border-width: 1px;
}
body {
    font-family: Arial, sans-serif;
    font-size: 100%;
    font-size: 14px;
    line-height: 20px;
    -webkit-text-size-adjust: none;
    text-align: justify;
}
.contact-mail {
    padding-top: 30px;
}
</style>
