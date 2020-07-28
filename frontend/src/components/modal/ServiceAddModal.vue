<template>
    <transition name="modal">
      <div id="serviceAddModal">
        <div class="modal-mask">
        <div class="modal-wrapper">
            <div class="modal-container">
            <div class="modal-header">
                <slot name="header">
                    <h3>서비스 등록</h3>
                </slot>
            </div>

            <div class="modal-body font-kor">
                <slot name="body">
                    <div class="input-box">
                      <label class="input-label form-block-head" for="inputname">분류</label>
                        <div class="col-12 d-flex flex-wrap p-0">
                        <select name="si" id="si" class="col-12 col-md-4 search-info m-0">
                        <option value="none" disabled selected>분류</option>
                      </select>
                        </div>
                    </div>
                    <div class="input-box">
                      <label class="input-label form-block-head" for="inputname">서비스명  </label>
                      <input type="text" id="inputname" placeholder="서비스명을 입력하세요." v-model="servicedata.title">
                    </div>

                    <div class="input-box">
                      <label class="input-label form-block-head" for="contents">서비스 내용 </label>
                      <input type="text" id="contents" placeholder="서비스내용을 입력하세요." v-model="servicedata.content">
                    </div>
                    
                    <label class="input-label form-block-head">위치 </label>
                    <div class="col-12 d-flex flex-wrap p-0">
                        <select name="si" id="si" class="col-12 col-md-4 search-info m-0">
                        <option value="none" disabled selected>시/도</option>
                      </select>
                      
                    <select name="gu" id="gu" class="col-12 col-md-4 search-info m-0">
                      <option value="none" disabled selected>구/군</option>
                    </select>
                    <select name="dong" id="dong" class="col-12 col-md-4 search-info m-0">
                      <option value="none" disabled selected>동/읍/면</option>
                    </select>
                     </div>
                        <div class="input-box font-notojp">
                      <label class="input-label form-block-head" for="price">가격</label>
                      <input type="number" min="0" step="100" id="price" placeholder="가격을 입력하세요." v-model="servicedata.price">
                    </div>
                </slot>
            </div>

            <div class="modal-footer">
                <slot name="footer">
                 <Button class="font-notojp btn-cancel" buttonText="취소" @click.native="$emit('close')" />
                 <Button class="font-notojp" buttonText="등록" @click.native="$emit('close')" />
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

<style>
  #serviceAddModal .modal-mask {
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
  #serviceAddModal .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }
   @media (min-width: 768px) and (max-width: 999px) {
    #serviceAddModal .modal-container {
         width: 250px;
    }

  }
  @media (min-width: 1000px) {
    #serviceAddModal .modal-container {
    width: 700px;
     }
  }
  #serviceAddModal .modal-container {
    margin: 0px auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  #serviceAddModal .modal-header h3 {
    margin: 0;
    color: rgb(236,128,116);
  }

  #serviceAddModal .form-block-head {
    font-size: 16px;
    margin: 10px 0 10px 0;
    padding: 8px 0 0 0;
    text-align: right;
    font-weight: bolder
  }

  #serviceAddModal .modal-body {
    margin: 0;
  }

  #serviceAddModal .modal-footer .btn-cancel {
    background-color: transparent;
    color: rgb(236,128,116);
  }

  #serviceAddModal .modal-enter {
    opacity: 0;
  }

  #serviceAddModal .modal-leave-active {
    opacity: 0;
  }

  #serviceAddModal .modal-enter .modal-container,
  #serviceAddModal .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }
  #serviceAddModal .search-info {
      margin: 10px 5px 10px 5px;
      height: 50px;
  }
  #serviceAddModal .input-box input, #serviceAddModal .modal-container select {
    width: 100%;
    height: 40px;
    border: 0.8px;
    border-radius: 0;
    padding-left: 10px;
    border-style: none none solid none;
  }

</style>
