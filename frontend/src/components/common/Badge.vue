<template>
    <div :class="getClass" :style="backgroundColor">
        <span class="badge-text" v-text="badgeText"></span>
        <span v-if="!isCountNull" class="badge-count" v-text="badgeCount"></span>
    </div>
</template>

<script>
export default {
    name: 'Badge',
    props: {
        badgeText: String,
        badgeColor: String,
        badgeCount: {
            type: Number,
            default: -1
        },
        badgeClass: String
    },
    computed: {
        backgroundColor() {
            return `background-color: ${this.badgeColor};`
        },
        isCountNull() {
            return this.badgeCount === -1
        },
        getClass() {
            if (this.badgeClass !== undefined ) {
                return `badge ${this.badgeClass}`
            }
            return 'badge'
        }
    }
}
</script>

<style scoped>
    div {
        text-align: right;
        font-size: 0.8rem;
        display: inline-block;
    }
    .badge {
        border-radius: 5px;
        color: white;
        padding: 2px 4px 2px 4px;
    }
    .badge-count {
        margin-left: 5px;
    }
</style>