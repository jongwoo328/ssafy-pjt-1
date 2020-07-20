<template>
    <li class="nav-item">
        <a href="" v-text="value" class="font-roboto"></a>
    </li>
</template>

<script>
export default {
    name: 'NavbarLinkListItem',
    props: {
        value: String,
    }
}
</script>

<style scoped>
    .nav-item {
        margin-left: 10px;
        margin-right: 10px;
    }
    .nav-item a {
        text-decoration: none;
        color: black;
        font-size: 1rem;
        padding-bottom: 10px;
    }
    .nav-item a:hover {
        border-bottom: 3px solid rgb(236,128,116);
    }
    @media (min-width: 768px) {
        .nav-item {
            margin-left: 20px;
            margin-right: 20px;
        }
        .nav-item a {
            font-size: 1.5rem;
        }
    }
</style>