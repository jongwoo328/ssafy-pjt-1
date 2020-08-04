<template>
    <router-link class="tr" :to="qnaLink" >
        <div class="row">
            <div class="col-2" v-text="rowData.qnano"></div>
            <div class="col-7 col-md-6">
                <div class="d-inline">
                    <Badge
                        class="d-md-none"
                        v-if="hasReply"
                        badgeColor="forestgreen"
                        badgeText="완료"
                    />
                    <Badge
                        class="d-md-none"
                        v-else
                        badgeColor="orangered"
                        badgeText="대기"
                    />
                </div>
                {{rowData.qtitle}}
            </div>
            <div class="d-none d-md-block col-md-2">
                <Badge
                    class="d-none d-md-inline-block"
                    v-if="hasReply"
                    badgeColor="forestgreen"
                    badgeText="완료"
                />
                <Badge
                    class="d-none d-md-inline-block"
                    v-else
                    badgeColor="orangered"
                    badgeText="대기"
                />
            </div>
            <div class="col-3 col-md-2" v-text="rowData.qdate"></div>
        </div>
    </router-link>
</template>

<script>
import Badge from '@/components/common/Badge.vue'

export default {
    name: 'TableRow',
    props: {
        rowData: Object,
    },
    computed: {
        qnaLink() {
            return `/qna/${this.rowData.qnano}`
        },
        hasReply() {
            return this.rowData.acontent !== null
        }
    },
    components: {
        Badge
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
    .tr div.row > div {
        border-bottom: 1px solid gray;
        text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
    }
    @media (min-width: 768px) {
        .d-md-inline-block {
            display: inline-block !important;
        }
    }
</style>