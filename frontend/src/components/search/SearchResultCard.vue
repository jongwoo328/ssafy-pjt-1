<template>
  <div class="search-result-card col-12 col-xl-4 font-kor">
      <div class="card-inner">
        <header>
            <h2>CSS 마스터</h2>
            <p>이종우</p>
        </header>
        <hr>
        <div class="card-content">
            <div class="card-description">
                <img src="@/assets/logo_mini.jpg" alt="">
                <p>css 기본기를 가르쳐 주는 과정입니다.</p>
            </div>
        </div>
        <hr>
        <footer>
            <div class="card-addr">
                <p>구미시 진평동</p>
            </div>
            <div class="card-price">
                <p><span>1,000,000</span> 원</p>
            </div>
        </footer>
      </div>
  </div>
</template>

<script>
export default {
    name: 'SearchResultCard',
    
}
</script>

<style>
    .search-result-card {
        display: inline-block;
        padding: 5px 10px 5px 10px;
    }
    .card-inner:hover {
        background-color: whitesmoke;
        cursor: pointer;
    }
    .card-inner {
        border-radius: 7px;
        box-shadow: 0 1px 5px gray;
        padding: 7px;
    }
    .card-inner header {
        position: relative;
        display: flex;
        justify-content: space-between;
        padding: 5px;
    }
    .card-inner header h2 {
        font-size: 1.25rem;
        font-weight: bold;
    }
    .card-inner hr {
        margin-top: 0;
        margin-bottom: 5px;
    }
    .card-inner footer {
        padding: 5px;
        display: flex;
        justify-content: space-between;
    }
    .card-inner footer p {
        margin-bottom: 0;
    }
    .card-inner footer span, .card-inner footer .card-addr {
        font-weight: bold;
    }
    .card-content {
        padding-left: 5px;
        padding-right: 5px;
    }
</style>