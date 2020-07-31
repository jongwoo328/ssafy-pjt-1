<template>
  <div class="card-list">
      <ServiceListItem v-for="service in services" :key="service.s_no" :service="service"/>
  </div>
</template>

<script>
import ServiceListItem from "./ServiceListItem.vue"

export default {
    name: 'ServiceList',
    components: {
        ServiceListItem,
    },
    props: {
        services: Array,
    }
}
</script>

<style>

</style>