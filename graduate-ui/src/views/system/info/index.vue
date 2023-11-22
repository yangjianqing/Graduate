<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公告标题" prop="nTitle">
        <el-input
          v-model="queryParams.nTitle"
          placeholder="请输入公告标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公告类型" prop="nType">
        <el-select v-model="queryParams.nType" placeholder="请选择公告类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_notice_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建者" prop="nPeople">
        <el-input
          v-model="queryParams.nPeople"
          placeholder="请输入创建者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="nTime">
        <el-date-picker clearable
          v-model="queryParams.nTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新者" prop="nMender">
        <el-input
          v-model="queryParams.nMender"
          placeholder="请输入更新者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择添加时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择最后修改时间">
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
          v-hasPermi="['system:info:add']"
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
          v-hasPermi="['system:info:edit']"
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
          v-hasPermi="['system:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公告ID" align="center" prop="nId" />
      <el-table-column label="公告标题" align="center" prop="nTitle" />
      <el-table-column label="公告类型" align="center" prop="nType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_notice_type" :value="scope.row.nType"/>
        </template>
      </el-table-column>
      <el-table-column label="公告内容" align="center" prop="nContent" show-overflow-tooltip />
      <el-table-column label="公告状态" align="center" prop="nStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_notice_status" :value="scope.row.nStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="nPeople" />
      <el-table-column label="创建时间" align="center" prop="nTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.nTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="nMender" />
      <el-table-column label="备注" align="center" prop="nRemark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:info:edit']"
          >修改</el-button>


<!--          <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)" v-hasPermi="['system:info:query', 'system:info:remove']">-->
<!--            <span class="el-dropdown-link">-->
<!--              <i class="el-icon-d-arrow-right el-icon&#45;&#45;right"></i>更多-->
<!--            </span>-->
<!--            <el-dropdown-menu slot="dropdown">-->
<!--              <el-dropdown-item command="handleView" icon="el-icon-view"-->
<!--                                v-hasPermi="['system:info:query']">详情</el-dropdown-item>-->
<!--              <el-dropdown-item command="handleDelete" icon="el-icon-delete"-->
<!--                                v-hasPermi="['system:info:remove']">删除</el-dropdown-item>-->
<!--            </el-dropdown-menu>-->
<!--          </el-dropdown>-->

          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:info:remove']"
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

    <!-- 添加或修改公告管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公告标题" prop="nTitle">
          <el-input v-model="form.nTitle" placeholder="请输入公告标题" />
        </el-form-item>
        <el-form-item label="公告类型" prop="nType">
          <el-select v-model="form.nType" placeholder="请选择公告类型">
            <el-option
              v-for="dict in dict.type.sys_notice_type"
              :key="dict.value"
              :label="dict.label"
:value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="公告内容">
          <editor v-model="form.nContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="公告状态">
          <el-radio-group v-model="form.nStatus">
            <el-radio
              v-for="dict in dict.type.sys_notice_status"
              :key="dict.value"
:label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="创建者" prop="nPeople">
          <el-input v-model="form.nPeople" placeholder="请输入创建者" />
        </el-form-item>
        <el-form-item label="创建时间" prop="nTime">
          <el-date-picker clearable
            v-model="form.nTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新者" prop="nMender">
          <el-input v-model="form.nMender" placeholder="请输入更新者" />
        </el-form-item>
        <el-form-item label="备注" prop="nRemark">
          <el-input v-model="form.nRemark" placeholder="请输入备注" />
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
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/system/info";

export default {
  name: "Info",
  dicts: ['sys_notice_status', 'sys_notice_type'],
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
      // 公告管理表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nTitle: undefined,
        nType: undefined,
        nContent: undefined,
        nPeople: undefined,
        nTime: undefined,
        nMender: undefined,
        createTime: undefined,
        updateTime: undefined
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
    /** 查询公告管理列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
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
        nId: null,
        nTitle: null,
        nType: null,
        nContent: null,
        nStatus: "0",
        nPeople: null,
        nTime: null,
        nMender: null,
        nRemark: null,
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
      this.ids = selection.map(item => item.nId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加公告管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const nId = row.nId || this.ids
      getInfo(nId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公告管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.nId != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },

    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        case "handleView":
          this.handleView(row);
          break;
        case "handleDelete":
          this.handleDelete(row);
          break;
        default:
          break;
      }
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const nIds = row.nId || this.ids;
      this.$modal.confirm('是否确认删除公告管理编号为"' + nIds + '"的数据项？').then(function() {
        return delInfo(nIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },


    /** 详细按钮操作 */
    handleView(row) {
      this.open = true;
      this.form = row;
      this.readOnly = false;
    },


    /** 导出按钮操作 */
    handleExport() {
      this.download('system/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
