<template>
  <div id="scrollUp" @click="scrollUp">
      <i class="fas fa-angle-up"></i>
  </div>
</template>

<script>
export default {
    name: 'ScrollUpButton',
    methods: {
        scrollUp() {
            window.scroll({
            top: 0,
            behavior: 'smooth'
            })
        }
    }
}
</script>

<style>
#scrollUp {
    width: 40px;
    height: 40px;
    position: fixed;
    bottom: 40px;
    right: 10px;
    background-color: white;
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2rem;
    color: rgb(236,128,116);
    box-shadow: 0 1px 5px gray;
}
#scrollUp:hover {
    cursor: pointer;
}
@media (min-width: 768px) {
    #scrollUp {
        display: none;
    }
}
</style>