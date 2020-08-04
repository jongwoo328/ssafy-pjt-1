<template>
    <div id="servicecreate" class="container">
        <h3>서비스 등록</h3>
        <hr>
            <div class=" form-group d-flex justify-content-around">
                <label class="font-kor" >분류</label>

                    <select  class="form-control col-6" name ="category"  v-model="categoryInfo">
                        <option value="" disabled selected>분류</option>
                        <option v-for="category in categoryList" :key="category.cateno" :value="cateno" v-text="category.cname" ></option>
                    </select>
                
            </div>
            <div class="form-block">
                <label class="font-kor"  for="servicename"> 
                    서비스 이름 
                </label>
                <input class="input-text" v-model="servname" id="servicename"/>
            </div>
            <div class="form-block">
                <label class="font-kor"  for="serviceprice"> 
                가격 
                </label>
                <input class="input-text" type="number" v-model="price" id="serviceprice"/>
            </div>
            <div form-group d-flex justify-content-around>
                <label class="font-kor" >
                주소
                </label>
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
            <div>
                <img v-if="serviceImageUrl" :src="serviceImageUrl">
                <br>
                <label class="font-kor" >
                </label>
                <br>
                <input ref="serviceImage" type="file" id="file" accept="image/*" @change="fileSelect">
                <br>
            </div>
            <div class="form-block">
                <label class="font-kor" for="servicedescription"> 
                서비스 내용 
                </label>
                <br>
                <Editor/>
            </div>
            <div>

            </div>
    </div>
        <Button class="btn_1" type="submit" button-text="등록" @click.native="submit()"/>
    </div>
</template>
<script>
import HTTP from "@/util/http-common.js"
import axios from 'axios'
import Button from '@/components/common/Button.vue'
import Editor from '@/components/common/Editor.vue'

export default {
    name : 'ServiceAdd',
    components : {
        Button,
        Editor
    },
    data(){
        return {
            categoryList: [],
            siList: [

            ],
            guList: [],
            dongList: [],
            categoryInfo:"",
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
            serviceImage:"",
            serviceImageUrl: "",
            cateno: "1",
            servname:"",
            price:"",
            description:"",
            saddr1:"",
            saddr2:"",
            saddr3:"",
            saddr4:"",
            saddr5:"",
            saddr6:"",
            imgurl:"",
        }

    }, watch: {
        siInfo: function() {
        this.getGuInfo()
      },
         guInfo: function() {
         this.getDongInfo()
    }
  },
     created() {
         axios.get(`${HTTP.BASE_URL}/fselect/cate`,HTTP.JSON_HEADER) 
          .then(res => {
            for(let category in res.data){
                this.categoryList.push({
                    "cname" : res.data[category]["cname"],
                    "cateno" : res.data[category]["cateno"]
                })
            }
        })
        .catch(err => {
            console.log(err)
        }),
        axios.get(`${HTTP.BASE_URL}/fselect`, HTTP.JSON_HEADER)
             .then(res => {
                 console.log(res);
             for (let si_data in res.data) {
                 this.siList.push({
            "siCode": res.data[si_data]["sido_code"],
            "siName": res.data[si_data]["sido_name"]
            })
        }
            console.log(this.siList)
      })
      .catch(err => {
        console.log(err)
      })
     },methods:{
         fileSelect() {
            console.log(this.$refs)
            this.serviceImage = this.$refs.serviceImage.files[0]
            this.serviceImageUrl = URL.createObjectURL(this.serviceImage)
            console.log(this.serviceImageUrl)
        },
         submit(){
             
             const formData = new FormData()
             const temp = document.getElementsByClassName('ql-editor')[0]
             this.description = temp.innerHTML
            formData.append('serviceImage', this.serviceImage)
            formData.append('cateno', this.cateno)
            formData.append('userno', this.$store.getters.getUserData.userno)
            formData.append('servname', this.servname)
            formData.append('price', this.price)
            formData.append('description', this.description)
            formData.append('saddr1', this.siInfo.siCode)
            formData.append('saddr2', this.siInfo.siName)
            formData.append('saddr3', this.guInfo.guCode)
            formData.append('saddr4', this.guInfo.guName)
            formData.append('saddr5', this.dongInfo.dongCode)
            formData.append('saddr6', this.dongInfo.dongName)
             for (let key of formData.entries())
            {
            console.log(`${key}`)
            }
            axios.post(`${HTTP.BASE_URL}/service`,formData) 
                .then(res => {
                 console.log(res);
                 this.$router.push("/myservice")
      })
      .catch(err => {
        console.log(err)
      })
         },
         getGuInfo() {
                let si_params = this.siInfo
                this.guList = []
                this.dongList = []
                this.guInfo = ""
                this.dongInfo = ""

        axios.get(`${HTTP.BASE_URL}/fselect/${si_params.siCode}`, HTTP.JSON_HEADER)
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

        axios.get(`${HTTP.BASE_URL}/fselect/sido/${gu_params.guCode}`, HTTP.JSON_HEADER)
        .then(res => {
      

          for (let dong_data in res.data) {
            this.dongList.push({
              "dongCode": res.data[dong_data]["code"],
              "dongName": res.data[dong_data]["dong"]
              })
          }
          console.log(this.dongList)
        }).catch(err => {
          console.log(err)
        })
      }
     }
}
</script>
<style scoped>
    .input-text {
        width: 90%;
        height: 40px;
        border: 0.8px;
        padding-left: 10px;
        border-style: none none solid none;
    }
    .form-block {
        display: flex;
        margin-bottom: 25px;
        justify-content: space-around;
    }
    Button {
        margin-left : 30px;
        margin-top : 30px;

    }
    #servicecreate label,textarea{
        display: block;
    }
    #servicecreate{
        margin-top: 40px;
    }
</style>
