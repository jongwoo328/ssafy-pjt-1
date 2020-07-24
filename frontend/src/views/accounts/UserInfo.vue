<template>
  <div class="UserInfo">
    <span class="UserInfo-text">회원정보</span>
    <hr class="mb-4 ">

    <div class="form-block">
        <label class="form-block-head col-3" for="email">
            <span class="form-block-text">Email</span>
        </label>
          <input 
          class="input-text unchangeableInfo col-8"
          :value="User.email"
          id="email" 
          disabled
          />

        <!-- <span v-if="email && duplicate.email">이미 존재하는 이메일입니다.</span> -->
        <!-- <span v-else>이 이메일은 사용가능합니다.</span> -->
    </div>

    <div class="form-block">
        <label class="form-block-head col-3" for="username">
          Username
        </label>
          <input 
          class="input-text unchangeableInfo col-8"
          :value="User.username" 
          id="username" 
          disabled
          />
    </div>

    <div class="form-block">
        <label class="form-block-head col-3" for="curpassword">
          Your Password
        </label>
          <input 
          class="input-text col-8"
          v-model="curPassword"
          id="curPassword" 
          type="password"
          placeholder="Enter your password"
          />
    </div>

    <div class="form-block">
      <label class="form-block-head col-3" for="password">
        New Password
      </label>
      <div class="col-8 new-password">
        <input 
            class="input-text"
            v-model="changePassword" 
            id="password" 
            placeholder="New password" 
            type="password"
            autocapitalize="none"
        />

        <div class="passwordConfirm">
            <input 
                class="input-text"
                v-model="changePasswordConfirm" 
                id="passwordConfirm"
                aria-label="Email or phone"
                placeholder="Password confirm" 
                type="password"
                autocapitalize="none"
                @keyup.enter="signUp"
            />
        
        </div>
      </div>
    </div>

    <div class="form-block">
        <label class="form-block-head col-3" for="Address">
          Address
        </label>
          <input 
          class="input-text col-8"
          v-model="User.address" 
          id="Address" 
          type="text"
          placeholder="Address"
          />
    </div>

    <div class="form-block">
        <label class="form-block-head col-3" for="TEL">
          Tel
        </label>
        <input 
            class="input-text col-8"
            v-model="User.tel" 
            id="tel" 
            placeholder="Tel" 
            type="text"
            autocapitalize="none"
        />

    </div>

    <Button 
        @click.native="changeUserInfo"
        buttonText="회원정보수정"
        class="btn-components"
    />

  </div>
</template>

<script>
import axios from "axios"
import Button from "@/components/common/Button.vue"
import PV from "password-validator";

export default {
  name: 'UserInfo',
  components: {
    Button
  },
  created() {
    axios.get("https://address:port/destination")
    .then(res => {
      // res.data 에 유저의 정보가 담겨있다고 생각하고 작성
      // this.user = res.data
      console.log(res)
    })
    .catch(err => {
      console.log(err)
    })
  },
  data() {
    return {
      User: {
        username: "stebia",
        email: "asdfasdf@naver.com",
        password: "@qhdks123",
        address: "경상북도 구미시 진평동",
        ispro: "",
        tel: "01012345678",
      },
      curPassword: "",
      changePassword: "",
      changePasswordConfirm: "",
      isError: false,
      passwordSchema: new PV(),
      changeSuccess: false
    }
  },
  methods: {
    checkform() {

        if (this.User.password !== this.curPassword) {
          this.isError = "비밀번호가 일치하지 않습니다."
          return
        }
        else this.isError = false

        if (this.User.password === this.changePassword) {
          this.isError = "현재 패스워드와 변경할 패스워드가 같습니다."
          return
        }
        else this.isError = false

        if (this.changePassword === "" || (this.changePassword.length > 0 && !this.passwordSchema.validate(this.changePassword))) {
          this.isError = "비밀번호는 영문, 숫자포함 8자리 이상이어야 합니다."
          return
        }
        else this.isError = false

        if (this.changePasswordConfirm === "" || (this.changePasswordConfirm.length > 0 && this.changePasswordConfirm !== this.changePassword)) {
          this.isError = "비밀번호 확인이 일치하지 않습니다."
          return
        }
        else this.isError = false

    },
    changeUserInfo() {
      
      this.checkform()
      if (this.isError) {
        alert(this.isError)
        return
      }
      let changeUser = {
        username: this.User.username,
        email: this.User.email,
        password: this.changePassword,
        address: this.User.address,
        tel: this.User.tel
      }

      axios.post("http://address:port/changeUserInfo", changeUser)
      .then(res => {
        console.log(res)
        alert("회원정보가 수정되었습니다.")
      })
      .catch(err => {
        console.log(err)
        alert("알 수 없는 오류가 발생했습니다.")
      })

    }
  }
}
</script>

<style scoped>
.UserInfo-text {
  display: block;
  font-size: 30px;
  font-weight: bolder;
  margin-bottom: 20px;
}

.UserInfo {
  padding: 0 30px;
  margin-top: 30px;
}
input:focus {
outline: none;
box-shadow: 0 0 0 3px #3487e683;
border: none;
}

.passwordConfirm {
  margin: 10px 0 25px;
}

.input-text {
width: 100%;
height: 40px;
border: 0.8px;
padding-left: 10px;
border-style: none none solid none;
}

.unchangeableInfo {
  border-style: none;
  font-weight: bold;
  background-color: #adafad;
}

.form-block-head {
  font-size: 16px;
  margin: 0;
  padding: 8px 0 0 0;
  text-align: right;
  font-weight: bolder
}

.new-password {
  padding: 0;
}
.form-block {
  display: flex;
  margin-bottom: 25px;
  justify-content: space-between;
}

.btn-components {
  margin-top: 30px;
  float: right;
}

</style>