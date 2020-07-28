<template>
  <div class="profile container" id="profileAdd">
      <h3>Profile</h3>
      <hr>
      <div>
        <img v-if="profileImageUrl" :src="profileImageUrl">
        <br>
        <label for="file"><ProfileFrame v-if="profileframe" /></label>
        <br>
        <input ref="profileImage" type="file" id="file" accept="image/*" @change="fileSelect">
        <br>
        <label for="description">소개</label>
        <textarea name ="description" id="description" v-model="comment"></textarea>
      </div>
      <Button class="ml-auto" type="submit" button-text="등록" @click.native="submit" />
  </div>
</template>

<script>
import axios from 'axios'
import Button from '@/components/common/Button.vue'
import ProfileFrame from '@/components/common/ProfileFrame.vue'

export default {
    name: 'ProfileAdd',
    data() {
        return {
            profileImage: '',
            profileImageUrl: null,
            comment: null,
            profileframe: true,
        }
    },
    components: {
      Button,
      ProfileFrame
    },
    methods: {
        fileSelect() {
            console.log(this.$refs)
            this.profileImage = this.$refs.profileImage.files[0]
            this.profileImageUrl = URL.createObjectURL(this.profileImage)
            this.profileframe = false
        },
        submit() {
          const formData = new FormData()
          formData.append('profileImage', this.profileImage)
          formData.append('Comment', this.comment)
          formData.append('userno', this.$store.getters.getUserData.userno)

          for (let key of formData.entries())
          {
            console.log(`${key}`)
          }

          axios.post('http://192.168.100.88:8090/profile', formData, {
            headers: {
              'Authorization': this.$session.get('jwstoken'),
              'Content-Type' : 'multipart/form-data'
            }
          }).then(res => {
            console.log(res)
            this.$router.push({ name : 'Profile' })
          }).catch(err => {
            console.log(err)
          })
        }
    },
}
</script>

<style>
    #profileAdd {
        margin: 30px 20px 30px 20px;
    }
    #profileAdd h3 {
      font-size: 3rem;
    }
    #profileAdd img {
        object-fit: cover;
        margin-top: 20px;
        width: 150px;
        height: 150px;
        border-radius: 7px;
    }
    #profileAdd textarea{
        width: 100%;
        height: 270px;
        box-shadow: 0 0 5px gray;
        border-radius: 7px;
    }
    #profileAdd input {
        margin-bottom: 20px;
    }
    #profileAdd Button {
        float: right;
        margin: 15px 0 15px 0px;
    }
</style>