<template>
  <div class="dashboard-container">
    <el-header>
      <h1>欢迎: {{ name }}</h1>
    </el-header>
    <!--      展示栏-->
    <el-divider/>
    <el-main>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card>
            <el-row :gutter="20">
              <el-col :span="12">
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
                      <div>
                        <el-progress
                          :text-inside="true"
                          :percentage="percent"
                          :stroke-width="20"></el-progress>
                      </div>
                    </li>
                  </ul>
                </div>
              </el-col>
              <el-col :span="12">
                <!--                加一张饼图-->
                <e-charts :option="option" style="height: 300px;"></e-charts>
              </el-col>
            </el-row>
          </el-card>
          <el-row :gutter="20">
            <el-col :span="24">
              <el-card style="margin-top: 10px;height: 500px;overflow: auto;">
                <div >
                  <h4>公告列表(用户也可以在此处留言)</h4>
<!--                  新增公告列表功能-->
                  <el-collapse>
                    <el-collapse-item v-for="(noticeInfo, i) in noticeInfos" :key="i" :title="`${noticeInfo.title}-(${formatDate(noticeInfo.releaseTime)})`">
                      <el-row>
                        <el-col :span="12">
                          <div>{{ noticeInfo.text }}</div>
                        </el-col>
                        <el-col :span="12">
                          <div></div>
                        </el-col>
                      </el-row>
                    </el-collapse-item>
                  </el-collapse>
                  <span></span>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="12">
          <el-card>
            <el-calendar v-model="calendar">
            </el-calendar>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
  </div>

</template>

<script>
import { mapGetters } from 'vuex'
import roomApi from '@/api/room'
import noticeApi from '@/api/notice'

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
    },
    option() {
      return {
        title: {
          text: '宾馆数据图',
          subtext: '今日状态',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: '宾馆房间数据',
            type: 'pie',
            radius: '50%',
            data: [
              { value: this.tableData.eroom, name: '未开房间' },
              { value: this.tableData.oroom, name: '已开房间' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
    }
  },
  data() {
    return {
      noticeInfos: [],
      calendar: new Date(),
      tableData: {
        oroom: null,
        eroom: null
      }
    }
  },
  methods: {
    formatDate(dateArray) {
      if (!dateArray || dateArray.length !== 6) {
        return ''
      }
      const [year, month, day, hour, minute, second] = dateArray
      const date = new Date(year, month - 1, day, hour, minute, second)
      const formattedDate =
        `${date.getFullYear()}年
        ${this.padZero(date.getMonth() + 1)}月${this.padZero(date.getDate())}日${this.padZero(date.getHours())}:${this.padZero(date.getMinutes())}:${this.padZero(date.getSeconds())}`
      return formattedDate
    },
    padZero(value) {
      return value < 10 ? '0' + value : value
    },
    getRoomInfo() {
      roomApi.getRoomInfo().then(rep => {
        this.tableData = rep.data
      })
    },
    getNoticeForDashboard() {
      noticeApi.getNoticeForDashboard().then(rep => {
        this.noticeInfos = rep.data
      })
    }
  },
  created() {
    this.getRoomInfo()
    this.getNoticeForDashboard()
  },
  mounted() {
    setInterval(() => {
      this.getNoticeForDashboard()
    }, 10000)
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

<style type="text/css">
.Notice li{
  margin-left: -4%;
  padding: 10px 0;
  list-style-type: none;
  border-top: solid #e7eaec 1px;
}
.Notice li:first-of-type{
  border-top: none;
}

</style>
