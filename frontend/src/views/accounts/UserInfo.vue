<template>
  <div id="UserInfo" class="UserInfo container">
    <GoBack/> 
    <span class="UserInfo-text">내 정보</span>
    <hr class="mb-4">
    <div class="form-box">
      <div class="form-block">
          <label class="form-block-head col-3 col-md-2" for="email">
              <span class="form-block-text">Email</span>
          </label>
            <input 
            class="input-text unchangeableInfo col-8"
            :value="User.email"
            id="email" 
            disabled
            />


      </div>

      <div class="form-block">
          <label class="form-block-head col-3 col-md-2" for="username">
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
          <label class="form-block-head col-3 col-md-2" for="curpassword">
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
        <label class="form-block-head col-3 col-md-2" for="password">
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

      <div class="form-group d-flex justify-content-around">
          <label class="form-block-head col-3 col-md-2">
            Address
          </label>
          <div class="col-8 form-address">
            <select class="form-control" id="exampleFormControlSelect1" v-model="siInfo" >
            <option v-if="siInfo" :value="siInfo" v-text="siInfo.siName"></option>
            <option v-else value="" disabled selected>시/도</option>
            <option v-for="si_obj in siList" :key="si_obj.siName" :value="si_obj" v-text="si_obj.siName"></option>
            </select>
            <div class="d-flex sub-address">
              <select class="form-control col-6" id="exampleFormControlSelect2" v-model="guInfo">
                <option v-if="guInfo" :value="guInfo" v-text="guInfo.guName"></option>
                <option v-else value="" disabled selected>구/군</option>
                <option v-for="gu_obj in guList" :key="gu_obj.guName" :value="gu_obj" v-text="gu_obj.guName"></option>
              </select>
              <select class="form-control col-6" id="exampleFormControlSelect3" v-model="dongInfo">
                <option v-if="dongInfo" :value="dongInfo" v-text="dongInfo.dongName"></option>
                <option v-else value="" disabled selected>동/읍/면</option>
                <option v-for="dong_obj in dongList" :key="dong_obj.dongName" :value="dong_obj" v-text="dong_obj.dongName"></option>
              </select>
            </div>
          </div>
      </div>

      <div class="form-block">
          <label class="form-block-head col-3 col-md-2" for="TEL">
            Tel
          </label>
          <input 
              class="input-text col-8"
              v-model="User.tel" 
              id="tel" 
              placeholder="Tel" 
              type="text"
          />

      </div>
    </div>

    <div class="d-flex justify-content-around">
      <span class="col-3 col-md-2" >
      </span>
      <div class="col-8 pr-0">
        <Button
            @click.native="changeUserInfo"
            buttonText="회원정보수정"
            class="btn-components"
        />
      </div>
    </div>

  </div>
</template>

<script>


import URL from "@/util/http-common.js"
import axios from "axios"
import Button from "@/components/common/Button.vue"
import PV from "password-validator"
import GoBack from '@/components/common/GoBack.vue'

