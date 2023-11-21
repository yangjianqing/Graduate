<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="就业岗位" prop="empName">
        <el-input
          v-model="queryParams.empName"
          placeholder="请输入要查询的岗位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="招聘人数" prop="empQuantity">
        <el-input
          v-model="queryParams.empQuantity"
          placeholder="请输入招聘人数（>=）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="薪资标准" prop="empPay">
        <el-input
          v-model="queryParams.empPay"
          placeholder="请输入薪资标准（>=）"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布状态" prop="empStatus">
        <el-select v-model="queryParams.empStatus" placeholder="请选择发布状态" clearable>
          <el-option
            v-for="dict in dict.type.b_empinfo"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:empinfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:empinfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:empinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:empinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="empinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="empId" />
      <el-table-column label="公司名称" align="center" prop="cName" />
      <el-table-column label="就业岗位" align="center" prop="empName" />
      <el-table-column label="职位描述" align="center" prop="empDes" />
      <el-table-column label="招聘人数" align="center" prop="empQuantity" />
      <el-table-column label="薪资标准" align="center" prop="empPay" />
      <el-table-column label="福利待遇" align="center" prop="empBonus" />
      <el-table-column label="工作内容" align="center" prop="empConten" />
      <el-table-column label="上岗需求" align="center" prop="empReq" />
      <el-table-column label="联系方式" align="center" prop="cPhone" />
      <el-table-column label="公司地址" align="center" prop="cAddress" />
      <el-table-column label="添加时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="就业信息详情" align="center" prop="empInfo" />
      <el-table-column label="发布状态" align="center" prop="empStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.b_empinfo" :value="scope.row.empStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:empinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:empinfo:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改就业信息发布对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="就业岗位" prop="empName">
          <el-input v-model="form.empName" placeholder="请输入就业岗位" />
        </el-form-item>
        <el-form-item label="职位描述" prop="empDes">
          <el-input v-model="form.empDes" placeholder="请输入职位描述" />
        </el-form-item>
        <el-form-item label="招聘人数" prop="empQuantity">
          <el-input v-model="form.empQuantity" placeholder="请输入招聘人数" />
        </el-form-item>
        <el-form-item label="薪资标准" prop="empPay">
          <el-input v-model="form.empPay" placeholder="请输入薪资标准" />
        </el-form-item>
        <el-form-item label="福利待遇" prop="empBonus">
          <el-input v-model="form.empBonus" placeholder="请输入福利待遇" />
        </el-form-item>
        <el-form-item label="工作内容" prop="empConten">
          <el-input v-model="form.empConten" placeholder="请输入工作内容" />
        </el-form-item>
        <el-form-item label="上岗需求" prop="empReq">
          <el-input v-model="form.empReq" placeholder="请输入上岗需求" />
        </el-form-item>
        <el-form-item label="联系方式" prop="cPhone">
          <el-input v-model="form.cPhone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="就业信息详情" prop="empInfo">
          <el-input v-model="form.empInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="发布状态">
          <el-radio-group v-model="form.empStatus">
            <el-radio
              v-for="dict in dict.type.b_empinfo"
              :key="dict.value"
:label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listEmpinfo, getEmpinfo, delEmpinfo, addEmpinfo, updateEmpinfo } from "@/api/system/empinfo";

export default {
  name: "Empinfo",
  dicts: ['b_empinfo'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 就业信息发布表格数据
      empinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cName: null,
        empName: null,
        empQuantity: null,
        empPay: null,
        cAddress: null,
        empStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询就业信息发布列表 */
    getList() {
      this.loading = true;
      listEmpinfo(this.queryParams).then(response => {
        this.empinfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        empId: null,
        cName: null,
        empName: null,
        empDes: null,
        empQuantity: null,
        empPay: null,
        empBonus: null,
        empConten: null,
        empReq: null,
        cPhone: null,
        cAddress: null,
        createTime: null,
        updateTime: null,
        empInfo: null,
        empStatus: "0"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.empId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加就业信息发布";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const empId = row.empId || this.ids
      getEmpinfo(empId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改就业信息发布";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.empId != null) {
            updateEmpinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmpinfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const empIds = row.empId || this.ids;
      this.$modal.confirm('是否确认删除就业信息发布编号为"' + empIds + '"的数据项？').then(function() {
        return delEmpinfo(empIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/empinfo/export', {
        ...this.queryParams
      }, `empinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
