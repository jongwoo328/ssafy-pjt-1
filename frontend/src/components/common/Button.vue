<template>
  <button :id="id" class="custom-button" v-text="buttonText" :style="color">
  </button>
</template>

<script>
export default {
    name: 'Button',
    props: {
        buttonText: String,
        id: String,
        buttonColor: {
            type: String,
            default: 'rgb(236,128,116)'
        },
    },
    computed: {
        color() {
            return `background-color: ${this.buttonColor};`
        }
    }
}
</script>

<style>
    .custom-button {
        background-color: rgb(236,128,116);
        border: 0;
        color: white;
        padding: 5px 15px 5px 15px;
        font-size: 1.25rem;
        border-radius: 3px;
    }
</style>