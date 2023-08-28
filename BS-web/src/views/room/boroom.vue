<template>
<el-main>
  <h3>开房记录</h3>
  <el-divider></el-divider>
<!--  数据-->
  <el-card>
    <el-input v-model="searchModel.roomName" placeholder="房间号" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
    <el-input v-model="searchModel.customerName" placeholder="姓名" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
    <el-input v-model="searchModel.customerPhone" placeholder="电话" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
    <el-button @click="getCustomer" type="primary">查询</el-button>
  </el-card>
  <el-card>
    <el-table :data="customerInfo">
      <el-table-column type="index" label="#" width="80" align="left"></el-table-column>
      <el-table-column prop="name" label="姓名" width="150"></el-table-column>
      <el-table-column prop="phone" label="手机号" width="180"></el-table-column>
      <el-table-column prop="id_number" label="身份证号" width="200"></el-table-column>
      <el-table-column prop="room_name" label="房间号" width="150"></el-table-column>
      <el-table-column prop="start_date" label="入住时间"></el-table-column>
      <el-table-column prop="end_date" label="退房时间" ></el-table-column>
      <el-table-column prop="room_price" label="支付金额" ></el-table-column>
      <el-table-column label="#" align="right"></el-table-column>
    </el-table>
<!--    分页栏-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo"
      :page-size="searchModel.pageSize"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </el-card>
</el-main>
</template>

<script>
import customerApi from '@/api/customer'
export default {
  data() {
    return {
      total: 3,
      customerInfo: [],
      searchModel: {
        pageNo: 1,
        pageSize: 5,
        roomName: null,
        customerName: null,
        customerPhone: null
      }
    }
  },
  methods: {
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getCustomer()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getCustomer()
    },
    getCustomer() {
      customerApi.getcustomer(this.searchModel).then(rep => {
        this.customerInfo = rep.data.customerInfo
        this.total = rep.data.total
      })
    }
  },
  created() {
    this.getCustomer()
  }
}
</script>

<style scoped>

</style>
