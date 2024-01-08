<template>
<el-main>
  <h3>订单记录</h3>
  <el-divider></el-divider>
  <el-card>
    <el-input v-model="searchModel.orderNumber"  placeholder="订单号" class="iinput"></el-input>
    <el-input v-model="searchModel.date" type="date"  placeholder="日期" class="iinput" ></el-input>
    <el-button type="primary" @click="getOrderfrom"> 查 询</el-button>
    <el-button @click="reset">重 置</el-button>
  </el-card>
  <el-card>
    <h4 style="color: #f9ca24">今日收入: {{ DIncome }}元</h4>
    <el-divider></el-divider>
    <span><h5>本月收入: {{ MIncome }}元</h5>
      <h5>今年收入: {{ YIncome }}元</h5></span>

  </el-card>
  <el-card>
    <el-table :data="orderfromForm">
      <el-table-column type="index" label="#" width="200"></el-table-column>
      <el-table-column prop="orderNumber" label="订单号" width="200"></el-table-column>
      <el-table-column prop="income" label="收入(元)" width="200"></el-table-column>
      <el-table-column prop="date" label="日期" align="center">
        <template slot-scope="{ row }">
          {{ formatDate(row.date) }}
        </template>
      </el-table-column>
    </el-table>
  </el-card>
  <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="searchModel.pageNo"
    :page-size="searchModel.pageSize"
    :page-sizes="[10, 20, 50, 100]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total">
  </el-pagination>
</el-main>
</template>

<script>
import orderfromApi from "@/api/orderfrom";

export default {
  data() {
    return {
      DIncome: null,
      MIncome: null,
      YIncome: null,
      orderfromForm: [],
      total: null,
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        orderNumber: null,
        date: null
      }
    }
  },
  methods: {
    getOrderfrom() {
      orderfromApi.getOrderfrom(this.searchModel).then(rep => {
        this.total = rep.data.total
        this.orderfromForm = rep.data.rows
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.getOrderfrom()
    },
    handleCurrentChange(pageNo) {
      this.pageNo = pageNo
      this.getOrderfrom()
    },
    todayIncome() {
      orderfromApi.getIncome().then(rep => {
        this.DIncome = rep.data.DIncome
        this.MIncome = rep.data.MIncome
        this.YIncome = rep.data.YIncome
      })
    },
    formatDate(dateArray) {
      if (!dateArray || dateArray.length !== 6) {
        return ''
      }
      const [year, month, day, hour, minute, second] = dateArray
      const date = new Date(year, month - 1, day, hour, minute, second)
      const formattedDate =
        `${date.getFullYear()}年
        ${this.padZero(date.getMonth() + 1)}月
        ${this.padZero(date.getDate())}日
        ${this.padZero(date.getHours())}:
        ${this.padZero(date.getMinutes())}:
        ${this.padZero(date.getSeconds())}`
      return formattedDate
    },
    padZero(value) {
      return value < 10 ? '0' + value : value
    },
    reset() {
      this.searchModel.date = null
      this.searchModel.orderNumber = null
      this.getOrderfrom()
    }
  },
  created() {
    this.getOrderfrom()
    this.todayIncome()
  }
}
</script>

<style>
.iinput{
  width: 30vh;
  margin-right: 0.5rem;
}
</style>
