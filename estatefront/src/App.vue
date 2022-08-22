<template>
  <el-container style="height: 500px; border: 1px solid #eee">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu :default-openeds="['1', '3']"  router>
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-message"></i>用户管理</template>
        <el-menu-item-group>
          <el-menu-item index="/manageuser">管理用户</el-menu-item>
          <el-menu-item index="/adduser">添加用户</el-menu-item>
        </el-menu-item-group>

      </el-submenu>
    </el-menu>
  </el-aside>

  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item>删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>富力半岛</span>
    </el-header>

    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
  </el-container>
</template>


<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>

<script>
export default {
  // data() {
  //
  //   return {
  //     tableData: [
  //     ],
  //   };
  // },
  // created(){
  //   const _this = this
  //   axios.get("http://localhost/user/all").then( function ( resp ) {
  //     console.log(resp)
  //     _this.tableData = resp.data
  //   })
  // }
};
</script>

