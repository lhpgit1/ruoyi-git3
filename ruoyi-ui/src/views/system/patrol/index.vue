<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="巡查人员ID" prop="patrolStaffId">
        <el-input
          v-model="queryParams.patrolStaffId"
          placeholder="请输入巡查人员ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="巡查人姓名" prop="patrolName">
        <el-input
          v-model="queryParams.patrolName"
          placeholder="请输入巡查人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="巡查时间" prop="patrolTime">
        <el-date-picker clearable
          v-model="queryParams.patrolTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择巡查时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="巡查楼栋" prop="patrolDept">
        <el-input
          v-model="queryParams.patrolDept"
          placeholder="请输入巡查楼栋"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="巡查原由" prop="patrolMatter">
        <el-input
          v-model="queryParams.patrolMatter"
          placeholder="请输入巡查原由"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="patrolPhoneNumber">
        <el-input
          v-model="queryParams.patrolPhoneNumber"
          placeholder="请输入联系方式"
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
          v-hasPermi="['system:patrol:add']"
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
          v-hasPermi="['system:patrol:edit']"
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
          v-hasPermi="['system:patrol:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:patrol:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="patrolList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="巡查记录号" align="center" prop="patrolId" />
      <el-table-column label="巡查人员ID" align="center" prop="patrolStaffId" />
      <el-table-column label="巡查人姓名" align="center" prop="patrolName" />
      <el-table-column label="巡查时间" align="center" prop="patrolTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.patrolTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="巡查楼栋" align="center" prop="patrolDept" />
      <el-table-column label="巡查原由" align="center" prop="patrolMatter" />
      <el-table-column label="联系方式" align="center" prop="patrolPhoneNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:patrol:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:patrol:remove']"
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

    <!-- 添加或修改巡查管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="巡查人员ID" prop="patrolStaffId">
          <el-input v-model="form.patrolStaffId" placeholder="请输入巡查人员ID" />
        </el-form-item>
        <el-form-item label="巡查人姓名" prop="patrolName">
          <el-input v-model="form.patrolName" placeholder="请输入巡查人姓名" />
        </el-form-item>
        <el-form-item label="巡查时间" prop="patrolTime">
          <el-date-picker clearable
            v-model="form.patrolTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择巡查时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="巡查楼栋" prop="patrolDept">
          <el-input v-model="form.patrolDept" placeholder="请输入巡查楼栋" />
        </el-form-item>
        <el-form-item label="巡查原由" prop="patrolMatter">
          <el-input v-model="form.patrolMatter" placeholder="请输入巡查原由" />
        </el-form-item>
        <el-form-item label="联系方式" prop="patrolPhoneNumber">
          <el-input v-model="form.patrolPhoneNumber" placeholder="请输入联系方式" />
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
import { listPatrol, getPatrol, delPatrol, addPatrol, updatePatrol } from "@/api/system/patrol";

export default {
  name: "Patrol",
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
      // 巡查管理表格数据
      patrolList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        patrolStaffId: null,
        patrolName: null,
        patrolTime: null,
        patrolDept: null,
        patrolMatter: null,
        patrolPhoneNumber: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        patrolStaffId: [
          { required: true, message: "巡查人员ID不能为空", trigger: "blur" }
        ],
        patrolName: [
          { required: true, message: "巡查人姓名不能为空", trigger: "blur" }
        ],
        patrolTime: [
          { required: true, message: "巡查时间不能为空", trigger: "blur" }
        ],
        patrolDept: [
          { required: true, message: "巡查楼栋不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询巡查管理列表 */
    getList() {
      this.loading = true;
      listPatrol(this.queryParams).then(response => {
        this.patrolList = response.rows;
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
        patrolId: null,
        patrolStaffId: null,
        patrolName: null,
        patrolTime: null,
        patrolDept: null,
        patrolMatter: null,
        patrolPhoneNumber: null
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
      this.ids = selection.map(item => item.patrolId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加巡查管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const patrolId = row.patrolId || this.ids
      getPatrol(patrolId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改巡查管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.patrolId != null) {
            updatePatrol(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPatrol(this.form).then(response => {
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
      const patrolIds = row.patrolId || this.ids;
      this.$modal.confirm('是否确认删除巡查管理编号为"' + patrolIds + '"的数据项？').then(function() {
        return delPatrol(patrolIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/patrol/export', {
        ...this.queryParams
      }, `patrol_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
