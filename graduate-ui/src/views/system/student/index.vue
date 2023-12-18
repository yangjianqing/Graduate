<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生学号" prop="sNumber">
        <el-input
          v-model="queryParams.sNumber"
          placeholder="请输入学生学号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生姓名" prop="sName">
        <el-input
          v-model="queryParams.sName"
          placeholder="请输入学生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="sGender">
        <el-select v-model="queryParams.sGender" placeholder="请选择性别" clearable>
          <el-option
            v-for="dict in dict.type.b_gender"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="学校"  prop="schoolId">
        <el-select v-model="queryParams.schoolId" :data="schools"  clearable placeholder="请选择学校" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in schools"
            :key="item.sId"
            :label="item.sName"
            :value="item.sId"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="公司" prop="compenyId">
        <el-select v-model="queryParams.compenyId" :data="companys" clearable placeholder="请选择公司" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in companys"
            :key="item.cId"
            :label="item.cName"
            :value="item.cId"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="就业状态" prop="cStatus">
        <el-select v-model="queryParams.cStatus" placeholder="请选择就业状态" clearable>
          <el-option
            v-for="dict in dict.type.c_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="班级" prop="classId">
        <el-select v-model="queryParams.classId" :data="clasei" clearable placeholder="请选择班级" @keyup.enter.native="handleQuery">
          <el-option
            v-for="item in clasei"
            :key="item.cId"
            :label="item.cName"
            :value="item.cId"
          ></el-option>
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
          v-hasPermi="['system:student:add']"
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
          v-hasPermi="['system:student:edit']"
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
          v-hasPermi="['system:student:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:student:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生ID" align="center" prop="sId" width="60"/>
      <el-table-column label="学生学号" align="center" prop="sNumber" />
      <el-table-column label="学生姓名" align="center" prop="sName" />
      <el-table-column label="性别" align="center" prop="sGender">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.b_gender" :value="scope.row.sGender"/>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="center" prop="sNumbers" width="110"/>
      <el-table-column label="学校" align="center" prop="schoolName"/>
      <el-table-column label="公司" align="center" prop="compenyName" />
      <el-table-column label="就业状态" align="center" prop="cStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.c_status" :value="scope.row.cStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="辅导员" align="center" prop="teacherName" />
      <el-table-column label="班级" align="center" prop="className" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="updateTime" width="150">
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
            v-hasPermi="['system:student:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:student:remove']"
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

    <!-- 添加或修改学生管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生姓名" prop="sName">
          <el-input v-model="form.sName" placeholder="请输入学生姓名" />
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sGender">
            <el-radio
              v-for="dict in dict.type.b_gender"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="联系方式" prop="sNumbers">
          <el-input v-model="form.sNumbers" placeholder="请输入联系方式" />
        </el-form-item>

        <el-form-item label="学校" prop="schoolId">
          <el-select v-model="form.schoolId" :data="schools" clearable placeholder="请选择学校">
            <el-option
              v-for="item in schools"
              :key="item.sId"
              :label="item.sName"
              :value="item.sId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="公司" prop="compenyId">
          <el-select v-model="form.compenyId" :data="companys" clearable placeholder="请选择公司">
            <el-option
              v-for="item in companys"
              :key="item.cId"
              :label="item.cName"
              :value="item.cId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="就业状态">
          <el-radio-group v-model="form.cStatus">
            <el-radio
              v-for="dict in dict.type.c_status"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>

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

        <el-form-item label="班级" prop="classId">
          <el-select v-model="form.classId" :data="clasei" clearable placeholder="请选择班级">
            <el-option
              v-for="item in clasei"
              :key="item.cId"
              :label="item.cName"
              :value="item.cId"
            ></el-option>
          </el-select>
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
import { listStudent, getStudent, delStudent, addStudent, updateStudent, initStudent } from "@/api/system/student";

export default {
  name: "Student",
  dicts: ['c_status', 'b_gender'],
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
      // 学生管理表格数据
      studentList: [],
      // 弹出层标题
      title: "",
      //公司列表
      companys:[],
      //辅导员列表
      teachers:[],
      //班級列表
      clasei:[],
      //学校列表
      schools: [],
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sNumber: null,
        sName: null,
        sGender: null,
        schoolId: null,
        compenyId: null,
        cStatus: null,
        classId: null,
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
    this.init();
  },
  methods: {
    // 页面加载初始化数据
    init(){
      initStudent().then(response => {
        this.companys=response.companys;
        this.teachers=response.teachers;
        this.clasei=response.clasei;
        this.schools=response.schools;
        console.log(this.schools)
      });
    },
    /** 查询学生管理列表 */
    getList() {
      this.loading = true;
      listStudent(this.queryParams).then(response => {
        this.studentList = response.rows;
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
        sId: null,
        sNumber: null,
        sName: null,
        sGender: 0,
        sNumbers: null,
        schoolId: null,
        compenyId: null,
        cStatus: 0,
        teacherId: null,
        classId: null,
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
      this.ids = selection.map(item => item.sId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sId = row.sId || this.ids
      getStudent(sId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sId != null) {
            updateStudent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStudent(this.form).then(response => {
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
      const sIds = row.sId || this.ids;
      this.$modal.confirm('是否确认删除学生管理编号为"' + sIds + '"的数据项？').then(function() {
        return delStudent(sIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/student/export', {
        ...this.queryParams
      }, `student_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
