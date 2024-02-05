<template>
<el-row>
  <el-col>
    <el-form ref="passwordRef" :rules="rules" :model="user" label-width="80px">
      <el-form-item label="旧密码" prop="oldPassword">
        <el-input v-model="user.oldPassword" placeholder="请输入旧密码" type="password" show-password></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input  v-model="user.newPassword" placeholder="请输入新密码" type="password" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPassword">
        <el-input  v-model="user.checkPassword" placeholder="请再次输入新密码" type="password" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">确 定</el-button>
        <el-button @click="reset">重 置</el-button>
      </el-form-item>
    </el-form>
  </el-col>
</el-row>
</template>

<script>
import { mapGetters } from "vuex";
import { updatePassWord } from '@/api/user'

export default {
  computed: {
    ...mapGetters([
      'id'
    ])
  },
  data() {
    const validateNewPassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码不能小于6位'))
      } else if (value.length >= 100) {
        callback(new Error('密码不能大于100位'))
      } else {
        callback()
      }
    }
    const validateCheckPassword = (rule, value, callback) => {
      if (value !== this.user.newPassword) {
        callback(new Error('两次密码输入不一致'))
      } else {
        callback()
      }
    }
    return {
      user: {
        id: null,
        oldPassword: null,
        newPassword: null,
        checkPassword: null
      },
      rules: {
        oldPassword: [
          { required: true, message: '旧密码不能为空', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, trigger: 'blur', message: '密码不能为空' },
          { trigger: 'blur', validator: validateNewPassword }
        ],
        checkPassword: [
          { required: true, trigger: 'blur', validator: validateCheckPassword }
        ]
      }
    }
  },
  methods: {
    submit() {
      this.$refs.passwordRef.validate(valid => {
        if (valid) {
          // 通过前端验证后，将数据传入后台，判断旧密码是否和输入的旧密码相同
          // 相同则对密码进行修改，否则传回旧密码错误的消息
          updatePassWord(this.id, this.user.oldPassword, this.user.newPassword).then(rep => {
            this.$confirm('密码修改成功,需要重新登入', {
              confirmButtonText: '确定',
              callback: action => {
                this.$message({
                  message: rep.message,
                  type: 'success'
                })
              }
            })
            this.logout()
          })
        }
      })
    },
    reset() {
      this.$refs.passwordRef.resetFields()
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    }
  }
}
</script>

<style scoped>

</style>
