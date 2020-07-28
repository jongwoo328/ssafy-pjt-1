<template>
  <div class="search-result-card col-12 col-xl-4 font-kor">
      <div class="card-inner">
        <header>
            <h2 v-text="service.s_name"></h2>
            <div class="pro-info">
                <img src="https://grepp-programmers.s3.amazonaws.com/production/company/logo/2640/_nolbal_bi_logo_%E1%84%89%E1%85%A6%E1%84%85%E1%85%A9.png" alt="">
                <p v-text="service.u_no"></p>
            </div>
        </header>
        <hr>
        <div class="card-content">
            <div class="card-description">
                <!-- <img src="@/assets/logo_mini.jpg" alt=""> -->
                <p v-text="service.description"></p>
            </div>
        </div>
        <hr>
        <footer>
            <div class="card-addr">
                <p v-text="service.seraddr"></p>
            </div>
            <div class="card-price">
                <p><span v-text="service.price"></span> 원</p>
            </div>
        </footer>
      </div>
  </div>
</template>

<script>
export default {
    name: 'ServiceListItem',
    props: {
        service: Object
    }
    
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
    .pro-info img{
        width: 50px;
        height: 50px;
    }
    .pro-info p {
        display: inline-block;
    }
</style>