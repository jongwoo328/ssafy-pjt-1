<template>
  <button :id="id" class="custom-button" v-text="buttonText">
  </button>
</template>

<script>
export default {
    name: 'Button',
    props: {
        buttonText: String,
        id: String
    }
}
</script>

<style>
    .custom-button {
        background-color: rgb(236,128,116);
        border: 0;
        color: white;
        padding: 10px 15px 10px 15px;
        font-size: 1.25rem;
        border-radius: 3px;
    }
</style>