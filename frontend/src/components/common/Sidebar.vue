<template>
  <div id="sidebar">

  </div>
</template>

<script>
export default {
    name: 'Sidebar',


}
</script>

<style>
    @media (min-width: 768px) {
        #sidebar {
            z-index: -1;
            display: inline-block;
            box-shadow: 2px 5px 3px gray;
            position: absolute;
            left: 0;
            height: 120%;
            width: 20%;
            min-width: 250px;
            background-color: #fff4e6;
        }
    }

</style>