<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <img :src="cat" alt="avatar" class="user-avatar">
          </div>
          <div>
            <ul class="list-group">
              <li class="list-group-item">
                <i class="el-icon-user">用户名称</i>
                <div class="pull-right">{{ user.username }}</div>
              </li>
              <li class="list-group-item">
                <i class="el-icon-phone" >手机号码</i>
                <div class="pull-right">{{ user.phone }}</div>
              </li>
              <li class="list-group-item">
                <i class="el-icon-message" >邮 箱</i>
                <div class="pull-right">{{ user.email }}</div>
              </li>
              <li class="list-group-item">
                <i class="el-icon-user-solid" >角色</i>
                <div class="pull-right">{{ user.role_desc }}</div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card >
          <div slot="header" class="clearfix">
            <span>基本资料</span>
          </div>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="基本资料" name="userInfo">
              <userInfo :user="user"/>
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="resetPwd">
              <resetPwd />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getUserInfoOrByUserID } from '@/api/user'
import myavatar from '@/icons/gif/index'
import userInfo from './userInfo'
import resetPwd from './resetPwd'

export default {
  components: {
    userInfo,
    resetPwd
  },
  computed: {
    ...mapGetters([
      'name',
      'id'
      // 通过token获取到用户的信息以来获取用户的资料  --2023年12月3日16:59:34
    ])
  },
  data() {
    return {
      activeTab: 'userInfo',
      user: {},
      ...myavatar.data()
    }
  },
  methods: {
    getUser() {
      getUserInfoOrByUserID(this.id).then(rep => {
        this.user = rep.data
      })
    }
  },
  created() {
    this.getUser()
  }
}
</script>

<style>
.list-group-striped > .list-group-item {
  border-left: 0;
  border-right: 0;
  border-radius: 0;
  padding-left: 0;
  padding-right: 0;
}

.list-group {
  padding-left: 0px;
  list-style: none;
}

.list-group-item {
  border-bottom: 1px solid #e7eaec;
  border-top: 1px solid #e7eaec;
  margin-bottom: -1px;
  padding: 11px 0px;
  font-size: 13px;
}
.pull-right {
  float: right !important;
}
.user-avatar {
  cursor: pointer;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: solid 1px;
  border-color: #f4f4f4;
}
</style>
