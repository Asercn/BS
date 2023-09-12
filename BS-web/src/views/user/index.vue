<template>
<el-main>
  <h3>用户管理</h3>
  <el-divider/>
  <el-card>
    <!--        dialog-->
    <el-dialog @close="clearFrom" :title="userTitle" :visible.sync="dialogFormVisible" width="30rem">
      <el-form :model="userForm" :rules="rules" ref="userFormref">
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
          <el-input v-model="userForm.username" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="职位" :label-width="formLabelWidth" prop="role_desc">
          <el-select v-model="userForm.role_desc" placeholder="请选择">
            <el-option
              v-for="item in roleData"
              :key="item.roleName"
              :label="item.roleDesc"
              :value="item.roleDesc">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="邮件地址" :label-width="formLabelWidth" prop="email">
          <el-input v-model="userForm.email" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" :label-width="formLabelWidth" prop="phone">
          <el-input v-model.number="userForm.phone" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUserForm()">确 定</el-button>
      </div>
    </el-dialog>

<!--    table-->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="index" label="#" width="180"></el-table-column>
      <el-table-column prop="username" label="用户名" ></el-table-column>
      <el-table-column prop="role_desc" label="职位"></el-table-column>
      <el-table-column prop="email" label="邮件地址"></el-table-column>
      <el-table-column prop="phone" label="电话号码"></el-table-column>
      <el-table-column prop="state" label="状态" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.state == 1" type="success" >启用</el-tag>
          <el-tag v-else type="danger" >禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="warning" icon="el-icon-edit" size="mini" circle @click="openEditUI(scope.row)"></el-button>  <!-- 编辑按钮-->
          <el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteUser(scope.row)"></el-button>  <!-- 删除按钮-->
        </template>
      </el-table-column>
      <el-table-column  label="#" align="right">
      </el-table-column>
    </el-table>
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
import { getUserInfoOrByUserID, deleteUserInfoByUserID } from '@/api/user'

import roleApi from '@/api/role'
export default {
  data() {
    const validateNumber = (rule, value, callback) => {
      if (value < 9999999999 || value > 100000000000) {
        callback(new Error('请输入11位数字'))
      } else {
        callback()
      }
    }
    return {
      formLabelWidth: '5rem',
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        role_desc: [
          { required: true, message: '请选择用户职位', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { type: 'email', message: '输入正确的邮箱格式', trigger: ['blur', 'change'] }
        ],
        phone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' },
          { validator: validateNumber, trigger: ['blur'] }
        ]

      },
      userForm: {
        username: null,
        role_desc: null,
        email: null,
        phone: null
      },
      userTitle: 'title',
      dialogFormVisible: false,
      total: null,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      tableData: [],
      roleData: []
    }
  },
  computed: {
  },
  methods: {
    saveUserForm() {
      this.$refs.userFormref.validate(valid => {
        if (valid) {
          // 验证成功,提交后台
          console.log('ok')
        } else {
          // 验证失败
          console.log('error')
        }
      })
    },
    deleteUser(row) {
      this.$confirm(`删除用户:${row.username}, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 删除操作
        deleteUserInfoByUserID(row.id).then(rep => {
          this.$message({
            type: 'success',
            message: rep.message
          })
          this.getUserInfo()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    clearFrom() {
      this.userForm = {}
      this.$refs.userFormref.clearValidate()
    },
    openEditUI(row) {
      this.dialogFormVisible = true
      this.searchModel.userID = row.id
      getUserInfoOrByUserID(this.searchModel).then(rep => {
        this.userTitle = '用户:' + rep.data.userInfo[0].username
        this.userForm = rep.data.userInfo[0]
      })
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getUserInfo()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getUserInfo()
    },
    getUserInfo() {
      getUserInfoOrByUserID(this.searchModel).then(rep => {
        this.tableData = rep.data.userInfo
        this.total = rep.data.total
      })
    },
    getRoleInfo() {
      roleApi.getRoleInfo().then(rep => {
        this.roleData = rep.data
      })
    }
  },
  created() {
    this.getUserInfo()
    this.getRoleInfo()
  }
}
</script>

<style scoped>

</style>
