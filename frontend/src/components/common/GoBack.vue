<template>
    <div class="back" @click="goBack">
        <i class="fas fa-arrow-left"></i>
    </div>
</template>

<script>
export default {
    name: 'GoBack',
    methods: {
        goBack() {
            this.$router.go(-1)
        }
    }
}
</script>

<style>
    .back {
        font-size: 2rem;
        display: inline-block;
    }
    .back:hover {
        cursor: pointer;
        color: rgb(236,128,116);
    }
</style>