<template>
  <div class="signup">
    <span class="signup-text">Sign Up</span>
    <hr class="mb-4 ">
    <div class="signup-head">
      <em class="asterisk-red">*</em>
      <span>사용자 구분: </span>
      <div class="ml-3">
          <input class="input-radio" type="radio" id="USER" @click="isPro=false" value="USER" name="isPro" checked>
          <label for="USER">USER</label>
          <input class="input-radio" type="radio" id="PRO" @click="isPro=true" value="PRO" name="isPro">
          <label for="PRO">PRO</label>
      </div>
    </div>
    <div class="error-msg" v-if="errorData.userInfo" v-text="errorData.userInfo"></div>

    <div class="form-block">
        <label class="form-block-head" for="username">
            <em class="asterisk-red">*</em>
            Username:
        </label>
        <input 
        class="input-text"
        v-model="username" 
        id="username" 
        placeholder="유저명을 입력하세요." 
        type="text" 
        autocapitalize="none"
        :class="{ error : errorData.username && this.username }"
        />
        <div class="error-msg" v-if="errorData.username">{{ errorData.username }}</div>
    </div>

    <div class="form-block">
        <label class="form-block-head" for="email">
            <em class="asterisk-red">*</em>
            Email:
        </label>
        <input 
        class="input-text"
        v-model="email" 
        id="email" 
        placeholder="이메일을 입력하세요." 
        type="text" 
        autocapitalize="none"
        :class="{ error : errorData.email  && this.email }"
        />
        <div class="error-msg" v-if="errorData.email && email">{{ errorData.email }}</div>
    </div>

    <div class="form-block">
      <label class="form-block-head" for="password">
          <em class="asterisk-red">*</em>
          Password:
      </label>
      <input 
          class="input-text"
          v-model="password" 
          id="password" 
          placeholder="비밀번호를 입력하세요." 
          type="password"
          autocapitalize="none"
          :class="{ error : errorData.password && this.password}"
      />
      <div class="error-msg" v-if="errorData.password && password">{{ errorData.password }}</div>

      <div class="passwordConfirm">
          <input 
              class="input-text"
              v-model="passwordConfirm" 
              id="passwordConfirm" 
              placeholder="비밀번호 확인" 
              type="password"
              autocapitalize="none"
              @keyup.enter="signUp"
              :class="{ error : errorData.passwordConfirm && this.passwordConfirm }"
          />
          <div class="error-msg" v-if="errorData.passwordConfirm && passwordConfirm">{{ errorData.passwordConfirm }}</div>
      </div>
    </div>


    <form>
      <div class="form-group row">
        <div class="col-12">
          <label for="exampleFormControlSelect1">
            <em class="asterisk-red">*</em>
            Address
            </label>
          <select class="form-control" id="exampleFormControlSelect1" v-model="siInfo">
            <option value="none" disabled selected>시/도</option>
            <option v-for="siItem in siList" :key="siItem" v-text="siItem"></option>
          </select>
        </div>
        <div class="col-12 d-flex sub-address">
          <select class="form-control col-6" id="exampleFormControlSelect1" v-model="guInfo">
            <option value="none" disabled selected>구/군</option>
            <option v-for="guItem in guList" :key="guItem" v-text="guItem"></option>
          </select>
          <select class="form-control col-6" id="exampleFormControlSelect1" v-model="dongInfo" >
            <option value="none" disabled selected>동/읍/면</option>
            <option v-for="dongItem in dongList" :key="dongItem" v-text="dongItem"></option>
          </select>
        </div>
      </div>
    </form>

    <div class="form-block">
        <label class="form-block-head" for="TEL">
          <em class="asterisk-red">*</em>
          Tel:
          </label>
        <input 
            class="input-text"
            v-model="tel" 
            id="tel" 
            placeholder="휴대전화번호" 
            type="text"
            autocapitalize="none"
        />
    </div>
    <label>
      <em class="asterisk-red">*</em>
      <input v-model="isTerm" type="checkbox" id="term" />
      <span>약관을 동의합니다.</span>
    </label>
    <TermModal v-if="termPopup" @close="termPopup = false">
    <h3 slot="header">전체 약관</h3>
    </TermModal>  
    <span @click="termPopup=true">약관보기</span>
    <div class="error-msg" v-if="errorData.isTerm" v-text="errorData.isTerm"></div>
    <Button 
        @click.native="signUp"
        :disabled="!isSubmit"
        buttonText="SignUp"
        class="btn-components"
        :class="{ deactivate : !isSubmit }"
    />

  </div>
</template>

<script>

import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from "axios"
import Button from "@/components/common/Button.vue"
import TermModal from "@/components/modal/TermModal.vue"

let signUpUrl = '/tmp'
// let getSiUrl = '/si'
// let getGuUrl = '/gu'
// let getDongUrl = '/dong'

