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
    <el-table :data="orderfromForm">
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column prop="orderNumber" label="订单号"></el-table-column>
      <el-table-column prop="income" label="收入(元)"></el-table-column>
      <el-table-column prop="date" label="日期">
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
    formatDate(dateArray) {
      if (!dateArray || dateArray.length !== 6) {
        return ''
      }
      const localDateTime = new Date(...dateArray)
      const formattedDate =
        localDateTime.getFullYear() +
        '年' +
        this.padZero(localDateTime.getMonth() + 1) +
        '月' +
        this.padZero(localDateTime.getDate()) +
        '日 ' +
        this.padZero(localDateTime.getHours()) +
        ':' +
        this.padZero(localDateTime.getMinutes())
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
  }
}
</script>

<style>
.iinput{
  width: 30vh;
  margin-right: 0.5rem;
}
</style>
