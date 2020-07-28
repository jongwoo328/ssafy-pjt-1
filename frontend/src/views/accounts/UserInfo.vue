<template>
  <div class="UserInfo container">
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

          <!-- <span v-if="email && duplicate.email">이미 존재하는 이메일입니다.</span> -->
          <!-- <span v-else>이 이메일은 사용가능합니다.</span> -->
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

      <!-- <div class="form-block">
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
      </div> -->


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
      <span class="col-3 col-md-2"></span>
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

let BASE_URL = "http://192.168.100.88"

import axios from "axios"
import Button from "@/components/common/Button.vue"
import PV from "password-validator";

export default {
  name: 'UserInfo',
  components: {
    Button
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
    // console.log(this.User)
    axios.get(`${BASE_URL}:8090/fselect`, {
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
    console.log(this.$session.get('jwstoken')),
    
    axios.post(`${BASE_URL}:8090/account/userinfo`, "", {
            headers: {
              'Authorization': this.$session.get('jwstoken'),
            }
          })
    .then(res => {
      console.log(res)
      
    })
    .catch(err => {
      console.log(err)
    })
  },
  data() {
    return {
      User: {
        username: "",
        email: "",
        password: "",
        address: "",
        ispro: "",
        tel: "",
        addr1: "",
        addr2: "",
        addr3: "",
        addr4: "",
        addr5: "",
        addr6: "",
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

      axios.get(`${BASE_URL}:8090/fselect/${si_params.siCode}`, {
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
      axios.get(`${BASE_URL}:8090/fselect/sido/${gu_params.guCode}`, {
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
    checkform() {
        if (this.User.password !== this.curPassword) {
          this.isError = "비밀번호가 일치하지 않습니다."
          return
        }
        else this.isError = false

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

        if (this.changePasswordConfirm.length > 0 && this.changePasswordConfirm !== this.changePassword) {
          this.isError = "비밀번호 확인이 일치하지 않습니다."
          return
        }
        else this.isError = false

        if (this.changePassword === "") this.isChangedPW = false
        else this.isChangedPW = true

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

      if (!this.isChangedPW) changeUser.password = this.curPassword
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
  font-size: 2rem;
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
  background-color: #bcbdbc;
} 

.form-block-head {
  font-size: 16px;
  margin: 0;
  padding: 8px 0 0 0;
  /* text-align: right; */
  font-weight: bolder
}

.new-password {
  padding: 0;
}
.form-block {
  display: flex;
  margin-bottom: 25px;
  justify-content: space-around;
}

.btn-components {
  margin-top: 30px;
  float: right;
}

.form-address {
  padding: 0px;
}

.sub-address {
  margin-top: 10px;
}

</style>