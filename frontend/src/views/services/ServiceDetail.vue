<template>
  <div id="service-detail" class="container">
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
    #service-detail {
        display: block;
        margin-top: 50px;
        min-height: 1000px;
        padding: 20px;
        border: 1px solid black;
    }
    .photo-service {
        display: flex;
        flex-direction: row;
        width: 100%;
        border: 1px solid black;
    }
    .photo {
        height: 300px;
        width: 50%;
        border: 1px solid black;
        object-fit: contain;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .service {
        border: 1px solid black;
        width: 50%;
    }
    .addr-review {
        display: flex;
        flex-direction: row;
        width: 100%;
        border: 1px solid black;
    }
    .addr {
        border: 1px solid black;
        width: 50%;
    }
    .review {
        border: 1px solid black;
        width: 50%;
        height: 300px;
    }
</style>