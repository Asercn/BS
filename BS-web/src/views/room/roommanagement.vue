<template>
  <el-main>
    <h3>房间设置</h3>
    <el-divider/>
    <el-card>
      <el-input v-model="searchModel.roomname" placeholder="房间号" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
      <el-button @click="getRoomList" type="primary">查询</el-button>
      <el-button @click="reset">重 置</el-button>
    </el-card>
    <el-card>
      <el-row class="buttonRow">
        <!--        dialog-->
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
            <el-form-item label="房间图片" :label-width="formLabelWidth" prop="roomPicture">
              <el-image style="height: 300px;width: 300px;" :src="'/home_images/' + roomForm.roomPicture"></el-image>
              <el-upload
                action="http://localhost:9998/room/upImage"
                :on-success="handle_success"
                :show-file-list="false"
                ref="imageUpload"
                :before-upload="beforeAvatarUpload">
              <el-button>上传图片</el-button>
<!--                <i class="el-icon-plus"></i>-->
              </el-upload>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveRoomForm()">确 定</el-button>
          </div>
        </el-dialog>

        <el-button type="primary" icon="el-icon-plus" circle @click="openEditUI(null)"></el-button>  <!-- 新增按钮-->
      </el-row>
      <el-divider/>
      <el-table :data="roomData">
        <el-table-column type="index" label="#" align="left" width="180px"></el-table-column>
        <el-table-column label="房间照片" align="center">
          <template scope="scope">
            <el-image style="width: 100px;height:100px;" :src="'/home_images/'+ scope.row.roomPicture" alt=""></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="roomName" label="房间名" align="center"></el-table-column>
        <el-table-column prop="roomPrice" label="房价"></el-table-column>
        <el-table-column prop="roomType" label="房间类型" align="center"></el-table-column>
        <el-table-column label="操作"  width="250"  align="center">
          <template slot-scope="scope">
            <el-button type="warning" icon="el-icon-edit" size="mini" circle @click="openEditUI(scope.row.roomId)"></el-button>  <!-- 编辑按钮-->
            <el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteRoom(scope.row)"></el-button>  <!-- 删除按钮-->
          </template>
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
        roomPicture: ''
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
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      const isImage = file.type.startsWith('image/')
      console.log(file.type)
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      if (!isImage) {
        this.$message.error('只能上传图片文件')
      }
      return isLt2M && isImage
    },
    handle_success(res) {
      console.log(res.data)
      return (this.roomForm.roomPicture = res.data)
    },
    getRoomList() {
      this.searchModel.pageNo = 1
      console.log(this.searchModel.roomname)
      roomApi.getRoom(this.searchModel).then(rep => {
        this.roomData = rep.data.rows
        this.total = rep.data.total
      })
    },
    deleteRoom(room) {
      this.$confirm(`删除房间:${room.roomName}, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        roomApi.deleteRoom(room.roomId).then(rep => {
          this.$message({
            type: 'success',
            message: rep.message
          })
          this.getRoomData()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    saveRoomForm() {  // 表单验证
      this.$refs.roomFormref.validate(vaild => {
        if (vaild) {  // 验证通过
          console.log('图片名:' + this.roomForm.roomPicture)
          // 提交后台
          roomApi.saveRoom(this.roomForm).then(rep => {
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
    openEditUI(id) {
      if (id == null) {
        this.roomTotal = '新增房间'
      } else {
        this.roomTotal = '修改房间信息'
        // 根据房间ID查询房间信息
        roomApi.getRoomById(id).then(rep => {
          this.roomForm = rep.data
        })
      }
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
    },
    reset() {
      this.searchModel.roomname = null
      this.getRoomData()
    }
  },
  created() {
    this.getRoomData()
  },
  computed: {

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
