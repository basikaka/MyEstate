<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" style = "width:60%">
    <el-form-item label="姓名" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item label="别名" prop="alias">
      <el-input v-model="ruleForm.alias"></el-input>
    </el-form-item>
    <el-form-item label="小区" prop="estate">
      <el-input v-model="ruleForm.estate"></el-input>
    </el-form-item>

    <el-form-item
    prop="email"
    label="邮箱"
    >
      <el-input v-model="ruleForm.email"></el-input>
    </el-form-item>

    <el-form-item label="密码" prop="password">
      <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
    </el-form-item>

    <el-form-item label="确认密码" prop="checkPassword">
      <el-input type="password" v-model="ruleForm.checkPassword" autocomplete="off"></el-input>
    </el-form-item>
  
    <el-form-item label="角色" prop="role">
      <el-select v-model="ruleForm.role" placeholder="请选择角色">
        <el-option label="业主" value="业主"></el-option>
        <el-option label="物业" value="物业"></el-option>
        <el-option label="租户" value="租户"></el-option>
        <el-option label="社区" value="社区"></el-option>
      </el-select>
    </el-form-item>
 
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
      <!-- <el-button @click="showRuleForm()" >showRuleForm </el-button> -->
    </el-form-item>
  </el-form>
</template>
<script>
  export default {
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPassword !== '') {
            this.$refs.ruleForm.validateField('checkPassword');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };

      return {
        ruleForm: {
          name: '',
          alias: '',
          estate: '',
          email: '',
          passord: '',
          checkPassword: '',
          role: '',
     
        },
        rules: {
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { min: 3, max: 15, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          alias: [
            { required: true, message: '请输入别名', trigger: 'blur' },
            { min: 3, max: 15, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          estate: [
            { required: true, message: '请输入小区', trigger: 'blur' },
            { min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPassword: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          role: [
            { required: true, message: '请选择角色', trigger: 'change' }
          ],
        }
      };
    },
    created(){
        // alert( this.$route.query.id);
        const _this = this
        axios.get("http://localhost/user/select/" + this.$route.query.id)
        .then( function( resp){
            // console.log( resp)
            _this.ruleForm = resp.data;
        }
        )
    },

    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          const _this = this;
          if (valid) {
          //  alert('submit!');
            axios.put("http://localhost/user/update", _this.ruleForm)
            .then(function(resp){
                console.log(resp);
                if( resp.data == 'succeed'){
                    _this.$alert('成功', '修改成功', {
                    confirmButtonText: '确定',
                    callback: action => {
                        _this.$router.push("/manageuser")
                    }
                    });
              }
            }
            )
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      // showRuleForm(){
      //   console.log( thsi.ruleForm)
      // }
    }
  }
</script>
