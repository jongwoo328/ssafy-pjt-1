<template>
  <div class="card-list">
      <SearchResultCardItem v-for="service in services" :key="service.s_no" :service="service"/>
  </div>
</template>

<script>
import SearchResultCardItem from './SearchResultCardItem.vue'

export default {
    name: 'SearchResultCard',
    components: {
        SearchResultCardItem
    },
    props: {
        services: Array,
    }
}
</script>

<style>
    .card-list {
        margin-top: 30px;
    }
</style>