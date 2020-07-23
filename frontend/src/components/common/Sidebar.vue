<template>
  <div id="sidebar">
      <div class="sidebar-link-list">
        <router-link to='/test' class="sidebar-link font-kor"><p>내 정보</p></router-link>
        <router-link to='/test' class="sidebar-link font-kor"><p>내 프로필</p></router-link>
        <router-link to='/test' class="sidebar-link font-kor"><p>결제내역</p></router-link>
        <router-link to='/test' class="sidebar-link font-kor"><p>팔로우</p></router-link>
        <router-link to='/test' class="sidebar-link font-kor"><p>Q&A</p></router-link>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Sidebar',


}
</script>

<style>
    .sidebar-link-list {
        display: none;
    }
    @media (min-width: 768px) {
        #sidebar {
            z-index: 1;
            display: inline-block;
            box-shadow: 2px 5px 3px gray;
            position: absolute;
            left: 0;
            height: 110%;
            /* width: 20%; */
            min-width: 250px;
            /* background-color: white; */
        }
        .sidebar-link-list {
            display: flex;
            flex-direction: column;
            margin-top: 50px;
        }
        .sidebar-link {
            display: inline-block;
            margin-left: 50px;
            margin-bottom: 20px;
            font-size: 1.5rem;
            color: black;
        }
        .sidebar-link:hover {
            text-decoration: none;
            color: black;
        }
        .sidebar-link p:hover {
            display: inline-block;
            color: rgb(236,128,116);
            border-bottom: 2px solid rgb(236,128,116);
        }
    }

</style>