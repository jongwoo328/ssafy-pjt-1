<template>
  <div>
      <button @click="changeModal">작성</button>
      <messageModal v-if="messagemodal" @close="changeModal"/>
  </div>
</template>

<script>
import messageModal from '@/components/modal/MessageModal.vue'

export default {
    name: 'MessageList',
    data() {
        return {
            messagemodal: false
        }
    },
    components: {
        messageModal
    },
    methods: {
        changeModal() {
            this.messagemodal = !this.messagemodal
        }
    },
}
</script>

<style>

</style>