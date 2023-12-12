<template>
<el-main>
  <h3>订单记录</h3>
  <el-divider></el-divider>
  <el-card>
    <el-input placeholder="订单号" class="iinput"></el-input>
    <el-input placeholder="客户姓名" class="iinput"></el-input>
    <el-input placeholder="房间名" class="iinput"></el-input>
    <el-button type="primary"> 查 询</el-button>
    <el-button @click="reset">重 置</el-button>
  </el-card>
  <el-card>
    <el-form>
      <el-form-item></el-form-item>
    </el-form>
  </el-card>
  <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="searchModel.pageNo"
    :page-size="searchModel.pageSize"
    :page-sizes="[10, 20, 50, 100]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="total">
  </el-pagination>
</el-main>
</template>

<script>
export default {
  data() {
    return {
      total: null,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      }
    }
  },
  methods: {
    handleSizeChange() {},
    handleCurrentChange() {},
    reset() {}
  }
}
</script>

<style>
.iinput{
  width: 20vh;
  margin-right: 0.5rem;
}
</style>
