<template>
<el-form :model="user" :rules="rules" ref="userFormRef" label-width="80px">
  <el-form-item label="用户名称" prop="username">
    <el-input v-model="user.username"></el-input>
  </el-form-item>
  <el-form-item label="联系电话" prop="phone">
    <el-input v-model="user.phone"></el-input>
  </el-form-item>
  <el-form-item label="邮 箱" prop="email">
    <el-input v-model="user.email"></el-input>
  </el-form-item>
<el-form-item>
  <el-button type="primary" @click="submit">保 存</el-button>
</el-form-item>
</el-form>
</template>

<script>
import { updateUser } from '@/api/user'
export default {
  props: {
    user: {}
  },
  data() {
    const validateNumber = (rule, value, callback) => {
      if (value < 9999999999 || value > 100000000000) {
        callback(new Error('请输入11位数字'))
      } else {
        callback()
      }
    }
    return {
      rules: {
        username: [
          { required: true, message: '用户昵称不能为空', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          { validator: validateNumber, trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱不能为空', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱格式', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submit() {
      this.$refs.userFormRef.validate(valid => {
        if (valid) {
          updateUser(this.user).then(rep => {
            this.$message({
              message: rep.message,
              type: 'success'
            })
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
