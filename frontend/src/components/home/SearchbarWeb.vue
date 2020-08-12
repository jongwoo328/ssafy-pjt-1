<template>
  <div id="join-web" class="font-kor container">
      <h1>검색하세요!</h1>
      <!-- <form class="row search-form"> -->
          <div class="col-12 search-category">
            <select v-model="categoryInfo" name="category" id="category" class="col-1 search-info">
                <option v-for="category in categoryList" :key="category.cname" :value="category" v-text="category.cname" ></option>
            </select>
            <input type="text" class="col-5 col-md-7 search-info" v-model="keyword">
            <Button buttonText="검색" @click.native="SearchInfoemit" />
          </div>
          <div class="searchaddr mt-3">
            <div class="checkAll" v-if="isLogin">
                <label class="mr-1">전체</label>
                <input  type="checkbox" v-model="isAll" /> 
            </div>
            <div class="addr-input row mt-2">
                <select name="si" id="si" class="col-4 search-info si" v-model="siInfo">
                    <option v-if="siInfo" :value="siInfo" v-text="siInfo.siName"></option>
                    <option v-else value="" disabled selected>시/도</option>
                    <option v-for="si_obj in siList" :key="si_obj.siName" :value="si_obj" v-text="si_obj.siName"></option>
                </select>
                <select name="gu" id="gu" class="col-3 search-info gu" v-model="guInfo">
                    <option v-if="guInfo" :value="guInfo" v-text="guInfo.guName"></option>
                        <option v-else value="" disabled selected>구/군</option>
                    <option v-for="gu_obj in guList" :key="gu_obj.guName" :value="gu_obj" v-text="gu_obj.guName"></option>
                </select>
                <select name="dong" id="dong" class="col-2 search-info" v-model="dongInfo">
                    <option v-if="dongInfo" :value="dongInfo" v-text="dongInfo.dongName"></option>
                        <option v-else value="" disabled selected>동/읍/면</option>
                    <option v-for="dong_obj in dongList" :key="dong_obj.dongName" :value="dong_obj" v-text="dong_obj.dongName"></option>
                </select>
            </div>
          </div>
      <!-- </form> -->
  </div>
</template>

<script>
import Button from '@/components/common/Button.vue'
import HTTP from "@/util/http-common.js"
import axios from 'axios'

export default {
    name: 'SearchbarWeb',
    components: {
        Button,
    },
    data(){
         return {
            categoryList: [],
            siList: [],
            guList: [],
            dongList: [],
            categoryInfo:[ 
                
            ],
            siInfo: [],
            guInfo: [],
            dongInfo: [],
            search:{},
            keyword:"",
            isLogin:false,
            isAll:false
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
            this.categoryInfo = this.categoryList[0]
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
    if(!this.$store.getters.isLoggedIn){
        this.siInfo.siName ="시/도",
        this.guInfo.guName = "구/군",
        this.dongInfo.dongName="동"
        this.isLogin=false
    }else{
    this.isLogin =true
    this.siInfo.siName =  this.$store.getters.getUserData.addr2,
    this.siInfo.siCode =  this.$store.getters.getUserData.addr1,
    this.guInfo.guName =  this.$store.getters.getUserData.addr4,
    this.guInfo.guCode =  this.$store.getters.getUserData.addr3,
    this.dongInfo.dongName =  this.$store.getters.getUserData.addr6,
    this.dongInfo.dongCode =  this.$store.getters.getUserData.addr5
    
    }
     axios.get(`${HTTP.BASE_URL}/fselect/${this.siInfo.siCode}`, HTTP.JSON_HEADER)
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
       axios.get(`${HTTP.BASE_URL}/fselect/sido/${this.guInfo.guCode}`, HTTP.JSON_HEADER)
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
 },
 computed: {
    userData() {
        return this.$store.getters.getUserData
    },
    searchData() {
        return {
            cateno: this.categoryInfo.cateno,
            saddr5: this.dongInfo.dongCode,
            keyword: this.keyword
        }
    }
 },
 watch: {
     searchData() {
         this.$emit('searchdata')
     },
    isAll : function(){
        if(this.isAll){
            this.siInfo.siName ="시/도",
            this.siInfo.siCode=null
            this.guInfo.guName = "구/군",
            this.guInfo.guCode=null
            this.dongInfo.dongName="동"
            this.dongInfo.dongCode=null
            this.gulist=null
            this.dongList=null
        }else{
            this.siInfo.siName =  this.$store.getters.getUserData.addr2,
            this.siInfo.siCode =  this.$store.getters.getUserData.addr1,
            this.guInfo.guName =  this.$store.getters.getUserData.addr4,
            this.guInfo.guCode =  this.$store.getters.getUserData.addr3,
            this.dongInfo.dongName =  this.$store.getters.getUserData.addr6,
            this.dongInfo.dongCode =  this.$store.getters.getUserData.addr5
        
        }
    },
    userData : function(){
        this.$router.go()
    },
    siInfo: function() {
        this.isAll=false;
        this.getGuInfo()
    },
    guInfo: function() {
        this.getDongInfo()
    }
  },
  methods:{
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
      },
    SearchInfoemit(){
        this.search.cateno = this.categoryInfo.cateno,
        this.search.saddr5 = this.dongInfo.dongCode,
        this.search.keyword = this.keyword
        console.log('search on web')
        console.log(this.search)
        this.$emit("child",this.search)
    }
  },
}
</script>

<style>
    .searchaddr {
        display: flex;
        flex-direction: column-reverse;
        align-items: flex-end;
    }
    .si, .gu {
        min-width: 145px;
    }
    .search-category {
        width: 100%;
    }
    .checkAll input{
        margin: 10px 20px 0 0 ;
    }
    .addr-input {
        min-width: 280px;
    }
    #join-web {
        display: none;
        margin: 0 0 30px 0;
        padding-right: 0;
    }
    #join-web h1 {
        display: inline-block;
        padding-top: 30px;
    }
    .search-info {
        width: 100%;
        height: 40px;
        border: 0.8px;
        border-radius: 0;
        border-style: none none solid none;
    }
    #join-web button {
        margin-left: 20px;
        padding: 5px 10px;
    }
    #category {
        min-width: 84px;
    }
    #join-web .search-form {
        width: 100%;
    }
    #dong {
        min-width: 125px;
    }
    @media (min-width: 768px) {
        #join-web {
            display: block;
        }
        .searchaddr {
            display: flex;
            flex-direction: row-reverse;
            align-items: center;
        }
        .addr-input {
            display: inline-block;
            margin-right: 5%;
        }
    }
</style>