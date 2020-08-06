<template>
    <li class="nav-item">
        <button v-text="value" class="font-kor"></button>
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

<style>
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
    .nav-item button:hover {
        border-bottom: 3px solid rgb(236,128,116);
    }
    .nav-item button {
        border: 0;
        background-color: transparent;
    }
    @media (min-width: 768px) {
        .nav-item {
            margin-left: 20px;
            margin-right: 20px;
        }
        .nav-item button {
            font-size: 1.5rem;
        }
    }
</style>