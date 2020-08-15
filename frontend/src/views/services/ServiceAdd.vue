<template>
    <div id="servicecreate" class="container">
        <GoBack/>
        <h3>서비스 등록</h3>
        <hr>
            <div class="form-block">
                <label class="font-kor" for="category">분류</label>
                <select  class="form-control " id="category" name ="category"  v-model="categoryInfo">
                    <option v-if="categoryInfo" :value="categoryInfo" v-text="categoryInfo.cname"></option>
                    <option v-for="category in categoryList" :key="category.cateno" :value="category" v-text="category.cname" ></option>
                </select>    
            </div>
            <div class="form-block">
                <label class="font-kor"  for="servicename"> 
                    제목
                </label>
                <input class="input-text" v-model="servname" id="servicename"/>
            </div>
            <div class="form-block">
                <label class="font-kor"  for="serviceprice"> 
                가격 
                </label>
                <input class="input-text" type="number" v-model="price" id="serviceprice"/>
            </div>
                <p><span v-text="korNumFormat"></span>원</p>
            <div class="form-address">
                <label class="font-kor label-address" >주소</label>
                <div class="form-block container-fluid row">        
                    <select class="form-control col-12 col-md-4" id="exampleFormControlSelect1" v-model="siInfo" >
                        <option v-if="siInfo" :value="siInfo" v-text="siInfo.siName"></option>
                        <option v-else value="" disabled selected>시/도</option>
                        <option v-for="si_obj in siList" :key="si_obj.siName" :value="si_obj" v-text="si_obj.siName"></option>
                    </select>
                    <div class="form-block sub-address col-12 col-md-8 row">
                        <select class="form-control col-6 col-md-4" id="exampleFormControlSelect2" v-model="guInfo">
                            <option v-if="guInfo" :value="guInfo" v-text="guInfo.guName"></option>
                            <option v-else value="" disabled selected>구/군</option>
                            <option v-for="gu_obj in guList" :key="gu_obj.guName" :value="gu_obj" v-text="gu_obj.guName"></option>
                        </select>
                        <select class="form-control col-6 col-md-4" id="exampleFormControlSelect3" v-model="dongInfo">
                            <option v-if="dongInfo" :value="dongInfo" v-text="dongInfo.dongName"></option>
                            <option v-else value="" disabled selected>동/읍/면</option>
                            <option v-for="dong_obj in dongList" :key="dong_obj.dongName" :value="dong_obj" v-text="dong_obj.dongName"></option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-block form-img">
                <label class="font-kor" >
                    이미지
                </label>
                <img v-if="serviceImageUrl" :src="serviceImageUrl">
                <input ref="serviceImage" type="file" id="file" accept="image/*" @change="fileSelect">
            </div>
            <div class="form-block form-content">
                <label class="font-kor" for="servicedescription"> 
                내용 
                </label>
                <Editor/>
            </div>
            <div>

            </div>
        <Button class="btn_1" type="submit" button-text="등록" @click.native="submit()"/>
    </div>
</template>
<script>
import HTTP from "@/util/http-common.js"
import axios from 'axios'
import Button from '@/components/common/Button.vue'
import Editor from '@/components/common/Editor.vue'
import Common from '@/util/common.js'
import GoBack from '@/components/common/GoBack.vue'

export default {
    name : 'ServiceAdd',
    components : {
        Button,
        Editor,
        GoBack
    },
    computed: {
        korNumFormat() {
            return Common.toKorNumberFormat(this.price)
        }
    },
    data(){
        return {
            categoryList: [],
            siList: [

            ],
            guList: [],
            dongList: [],
            categoryInfo:{
                cateno : "",
                cname : "분류"
            },
            siInfo: {
                siName: "시/도",
                siCode: ""
            },
            guInfo: {
                guName: "구/군",
                 guCode: "",
            },
            dongInfo: {
                dongName: "동",
                dongCode: "",
            },
            serviceImage:"",
            serviceImageUrl: "",
            cateno: "",
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
    },
  },
     created() {
         if (!this.$store.getters.isPro) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 403
                }
            })
        }
         this.$emit('sidebar')
         axios.get(`${HTTP.BASE_URL}/fselect/cate`,HTTP.JSON_HEADER) 
          .then(res => {
            for(let category in res.data){
                if(category==0){
                    continue;
                }
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
             for (let si_data in res.data) {
                 this.siList.push({
            "siCode": res.data[si_data]["sido_code"],
            "siName": res.data[si_data]["sido_name"]
            })
        }
      })
      .catch(err => {
        console.log(err)
      })
     },methods:{
         fileSelect() {
            this.serviceImage = this.$refs.serviceImage.files[0]
            this.serviceImageUrl = URL.createObjectURL(this.serviceImage)
        },
         submit(){
             
             const formData = new FormData()
             const temp = document.getElementsByClassName('ql-editor')[0]
             this.description = temp.innerHTML
            formData.append('serviceImage', this.serviceImage)
            formData.append('cateno', this.categoryInfo.cateno)
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

            axios.post(`${HTTP.BASE_URL}/service`,formData) 
            .then(() => {
              setTimeout(() => {
              alert('등록되었습니다.')
              this.$router.push("/myservice")
            },100)
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
        }).catch(err => {
          console.log(err)
        })
      }
     }
}
</script>
<style>
    #servicecreate label {
        font-size: 1.25rem;
    }
    #servicecreate {
        margin-top: 50px;
        padding-bottom: 100px;
    }
    #servicecreate h3 {
        font-size: 2rem;
    }
    #servicecreate .form-block,
    #servicecreate .form-group {
        display: block;
        margin: 20px 0;
    }
    #servicecreate .form-block {
        display: flex;
        flex-direction: row;
    }
    #servicecreate .form-block label {
        min-width: 50px;
    }
    #servicecreate #category {
        min-width: 100px;
        width: 300px;
    }
    #servicecreate input,
    #servicecreate select {
        border-radius: 0;
        width: 100%;
        border: 1px solid black;
        border-style: none none solid none;
    }
    #servicecreate .form-address .form-block {
        margin: 0;
    }
    #servicecreate .form-address .label-address{
        padding: 0;
        display: block;
    }
    #servicecreate .sub-address {
        padding: 0;
    }
    #servicecreate .form-img,
    #servicecreate .form-img label,
    #servicecreate .form-content{
        display: block;
    }
    #servicecreate .form-img input {
        margin-top: 5px;
        border: none;
    }
    #servicecreate .form-img img {
        max-width: 100px;
        max-height: 100px;
    }
    #servicecreate .ql-editor {
        min-height: 200px;
    }
    #servicecreate Button {
        float: right;
    }
    @media (min-width: 768px) {
        #servicecreate .form-address {
            display: flex;
            flex-direction: row;
        }
        #servicecreate .form-address label {
            min-width: 50px;
            display: inline-block;
        }
    }
</style>
