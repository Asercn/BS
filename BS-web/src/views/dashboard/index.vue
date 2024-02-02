<template>
  <div class="dashboard-container">
    <el-header>
      <h1>欢迎: {{ name }}</h1>
    </el-header>
    <!--      展示栏-->
    <el-divider/>
    <el-main>
      <el-card>
        <p style="font-weight: bold;">房间数据</p>
        <div>
          <ul class="list-group">
            <li class="list-group-item">
              <span class="textpd">已开房间:</span>
              <div style="display: inline">{{ tableData.oroom}}</div>
            </li>
            <li class="list-group-item">
              <span class="textpd">未开房间:</span>
              <div style="display: inline">{{ tableData.eroom}}</div>
            </li>
            <li class="list-group-item">
              <span class="textpd" style="display: inline-block">占 比:</span>
              <div style="width: 110em;display: inline-block">
                <el-progress
                  :text-inside="true"
                  :percentage="percent"
                  :stroke-width="20"></el-progress>
              </div>
            </li>
          </ul>
        </div>
      </el-card>
      <el-card>
        <span>通知</span>
      </el-card>

      <el-card>
        <el-collapse>
          <el-collapse-item title="日历">
            <el-calendar v-model="calendar">
            </el-calendar>
          </el-collapse-item>
        </el-collapse>
      </el-card>
    </el-main>
  </div>

</template>

<script>
import { mapGetters } from 'vuex'
import roomApi from '@/api/room'

export default {
  computed: {
    ...mapGetters([
      'name'
    ]),
    percent() {
      if (this.tableData.oroom === null || this.tableData.eroom === null) {
        return 0
      } else {
        // console.log(((this.tableData.oroom) / (this.tableData.oroom + this.tableData.eroom)) * 100)
        return parseInt(((this.tableData.oroom) / (this.tableData.oroom + this.tableData.eroom)) * 100)
      }
    }
  },
  data() {
    return {
      calendar: new Date(),
      tableData: {
        oroom: null,
        eroom: null
      }
    }
  },
  methods: {
    getRoomInfo() {
      roomApi.getRoomInfo().then(rep => {
        this.tableData = rep.data
      })
    }
  },
  created() {
    this.getRoomInfo()
  }
}
</script>

<style lang="scss" scoped>
.textpd{
  padding-right: 30px;
}
.list-group {
  padding-left: 0px;
  list-style: none;
}
.list-group-item {
  border-bottom: 1px solid #e7eaec;
  border-top: 1px solid #e7eaec;
  margin-bottom: -1px;
  padding: 11px 0px;
  font-size: 13px;
}

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
