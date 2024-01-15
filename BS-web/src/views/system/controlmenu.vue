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
    <el-button size="small" @click="openDialog">+新增</el-button>
<!--    dialog-->
    <el-dialog title="添加菜单" :visible.sync="dialogVisible" width="50%" @close="clearForm()">
      <el-form ref="menuFormRef" :model="menuForm" label-width="200px" label-position="right" :inline="true" :rules="menuFormRule">
        <el-row>
          <el-col :span="12">
            <el-form-item label="上级菜单(最上级为0)" prop="parentId"><el-input-number v-model="menuForm.parentId" :min="0"></el-input-number></el-form-item>
            <el-form-item label="新菜单名称" prop="title"><el-input v-model="menuForm.title"></el-input></el-form-item>
            <el-form-item label="name" prop="name"><el-input v-model="menuForm.name"></el-input></el-form-item>
            <el-form-item label="路径名" prop="path"><el-input v-model="menuForm.path"></el-input></el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="定向到(父级填写)" prop="redirect"><el-input v-model="menuForm.redirect"></el-input></el-form-item>
            <el-form-item label="图标" prop="icon"><el-input v-model="menuForm.icon"></el-input></el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false" >取 消</el-button>
      <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
<!--    获取MenuList-->
    <el-table
      :data="menuList"
      row-key="menuId"
      :tree-props="{children:'children'}"
    >
      <el-table-column prop="menuId" label="编号"></el-table-column>
      <el-table-column prop="title" label="菜单名称" width="300"></el-table-column>
      <el-table-column prop="icon" label="图标" width="100">
        <template slot-scope="scope">
          <i :class="scope.row.icon"></i>
          <svg-icon :icon-class="scope.row.icon"></svg-icon>
        </template>
      </el-table-column>
      <el-table-column prop="component" label="路径" ></el-table-column>
      <el-table-column prop="redirect" label="重定向"></el-table-column>
      <el-table-column prop="name" label="name"></el-table-column>
      <el-table-column label="操作" align="center">
        <el-button type="warning" size="mini" icon="el-icon-edit" circle></el-button>
        <el-button type="danger" size="mini" icon="el-icon-delete" circle></el-button>
      </el-table-column>
    </el-table>
  </el-card>

</el-main>
</template>

<script>
import menuApi from '@/api/menu'
export default {
  computed: {
  },
  data() {
    return {
      menuList: [],
      dialogVisible: false,
      menuForm: {},
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
        ],
        path: [
          { required: true, message: '请输入路径名', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    getAllMenu() {
      menuApi.getAllMenu().then(rep => {
        this.menuList = rep.data
      })
    },
    openDialog() {
      this.dialogVisible = true
    },
    clearForm() {
      this.menuForm = [] // 清楚表单信息
      this.$refs.menuFormRef.clearValidate() // 清楚表单校验结果
    }
  },
  created() {
    this.getAllMenu()
  }
}
</script>

<style scoped>

</style>
