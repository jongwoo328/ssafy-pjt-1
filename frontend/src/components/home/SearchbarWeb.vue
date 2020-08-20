<template>
  <div id="join-web" class="font-kor container">
      <h1>검색하세요!</h1>
      <!-- <form class="row search-form"> -->
          <div class="col-12 search-category">
            <select v-model="categoryInfo" name="category" id="category" class="col-2 search-info">
                <option v-for="category in categoryList" :key="category.id" :value="category" v-text="category.cname" ></option>
            </select>
            <input type="text" class="col-5 col-md-7 search-info" @keyup.enter="SearchInfoemit" v-model="keyword">
            <Button buttonText="검색" @click.native="SearchInfoemit" />
          </div>
          <div class="searchaddr mt-3">
            <div class="checkAll">
                <label class="mr-1">전체</label>
                <input  type="checkbox" v-model="isAll" /> 
            </div>
            <div class="addr-input row mt-2">
                <select @change="getGuInfo" name="si" id="si" class="col-4 search-info si">
                    <option v-for="si_data in siList" :key="si_data" :value="getCode(si_data)" v-text="getName(si_data)"></option>
                </select>
                <select @change="getDongInfo" name="gu" id="gu" class="col-3 search-info gu">
                    <option v-for="gu_data in guList" :key="gu_data" :value="getCode(gu_data)" v-text="getName(gu_data)"></option>
                </select>
                <select name="dong" id="dong" class="col-2 search-info">
                    <option v-for="dong_data in dongList" :key="dong_data" :value="getCode(dong_data)" v-text="getName(dong_data)"></option>
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
            categoryInfo:[],
            keyword:"",
            isAll:false,
            createFlag: true
         }
    },
 created() {
    // 카테고리 정보 불러오기
    this.createFlag = true
    axios.get(`${HTTP.BASE_URL}/fselect/cate`,HTTP.JSON_HEADER) 
        .then(res => {
        for(let category in res.data){
            this.categoryList.push({
                cname : res.data[category]["cname"],
                cateno : res.data[category]["cateno"],
                id: Math.random()
            })
        }
        this.categoryInfo = this.categoryList[0]
    })
    .catch(err => {
        console.log(err)
    })
    
    this.setAddrDefault()
    if (!this.$store.getters.isLoggedIn) {
        this.isAll = true
    }
    this.createFlag = false
 },
 watch: {
    searchData() {
        this.$emit('searchdata')
    },
    isAll(){
        const addrInputs = document.querySelectorAll('.addr-input select')
        if(this.isAll){
            addrInputs.forEach(input => {
                input.disabled = true
            })
        }else{
            this.setAddrDefault()
            addrInputs.forEach(input => {
                input.disabled = false
            })
        }
    },
  },
  methods:{
    getCode(inputString) {
        const i = inputString.indexOf(' ')
        return inputString.slice(0, i)
    },
    getName(inputString) {
        const i = inputString.indexOf(' ')
        return inputString.slice(i+1)
    },
    setAddrDefault() {
        this.siList = []
        this.guList = []
        this.dongList = []
        // 시/도 정보 불러오기
        axios.get(`${HTTP.BASE_URL}/fselect`, HTTP.JSON_HEADER)
        .then(res => {
        for (let si_data in res.data) {
            this.siList.push(
                `${res.data[si_data]["sido_code"]} ${res.data[si_data]["sido_name"]}`
            )
        }

            // 구/군 정보 불러오기
            let sicode
            if (!this.$store.getters.isLoggedIn) {
                sicode = 11
            } else {
                sicode = this.$store.getters.getUserData.addr1
            }
            axios.get(`${HTTP.BASE_URL}/fselect/${sicode}`, HTTP.JSON_HEADER)
            .then(res => {
                for (let gu_data in res.data) {
                this.guList.push(
                    `${res.data[gu_data]["gugun_code"]} ${res.data[gu_data]["gugun_name"]}`
                )
                }
                // 동 정보 불러오기
                let gucode
                if (!this.$store.getters.isLoggedIn) {
                    gucode = 11110
                } else {
                    gucode = this.$store.getters.getUserData.addr3
                }
                axios.get(`${HTTP.BASE_URL}/fselect/sido/${gucode}`, HTTP.JSON_HEADER)
                .then(res => {
                    for (let dong_data in res.data) {
                    this.dongList.push(
                        `${res.data[dong_data]["code"]} ${res.data[dong_data]["dong"]}`
                        )
                    }

                    // 로그인 됐을경우 자동으로 설정되게
                    
                        const siOptions = document.querySelectorAll('#si option')
                        for (const i in siOptions) {
                            if (siOptions[i].innerText === this.$store.getters.getUserData.addr2) {
                                document.querySelector(`#si option:nth-child(${parseInt(i)+1})`).selected = true
                                break
                            }
                        }
                        const guOptions = document.querySelectorAll('#gu option')
                        for (const i in guOptions) {
                            if (guOptions[i].innerText === this.$store.getters.getUserData.addr4) {
                                document.querySelector(`#gu option:nth-child(${parseInt(i)+1})`).selected = true
                                break
                            }
                        }
                        setTimeout(() => {
                            const dongOptions = document.querySelectorAll('#dong option')
                            for (const i in dongOptions) {
                                if (dongOptions[i].innerText === this.$store.getters.getUserData.addr6) {
                                    document.querySelector(`#dong option:nth-child(${parseInt(i)+1})`).selected = true
                                    break
                                }
                            }
                        }, 10);
                    
                })
                .catch(err => {
                    console.log(err)
                })
            })
            .catch(err => {
                console.log(err)
            })
            
        })
        .catch(err => {
            console.log(err)
        })
    },
    getGuInfo() {
        if(!this.createFlag){
            const sicode = document.querySelector('#si').value
            this.guList = []
            this.dongList = []

            axios.get(`${HTTP.BASE_URL}/fselect/${sicode}`, HTTP.JSON_HEADER)
            .then(res => {
                for (let gu_data in res.data) {
                this.guList.push(
                    `${res.data[gu_data]["gugun_code"]} ${res.data[gu_data]["gugun_name"]}`
                    )
                }

            }).catch(err => {
                console.log(err)
            })
        }
    },
    getDongInfo() {
        if(!this.createFlag) {
            const dongcode = document.querySelector('#gu').value
            this.dongList = []
            axios.get(`${HTTP.BASE_URL}/fselect/sido/${dongcode}`, HTTP.JSON_HEADER)
            .then(res => {
                for (let dong_data in res.data) {
                this.dongList.push(
                    `${res.data[dong_data]["code"]} ${res.data[dong_data]["dong"]}`
                    )
                }
            }).catch(err => {
                console.log(err)
            })
        }
    },
    SearchInfoemit(){
        const searchData = {
            cateno: this.categoryInfo.cateno,
            saddr5: document.querySelector('#dong').value,
            keyword: this.keyword
        }
        if (this.isAll) {
            searchData.saddr5 = null
        }
        this.$emit("child",searchData)
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
        min-width: 145px;
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