export default {
    name: 'SignUp',
    components: {
      Button,
      TermModal
    },
    created() { 
      axios.get("http://192.168.100.67:9999/happyhouse/api/fselect", {
        headers: {
            'Content-Type': 'application/json',
        }
      })
      .then(res => {
        // this.siList = res
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      }),
      this.passwordSchema
        .is()
        .min(8)
        .is()
        .max(100)
        .has()
        .digits()
        .has()
        .letters();
    },
    data() {
      return {
        username: "",
        password: "",
        passwordConfirm: "",
        email: "",
        tel: "",
        errorData: {
          username: false,
          password: false,
          passwordConfirm: false,
          email: false,
          isTerm: false,
        },
        siList: [],
        guList: [],
        dongList: [],
        siInfo: "none",
        guInfo: "none",
        dongInfo: "none",
        passwordSchema: new PV(),
        isSubmit: false,
        isTerm: false,
        isPro: false,
        termPopup: false,
      }
    },
    watch: {
      password: function() {
        this.formCheck()
      },
      email: function() {
        this.formCheck()  
      },
      username: function() {
        this.formCheck()
      },
      passwordConfirm: function() {
        this.formCheck()
      },
    },
    methods: {
      getGuInfo() {
        axios.get('/sadf', this.siInfo)
        .then(res => {
          console.log(res)
        }).catch(err => {
          console.log(err)
        })
      },
      getDongInfo() {
        axios.get('/sadf', this.guInfo)
        .then(res => {
          console.log(res)
        }).catch(err => {
          console.log(err)
        })
      },
      formCheck() {
        if (this.username && (this.username.length < 3 || this.username.length > 8))
          this.errorData.username = "username은 3자이상 8자 이하여야 합니다."
        else this.errorData.username = false

        if (this.email === "" || (this.email.length > 0 && !EmailValidator.validate(this.email)))
          this.errorData.email = "올바른 이메일 형식이 아닙니다."
        else this.errorData.email = false

        if (this.password === "" || (this.password.length > 0 && !this.passwordSchema.validate(this.password)))
          this.errorData.password = "비밀번호는 영문, 숫자포함 8자리 이상이어야 합니다."
        else this.errorData.password = false

        if (this.passwordConfirm === "" || (this.passwordConfirm.length > 0 && this.passwordConfirm !== this.password))
          this.errorData.passwordConfirm = "비밀번호가 일치하지 않습니다."
        else this.errorData.passwordConfirm = false

        let isSubmit = true;  
        Object.values(this.errorData).map(v => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      },
      signUp() {
        console.log(this.siInfo)
        // console.log(this.email)
        let signUpData = {
          'email': this.email,
          'username': this.username,
          'password': this.password,
          'tel': this.tel,
          'address': this.siInfo + this.guInfo + this.dongInfo,
          'isPro': this.isPro
        }

        console.log(signUpData)
        if (this.username === null || this.username.length > 8){
          alert('닉네임은 1글자 이상 8글자 이하여야 합니다.')
          return
        }
        if (this.email === null || this.email.length > 128){
          alert('이메일은 128자 이하여야 합니다.')
          return
        }
        if (this.password !== this.passwordConfirm) {
            alert('비밀번호와 비밀번호 확인이 맞지 않습니다.')
            return
        }
        if (this.password.length < 8 || this.password.length > 128) {
            alert('비밀번호는 영문, 숫자 포함 8글자 이상 128글자 이하여야 합니다.')
            return
        }

        if (!this.isTerm) {
          this.errorData.isTerm = "약관에 동의해야 합니다."
          return
        }
        else this.errorData.isTerm = false

        axios.post(signUpUrl, signUpData)
        .then(res => {
          this.$router.push('/')
          this.isSubmit = true
          console.log(res)
        })
        .catch(err => {
          this.isSubmit = true
          console.log(err)
        })
      }
    }
}
</script>

<style scoped>
.btn-components {
  width: 100%;
  outline: none;
  box-shadow: 0 0 0 1.5px #EE4B55;
}

.deactivate {
  background-color: rgb(158, 69, 59);

}

.passwordConfirm {
  margin: 10px 0 25px;
}
.signup-head {
  display: flex;
  font-size: 15px;
  margin: 15px 4px;
}
.error-msg {
  width: 100%;
  float: left;
  color: #EE4B55;
  font-size: 14px;
}
.signup {
  padding: 0 30px;
  margin-top: 30px;
}
.signup-text {
  font-size: 30px;
  font-weight: bolder;
  margin-bottom: 10px;
  /* color: #3487e683; */
}

.form-block-head {
  display: block;
  font-size: 16px;
}

.form-block {
  margin-bottom: 25px;
}
.asterisk-red {
  color: red;
}

.input-text {
  width: 100%;
  height: 40px;
  border-radius: 10px;
  border: 0.8px solid;
  padding-left: 10px;
}

.form-control {
  border-radius: 10px;
  border: 0.8px solid;
}

.sub-address {
  margin-top: 10px;
}

input:focus {
  outline: none;
  box-shadow: 0 0 0 1.5px #3487e683;
}

.input-radio {
  margin: 0 5px;
  width: 15px;
  height: 15px;
}

.error {
  border: 1px solid;
  border-color: #EE4B55;
  outline-style: none;
}
.form-group label:first-child {
  display: block;
}
/* .form-group div:nth-child(2) {
  padding-left: 40px;
}
.form-group div:nth-child(2) select {
  margin-left: 20px;
} */
</style>