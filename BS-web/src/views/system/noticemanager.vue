<template>
  <div>
    <el-container>
      <el-header>
        <h2>公告管理</h2>
      </el-header>
    </el-container>
    <el-main>
      <el-divider></el-divider>
      <el-card>
        <el-input v-model="searchModel.title" placeholder="公告标题" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
        <el-button @click="SelectNotice" type="primary">查询</el-button>
        <el-button @click="reset">重置</el-button>
      </el-card>
      <el-card>
        <el-button type="primary" icon="el-icon-plus" circle @click="openDialogUI(null)"></el-button>  <!-- 新增按钮-->
        <!--        dialog-->
        <el-dialog @close="clearFrom" :title="noticeTitle" :visible.sync="dialogFormVisible" width="30rem">
          <el-form :model="noticeForm" :rules="rules" ref="roleFormref">
            <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
              <el-input v-model="noticeForm.title" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="文字" :label-width="formLabelWidth" prop="text">
              <el-input v-model="noticeForm.text" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="状态" :label-width="formLabelWidth">
              <el-switch v-model="noticeForm.hidden" active-value="0" active-text="显示" active-color="#13ce66" inactive-value="1"  inactive-text="隐藏"></el-switch>
            </el-form-item>
          </el-form>
          <div slot="footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveNoticeForm">确 定</el-button>
          </div>
        </el-dialog>
        <el-table :data="noticeInfo">
          <el-table-column label="ID" prop="id"></el-table-column>
          <el-table-column label="标题" prop="title"></el-table-column>
          <el-table-column label="公告时间" prop="releaseTime">
            <template slot-scope="{ row }">
<!--              {{ formatDate(row.date) }}-->
              {{  formatDate(row.releaseTime) }}
            </template>
          </el-table-column>
          <el-table-column label="文字" prop="text"></el-table-column>
          <el-table-column label="是否启用" prop="hidden">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.hidden === '0'" type="success" >显示</el-tag>
              <el-tag v-else type="info" >隐藏</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button type="warning" icon="el-icon-edit" size="mini" circle @click="openDialogUI(scope.row.id)"></el-button>  <!-- 编辑按钮-->
              <el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteNotice(scope.row.id)"></el-button>  <!-- 删除按钮-->
            </template>
          </el-table-column>
        </el-table>
      </el-card>

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
    </el-main>
  </div>
</template>

<script>
import axios from "axios";
import noticeApi from '@/api/notice'
import RoleApi from "@/api/role";

export default {
  data() {
    const validateTitle = (rule, value, callback) => {
      if (value.length > 50) {
        callback(new Error('标题不能大于50个字'))
      } else {
        callback()
      }
    }
    return {
      formLabelWidth: '5rem',
      dialogFormVisible: false,
      noticeTitle: null,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      total: null,
      noticeInfo: [],
      noticeForm: {
        hidden: '0',
        title: '',
        text: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'change' },
          { validator: validateTitle, trigger: 'blur' }
        ],
        text: [
          { required: true, message: '请输入文字', trigger: 'change' }
        ]
      },
    }
  },
  created() {
    this.getAllNotice()
  },
  methods: {
    SelectNotice() {
      this.searchModel.pageNo = 1
      this.getAllNotice()
    },
    reset() {
      this.searchModel.title = ''
      this.getAllNotice()
    },
    formatDate(dateArray) {
      if (!dateArray || dateArray.length !== 6) {
        return ''
      }
      const [year, month, day, hour, minute, second] = dateArray
      const date = new Date(year, month - 1, day, hour, minute, second)
      const formattedDate =
        `${date.getFullYear()}年${this.padZero(date.getMonth() + 1)}月${this.padZero(date.getDate())}日${this.padZero(date.getHours())}:${this.padZero(date.getMinutes())}:${this.padZero(date.getSeconds())}`
      return formattedDate
    },
    padZero(value) {
      return value < 10 ? '0' + value : value
    },
    deleteNotice(id) {
      this.$confirm(`确定删除?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        noticeApi.deletedNoticeById(id).then(rep => {
          this.$message({
            message: rep.message,
            type: "success"
          })
          this.getAllNotice()

        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
      this.dialogFormVisible = false
    },
    saveNoticeForm() {
      this.$refs.roleFormref.validate(validate => {
        if (validate) {
          noticeApi.saveNoticeForm(this.noticeForm).then(rep => {
            this.$message({
              message: rep.message,
              type: "success"
            })
            this.getAllNotice()
            this.dialogFormVisible = false
          })
        } else {
          console.log('提交失败')
          return false
        }
      })
    },
    clearFrom() {
      this.$refs.roleFormref.resetFields()
      this.noticeForm = {
        hidden: '0'
      }
    },
    async openDialogUI(id) {
      if (id === null || id === undefined) {
        this.noticeTitle = '发布公告'
      } else {
        this.noticeTitle = '修改公告'
        // 回显
        await noticeApi.getNoticeById(id).then(rep => {
          this.noticeForm = rep.data
        })
      }
      this.dialogFormVisible = true
    },
    getAllNotice() {
      // axios写法有BUG，无法渲染到VUE中
      // const params = {
      //   pageNo: this.searchModel.pageNo,
      //   pageSize: this.searchModel.pageSize
      // }
      // axios.get('/notice', { params }).then(rep => {
      //   this.noticeInfo = rep.data.noticeInfo
      //   this.total = rep.data.total
      // })

      noticeApi.getAllNotice(this.searchModel).then(rep => {
        this.total = rep.data.total
        this.noticeInfo = rep.data.noticeInfo
      })
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getAllNotice()
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getAllNotice()
    }
  }
}
</script>

<style scoped>
</style>
