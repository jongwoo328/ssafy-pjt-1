<template>
  <div class="notice-icon" @mouseover="mouseover" @mouseout="mouseout">
      <div v-if="hasMsg" class="notice-count"><p v-text="unreadCount" ></p></div>
      <i class="far fa-bell"></i>
  </div>
</template>

<script>
export default {
    name: 'NoticeIcon',
    // mounted or created일 떄 요청보내고,
    // 안읽은 개수 만큼 unread에 저장
    computed: {
        unreadCount() {
            return this.$store.getters.getUserData.msgcount
        },
        bell() {
            return document.querySelector('div.notice-icon > i')
        },
        hasMsg() {
            return this.$store.getters.getUserData.msgcount > 0
        }
    },
    methods: {
        mouseover() {
            this.bell.setAttribute('class', 'fas fa-bell')
        },
        mouseout() {
            this.bell.setAttribute('class', 'far fa-bell')
        }
    }
}
</script>

<style>
    .notice-icon {
        position: relative;
        margin-left: 10px;
        /* margin-right: 15px; */
        font-size: 1.1rem;
        color: black;
        transition: color 0.5s ease;
    }
    .notice-icon:hover {
        cursor: pointer;
        color: #fab005;
    }
    .notice-count {
        position: absolute;
        top: 3px;
        right: 5px;
        width: 9px;
        height: 9px;
        border-radius: 9px;
        background-color: red;
    }
    .notice-count p {
        display: none;
    }
    @media (min-width: 768px) {
        .notice-icon {
            margin-left: 20px;
            margin-right: 20px;
        }
        .notice-icon i {
            font-size: 2rem;
        }
        .notice-count {
            width: 15px;
            height: 15px;
            top: 0;
            right: 2px;
            border-radius: 15px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .notice-count p {
            display: block;
            margin-bottom: 4px;
            padding: 0;
            text-align: center;
            width: 15px;
            height: 15px;
            font-size: 12px;
            font-weight: bold;
            color: white;
        }
    }
</style>