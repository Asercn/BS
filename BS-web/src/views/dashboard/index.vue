<template>
  <div class="dashboard-container">
    <el-header>
      <h1>欢迎: {{ name }}</h1>
    </el-header>
    <!--      展示栏-->
    <el-divider/>
    <el-main>
      <el-card class="dashboard-box-card">
        <p style="font-weight: bold;">今日数据</p>
        <el-table :data="tableData">
          <el-table-column label="#" align="left"></el-table-column>
          <el-table-column label="开房(间)" prop="oroom"></el-table-column>
          <el-table-column label="空房(间)" prop="eroom"></el-table-column>
          <el-table-column label="#" align="right"></el-table-column>
        </el-table>
      </el-card>
      <el-collapse>
        <el-collapse-item title="日历">
          <el-calendar v-model="calendar">
          </el-calendar>
        </el-collapse-item>
      </el-collapse>
    </el-main>
  </div>

</template>

<script>
import { mapGetters } from 'vuex'
import roomApi from '@/api/room'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      drawer: false,
      calendar: new Date(),
      tableData: [],
      oroom: null,
      eroom: null
    }
  },
  methods: {
    getRoomInfo() {
      roomApi.getRoomInfo().then(rep => {
        this.tableData = rep.data.tableData
      })
    }
  },
  created() {
    this.getRoomInfo()
  }
}

</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
  &-box-card{
    width: auto;
  }
}
.drawerBt{
  position: fixed;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
}
</style>
