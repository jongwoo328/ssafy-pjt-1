<template>
  <div id="profile" class="container">
      <h1>Profile</h1>
      <hr>
      <ProfileFrame v-if="profileFrame" />
      <br>
      <div class="description">
        <h2>소개</h2>
        <Button v-if="isProfile" type="submit" buttonText="수정" />
      </div>
      <hr>
      <div v-if="!isProfile">
        <p >본인을 소개해주세요.</p>
        <router-link to="/accounts/:username/create"><Button type="submit" buttonText="등록" @click="create"/></router-link>
      </div>
  </div>
</template>

<script>
import ProfileFrame from '@/components/common/ProfileFrame.vue'
import Button from '@/components/common/Button.vue'

export default {
    name: 'Profile',
    components: {
        ProfileFrame,
        Button,
    },
    data() {
        return {
            profileFrame: true,
            isProfile: false
        }
    },
}
</script>

<style scoped>
    #profile {
        padding-top: 50px;
    }
    Button {
        float: right;
    }
    .description {
        display: flex;
        justify-content: space-between;
    }
    .description Button {
        color: rgb(236,128,116);
        background-color: white;
    }
</style>