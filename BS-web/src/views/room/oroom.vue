<template>
  <el-main>
    <h3>房间状态</h3>
    <el-divider/>
    <el-card>
      <el-input v-model="searchModel.roomname" placeholder="房间号" style="width: 180px; margin-right: 0.5rem" clearable></el-input>
      <el-button @click="getRoomList" type="primary">查询</el-button>
    </el-card>
    <el-card class="oroom_body example-pagination-block">
      <el-button class="r el-icon-s-home" v-for="(v,i) in orooms" :key="i">{{ v.roomName }}</el-button>
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
import roomApi from '@/api/room'

export default {
  data() {
    return {
      total: 0,
      orooms: [],
      roomname: null,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      }
    }
  },
  methods: {
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getRoomList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getRoomList()
    },
    getRoomList() {
      roomApi.getRoom(this.searchModel).then(rep => {
        this.orooms = rep.data.rows
        this.total = rep.data.total
      })
    }
  },
  // 钩子函数
  created() {
    this.getRoomList()
  }
}

</script>

<style scoped>
.r {
  width: 7rem;
  height: 7rem;
  margin: 10px;
}

.r::before {
  font-size: 150%;
}
</style>
