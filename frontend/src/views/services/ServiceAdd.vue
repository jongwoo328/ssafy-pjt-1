<template>
    <div class="container">
        <h3>서비스 등록</h3>
        <hr>
        <div>
            <label>분류</label>
            <select name ="category">
                <option value="" v-model:>

        </div>
    </div>
</template>
<script>
import URL from "@/util/http-common.js"
import Axios from 'axios'

export default {
    name : 'ServiceAdd',
    components : {

    },
    data(){
        return {
            Category:[
                {
                    cateno:"",
                    cname:""
                }
            ],

        }

    },
     created() {
         axios.get('${URL.BASE_URL}/fselect/cate') 
          .then(res => {
            console.log(res)
            this.Category = {
                cateno:  res.data.cateno,
                cname: res.data.cname
            }
            console.log(this.Category.cateno)
            console.log(this.Category.cname)
           
        })
        .catch(err => {
            console.log(err)
        })
     }
}
</script>