<template>
<div id="content">
    <div id="content-mobile">
            <div class="icon-wrap">
                <i class="fas fa-dumbbell"></i>
                <div class="desc font-lobster">Fitness</div>
            </div>
            <div class="icon-wrap">
                <i class="fas fa-drum"></i>
                <div class="desc font-lobster">Music</div>
            </div>
            <div class="icon-wrap">
                <i class="fas fa-square-root-alt"></i>
                <div class="desc font-lobster">Study</div>
            </div>
            <div class="icon-wrap">
                <div class="desc desc-none font-lobster">with <span class="font-notojp connector">CONNECTOR</span></div>
            </div>
    </div>
    <div id="content-desktop">
        
    </div>
</div>
</template>

<script>

export default {
    name: 'Content',
}
</script>

<style>
    #content {
        margin-top: 30px;
    }
    #content-mobile {
        box-shadow: 0 0 5px gray;
        height: 450px;
        border-radius: 7px;
        padding: 30px 20px 30px 10%;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        background-color: #343a40;
    }
    #content i {
        display: inline-block;
        font-size: 4rem;
    }
    .icon-wrap {
        vertical-align: center;
        display: flex;
        justify-content: space-evenly;
    }
    .desc {
        display: inline-block;
        margin-left: 10%;
        height: 64px;
        line-height: 64px;
        font-size: 2rem;
        vertical-align: top;
        padding: 0;
        color: white;
    }
    .connector {
        font-style: italic;
        margin-left: 5vw;
        border-bottom: 2px solid white;
    }
    .desc-none {
        font-size: 1.5rem;
        margin-top: 20px;
        margin-left: 0%;
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
        #content-mobile {
            display: none;
        }
        #content i {
            font-size: 6rem;
        }
        .desc {
            font-size: 4rem;
        }
        .desc-none {
            font-size: 3rem;
        };
    }
</style>