<template>
  <div id="sidebar-card-list">
    <div class="sidebar-event-title">
        <h3>진행중인 이벤트</h3>
    </div>
    <SidebarCardListItem v-for="event in eventList" :key="event.eventno" :event="event" />
  </div>
</template>

<script>
import SidebarCardListItem from './SidebarCardListItem.vue'

export default {
    name: 'SidebarCardList',
    components: {
        SidebarCardListItem
    },
    props: {
        eventList: Array,
    }

}
</script>

<style>
    #sidebar-card-list .sidebar-event-title {
        text-align: center;
    }
    #sidebar-card-list .sidebar-event-title h3 {
        border: 2px solid rgb(236,128,116);
        background-color: rgb(233, 91, 75);
        color: white;
        padding: 5px 0 5px 0;
    }
</style>