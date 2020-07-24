<template>
  <div class="profile">
      <h3>Profile</h3>
      <div>
        <img v-if="profileImageUrl" :src="profileImageUrl">
        <br>
        <label for="file">프로필 사진(150*150 px)</label>
        <input ref="profileImage" type="file" id="file" accept="image/*" @change="fileSelect">
        <br>
        <label for="description">소개</label>
        <textarea name ="description" id="description" v-model="description"></textarea>
      </div>
      <Button class="ml-auto" type="submit" button-text="등록" @click.native="submit" />
  </div>
</template>

<script>
import axios from 'axios'
import Button from '@/components/common/Button.vue'

export default {
    name: 'ProfileAdd',
    data() {
        return {
            profileImage: '',
            profileImageUrl: null,
            description: null,
        }
    },
    components: {
      Button,
    },
    methods: {
        fileSelect() {
            console.log(this.$refs)
            this.profileImage = this.$refs.profileImage.files[0]
            this.profileImageUrl = URL.createObjectURL(this.profileImage)
        },
        submit() {
          const formData = new FormData()
          formData.append('profileImage', this.profileImage)
          formData.append('profileImageUrl', this.profileImageUrl)
          formData.append('Description', this.description)

          for (let key of formData.entries())
          {
            console.log(`${key}`)
          }

          axios.post('http://localhost:8080/profile/add', formData, {
            headers: {
              'Content-Type' : 'multipart/form-data'
            }
          }).then(res => {
            console.log(res)
          }).catch(err => {
            console.log(err)
          })
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
        height: 270px;
        box-shadow: 0 0 5px gray;
    }
    input {
        margin-bottom: 20px;
    }
    Button {
        float: right;
        margin: 15px 0 15px 0;
    }
</style>