<template>
<el-form :model="user" :rules="rules" ref="userFormRef" label-width="80px">
  <el-form-item label="用户名称" prop="username">
    <el-input v-model="user.username"></el-input>
  </el-form-item>
  <el-form-item label="联系电话" prop="phone">
    <el-input v-model.number="user.phone"></el-input>
  </el-form-item>
  <el-form-item label="邮 箱" prop="email">
    <el-input v-model="user.email"></el-input>
  </el-form-item>
<el-form-item>
  <el-button type="primary" @click="submit">保 存</el-button>
  <el-button @click="reset">重 置</el-button>
</el-form-item>
</el-form>
</template>

<script>
import { getUserInfoOrByUserID, updateUser } from '@/api/user'

export default {
  props: {
    user: {}
  },
  data() {
    const validateUsername = (rule, value, callback) => {
      const regex = /^[a-zA-Z0-9_]+$/
      if (!regex.test(value)) {
        callback(new Error('用户名只允许字母、数字或下划线'))
      } else if (value.length < 2) {
        callback(new Error('用户名不能小于2位'))
      } else if (value.length >= 20) {
        callback(new Error('用户名长度限制小于20'))
      } else {
        callback()
      }
    }
    const validatePhone = (rule, value, callback) => {
      if (value < 9999999999 || value > 100000000000) {
        callback(new Error('电话号码长度只能为11'))
      } else {
        callback()
      }
    }
    return {
      userInfo: {
        username: '',
        email: '',
        phone: null
      },
      rules: {
        username: [
          { required: true, trigger: 'blur', message: '用户名不能为空' },
          { trigger: 'blur', validator: validateUsername },
          { trigger: 'blur', validator: this.checkUsername }
        ],
        phone: [
          { type: 'number', message: '电话号码只能为数字', trigger: 'blur' },
          { required: true, message: '电话号码不能为空', trigger: 'blur' },
          { trigger: 'blur', validator: validatePhone }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    getUserInfo() {
      getUserInfoOrByUserID(this.user.id).then(rep => {
        this.userInfo = rep.data
      })
    },
    checkUsername(rule, value, callback) {
      console.log(this.user.id)
      // 调用后端API检查用户名是否存在
      this.$axios.get('/user/checkUser', { params: { username: value, userId: this.user.id }})
        .then(rep => {
          if (rep.data.data) {
            callback(new Error('用户已存在'))
          } else {
            callback()
          }
        })
    },
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
    },
    reset() {
      this.$refs.userFormRef.resetFields()
    }
  },
  // created() {
  //   this.getUserInfo()
  // }
}
</script>

<style scoped>

</style>
