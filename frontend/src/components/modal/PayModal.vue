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
                <p><span>서비스명 : </span><span>{{service.servname}}</span></p>
                <p><span>결제금액 : </span><span>{{service.price}}</span></p>
                </slot>
            </div>
            <div class="modal-footer">
                <slot name="footer">
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

export default {
    components:{
        Button
    },
    data(){
        return {
            service:[],
            payData:[]
        }
    },
    props:{
        servicedataModal: Object
    },
    created(){
       this.service = this.servicedataModal
       console.log(this.service) 
    },
    methods:{
      payed(){
        this.payData={
          'userno' : `${this.$store.getters.getUserData.userno}`,
          'servno' : `${this.$route.params.service_id}`
        }
        console.log(this.payData)
        axios.post(`${HTTP.BASE_URL}/pay`,this.payData)
        .then(res=>{
          console.log(res)
          
        })
        .catch(err=>{
          console.log(err)
        })
      },
      modalclose () {
      this.$emit('close')
    }
        // axios.post()    
    }

}
</script>

<style scoped>
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

  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }

  .modal-container {
    width: 300px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  .modal-header h3 {
    margin-top: 0;
    color: #42b983;
  }

  .modal-body {
    margin: 20px 0;
  }

  .modal-default-button {
    float: right;
  }

  /*
  * The following styles are auto-applied to elements with
  * transition="modal" when their visibility is toggled
  * by Vue.js.
  *
  * You can easily play with the modal transition by editing
  * these styles.
  */

  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
  }

  .modal-enter .modal-container,
  .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }
</style>
