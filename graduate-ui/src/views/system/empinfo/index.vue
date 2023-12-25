<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="公司名称" prop="cName">
        <el-select v-model="queryParams.cName"
                   :data="AllCompanyList"
                   placeholder="请选择要查询的公司"
                   clearable
                   @keyup.enter.native="handleQuery"
                   filterable
                   :no-match-text="'没有关于此公司的招聘信息'">
          <el-option v-for="(cName,index) in AllCompanyList"
                     :key="index"
                     :label="cName.label"
                     :value="cName.value"/>
        </el-select>
      </el-form-item>

      <el-form-item label="所属地区" prop="cAddress">
        <el-select
          v-model="queryParams.cAddress"
          :data="AllAddressList"
          placeholder="请选择公司所属地区" clearable @keyup.enter.native="handleQuery"
          filterable
          :no-match-text="'没有关于此地区的招聘信息'">
          <el-option
            v-for="(cAddress,index) in AllAddressList"
            :key="index"
            :label="cAddress.label"
            :value="cAddress.value"/>
        </el-select>
      </el-form-item>

      <el-form-item label="招聘人数" prop="empQuantity">
        <el-input
          v-model="queryParams.empQuantity"
          placeholder="招聘人数"
          clearable
          @keyup.enter.native="handleQuery"
          style="width: 120px;"
        />
      </el-form-item>
      <el-form-item label="薪资标准" prop="empPay">
        <el-input
          v-model="queryParams.empPay"
          placeholder="最低薪资"
          clearable
          @keyup.enter.native="handleQuery"
          style="width: 120px;"
        />
      </el-form-item>
      <el-form-item label="" prop="empPayMax">
        <el-input
          v-model="queryParams.empPayMax"
          placeholder="最高薪资"
          clearable
          @keyup.enter.native="handleQuery"
          style="width: 120px;"
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
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:empinfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="empinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center"/>
      <el-table-column label="序号" align="center" prop="empId"/>
      <el-table-column label="公司名称" align="center" prop="cName"/>
      <el-table-column label="就业岗位" align="center" prop="empName">
        <template slot-scope="scope">
          {{scope.row.empName.length > 4 ? (scope.row.empName.substring(0, 4) + '...') : scope.row.empName}}
        </template>
      </el-table-column>

      <el-table-column label="职位描述" align="center" prop="empDes" tooltip-effect="light">
        <template slot-scope="{ row }">
          <el-tooltip class="item" effect="light" :content="row.empDes" placement="left" popper-class="tooltip-width">
            <div
              style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2; max-width: 200px; overflow: hidden;">
              {{ row.empDes }}
            </div>
          </el-tooltip>
        </template>
      </el-table-column>

      <el-table-column label="招聘人数" align="center" prop="empQuantity"/>
      <el-table-column label="最低薪资" align="center" prop="empPay"/>
      <el-table-column label="最高薪资" align="center" prop="empPayMax"/>

      <el-table-column label="福利待遇" align="center" prop="empBonus" tooltip-effect="light">
        <template slot-scope="{ row }">
          <el-tooltip class="item" effect="light" :content="row.empBonus" placement="left" popper-class="tooltip-width">
            <div
              style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2; max-width: 200px; overflow: hidden;">
              {{ row.empBonus }}
            </div>
          </el-tooltip>
        </template>
      </el-table-column>

      <el-table-column label="工作内容" align="center" prop="empConten" tooltip-effect="light">
        <template slot-scope="{ row }">
          <el-tooltip class="item" effect="light" :content="row.empConten" placement="left" popper-class="tooltip-width">
            <div
              style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2; max-width: 200px; overflow: hidden;">
              {{ row.empConten }}
            </div>
          </el-tooltip>
        </template>
      </el-table-column>

      <el-table-column label="上岗需求" align="center" prop="empReq" tooltip-effect="light">
        <template slot-scope="{ row }">
          <el-tooltip class="item" effect="light" :content="row.empReq" placement="left" popper-class="tooltip-width">
            <div
              style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2; max-width: 200px; overflow: hidden;">
              {{ row.empReq }}
            </div>
          </el-tooltip>
        </template>
      </el-table-column>

      <el-table-column label="联系方式" align="center" :min-width="100" prop="cPhone"/>
      <el-table-column label="公司地址" align="center" prop="cAddress">
        <template slot-scope="scope">
          {{ scope.row.cAddress.length > 4 ? (scope.row.cAddress.substring(0, 4) + '...') : scope.row.cAddress }}
        </template>
      </el-table-column>
      <el-table-column label="添加时间" align="center" prop="createTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后修改时间" align="center" prop="updateTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="公司信息" align="center" prop="empInfo" tooltip-effect="light">
        <template slot-scope="{ row }">
          <el-tooltip class="item" effect="light" :content="row.empInfo" placement="left" popper-class="tooltip-width">
            <div
              style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2; max-width: 200px; overflow: hidden;">
              {{ row.empInfo }}
            </div>
          </el-tooltip>
        </template>
      </el-table-column>

      <el-table-column label="发布状态" align="center" prop="empStatus">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.empStatus==='1'" :active-value="true" :inactive-value="false"
                     active-color="#00FF00" inactive-color="#CCCCCC" @change="handleModified(scope.row)">
          </el-switch>
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
          >修改
          </el-button>
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
        <el-form-item label="公司名称" prop="cName">
          <el-autocomplete
            class="inline-input"
            v-model="form.cName"
            :fetch-suggestions="querySearchOfCompany"
            placeholder="请输入公司名称">
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="公司地址" prop="cAddress">
          <el-autocomplete
            class="inline-input"
            v-model="form.cAddress"
            :fetch-suggestions="querySearchOfAddress"
            placeholder="请输入公司地址"></el-autocomplete>
        </el-form-item>

        <el-form-item label="联系方式" prop="cPhone">
          <el-input v-model="form.cPhone" placeholder="请输入联系方式"/>
        </el-form-item>
        <el-form-item label="就业岗位" prop="empName">
          <el-input v-model="form.empName" placeholder="请输入就业岗位"/>
        </el-form-item>
        <el-form-item label="职位描述" prop="empDes">
          <el-input v-model="form.empDes" placeholder="请输入职位描述"/>
        </el-form-item>
        <el-form-item label="招聘人数" prop="empQuantity">
          <el-input v-model="form.empQuantity" placeholder="请输入招聘人数"/>
        </el-form-item>
        <el-row>
          <el-col :span="6">
            <el-form-item label="薪资标准" prop="empPay">
              <el-input v-model="form.empPay" placeholder="最低薪资" style="width: 100px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="" prop="empPayMax">
              —
              <el-input v-model="form.empPayMax" placeholder="最高薪资" style="width: 100px;"/>
              K
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="福利待遇" prop="empBonus">
          <el-input v-model="form.empBonus" placeholder="请输入福利待遇"/>
        </el-form-item>
        <el-form-item label="工作内容" prop="empConten">
          <el-input v-model="form.empConten" placeholder="请输入工作内容"/>
        </el-form-item>
        <el-form-item label="上岗需求" prop="empReq">
          <el-input v-model="form.empReq" placeholder="请输入上岗需求"/>
        </el-form-item>
        <el-form-item label="公司信息" prop="empInfo">
          <el-input v-model="form.empInfo" type="textarea" :autosize="{ minRows: 2, maxRows: 12 }" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">发 布</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style>
