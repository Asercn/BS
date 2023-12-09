<template>
  <div class="register-container">
    <el-form ref="registerRef" :model="registerForm" :rules="registerRule" class="register-form" label-position="left">

      <div class="title-container">
        <h3 class="title">注册用户</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          v-model="registerForm.username"
          placeholder="用户名"
        />
      </el-form-item>
      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          v-model="registerForm.password"
          :type="passwordType"
          placeholder="密码"
        />
      </el-form-item>
      <el-form-item prop="repassword">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          v-model="registerForm.repassword"
          :type="passwordType"
          placeholder="再次输入密码"
        />
      </el-form-item>

      <el-form-item prop="email">
        <span class="svg-container">
          <i class="el-icon-star-on"></i>
        </span>
        <el-input
          v-model="registerForm.email"
          placeholder="联系邮箱"
        />
      </el-form-item>

      <el-form-item prop="phone">
        <span class="svg-container">
          <i class="el-icon-phone"></i>
        </span>
        <el-input
          v-model.number="registerForm.phone"
          placeholder="手机号码"
        />
      </el-form-item>

      <el-button type="primary" style="width: 68%;margin-bottom: 10px;margin-right: 2%;" @click="saveRegisterForm">注册</el-button>
      <el-button style="width: 30%;margin-bottom: 10px;" @click="resetForm">重置 <i class="el-icon-refresh"></i></el-button>
      <el-button style="width: 100%;margin-bottom: 30px;" @click.prevent.native="handleLogin">返回</el-button>
    </el-form>
  </div>
</template>

<script>
import { addUser } from '@/api/user'

export default {
  name: 'Register',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (value.length < 2) {
        callback(new Error('用户名不能小于2位'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码不能小于6位'))
      } else {
        callback()
      }
    }
    const validateRePassword = (rule, value, callback) => {
      if (value !== this.registerForm.password) {
        callback(new Error('两次密码输入不一致'))
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
      registerForm: {},
      registerRule: {
        username: [
          { required: true, trigger: 'blur', message: '用户名不能为空' },
          { trigger: 'blur', validator: validateUsername },
          { trigger: 'blur', validator: this.checkUsername }
        ],
        password: [
          { required: true, trigger: 'blur', message: '密码不能为空' },
          { trigger: 'blur', validator: validatePassword }
        ],
        repassword : [
          { trigger: ['blur', 'change'], validator: validateRePassword }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '电话号码不能为空', trigger: 'blur' },
          { trigger: 'blur', validator: validatePhone },
          { type: 'number', message: '电话号码只能为数字', trigger: 'blur' }
        ]

      },
      passwordType: 'password'

    }
  },
  methods: {
    checkUsername(rule, value, callback) {
      // 调用后端API检查用户名是否存在
      this.$axios.get('/user/checkUser', { params: { username: value }})
        .then(rep => {
          if (rep.data.data) {
            callback(new Error('用户已存在'))
          } else {
            callback()
          }
        })
    },
    handleLogin() {
      this.$router.push({ path: '/login' })
    },
    resetForm() {
      this.$refs.registerRef.resetFields()
    },
    saveRegisterForm() {
      this.$refs.registerRef.validate(valid => {
        if (valid) {
          // 存入数据库
          // console.log('我在这里')
          addUser(this.registerForm).then(rep => {
            this.$message({
              message: rep.message,
              type: 'success'
            })
          })
          this.$router.push({ path: '/login' })
        } else {
          console.log('表单验证错误')
          this.$alert('注册失败', '提示', {
            confirmButtonText: '确定'
          })
        }
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.register-container {

  .el-button + .el-button{
    margin: 0px;
  }

  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style scoped lang="scss">
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
.register-container {

  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .register-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 30px 35px 0;
    margin: 0 auto;
    overflow: hidden;
    background-color: #636e72;
    border-radius: 5px;
    opacity: .9;
  }

  display: flex;
  align-items: center;

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }
}

</style>
