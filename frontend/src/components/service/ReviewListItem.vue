<template>
  <div class="review-list-item">
      <div class="header">
        <h3 v-text="review.title"></h3>
        <p class="rating" v-text="star"></p>
      </div>
      <hr>
      <p v-text="review.writer"></p>
      <p v-text="review.content"></p>
  </div>
</template>

<script>
export default {
    name: 'ReviewListItem',
    props:{
        review: Object
    },
    computed:{
        star(){
            let result = ''
                for (let i=0; i<this.review.point; i++) {
                    result += '★'
                }
            for (let i=0; i<(5 - this.review.point); i++) {
                result += '☆'
            }
                return result
        }

    },
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