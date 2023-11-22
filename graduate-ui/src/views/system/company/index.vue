<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司名称" prop="cName">
        <el-input
          v-model="queryParams.cName"
          placeholder="请输入公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成立时间" prop="cData">
        <el-date-picker clearable
          v-model="queryParams.cData"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择成立时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="公司类型" prop="cType">
        <el-select v-model="queryParams.cType" placeholder="请选择公司类型" clearable>
          <el-option
            v-for="dict in dict.type.b_company_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="公司地址" prop="cAddress">
        <el-input
          v-model="queryParams.cAddress"
          placeholder="请输入公司地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:company:add']"
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
          v-hasPermi="['system:company:edit']"
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
          v-hasPermi="['system:company:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:company:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公司Id" align="center" prop="cId" />
      <el-table-column label="公司名称" align="center" prop="cName" />
      <el-table-column label="成立时间" align="center" prop="cData" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cData, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="公司类型" align="center" prop="cType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.b_company_type" :value="scope.row.cType"/>
        </template>
      </el-table-column>
      <el-table-column label="公司地址" align="center" prop="cAddress" />
      <el-table-column label="联系方式" align="center" prop="cPhone" />
      <el-table-column label="添加时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="经度" align="center" prop="cLongitude" />
      <el-table-column label="纬度" align="center" prop="cLatitude" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:company:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:company:remove']"
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

    <!-- 添加或修改公司管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="cName">
          <el-input v-model="form.cName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="成立时间" prop="cData">
          <el-date-picker clearable
            v-model="form.cData"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择成立时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="公司类型" prop="cType">
          <el-select v-model="form.cType" placeholder="请选择公司类型">
            <el-option
              v-for="dict in dict.type.b_company_type"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="公司地址" prop="cAddress">
          <el-input v-model="form.cAddress" placeholder="请输入公司地址" />
        </el-form-item>
        <el-form-item label="联系方式" prop="cPhone">
          <el-input v-model="form.cPhone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="经度" prop="cLongitude">
          <el-input v-model="form.cLongitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="cLatitude">
          <el-input v-model="form.cLatitude" placeholder="请输入纬度" />
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
import { listCompany, getCompany, delCompany, addCompany, updateCompany } from "@/api/system/company";

export default {
  name: "Company",
  dicts: ['b_company_type'],
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
      // 公司管理表格数据
      companyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cName: null,
        cData: null,
        cType: null,
        cAddress: null,
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
    /** 查询公司管理列表 */
    getList() {
      this.loading = true;
      listCompany(this.queryParams).then(response => {
        this.companyList = response.rows;
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
        cId: null,
        cName: null,
        cData: null,
        cType: null,
        cAddress: null,
        cPhone: null,
        createTime: null,
        updateTime: null,
        cLongitude: null,
        cLatitude: null
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
      this.ids = selection.map(item => item.cId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加公司管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cId = row.cId || this.ids
      getCompany(cId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公司管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cId != null) {
            updateCompany(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompany(this.form).then(response => {
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
      const cIds = row.cId || this.ids;
      this.$modal.confirm('是否确认删除公司管理编号为"' + cIds + '"的数据项？').then(function() {
        return delCompany(cIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/company/export', {
        ...this.queryParams
      }, `company_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
