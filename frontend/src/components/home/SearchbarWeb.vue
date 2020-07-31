<template>
  <div id="join-web" class="font-kor container">
      <h1>검색하세요!</h1>
      <form action="" class="row search-form">
          <div class="col-12">
                <select name="category" id="category" class="col-1 search-info">
                    <option value="none" selected disabled>분류</option>
                </select>
                <input type="text" class="col-7 search-info">
                <Button buttonText="검색" />
          </div>
          <div class="offset-3 col row mt-2">
            <select name="si" id="si" class="col-3 search-info">
                <option value="none" disabled selected>시/도</option>
            </select>
            <select name="gu" id="gu" class="col-3 search-info">
                <option value="none" disabled selected>구/군</option>
            </select>
            <select name="dong" id="dong" class="col-3 search-info">
                <option value="none" disabled selected>동/읍/면</option>
            </select>
          </div>
      </form>
  </div>
</template>

<script>
import Button from '@/components/common/Button.vue'

export default {
    name: 'SearchbarWeb',
    components: {
        Button,
    }
}
</script>

<style>
    #join-web {
        display: none;
    }
    @media (min-width: 768px) {
        #join-web {
            display: inline-block;
            margin: 0 auto 30px 0;
            padding-right: 0;
        }
        #join-web h1 {
            display: inline-block;
            padding-top: 30px;
        }
        .search-info {
            width: 100%;
            height: 40px;
            border: 0.8px;
            border-radius: 0;
            padding-left: 10px;
            border-style: none none solid none;
            /* margin: 10px 5px 10px 5px;
            border-radius: 7px;
            height: 50px; */
        }
        #join-web button {
            margin-left: 20px;
            padding: 5px 10px;
        }
        #category {
            min-width: 84px;
        }
        #join-web .search-form {
            width: 100%;
        }
        #dong {
            min-width: 125px;
        }
        
    }
</style>