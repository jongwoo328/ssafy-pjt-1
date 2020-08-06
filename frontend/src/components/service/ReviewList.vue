<template>
  <div id="review-list">
      
      <ReviewListItem v-for="review in reviews" :key="review.revno" :review="review" />
  </div>
</template>

<script>
import ReviewListItem from './ReviewListItem.vue'

export default {
    name: 'ReviewList',
    components: {
        ReviewListItem,
    },
    props:{
        reviews : Array
    }

}
</script>

<style>

</style>