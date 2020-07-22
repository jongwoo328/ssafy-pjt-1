<template>
  <div id="service-detail" class="container">
      <h1 v-text="this.$route.params.service_id"></h1>
  </div>
</template>

<script>
export default {
    name: 'ServiceDetail',
    data() {
        return {
            serviceId: 0,
            serviceData: {},

        }
    },
    created() {
        
    }
}
</script>

<style>

</style>