<template>
  <div id="content">
    <div class="icon-wrap">
        <i class="fas fa-dumbbell left-30"></i>
        <div class="desc font-lobster">Fitness</div>
    </div>
    <div>
        <i class="fas fa-drum left-60"></i>
        <div class="desc font-lobster">Music</div>
    </div>
    <div>
        <i class="fas fa-square-root-alt left-30"></i>
        <div class="desc font-lobster">Study</div>
    </div>
    <div>
        <div class="desc desc-none font-lobster">with <span class="font-notojp connector">CONNECTOR</span></div>
    </div>
  </div>
</template>

<script>

export default {
    name: 'Content',
    components: {
        
    }
}
</script>

<style scoped>
    #content {
        box-shadow: 0 0 5px gray;
        height: 450px;
        border-radius: 7px;
        padding: 50px 20px 50px 20px;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        background-color: #343a40;
    }
    i {
        display: inline-block;
        height: 64px;
        font-size: 4rem;
    }
    .icon-warp {
        vertical-align: center;
    }
    .desc {
        display: inline-block;
        height: 64px;
        margin-left: 70px;
        font-size: 2rem;
        vertical-align: top;
        padding: 0;
        color: white;
    }
    .connector {
        font-style: italic;
        margin-left: 10px;
        border-bottom: 2px solid white;
    }
    .desc-none {
        margin: 20px;
    }
    .fa-dumbbell {
        color: #fa5252;
    }
    .fa-drum {
        color: #748ffc;
    }
    .fa-square-root-alt {
        color: #20c997;
    }
    @media (min-width: 768px) {
        #content {
            height: 800px;
        }
    }
</style>