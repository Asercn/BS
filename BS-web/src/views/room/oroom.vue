<template>
  <el-main>
    <h3>订 房</h3>
    <el-divider/>
    <el-card>
      <el-input v-model="searchModel.roomname" placeholder="房间号" style="width: 20vh; margin-right: 0.5rem" clearable></el-input>
      <el-button @click="getRoomList1" type="primary">查询</el-button>
      <el-button @click="reset">重 置</el-button>
    </el-card>
    <el-card class="oroom_body example-pagination-block">
      <!-- 开房dialog -->
      <el-dialog :title="customerRoomTitle" :visible.sync="dialogFormVisible" @close="clearForm()" width="50rem">
        <el-form :model="customerRoomForm" :rules="rules" ref="customerRoomFormRef">
         <el-row>
           <el-col :span="12">
             <el-form-item label="姓名:" :label-width="formLabelWidth" prop="customerName">
               <el-input v-model="customerRoomForm.customerName" autocomplete="off" style="width: 10rem;" placeholder="请输入姓名"></el-input>
             </el-form-item>
             <el-form-item label="性别:" :label-width="formLabelWidth" prop="sex">
               <el-select v-model="customerRoomForm.sex" placeholder="请选择性别" style="width: 10rem;">
                 <el-option
                   v-for="item in sexs"
                   :key="item.value"
                   :label="item.label"
                   :value="item.value">
                 </el-option>
               </el-select>
             </el-form-item>
             <el-form-item label="手机号:" :label-width="formLabelWidth" prop="customerPhone">
               <el-input v-model.number.trim="customerRoomForm.customerPhone" autocomplete="off" style="width: 15rem;" placeholder="请输入手机号"></el-input>
             </el-form-item>
             <el-form-item label="身份证号:" :label-width="formLabelWidth" prop="customerIdNumber">
               <el-input v-model="customerRoomForm.customerIdNumber" autocomplete="off" style="width: 15rem;" placeholder="请输入身份证号"></el-input>
             </el-form-item>
             <el-form-item label="入住时间:" :label-width="formLabelWidth" prop="startDate" >
               <el-date-picker
                 v-model="customerRoomForm.startDate"
                 type="date"
                 placeholder="选择日期"
                 style="width: 15rem;">
               </el-date-picker>
             </el-form-item>
             <el-form-item label="退房时间:" :label-width="formLabelWidth" prop="endDate">
               <el-date-picker
                 v-model="customerRoomForm.endDate"
                 type="date"
                 placeholder="选择日期"
                 style="width: 15rem;">
               </el-date-picker>
             </el-form-item>
           </el-col>
           <el-col :span="12">
             <el-form-item label="图片:" prop="src">
               <el-image
                 style="max-height: 300px;"
                 :src="'/home_images/' + customerRoomForm.roomPicture"
                 fit="fit"
                 alt="暂无图片"
               ></el-image>
             </el-form-item>
             <el-form-item label="房间价格:" prop="roomPrice">
               <div>{{ this.customerRoomForm.roomPrice }}</div>
             </el-form-item>
           </el-col>
         </el-row>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="resetForm()">重 置</el-button>
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveCustomerRoomForm()">订 房</el-button>
        </div>
      </el-dialog>
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
      <el-tabs type="border-card">
        <el-tab-pane label="网格风格">
          <span><h4>网格</h4></span>
          <el-row class="flex-container">
            <el-col>
              <el-button
                v-for="(v,i) in orooms"
                :key="i"
                :span="3"
                class="r el-icon-s-home"
                @click="openRoomUI(v)"
                v-if="!isRoomIdInboRoomList(v.roomId)">
                <div>房型：{{ v.roomType }}</div>
                <div>房号：{{ v.roomName }}</div>
                <div>价格: {{ v.roomPrice }}</div>
              </el-button>
              <el-button class="r el-icon-s-home activeState" @click="exitRoomUI(v)" v-else>
                <div>房型：{{ v.roomType }}</div>
                <div>房号：{{ v.roomName }}</div>
                <div>价格: {{ v.roomPrice }}</div>
              </el-button>
            </el-col>
          </el-row>

        </el-tab-pane>
        <el-tab-pane label="列表风格">
          <span><h4>列表</h4></span>
          <!--      测试部分-->
          <el-table :data="orooms">
            <el-table-column type="index" label="#"></el-table-column>
            <el-table-column prop="roomName" label="房间号" width="100" align="center"></el-table-column>
            <el-table-column prop="roomPicture" label="房间图片" align="center">
              <template slot-scope="scope">
                <el-image
                  style="max-width: 100px; max-height: 100px;"
                  :src="'/home_images/' + scope.row.roomPicture"></el-image>
              </template>
            </el-table-column>
            <el-table-column prop="roomPrice" label="房间价格" align="center"></el-table-column>
            <el-table-column prop="roomType" label="房间类型"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button size="small" @click="openRoomUI(scope.row)" type="primary" v-if="!isRoomIdInboRoomList(scope.row.roomId)">订 房</el-button>
                <el-button size="small" @click="exitRoomUI(scope.row)" type="danger" v-else>退 房</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>

      <!--        分页-->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="searchModel.pageNo"
        :page-size="searchModel.pageSize"
        :page-sizes="[30, 60, 100, 200]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-card>
  </el-main>
