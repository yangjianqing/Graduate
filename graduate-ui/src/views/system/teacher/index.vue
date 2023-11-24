<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教师编号" prop="tchrNum">
        <el-input
          v-model="queryParams.tchrNum"
          placeholder="请输入教师编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教师姓名" prop="tchrName">
        <el-input
          v-model="queryParams.tchrName"
          placeholder="请输入教师姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="所属院校" prop="sId">-->
<!--        <el-input-->
<!--          v-model="queryParams.sId"-->
<!--          placeholder="请输入所属院校"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="所属院校" prop="sId">
        <el-select v-model="queryParams.sId" placeholder="请选择学校" clearable @keyup.enter.native="handleQuery">
          <el-option v-for="s in schoolList"
                     :key="s.sId"
                     :label="s.sName"
                     :value="s.sId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="在校状态" prop="tchrStatus">
        <el-select v-model="queryParams.tchrStatus" placeholder="请选择教师在职状态" clearable>
          <el-option
            v-for="dict in dict.type.b_teacher_status"
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
          v-hasPermi="['system:teacher:add']"
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
          v-hasPermi="['system:teacher:edit']"
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
          v-hasPermi="['system:teacher:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:teacher:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="tchrId" />
      <el-table-column label="教师编号" align="center" prop="tchrNum" />
<!--      <el-table-column label="所属院校" align="center" prop="sId" />-->
      <el-table-column label="所属院校" align="center" prop="sId">
        <template slot-scope="scope">
          <span>{{getSchoolName(scope.row.sId)}}</span>
        </template>
      </el-table-column>
      <el-table-column label="教师姓名" align="center" prop="tchrName" />
      <el-table-column label="教师性别" align="center" prop="tchrGender">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.b_teacher_gender" :value="scope.row.tchrGender"/>
        </template>
      </el-table-column>
      <el-table-column label="教师年龄" align="center" prop="tchrAge" />
<!--      <el-table-column label="教师照片" align="center" prop="tchrImg" />-->
      <el-table-column label="教师照片" align="center" prop="tchrImg" width="100">
        <template slot-scope="scope">
          <image-preview v-if="scope.row.tchrImg" :src="scope.row.tchrImg" width="30" height="30"/>
          <span v-else>该教师暂未上传照片</span>
        </template>
      </el-table-column>
      <el-table-column label="教师联系方式" align="center" prop="tchrPhone" />
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
      <el-table-column label="教师在职状态" align="center" prop="tchrStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.b_teacher_status" :value="scope.row.tchrStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:teacher:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:teacher:remove']"
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

    <!-- 添加或修改教师信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教师编号" prop="tchrNum">
          <el-input v-model="form.tchrNum" placeholder="请输入教师编号" />
        </el-form-item>
        <el-form-item label="教师姓名" prop="tchrName">
          <el-input v-model="form.tchrName" placeholder="请输入教师姓名" />
        </el-form-item>
<!--        <el-form-item label="所属院校" prop="sId">-->
<!--          <el-input v-model="form.sId" placeholder="请输入所属院校" />-->
<!--        </el-form-item>-->
        <el-form-item label="所属院校" prop="sId">
          <el-select v-model="form.sId" placeholder="请输入所属院校">
            <el-option v-for="s in schoolList"
                       :key="s.sId"
                       :label="s.sName"
                       :value="s.sId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="教师性别">
          <el-radio-group v-model="form.tchrGender">
            <el-radio
              v-for="dict in dict.type.b_teacher_gender"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="教师年龄" prop="tchrAge">
          <el-input v-model="form.tchrAge" placeholder="请输入教师年龄" />
        </el-form-item>
        <el-form-item label="教师联系方式" prop="tchrPhone">
          <el-input v-model="form.tchrPhone" placeholder="请输入教师联系方式" />
        </el-form-item>
        <el-form-item label="上传照片" prop="tchrImg">
          <ImageUpload :limit="1" v-model="form.tchrImg"></ImageUpload>
        </el-form-item>
        <el-form-item label="在职状态">
          <el-radio-group v-model="form.tchrStatus">
            <el-radio
              v-for="dict in dict.type.b_teacher_status"
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
import { listTeacher, getTeacher, delTeacher, addTeacher, updateTeacher } from "@/api/system/teacher";
import {listSchool} from "@/api/system/school";

export default {
  name: "Teacher",
  dicts: ['b_teacher_status', 'b_teacher_gender'],
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
      // 教师信息表格数据
      teacherList: [],
      schoolList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tchrNum: null,
        sId: null,
        tchrName: null,
        tchrGender: null,
        tchrAge: null,
        tchrImg: null,
        tchrPhone: null,
        tchrStatus: null
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
    this.getSchoolList();
  },
  methods: {
    //根据sId获取学校名称
    getSchoolName(sId){
      const school = this.schoolList.find(item => item.sId === sId);
      return school ? school.sName : '';
    },
    /** 查询教师信息列表 */
    getList() {
      this.loading = true;
      listTeacher(this.queryParams).then(response => {
        this.teacherList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询学校管理列表 */
    getSchoolList() {
      this.loading = true;
      listSchool(this.queryParams).then(response => {
        this.schoolList = response.rows;
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
        tchrId: null,
        tchrNum: null,
        sId: null,
        tchrName: null,
        tchrGender: null,
        tchrAge: null,
        tchrImg: null,
        tchrPhone: null,
        createTime: null,
        updateTime: null,
        tchrStatus: "0"
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
      this.ids = selection.map(item => item.tchrId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加教师信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tchrId = row.tchrId || this.ids
      getTeacher(tchrId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改教师信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tchrId != null) {
            updateTeacher(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTeacher(this.form).then(response => {
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
      const tchrIds = row.tchrId || this.ids;
      this.$modal.confirm('是否确认删除教师信息编号为"' + tchrIds + '"的数据项？').then(function() {
        return delTeacher(tchrIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/teacher/export', {
        ...this.queryParams
      }, `teacher_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
