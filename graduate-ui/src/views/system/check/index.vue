<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="ckName">
        <el-input
          v-model="queryParams.ckName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
          style="width:170px"
        />
      </el-form-item>
      <el-form-item label="时间" prop="ckTime">
        <el-date-picker clearable
          v-model="queryParams.ckTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择时间"
        style="width:160px">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="地址" prop="ckAddress">
        <el-input
          v-model="queryParams.ckAddress"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
          style="width:170px"

        />
      </el-form-item>
<!--      <el-form-item label="班级" prop="bClass">-->
<!--        <el-input-->
<!--          v-model="queryParams.bClass"-->
<!--          placeholder="请输入班级"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="类型" prop="ckTpye">
        <el-select v-model="queryParams.ckTpye" placeholder="请选择类型" clearable filterable style="width:170px">
          <el-option
            v-for="dict in dict.type.b_check_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      班级名称-->
      <el-form-item label="班级" prop="cName">
        <el-select v-model="queryParams.cName" :data="classList" placeholder="请选择类型" clearable filterable style="width:170px">
          <el-option
            v-for="dict in classList"
            :key="dict.cId"
            :label="dict.cName"
            :value="dict.cId"
          />
        </el-select>
      </el-form-item>


      <el-form-item label="公司" prop="cId">
        <el-select
          v-model="queryParams.cId" :data="companyList"
          placeholder="请输入公司名称" clearable filterable @keyup.enter.native="handleQuery" style="width:170px">
        <el-option
          v-for="dict in companyList"
          :key="dict.cId"
          :label="dict.cName"
          :value="dict.cId"
        />
          </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['system:check:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['system:check:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['system:check:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:check:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="checkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="ckId" />
      <el-table-column label="姓名" align="center" prop="ckName" />
      <el-table-column label="时间" align="center" prop="ckTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ckTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center" prop="ckAddress" />
      <el-table-column label="经度" align="center" prop="ckLongitude" />
      <el-table-column label="纬度" align="center" prop="ckLatitude" />
      <el-table-column label="班级" align="center" prop="bClass" >
        <template slot-scope="scope">
          <span>{{getCalssName(scope.row.bClass)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="公司名称" align="center" prop="cId" >
        <template slot-scope="scope">
          <span>{{getCompanyName(scope.row.cId)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="类型" align="center" prop="ckTpye">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.b_check_type" :value="scope.row.ckTpye"/>
        </template>
      </el-table-column>

<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:check:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:check:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改签到对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="姓名" prop="ckName">-->
<!--          <el-input v-model="form.ckName" placeholder="请输入姓名" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="时间" prop="ckTime">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.ckTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="地址" prop="ckAddress">-->
<!--          <el-input v-model="form.ckAddress" placeholder="请输入地址" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="经度" prop="ckLongitude">-->
<!--          <el-input v-model="form.ckLongitude" placeholder="请输入经度" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="纬度" prop="ckLatitude">-->
<!--          <el-input v-model="form.ckLatitude" placeholder="请输入纬度" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="班级" prop="bClass">-->
<!--          <el-input v-model="form.bClass" placeholder="请输入班级" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="类型" prop="ckTpye">-->
<!--          <el-select v-model="form.ckTpye" placeholder="请选择类型">-->
<!--            <el-option-->
<!--              v-for="dict in dict.type.b_check_type"-->
<!--              :key="dict.value"-->
<!--              :label="dict.label"-->
<!--:value="dict.value"-->
<!--            ></el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="公司Id" prop="cId">-->
<!--          <el-input v-model="form.cId" placeholder="请输入公司Id" />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
  </div>
</template>

<script>
import { listCheck, getCheck, delCheck, addCheck, updateCheck } from "@/api/system/check";
import { listClass } from "@/api/system/class";
import {listCompany} from "@/api/system/company";

export default {
  name: "Check",
  dicts: ['b_check_type'],
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
      // 签到表格数据
      checkList: [],
      classList: [],
      companyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ckName: null,
        ckTime: null,
        ckAddress: null,
        bClass: null,
        ckTpye: null,
        cId: null
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
    this.getCalssList();
    this.getCompanyList();

  },
  methods: {
    getCalssName(cId){
      // console.log(cId);
      const  calss=this.classList.find(item =>item.cId ===cId);
      return calss ? calss.cName : '';
    },
    getCompanyName(cId){
      console.log(cId);
      const  company=this.companyList.find(item =>item.cId ===cId);
      return company ? company.cName : '';
    },
    /** 查询签到列表 */
    getList() {
      this.loading = true;
      listCheck(this.queryParams).then(response => {
        this.checkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询班级列表 */
    getCalssList() {
      this.loading = true;
      listClass(this.queryParams).then(response => {
        this.classList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询公司管理列表 */
    getCompanyList() {
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
        ckId: null,
        ckName: null,
        ckTime: null,
        ckAddress: null,
        ckLongitude: null,
        ckLatitude: null,
        bClass: null,
        ckTpye: null,
        cId: null
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
      this.ids = selection.map(item => item.ckId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    // /** 新增按钮操作 */
    // handleAdd() {
    //   this.reset();
    //   this.open = true;
    //   this.title = "添加签到";
    // },
    // /** 修改按钮操作 */
    // handleUpdate(row) {
    //   this.reset();
    //   const ckId = row.ckId || this.ids
    //   getCheck(ckId).then(response => {
    //     this.form = response.data;
    //     this.open = true;
    //     this.title = "修改签到";
    //   });
    // },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ckId != null) {
            updateCheck(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCheck(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    // /** 删除按钮操作 */
    // handleDelete(row) {
    //   const ckIds = row.ckId || this.ids;
    //   this.$modal.confirm('是否确认删除签到编号为"' + ckIds + '"的数据项？').then(function() {
    //     return delCheck(ckIds);
    //   }).then(() => {
    //     this.getList();
    //     this.$modal.msgSuccess("删除成功");
    //   }).catch(() => {});
    // },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/check/export', {
        ...this.queryParams
      }, `check_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
