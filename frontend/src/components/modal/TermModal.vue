<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
              전체 약관
            </slot>
          </div>

          <div class="modal-body w-100">
            <slot name="body">
                제1조 (목적)
                <br>
                이 약관은 커넥터(이하 당사)가 제공하는 수업 연결 서비스를 이용함에 있어 당사와 이용자의 권리, 의무 및 책임사항을 규정합니다. 이를 통하여 당사와 이용자는 알아야 할 사항을 숙지, 상호 신뢰의 증진을 목적으로 합니다.
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
@media (min-width: 768px) {
  .modal-container {
    width: 25%;
    height: 70%;
  }
}
</style>