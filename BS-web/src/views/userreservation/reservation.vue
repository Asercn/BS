<template>
  <div>
    <el-menu mode="horizontal" :default-active="activeIndex" @select="handleMenuSelect" active-text-color="#fad089" text-color="#f1f2f6" background-color="#747d8c">
      <el-menu-item index="roomreservation">房间预定</el-menu-item>
      <el-menu-item index="message">留言板</el-menu-item>
    </el-menu>
    <component :is="activeIndex"></component>
  </div>
</template>

<script>
import Roomreservation from "@/views/userreservation/roomreservation";
import message from "@/views/userreservation/message";
export default {
  name: "reservation",
  components: { Roomreservation, message },
  data() {
    return {
      activeIndex: 'roomreservation'
    }
  },
  methods: {
    handleMenuSelect(index) {
      this.activeIndex = index
    }
  },
  computed: {

  }
}
</script>

<style scoped>

</style>
