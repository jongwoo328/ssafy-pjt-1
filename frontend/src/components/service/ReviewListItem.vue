<template>
  <div class="review-list-item">
      <div class="header">
        <h3>리뷰 제목</h3>
        <p class="rating">★★★★★</p>
      </div>
      <hr>
      <p>작성자</p>
      <p>리뷰내용</p>
  </div>
</template>

<script>
export default {
    name: 'ReviewListItem',


}
</script>

<style>
    .review-list-item {
        border: 1px solid black;
        border-radius: 5px;
        padding: 10px;
        margin: 20px 0 20px 0;
    }
    .review-list-item hr {
        margin: 5px 0 5px 0;
    }
    .review-list-item h3 {
        display: inline-block;
        font-size: 1.25rem;
    }
    p.rating {
        float: right;
        color: orangered;
    }
</style>