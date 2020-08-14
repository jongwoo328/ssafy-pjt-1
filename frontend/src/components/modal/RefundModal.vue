<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
                ['Connector' 취소 및 환불 규정]
            </slot>
          </div>

          <div class="modal-body w-100">
            <slot name="body">
                <p>결제일 기준으로 'Connector'의 특약이 적용됩니다.</p>
                <hr>
                <p>1) 결제일로부터 7일 전 취소시 : 총 결재금액 100% 환불</p> 
                <p>2) 결제일로부터 7일 후 취소시 : 결제 취소 및 환불 불가</p> 
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              <Button id="modalExit" buttonText="확인" @click.native="$emit('close')" />
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import Button from '@/components/common/Button.vue'

export default {
  components: {
    Button
  }
}
</script>

<style scoped>
.modal-mask {
  color: black;
  text-align: left;
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
/* 
.modal-default-button {
  float: right;
} */

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

#modalExit {
    margin-bottom: 10px;
}
</style>