</template>

<script>
import roomApi from '@/api/room'
import customerroomApi from "@/api/customerroom"
import customerApi from "@/api/customer"
import orderfromApi from '@/api/orderfrom'

export default {
  data() {
    const validateStartDate = (rule, value, callback) => {
      if (value === null || value.length === 0 || value === undefined) {
        callback(new Error('选择入住日期'))
      } else {
        if (this.customerRoomForm.endDate !== null) {
          this.$refs.customerRoomFormRef.validateField('endDate')
        }
        callback()
      }
    }
    const validateEndDate = (rule, value, callback) => {
      if (value === null || value.length === 0 || value === undefined) {
        callback(new Error('选择退房日期'))
      } else if (value <= this.customerRoomForm.startDate) {
        callback(new Error('退房日期不能早于或等于入住日期!'))
      } else {
        callback()
      }
    }
    const validateNumber = (rule, value, callback) => {
      if (value < 9999999999 || value > 100000000000) {
        callback(new Error('请输入11位数字'))
      } else {
        callback()
      }
    }
    const validateIdNumber = (rule, value, callback) => {
      // 身份证号码验证正则表达式
      const idCardRegex = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
      if (!idCardRegex.test(value)) {
        callback(new Error('身份证号码格式不正确'))
      } else {
        callback()
      }
    }
    return {
      rules: {
        customerName: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        sex: { required: true, message: '请选择性别', trigger: ['change'] },
        customerPhone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' },
          { type: 'number', message: '电话号码只能为数字', trigger: 'blur' },
          { validator: validateNumber, trigger: 'blur' }
        ],
        customerIdNumber: [
          { required: true, message: '请输入身份证号', trigger: 'blur' },
          { validator: validateIdNumber, trigger: '' }
        ],
        startDate: [
          { required: true, validator: validateStartDate, trigger: 'blur' }
        ],
        endDate: [
          { required: true, validator: validateEndDate, trigger: 'blur' }
        ]
      },
      sexs: [{
        value: '男',
        label: '男'
      }, {
        value: '女',
        label: '女'
      }, {
        value: '其它',
        label: '其它'
      }],
      customerRoomForm: {
        id: null,
        startDate: null,
        endDate: null,
        sex: '',
        roomId: null,
        customerName: null,
        customerIdNumber: null,
        customerPhone: null,
        roomName: null,
        roomPrice: null
      },
      Customer: {},
      customerRoom: {},
      customerRoomTitle: null,
      dialogFormVisible: false,
      dialogVisible: false,
      formLabelWidth: '7rem',
      total: 0,
      orooms: [],
      searchModel: {
        roomname: null,
        pageNo: 1,
        pageSize: 30
      },
      boroomList: []
    }
  },
  methods: {
    getBoRoomList() {
      // 获取到已开房间的列表
      customerroomApi.getBoRoomList().then(rep => {
        this.boroomList = rep.data.boroomList
      })
    },
    isRoomIdInboRoomList(id) {
      return this.boroomList.some(item => item.roomId === id)
    },
    saveCustomerRoomForm() {
      // 获取房间名字
      this.customerRoomForm.roomName = this.customerRoomTitle
      console.log(this.customerRoomForm.roomId)
      this.$refs.customerRoomFormRef.validate(valid => {
        if (valid) {
          console.log('表单验证成功')
          // 提交后台
          this.Customer.idNumber = this.customerRoomForm.customerIdNumber
          this.Customer.name = this.customerRoomForm.customerName
          this.Customer.sex = this.customerRoomForm.sex
          this.Customer.phone = this.customerRoomForm.customerPhone
          // 传入顾客信息
          customerApi.addCustomer(this.Customer).then(rep1 => {
            // 传回顾客ID
            this.customerRoom.customerId = rep1.data.customer.id
            if (this.customerRoom.customerId != null || this.customerRoom.customerId !== '') {
              this.customerRoom.roomId = this.customerRoomForm.roomId
              this.customerRoom.startDate = this.customerRoomForm.startDate
              this.customerRoom.endDate = this.customerRoomForm.endDate
              // 添加开房记录
              customerroomApi.addCustomerRoom(this.customerRoom).then(rep2 => {
                this.$alert(rep2.message, '提示', {
                  confirmButtonText: '确定'
                })
                this.getRoomList()
                this.getBoRoomList()
                this.dialogFormVisible = false
              })
              // 添加开房订单 2023年12月12日
              roomApi.getRoomById(this.customerRoom.roomId).then(async rep => {
                const roomPrice = rep.data.roomPrice
                console.log('房间id:' + this.customerRoom.roomId)
                const date = ((this.customerRoom.endDate - this.customerRoom.startDate) / (1000 * 60 * 60 * 24))
                const income = (roomPrice * date)
                // 获取到订单的ID
                const rep1 = await customerroomApi.getCustomerRoomByRoomId(this.customerRoom.roomId)
                const order_number = rep1.data.id
                console.log(income, order_number)
                // 导入后台
                orderfromApi.addOrder(income, order_number).then(rep2 => {
                  this.$message.success('订单创建成功')
                })
              })
            } else {
              console.log('顾客信息添加失败')
              this.$alert('顾客信息添加失败', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  this.$message({
                    type: 'info',
                    message: `action: ${action}`
                  })
                }
              })
            }
          })
        }
      })
    },
    resetForm() {
      this.$refs.customerRoomFormRef.resetFields() // 重置表单
    },
    openRoomUI(v) {
      this.customerRoomTitle = '房间:' + v.roomName
      this.customerRoomForm.roomId = v.roomId
      this.customerRoomForm.roomPrice = v.roomPrice
      this.customerRoomForm.roomPicture = v.roomPicture
      this.dialogFormVisible = true
      // 返回roomId
      console.log(v.roomId)
    },
    exitRoomUI(v) {
      this.searchModel.pageNo = 1
      customerroomApi.getCustomerRoomByRoomId(v.roomId).then(rep => {
        this.customerRoom = rep.data
        console.log(this.customerRoom)
      })
      this.dialogVisible = true
    },
    async outRoom() {
      this.searchModel.pageNo = 1
      // 获取当前房间的表单

      await customerroomApi.outRoom(this.customerRoom).then(rep => {
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
      })
      this.getRoomList()
      this.getBoRoomList()
      this.getRoomList1()
    },
    reset() {
      this.searchModel.roomname = null
      this.getRoomList()
    },
    clearForm() {
      this.customerRoomForm = {} // 清除表单信息
      this.$refs.customerRoomFormRef.clearValidate() // 清除表单校验结果
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getRoomList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getRoomList()
    },
    getRoomList() {
      roomApi.getRoom(this.searchModel).then(rep => {
        this.orooms = rep.data.rows
        this.total = rep.data.total
      })
    },
    getRoomList1() {
      this.searchModel.pageNo = 1
      this.getRoomList()
    }
  },
  // 钩子函数
  created() {
    this.getRoomList()
    this.getBoRoomList()
  },
  computed: {

  }
}

</script>

<style scoped>
.flex-container{
}
.r {
  width: 10em;
  height: 10em;
  margin-bottom: 15px;
  margin-left: 0;
  margin-right: 10px;
}

.r::before {
  font-size: 120%;
}

.activeState {
  background-color: #39c5bb;
  color: white;
}

activeState:focus {
  background-color: #ffffff;
}
</style>
