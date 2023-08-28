<template>
  <div class="dashboard-container">
    <el-header>
      <h1>欢迎: {{ name }}</h1>
    </el-header>
    <!--      展示栏-->
    <el-divider/>
    <el-main>
      <el-card class="dashboard-box-card">
        <p style="font-weight: bold;">今日数据</p>
        <el-table :data="tableData">
          <el-table-column label="#" width="100"></el-table-column>
          <el-table-column label="开房(间)" prop="oroom"></el-table-column>
          <el-table-column label="退房(间)" prop="boroom"></el-table-column>
          <el-table-column label="空房(间)" prop="eroom"></el-table-column>
          <el-table-column label="#" width="100"></el-table-column>
        </el-table>
        <!--        分页-->
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="searchModel.pageNo"
          :page-sizes="[5, 10, 20, 50]"
          :page-size="searchModel.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </el-card>
    </el-main>
  </div>

</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  data() {
    return {
      oroom: null,
      boroom: null,
      eroom: null,
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 5
      }
    }
  },
  methods: {
    handleSizeChange(pageSize) {
    },
    handleCurrentChange(pageNo) {
    }
  }
}

</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
  &-box-card{
    width: auto;
  }
}

</style>
