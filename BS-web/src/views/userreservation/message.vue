<template>
  <div>
    <el-container>
      <!-- 留言板列表 -->
      <el-main>
        <el-card>
          <div slot="header">
            <el-input v-model="searchText" placeholder="搜索留言"></el-input>
          </div>
        </el-card>
        <el-card style="height: 400px; overflow: auto">
          <el-timeline>
            <el-timeline-item v-for="(message, i) in filteredMessages" :key="i" :timestamp="formatDate(message.releaseTime)">
              <span><h3>{{ message.title }}</h3></span>
              {{ message.text }}
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-main>

      <!-- 留言统计图表 -->
      <el-aside width="600px">
        <el-card>
          <div slot="header">留言统计</div>
          <e-charts style="height: 440px;" :option="chartOptions"></e-charts>
        </el-card>
      </el-aside>
    </el-container>

    <!-- 输入留言 -->
    <el-container>
      <el-main>
        <el-card>
          <div slot="header">发表留言</div>
          <el-form :model="newMessage" ref="newMessageForm" label-width="80px" :rules="rules">
            <el-form-item label="标题" prop="title">
              <el-input v-model="newMessage.title"/>
            </el-form-item>
            <el-form-item label="留言内容" prop="text">
              <el-input v-model="newMessage.text" type="textarea" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitMessage">提交留言</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import noticeApi from '@/api/notice'

export default {
  data() {
    const validateTitle = (rule, value, callback) => {
      if (value.length > 50) {
        callback(new Error('标题不能大于50个字'))
      } else {
        callback()
      }
    }
    return {
      searchText: '',
      messages: [],
      newMessage: {},
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'change' },
          { validator: validateTitle, trigger: 'blur' }
        ],
        text: [
          { required: true, message: '请输入文字', trigger: 'change' }
        ]
      },
      messageCount: []
    }
  },
  computed: {
    chartOptions() {
      return {
        xAxis: {
          type: 'category',
          data: ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.messageCount,
            type: 'line'
          }
        ]
      }
    },
    filteredMessages() {
      if (this.searchText !== '' || this.searchText === null) {
        return this.messages.filter((message) =>
          message && message.title && message.text && message.releaseTime &&
          (message.title.includes(this.searchText) || message.text.includes(this.searchText) || message.releaseTime.includes(this.searchText))
        )
      } else {
        // 如果搜索文本为空，返回所有留言
        return this.messages
      }
    },
    ...mapState(['user'])
  },
  methods: {
    transform() {
      // 计算每个消息发布时间对应的星期
      const weekCounts = Array(7).fill(0) // 初始化一个长度为7的数组，用于存储每个星期的消息数量

      this.messages.forEach(message => {
        const releaseTimeArray = message.releaseTime // 假设 releaseTime 是一个包含日期信息的数组
        const releaseTime = new Date(releaseTimeArray[0], releaseTimeArray[1] - 1, releaseTimeArray[2], releaseTimeArray[3], releaseTimeArray[4], releaseTimeArray[5])
        const dayOfWeek = releaseTime.getDay() // 获取星期的数字（0-6）
        weekCounts[dayOfWeek] += 1 // 将对应星期的消息数量加1
      })

      // 现在 weekCounts 数组中存储了每个星期的消息数量，可以将其赋值给 this.messageCount
      this.messageCount = weekCounts
    },
    formatDate(dateArray) {
      if (!dateArray || dateArray.length !== 6) {
        return ''
      }
      const [year, month, day, hour, minute, second] = dateArray
      const date = new Date(year, month - 1, day, hour, minute, second)
      const formattedDate =
        `${date.getFullYear()}年${this.padZero(date.getMonth() + 1)}月${this.padZero(date.getDate())}日${this.padZero(date.getHours())}:${this.padZero(date.getMinutes())}:${this.padZero(date.getSeconds())}`
      return formattedDate
    },
    padZero(value) {
      return value < 10 ? '0' + value : value
    },
    getMessage() {
      noticeApi.getNoticeForDashboard().then(rep => {
        this.messages = rep.data
        this.transform()
      })
    },
    submitMessage() {
      this.$refs.newMessageForm.validate(validate => {
        if (validate) {
          noticeApi.addNotice(this.newMessage).then(rep => {
            this.$message({
              message: rep.message,
              type: "success"
            })
            this.$refs.newMessageForm.resetFields()
          })
        }
      })
    }
  },
  mounted() {
    setInterval(() => {
      this.getMessage()
      this.transform()
    }, 10000)
  },
  created() {
    this.getMessage()
  }
}
</script>

<style>
/* 根据需要添加样式 */
</style>
