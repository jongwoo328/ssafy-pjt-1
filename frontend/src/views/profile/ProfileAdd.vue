<template>
  <div class="profile">
      <h3>Profile</h3>
      <img v-if="imageUrl" :src="imageUrl">
      <br>
      <label for="file">프로필 사진</label>
      <input ref="imageInput" type="file" id="file" accept="image/*" @change="onChangeImages">
      <br>
      <label for="description">소개</label>
      <textarea name ="description" id="description"></textarea>
      <button class="ml-auto" type="submit">등록</button>
  </div>
</template>

<script>
// import axios from 'axios'

export default {
    name: 'ProfileAdd',
    data() {
        return {
            imageUrl: null,
        }
    },
    methods: {
        onClickImageUpload() {
            this.$refs.imageInput.click();
        },
        onChangeImages(e) {
            console.log(e.target.files)
            const file = e.target.files[0];
            this.imageUrl = URL.createObjectURL(file);
        }
    },
}
</script>

<style scoped>
    .profile {
        margin: 30px 20px 30px 20px;
    }
    img {
        object-fit: cover;
        width: 150px;
        height: 150px;
    }
    textarea{
        width: 100%;
        height:  300px;
        box-shadow: 0 0 5px gray;
    }
    input {
        margin-bottom: 20px;
    }
</style>