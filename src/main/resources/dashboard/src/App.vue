<template>
  <div id="app">
    <el-header>shepherds</el-header>
    <el-container style="height: 100%">
      <el-aside width="65px">
        <el-menu
          default-active="1-4-1"
          class="el-menu-vertical-demo"
          :collapse="isCollapse"
          style="height:95%"
        >
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">控制中心</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-document"></i>
            <span slot="title">工人管理</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-plus"></i>
            <span slot="title">新增工人</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-search"></i>
            <span slot="title">插件管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-main>Main</el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "app",
  data() {
    return {
      isCollapse: true,
    };
  },
  methods: {},
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 10px;
  height: 100%;
  width: 100%;
  position: fixed;
}
.el-header,
.el-footer {
  color: #333;
  text-align: left;
  font-size: 2em;
  font-family: "Consolas";
  line-height: 60px;
  border-bottom-style: solid;
  border-bottom-width: 3px;
  border-bottom-color: rgb(38, 97, 97);
}

</style>
