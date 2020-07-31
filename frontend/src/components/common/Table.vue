<template>
    <table class="container table font-kor px-3">
        <thead class="row">
            <div class="col-2">번호</div>
            <div class="col-4 col-md-6">글 제목</div>
            <div class="col-3 col-md-2">작성자</div>
            <div class="col-3 col-md-2">작성시간</div>
        </thead>
        <tbody class="w-100">
            <TableRow v-for="row in rowList" :key="row.q_no" :rowData="row"/>
        </tbody>
    </table>
</template>

<script>
import TableRow from './TableRow.vue'

export default {
    name: 'Table',
    components: {
        TableRow,
    },
    props: {
        rowList: Array,
    }

}
</script>

<style>
.table {
    text-align: center;
    vertical-align: middle;
    height: 40px;
    line-height: 40px;
    font-size: 1rem;
    margin-top: 20px;
}
.table thead {
    border-bottom: 2px solid black;
    border-top: 1px solid black;
    font-size: 1rem;
    font-weight: bold;
}

</style>