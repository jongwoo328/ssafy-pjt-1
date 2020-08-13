<template>
    <table class="container table font-kor px-3">
        <thead class="row">
            <div class="col-2">번호</div>
            <div class="col-7 col-md-6">글 제목</div>
            <div class="d-none d-md-block col-md-2">답변</div>
            <div class="col-3 col-md-2">작성시간</div>
        </thead>
        <tbody class="w-100">
            <TableRow v-for="row in rowsInPage" :key="row.q_no" :rowData="row"/>
        </tbody>
        
        <div class="w3-bar" id="paginator">
            <a class="w3-button" @click="previous">&laquo;</a>
            <a v-for="pNum in pageRange" :key="pNum" v-text="pNum" class="w3-button" @click="getPage"/>
            <a class="w3-button" @click="next">&raquo;</a>
        </div>
    </table>
</template>

<script>
import TableRow from './TableRow.vue'

export default {
    name: 'Table',
    components: {
        TableRow,
    },
    data() {
        return {
            rowPerPage: 10,
            pageNow: 1,
            range: 5,
            beforeIndex: 1,
            dummy: null
        }
    },
    props: {
        rowList: Array,
    },
    computed: {
        pageNumber() {
            if (this.rowList.length % this.rowPerPage > 0) {
                return Math.floor(this.rowList.length / this.rowPerPage) + 1
            } else {
                return Math.floor(this.rowList.length / this.rowPerPage)
            }
        },
        pageRange() {
            return [...Array(this.pageNumber).keys()].map(key => key+1)
        },
        rowsInPage() {
            return this.rowList.slice((this.pageNow - 1) * this.rowPerPage, this.pageNow * this.rowPerPage)
        },
        rangeStart() {
            return Math.floor(this.pageNow / this.range)
        },
    },
    methods: {
        previous() {
            if (this.pageNow - this.range >= 1) {
                this.pageNow -= this.range
            } else {
                this.pageNow = 1
            }
            this.checkIndex(this.pageNow)
        },
        next() {
            if (this.pageNow + this.range > this.pageNumber) {
                this.pageNow = this.pageNumber
            } else {
                this.pageNow += this.range
            }
            this.checkIndex(this.pageNow)
        },
        getPage(e) {
            this.checkIndex(e.target.innerText)
            this.pageNow = e.target.innerText
        },
        checkIndex(number) {
            const indexList = document.querySelectorAll('#paginator a')
            indexList[this.beforeIndex].setAttribute('style', '')
            indexList[number].setAttribute('style', 'background-color: rgb(236,128,116); color: white;')
            this.beforeIndex = number

            const pageStart = Math.floor(this.pageNow / this.range) + 1
            if (pageStart + this.range - 1 > this.pageNumber) {
                this.dummy = pageStart
            } else {
                this.pageRange
            }
        },
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
.table .w3-bar {
    margin-top: 50px;
}
.table .w3-bar p {
    display: inline-block;
}
.table .w3-bar a:hover {
    background-color: white;
    border-bottom: 1px solid black;
}
</style>