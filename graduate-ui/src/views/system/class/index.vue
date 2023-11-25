<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="班级编号" prop="cNumber">
        <el-input
          v-model="queryParams.cNumber"
          placeholder="请输入班级编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="班级名称" prop="cName">-->
<!--        <el-input-->
<!--          v-model="queryParams.cName"-->
<!--          placeholder="请输入班级名称"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

<!--      <el-form-item label="辅导员" prop="teacherId">-->
<!--        <el-input-->
<!--          v-model="queryParams.teacherId"-->
<!--          placeholder="请输入辅导员"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

      <el-form-item label="班级" prop="cName">
        <el-select v-model="queryParams.cName" :data="clasei" clearable placeholder="请选择班级" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in clasei"
            :key="item.cName"
            :label="item.cName"
            :value="item.cName"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="辅导员" prop="teacherId">
        <el-select v-model="queryParams.teacherId" :data="teachers" clearable placeholder="请选择辅导员" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in teachers"
            :key="item.tchrId"
            :label="item.tchrName"
            :value="item.tchrId"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
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
          v-hasPermi="['system:class:add']"
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
          v-hasPermi="['system:class:edit']"
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
          v-hasPermi="['system:class:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:class:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="班级id" align="center" prop="cId" />
      <el-table-column label="班级编号" align="center" prop="cNumber" />
      <el-table-column label="班级名称" align="center" prop="cName" />
      <el-table-column label="辅导员" align="center" prop="teacherName" />
      <el-table-column label="联系方式" align="center" prop="cPhone" />
      <el-table-column label="班级人数" align="center" prop="cCount" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:class:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:class:remove']"
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

    <!-- 添加或修改班级管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

<!--        <el-form-item label="班级名称" prop="cName">-->
<!--          <el-input v-model="form.cName" placeholder="请输入班级名称" />-->
<!--        </el-form-item>-->

        <el-form-item label="班级" prop="cName">
          <el-select v-model="form.cName" :data="clasei" clearable placeholder="请选择班级">
            <el-option
              v-for="item in clasei"
              :key="item.cName"
              :label="item.cName"
              :value="item.cName"
            ></el-option>
          </el-select>
        </el-form-item>

<!--        <el-form-item label="辅导员" prop="teacherId">-->
<!--          <el-input v-model="form.teacherId" placeholder="请输入辅导员" />-->
<!--        </el-form-item>-->

        <el-form-item label="辅导员" prop="teacherId">
          <el-select v-model="form.teacherId" :data="teachers" clearable placeholder="请选择辅导员">
            <el-option
              v-for="item in teachers"
              :key="item.tchrId"
              :label="item.tchrName"
              :value="item.tchrId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="联系方式" prop="cPhone">
          <el-input v-model="form.cPhone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="班级人数" prop="cCount">
          <el-input v-model="form.cCount" placeholder="请输入班级人数" />
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
import { listClass, getClass, delClass, addClass, updateClass , listClasesi} from "@/api/system/class";

export default {
  name: "Class",
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
      // 班级管理表格数据
      classList: [],
      // 弹出层标题
      title: "",
      //老师列表
      teachers:[],
      //班级列表
      clasei:[],
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cNumber: null,
        cName: null,
        teacherId: null,
        createTime: null,
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
    this.getC();
  },
  methods: {
    // 页面加载初始化数据
    getC(){
      listClasesi().then(response => {
        console.log(response)
        this.teachers=response.teachers;
        this.clasei=response.clasei;
      });
    },
    /** 查询班级管理列表 */
    getList() {
      this.loading = true;
      listClass(this.queryParams).then(response => {
        this.classList = response.rows;
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
        cNumber: null,
        cName: null,
        teacherId: null,
        cPhone: null,
        cCount: null,
        createTime: null,
        updateTime: null
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
      this.title = "添加班级管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cId = row.cId || this.ids
      getClass(cId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改班级管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cId != null) {
            updateClass(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClass(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除班级管理编号为"' + cIds + '"的数据项？').then(function() {
        return delClass(cIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/class/export', {
        ...this.queryParams
      }, `class_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
