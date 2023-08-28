<template>
  <el-main>
    <h3>房间管理</h3>
    <el-divider/>
    <el-card>
      <el-row class="buttonRow">
        <el-button type="primary" icon="el-icon-plus" circle @click="openAddUI"></el-button>  <!-- 新增按钮-->
        <!--        新增框-->
        <el-dialog @close="clearFrom" :title="roomTotal" :visible.sync="dialogFormVisible" width="30rem">
          <el-form :model="roomForm" :rules="rules" ref="roomFormref">
            <el-form-item label="房间名" :label-width="formLabelWidth" prop="roomName">
              <el-input v-model="roomForm.roomName" autocomplete="off" class="inputWidth"></el-input>
            </el-form-item>
            <el-form-item label="房间价格" :label-width="formLabelWidth" prop="roomPrice">
              <el-input v-model.number="roomForm.roomPrice" autocomplete="off" class="inputWidth"></el-input>
            </el-form-item>
            <el-form-item label="房间类型" :label-width="formLabelWidth">
              <el-input v-model="roomForm.roomType" autocomplete="off" class="inputWidth"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveRoomForm()">确 定</el-button>
          </div>
        </el-dialog>

      </el-row>
      <el-divider/>
      <el-table :data="roomData">
        <el-table-column type="index" label="#" align="left" width="180px"></el-table-column>
        <el-table-column prop="roomName" label="房间名"></el-table-column>
        <el-table-column prop="roomPrice" label="房价"></el-table-column>
        <el-table-column prop="roomType" label="房间类型" align="center"></el-table-column>
        <el-table-column label="操作"  width="250"  align="center">
          <el-button type="warning" icon="el-icon-edit" circle></el-button>  <!-- 编辑按钮-->
          <el-button type="danger" icon="el-icon-delete" circle></el-button>  <!-- 删除按钮-->
        </el-table-column>
      </el-table>
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
      rules: {
        roomName: [
          { required: true, message: '请输入房间名', trigger: 'blur' }
        ],
        roomPrice: [
          { required: true, message: '请输入房间价格', trigger: 'blur' },
          { type: 'number', message: '房间价格只能为数字', trigger: 'blur' }
        ]
      },
      roomTotal: null,
      formLabelWidth: '7rem',
      roomForm: {
        roomName: null,
        roomPrice: null,
        roomType: null
      },
      dialogFormVisible: false,
      total: null,
      roomData: [],
      searchModel: {
        pageNo: 1,
        pageSize: 10
      }

    }
  },
  methods: {
    saveRoomForm() {  // 表单验证
      this.$refs.roomFormref.validate(vaild => {
        if (vaild) {  // 验证通过
          // 提交后台
          roomApi.addRoom(this.roomForm).then(rep => {
            // 提交成功提示
            this.$message({
              message: rep.message,
              type: "success"
            })
            this.dialogFormVisible = false
            this.getRoomData()
          })
        } else {  // 验证失败
          console.log('提交失败')
          return false
        }
      })
    },
    clearFrom() {
      this.roomForm = {}
      this.$refs.roomFormref.clearValidate()
    },
    openAddUI() {
      this.roomTotal = '新增房间'
      this.dialogFormVisible = true
    },
    openEditUI() {
      this.roomTotal = '修改房间'
      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getRoomData()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getRoomData()
    },
    getRoomData() {
      roomApi.getRoomData(this.searchModel).then(rep => {
        this.roomData = rep.data.roomData
        this.total = rep.data.total
      })
    }
  },
  created() {
    this.getRoomData()
  }
}
</script>

<style scoped>
.inputWidth{
  width: 15rem;
}
.buttonRow{
  margin: 1rem 0;
}
.buttonRow>button{
  margin-left: 1rem;
}
</style>
