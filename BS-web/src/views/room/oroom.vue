<template>
  <el-main>
    <h3>房间状态</h3>
    <el-divider/>
    <el-card>
      <el-input v-model="searchModel.roomname" placeholder="房间号" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
      <el-button @click="getRoomList" type="primary">查询</el-button>
    </el-card>
    <el-card class="oroom_body example-pagination-block">
      <!-- dialog -->
      <el-dialog :title="customerRoomTitle" :visible.sync="dialogFormVisible" @close="clearForm()" width="35rem">
        <el-form :model="customerRoomForm" :rules="rules" ref="customerRoomFormRef">
          <el-form-item label="姓名:" :label-width="formLabelWidth" prop="customerName">
            <el-input v-model="customerRoomForm.customerName" autocomplete="off" style="width: 10rem;"></el-input>
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
            <el-input v-model.number.trim="customerRoomForm.customerPhone" autocomplete="off" style="width: 15rem;"></el-input>
          </el-form-item>
          <el-form-item label="身份证号:" :label-width="formLabelWidth" prop="customerIdNumber">
            <el-input v-model="customerRoomForm.customerIdNumber" autocomplete="off" style="width: 15rem;"></el-input>
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
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="resetForm()">重 置</el-button>
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveCustomerRoomForm()">确 定</el-button>
        </div>
      </el-dialog>
      <el-button class="r el-icon-s-home" v-for="(v,i) in orooms" :class="setRoomState(v.roomId) " :key="i" @click="openRoomUI(v)">{{ v.roomName }}</el-button>
      <!--        分页-->
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
import roomApi from '@/api/room'
import customerroomApi from "@/api/customerroom"
import customerApi from "@/api/customer";

export default {
  data() {
    const validateStartDate = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('选择入住日期'))
      } else {
        if (this.customerRoomForm.endDate !== null) {
          this.$refs.customerRoomFormRef.validateField('endDate')
        }
        callback()
      }
    }
    const validateEndDate = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('选择退房日期'))
      } else if (value < this.customerRoomForm.startDate) {
        callback(new Error('退房住日期不能比入住日期早!'))
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
    return {
      rules: {
        customerName: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        sex: { required: true, message: '请选择性别', trigger: 'blur' },
        customerPhone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' },
          { validator: validateNumber, trigger: 'blur' },
        ],
        customerIdNumber: [
          { required: true, message: '请输入身份证号', trigger: 'blur' },
          { min: 18, max: 18, message: '身份证号为18位，请检查', trigger: 'blur' }
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
        startDate: null,
        endDate: null,
        sex: '',
        roomId: null,
        customerName: null,
        customerIdNumber: null,
        customerPhone: null,
        roomName: null
      },
      Customer: {},
      customerRoom: {},
      customerRoomTitle: null,
      dialogFormVisible: false,
      formLabelWidth: '7rem',
      total: 0,
      orooms: [],
      searchModel: {
        roomname: null,
        pageNo: 1,
        pageSize: 10
      },
      // status: ''
    }
  },
  methods: {
    // 还不知道怎么用来改变状态
    async setRoomState(roomId) {
      try {
        // 获取到这个按钮的房间的ID
        // 查询这个房间的最大的endDate是否大于等于今天，
        // 如果是今天则为'active'，否则为'normal'
        const response = await customerroomApi.getLastCustomerRoomByRoomId(roomId)
        // console.log(response)
        if (response.data === null) {
          return 'normalState'
        }
        const customerRoom = response.data.customerRoom
        console.log('时间' + new Date(customerRoom.endDate))
        if (new Date(customerRoom.endDate) > new Date()) {
          console.log('房间已开')
          return 'activeState'
        } else {
          console.log('房间空')
          return 'normalState'
        }
      } catch (error) {
        console.log('获取信息失败', error)
        return 'normalState'
      }
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
              // 添加开房订单
              customerroomApi.addCustomerRoom(this.customerRoom).then(rep2 => {
                this.$alert(rep2.message, '提示', {
                  confirmButtonText: '确定'
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
          // customerroomApi.getCustomerRoomById(this.customerRoomForm.roomId).then(rep => {
          //
          //   // // 判断房间是否为空
          //   // if (rep.data.customerRoom.endDate >= todayDate) {  // 不为空
          //   //   this.$alert('入住失败，此房间已经开出', '提示', {
          //   //     confirmButtonText: '确定',
          //   //     callback: action => {
          //   //       this.$message({
          //   //         type: 'info',
          //   //         message: `action: ${action}`
          //   //       })
          //   //     }
          //   //   })
          //   // } else {  // 房间空
          //   //
          //   // }
          // })
        }
      })
    },
    resetForm() {
      this.$refs.customerRoomFormRef.resetFields()  // 重置表单
    },
    openRoomUI(v) {
      this.customerRoomTitle = '房间:' + v.roomName
      this.customerRoomForm.roomId = v.roomId
      this.dialogFormVisible = true
      // 返回roomId
      console.log(v.roomId)
    },
    clearForm() {
      this.customerRoomForm = {}  // 清除表单信息
      this.$refs.customerRoomFormRef.clearValidate()  // 清除表单校验结果
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
    }
  },
  // 钩子函数
  created() {
    this.getRoomList()
  },
  computed: {
    // computedClass() {
    //   return {
    //     'activeState': this.status === 'active',
    //     'normalState': this.status === 'normal'
    //   }
    // }
    // utcDateValue() {
    //   if (this.customerRoomForm.startDate) {
    //     return new Date(Date.UTC(
    //       this.customerRoomForm.startDate.getFullYear(),
    //       this.customerRoomForm.startDate.getMonth(),
    //       this.customerRoomForm.startDate.getDate())
    //     )
    //   }
    //   if (this.customerRoomForm.endDate) {
    //     return new Date(Date.UTC(
    //       this.customerRoomForm.endDate.getFullYear(),
    //       this.customerRoomForm.endDate.getMonth(),
    //       this.customerRoomForm.endDate.getDate())
    //     )
    //   }
    // }
  }
}

</script>

<style scoped>
.r {
  width: 7rem;
  height: 7rem;
  margin: 10px;
}

.r::before {
  font-size: 150%;
}

.activeState {
  background-color: green;
  color: white;
}
.normalState {
}

</style>
