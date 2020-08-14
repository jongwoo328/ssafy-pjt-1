<template>
  <div id="qna-create" class="container">
    <GoBack/>
    <h2>Q&A 작성</h2>
    <label for="qtitle" class="font-kor">제목</label>
    <input v-model="qtitle" type="text" name="qtitle" id="qtitle" class="font-kor">
    <label for="" class="font-kor">내용</label>
    <Editor/>
    <Button buttonText="작성" id="create" @click.native="postQna"/>
  </div>
</template>

<script>
import Editor from '@/components/common/Editor.vue'
import Button from '@/components/common/Button.vue'
import axios from 'axios'
import URL from '@/util/http-common.js'
import GoBack from '@/components/common/GoBack.vue'

export default {
    name: 'QnaCreate',
    components: {
        Editor,
        Button,
        GoBack
    },
    data() {
        return {
            qtitle: "",
        }
    },
    created() {
        if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }
        this.$emit('sidebar')
    },
    methods: {
        postQna() {
            const qnaData = {
                qtitle: this.qtitle,
                qcontent: document.getElementsByClassName('ql-editor')[0].innerHTML,
                userno: this.$store.getters.getUserData.userno
            }
            axios.post(`${URL.BASE_URL}/qna`, qnaData, URL.JSON_HEADER)
            .then(() => {
                this.$router.push({ name:'Qna' })
            })
            .catch(err => console.log(err))
        },
        
    }
}
</script>

<style>
    #qna-create {
        margin-top: 40px;
    }
    #qna-create label, #qna-create input, #qna-create textarea {
        display: block;
    }
    #qna-create input, #qna-create textarea {
        width: 100%;
    }
    #qna-create .ql-container {
        height: 300px;
    }

</style>