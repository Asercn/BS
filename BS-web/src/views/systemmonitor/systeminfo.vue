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
          <el-card class="myCard">
            <el-col :span="14">
              <span><i class="el-icon-receiving"></i> 磁盘使用情况</span>
              <el-divider></el-divider>
              <ul style="list-style-type: none;">
                <li v-for="(Disr, index) in this.systemInfo.serverDisr" :key="index">
                  <span>{{ Disr.disrName }}盘</span>
                  <el-progress :text-inside="true" :percentage="useDisr(Disr.freeSpace, Disr.totalSpace)"  v-if="!isNaN(useDisr(Disr.freeSpace, Disr.totalSpace))" :color="colors" :stroke-width="strokeWidth"></el-progress>
                </li>
              </ul>
            </el-col>
            <el-col :span="10" style="border-left: solid 1px #e7eaec;" class="myCard">
              <e-charts style="height: 300px;" :option="diskOption"></e-charts>
            </el-col>
          </el-card>
          <el-col :span="24">
            <el-card>
              <span><i class="el-icon-odometer"></i> 内存信息</span>
              <el-divider></el-divider>
              <e-charts style="height: 300px;" :option="lineOption"></e-charts>
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
import axios from 'axios'

export default {
  data() {
    return {
      systemInfo: {
        serverDisr: []
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
      memoryData: [],
      runtimeData: [1]
    }
  },
  methods: {
    getServerInfo() {
      monitorApi.getServerInfo().then(rep => {
        this.systemInfo = rep.data
      })
    },
    useDisr(freeSpace, totalSpace) {
      const useSpace = totalSpace - freeSpace
      return parseInt((useSpace / totalSpace * 100).toFixed(0))
    }
  },
  mounted() {
    // 每隔一秒钟获取一次数据
    setInterval(() => {
      this.getServerInfo()
      const num = this.runtimeData[this.runtimeData.length - 1] + 1
      this.runtimeData.push(num)
      this.memoryData.push((this.systemInfo.totalMemory - this.systemInfo.freeMemory) / 1024 / 1024)
      // 检查数组长度，如果超过10个元素，移除第一个元素
      if (this.memoryData.length > 10) {
        this.runtimeData.shift()
        this.memoryData.shift()
      }

    }, 1000)
  },
  computed: {
    diskOption() {
      return {
        title: {
          text: '硬盘剩余空间(GB)'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '硬盘剩余空间(GB)',
            type: 'pie',
            radius: ['40%', '60%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: this.systemInfo.serverDisr.map(d => ({ value: (d.freeSpace / 1024 / 1024 / 1024).toFixed(0), name: d.disrName + '盘' }))
          }
        ]
      }
    },
    lineOption() {
      return {
        title: {
          text: '内存使用量'
        },
        xAxis: {
          type: 'category',
          name: '时间',
          data: this.runtimeData

        },
        yAxis: {
          type: 'value',
          name: '使用内存(MB)'
        },
        series: [
          {
            data: this.memoryData,
            type: 'line',
            areaStyle: {},
            animation: {
              type: 'linear',
            }
          }
        ]
      }
    }
  },
  created() {
    this.getServerInfo()
  }
}
</script>

<style scoped>
.myCard{
  margin: 15px 0;
}

table td:not(:last-child) {
  margin-right: 10px;
}

</style>
