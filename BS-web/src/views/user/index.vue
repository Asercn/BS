<template>
<el-main>
  <h3>用户管理</h3>
  <el-divider/>
  <el-card>
    <!--        修改框-->
    <el-dialog @close="clearFrom" :title="userTotal" :visible.sync="dialogFormVisible" width="30rem">
      <el-form :model="userForm" :rules="rules" ref="userFormref">
        <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
          <el-input v-model="userForm.username" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="职位" :label-width="formLabelWidth" prop="role_desc">
          <el-input v-model="userForm.role_desc" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="邮件地址" :label-width="formLabelWidth" prop="email">
          <el-input v-model="userForm.email" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" :label-width="formLabelWidth" prop="phone">
          <el-input v-model="userForm.phone" autocomplete="off" class="inputWidth"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveUserForm()">确 定</el-button>
      </div>
    </el-dialog>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="index" label="#" width="180">
      </el-table-column>
      <el-table-column prop="username" label="用户名" >
      </el-table-column>
      <el-table-column prop="role_desc" label="职位">
      </el-table-column>
      <el-table-column prop="email" label="邮件地址">
      </el-table-column>
      <el-table-column prop="phone" label="电话号码">
      </el-table-column>
      <el-table-column prop="state" label="状态" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.state == 1" type="success" >启用</el-tag>
          <el-tag v-else type="danger" >禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="warning" icon="el-icon-edit" size="mini" circle @click="openEditUI(scope.row)"></el-button>  <!-- 编辑按钮-->
          <el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteRoom(scope.row)"></el-button>  <!-- 删除按钮-->
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
import { getUserInfoOrByUserID } from '@/api/user'
import roleApi from '@/api/role'
export default {
  name: "index",
  data() {
    return {

      formLabelWidth: '5rem',
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],

      },
      userForm: [],
      userTotal: 'title',
      dialogFormVisible: false,
      total: null,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      tableData: [],
      RoleData: []
    }
  },
  methods: {
    saveUserForm() {
      this.$refs.userFormref.validate(valid => {
        if (valid) {
          // 验证成功
          console.log('ok')
        } else {
          // 验证失败
          console.log('error')
        }
      })
    },
    clearFrom() {
      this.$refs.userFormref.clearValidate()
    },
    openEditUI(row) {
      this.dialogFormVisible = true
      this.userTotal = '用户:' + row.username
      this.userForm = row
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
        this.RoleData = rep.data
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
