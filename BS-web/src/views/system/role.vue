<template>
  <el-main>
    <h3>角色管理</h3>
    <el-divider/>
    <el-card>
      <el-input v-model="searchModel.roleName" placeholder="角色名" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
      <el-button @click="getRoleList" type="primary">查询</el-button>
    </el-card>
    <el-card>
      <el-button type="primary" icon="el-icon-plus" circle @click="openEditUI(null)"></el-button>  <!-- 新增按钮-->
      <!--        dialog-->
      <el-dialog @close="clearFrom" :title="RoleTitle" :visible.sync="dialogFormVisible" width="30rem">
        <el-form :model="roleForm" :rules="rules" ref="roleFormref">
          <el-form-item label="角色名" :label-width="formLabelWidth" prop="roleName">
            <el-input v-model="roleForm.roleName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" :label-width="formLabelWidth">
            <el-input v-model="roleForm.roleDesc" autocomplete="off" class="inputWidth"></el-input>
          </el-form-item>
          <el-form-item label="权限设置" :label-width="formLabelWidth" prop="menuIdList">
            <el-tree :data="menuList" :props="menuProps" show-checkbox node-key="menuId" ref="menuRef"></el-tree>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveRoleForm()">确 定</el-button>
        </div>
      </el-dialog>

      <!--    table-->
      <el-table :data="RoleList" style="width: 100%">
        <el-table-column type="index" label="#" width="180"></el-table-column>
        <el-table-column prop="roleName" label="角色" ></el-table-column>
        <el-table-column prop="roleDesc" label="角色描述"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="warning" icon="el-icon-edit" size="mini" circle @click="openEditUI(scope.row.roleId)"></el-button>  <!-- 编辑按钮-->
            <el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteRole(scope.row)"></el-button>  <!-- 删除按钮-->
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
import RoleApi from '@/api/role'
import MenuApi from '@/api/menu'

export default {
  data() {
    return {
      menuList: [],
      menuProps: {
        children: 'children',
        label: 'title'
      },
      total: null,
      searchModel: {
        pageNo: 1,
        pageSize: 10,
        roleName: null
      },
      RoleTitle: null,
      dialogFormVisible: false,
      RoleList: [],
      roleForm: {},
      formLabelWidth: '5rem',
      rules: {
        roleName: { required: true, message: '请输入角色名', trigger: 'blur' },
        roleDesc: { required: true, message: '请输入角色描述', trigger: 'blur' }
      }
    }
  },
  methods: {
    getAllMenu() {
      MenuApi.getAllMenu().then(rep => {
        this.menuList = rep.data
      })
    },
    openEditUI(id) {
      if (id == null) {
        this.RoleTitle = '新增角色'
      } else {
        this.RoleTitle = '修改角色'
        RoleApi.getRoleInfoById(id).then(rep => {
          this.roleForm = rep.data
          this.$refs.menuRef.setCheckedKeys(rep.data.menuIdList)
        })
      }
      this.dialogFormVisible = true
    },
    getRoleList() {
      this.searchModel.pageNo = 1
      RoleApi.getRoleInfo(this.searchModel).then(rep => {
        this.RoleList = rep.data.roleInfo
        this.total = rep.data.total
      })
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getRoleList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getRoleList()
    },
    clearFrom() {
      this.roleForm = {}
      this.$refs.roleFormref.clearValidate()
      this.$refs.menuRef.setCheckedKeys([])
    },
    saveRoleForm() {  // 表单验证
      this.$refs.roleFormref.validate(vaild => {
        if (vaild) {  // 验证通过
          let checkedKeys = this.$refs.menuRef.getCheckedKeys()
          let halfKeys = this.$refs.menuRef.getHalfCheckedKeys()
          this.roleForm.menuIdList = checkedKeys.concat(halfKeys)
          // console.log(this.roleForm.menuIdList)
          // 提交后台
          RoleApi.saveRole(this.roleForm).then(rep => {
            // 提交成功提示
            this.$message({
              message: rep.message,
              type: "success"
            })
            this.dialogFormVisible = false
            this.getRoleList()
          })
        } else {  // 验证失败
          console.log('提交失败')
          return false
        }
      })
    },
    deleteRole(Form) {
      this.$confirm(`删除角色:${Form.roleName}, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        RoleApi.deleteRole(Form).then(rep => {
          // 删除成功提示
          this.$message({
            message: rep.message,
            type: "success"
          })
          this.getRoleList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  },
  created() {
    this.getRoleList()
    this.getAllMenu()
  }
}
</script>

<style scoped>

</style>
