<template>
    <li @mouseover="inline" @mouseout="outline" class="nav-item">
        <button v-text="value" class="font-kor"></button>
        <div class="nav-line" style="width: 0; background-color:rgb(236,128,116); transition:width 0.2s linear"></div>
    </li>
</template>

<script>
export default {
    name: 'NavbarLinkListItem',
    props: {
        value: String,
    },
    computed: {
        selector() {
            return `#${this.value} .nav-line`
        }
    },
    methods: {
        inline() {
            // console.log(e.target.id)
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
    .nav-item .nav-line {
        height: 2px;
    }
    .nav-item {
        margin-left: 5px;
        margin-right: 5px;
    }
    .nav-item a {
        text-decoration: none;
        color: black;
        font-size: 1rem;
        padding-bottom: 10px;
    }
    /* .nav-item button:hover {
        border-bottom: 3px solid rgb(236,128,116);
    } */
    .nav-item button {
        border: 0;
        padding: 0;
        font-size: 0.8rem;
        background-color: transparent;
    }
    @media (min-width: 768px) {
        .nav-item {
            margin-left: 20px;
            margin-right: 20px;
        }
        .nav-item button {
            font-size: 1.5rem;
            padding: 1px 6px;
        }
    }
</style>