<template>
  <div @mouseover="inline" @mouseout="outline" class="sidebar-link font-kor" :id="i">
    <router-link :to="to">
        <p v-text="value"></p>
        <div class="line" style="width: 0px;"></div>
    </router-link>
  </div>
</template>

<script>
export default {
    name: 'SidebarItem',
    props: {
        to: String,
        value: String,
        i: String
    },
    computed: {
        selector() {
            return `#${this.i} .line`
        }
    },
    methods: {
        inline() {
            const line = document.querySelector(this.selector)
            line.style.width = "100%"
        },
        outline() {
            const line = document.querySelector(this.selector)
            line.style.width = "0px"
        }
    }
}
</script>

<style>
.line {
    height: 2px;
    background-color: rgb(236,128,116);
    transition: width 0.2s linear;
}
.sidebar-link {
    display: inline-block;
    width: fit-content;
}
@media (min-width: 768px) {
    #sidebar .sidebar-link {
            display: inline-block;
            margin-left: 50px;
            margin-bottom: 20px;
            font-size: 1.5rem;
            color: black;
    }
    #sidebar .sidebar-link a:hover {
        text-decoration: none;
        color: black;
    }
    /* #sidebar .sidebar-link p:hover {
        display: inline-block;
        color: rgb(236,128,116);
        border-bottom: 2px solid rgb(236,128,116);
    } */
}
</style>