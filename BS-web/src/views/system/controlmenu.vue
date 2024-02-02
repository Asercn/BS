<template>
<el-main>
  <h3>菜单控制</h3>
  <el-divider/>
  <el-card>
    <el-input placeholder="菜单名称" style="width: 180px; margin-right: 0.5rem"></el-input>
    <el-button type="primary">查询</el-button>
    <el-button type="normal">重置</el-button>
  </el-card>
  <el-card>
    <el-button size="small" @click="openDialog(null)">+新增</el-button>
<!--    dialog-->
    <el-dialog :title="title" :visible.sync="dialogVisible" width="50%" @close="clearForm()">
      <el-form ref="menuFormRef" :model="menuForm" label-width="200px" label-position="right" :inline="true" :rules="menuFormRule">
        <el-row>
          <el-col :span="12">
            <el-form-item label="上级菜单(最上级为0)" prop="parentId"><el-input-number v-model="menuForm.parentId" :min="0"></el-input-number></el-form-item>
            <el-form-item label="菜单名称" prop="title"><el-input v-model="menuForm.title"></el-input></el-form-item>
            <el-form-item label="name" prop="name"><el-input v-model="menuForm.name"></el-input></el-form-item>
<!--            <el-form-item label="路径名" prop="path"><el-input v-model="menuForm.path"></el-input></el-form-item>-->
          </el-col>
          <el-col :span="12">
            <el-form-item label="定向到(父级填写)" prop="redirect"><el-input v-model="menuForm.redirect"></el-input></el-form-item>
            <el-form-item label="图标" prop="icon"><el-input v-model="menuForm.icon"></el-input></el-form-item>
            <el-form-item label="顺序" prop="number"><el-input-number v-model="menuForm.number"></el-input-number></el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false" >取 消</el-button>
      <el-button type="primary" @click="saveForm">确 定</el-button>
      </span>
    </el-dialog>
<!--    获取MenuList-->
    <el-table
      :data="menuList"
      row-key="menuId"
      :tree-props="{children:'children'}"
    >
      <el-table-column prop="menuId" label="编号"></el-table-column>
      <el-table-column prop="icon" label="图标" width="100">
        <template slot-scope="scope">
          <i :class="scope.row.icon"></i>
          <svg-icon :icon-class="scope.row.icon"></svg-icon>
        </template>
      </el-table-column>
      <el-table-column prop="number" label="顺序"></el-table-column>
      <el-table-column prop="isLeaf" label="是否子菜单" align="center">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.isLeaf=='Y'"><i class="el-icon-check"></i></el-tag>
          <el-tag type="info" v-else>否</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="title" label="菜单名称"></el-table-column>
      <el-table-column prop="name" label="name"></el-table-column>
      <el-table-column prop="component" label="路径" ></el-table-column>
      <el-table-column prop="redirect" label="重定向"></el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button @click="openDialog(scope.row.menuId)" type="warning" size="mini" icon="el-icon-edit" circle></el-button>
          <el-button @click="addDialog(scope.row.menuId)" type="primary" size="mini" icon="el-icon-plus" circle></el-button>
          <el-button @click="deleteMenu(scope.row.menuId)" type="danger" size="mini" icon="el-icon-delete" circle></el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

</el-main>
</template>

<script>
import menuApi from '@/api/menu'
import roomApi from "@/api/room";
export default {
  computed: {
  },
  data() {
    return {
      title: null,
      menuList: [],
      dialogVisible: false,
      menuForm: {
        number: 0,
      },
      selectMenu: [],
      menuFormRule: {
        parentId: [
          { required: true, message: '输入父级编号', trigger: 'change' }
        ],
        title: [
          { required: true, message: '请输入菜单名称', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入name', trigger: 'blur' }
        ]
        // path: [
        //   { required: true, message: '请输入路径名', trigger: 'blur' }
        // ]
      }
    }
  },
  methods: {
    getAllMenu() {
      menuApi.getAllMenu().then(rep => {
        this.menuList = rep.data
      })
    },
    addDialog(id) {
      this.title = '新增菜单'
      this.dialogVisible = true
      menuApi.getMenuById(id).then(rep => {
        this.menuForm.parentId = rep.data.menuId
      })
    },
    openDialog(id) {
      this.dialogVisible = true
      if (id === null || id === undefined) {
        this.title = '新增菜单'
      } else {
        this.title = '修改菜单'
        // 添加回显
        menuApi.getMenuById(id).then(rep => {
          this.menuForm = rep.data
        })
      }
    },
    clearForm() {
      this.menuForm = {} // 清楚表单信息
      this.menuForm.number = 0
      this.$refs.menuFormRef.clearValidate() // 清楚表单校验结果
    },
    saveForm() {
      this.$refs.menuFormRef.validate(valid => {
        if (valid) {
          console.log("提交后台")
          menuApi.saveMenu(this.menuForm).then(rep => {
            this.$alert(rep.message, '提示', {
              confirmButtonText: '确定',
              callback: () => {
                // 异步操作完成后再刷新页面
                this.getAllMenu()
                // 可能会改变路由状态，所以刷新页面
                this.$router.go(0)
              }
            })
          })
        }
      })
      this.dialogVisible = false
    },
    deleteMenu(ID) {
      this.$confirm(`删除编号为:${ID}的菜单, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 删除菜单
        menuApi.deleteMenu(ID).then(rep => {
          // 刷新页面
          this.getAllMenu()
          this.$message({
            type: 'success',
            message: rep.message
          })
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
    this.getAllMenu()
  }
}
</script>

<style scoped>

</style>