export default {
  name: 'UserInfo',
  components: {
    Button,
    GoBack
  },
  watch: {
    siInfo: function() {
      this.getGuInfo()
    },
    guInfo: function() {
      this.getDongInfo()
    }
  },
  created() {
    this.$emit('sidebar')
    if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }

    axios.get(`${URL.BASE_URL}/fselect`, URL.JSON_HEADER)
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
    this.User.userno = this.$store.getters.getUserData.userno
    this.User.username = this.$store.getters.getUserData.name,
    this.User.email =  this.$store.getters.getUserData.email,
    this.User.password =  this.$store.getters.getUserData.pw,
    this.User.ispro =  this.$store.getters.getUserData.ispro,
    this.User.tel =  this.$store.getters.getUserData.tel,
    this.siInfo.siName =  this.$store.getters.getUserData.addr2,
    this.siInfo.siCode =  this.$store.getters.getUserData.addr1,
    this.guInfo.guName =  this.$store.getters.getUserData.addr4,
    this.guInfo.guCode =  this.$store.getters.getUserData.addr3,
    this.dongInfo.dongName =  this.$store.getters.getUserData.addr6,
    this.dongInfo.dongCode =  this.$store.getters.getUserData.addr5
  },
  data() {
    return {
      User: {
        username: "",
        email: "",
        password: "",
        ispro: "",
        tel: "",
        addr1: "",
        addr2: "",
        addr3: "",
        addr4: "",
        addr5: "",
        addr6: "",
        userno: "",
      },
      curPassword: "",
      changePassword: "",
      changePasswordConfirm: "",
      isError: false,
      passwordSchema: new PV(),
      isChangedPW: false,
      siList: [],
      guList: [],
      dongList: [],
      siInfo: {
        siName: "",
        siCode: ""
      },
      guInfo: {
        guName: "",
        guCode: "",
      },
      dongInfo: {
        dongName: "",
        dongCode: "",
      },
    }
  },
  methods: {
    getGuInfo() {
      let si_params = this.siInfo
      this.guList = []
      this.dongList = []
      this.guInfo = ""
      this.dongInfo = ""

      axios.get(`${URL.BASE_URL}/fselect/${si_params.siCode}`, URL.JSON_HEADER)
      .then(res => {
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
      axios.get(`${URL.BASE_URL}/fselect/sido/${gu_params.guCode}`, URL.JSON_HEADER)
      .then(res => {

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
    checkform() {
        if (!this.curPassword) {
          this.isError = "현재 비밀번호를 입력하여야 정보수정이 가능합니다."
          return
        }
        else this.isError = false

        if (this.User.password !== this.curPassword) {
          this.isError = "현재 비밀번호가 일치하지 않습니다."
          return
        }
        else this.isError = false

        if (this.changePassword === "") this.isChangedPW = false
        else this.isChangedPW = true

        if (this.changePassword){
          if (this.User.password === this.changePassword) {
            this.isError = "변경할 패스워드는 현재 패스워드와 달라야합니다."
            return
          }
          else this.isError = false
          if (this.changePassword.length > 0 && !this.passwordSchema.validate(this.changePassword)) {
            this.isError = "비밀번호는 영문, 숫자포함 8자리 이상이어야 합니다."
            return
          }
          else this.isError = false
        }

        if (this.changePasswordConfirm.length > 0 && this.changePasswordConfirm !== this.changePassword) {
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
        userno: this.User.userno,
        name: this.User.username,
        email: this.User.email,
        pw: this.changePassword,
        tel: this.User.tel,
        ispro: this.User.ispro,
        addr1: this.siInfo.siCode,
        addr2: this.siInfo.siName,
        addr3: this.guInfo.guCode,
        addr4: this.guInfo.guName,
        addr5: this.dongInfo.dongCode,
        addr6: this.dongInfo.dongName
      }

      if (!this.isChangedPW) changeUser.pw = this.curPassword

      axios.post(`${URL.BASE_URL}/account/userinfo/modify`, changeUser, {
            headers: {
              'Authorization': this.$session.get('jwstoken'),
            }
          })
      .then(() => {
        console.log(this.User)
        this.$store.commit('setUserInfo',changeUser)
        alert("회원정보가 수정되었습니다.")
        this.$router.push({ name: "Home" })
      })
      .catch(err => {
        console.log(err)
        alert("알 수 없는 오류가 발생했습니다.")
      })

    }
  }
}
</script>

<style>
#UserInfo .UserInfo-text {
  display: block;
  font-size: 2rem;
  /* font-weight: bold; */
  margin: 20px 0;
  
}

#UserInfo  .UserInfo {
  padding: 0 30px;
  margin-top: 30px;
}
#UserInfo input:focus {
outline: none;
box-shadow: 0 0 0 3px #3487e683;
border: none;
}

#UserInfo .passwordConfirm {
  margin: 10px 0 25px;
}

#UserInfo .input-text {
width: 100%;
height: 40px;
border: 0.8px;
padding-left: 10px;
border-style: none none solid none;
}

#UserInfo .unchangeableInfo {
  border-style: none;
  /* font-weight: bold; */
  background-color: #bcbdbc;
} 

#UserInfo .form-block-head {
  font-size: 16px;
  margin: 0;
  padding: 8px 0 0 0;
  /* text-align: right; */
  /* font-weight: bolder */
}

#UserInfo .new-password {
  padding: 0;
}

#UserInfo .form-block {
  display: flex;
  margin-bottom: 25px;
  justify-content: space-around;
}

#UserInfo .btn-components {
  margin-top: 0px;
  float: right;
}

#UserInfo select {
    width: 100%;
    height: 40px;
    border: 0.8px;
    border-radius: 0;
    padding-left: 10px;
    border-style: none none solid none;
}
#UserInfo .form-address {
  padding: 0px;
}

#UserInfo .sub-address {
  margin-top: 10px;
}

#UserInfo .error-msg {
  width: 100%;
  color: #EE4B55;
  font-size: 14px;
  text-align: right;
}
</style>