<template>
  <div>
    <el-container>
      <el-header>
        <h2>系统信息</h2>
      </el-header>
      <el-main>
        <el-row :gutter="10">
          <el-col :span="10">
            <el-card class="myCard">
              <span><i class="el-icon-s-platform"></i>基本信息</span>
              <el-divider></el-divider>
              <ul>
                <li><span>操作系统名称: {{systemInfo.OSName}}</span></li>
                <li><span>系统版本: {{systemInfo.OSVersion}}</span></li>
                <li><span>CPU核心数: {{systemInfo.OSArch}}</span></li>
                <li><span>主机名: {{systemInfo.hostname}}</span></li>
                <li><span>运行时间: {{ (this.systemInfo.runTime/60/60).toFixed(0) }}分钟</span></li>
              </ul>
            </el-card>
          </el-col>
          <el-col :span="14">
            <el-card class="myCard">
              <span><i class="el-icon-coffee-cup"></i> Java虚拟机信息</span>
              <el-divider></el-divider>
              <div align="center">
                <table cellspacing="30">
                  <tr>
                    <td><span>Java虚拟机名称:{{this.systemInfo.JVMName}}</span></td>
                    <td><span>Java虚拟机版本:{{this.systemInfo.JVMVersion}}</span></td>
                    <td><span>Java虚拟机供应商:{{this.systemInfo.JVMVendor}}</span></td>
                  </tr>
                  <tr>
                    <td><span>JIT编译器的名称:{{this.systemInfo.JITName}}</span></td>
                  </tr>
                  <tr>
                    <td><span>当前加载的类的数量:{{this.systemInfo.LoadedClass}}</span></td>
                    <td><span>加载的类的总数:{{this.systemInfo.TotalLoadedClass}}</span></td>
                    <td><span>已卸载的类的数量:{{this.systemInfo.UnloadedClass}}</span></td>
                  </tr>
                </table>
              </div>
            </el-card>
          </el-col>
          <el-col :span="24">
            <el-card class="myCard">
              <span><i class="el-icon-receiving"></i> 磁盘使用情况</span>
              <el-divider></el-divider>
              <ul style="list-style-type: none;">
                <li><span>C盘: <el-progress :text-inside="true" :percentage="C" v-if="!isNaN(C)" :color="colors" :stroke-width="strokeWidth"></el-progress></span></li>
                <li><span>F盘: <el-progress :text-inside="true" :percentage="F" v-if="!isNaN(F)" :color="colors" :stroke-width="strokeWidth"></el-progress></span></li>
                <li><span>G盘: <el-progress :text-inside="true" :percentage="G" v-if="!isNaN(G)" :color="colors" :stroke-width="strokeWidth"></el-progress></span></li>
              </ul>
            </el-card>
          </el-col>
          <el-col :span="24">
            <el-card>
              <span><i class="el-icon-odometer"></i> 内存信息</span>
              <el-divider></el-divider>
              <e-charts style="height: 400px;" :option="option"></e-charts>
              <ul>
                <li><span>最大可用内存: {{ this.systemInfo.maxMemory/1024/1024 }} MB</span></li>
                <li><span>总内存: {{ this.systemInfo.totalMemory/1024/1024 }} MB</span></li>
                <li><span>空闲内存: {{ (this.systemInfo.freeMemory/1024/1024).toFixed(2) }} MB</span></li>
                <li><span>系统负载: {{ this.systemInfo.sysLoadAverage }}</span></li>
              </ul>
            </el-card>
          </el-col>
        </el-row>

      </el-main>
    </el-container>
  </div>

</template>

<script>
import monitorApi from '@/api/monitor'

export default {
  data() {
    return {
      option: {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: 'line'
          }
        ]
      },
      strokeWidth: 15,
      colors: [
        { color: '#eb3b5a', percentage: 100 },
        { color: '#fc5c65', percentage: 90 },
        { color: '#fa8231', percentage: 80 },
        { color: '#fd9644', percentage: 70 },
        { color: '#f7b731', percentage: 60 },
        { color: '#20bf6b', percentage: 50 },
        { color: '#26de81', percentage: 40 },
        { color: '#0fb9b1', percentage: 30 },
        { color: '#2bcbba', percentage: 20 },
        { color: '#2d98da', percentage: 10 }
      ],
      name: 'systeminfo',
      systemInfo: {}
    }
  },
  methods: {
    getServerInfo() {
      monitorApi.getServerInfo().then(rep => {
        this.systemInfo = rep.data
      })
    }
  },
  created() {
    this.getServerInfo()
  },
  computed: {
    C() {
      const freeC = this.systemInfo['C:\\freeSpace']
      const totalC = this.systemInfo['C:\\totalSpace']
      const useC = totalC - freeC

      if (freeC === null || totalC === null || useC === null) {
        return 0
      } else {
        return parseInt((useC / totalC) * 100)
      }
    },
    F() {
      const freeF = this.systemInfo['F:\\freeSpace']
      const totalF = this.systemInfo['F:\\totalSpace']
      const useF = totalF - freeF

      if (freeF === null || totalF === null || useF === null) {
        return 0
      } else {
        return parseInt((useF / totalF) * 100)
      }
    },
    G() {
      const totalG = this.systemInfo['G:\\totalSpace']
      const freeG = this.systemInfo['G:\\freeSpace']
      const useG = totalG - freeG

      if (freeG === null || totalG === null || useG === null) {
        return 0
      } else {
        return parseInt((useG / totalG) * 100)
      }
    }
  }
}
</script>

<style scoped>
.myCard{
  margin-bottom: 15px;
}

table td:not(:last-child) {
  margin-right: 10px;
}

</style>