.tooltip-width {
  max-width: 600px;
  font-size: 24px;
  font-weight: bold;
  color: #00afff;
}
</style>
<script>
import {addEmpinfo, getEmpinfo, listEmpinfo, modifiedEmpinfo, updateEmpinfo,noPageList} from "@/api/system/empinfo";
import {listCompany} from "@/api/system/company";

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
      addressList: [],
      AllAddressList: [],
      AllCompanyList: [],
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
        empPayMax: null,
        cAddress: null,
        empStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
    this.getAllList();
  },
  methods: {
    querySearchOfAddress(queryString, cb) {
      const restaurants = this.AllAddressList;
      const results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      cb(results);
    },
    querySearchOfCompany(queryString, cb) {
      const restaurants = this.AllCompanyList;
      const results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
      cb(results);
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    /** 查询就业信息发布列表 */
    getList() {
      this.loading = true;
      listEmpinfo(this.queryParams).then(response => {
        this.empinfoList = response.rows;
        this.total = response.total;
        this.pageSize = this.total;
        this.loading = false;
      });
    },
    getAllList(){
      noPageList(this.queryParams).then(response => {
        this.addressList = response.rows;
        /** 公司与地址去重 **/
        const addressSet = new Set();
        const companySet = new Set();
        this.addressList.forEach(empinfo => {
          addressSet.add(empinfo.cAddress);
          companySet.add(empinfo.cName);
        });
        this.AllAddressList = Array.from(addressSet).map(address => ({"value": address, "address": address}));
        this.AllCompanyList = Array.from(companySet).map(company => ({"value": company, "address": company}));
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
        empPayMax: null,
        empBonus: null,
        empConten: null,
        empReq: null,
        cPhone: null,
        cAddress: null,
        createTime: null,
        updateTime: null,
        empInfo: null,
        empStatus: "1"
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
      this.single = selection.length !== 1
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
    /** 调整按钮操作 */
    handleModified(row) {
      const empId = row.empId;
      const empStatus = row.empStatus;
      modifiedEmpinfo(empId, empStatus).then(response => {
        if (empStatus === "0") {
          this.$modal.msgSuccess("发布成功");
        } else {
          // this.$modal.msgSuccess("下架成功");
          this.$message({message: "下架成功", type: "info"});
        }
        this.open = false;
        this.getList();
      });
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
