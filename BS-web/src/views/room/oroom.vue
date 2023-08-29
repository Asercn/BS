<template>
  <el-main>
    <h3>房间状态</h3>
    <el-divider/>
    <el-card>
      <el-input v-model="searchModel.roomname" placeholder="房间号" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
      <el-button @click="getRoomList" type="primary">查询</el-button>
    </el-card>
    <el-card class="oroom_body example-pagination-block">
      <!-- Form -->
      <el-dialog :title="customerRoomTitle" :visible.sync="dialogFormVisible" @close="clearForm()" width="35rem">
        <el-form :model="customerRoomForm" >
          <el-form-item label="姓名:" :label-width="formLabelWidth" >
            <el-input v-model="customerRoomForm.customerName" autocomplete="off" style="width: 10rem;"></el-input>
          </el-form-item>
          <el-form-item label="性别:" :label-width="formLabelWidth">
            <el-select v-model="sex" placeholder="请选择" style="width: 6rem;">
              <el-option
                v-for="item in sexs"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="手机号:" :label-width="formLabelWidth">
            <el-input v-model="customerRoomForm.customerIdNumber" autocomplete="off" style="width: 15rem;"></el-input>
          </el-form-item>
          <el-form-item label="身份证号:" :label-width="formLabelWidth">
            <el-input v-model="customerRoomForm.customerIdNumber" autocomplete="off" style="width: 15rem;"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
      <template>
        <el-button class="r el-icon-s-home" v-for="(v,i) in orooms" :key="i" @click="openRoomUI(v.roomName)">{{ v.roomName }}</el-button>
      </template>
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

export default {
  data() {
    return {
      sex: null,
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
        customerName: null,
        customerIdNumber: null
      },
      customerRoomTitle: null,
      dialogFormVisible: false,
      formLabelWidth: '7rem',
      total: 0,
      orooms: [],
      searchModel: {
        roomname: null,
        pageNo: 1,
        pageSize: 10
      }
    }
  },
  methods: {
    openRoomUI(roomname) {
      this.customerRoomTitle = '房间:' + roomname
      this.dialogFormVisible = true
    },
    clearForm() {
      this.customerRoomForm = {}
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
</style>
