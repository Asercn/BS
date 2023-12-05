<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <ul class="list-group">
              <li class="list-group-item">
                <i class="el-icon-user-solid">用户名称</i>
                <div class="pull-right">{{ user.name }}</div>
              </li>
              <li class="list-group-item">
                <i class="el-icon-phone" >手机号码</i>
                <div class="pull-right">{{ user.id }}</div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
// import userApi from '@/api/user'

export default {
  computed: {
    ...mapGetters([
      'name',
      'token'
      // 通过token获取到用户的信息以来获取用户的资料  --2023年12月3日16:59:34
    ])
  },
  data() {
    return {
      user: {}
    }
  },
  methods: {
    getUser() {
      // userApi.getInfo(this.token).then(rep => {
      //   this.user.id = rep.data.id
      //   console.log(this.user)
      // })
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
</style>
