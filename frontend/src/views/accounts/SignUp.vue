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
        <label class="form-block-head" for="email">
            <em class="asterisk-red">*</em>
            Email:
        </label>
        <div class="d-flex justify-content-between">
          <input 
          class="input-text col-8"
          v-model="email" 
          id="email" 
          placeholder="이메일을 입력하세요." 
          type="text" 
          autocapitalize="none"
          :class="{ error : errorData.email  && this.email }"
          />
          <Button 
              @click.native="idCheck"
              buttonText="중복체크"
              class="btn-components id-check-btn"
            />
        </div>
        <div class="error-msg" v-if="errorData.email && (email || isFail)">{{ errorData.email }}</div>
        <span class="dup-err" v-if="email && duplicate.email === duplicate.impos">이미 존재하는 이메일입니다.</span>
        <span class="success" v-if="email && duplicate.email === duplicate.pos">이 이메일은 사용가능합니다.</span>
    </div>

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
          <!-- <span class="col-1"> </span> -->
        <div class="error-msg" v-if="errorData.username && (username || isFail)">{{ errorData.username }}</div>
        <span class="dup-err" v-if="username && duplicate.username === duplicate.impos">이미 존재하는 사용자명입니다.</span>
        <span class="success" v-if="username && duplicate.username === duplicate.pos">이 사용자명은 사용가능합니다.</span>
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
      <div class="error-msg" v-if="errorData.password && (password || isFail)">{{ errorData.password }}</div>

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
          <div class="error-msg" v-if="errorData.passwordConfirm && (passwordConfirm || isFail)">{{ errorData.passwordConfirm }}</div>
      </div>
    </div>


    <form>
      <div class="form-group row">
        <div class="col-12">
          <label for="exampleFormControlSelect1">
            <em class="asterisk-red">*</em>
            Address
          </label>
          <select class="form-control" id="exampleFormControlSelect1" v-model="siInfo" >
            <option value="" disabled selected>시/도</option>
            <option v-for="si_obj in siList" :key="si_obj.siName" :value="si_obj.siCode" v-text="si_obj.siName"></option>
          </select>
        </div>
        <div class="col-12 d-flex sub-address">
          <select class="form-control col-6" id="exampleFormControlSelect2" v-model="guInfo">
            <option value="" disabled selected>구/군</option>
            <option v-for="gu_obj in guList" :key="gu_obj.guName" :value="gu_obj.guCode" v-text="gu_obj.guName"></option>
          </select>
          <select class="form-control col-6" id="exampleFormControlSelect3" v-model="dongInfo" >
            <option value="" disabled selected>동/읍/면</option>
            <option v-for="dong_obj in dongList" :key="dong_obj.dongName" :value="dong_obj.dongCode" v-text="dong_obj.dongName"></option>
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
            placeholder="휴대전화번호를 입력하세요 ('-' 제외)" 
            type="text"
            autocapitalize="none"
        />
        <div class="error-msg" v-if="errorData.tel && (tel || isFail)">{{ errorData.tel }}</div>
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
    <div class="error-msg tems-err-msg" v-if="errorData.isTerm" v-text="errorData.isTerm"></div>
    <Button 
        @click.native="signUp"
        buttonText="SignUp"
        class="btn-components"
    />

  </div>
</template>

<script>

let BASE_URL = "http://192.168.219.162"

import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from "axios"
import Button from "@/components/common/Button.vue"
import TermModal from "@/components/modal/TermModal.vue"



