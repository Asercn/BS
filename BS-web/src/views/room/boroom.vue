<template>
<el-main>
  <h3>开房记录</h3>
  <el-divider></el-divider>
<!--  数据-->
  <el-card>
    <el-input v-model="searchModel.roomName" placeholder="房间号" style="width: 20vh; margin-right: 0.5rem" clearable></el-input>
    <el-input v-model="searchModel.customerName" placeholder="姓名" style="width: 20vh; margin-right: 0.5rem" clearable></el-input>
    <el-input v-model="searchModel.customerPhone" placeholder="电话" style="width: 20vh; margin-right: 0.5rem" clearable></el-input>
    <el-button @click="getCustomer" type="primary">查询</el-button>
  </el-card>
  <el-card>
    <!--    退房对话框-->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%">
      <span>确定退房嘛？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="outRoom()">确 定</el-button>
      </span>
    </el-dialog>
    <el-table :data="customerInfo">
      <el-table-column type="index" label="#" width="80" align="left"></el-table-column>
      <el-table-column prop="id" label="订单号"></el-table-column>
      <el-table-column prop="name" label="姓名" width="150"></el-table-column>
      <el-table-column prop="phone" label="手机号" width="180"></el-table-column>
      <el-table-column prop="id_number" label="身份证号" width="200"></el-table-column>
      <el-table-column prop="room_name" label="房间号" width="150"></el-table-column>
      <el-table-column prop="start_date" label="入住时间"></el-table-column>
      <el-table-column prop="end_date" label="退房时间" ></el-table-column>
      <el-table-column prop="room_price" label="支付金额" ></el-table-column>
      <el-table-column prop="room_state" label="退房操作" align="center">
        <template slot-scope="scope">
          <el-button type="danger" v-if="scope.row.room_state==1" @click="openOutRoomDialog(scope.row)">退房</el-button>
          <el-button type="info" plain disabled v-if="scope.row.room_state==0">退房</el-button>
        </template>
      </el-table-column>
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
import customerroomApi from "@/api/customerroom"
export default {
  data() {
    return {
      total: null,
      dialogVisible: false,
      customerInfo: [],
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        roomName: null,
        customerName: null,
        customerPhone: null
      },
      customForm: {}
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
      // this.searchModel.pageNo = 1
      customerApi.getcustomer(this.searchModel).then(rep => {
        this.customerInfo = rep.data.customerInfo
        this.customerInfo.map(data => {
          // console.log(new Date(data.end_date).toLocaleDateString())
          data.end_date = new Date(data.end_date).toLocaleDateString('zh-CN')
          data.start_date = new Date(data.start_date).toLocaleDateString('zh-CN')
        })
        this.total = rep.data.total
      })
    },
    openOutRoomDialog(row) {
      this.dialogVisible = true
      this.customForm.id = row.id
    },
    outRoom() {
      this.searchModel.pageNo = 1
      customerroomApi.outRoom(this.customForm).then(rep => {
        console.log('退房成功')
        this.$alert(rep.message, '提示', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'success',
              message: `提示: ${rep.message}`
            })
          }
        })
        this.dialogVisible = false
        this.getCustomer()
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
