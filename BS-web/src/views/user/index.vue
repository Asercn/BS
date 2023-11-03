<template>
<el-main>
  <h3>用户管理</h3>
  <el-divider/>
  <el-card>
    <el-input v-model="searchModel.username" placeholder="用户名" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
    <el-button @click="getUserInfo" type="primary">查询</el-button>
  </el-card>
  <el-card>
    <!--        dialog-->
    <el-dialog @close="clearFrom" :title="userTitle" :visible.sync="dialogFormVisible" width="30rem">
      <el-form :model="userForm" :rules="rules" ref="userFormref">
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
          <el-input v-model="userForm.username" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="职位" :label-width="formLabelWidth" prop="role_desc">
          <el-select v-model="userForm.role_id" placeholder="请选择">
            <el-option
              v-for="item in roleData"
              :key="item.roleName"
              :label="item.roleDesc"
              :value="item.roleId">
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
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="warning" icon="el-icon-edit" size="mini" circle @click="openEditUI(scope.row)"></el-button>  <!-- 编辑按钮-->
          <el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteUser(scope.row)"></el-button>  <!-- 删除按钮-->
        </template>
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
import {
  getUserInfoOrByUserID,
  deleteUserInfoByUserID,
  saveForm,
  getUserInfo,
  updateUser,
  updateUserRole
} from '@/api/user'

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
          { validator: validateNumber, trigger: ['change'] }
        ]

      },
      userForm: {
        id: null,
        username: null,
        role_desc: null,
        email: null,
        phone: null,
        role_id: null
      },
      userTitle: 'title',
      dialogFormVisible: false,
      total: null,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      tableData: [],
      roleData: [],
      userRoleForm: {
        userId: null,
        roleId: null
      }
    }
  },
  computed: {
  },
  methods: {
    saveUserForm() {
      this.$refs.userFormref.validate(valid => {
        if (valid) {
          // 验证成功,提交后台
          this.userRoleForm.userId = this.userForm.id  // 获取userid
          // 2023.9.15 预期获取userRole的id --已完成
          this.userRoleForm.roleId = this.userForm.role_id  // 获取修改后的roleID
          updateUser(this.userForm).then(rep => {
            updateUserRole(this.userRoleForm).then(rep => {
              this.$message({
                message: rep.message,
                type: 'success'
              })
              // 2023年9月15日15:01:01 出现bug 修改完成后，刷新只会显示刚刚修改过的数据 --已修复
              // 2023年9月15日16:27:39 bug:无法单独修改用户职位 --已修复
              this.getUserInfo()
            })
          })
          this.dialogFormVisible = false
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
            message: rep.message,
            type: 'success'
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
      getUserInfoOrByUserID(row.id).then(rep => {
        this.userForm = rep.data
        this.userTitle = '用户' + rep.data.username
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
      this.searchModel.pageNo = 1
      getUserInfo(this.searchModel).then(rep => {
        this.tableData = rep.data.userInfo
        this.total = rep.data.total
      })
    },
    getRole() {
      roleApi.getRole().then(rep => {
        this.roleData = rep.data
      })
    }
  },
  created() {
    this.getUserInfo()
    this.getRole()
  }
}
</script>

<style scoped>

</style>