export default {
    name: 'SignUp',
    components: {
      Button,
      TermModal
    },
    created() {
      axios.get(`${BASE_URL}:3000/happyhouse/api/fselect`, {
        headers: {
            'Content-Type': 'application/json',
        }
      })
      .then(res => {
        for (let si_data in res.data) {
          this.siList.push({
            "siCode": res.data[si_data]["sido_code"],
            "siName": res.data[si_data]["sido_name"]
            })
        }
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
          tel: false,
        },
        siList: [],
        guList: [],
        dongList: [],
        siInfo: "",
        guInfo: "",
        dongInfo: "",
        passwordSchema: new PV(),
        isSubmit: false,
        isTerm: false,
        isPro: false,
        termPopup: false,
        isFail: false,
        duplicate: {
          username: "",
          email: "",
          pos: "possible",
          impos: "impossible"
        }
        // phoneNumValidation: /^\w{10}$/
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
        if (!this.errorData.username) {
          this.usernameCheck()
        }
      },
      passwordConfirm: function() {
        this.formCheck()
      },
      isTerm: function() {
        this.formCheck()
      },
      siInfo: function() {
        this.getGuInfo()
      },
      guInfo: function() {
        this.getDongInfo()
      }
    },
    methods: {
      usernameCheck() {
        console.log("test")
        axios.get(`${BASE_URL}:8090/app/account/idcheck`, this.email,{
          headers: {
              'Content-Type': 'application/json',
          }
        }).then(res => {
          console.log(res)
          if (res) this.duplicate.username = "possible"
        }).catch(err => {
          console.log(err)
          if (err) this.duplicate.username = "impossible"
        })
      },
      idCheck() {
        axios.post(`${BASE_URL}:8090/app/account/idcheck`, this.email, {
        headers: {
            'Content-Type': 'application/json',
        }
      }).then(res => {
        console.log(res)
        if (res) this.duplicate.email = "possible"
      }).catch(err => {
        console.log(err)
        if (err) this.duplicate.email = "impossible"
      })
      },
      getGuInfo() {
        let si_params = this.siInfo
        this.guList = []
        this.dongList = []
        this.guInfo = ""
        this.dongInfo = ""

        axios.get(`${BASE_URL}:3000/happyhouse/api/fselect/${si_params}`, {
        headers: {
            'Content-Type': 'application/json',
        }
      }).then(res => {
          for (let gu_data in res.data) {
            this.guList.push({
              "guCode": res.data[gu_data]["gugun_code"],
              "guName": res.data[gu_data]["gugun_name"]
              })
          }

        }).catch(err => {
          console.log(err)
        })
      },
      getDongInfo() {
        let gu_params = this.guInfo
        this.dongList = []
        this.dongInfo = ""
        // console.log(gu_params)
        axios.get(`${BASE_URL}:3000/happyhouse/api/fselect/sido/${gu_params}`, {
        headers: {
            'Content-Type': 'application/json',
        }
      })
        .then(res => {
          // console.log(res)

          for (let dong_data in res.data) {
            this.dongList.push({
              "dongCode": res.data[dong_data]["code"],
              "dongName": res.data[dong_data]["dong"]
              })
          }
        }).catch(err => {
          console.log(err)
        })
      },
      formCheck() {
        if (this.username === "" || this.username && (this.username.length < 3 || this.username.length > 8))
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

        if (!this.isTerm) {
          this.errorData.isTerm = "약관에 동의해야 합니다."
        }
        else this.errorData.isTerm = false

        // if (this.tel === "" || (this.tel.length > 0 && !this.tel.value.match(this.phoneNumValidation)))
        //   this.errorData.tel = "전화번호를 입력하세요"
        // else this.errorData.tel = false

        let isSubmit = true;  
        Object.values(this.errorData).map(v => {
          if (v) isSubmit = false;
        });
        this.isSubmit = isSubmit;
      },
      signUp() {
        this.formCheck()

        if (!this.isSubmit) {
          this.isFail = true
          return
        }
        else this.isFail = false

        let signUpData = {
          'email': this.email,
          'username': this.username,
          'password': this.password,
          'tel': this.tel,
          'address': this.dongInfo,
          'isPro': this.isPro
        }


        console.log(signUpData)
        axios.post(`${BASE_URL}:3000/account/signup`, signUpData)
        .then(res => {
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
.duplicateMsg {
  color: greenyellow;
}

.duplicate {
  color: red;
}

.id-check-btn {
  height: 40px;
  font-size: 17px;
  padding: 7px;
  margin-left: 10px;

}
.btn-components {
  width: 100%;
  outline: none;
  box-shadow: 0 0 0 3px #EE4B55;
}

.dup-err {
  color: #EE4B55;
}

.success {
  color: greenyellow;
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
  display: block;
  font-size: 30px;
  font-weight: bolder;
  margin-bottom: 20px;
  /* color: #3487e683; */
}

.terms {
  margin-bottom: 0;
}

.terms-err-msg {
  margin-left: 20px;
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

</style>