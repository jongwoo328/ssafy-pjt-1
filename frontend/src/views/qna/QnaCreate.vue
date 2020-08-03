<template>
  <div id="qna-create" class="container">
    <div class="back" @click="goBack">
        <i class="fas fa-arrow-left"></i>
    </div>
    <h2>Q&A 작성</h2>
    <label for="qtitle" class="font-kor">제목</label>
    <input type="text" name="qtitle" id="qtitle" class="font-kor">
    <label for="" class="font-kor">내용</label>
    <Editor/>
    <Button buttonText="작성" id="create"/>
  </div>
</template>

<script>
import Editor from '@/components/common/Editor.vue'
import Button from '@/components/common/Button.vue'

export default {
    name: 'QnaCreate',
    components: {
        Editor,
        Button,
    },
    data() {
        return {

        }
    },
    methods: {
        goBack() {
            this.$router.go(-1)
        }
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
    #qna-create .back {
        font-size: 2rem;
        display: inline-block;
    }
    #qna-create .back:hover {
        cursor: pointer;
        color: rgb(236,128,116);
    }
</style>