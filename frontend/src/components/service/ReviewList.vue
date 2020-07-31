<template>
  <div id="review-list">
      <ReviewListItem/>
      <ReviewListItem/>
      <ReviewListItem/>
  </div>
</template>

<script>
import ReviewListItem from './ReviewListItem.vue'

export default {
    name: 'ReviewList',
    components: {
        ReviewListItem,
    }

}
</script>

<style>

</style>