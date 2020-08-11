<template>
    <div v-if="!urltype" class="profile container" id="profileAdd">
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
     <Editor/>
        <!-- <textarea name ="description" id="description" v-model="comment"></textarea> -->
      </div>
      <Button class="ml-auto" type="submit" button-text="등록" @click.native="submit" />
    </div>
    <div v-else class="profile container" id="profileAdd">
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
        <Editor/>
        <!-- <textarea name ="description" id="description" v-model="comment"></textarea> -->
      </div>
      <Button class="ml-auto" type="submit" button-text="수정" @click.native="submit" />
   </div> 
</template>

<script>
import axios from 'axios'
import Button from '@/components/common/Button.vue'
import ProfileFrame from '@/components/common/ProfileFrame.vue'
import HTTP from "@/util/http-common.js"
import Editor from '@/components/common/Editor.vue'
export default {
    name: 'ProfileAdd',
    data() {
        return {
            profileImage: '',
            profileImageUrl: null,
            comment: null,
            profileframe: true,
            urltype: "",
        }
    },
    components: {
      Editor,
      Button,
      ProfileFrame
    },
    created() {
      this.$emit('sidebar')
      if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }
        if (this.$route.params.type === "update") this.urltype = true
        else this.urltype = false

        if (this.urltype) {
          console.log('test')
          axios.get(`${HTTP.BASE_URL}/profile/${this.$store.getters.getUserData.name}`)
          .then(res => {
              this.profileframe = false
              console.log(res)  
              this.profileImageUrl = `${HTTP.BASE_URL}/` + res.data.imgurl
              const fsda = document.querySelector('.ql-editor')
              fsda.innerHTML = res.data.comment
              this.comment = res.data.comment.innerHTML
              console.log(this.profileData.imgUrl)
              console.log(this.profileData.comment)
          })
          .catch(err => {
              console.log(err)
          })
              }
      },
    methods: {
        fileSelect() {
            console.log(this.$refs)
            this.profileImage = this.$refs.profileImage.files[0]
            this.profileImageUrl = URL.createObjectURL(this.profileImage)
            this.profileframe = false
        },
        submit() {
          const temp = document.getElementsByClassName('ql-editor')[0]
          this.comment = temp.innerHTML
          console.log(temp.innerHTML)
          console.log(this.comment)
          const formData = new FormData()
          formData.append('profileImage', this.profileImage)
          formData.append('Comment', this.comment)
          formData.append('userno', this.$store.getters.getUserData.userno)

          for (let key of formData.entries())
          {
            console.log(`${key}`)
          }

          if (this.urltype) {
            axios.put(`${HTTP.BASE_URL}/profile`, formData, {
            headers: {
              'Authorization': this.$session.get('jwstoken'),
              'Content-Type' : 'multipart/form-data'
            }
          }).then(res => {
            console.log(res)
            
            setTimeout(() => {
              alert('수정되었습니다.')
              this.$router.push({ name : 'Profile' })
            },1000)
          }).catch(err => {
            console.log(err)
          })
          }

          else {
            axios.post(`${HTTP.BASE_URL}/profile`, formData, {
            headers: {
              'Authorization': this.$session.get('jwstoken'),
              'Content-Type' : 'multipart/form-data'
              }
            }).then(res => {
              console.log(res)
            setTimeout(() => {
              alert('등록되었습니다.')
              this.$router.push({ name : 'Profile' })
            },1000)
            }).catch(err => {
              console.log(err)
            })
          } 
        }
    },
}
</script>

<style>
    #profileAdd {
        margin-top: 50px;
    }
    #profileAdd h3 {
      font-size: 2rem;
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