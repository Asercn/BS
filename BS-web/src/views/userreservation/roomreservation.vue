<template>
  <div>
    <div class="mySelect">
      <el-input class="myInput" placeholder="房间类型" v-model="selectInfo.roomType"></el-input>
      <el-input class="myInput" placeholder="最大房间价格" v-model="selectInfo.roomPrice" onkeyup="this.value=this.value.replace(/[^\d.]/g,'');"></el-input>
      <el-button style="color: #fad089;" @click="getRoomInfo">查询</el-button>
      <el-button style="color: #fad089;" @click="resetInput">重置</el-button>
    </div>
    <el-row :gutter="10">
      <el-col :span="12" v-for="(roomInfo, i) in roomInfos" :key="i">
        <div class="cardContainer">
          <el-card class="myCard">
            <el-row :gutter="5" type="flex">
              <el-col :span="12">
                <el-image :src="'/home_images/' + roomInfo.roomPicture" style="height: 200px;"></el-image>
              </el-col>
              <el-col>
                <div style="background-color: #faba4d; padding: 3px; color:#fefefe;"><h3>房间类型:{{ roomInfo.roomType }}</h3></div>
                <div>
                  <ul class="myList">
                    <li><span>房间号：{{ roomInfo.roomName }}</span></li>
                    <li><span style="font-weight: 800;">房间价格： {{ roomInfo.roomPrice}}</span></li>
                    <div class="myButtonContainer">
                      <el-button class="myButton" @click="opendialogForm(roomInfo)" v-if="!isRoomIdInboRoomList(roomInfo.roomId)">预定</el-button>
                      <el-button type="danger" @click="opendialogForm(roomInfo)" v-else disabled>已开</el-button>
                    </div>
                  </ul></div>
              </el-col>
            </el-row>
          </el-card>
        </div>
      </el-col>
    </el-row>

    <!-- 开房dialog -->
    <el-dialog title="预定房间" :visible.sync="dialogFormVisible" @close="reset()" width="50rem">
      <el-form :model="customerRoomForm" :rules="rules" ref="customerRoomFormRef">
        <el-row>
          <el-col :span="12">
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
        <el-button @click="reset">重 置</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveCustomerRoomForm()">订 房</el-button>
      </div>
    </el-dialog>

<!--            分页-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo"
      :page-size="searchModel.pageSize"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import roomApi from '@/api/room'
import customerApi from "@/api/customer";
import customerroomApi from "@/api/customerroom";
import orderfromApi from "@/api/orderfrom";
export default {
  name: "roomreservation",
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
      total: null,
      roomInfos: [],
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      selectInfo: {},
      dialogFormVisible: false,
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
      formLabelWidth: '7rem',
      rules: {
        customerName: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        sex: { required: true, message: '请选择性别', trigger: 'blur' },
        customerPhone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' },
          { validator: validateNumber, trigger: 'blur' }
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
      boroomList: [],
      orooms: [],
    }
  },
  methods: {
    isRoomIdInboRoomList(id) {
      return this.boroomList.some(item => item.roomId === id)
    },
    getRoomList() {
      roomApi.getRoom(this.searchModel).then(rep => {
        this.orooms = rep.data.rows
        this.total = rep.data.total
      })
    },
    getBoRoomList() {
      // 获取到已开房间的列表
      customerroomApi.getBoRoomList().then(rep => {
        this.boroomList = rep.data.boroomList
      })
    },
    opendialogForm(roomInfo) {
      this.customerRoomForm.roomId = roomInfo.roomId
      this.customerRoomForm.roomPicture = roomInfo.roomPicture
      this.customerRoomForm.roomPrice = roomInfo.roomPrice
      this.dialogFormVisible = true
    },
    reset() {
      this.$refs.customerRoomFormRef.resetFields() // 重置表单
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
    resetInput() {
      this.searchModel.pageNo = 1
      this.selectInfo = {}
      this.getRoomInfo()
    },
    getRoomInfo() {
      roomApi.userGetRoom(this.searchModel, this.selectInfo).then(rep => {
        this.roomInfos = rep.data.roomData
        this.total = rep.data.total
      })
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getRoomInfo()
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getRoomInfo()
    }

  },
  created() {
    this.getRoomInfo()
    this.getRoomList()
    this.getBoRoomList()
  }
}
</script>

<style scoped>

.myCard{
  margin: 10px 0;
}
.myList{
  list-style-type: none;
  padding-left: 0;
}
.myList li{
  border-bottom: 1px solid #ffeccc;
  padding: 10px 0;
}
.myList li:last-of-type{
  border-bottom: none;
}
.myButtonContainer{
  width: 100%;
  text-align: right;
}
.myButton{
  background-color: #faba4d;
  border: #faba4d 1px solid;
  color: #ffffff;
  padding: 12px 20px;
}
.myButton:hover{
  background-color: #fad089;
  border: #fad089 1px solid;
}
.mySelect{
  background-color: #faba4d;
  padding: 10px;
}
.myInput{
  width: 150px;
  margin: 0 2px;

}

</style>
