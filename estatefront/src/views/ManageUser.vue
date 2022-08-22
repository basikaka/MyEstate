<template>
  <div>
    <el-table
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column
          fixed
          prop="id"
          label="编号"
          width="150">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          width="120">
      </el-table-column>
      <el-table-column
          prop="alias"
          label="别名"
          width="120">
      </el-table-column>
      <el-table-column
          prop="password"
          label="密码"
          width="120">
      </el-table-column>
      <el-table-column
          prop="email"
          label="邮箱"
          width="180">
      </el-table-column>
      <el-table-column
          prop="estate"
          label="小区"
          width="300">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button @click="updateUser(scope.row)" type="text" size="small">修改</el-button>
          <el-button @click="deleteUser(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="total" :page-size="5"
        @current-change="page">
    </el-pagination>
  </div>
</template>

<script>
export default {
  methods: {
    updateUser(row) {
      console.log(row);
      this.$router.push({
        path:  "/updateuser",
        query : {
          id:row.id
        } 
      });
    },
    deleteUser(row){
      const _this = this;
      axios.delete("http://localhost/user/delete/" + row.id)
        .then( function(resp){
           _this.$alert(row.name + '删除成功', "Ok", {
               confirmButtonText: '确定',
              callback: action => {
               window.location.reload();
              }
            });
        })
    },

    page(currentPage){
      // alert(currentPage)
      const _this = this
      axios.get("http://localhost/user/all/" + currentPage +"/5").then( function ( resp ) {
        console.log(resp)
        _this.tableData = resp.data.content
      })
    }
  },

  data() {

    return {
      tableData: [
      ],
      total: 0
    };
  },
  created(){
    const _this = this
    axios.get("http://localhost/user/all/1/5").then( function ( resp ) {
      console.log(resp)
      _this.tableData = resp.data.content;
      _this.total = resp.data.totalElements;
    })
  }
}
</script>
