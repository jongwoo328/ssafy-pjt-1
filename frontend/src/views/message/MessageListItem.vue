<template>
    <div>
        <p>발신자 : {{message.writername}}</p>
        <p>제목 : {{message.title}}</p>
        <p>읽음표시 : {{message.readcheck}}</p>
        <p>{{message.senddate}}</p>
        <hr>
    </div>
</template>

<script>
export default {
    name: 'MessageListItem',
    props: {
        message: Object
    },
    computed: {
        
    }
}
</script>

<style>

</style>