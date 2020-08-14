<template>
    <transition name="modal">
       <div id="PayModal">
        <div class="modal-mask">
        <div class="modal-wrapper">
            <div class="modal-container">

            <div class="modal-header"> 
                <h3>Payment</h3>
                <button type="button" class="close" data-dismiss="modal" @click="modalclose">×</button>
            </div>
            <div class="modal-body">
                <slot name="body">
                <div>
                  <p><span>서비스명 : </span><span>{{service.servname}}</span></p>
                  <p><span>결제금액 : </span><span>{{service.price}}</span></p>
                </div>
                
                </slot>
                  <div class="text-right">
                    <input v-model="isTerm" type="checkbox" />
                    <span>약관을 동의합니다.</span>
                    <RefundModal v-if="refundmodal" @close="refundpopup" />
                    <p class="mb-0"><span class="showpopup" @click="refundpopup">취소 및 환불 규정 보기</span></p>
                  </div>
            </div>
            <div class="modal-footer">
                <slot name="footer">
                  <Button class="btn_1" type="submit" data-dismiss="modal" button-text="취소" buttonColor="transparent" @click.native="modalclose"/>
                  <Button class="btn_1" type="submit" button-text="결제" @click.native="payed"/>
                </slot>
            </div>
            </div>
        </div>
        </div>
       </div>
    </transition>
</template>

<script>
import Button from '@/components/common/Button.vue'
import HTTP from "@/util/http-common.js"
import axios from 'axios'
import RefundModal from '@/components/modal/RefundModal'

export default {
    components:{
        Button,
        RefundModal,
    },
    data(){
        return {
            service:[],
            payData:[],
            refundmodal: false,
            isTerm: false,
        }
    },
    props:{
        servicedataModal: Object
    },
    created(){
       this.service = this.servicedataModal
    },
    methods:{
      refundpopup() {
        this.refundmodal = !this.refundmodal
      },
      messagesend(){
        let messageData={
              writername: this.$store.getters.getUserData.name,
              recivername: this.service.username,
              title: "서비스 알림",
              content: this.$store.getters.getUserData.name+"님이 "+ '"'+this.service.servname+'"'+"을 신청했습니다.",
        }
        axios.post(`${HTTP.BASE_URL}/msg`, messageData)
      .then(() => {

        alert('결제에 성공했습니다.')  
          setTimeout(()=>{
          this.modalclose()
          
        },1)

      })
      .catch(err => {
        alert(err)
      })
    },

      payed(){
        if (!this.isTerm) {
          alert('약관에 동의하여야 합니다.')
        }
        else {          
          this.payData={
            userno : `${this.$store.getters.getUserData.userno}`,
            servno : `${this.$route.params.service_id}`
          }
          axios.post(`${HTTP.BASE_URL}/pay`,this.payData)
          .then(res=>{
            if(res.data=="payed"){
              alert('이미 결제 되었습니다.')
            }
            this.messagesend()
            
          })
          .catch(err=>{
            console.log(err)
          })
        }
      },  
      modalclose () {
        this.$emit('close')
      }
    }
}
</script>
<style scoped>
  #PayModal .modal-header h3,
  .modal-header button{
    color: white;
  }
  .modal-footer {
    position: absolute;
    width: 100%;
    height: 40px;
    padding: 0;
    bottom: 0;
  }
  #PayModal hr {
    margin-top: 0;
  }
  span{
    font-size : 14px;
    text-align : right;
    font-weight : bolder
  }
    /* model */
  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }
  .showpopup:hover {
    color: red;
    cursor: pointer;
  }
  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  .modal-container {
    width: 350px;
    height: 300px;
    margin: 0px auto;
    padding: 0;
    position: relative;
    /* border-radius: 2px; */
    background-color: #343a40;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  .modal-header h3 {
    margin-top: 0;
  }

  .modal-body {
    color: white;
    height: 200px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  .modal-footer {
    display: flex;
    flex-direction: row;
    justify-content: space-between !important;
    align-items: center;
  }
  .modal-footer button {
    border-radius: 0;
    margin: 0;
    width: 50%;
  }
  .modal-default-button {
    float: right;
  }
  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
  }

</style>