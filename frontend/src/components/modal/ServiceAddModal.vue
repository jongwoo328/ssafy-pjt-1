<template>
    <transition name="modal">
        <div class="modal-mask">
        <div class="modal-wrapper">
            <div class="modal-container">
            <div class="modal-header">
                <slot name="header">
                    <h3>서비스 등록</h3>
                </slot>
            </div>

            <div class="modal-body">
                <slot name="body">
                    <div class="input-box font-notojp">
                      <label class="input-label" for="inputname">서비스명  </label>
                      <input type="text" id="inputname" placeholder="서비스명을 입력하세요." v-model="servicedata.title">
                    </div>

                    <div class="input-box font-notojp">
                      <label class="input-label" for="contents">서비스 내용 </label>
                      <input type="text" id="contents" placeholder="서비스내용을 입력하세요." v-model="servicedata.content">
                    </div>
                    
                    <label class="input-label">위치 </label>
                    <div class="col-12 row">
                        <select name="si" id="si" class="col-12 col-md-4 search-info">
                        <option value="none" disabled selected>시/도</option>
                      </select>
                      
                    <select name="gu" id="gu" class="col-12 col-md-4 search-info">
                      <option value="none" disabled selected>구/군</option>
                    </select>
                      <select name="dong" id="dong" class="col-12 col-md-4 search-info">
                      <option value="none" disabled selected>동/읍/면</option>
                    </select>
                     </div>
                        <div class="input-box font-notojp">
                      <label class="input-label" for="price">가격</label>
                      <input type="text" id="price" placeholder="가격을 입력하세요." v-model="servicedata.price">
                    </div>
                </slot>
            </div>

            <div class="modal-footer">
                <slot name="footer">
                 <Button class="font-notojp" buttonText="취소" @click.native="$emit('close')" />
                 <Button class="font-notojp" buttonText="등록" @click.native="$emit('close')" />
                </slot>
            </div>
            </div>
        </div>
        </div>
    </transition>
</template>

<script>
import Button from '@/components/common/Button.vue'
// import axios from 'axios'

export default {
name: 'ServiceAddModal',
  components: {
    Button
  },
  data() {
    return {
      servicedata: {
        title : "",
        content: "",
        price : "",
        location :""
      },
    }
  }
}
</script>

<style scoped>
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
   @media (min-width: 768px) and (max-width: 999px) {
    .modal-container {
         width: 250px;
    }

  }
  @media (min-width: 1000px) {
    .modal-container {
    width: 700px;
     }
  }
  .modal-container {

    margin: 0px auto;
    padding: 0;
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
    margin: 0;
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
.search-info {
    margin: 10px 5px 10px 5px;
    border-radius: 7px;
    height: 50px;
        }
        .input-box input, .modal-container select {
    width: 100%;
    height: 40px;
    border: 1.5px solid black;
    font-size: 13px;
    padding: 0 5px 0 5px;
    border-radius: 5px;
  }
    .input-label {    /* display: none; */
    margin: 15px 0 0px 0;
  }
</style>
