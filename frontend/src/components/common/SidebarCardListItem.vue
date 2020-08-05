<template>
  <div class="sidebar-card">
      <p class="sidebar-card-main">Spring 선착순 할인</p>
      <hr>
      <div class="sidebar-card-sub">
          <p>ksadjhflaksjdhflaksjdhfflaksjdhfflaksjdhfflaksjdhfflaksjdhfflaksjdhflaasdfasdfasdfasdfasdfasdfasdfasdfasdfasfsaksjhdflaksjdhflaksjh</p>
      </div>
  </div>
</template>

<script>
export default {
    name: 'SidebarCardListItem',
}
</script>

<style>
    .sidebar-card {
        border: 1px solid black;
        margin: 10px 0 10px 0;
        padding: 5px;
        max-width: 180px;
    }
    .sidebar-card > p {
        width: 100%;
        text-overflow: ellipsis;
        white-space: wrap;
        overflow: hidden;
    }
    .sidebar-card hr {
        margin: 5px 0 5px 0;
    }
    .sidebar-card p {
        margin: 0;
    }
    .sidebar-card .sidebar-card-sub {
        min-height: 50px;
        word-break: break-all;
    }
</style>