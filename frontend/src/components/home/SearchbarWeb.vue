<template>
  <div id="join-web" class="font-kor container">
      <h1>검색하세요!</h1>
      <form action="" class="row search-form">
          <div class="col-12">
                <select v-model="categoryInfo" name="category" id="category" class="col-1 search-info">
                    <option value="none" selected disabled>분류</option>
                    <option v-for="category in categoryList" :key="category.cateno" :value="category_obj" v-text="category.cname" ></option>
                </select>
                <input type="text" class="col-7 search-info">
                <Button buttonText="검색" />
          </div>
          <div class="offset-3 col row mt-2">
            <select name="si" id="si" class="col-3 search-info" v-model="siInfo">
                <option value="none" disabled selected>시/도</option>
                <option v-for="si_obj in siList" :key="si_obj.siName" :value="si_obj" v-text="si_obj.siName"></option>
            </select>
            <select name="gu" id="gu" class="col-3 search-info" v-model="guInfo">
                <option value="none" disabled selected>구/군</option>
                 <option v-for="gu_obj in guList" :key="gu_obj.guName" :value="gu_obj" v-text="gu_obj.guName"></option>
            </select>
            <select name="dong" id="dong" class="col-3 search-info" v-model="dongInfo">
                <option value="none" disabled selected>동/읍/면</option>
                <option v-for="dong_obj in dongList" :key="dong_obj.dongName" :value="dong_obj" v-text="dong_obj.dongName"></option>
            </select>
          </div>
      </form>
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
            categoryInfo:"none",
            siInfo: "none",
            guInfo: "none",
            dongInfo: "none",
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
     },
 watch: {
        siInfo: function() {
        this.getGuInfo()
      },
         guInfo: function() {
         this.getDongInfo()
    }
  },methods:{
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
  },
}
</script>

<style>
    #join-web {
        display: none;
    }
    @media (min-width: 768px) {
        #join-web {
            display: inline-block;
            margin: 0 auto 30px 0;
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
            padding-left: 10px;
            border-style: none none solid none;
            /* margin: 10px 5px 10px 5px;
            border-radius: 7px;
            height: 50px; */
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
        
    }
</style>