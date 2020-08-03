<template>
    <router-link class="tr" :to="qnaLink" >
        <div class="row">
            <div class="col-2" v-text="rowData.qnano"></div>
            <div class="col-4 col-md-6" v-text="rowData.qtitle"></div>
            <div v-if="rowData.qwriter===null" class="col-3 col-md-2" v-text="this.$store.getters.getUserData.name"></div>
            <div v-else class="col-3 col-md-2" v-text="rowData.qwriter"></div>
            <div class="col-3 col-md-2" v-text="rowData.qdate"></div>
        </div>
    </router-link>
</template>

<script>
export default {
    name: 'TableRow',
    props: {
        rowData: Object,
    },
    computed: {
        qnaLink() {
            return `/qna/${this.rowData.qnano}`
        }
    }
}
</script>

<style>
    .tr {
        display: block;
        color: black;
    }
    .tr:hover {
        text-decoration: none;
        color: black;
        background-color: whitesmoke;
    }
    .tr div.row div {
        border-bottom: 1px solid gray;
        text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
    }
    
</style>