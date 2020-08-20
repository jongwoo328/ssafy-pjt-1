<template>
  <div id="see-more" @click="seeMore" v-if="displayFlag">
      <hr>
      <p>더 보기</p>
  </div>
</template>

<script>
import axios from 'axios'
import URL from '@/util/http-common.js'

export default {
    name: 'SeeMore',
    data() {
        return {
            page: 2,
            displayFlag: true,
            reset: this.seeMoreFlag
        }
    },
    props: {
        state: String,
        searchData: Object,
        seeMoreFlag: Boolean,
        seeMoreKeyword: String,
        seeMoreReset: Boolean
    },
    computed: {
        cateno() {
            const cate = document.querySelector('#category')
            if (cate) {
                return cate.selectedIndex
            } else {
                return 0
            }
        }
    },
    watch: {
        seeMoreFlag() {
            if (this.seeMoreFlag) {
                this.page = 2
                this.displayFlag = true
            }
        },
        seeMoreReset() {
            this.page = 2
            this.displayFlag = true
        }
    },
    methods: {
        seeMore() {
            this.displayFlag = true
            if (this.state === 'main') {
                axios.get(`${URL.BASE_URL}/service/main/${this.page++}`)
                .then(res => {
                    this.$emit('seemore', res.data)
                    if (res.data.length !== 6) {
                        this.displayFlag = false
                    }
                    
                })
                .catch(err => {
                    console.log(err)
                })
            } else if (this.state === 'search') {
                let requestData
                    requestData = {
                        cateno: this.cateno,
                        keyword: this.seeMoreKeyword,
                        saddr6: null
                    }
                requestData['num'] = this.page++
                axios.post(`${URL.BASE_URL}/service/search`, requestData)
                .then(res => {
                    this.$emit('seemore', res.data)
                    if (res.data.length !== 6) {
                        this.displayFlag = false
                        this.page = 2
                    }
                    
                })
                .catch(err => {
                    console.log(err)
                })
            }
            this.$store.dispatch('setSidebar')
        }
    }
}
</script>

<style>
#see-more {
    position: relative;
    display: flex;
    padding: 0 30px;
    border-radius: 5px;
    border-color: gray;
}
#see-more hr {
    height: 2px;
    width: 100%;
    border-color: inherit;
}
#see-more p {
    position: absolute;
    border-radius: 5px;
    margin: 0;
    padding: 0 20px;
    background-color: white;
    top: 25%;
    left: 50%;
    transform: translateX(-50%);
}
#see-more:hover {
    cursor: pointer;
    color: rgb(236,128,116);
    border-color: rgb(236,128,116);
}
</style>